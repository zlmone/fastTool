package com.lanmosoft.dao.mapper;

import com.lanmosoft.dao.model.Org;
import com.lanmosoft.model.WhereCondition;
/**
 * org
 * @author su.zhang
 *
 */
public interface OrgMapper extends BaseMapper<Org>{
	Integer getMaxSortNum(WhereCondition wc);
}
