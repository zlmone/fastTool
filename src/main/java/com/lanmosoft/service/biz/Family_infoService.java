package com.lanmosoft.service.biz;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lanmosoft.dao.mapper.Family_infoMapper;
import com.lanmosoft.dao.model.Family_info;
import com.lanmosoft.model.WhereCondition;
/**
 * 服务层
 * @author su.zhang
 *
 */
@Service
public class Family_infoService {
	@Autowired
	Family_infoMapper family_infoMapper;
    public void insert(Family_info t){
    	family_infoMapper.insert(t);
    }
    
    public void update(Family_info t){
    	family_infoMapper.update(t); 
    }
   
    public void updateForce(Family_info t){
    	family_infoMapper.updateForce(t); 
    }
   
    public void delete(String id){
    	family_infoMapper.delete(id);
    }
   
    public void deleteByCondition(WhereCondition wc){
    	family_infoMapper.deleteByCondition(wc); 
    }
	
	public void updateByCondition(WhereCondition wc,Family_info t){
    	Map map  = new HashMap();
    	map.put("domain", t);
    	map.put("wc", wc);
    	family_infoMapper.updateByCondition(map); 
    }
    public List<Family_info> query(WhereCondition wc){
    	return family_infoMapper.query(wc); 
    }
    
    public int count(WhereCondition wc){
    	return family_infoMapper.count(wc);
    }
   
    public Family_info loadById(String id){
    	return family_infoMapper.loadById(id);
    }
}
