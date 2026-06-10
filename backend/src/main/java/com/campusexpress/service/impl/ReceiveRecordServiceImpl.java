package com.campusexpress.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.campusexpress.entity.ReceiveRecord;
import com.campusexpress.mapper.ReceiveRecordMapper;
import com.campusexpress.service.ReceiveRecordService;
import org.springframework.stereotype.Service;

@Service
public class ReceiveRecordServiceImpl extends ServiceImpl<ReceiveRecordMapper, ReceiveRecord> implements ReceiveRecordService {
}
