package com.jiuxian.service.impl;

import com.jiuxian.service.DoSthService;
import org.springframework.stereotype.Service;

/**
 * Author: LIU ZEJUN
 * Date: 2019-01-23 11:02:00
 * Comment:
 */

@Service
public class DoSthServiceImpl implements DoSthService {

    @Override
    public void doSth() {
        System.out.println("do sth ....");
    }
    
}
