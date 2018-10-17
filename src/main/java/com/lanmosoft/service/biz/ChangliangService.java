package com.lanmosoft.service.biz;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.cxf.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.lanmosoft.dao.mapper.ChangliangMapper;
import com.lanmosoft.dao.model.Changliang;
import com.lanmosoft.model.WhereCondition;
/**
 * 服务层
 * @author su.zhang
 *
 */
@Service
public class ChangliangService {
	@Autowired
	ChangliangMapper changliangMapper;

    public void insert(Changliang t){
    	changliangMapper.insert(t);
    }
    
    public void update(Changliang t){
    	changliangMapper.update(t); 
    }
   
    public void updateForce(Changliang t){
    	changliangMapper.updateForce(t); 
    }
   
    public void delete(String id){
    	changliangMapper.delete(id);
    }
   
    public void deleteByCondition(WhereCondition wc){
    	changliangMapper.deleteByCondition(wc); 
    }
	
	public void updateByCondition(WhereCondition wc,Changliang t){
    	Map map  = new HashMap();
    	map.put("domain", t);
    	map.put("wc", wc);
    	changliangMapper.updateByCondition(map); 
    }
    
    public List<Changliang> query(WhereCondition wc){
    	return changliangMapper.query(wc); 
    }
    
    public String queryge(String leibie,String daima){
    	if(StringUtils.isEmpty(leibie)||StringUtils.isEmpty(daima)){
    		return "";
    	}
    	WhereCondition wc = new WhereCondition();
    	wc.andEquals("leibie", leibie);
    	wc.andEquals("daima", daima);
    	List<Changliang> list= changliangMapper.query(wc ); 
    	if(!CollectionUtils.isEmpty(list)){
    		return list.get(0).getMingcheng();
    	}
    	return "";
    	
    }
    
    public int count(WhereCondition wc){
    	return changliangMapper.count(wc);
    }
   
    public Changliang loadById(String id){
    	return changliangMapper.loadById(id);
    }
}
