package com.lanmosoft.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lanmosoft.dao.model.Org;
import com.lanmosoft.dao.model.User;
import com.lanmosoft.enums.Enums;
import com.lanmosoft.enums.Enums.OrgType;
import com.lanmosoft.model.WhereCondition;
import com.lanmosoft.service.biz.OrgService;
import com.lanmosoft.service.biz.UserService;
import com.lanmosoft.service.lanmobase.SequenceManager;
/**
 * 机构 jigou 部门 bumen岗位 gangwei，人员renyuan
 * @author su.zhang
 *
 */
@Service
public class OrgManager {
	@Autowired
	SequenceManager sequenceManager;
	@Autowired
	OrgService orgService;
	@Autowired
	UserService userService;
	/**
	 * 添加组织
	 * @param org
	 */
	public void addOrg(Org org){
		if(StringUtils.isEmpty(org.getId())){
			org.setId(sequenceManager.generateId("org"));
		}
		if(StringUtils.isNotEmpty(org.getDepartment_id())){
			Org parentDept = orgService.loadById(org.getDepartment_id());
			org.setDepartment_name(parentDept.getName());
			org.setFulldept_id(filterPath(parentDept.getFulldept_id()+org.getId()+Enums.SEPARATOR));
			org.setFulldept_name(filterPath(parentDept.getFulldept_name()+org.getName()+Enums.SEPARATOR));
			//顶级部门
			org.setSuperOrg_id(parentDept.getSuperOrg_id());
			org.setSuperOrg_name(parentDept.getSuperOrg_name());
		}else{//自己为顶级部门
//			org.setDepartment_id(org.getId());
//			org.setDepartment_name(org.getName());
			org.setFulldept_id(Enums.SEPARATOR+org.getId()+Enums.SEPARATOR);
			org.setFulldept_name(Enums.SEPARATOR+org.getName()+Enums.SEPARATOR);
			//顶级部门
			org.setSuperOrg_id(org.getId());
			org.setSuperOrg_name(org.getName());
		}
		if(StringUtils.equals(OrgType.POSITION, org.getOrg_type())){
			if(StringUtils.isNotEmpty(org.getPosition_id())){
				Org parentPost = orgService.loadById(org.getPosition_id());
				org.setPosition_name(parentPost.getName());
				org.setFullpost_id(filterPath(parentPost.getFullpost_id()+org.getId()+Enums.SEPARATOR));
				org.setFullpost_name(filterPath(parentPost.getFullpost_name()+org.getName()+Enums.SEPARATOR));
			}else{
//				org.setPosition_id(org.getId());
//				org.setPosition_name(org.getName());
				org.setFullpost_id(Enums.SEPARATOR+org.getId()+Enums.SEPARATOR);
				org.setFullpost_name(Enums.SEPARATOR+org.getName()+Enums.SEPARATOR);
			}
		}
		org.setStatus(Enums.TRUE_STRING);
		org.setSortNum(getSortNum(org));
		orgService.insert(org);
	}
	/**
	 * 修改组织
	 * @param zuzhi
	 */
	public void updateOrg(Org org){
		if(StringUtils.isNotEmpty(org.getDepartment_id())){
			String fulldept_id_old = org.getFulldept_id();
			String fullpost_id_old = org.getFullpost_id();
			Org parentDept = orgService.loadById(org.getDepartment_id());
			org.setDepartment_name(parentDept.getName());
			org.setFulldept_id(filterPath(parentDept.getFulldept_id()+org.getId()+Enums.SEPARATOR));
			org.setFulldept_name(filterPath(parentDept.getFulldept_name()+org.getName()+Enums.SEPARATOR));
			//顶级部门
			org.setSuperOrg_id(parentDept.getSuperOrg_id());
			org.setSuperOrg_name(parentDept.getSuperOrg_name());
		}else{
//			org.setDepartment_id(org.getId());
//			org.setDepartment_name(org.getName());
			org.setFulldept_id(Enums.SEPARATOR+org.getId()+Enums.SEPARATOR);
			org.setFulldept_name(Enums.SEPARATOR+org.getName()+Enums.SEPARATOR);
			//顶级部门
			org.setSuperOrg_id(org.getId());
			org.setSuperOrg_name(org.getName());
		}
		if(StringUtils.equals(OrgType.POSITION, org.getOrg_type())){
			if(StringUtils.isNotEmpty(org.getPosition_id())){
				Org parentPost = orgService.loadById(org.getPosition_id());
				org.setPosition_name(parentPost.getName());
				org.setFullpost_id(filterPath(parentPost.getFullpost_id()+org.getId()+Enums.SEPARATOR));
				org.setFullpost_name(filterPath(parentPost.getFullpost_name()+org.getName()+Enums.SEPARATOR));
			}else{
//				org.setPosition_id(org.getId());
//				org.setPosition_name(org.getName());
				org.setFullpost_id(Enums.SEPARATOR+org.getId()+Enums.SEPARATOR);
				org.setFullpost_name(Enums.SEPARATOR+org.getName()+Enums.SEPARATOR);
			}
		}
		org.setSortNum(getSortNum(org));
		orgService.update(org);
		//修改同级别组织sortNum
		updateSort(org.getDepartment_id(), org.getOrg_code());
		//修改组织下所有人员
		
		
	}
	/**
	 * 删除组织以及所有下属组织
	 * @param id
	 */
	public void deleteOrg(String id,String org_type){
		WhereCondition wc = new WhereCondition();
		if(StringUtils.equals(OrgType.DEPARTMENT, org_type)){
			wc.andFullLike("fulldept_id", Enums.SEPARATOR+id+Enums.SEPARATOR);
		}else if(StringUtils.equals(OrgType.POSITION, org_type)){
			wc.andFullLike("fullpost_id", Enums.SEPARATOR+id+Enums.SEPARATOR);
		}
		orgService.deleteByCondition(wc);
	}
	
