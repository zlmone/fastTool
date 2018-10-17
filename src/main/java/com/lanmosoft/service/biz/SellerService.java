package com.lanmosoft.service.biz;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lanmosoft.dao.mapper.SellerMapper;
import com.lanmosoft.dao.model.Seller;
import com.lanmosoft.model.WhereCondition;
/**
 * 服务层
 * @author su.zhang
 *
 */
@Service
public class SellerService {
	@Autowired
	SellerMapper sellerMapper;
    public void insert(Seller t){
    	sellerMapper.insert(t);
    }
    
    public void update(Seller t){
    	sellerMapper.update(t); 
    }
   
    public void updateForce(Seller t){
    	sellerMapper.updateForce(t); 
    }
   
    public void delete(String id){
    	sellerMapper.delete(id);
    }
   
    public void deleteByCondition(WhereCondition wc){
    	sellerMapper.deleteByCondition(wc); 
    }
	
	public void updateByCondition(WhereCondition wc,Seller t){
    	Map map  = new HashMap();
    	map.put("domain", t);
    	map.put("wc", wc);
    	sellerMapper.updateByCondition(map); 
    }
    public List<Seller> query(WhereCondition wc){
    	return sellerMapper.query(wc); 
    }
    
    public int count(WhereCondition wc){
    	return sellerMapper.count(wc);
    }
   
    public Seller loadById(String id){
    	return sellerMapper.loadById(id);
    }
}