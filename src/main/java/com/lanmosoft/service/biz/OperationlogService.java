package com.lanmosoft.service.biz;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lanmosoft.dao.mapper.OperationlogMapper;
import com.lanmosoft.dao.model.Operationlog;
import com.lanmosoft.model.WhereCondition;
/**
 * 服务层
 * @author su.zhang
 *
 */
@Service
public class OperationlogService {
	@Autowired
	OperationlogMapper operationlogMapper;
    public void insert(Operationlog t){
    	operationlogMapper.insert(t);
    }
    
    public void update(Operationlog t){
    	operationlogMapper.update(t); 
    }
   
    public void updateForce(Operationlog t){
    	operationlogMapper.updateForce(t); 
    }
   
    public void delete(String id){
    	operationlogMapper.delete(id);
    }
   
    public void deleteByCondition(WhereCondition wc){
    	operationlogMapper.deleteByCondition(wc); 
    }
	
	public void updateByCondition(WhereCondition wc,Operationlog t){
    	Map map  = new HashMap();
    	map.put("domain", t);
    	map.put("wc", wc);
    	operationlogMapper.updateByCondition(map); 
    }
    public List<Operationlog> query(WhereCondition wc){
    	return operationlogMapper.query(wc); 
    }
    
    public int count(WhereCondition wc){
    	return operationlogMapper.count(wc);
    }
   
    public Operationlog loadById(String id){
    	return operationlogMapper.loadById(id);
    }
}
