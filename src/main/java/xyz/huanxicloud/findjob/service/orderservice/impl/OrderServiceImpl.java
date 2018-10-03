package xyz.huanxicloud.findjob.service.orderservice.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.huanxicloud.findjob.mapper.POrderMapper;
import xyz.huanxicloud.findjob.service.orderservice.OerderService;

@Service
public class OrderServiceImpl implements OerderService {
    @Autowired
    POrderMapper pOrderMapper;

}
