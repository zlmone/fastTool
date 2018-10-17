package com.lanmosoft.service.biz;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lanmosoft.dao.mapper.ZiduanshezhiMapper;
import com.lanmosoft.dao.model.Ziduanshezhi;
import com.lanmosoft.model.WhereCondition;
/**
 * 服务层
 * @author su.zhang
 *
 */
@Service
public class ZiduanshezhiService {
	@Autowired
	ZiduanshezhiMapper ziduanshezhiMapper;

    public void insert(Ziduanshezhi t){
    	ziduanshezhiMapper.insert(t);
    }
    
    public void update(Ziduanshezhi t){
    	ziduanshezhiMapper.update(t); 
    }
   
    public void updateForce(Ziduanshezhi t){
    	ziduanshezhiMapper.updateForce(t); 
    }
   
    public void delete(String id){
    	ziduanshezhiMapper.delete(id);
    }
   
    public void deleteByCondition(WhereCondition wc){
    	ziduanshezhiMapper.deleteByCondition(wc); 
    }
	
	public void updateByCondition(WhereCondition wc,Ziduanshezhi t){
    	Map map  = new HashMap();
    	map.put("domain", t);
    	map.put("wc", wc);
    	ziduanshezhiMapper.updateByCondition(map); 
    }
    
    public List<Ziduanshezhi> query(WhereCondition wc){
    	return ziduanshezhiMapper.query(wc); 
    }
    
    public int count(WhereCondition wc){
    	return ziduanshezhiMapper.count(wc);
    }
   
    public Ziduanshezhi loadById(String id){
    	return ziduanshezhiMapper.loadById(id);
    }
}
