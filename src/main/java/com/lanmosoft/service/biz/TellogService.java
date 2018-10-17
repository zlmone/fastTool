package com.lanmosoft.service.biz;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lanmosoft.dao.mapper.TellogMapper;
import com.lanmosoft.dao.model.Tellog;
import com.lanmosoft.model.WhereCondition;
/**
 * 服务层
 * @author su.zhang
 *
 */
@Service
public class TellogService {
	@Autowired
	TellogMapper tellogMapper;
    public void insert(Tellog t){
    	tellogMapper.insert(t);
    }
    
    public void update(Tellog t){
    	tellogMapper.update(t); 
    }
   
    public void updateForce(Tellog t){
    	tellogMapper.updateForce(t); 
    }
   
    public void delete(String id){
    	tellogMapper.delete(id);
    }
   
    public void deleteByCondition(WhereCondition wc){
    	tellogMapper.deleteByCondition(wc); 
    }
	
	public void updateByCondition(WhereCondition wc,Tellog t){
    	Map map  = new HashMap();
    	map.put("domain", t);
    	map.put("wc", wc);
    	tellogMapper.updateByCondition(map); 
    }
    public List<Tellog> query(WhereCondition wc){
    	return tellogMapper.query(wc); 
    }
    
    public int count(WhereCondition wc){
    	return tellogMapper.count(wc);
    }
   
    public Tellog loadById(String id){
    	return tellogMapper.loadById(id);
    }
}
