package com.xzsd.pc.customer.dao;


import com.xzsd.pc.customer.entity.CustomerInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 *客户管理dao层
 * @author zhong
 * @date  2020-04-04
 */
@Mapper
public interface CustomerDao {
    /**
     * 查询客户列表
     * @param customerInfo
     * @return
     */
    List<CustomerInfo> listCustomerByPage(CustomerInfo customerInfo);
}
