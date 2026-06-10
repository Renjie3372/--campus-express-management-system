package com.campusexpress.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.campusexpress.common.Result;
import com.campusexpress.dto.AssignCourierRequest;
import com.campusexpress.entity.SendRequest;
import com.campusexpress.service.SendRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.util.StringUtils;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SendRequestController {

    @Autowired
    private SendRequestService sendRequestService;

    @PostMapping("/send-requests")
    public Result<SendRequest> addSendRequest(@RequestBody SendRequest request, @RequestParam(required = false) Long senderId) {
        if (senderId != null) {
            request.setSenderId(senderId);
        }
        request.setStatus("已提交");
        sendRequestService.save(request);
        return Result.success(request);
    }

    @GetMapping("/send-requests")
    public Result<List<SendRequest>> getSendRequests(
            @RequestParam(required = false) String status,
            @RequestParam(required = false) Long senderId) {
        QueryWrapper<SendRequest> wrapper = new QueryWrapper<>();
        if (StringUtils.hasText(status)) {
            wrapper.eq("status", status);
        }
        if (senderId != null) {
            wrapper.eq("sender_id", senderId);
        }
        return Result.success(sendRequestService.list(wrapper));
    }

    @GetMapping("/my/send-requests")
    public Result<List<SendRequest>> getMySendRequests(@RequestParam Long userId) {
        QueryWrapper<SendRequest> wrapper = new QueryWrapper<>();
        wrapper.eq("sender_id", userId);
        return Result.success(sendRequestService.list(wrapper));
    }

    @PostMapping("/send-requests/{id}/assign")
    public Result<SendRequest> assignCourier(@PathVariable Long id, @RequestBody AssignCourierRequest request) {
        SendRequest sendRequest = sendRequestService.getById(id);
        if (sendRequest == null) {
            return Result.error(404, "记录不存在");
        }
        sendRequest.setCourierId(request.getCourierId());
        sendRequest.setStatus("已分配");
        sendRequestService.updateById(sendRequest);
        return Result.success(sendRequest);
    }

    @GetMapping("/my/send-tasks")
    public Result<List<SendRequest>> getMySendTasks(@RequestParam Long courierId) {
        QueryWrapper<SendRequest> wrapper = new QueryWrapper<>();
        wrapper.eq("courier_id", courierId);
        return Result.success(sendRequestService.list(wrapper));
    }

    @PostMapping("/send-requests/{id}/pickup")
    public Result<SendRequest> pickup(@PathVariable Long id) {
        SendRequest sendRequest = sendRequestService.getById(id);
        if (sendRequest == null) {
            return Result.error(404, "记录不存在");
        }
        sendRequest.setStatus("已揽件");
        sendRequestService.updateById(sendRequest);
        return Result.success(sendRequest);
    }
}
