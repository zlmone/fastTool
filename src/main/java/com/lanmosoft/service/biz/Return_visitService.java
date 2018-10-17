package com.lanmosoft.service.biz;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lanmosoft.dao.mapper.Return_visitMapper;
import com.lanmosoft.dao.model.Return_visit;
import com.lanmosoft.model.WhereCondition;
/**
 * 服务层
 * @author su.zhang
 *
 */
@Service
public class Return_visitService {
	@Autowired
	Return_visitMapper return_visitMapper;
    public void insert(Return_visit t){
    	return_visitMapper.insert(t);
    }
    
    public void update(Return_visit t){
    	return_visitMapper.update(t); 
    }
   
    public void updateForce(Return_visit t){
    	return_visitMapper.updateForce(t); 
    }
   
    public void delete(String id){
    	return_visitMapper.delete(id);
    }
   
    public void deleteByCondition(WhereCondition wc){
    	return_visitMapper.deleteByCondition(wc); 
    }
	
	public void updateByCondition(WhereCondition wc,Return_visit t){
    	Map map  = new HashMap();
    	map.put("domain", t);
    	map.put("wc", wc);
    	return_visitMapper.updateByCondition(map); 
    }
    public List<Return_visit> query(WhereCondition wc){
    	return return_visitMapper.query(wc); 
    }
    
    public int count(WhereCondition wc){
    	return return_visitMapper.count(wc);
    }
   
    public Return_visit loadById(String id){
    	return return_visitMapper.loadById(id);
    }
}
