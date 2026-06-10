package com.campusexpress.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.campusexpress.entity.SendRequest;
import com.campusexpress.mapper.SendRequestMapper;
import com.campusexpress.service.SendRequestService;
import org.springframework.stereotype.Service;

@Service
public class SendRequestServiceImpl extends ServiceImpl<SendRequestMapper, SendRequest> implements SendRequestService {
}
