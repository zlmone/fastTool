package com.lanmosoft.service.biz;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lanmosoft.dao.mapper.PayinfoMapper;
import com.lanmosoft.dao.model.Payinfo;
import com.lanmosoft.model.WhereCondition;
/**
 * 服务层
 * @author su.zhang
 *
 */
@Service
public class PayinfoService {
	@Autowired
	PayinfoMapper payinfoMapper;
    public void insert(Payinfo t){
    	payinfoMapper.insert(t);
    }
    
    public void update(Payinfo t){
    	payinfoMapper.update(t); 
    }
   
    public void updateForce(Payinfo t){
    	payinfoMapper.updateForce(t); 
    }
   
    public void delete(String id){
    	payinfoMapper.delete(id);
    }
   
    public void deleteByCondition(WhereCondition wc){
    	payinfoMapper.deleteByCondition(wc); 
    }
	
	public void updateByCondition(WhereCondition wc,Payinfo t){
    	Map map  = new HashMap();
    	map.put("domain", t);
    	map.put("wc", wc);
    	payinfoMapper.updateByCondition(map); 
    }
    public List<Payinfo> query(WhereCondition wc){
    	return payinfoMapper.query(wc); 
    }
    
    public int count(WhereCondition wc){
    	return payinfoMapper.count(wc);
    }
   
    public int count1(WhereCondition wc){
    	return payinfoMapper.count1(wc);
    }
    
    public Payinfo loadById(String id){
    	return payinfoMapper.loadById(id);
    }
    public List<Payinfo> query1(WhereCondition wc){
    	return payinfoMapper.query1(wc);
    }
    public List<Payinfo> query2(WhereCondition wc){
    	return payinfoMapper.query2(wc);
    }
}
