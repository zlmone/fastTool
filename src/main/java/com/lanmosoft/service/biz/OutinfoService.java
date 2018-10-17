package com.lanmosoft.service.biz;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lanmosoft.dao.mapper.OutinfoMapper;
import com.lanmosoft.dao.model.Outinfo;
import com.lanmosoft.model.WhereCondition;
/**
 * 服务层
 * @author su.zhang
 *
 */
@Service
public class OutinfoService {
	@Autowired
	OutinfoMapper outinfoMapper;
    public void insert(Outinfo t){
    	outinfoMapper.insert(t);
    }
    
    public void update(Outinfo t){
    	outinfoMapper.update(t); 
    }
   
    public void updateForce(Outinfo t){
    	outinfoMapper.updateForce(t); 
    }
   
    public void delete(String id){
    	outinfoMapper.delete(id);
    }
   
    public void deleteByCondition(WhereCondition wc){
    	outinfoMapper.deleteByCondition(wc); 
    }
	
	public void updateByCondition(WhereCondition wc,Outinfo t){
    	Map map  = new HashMap();
    	map.put("domain", t);
    	map.put("wc", wc);
    	outinfoMapper.updateByCondition(map); 
    }
    public List<Outinfo> query(WhereCondition wc){
    	return outinfoMapper.query(wc); 
    }
    
    public int count(WhereCondition wc){
    	return outinfoMapper.count(wc);
    }
   
    public Outinfo loadById(String id){
    	return outinfoMapper.loadById(id);
    }
}
