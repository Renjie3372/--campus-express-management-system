package com.campusexpress.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.campusexpress.entity.ExceptionRecord;
import com.campusexpress.mapper.ExceptionRecordMapper;
import com.campusexpress.service.ExceptionRecordService;
import org.springframework.stereotype.Service;

@Service
public class ExceptionRecordServiceImpl extends ServiceImpl<ExceptionRecordMapper, ExceptionRecord> implements ExceptionRecordService {
}
