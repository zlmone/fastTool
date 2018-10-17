package com.lanmosoft.service.biz;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lanmosoft.dao.mapper.OnwindctrMapper;
import com.lanmosoft.dao.model.Onwindctr;
import com.lanmosoft.model.WhereCondition;
/**
 * 服务层
 * @author su.zhang
 *
 */
@Service
public class OnwindctrService {
	@Autowired
	OnwindctrMapper onwindctrMapper;
    public void insert(Onwindctr t){
    	onwindctrMapper.insert(t);
    }
    
    public void update(Onwindctr t){
    	onwindctrMapper.update(t); 
    }
   
    public void updateForce(Onwindctr t){
    	onwindctrMapper.updateForce(t); 
    }
   
    public void delete(String id){
    	onwindctrMapper.delete(id);
    }
   
    public void deleteByCondition(WhereCondition wc){
    	onwindctrMapper.deleteByCondition(wc); 
    }
	
	public void updateByCondition(WhereCondition wc,Onwindctr t){
    	Map map  = new HashMap();
    	map.put("domain", t);
    	map.put("wc", wc);
    	onwindctrMapper.updateByCondition(map); 
    }
    public List<Onwindctr> query(WhereCondition wc){
    	return onwindctrMapper.query(wc); 
    }
    
    public int count(WhereCondition wc){
    	return onwindctrMapper.count(wc);
    }
   
    public Onwindctr loadById(String id){
    	return onwindctrMapper.loadById(id);
    }
}
