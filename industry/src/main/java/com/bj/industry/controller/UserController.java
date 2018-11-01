package com.bj.industry.controller;

import com.bj.industry.common.base.PageVO;
import com.bj.industry.common.base.ResponseVO;
import com.bj.industry.common.constant.BusinessConstant;
import com.bj.industry.common.exception.BusinessException;
import com.bj.industry.service.IUserService;
import com.bj.industry.vo.UserInfoVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/user")
@Api(value = "user manager", description = "用户相关接口[by:fwd]")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private IUserService userService;

    @ApiOperation(nickname = "userInfo", value = "用户信息", notes = "查询用户信息", httpMethod = "GET")
    @ApiImplicitParams({ @ApiImplicitParam(name = "name", value = "用户名称", required = true, dataType = "String", paramType = "query") })
    @GetMapping("/center/get")
    public ResponseVO getUser(String name) throws Exception {
        LOGGER.info("根据用户名:{} 查询用户信息", name);
        if (StringUtils.isBlank(name)) {
            throw new BusinessException(BusinessConstant.PARAMETER_ERROR);
        }

        UserInfoVO userByName = userService.findUserByName(name);
        return ResponseVO.OfMessage(BusinessConstant.SUCCESS, userByName);
    }


    @ApiOperation(nickname = "delete", value = "删除用户", notes = "逻辑删除用户", httpMethod = "GET")
    @ApiImplicitParams({ @ApiImplicitParam(name = "name", value = "用户名称", required = true, dataType = "String", paramType = "query") })
    @GetMapping("/center/delete")
    public ResponseVO deleteUser(String name) throws BusinessException {
        LOGGER.info("根据用户名:{} 删除用户信息", name);
        if (StringUtils.isBlank(name)) {
            throw new BusinessException(BusinessConstant.PARAMETER_ERROR);
        }
        userService.deleteUserInfo(name);
        return ResponseVO.OfMessage(BusinessConstant.SUCCESS);
    }

    @ApiOperation(nickname = "userList", value = "用户列表信息", notes = "查询用户信息列表(包含已删除用户)", httpMethod = "POST")
    @ApiImplicitParams({ @ApiImplicitParam(name = "name", value = "用户名称", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "page", value = "当前页,默认第一页", dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "number", value = "每页显示多少条记录,默认10条", dataType = "int", paramType = "query")
    })
    @RequestMapping("/center/list")
    public ResponseVO getList(String name, @RequestParam(defaultValue = "0") Integer page,
                              @RequestParam(defaultValue = "10") Integer number) throws BusinessException {
        LOGGER.info("用户:{} 分页获取用户列表,page:{} number:{} ", name, page, number);
        if (StringUtils.isBlank(name)) {
            throw new BusinessException(BusinessConstant.PARAMETER_ERROR);
        }
        PageVO userInfoVOList = userService.getUserPage(name, page, number);
        return ResponseVO.OfMessage(BusinessConstant.SUCCESS, userInfoVOList);
    }

}
