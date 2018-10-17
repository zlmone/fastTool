package com.lanmosoft.service.biz;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lanmosoft.dao.mapper.ProcessActionMapper;
import com.lanmosoft.dao.model.ProcessAction;
import com.lanmosoft.model.WhereCondition;
/**
 * 服务层
 * @author su.zhang
 *
 */
@Service
public class ProcessActionService {
	@Autowired
	ProcessActionMapper processActionMapper;
    public void insert(ProcessAction t){
    	processActionMapper.insert(t);
    }
    
    public void update(ProcessAction t){
    	processActionMapper.update(t); 
    }
   
    public void updateForce(ProcessAction t){
    	processActionMapper.updateForce(t); 
    }
   
    public void delete(String id){
    	processActionMapper.delete(id);
    }
   
    public void deleteByCondition(WhereCondition wc){
    	processActionMapper.deleteByCondition(wc); 
    }
	
	public void updateByCondition(WhereCondition wc,ProcessAction t){
    	Map map  = new HashMap();
    	map.put("domain", t);
    	map.put("wc", wc);
    	processActionMapper.updateByCondition(map); 
    }
    public List<ProcessAction> query(WhereCondition wc){
    	return processActionMapper.query(wc); 
    }
    
    public int count(WhereCondition wc){
    	return processActionMapper.count(wc);
    }
   
    public ProcessAction loadById(String id){
    	return processActionMapper.loadById(id);
    }
}
