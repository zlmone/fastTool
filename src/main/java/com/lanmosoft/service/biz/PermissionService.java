package com.lanmosoft.service.biz;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lanmosoft.dao.mapper.PermissionMapper;
import com.lanmosoft.dao.model.Permission;
import com.lanmosoft.model.WhereCondition;
/**
 * 服务层
 * @author su.zhang
 *
 */
@Service
public class PermissionService {
	@Autowired
	PermissionMapper permissionMapper;
    public void insert(Permission t){
    	permissionMapper.insert(t);
    }
    
    public void update(Permission t){
    	permissionMapper.update(t); 
    }
   
    public void updateForce(Permission t){
    	permissionMapper.updateForce(t); 
    }
   
    public void delete(String id){
    	permissionMapper.delete(id);
    }
   
    public void deleteByCondition(WhereCondition wc){
    	permissionMapper.deleteByCondition(wc); 
    }
	
	public void updateByCondition(WhereCondition wc,Permission t){
    	Map map  = new HashMap();
    	map.put("domain", t);
    	map.put("wc", wc);
    	permissionMapper.updateByCondition(map); 
    }
    public List<Permission> query(WhereCondition wc){
    	return permissionMapper.query(wc); 
    }
    
    public int count(WhereCondition wc){
    	return permissionMapper.count(wc);
    }
   
    public Permission loadById(String id){
    	return permissionMapper.loadById(id);
    }
}
