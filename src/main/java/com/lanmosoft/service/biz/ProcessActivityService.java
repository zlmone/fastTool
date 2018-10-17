package com.lanmosoft.service.biz;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lanmosoft.dao.mapper.ProcessActivityMapper;
import com.lanmosoft.dao.model.ProcessActivity;
import com.lanmosoft.model.WhereCondition;
/**
 * 服务层
 * @author su.zhang
 *
 */
@Service
public class ProcessActivityService {
	@Autowired
	ProcessActivityMapper processActivityMapper;
    public void insert(ProcessActivity t){
    	processActivityMapper.insert(t);
    }
    
    public void update(ProcessActivity t){
    	processActivityMapper.update(t); 
    }
   
    public void updateForce(ProcessActivity t){
    	processActivityMapper.updateForce(t); 
    }
   
    public void delete(String id){
    	processActivityMapper.delete(id);
    }
   
    public void deleteByCondition(WhereCondition wc){
    	processActivityMapper.deleteByCondition(wc); 
    }
	
	public void updateByCondition(WhereCondition wc,ProcessActivity t){
    	Map map  = new HashMap();
    	map.put("domain", t);
    	map.put("wc", wc);
    	processActivityMapper.updateByCondition(map); 
    }
    public List<ProcessActivity> query(WhereCondition wc){
    	return processActivityMapper.query(wc); 
    }
    
    public int count(WhereCondition wc){
    	return processActivityMapper.count(wc);
    }
   
    public ProcessActivity loadById(String id){
    	return processActivityMapper.loadById(id);
    }
}
