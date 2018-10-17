package com.lanmosoft.service.biz;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lanmosoft.dao.mapper.ProcessappMapper;
import com.lanmosoft.dao.model.Processapp;
import com.lanmosoft.model.WhereCondition;
/**
 * 服务层
 * @author su.zhang
 *
 */
@Service
public class ProcessappService {
	@Autowired
	ProcessappMapper processappMapper;
    public void insert(Processapp t){
    	processappMapper.insert(t);
    }
    
    public void update(Processapp t){
    	processappMapper.update(t); 
    }
   
    public void updateForce(Processapp t){
    	processappMapper.updateForce(t); 
    }
   
    public void delete(String id){
    	processappMapper.delete(id);
    }
   
    public void deleteByCondition(WhereCondition wc){
    	processappMapper.deleteByCondition(wc); 
    }
	
	public void updateByCondition(WhereCondition wc,Processapp t){
    	Map map  = new HashMap();
    	map.put("domain", t);
    	map.put("wc", wc);
    	processappMapper.updateByCondition(map); 
    }
    public List<Processapp> query(WhereCondition wc){
    	return processappMapper.query(wc); 
    }
    
    public int count(WhereCondition wc){
    	return processappMapper.count(wc);
    }
   
    public Processapp loadById(String id){
    	return processappMapper.loadById(id);
    }
}
