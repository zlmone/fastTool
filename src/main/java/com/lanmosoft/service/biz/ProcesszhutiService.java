package com.lanmosoft.service.biz;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lanmosoft.dao.mapper.ProcesszhutiMapper;
import com.lanmosoft.dao.model.Processzhuti;
import com.lanmosoft.model.WhereCondition;
/**
 * 服务层
 * @author su.zhang
 *
 */
@Service
public class ProcesszhutiService {
	@Autowired
	ProcesszhutiMapper processzhutiMapper;
    public void insert(Processzhuti t){
    	processzhutiMapper.insert(t);
    }
    
    public void update(Processzhuti t){
    	processzhutiMapper.update(t); 
    }
   
    public void updateForce(Processzhuti t){
    	processzhutiMapper.updateForce(t); 
    }
   
    public void delete(String id){
    	processzhutiMapper.delete(id);
    }
   
    public void deleteByCondition(WhereCondition wc){
    	processzhutiMapper.deleteByCondition(wc); 
    }
	
	public void updateByCondition(WhereCondition wc,Processzhuti t){
    	Map map  = new HashMap();
    	map.put("domain", t);
    	map.put("wc", wc);
    	processzhutiMapper.updateByCondition(map); 
    }
    public List<Processzhuti> query(WhereCondition wc){
    	return processzhutiMapper.query(wc); 
    }
    
    public int count(WhereCondition wc){
    	return processzhutiMapper.count(wc);
    }
   
    public Processzhuti loadById(String id){
    	return processzhutiMapper.loadById(id);
    }
}
