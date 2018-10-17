package com.lanmosoft.dao.mapper;

import java.util.List;

import com.lanmosoft.dao.model.User;
import com.lanmosoft.model.WhereCondition;
/**
 * 人员
 * @author su.zhang
 *
 */
public interface UserMapper extends BaseMapper<User>{
	  void	deleteBystatus(WhereCondition wc);
	  List<String> queryByPost(WhereCondition wc);
	  
	  
	  List<User> query1(WhereCondition wc);
	    
	  int count1(WhereCondition wc);
}
