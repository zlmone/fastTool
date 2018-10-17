package com.lanmosoft.service.biz;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lanmosoft.dao.mapper.UserMapper;
import com.lanmosoft.dao.model.User;
import com.lanmosoft.model.WhereCondition;
/**
 * 服务层
 * @author su.zhang
 *
 */
@Service
public class UserService {
	@Autowired
	UserMapper userMapper;
    public void insert(User t){
    	userMapper.insert(t);
    }
    
    public void update(User t){
    	userMapper.update(t); 
    }
   
    public void updateForce(User t){
    	userMapper.updateForce(t); 
    }
   
    public void delete(String id){
    	userMapper.delete(id);
    }
   
    public void deleteByCondition(WhereCondition wc){
    	userMapper.deleteByCondition(wc); 
    }
	public void deleteBystatus(WhereCondition wc){
    	userMapper.deleteBystatus(wc); 
    }
	
	public void updateByCondition(WhereCondition wc,User t){
    	Map map  = new HashMap();
    	map.put("domain", t);
    	map.put("wc", wc);
    	userMapper.updateByCondition(map); 
    }
    public List<User> query(WhereCondition wc){
    	return userMapper.query(wc); 
    }
    
    public int count(WhereCondition wc){
    	return userMapper.count(wc);
    }
    
    public List<User> query1(WhereCondition wc){
    	return userMapper.query1(wc); 
    }
    
    public int count1(WhereCondition wc){
    	return userMapper.count1(wc);
    }
   
    public User loadById(String id){
    	return userMapper.loadById(id);
    }
    
    public List<String> queryByPost(String position_id, String self_id, boolean includeSelf) {
		WhereCondition wc = new WhereCondition();
		wc.andEquals("position_id", position_id);
		if(!includeSelf){
			wc.andNotEquals("id", self_id);
		}
		return userMapper.queryByPost(wc);
	}
}
