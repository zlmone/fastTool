package com.lanmosoft.service.biz;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lanmosoft.dao.mapper.CustomerMapper;
import com.lanmosoft.dao.model.Customer;
import com.lanmosoft.dao.model.Customer;
import com.lanmosoft.model.WhereCondition;
/**
 * 服务层
 * @author luohuiqi
 *
 */
@Service
public class CustomerService {
	@Autowired
	CustomerMapper customerMapper;
	  public void insert(Customer t){
	    	customerMapper.insert(t);
	    }
	    
	    public void update(Customer t){
	    	customerMapper.update(t); 
	    }
	   
	    public void updateForce(Customer t){
	    	customerMapper.updateForce(t); 
	    }
	   
	    public void delete(String id){
	    	customerMapper.delete(id);
	    }
}
