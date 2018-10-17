package com.lanmosoft.dao.mapper;

import com.lanmosoft.dao.model.Order;
/**
 * 订单管理
 * @author su.zhang
 *
 */
import com.lanmosoft.dao.model.TongJi;
import com.lanmosoft.model.WhereCondition;
public interface OrderMapper extends BaseMapper<Order>{
		
	public TongJi tongji(WhereCondition wc);	
	
	public TongJi tongji1(WhereCondition wc);
}
