package com.nova.sbnetty.web;

import com.nova.sbnetty.base.BaseResponse;
import com.nova.sbnetty.base.SendMsgReqVO;
import com.nova.sbnetty.base.SendMsgResVO;
import com.nova.sbnetty.base.StatusEnum;
import com.nova.sbnetty.protocol.CustomProtocol;
import com.nova.sbnetty.server.HeartbeatServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class ClientSendController {

    @Autowired
    private CounterService counterService;
    @Autowired
    private HeartbeatServer heartbeatClient;
    @RequestMapping("sendMsg")
    public BaseResponse<SendMsgResVO> sendMsg(@RequestBody SendMsgReqVO sendMsgReqVO) {

        BaseResponse<SendMsgResVO> res = new BaseResponse();
        heartbeatClient.sendMsg(new CustomProtocol(sendMsgReqVO.getId(), sendMsgReqVO.getMsg()));
        counterService.increment("counter.server.push.count");

        SendMsgResVO sendMsgResVO = new SendMsgResVO();
        sendMsgResVO.setMsg("OK");
        res.setCode(StatusEnum.SUCCESS.getCode());
        res.setMsg(StatusEnum.SUCCESS.getMessage());
        res.setDataBody(sendMsgResVO);
        return res;
    }

}
