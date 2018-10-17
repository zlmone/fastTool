package com.lanmosoft.service.biz;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lanmosoft.dao.mapper.TelinfoMapper;
import com.lanmosoft.dao.model.Telinfo;
import com.lanmosoft.model.WhereCondition;
/**
 * 服务层
 * @author su.zhang
 *
 */
@Service
public class TelinfoService {
	@Autowired
	TelinfoMapper telinfoMapper;
    public void insert(Telinfo t){
    	telinfoMapper.insert(t);
    }
    
    public void update(Telinfo t){
    	telinfoMapper.update(t); 
    }
   
    public void updateForce(Telinfo t){
    	telinfoMapper.updateForce(t); 
    }
   
    public void delete(String id){
    	telinfoMapper.delete(id);
    }
   
    public void deleteByCondition(WhereCondition wc){
    	telinfoMapper.deleteByCondition(wc); 
    }
	
	public void updateByCondition(WhereCondition wc,Telinfo t){
    	Map map  = new HashMap();
    	map.put("domain", t);
    	map.put("wc", wc);
    	telinfoMapper.updateByCondition(map); 
    }
    public List<Telinfo> query(WhereCondition wc){
    	return telinfoMapper.query(wc); 
    }
    
    public int count(WhereCondition wc){
    	return telinfoMapper.count(wc);
    }
   
    public Telinfo loadById(String id){
    	return telinfoMapper.loadById(id);
    }
}
