package com.xzsd.pc.customer.controller;


import com.neusoft.core.restful.AppResponse;
import com.xzsd.pc.customer.entity.CustomerInfo;
import com.xzsd.pc.customer.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 客户管理的增删查改
 * @author zhong
 * @date 2020-04-04
 */
@RestController
@RequestMapping("/customer")
public class CustomerController {

    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @Resource
    private CustomerService customerService;
    
    @PostMapping(value = "listCustomerByPage")
   public AppResponse listCustomerByPage(CustomerInfo customerInfo){
        try{
            return customerService.listCustomerByPage(customerInfo);
        }catch (Exception e){
            logger.error("查询客户列表异常",e);
            System.out.println(e.toString());
            throw e;
        }
    }

}
