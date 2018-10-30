package com.bj.industry.controller;

import com.bj.industry.common.base.ResponseVO;
import com.bj.industry.common.constant.BusinessConstant;
import com.bj.industry.common.exception.BusinessException;
import com.bj.industry.service.IUserService;
import com.bj.industry.vo.UserInfoVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
@Api(value = "user manager", description = "用户相关接口[by:fwd]")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private IUserService userService;

    @ApiOperation(nickname = "userInfo", value = "用户信息", notes = "查询用户信息", httpMethod = "GET")
    @GetMapping("/center/get")
    public ResponseVO get(HttpServletRequest request, String name) throws Exception {
        LOGGER.info("根据用户名:{} 查询用户信息", name);
        if (StringUtils.isBlank(name)) {
            throw new BusinessException(BusinessConstant.PARAMETER_ERROR);
        }

        UserInfoVO userByName = userService.findUserByName(name);
        return ResponseVO.OfMessage(BusinessConstant.SUCCESS, userByName);
    }

}
