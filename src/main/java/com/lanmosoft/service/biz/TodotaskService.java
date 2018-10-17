package com.lanmosoft.service.biz;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lanmosoft.dao.mapper.TodotaskMapper;
import com.lanmosoft.dao.model.Todotask;
import com.lanmosoft.model.WhereCondition;
/**
 * 服务层
 * @author su.zhang
 *
 */
@Service
public class TodotaskService {
	@Autowired
	TodotaskMapper todotaskMapper;
    public void insert(Todotask t){
    	todotaskMapper.insert(t);
    }
    
    public void update(Todotask t){
    	todotaskMapper.update(t); 
    }
   
    public void updateForce(Todotask t){
    	todotaskMapper.updateForce(t); 
    }
   
    public void delete(String id){
    	todotaskMapper.delete(id);
    }
   
    public void deleteByCondition(WhereCondition wc){
    	todotaskMapper.deleteByCondition(wc); 
    }
	
	public void updateByCondition(WhereCondition wc,Todotask t){
    	Map map  = new HashMap();
    	map.put("domain", t);
    	map.put("wc", wc);
    	todotaskMapper.updateByCondition(map); 
    }
    public List<Todotask> query(WhereCondition wc){
    	return todotaskMapper.query(wc); 
    }
    
    public int count(WhereCondition wc){
    	return todotaskMapper.count(wc);
    }
   
    public Todotask loadById(String id){
    	return todotaskMapper.loadById(id);
    }
}