	/**
	 * 列出所有组织
	 * @param isIncludePassive 是否包含禁用的组织
	 * @param jiedianleixingList 节点类型
	 * @param id 
	 * @param onlyXiaji 是否只列出下级
	 * @return
	 */
	public List<Org> listDept(WhereCondition wc ,String id,List<String> OrgTypeList,boolean isIncludePassive,boolean onlyXiaji){
		if(!isIncludePassive){
			wc.andEquals("status", Enums.TRUE_STRING) ;
		}
		if(StringUtils.isNotEmpty(id)){
			wc.andFullLike("fulldept_id", Enums.SEPARATOR+id+Enums.SEPARATOR);
		}
		if(onlyXiaji && StringUtils.isNotEmpty(id)){
			wc.andEquals("department_id", id);
		}else if(onlyXiaji){
			wc.andIsNull("department_id");
		}
		if(CollectionUtils.isNotEmpty(OrgTypeList)){
			wc.andIn("org_type", OrgTypeList);
		}
		
		List<Org> list = orgService.query(wc );
		return list;
	}
	public List<Org> listPost(WhereCondition wc ,String id,List<String> OrgTypeList,boolean isIncludePassive,boolean onlyXiaji){
		if(!isIncludePassive){
			wc.andEquals("status", Enums.TRUE_STRING) ;
		}
		if(StringUtils.isNotEmpty(id)){
			wc.andFullLike("fullpost_id", Enums.SEPARATOR+id+Enums.SEPARATOR);
		}
		if(onlyXiaji && StringUtils.isNotEmpty(id)){
			wc.andEquals("position_id", id);
		}else if(onlyXiaji){
			wc.andIsNull("position_id");
		}
		if(CollectionUtils.isNotEmpty(OrgTypeList)){
			wc.andIn("org_type", OrgTypeList);
		}
		List<Org> list = orgService.query(wc );
		return list;
	}
	
	
	
/*	public List<Department> listUserbyDept(String org_code){
		if(StringUtils.isEmpty(org_code)) return null;
		WhereCondition wc =new WhereCondition();
		wc.andEquals("org_type", OrgType.DEPARTMENT);
		if(StringUtils.isNotEmpty(org_code)){
			wc.andEquals("org_code", org_code);
		}
		List<Department> deptList = new ArrayList<Department>();
		List<Org> orgList = orgService.query(wc);
		for(Org org :orgList){
			Department dept = new Department();
			dept.setId(org.getId());
			dept.setName(org.getName());
			wc =new WhereCondition();
			wc.andEquals("org_code", org_code);
			wc.andEquals("department_id", org.getId());
			List<User> userList = userService.query(wc);
			dept.setUserList(userList);
			deptList.add(dept);
		}
		return deptList;
	}*/
	
	
	private String filterPath(String s){
		return s.replace("//", Enums.SEPARATOR);
	}
	
	/**
	 * 获取sortNum
	 * @param org
	 * @return
	 */
	private int getSortNum(Org org){
		Integer sortNum = null;
		WhereCondition wc = new WhereCondition();
		wc.andEquals("org_code", org.getOrg_code());
		if(StringUtils.isEmpty(org.getDepartment_id())){
			wc.andIsNull("department_id");
		}else{
			wc.andEquals("department_id", org.getDepartment_id());
		}
		Integer maxSortNum = orgService.getMaxSortNum(wc);
		if(maxSortNum==null)
			sortNum = 1;
		else
			sortNum = maxSortNum + 1;
		return sortNum;
	}
	/**
	 * 更新部门下所有组织的sortNum
	 * @param department_id
	 * @param org_code
	 */
	private void updateSort(String department_id, String org_code) {
		if(StringUtils.isEmpty(department_id)){
			return;
		}
		WhereCondition wc = new WhereCondition();
		wc.andEquals("org_code", org_code);
		wc.andEquals("department_id", department_id);
		wc.setOrderBy("sortNum asc");
		List<Org> orgList = orgService.query(wc);
		int sortNum = 1;
		for(Org org :orgList){
			org.setSortNum(sortNum);
			orgService.update(org);
			sortNum++;
		}
	}
	
	public void updateSort(List<Org> orgList) {
		int sortNum = 1;
		for(Org org :orgList){
			org.setSortNum(sortNum);
			orgService.update(org);
			sortNum++;
		}
	}
	
	public boolean isExists(Org org){
		if(org == null)
			return false;
		
		WhereCondition wc = new WhereCondition();
		wc.andEquals("org_code", org.getOrg_code());
		wc.andEquals("name", org.getName());
		if(StringUtils.isNotEmpty(org.getId())){
			wc.andNotEquals("id", org.getId());
		}
		int count = orgService.count(wc);
		if(count>0)
			return true;
		else
			return false;
	}
}
