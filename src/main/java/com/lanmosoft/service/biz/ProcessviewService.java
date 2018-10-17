package com.lanmosoft.service.biz;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lanmosoft.dao.mapper.ProcessviewMapper;
import com.lanmosoft.dao.model.Processview;
import com.lanmosoft.model.WhereCondition;
/**
 * 服务层
 * @author su.zhang
 *
 */
@Service
public class ProcessviewService {
	@Autowired
	ProcessviewMapper processviewMapper;
    public List<Processview> query(WhereCondition wc){
    	return processviewMapper.query(wc); 
    }
    
    public int count(WhereCondition wc){
    	return processviewMapper.count(wc);
    }
   
    public Processview loadById(String id){
    	return processviewMapper.loadById(id);
    }
}
