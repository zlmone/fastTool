package com.lanmosoft.service.biz;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lanmosoft.dao.mapper.Urge_detailMapper;
import com.lanmosoft.dao.model.Urge_detail;
import com.lanmosoft.model.WhereCondition;
/**
 * 服务层
 * @author su.zhang
 *
 */
@Service
public class Urge_detailService {
	@Autowired
	Urge_detailMapper urge_detailMapper;
    public void insert(Urge_detail t){
    	urge_detailMapper.insert(t);
    }
    
    public void update(Urge_detail t){
    	urge_detailMapper.update(t); 
    }
   
    public void updateForce(Urge_detail t){
    	urge_detailMapper.updateForce(t); 
    }
   
    public void delete(String id){
    	urge_detailMapper.delete(id);
    }
   
    public void deleteByCondition(WhereCondition wc){
    	urge_detailMapper.deleteByCondition(wc); 
    }
	
	public void updateByCondition(WhereCondition wc,Urge_detail t){
    	Map map  = new HashMap();
    	map.put("domain", t);
    	map.put("wc", wc);
    	urge_detailMapper.updateByCondition(map); 
    }
    public List<Urge_detail> query(WhereCondition wc){
    	return urge_detailMapper.query(wc); 
    }
    
    public int count(WhereCondition wc){
    	return urge_detailMapper.count(wc);
    }
   
    public Urge_detail loadById(String id){
    	return urge_detailMapper.loadById(id);
    }
}
