package com.lanmosoft.service.biz;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lanmosoft.dao.mapper.FilerecordMapper;
import com.lanmosoft.dao.model.Filerecord;
import com.lanmosoft.model.WhereCondition;
import com.lanmosoft.util.JSONUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
/**
 * 服务层
 * @author su.zhang
 *
 */
@Service
public class FilerecordService {
	@Autowired
	FilerecordMapper filerecordMapper;
    public void insert(Filerecord t){
    	filerecordMapper.insert(t);
    }
    
    public void update(Filerecord t){
    	filerecordMapper.update(t); 
    }
   
    public void updateForce(Filerecord t){
    	filerecordMapper.updateForce(t); 
    }
   
    public void delete(String id){
    	filerecordMapper.delete(id);
    }
   
    public void deleteByCondition(WhereCondition wc){
    	filerecordMapper.deleteByCondition(wc); 
    }
	
	public void updateByCondition(WhereCondition wc,Filerecord t){
    	Map map  = new HashMap();
    	map.put("domain", t);
    	map.put("wc", wc);
    	filerecordMapper.updateByCondition(map); 
    }
    public List<Filerecord> query(WhereCondition wc){
    	return filerecordMapper.query(wc); 
    }
    
    public int count(WhereCondition wc){
    	return filerecordMapper.count(wc);
    }
   
    public Filerecord loadById(String id){
    	return filerecordMapper.loadById(id);
    }
    
    public void uploadFiles(HttpServletRequest request, JSONObject jsonObj, String refid) {
		JSONArray ja= jsonObj.getJSONArray("files");
		if(ja!=null&&ja.size()>0){
			String sessionId=request.getSession().getId();
			for(int i=0;i<ja.size();i++){
				String uniqueIdentifier=JSONUtils.getStr(ja.getJSONObject(i), "uniqueIdentifier");
				String flowFilename=JSONUtils.getStr(ja.getJSONObject(i), "flowFilename");
				String size=JSONUtils.getStr(ja.getJSONObject(i), "size");
				WhereCondition wc = new WhereCondition();
				wc.andEquals("yuanshiid", sessionId+"_"+uniqueIdentifier);
				wc.andEquals("yuanshimingcheng",flowFilename);
				wc.andEquals("size",size);
				Filerecord fr=query(wc).get(0);
				fr.setRefid(refid);
				update(fr);
			}
		}
	}
}
