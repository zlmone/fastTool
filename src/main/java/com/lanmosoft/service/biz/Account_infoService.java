package com.lanmosoft.service.biz;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lanmosoft.dao.mapper.Account_infoMapper;
import com.lanmosoft.dao.model.Account_info;
import com.lanmosoft.model.WhereCondition;
/**
 * 服务层
 * @author su.zhang
 *
 */
@Service
public class Account_infoService {
	@Autowired
	Account_infoMapper account_infoMapper;
    public void insert(Account_info t){
    	account_infoMapper.insert(t);
    }
    
    public void update(Account_info t){
    	account_infoMapper.update(t); 
    }
   
    public void updateForce(Account_info t){
    	account_infoMapper.updateForce(t); 
    }
   
    public void delete(String id){
    	account_infoMapper.delete(id);
    }
   
    public void deleteByCondition(WhereCondition wc){
    	account_infoMapper.deleteByCondition(wc); 
    }
	
	public void updateByCondition(WhereCondition wc,Account_info t){
    	Map map  = new HashMap();
    	map.put("domain", t);
    	map.put("wc", wc);
    	account_infoMapper.updateByCondition(map); 
    }
    public List<Account_info> query(WhereCondition wc){
    	return account_infoMapper.query(wc); 
    }
    
    public int count(WhereCondition wc){
    	return account_infoMapper.count(wc);
    }
   
    public Account_info loadById(String id){
    	return account_infoMapper.loadById(id);
    }
}
