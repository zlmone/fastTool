package com.lanmosoft.service.biz;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lanmosoft.dao.mapper.Base_infoMapper;
import com.lanmosoft.dao.model.Base_info;
import com.lanmosoft.model.WhereCondition;
/**
 * 服务层
 * @author su.zhang
 *
 */
@Service
public class Base_infoService {
	@Autowired
	Base_infoMapper base_infoMapper;
    public void insert(Base_info t){
    	base_infoMapper.insert(t);
    }
    
    public void update(Base_info t){
    	base_infoMapper.update(t); 
    }
   
    public void updateForce(Base_info t){
    	base_infoMapper.updateForce(t); 
    }
   
    public void delete(String id){
    	base_infoMapper.delete(id);
    }
   
    public void deleteByCondition(WhereCondition wc){
    	base_infoMapper.deleteByCondition(wc); 
    }
	
	public void updateByCondition(WhereCondition wc,Base_info t){
    	Map map  = new HashMap();
    	map.put("domain", t);
    	map.put("wc", wc);
    	base_infoMapper.updateByCondition(map); 
    }
    public List<Base_info> query(WhereCondition wc){
    	return base_infoMapper.query(wc); 
    }
    
    public int count(WhereCondition wc){
    	return base_infoMapper.count(wc);
    }
   
    public Base_info loadById(String id){
    	return base_infoMapper.loadById(id);
    }
}
