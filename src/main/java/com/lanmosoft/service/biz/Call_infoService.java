package com.lanmosoft.service.biz;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lanmosoft.dao.mapper.Call_infoMapper;
import com.lanmosoft.dao.model.Call_info;
import com.lanmosoft.model.WhereCondition;
/**
 * 服务层
 * @author su.zhang
 *
 */
@Service
public class Call_infoService {
	@Autowired
	Call_infoMapper call_infoMapper;
    public void insert(Call_info t){
    	call_infoMapper.insert(t);
    }
    
    public void update(Call_info t){
    	call_infoMapper.update(t); 
    }
   
    public void updateForce(Call_info t){
    	call_infoMapper.updateForce(t); 
    }
   
    public void delete(String id){
    	call_infoMapper.delete(id);
    }
   
    public void deleteByCondition(WhereCondition wc){
    	call_infoMapper.deleteByCondition(wc); 
    }
	
	public void updateByCondition(WhereCondition wc,Call_info t){
    	Map map  = new HashMap();
    	map.put("domain", t);
    	map.put("wc", wc);
    	call_infoMapper.updateByCondition(map); 
    }
    public List<Call_info> query(WhereCondition wc){
    	return call_infoMapper.query(wc); 
    }
    
    public int count(WhereCondition wc){
    	return call_infoMapper.count(wc);
    }
   
    public Call_info loadById(String id){
    	return call_infoMapper.loadById(id);
    }
}
