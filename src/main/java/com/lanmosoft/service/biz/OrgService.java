package com.lanmosoft.service.biz;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lanmosoft.dao.mapper.OrgMapper;
import com.lanmosoft.dao.model.Org;
import com.lanmosoft.model.WhereCondition;
/**
 * 服务层
 * @author su.zhang
 *
 */
@Service
public class OrgService {
	@Autowired
	OrgMapper orgMapper;
    public void insert(Org t){
    	orgMapper.insert(t);
    }
    
    public void update(Org t){
    	orgMapper.update(t); 
    }
   
    public void updateForce(Org t){
    	orgMapper.updateForce(t); 
    }
   
    public void delete(String id){
    	orgMapper.delete(id);
    }
   
    public void deleteByCondition(WhereCondition wc){
    	orgMapper.deleteByCondition(wc); 
    }
	
	public void updateByCondition(WhereCondition wc,Org t){
    	Map map  = new HashMap();
    	map.put("domain", t);
    	map.put("wc", wc);
    	orgMapper.updateByCondition(map); 
    }
    public List<Org> query(WhereCondition wc){
    	return orgMapper.query(wc); 
    }
    
    public int count(WhereCondition wc){
    	return orgMapper.count(wc);
    }
   
    public Org loadById(String id){
    	return orgMapper.loadById(id);
    }
    
    public synchronized Integer getMaxSortNum(WhereCondition wc) {
		return orgMapper.getMaxSortNum(wc);
	}
}
