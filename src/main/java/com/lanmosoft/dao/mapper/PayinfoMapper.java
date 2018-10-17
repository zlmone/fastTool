package com.lanmosoft.dao.mapper;

import java.util.List;

import com.lanmosoft.dao.model.Payinfo;
import com.lanmosoft.model.WhereCondition;
/**
 * 还款信息
 * @author su.zhang
 *
 */
public interface PayinfoMapper extends BaseMapper<Payinfo>{
		
	public List<Payinfo> query1(WhereCondition wc);	
	
	public List<Payinfo> query2(WhereCondition wc);
	
	public int count1(WhereCondition wc);
}
