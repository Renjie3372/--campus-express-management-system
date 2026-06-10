package com.campusexpress.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.campusexpress.common.Result;
import com.campusexpress.dto.HandleExceptionRequest;
import com.campusexpress.entity.ExceptionRecord;
import com.campusexpress.service.ExceptionRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ExceptionRecordController {

    @Autowired
    private ExceptionRecordService exceptionRecordService;

    @PostMapping("/exception-records")
    public Result<ExceptionRecord> addExceptionRecord(@RequestBody ExceptionRecord record) {
        record.setStatus("待处理");
        exceptionRecordService.save(record);
        return Result.success(record);
    }

    @GetMapping("/exception-records")
    public Result<List<ExceptionRecord>> getExceptionRecords(
            @RequestParam(required = false) String trackingNumber,
            @RequestParam(required = false) String status) {
        QueryWrapper<ExceptionRecord> wrapper = new QueryWrapper<>();
        if (StringUtils.hasText(trackingNumber)) {
            wrapper.like("tracking_number", trackingNumber);
        }
        if (StringUtils.hasText(status)) {
            wrapper.eq("status", status);
        }
        return Result.success(exceptionRecordService.list(wrapper));
    }

    @PostMapping("/exception-records/{id}/handle")
    public Result<ExceptionRecord> handleException(@PathVariable Long id, @RequestBody HandleExceptionRequest request) {
        ExceptionRecord record = exceptionRecordService.getById(id);
        if (record == null) {
            return Result.error(404, "记录不存在");
        }
        record.setResult(request.getResult());
        record.setStatus(request.getStatus());
        record.setHandleTime(new Date());
        exceptionRecordService.updateById(record);
        return Result.success(record);
    }
}
