package com.nova.sbnetty.base;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

/**
 * @ClassName SendMsgReqVO
 * @Description TODO
 * @Author Lyn
 * @Date 2019/3/1 0001 下午 5:12
 * @Version 1.0
 */
public class SendMsgReqVO extends BaseRequest {

    @NotNull(message = "msg 不能为空")
    @ApiModelProperty(required = true, value = "msg", example = "hello")
    private String msg ;

    @NotNull(message = "id 不能为空")
    @ApiModelProperty(required = true, value = "id", example = "11")
    private long id ;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
