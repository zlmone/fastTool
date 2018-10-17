package com.lanmosoft.service.biz;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lanmosoft.dao.mapper.OrderMapper;
import com.lanmosoft.dao.model.Order;
import com.lanmosoft.dao.model.TongJi;
import com.lanmosoft.model.WhereCondition;
/**
 * 服务层
 * @author su.zhang
 *
 */
@Service
public class OrderService {
	@Autowired
	OrderMapper orderMapper;
    public void insert(Order t){
    	orderMapper.insert(t);
    }
    
    public void update(Order t){
    	orderMapper.update(t); 
    }
   
    public void updateForce(Order t){
    	orderMapper.updateForce(t); 
    }
   
    public void delete(String id){
    	orderMapper.delete(id);
    }
   
    public void deleteByCondition(WhereCondition wc){
    	orderMapper.deleteByCondition(wc); 
    }
	
	public void updateByCondition(WhereCondition wc,Order t){
    	Map map  = new HashMap();
    	map.put("domain", t);
    	map.put("wc", wc);
    	orderMapper.updateByCondition(map); 
    }
    public List<Order> query(WhereCondition wc){
    	return orderMapper.query(wc); 
    }
    
    public int count(WhereCondition wc){
    	return orderMapper.count(wc);
    }
   
    public Order loadById(String id){
    	return orderMapper.loadById(id);
    }
    
    public TongJi tongji(WhereCondition wc){
    	return orderMapper.tongji(wc);
    }
    
    public TongJi tongji1(WhereCondition wc){
    	return orderMapper.tongji1(wc);
    }
}
