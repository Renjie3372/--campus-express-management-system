package com.campusexpress.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.campusexpress.common.Result;
import com.campusexpress.service.ExceptionRecordService;
import com.campusexpress.service.ReceiveRecordService;
import com.campusexpress.service.SendRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class DashboardController {

    @Autowired
    private ReceiveRecordService receiveRecordService;
    @Autowired
    private SendRequestService sendRequestService;
    @Autowired
    private ExceptionRecordService exceptionRecordService;

    @GetMapping("/dashboard/summary")
    public Result<Map<String, Object>> getSummary() {
        QueryWrapper<com.campusexpress.entity.ReceiveRecord> receiveWrapper = new QueryWrapper<>();
        receiveWrapper.eq("status", "待取件");
        long pendingReceiveCount = receiveRecordService.count(receiveWrapper);

        QueryWrapper<com.campusexpress.entity.SendRequest> sendWrapper = new QueryWrapper<>();
        long sendRequestCount = sendRequestService.count(sendWrapper);

        QueryWrapper<com.campusexpress.entity.ExceptionRecord> exceptionWrapper = new QueryWrapper<>();
        long exceptionCount = exceptionRecordService.count(exceptionWrapper);

        Map<String, Object> data = new HashMap<>();
        data.put("pending_receive_count", pendingReceiveCount);
        data.put("send_request_count", sendRequestCount);
        data.put("exception_count", exceptionCount);

        return Result.success(data);
    }
}
