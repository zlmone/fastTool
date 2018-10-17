package com.lanmosoft.service.biz;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lanmosoft.dao.mapper.Bill_infoMapper;
import com.lanmosoft.dao.model.Bill_info;
import com.lanmosoft.model.WhereCondition;
/**
 * 服务层
 * @author su.zhang
 *
 */
@Service
public class Bill_infoService {
	@Autowired
	Bill_infoMapper bill_infoMapper;
    public void insert(Bill_info t){
    	bill_infoMapper.insert(t);
    }
    
    public void update(Bill_info t){
    	bill_infoMapper.update(t); 
    }
   
    public void updateForce(Bill_info t){
    	bill_infoMapper.updateForce(t); 
    }
   
    public void delete(String id){
    	bill_infoMapper.delete(id);
    }
   
    public void deleteByCondition(WhereCondition wc){
    	bill_infoMapper.deleteByCondition(wc); 
    }
	
	public void updateByCondition(WhereCondition wc,Bill_info t){
    	Map map  = new HashMap();
    	map.put("domain", t);
    	map.put("wc", wc);
    	bill_infoMapper.updateByCondition(map); 
    }
    public List<Bill_info> query(WhereCondition wc){
    	return bill_infoMapper.query(wc); 
    }
    
    public int count(WhereCondition wc){
    	return bill_infoMapper.count(wc);
    }
   
    public Bill_info loadById(String id){
    	return bill_infoMapper.loadById(id);
    }
}
