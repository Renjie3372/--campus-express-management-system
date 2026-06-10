package com.campusexpress.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.campusexpress.common.Result;
import com.campusexpress.dto.OutboundRequest;
import com.campusexpress.entity.ReceiveRecord;
import com.campusexpress.service.ReceiveRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/api")
public class ReceiveRecordController {

    @Autowired
    private ReceiveRecordService receiveRecordService;

    @PostMapping("/receive-records")
    public Result<ReceiveRecord> addReceiveRecord(@RequestBody ReceiveRecord record) {
        QueryWrapper<ReceiveRecord> wrapper = new QueryWrapper<>();
        wrapper.eq("tracking_number", record.getTrackingNumber());
        if (receiveRecordService.count(wrapper) > 0) {
            return Result.error(400, "该快递单号已入库");
        }
        
        record.setStatus("待取件");
        // 生成6位随机取件码
        String pickupCode = String.format("%06d", new Random().nextInt(1000000));
        record.setPickupCode(pickupCode);
        
        receiveRecordService.save(record);
        return Result.success(record);
    }

    @GetMapping("/receive-records")
    public Result<List<ReceiveRecord>> getReceiveRecords(
            @RequestParam(required = false) String trackingNumber,
            @RequestParam(required = false) String status) {
        QueryWrapper<ReceiveRecord> wrapper = new QueryWrapper<>();
        if (StringUtils.hasText(trackingNumber)) {
            wrapper.like("tracking_number", trackingNumber);
        }
        if (StringUtils.hasText(status)) {
            wrapper.eq("status", status);
        }
        return Result.success(receiveRecordService.list(wrapper));
    }

    @GetMapping("/my/receive-records")
    public Result<List<ReceiveRecord>> getMyReceiveRecords(@RequestParam Long userId) {
        // 简单实现：暂时要求前端传入userId查
        QueryWrapper<ReceiveRecord> wrapper = new QueryWrapper<>();
        wrapper.eq("receiver_id", userId);
        return Result.success(receiveRecordService.list(wrapper));
    }

    @PostMapping("/receive-records/outbound")
    public Result<ReceiveRecord> outbound(@RequestBody OutboundRequest request) {
        QueryWrapper<ReceiveRecord> wrapper = new QueryWrapper<>();
        wrapper.eq("tracking_number", request.getTrackingNumber());
        ReceiveRecord record = receiveRecordService.getOne(wrapper);
        
        if (record == null) {
            return Result.error(404, "未找到该快递");
        }
        if (!"待取件".equals(record.getStatus())) {
            return Result.error(400, "该快递不是待取件状态");
        }
        if (!record.getPickupCode().equals(request.getPickupCode())) {
            return Result.error(400, "取件码错误");
        }
        
        record.setStatus("已出库");
        receiveRecordService.updateById(record);
        return Result.success(record);
    }
}
