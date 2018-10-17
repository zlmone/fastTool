package com.lanmosoft.service.biz;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lanmosoft.dao.mapper.ChangliangshuMapper;
import com.lanmosoft.dao.model.Changliangshu;
import com.lanmosoft.model.WhereCondition;
/**
 * 服务层
 * @author su.zhang
 *
 */
@Service
public class ChangliangshuService {
	@Autowired
	ChangliangshuMapper changliangshuMapper;

    public void insert(Changliangshu t){
    	changliangshuMapper.insert(t);
    }
    
    public void update(Changliangshu t){
    	changliangshuMapper.update(t); 
    }
   
    public void updateForce(Changliangshu t){
    	changliangshuMapper.updateForce(t); 
    }
   
    public void delete(String id){
    	changliangshuMapper.delete(id);
    }
   
    public void deleteByCondition(WhereCondition wc){
    	changliangshuMapper.deleteByCondition(wc); 
    }
	
	public void updateByCondition(WhereCondition wc,Changliangshu t){
    	Map map  = new HashMap();
    	map.put("domain", t);
    	map.put("wc", wc);
    	changliangshuMapper.updateByCondition(map); 
    }
    
    public List<Changliangshu> query(WhereCondition wc){
    	return changliangshuMapper.query(wc); 
    }
    
    public int count(WhereCondition wc){
    	return changliangshuMapper.count(wc);
    }
   
    public Changliangshu loadById(String id){
    	return changliangshuMapper.loadById(id);
    }
}
