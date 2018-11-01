package com.bj.industry.common.base;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

public class PageVO<T> implements Serializable {

    private static final long serialVersionUID = 1084868840259756157L;

    /**
     * 默认记录数
     */
    private static final int DEFAULT_PAGE_SIZE = 10;

    @ApiModelProperty(value = "页码")
    private int pageNo = 1;

    @ApiModelProperty("每页记录数")
    private int pageSize = DEFAULT_PAGE_SIZE;

    /**
     * 数据列表
     */
    private List<T> result = Collections.emptyList();

    @ApiModelProperty("记录总条数")
    private int totalCount = -1;

    @ApiModelProperty("总页数")
    private int totalPages = -1;

    @ApiModelProperty("是否还有下一页")
    private boolean hasNext;

    @ApiModelProperty("下页的页号,序号从1")
    private int nextPage;

    @ApiModelProperty("是否还有上一页")
    private boolean hasPre = false;

    @ApiModelProperty("上页的页号,序号从1开始")
    private int prePage;

    /**
     * 当前页实际的记录数
     */
    @ApiModelProperty("当前页实际的记录数")
    protected int numOfElements;

    public PageVO() {
        hasNext = false;
    }

    PageVO(int pageNo, int pageSize, List<T> results, int totalCount) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.result = results;
        this.totalCount = totalCount;
        int count = this.totalCount / this.pageSize;
        if (this.totalCount % this.pageSize > 0) {
            count++;
        }
        this.totalPages = count;
        //////////////// 计算总页数////////////////////////
        hasNext = false;
        this.hasNext = (this.pageNo + 1) <= this.totalPages;
        this.nextPage = this.hasNext ? this.pageNo + 1 : this.pageNo;
        this.hasPre = this.pageNo != 1;
        this.prePage = this.hasPre ? this.pageNo - 1 : this.pageNo;
        this.numOfElements = this.totalCount;
    }


    public static Builder newBuilder() {
        return new Builder();
    }

    public int getPageNo() {
        return this.pageNo;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public List<T> getResult() {
        return this.result;
    }

    public int getTotalCount() {
        return this.totalCount;
    }

    public int getTotalPages() {
        return this.totalPages;
    }

    public boolean isHasNext() {
        return this.hasNext;
    }

    public int getNextPage() {
        return this.nextPage;
    }

    public boolean isHasPre() {
        return this.hasPre;
    }

    public int getPrePage() {
        return this.prePage;
    }

    public int getNumOfElements() {
        return this.numOfElements;
    }


    public static final class Builder {
        private int pageNo;
        private int pageSize;
        private  List<?> result;
        private int totalCount;
        private int totalPages;
        private boolean hasNext;
        private int nextPage;
        private boolean hasPre;
        private int prePage;
        private int numOfElements;

        private Builder() {
        }

        public Builder pageNo(int pageNo) {
            this.pageNo = pageNo;
            return this;
        }

        public Builder pageSize(int pageSize) {
            this.pageSize = pageSize;
            return this;
        }

        public Builder result(List<?> result) {
            this.result = result;
            return this;
        }

        public Builder totalCount(int totalCount) {
            this.totalCount = totalCount;
            return this;
        }

        public Builder totalPages(int totalPages) {
            this.totalPages = totalPages;
            return this;
        }

        public Builder hasNext(boolean hasNext) {
            this.hasNext = hasNext;
            return this;
        }

        public Builder nextPage(int nextPage) {
            this.nextPage = nextPage;
            return this;
        }

        public Builder hasPre(boolean hasPre) {
            this.hasPre = hasPre;
            return this;
        }

        public Builder prePage(int prePage) {
            this.prePage = prePage;
            return this;
        }

        public Builder numOfElements(int numOfElements) {
            this.numOfElements = numOfElements;
            return this;
        }

        public <T> PageVO build() {
            return new PageVO(this.pageNo, this.pageSize, this.result, this.totalCount);
        }
    }
}
