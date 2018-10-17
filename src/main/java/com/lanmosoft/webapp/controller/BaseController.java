package com.lanmosoft.webapp.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertySetStrategy;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.util.CollectionUtils;

import com.lanmosoft.dao.model.Permission;
import com.lanmosoft.dao.model.ProcessActivity;
import com.lanmosoft.dao.model.Quanxianbiaoview;
import com.lanmosoft.dao.model.User;
import com.lanmosoft.enums.Enums;
import com.lanmosoft.enums.Enums.DataRole;
import com.lanmosoft.enums.Enums.DelStatus;
import com.lanmosoft.enums.Enums.UserType;
import com.lanmosoft.json.processor.JsonDateProcessor;
import com.lanmosoft.model.Function;
import com.lanmosoft.model.WhereCondition;
import com.lanmosoft.model.Ztree;
import com.lanmosoft.res.ResManager;
import com.lanmosoft.service.biz.ChangliangService;
import com.lanmosoft.service.biz.FilerecordService;
import com.lanmosoft.service.biz.ProcessActivityService;
import com.lanmosoft.service.biz.ReminderService;
import com.lanmosoft.service.biz.ShenpimobanService;
import com.lanmosoft.service.biz.UserService;
import com.lanmosoft.service.lanmobase.DateUtils;
import com.lanmosoft.service.lanmobase.LanDateUtils;
import com.lanmosoft.service.lanmobase.ProcessManager;
import com.lanmosoft.service.lanmobase.SequenceManager;
import com.lanmosoft.util.JSONPropertyStrategyWrapper;
import com.lanmosoft.util.JSONUtils;
import com.lanmosoft.webapp.AuthException;
import com.lanmosoft.webapp.controller.searchForm.Page;
import com.lanmosoft.webapp.controller.searchForm.SearchItem;
import com.lanmosoft.webapp.webmodel.LoginModel;

public class BaseController {
	@Autowired
	TransactionTemplate transactionTemplate;
	@Autowired
	SequenceManager sequenceManager;
	@Autowired
	FilerecordService filerecordService;
	@Autowired
	ShenpimobanService shenpimobanService;
	@Autowired
	ProcessManager processManager;
	@Autowired
	ProcessActivityService processActivityService;
	@Autowired
	UserService userService;
	@Autowired
	ChangliangService changliangService;
	@Autowired
	ReminderService reminderService;

	
	protected JsonConfig jsonConfig;

	public BaseController() {
		net.sf.json.util.JSONUtils.getMorpherRegistry().registerMorpher(
				new DateMorpher(new String[] { "yyyy-MM-dd HH:mm:ss" ,"yyyy-MM-dd"
						}));
		jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Date.class,
				new JsonDateProcessor());
		jsonConfig.setPropertySetStrategy(new JSONPropertyStrategyWrapper(
				PropertySetStrategy.DEFAULT));
	}
	
	
	public String getProcessStatusAll(Object bean) {
		WhereCondition wc = new WhereCondition();
		try {
			wc.andEquals("biaodanid", BeanUtils.getProperty(bean, "id"));
			wc.andEquals("zhuangtai","1");
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<ProcessActivity> list = processActivityService.query(wc);
		if (CollectionUtils.isEmpty(list)) {//null 不要审批
			return "0";// 不需审批
		}else{//需要审批
			return "-1";
		}
	}
	
	
	protected void initWanNengChaXuns(JSONObject jsonObj,WhereCondition wc ){
		//查询项
		List<SearchItem> searchItems = JSONArray.toList(
				jsonObj.getJSONArray("searchItems"), SearchItem.class);
		for (SearchItem searchItem : searchItems) {
			if (StringUtils.equals(searchItem.getCode(), "eq")) {
				wc.andEquals(searchItem.getName(),
						JSONUtils.getStr(jsonObj, searchItem.getName()));
			} else if (StringUtils.equals(searchItem.getCode(), "lk")) {
				wc.andFullLike(searchItem.getName(),
						JSONUtils.getStr(jsonObj, searchItem.getName()));
			}else if (StringUtils.equals(searchItem.getCode(), "gt")) {
				wc.andGreaterThan(searchItem.getName(),
						JSONUtils.getStr(jsonObj, searchItem.getName()));
			}else if (StringUtils.equals(searchItem.getCode(), "lt")) {
				wc.andLessThan(searchItem.getName(),
						JSONUtils.getStr(jsonObj, searchItem.getName()));
			}else if (StringUtils.equals(searchItem.getCode(), "ge")) {
				wc.andGreaterEquals(searchItem.getName(),
						JSONUtils.getStr(jsonObj, searchItem.getName()));
			}else if (StringUtils.equals(searchItem.getCode(), "le")) {
				wc.andLessEquals(searchItem.getName(),
						JSONUtils.getStr(jsonObj, searchItem.getName()));
			}else if (StringUtils.equals(searchItem.getCode(), "start")) {
				if(StringUtils.isNotEmpty(JSONUtils.getStr(jsonObj, searchItem.getName()))&&!StringUtils.equals(JSONUtils.getStr(jsonObj, searchItem.getName()), "null")){
					wc.andGreaterEquals(searchItem.getName().substring(0, searchItem.getName().lastIndexOf("start")-1),
							JSONUtils.getStr(jsonObj, searchItem.getName()));
				}
			}else if (StringUtils.equals(searchItem.getCode(), "end")&&!StringUtils.equals(JSONUtils.getStr(jsonObj, searchItem.getName()), "null")) {
				if(StringUtils.isNotEmpty(JSONUtils.getStr(jsonObj, searchItem.getName()))){
					wc.andLessEquals(searchItem.getName().substring(0, searchItem.getName().lastIndexOf("end")-1),
							JSONUtils.getStr(jsonObj, searchItem.getName()));
				}
			}else if (StringUtils.equals(searchItem.getCode(), "in")) {
				String invalue = JSONUtils.getStr(jsonObj, searchItem.getName());
				if(StringUtils.isNotEmpty(invalue)) {
					wc.andIn(searchItem.getName(), Arrays.asList(invalue.split(",")));
				}
			}else if (StringUtils.equals(searchItem.getCode(), "nin")) {
				String invalue = JSONUtils.getStr(jsonObj, searchItem.getName());
				if(StringUtils.isNotEmpty(invalue)) {
					wc.andNotIn(searchItem.getName(), Arrays.asList(invalue.split(",")));
				}
			}else if (StringUtils.equals(searchItem.getCode(), "neq")) {
				wc.andNotEquals(searchItem.getName(), JSONUtils.getStr(jsonObj, searchItem.getName()));
			}
		}
	}
	

	protected void initWanNengChaXun(JSONObject jsonObj, WhereCondition wc) {
		// 查询项
		JSONObject jsonObjsearchItems = jsonObj.getJSONObject("searchItems");
		Set set = jsonObjsearchItems.keySet();
		for (Object o : set) {
			String key = o.toString();
			String value = jsonObjsearchItems.getString(key);
			if (StringUtils.endsWith(key, "_eq")) {
				wc.andEquals(key.substring(0, key.length() - 3), value);
			} else if (StringUtils.endsWith(key, "_lk")) {
				wc.andFullLike(key.substring(0, key.length() - 3), value);
			} else if (StringUtils.endsWith(key, "_gt")) {
				wc.andGreaterThan(key.substring(0, key.length() - 3), value);
			} else if (StringUtils.endsWith(key, "_lt")) {
				wc.andLessThan(key.substring(0, key.length() - 3), value);
			} else if (StringUtils.endsWith(key, "_ge")) {
				wc.andGreaterEquals(key.substring(0, key.length() - 3), value);
			} else if (StringUtils.endsWith(key, "_le")) {
				wc.andLessEquals(key.substring(0, key.length() - 3), value);
			} else if (StringUtils.endsWith(key, "_start")) {
				if (StringUtils.isNotEmpty(value)
						&& !StringUtils.equals(value, "null")) {
					wc.andGreaterEquals(key.substring(0, key.length() - 6),
							value);
				}
			} else if (StringUtils.endsWith(key, "_end")
					&& !StringUtils.equals(value, "null")) {
				if (StringUtils.isNotEmpty(value)) {
					wc.andLessEquals(key.substring(0, key.length() - 4), value);
				}
			} else if (StringUtils.endsWith(key, "_in")) {
				if (StringUtils.isNotEmpty(value)) {
					wc.andIn(key.substring(0, key.length() - 3),
							Arrays.asList(value.split(",")));
				}
			} else if (StringUtils.endsWith(key, "_nin")) {
				if (StringUtils.isNotEmpty(value)) {
					wc.andNotIn(key.substring(0, key.length() - 4),
							Arrays.asList(value.split(",")));
				}
			} else if (StringUtils.endsWith(key, "_neq")) {
				wc.andNotEquals(key.substring(0, key.length() - 4), value);
			}else{
				if(StringUtils.isNotEmpty(key)&&StringUtils.isNotEmpty(value)){
					wc.andEquals(key, value);
				}
			}
		}
	}

	/**
	 * 数据的地域权限
	 * **/
	
	protected List<String> data_city(HttpServletRequest request, HttpServletResponse response){
		User user = getLogin(request).getUser();
		List<String> list=new ArrayList<String>();
		if(StringUtils.equals(user.getCategory_id(),"0")){//超级管理员
			
		}else if(StringUtils.equals(user.getCategory_id(),"1")){//管理员
			
		}else{//一般用户
			String kejian_city = user.getKejian_city();
			if(StringUtils.equals("", kejian_city)||kejian_city==null){//没有数据
				list.add("xxxxxxxx");
			}else{
				 int a=kejian_city.indexOf("全国");
				  if(a>=0){ 
				  }else{
					  String[] split = kejian_city.split(",");
						for (int i = 0; i < split.length; i++) {
							list.add(split[i]);
						} 
				  }
			}
		}
		
		return  list;
	}
	
	
	protected List<String> data_citys(HttpServletRequest request, HttpServletResponse response){
		User user = getLogin(request).getUser();
		List<String> list=new ArrayList<String>();
		if(StringUtils.equals(user.getCategory_id(),"0")){//超级管理员
			  list.add("上海市");
			  list.add("杭州市");
			  list.add("北京市");
			  list.add("天津市");
			  list.add("广州市");
			  list.add("南京市");
			  list.add("义乌市");
			  list.add("宁波市");
		}else if(StringUtils.equals(user.getCategory_id(),"1")){//管理员
			  list.add("上海市");
			  list.add("杭州市");
			  list.add("北京市");
			  list.add("天津市");
			  list.add("广州市");
			  list.add("南京市");
			  list.add("义乌市");
			  list.add("宁波市");
		}else{//一般用户
			String kejian_city = user.getKejian_city();
			if(StringUtils.equals("", kejian_city)||kejian_city==null){//没有数据
				 
			}else{
				 int a=kejian_city.indexOf("全国");
				  if(a>=0){ 
					  list.add("上海市");
					  list.add("杭州市");
					  list.add("北京市");
					  list.add("天津市");
					  list.add("广州市");
					  list.add("南京市");
					  list.add("义乌市");
					  list.add("宁波市");
					  
				  }else{
					  String[] split = kejian_city.split(",");
						for (int i = 0; i < split.length; i++) {
							list.add(split[i]);
						} 
				  }
			}
		}
		
		return  list;
	}
	
	
	/**
	 * 获取权限查询条件
	 * @param jsonObj
	 * @param wc
	 * @param request
	 * @param response
	 */
	protected void permissionFilter(JSONObject jsonObj, WhereCondition wc, 
			HttpServletRequest request, HttpServletResponse response) {
		String fn = JSONUtils.getStr(jsonObj, "fn");
		if (StringUtils.isEmpty(fn)) {// 如果功能号为空，那么禁止使用此功能
			throw new AuthException();
		}

		List<Permission> permissionList = getLogin(request).getPermissionList();
		User user = getLogin(request).getUser();
		Function function = ResManager.getFunction(fn);
		if(function==null){//功能没有定义
			throw new AuthException();
		}
		
		if(StringUtils.equals(UserType.GENERAL, user.getCategory_id())){
			Permission p = getPermission(fn, permissionList);
			if(p==null){//没有权限访问此功能
				throw new AuthException();
			}
			String position_id = user.getPosition_id();
			List<String> userIdList = userService.queryByPost(position_id, user.getId(), false);
			if(StringUtils.equals(DataRole.DEPT, p.getData_role())){//部门所有人
				wc.andFullLike("fulldept_id", user.getDepartment_id());
			}else if(StringUtils.equals(DataRole.POST, p.getData_role())){//自己和下属
				wc.andFullLike("fullpost_id", Enums.SEPARATOR+user.getPosition_id()+Enums.SEPARATOR)
				.andNotIn("creatorId", userIdList);
			}else if(StringUtils.equals(DataRole.SELF, p.getData_role())){//仅自己
				wc.andEquals("creatorId", user.getId());
			}else{//组织下所有
				wc.andEquals("org_code", user.getOrg_code());
			}
		}else if(StringUtils.equals(UserType.ADMIN, user.getCategory_id())){
			//组织下所有
			wc.andEquals("org_code", user.getOrg_code());
			
		}else if(StringUtils.equals(UserType.SUPER, user.getCategory_id())){
			//所有
		}
	}
	
	protected String getOrderGuize(String orderGuize) {
		if (orderGuize == null) {
			return orderGuize;
		}
		if (orderGuize.endsWith("asc")) {
			return orderGuize.substring(0, orderGuize.length() - 3) + " asc";
		} else if (orderGuize.endsWith("desc")) {
			return orderGuize.substring(0, orderGuize.length() - 4) + " desc";
		}
		return null;
	}

	// 获取权限查询条件
	/*protected String getGlobalFilter(String content,
			HttpServletRequest request, HttpServletResponse response) {
		String globalFilter = null;
		String fn;
		LoginModel login = (LoginModel) request.getSession().getAttribute(
				"login");
		if (StringUtils.equals(login.getRenyuan().getDengluming(), "admin")) {
			return "";
		}
		try {
			fn = JSONUtils.getStr(JSONObject.fromObject(content), "fn");
			if (StringUtils.isEmpty(fn)) {// 如果功能号为空，那么禁止使用此功能
				// AjaxUtils.ajaxJsonErrorMessage(response,
				// "无权访问此功能,ERROR_CODE=FN10!");
				throw new AuthException();
			}
		} catch (Exception e) {
			// AjaxUtils
			// .ajaxJsonErrorMessage(response, "无权访问此功能,ERROR_CODE=FN10!");
			throw new AuthException();
		}
		// 判断此功能的合法性
		Ztree ztree = ResManager.getZtreeNode(fn);
		if (ztree == null) {
			// AjaxUtils
			// .ajaxJsonErrorMessage(response, "无权访问此功能,ERROR_CODE=FN11!");
			throw new AuthException();
		} else {// 功能与URL是否匹配
			//
			// if (!isPipei) {// 不匹配
			// AjaxUtils.ajaxJsonErrorMessage(response,
			// "无权访问此功能,ERROR_CODE=FN12!");
			// throw new AuthException();
			// }
		}
		// 判断有无授权此功能

		List<Quanxianbiaoview> gongnengList = login.getGongnengQuanxianList();
		List<Quanxianbiaoview> shujuList = login.getShujuQuanxianList();
		if (CollectionUtils.isEmpty(gongnengList)) {
			// AjaxUtils
			// .ajaxJsonErrorMessage(response, "无权访问此功能,ERROR_CODE=FN20!");
			throw new AuthException();
		}
		boolean isauth = false;
		for (Quanxianbiaoview q : gongnengList) {
			if (StringUtils.equals(q.getGongnengid(), fn)) {
				isauth = true;
				break;
			}
		}
		if (isauth) {// 判断数据权限
			// 组装数据权限
			List<Quanxianbiaoview> targetList = new ArrayList<Quanxianbiaoview>();
			for (Quanxianbiaoview q : shujuList) {
				if (StringUtils.equals(q.getGongnengid(), fn)) {
					targetList.add(q);
				}
			}
			// 如果不配置，只能查询到自己创建的数据

			globalFilter = " ";
			String sqlfilter = "";
			boolean flag = false;
			if (!CollectionUtils.isEmpty(targetList)) {
				int i = 0;
				for (Quanxianbiaoview q : targetList) {
					if (StringUtils.isNotEmpty(q.getYewudongzuo())) {
						String field = "creatorIdPath";
						if (StringUtils.isNotEmpty(q.getYuyishujuquanxian())) {
							field = q.getYuyishujuquanxian();
						}
						String[] renyuanids = StringUtils.split(
								q.getYewudongzuo(), ",");
						for (String renid : renyuanids) {
							globalFilter = globalFilter + " or " + field
									+ " like '%" + filterRenyuanid(renid)
									+ "%' ";
							flag = true;
						}

					} else if (StringUtils.isNotEmpty(q.getYuyishujuquanxian())) {// 如果人员为空，字段不为空，那么只能查询到自己的
						String field = "creatorIdPath";
						if (StringUtils.isNotEmpty(q.getYuyishujuquanxian())) {
							field = q.getYuyishujuquanxian();
						}
						globalFilter = globalFilter + " or " + field
								+ " like '%" + login.getRenyuan().getId()
								+ "%' ";
						flag = true;
					}
					if (StringUtils.isNotEmpty(q.getYewuguocheng())) {
						sqlfilter = sqlfilter + " and (" + q.getYewuguocheng()
								+ ") ";
					}
					if (i == targetList.size() - 1) {
						globalFilter = globalFilter + ")";
					}
					i++;
				}
			} else {
				globalFilter = globalFilter + ")";
			}
			if (flag) {
				String tmpglobalFilter = " and ( 1>2 ";
				globalFilter = tmpglobalFilter + globalFilter;
			} else {// 默认只能查询到自己创建的数据
				String tmpglobalFilter = " and ( creatorId='"
						+ login.getRenyuan().getId()
						+ "' or creatorId is null ";
				globalFilter = tmpglobalFilter + globalFilter;
			}
			if (StringUtils.isNotEmpty(sqlfilter)) {
				globalFilter = globalFilter + sqlfilter;
			}

		} else {
			// AjaxUtils
			// .ajaxJsonErrorMessage(response, "无权访问此功能,ERROR_CODE=FN14!");
			throw new AuthException();
		}
		return globalFilter;
	}*/

	protected void startProcess(String content, HttpServletRequest request,
			Object bean) {
		String fn = JSONUtils.getStr(JSONObject.fromObject(content), "fn");
		processManager.startProcess(fn, bean, request);
	}

	protected void forwardProcess(Object bean, HttpServletRequest request,
			String beizhu) {
		processManager.forwardProcess(bean, request, beizhu);
	}

	public void backProcess(Object bean, HttpServletRequest request,
			String beizhu) {
		processManager.backProcess(bean, request, beizhu);
	}

	// 返回流程状态
	/**
	 * public static final String ACTIVITY_STATUS_PROGRESS = "1";// 流程进行中 public
	 * static final String ACTIVITY_STATUS_END = "2"; // 流程结束 public static
	 * final String ACTIVITY_STATUS_TERMINATE = "3"; // 流程终结
	 * 
	 * @param bean
	 * @return
	 */
	public String getProcessStatus(Object bean) {
		WhereCondition wc = new WhereCondition();
		try {
			wc.andEquals("biaodanid", BeanUtils.getProperty(bean, "id"));
			List<ProcessActivity> list = processActivityService.query(wc);
			if (!CollectionUtils.isEmpty(list)) {
				ProcessActivity ac = list.get(0);
				if (StringUtils.equals(ac.getDangqiankusuo(), "start")) {
					return "-1";
				}
				return ac.getZhuangtai();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "0";// 不需审批
	}
	
	//初始化创建 creatorId,creatorName,creatorCode,creatorIdPath,creatorNamePath,creatorCodePath,createTime,
		public void initCreate(Object bean, HttpServletRequest request){
			try {
				LoginModel login = (LoginModel)request.getSession().getAttribute("login");
				BeanUtils.copyProperty(bean, "delstatus", DelStatus.UNREMOVED);
				BeanUtils.copyProperty(bean, "creatorId",login.getUser().getId());
				BeanUtils.copyProperty(bean, "creatorName",login.getUser().getName());
				BeanUtils.copyProperty(bean, "superOrg_id",login.getUser().getSuperOrg_id());
				BeanUtils.copyProperty(bean, "superOrg_name",login.getUser().getSuperOrg_name());
				BeanUtils.copyProperty(bean, "fulldept_id",login.getUser().getFulldept_id());
				BeanUtils.copyProperty(bean, "fulldept_name",login.getUser().getFulldept_name());
				BeanUtils.copyProperty(bean, "fullpost_id",login.getUser().getFullpost_id());
				BeanUtils.copyProperty(bean, "fullpost_name",login.getUser().getFullpost_name());
				BeanUtils.copyProperty(bean, "org_code",login.getUser().getOrg_code());
				BeanUtils.copyProperty(bean, "createTime", DateUtils.getNowDate());
//				BeanUtils.copyProperty(bean, "modifiedTime", DateUtils.getNowDate());
			} catch (Exception e) {
				throw new RuntimeException("创建初始化错误:"+ToStringBuilder.reflectionToString(bean));
			} 
		}

/*	// 初始化创建
	// creatorId,creatorName,creatorCode,creatorIdPath,creatorNamePath,creatorCodePath,createTime,
	public void initCreates(Object bean, HttpServletRequest request) {
		try {
			LoginModel login = (LoginModel) request.getSession().getAttribute(
					"login");
			BeanUtils.copyProperty(bean, "creatorId", login.getRenyuan()
					.getId());
			BeanUtils.copyProperty(bean, "creatorName", login.getRenyuan()
					.getMingcheng());
			BeanUtils.copyProperty(bean, "creatorCode", login.getRenyuan()
					.getBianma());
			BeanUtils.copyProperty(bean, "creatorIdPath", login.getZuzhi()
					.getQuanlujingid());
			BeanUtils.copyProperty(bean, "creatorNamePath", login.getZuzhi()
					.getQuanlujingming());
			BeanUtils.copyProperty(bean, "creatorCodePath", login.getZuzhi()
					.getQuanlujingbianma());
			BeanUtils.copyProperty(bean, "delstatus", DelStatus.UNREMOVED);
			BeanUtils.copyProperty(bean, "createTime", LanDateUtils.getNowDate());
			BeanUtils
					.copyProperty(bean, "modifiedTime", LanDateUtils.getNowDate());
		} catch (Exception e) {
			throw new RuntimeException("创建初始化错误:"
					+ ToStringBuilder.reflectionToString(bean));
		}
	}*/
	
	protected Page getPage(JSONObject jsonObj) {
		Page page = (Page) JSONObject.toBean(jsonObj.getJSONObject("page"),
				Page.class);
		if (page == null) {
			page = new Page();
		}
		return page;
	}

	protected LoginModel getLogin(HttpServletRequest request) {
		return (LoginModel) request.getSession().getAttribute("login");
	}


	public static String filterRenyuanid(String id) {
		if (id == null) {
			return null;
		}
		int a = id.indexOf("LANMO");
		if (a >= 0) {
			return id.substring(a + 5);
		}
		return id;
	}
 
	
	protected Permission getPermission(String function_id,List<Permission> permissionList){
		for(Permission p :permissionList){
			if(StringUtils.equals(function_id, p.getFunction_id()))
				return p;
		}
		return null;
	}
	
	protected void topSearch(JSONObject jsonObj, WhereCondition wc ){
		String status = JSONUtils.getStr(jsonObj, "status");
		if(StringUtils.equals("1", status)){//多条件搜索
			String str = JSONUtils.getStr(jsonObj, "listArray");
			if(StringUtils.isEmpty(str)) return;
			JSONArray jsonArray = JSONArray.fromObject(str);
			for (Object object : jsonArray) {
			    jsonObj = JSONObject.fromObject(object);
			    
			    String searchItemName = JSONUtils.getStr(jsonObj, "searchItemName");
				if(StringUtils.isEmpty(searchItemName)) continue;
				if(StringUtils.endsWith(searchItemName, "_date")){
					searchItemName = StringUtils.substring(searchItemName, 0, searchItemName.lastIndexOf("_date"));
					String searchItemValue_start = JSONUtils.getStr(jsonObj, "searchItemValue_start");
					String searchItemValue_end = JSONUtils.getStr(jsonObj, "searchItemValue_end");
					if(StringUtils.isNotEmpty(searchItemValue_start)&&
							!StringUtils.equalsIgnoreCase(searchItemValue_start, "null")){
						wc.andGreaterEquals(searchItemName, searchItemValue_start+" 00:00:00");
					}
					if(StringUtils.isNotEmpty(searchItemValue_end)&&
							!StringUtils.equalsIgnoreCase(searchItemValue_end, "null")){
						wc.andLessEquals(searchItemName, searchItemValue_end+" 23:59:59");
					}
				}else if(StringUtils.endsWith(searchItemName, "_select")){
					searchItemName = StringUtils.substring(searchItemName, 0, searchItemName.lastIndexOf("_select"));
					String searchItemValue = JSONUtils.getStr(jsonObj, "searchItemValue");
					if(searchItemValue.lastIndexOf("_neq")!=-1){
						searchItemValue = StringUtils.substring(searchItemValue, 0, searchItemValue.lastIndexOf("_neq"));
						wc.andNotEquals(searchItemName, searchItemValue);
					}else{
						if(StringUtils.equals(searchItemValue, "null")||searchItemValue==null){
							
						}else{
							wc.andEquals(searchItemName, searchItemValue);
						}
						
					}
				}else{
					if(StringUtils.endsWith(searchItemName, "_text")){
						searchItemName = StringUtils.substring(searchItemName, 0, searchItemName.lastIndexOf("_text"));
					}else if(StringUtils.endsWith(searchItemName, "_number")){
						searchItemName = StringUtils.substring(searchItemName, 0, searchItemName.lastIndexOf("_number"));
					}else if(StringUtils.endsWith(searchItemName, "_decimal")){
						searchItemName = StringUtils.substring(searchItemName, 0, searchItemName.lastIndexOf("_decimal"));
					}
					String searchItemCode = JSONUtils.getStr(jsonObj, "searchItemCode");
					String searchItemValue = JSONUtils.getStr(jsonObj, "searchItemValue");
					if(StringUtils.isNotEmpty(searchItemName)){
						if (StringUtils.equals(searchItemCode, "eq")) {
							wc.andEquals(searchItemName, searchItemValue);
						}else if (StringUtils.equals(searchItemCode, "neq")) {
							wc.andNotEquals(searchItemName, searchItemValue);
						}else if (StringUtils.equals(searchItemCode, "lk")) {
							wc.andFullLike(searchItemName, searchItemValue);
						}else if (StringUtils.equals(searchItemCode, "llk")) {
							wc.andLeftLike(searchItemName, searchItemValue);
						}else if (StringUtils.equals(searchItemCode, "rlk")) {
							wc.andRightLike(searchItemName, searchItemValue);
						}else if (StringUtils.equals(searchItemCode, "isnull")) {
							wc.andIsNull(searchItemName);
						}else if (StringUtils.equals(searchItemCode, "notnull")) {
							wc.andIsNotNull(searchItemName);
						}else if (StringUtils.equals(searchItemCode, "gt")) {
							wc.andGreaterThan(searchItemName, searchItemValue);
						}else if (StringUtils.equals(searchItemCode, "lt")) {
							wc.andLessThan(searchItemName, searchItemValue);
						}else if (StringUtils.equals(searchItemCode, "ge")) {
							wc.andGreaterEquals(searchItemName, searchItemValue);
						}else if (StringUtils.equals(searchItemCode, "le")) {
							wc.andLessEquals(searchItemName, searchItemValue);
						}
					}
				}
			}
			return;
		}else{								//一般搜索
		String searchItemName = JSONUtils.getStr(jsonObj, "searchItemName");
		if(StringUtils.isEmpty(searchItemName)) return;
		if(StringUtils.endsWith(searchItemName, "_date")){
			searchItemName = StringUtils.substring(searchItemName, 0, searchItemName.lastIndexOf("_date"));
			String searchItemValue_start = JSONUtils.getStr(jsonObj, "searchItemValue_start");
			String searchItemValue_end = JSONUtils.getStr(jsonObj, "searchItemValue_end");
			if(StringUtils.isNotEmpty(searchItemValue_start)&&
					!StringUtils.equalsIgnoreCase(searchItemValue_start, "null")){
				wc.andGreaterEquals(searchItemName, searchItemValue_start+" 00:00:00");
			}
			if(StringUtils.isNotEmpty(searchItemValue_end)&&
					!StringUtils.equalsIgnoreCase(searchItemValue_end, "null")){
				wc.andLessEquals(searchItemName, searchItemValue_end+" 23:59:59");
			}
		}else if(StringUtils.endsWith(searchItemName, "_select")){
			searchItemName = StringUtils.substring(searchItemName, 0, searchItemName.lastIndexOf("_select"));
			String searchItemValue = JSONUtils.getStr(jsonObj, "searchItemValue");
			if(searchItemValue.lastIndexOf("_neq")!=-1){
				searchItemValue = StringUtils.substring(searchItemValue, 0, searchItemValue.lastIndexOf("_neq"));
				wc.andNotEquals(searchItemName, searchItemValue);
			}else{
				if(StringUtils.equals(searchItemValue, "null")||searchItemValue==null){
					
				}else{
					wc.andEquals(searchItemName, searchItemValue);
				}
				
			}
		}else{
			if(StringUtils.endsWith(searchItemName, "_text")){
				searchItemName = StringUtils.substring(searchItemName, 0, searchItemName.lastIndexOf("_text"));
			}else if(StringUtils.endsWith(searchItemName, "_number")){
				searchItemName = StringUtils.substring(searchItemName, 0, searchItemName.lastIndexOf("_number"));
			}else if(StringUtils.endsWith(searchItemName, "_decimal")){
				searchItemName = StringUtils.substring(searchItemName, 0, searchItemName.lastIndexOf("_decimal"));
			}
			String searchItemCode = JSONUtils.getStr(jsonObj, "searchItemCode");
			String searchItemValue = JSONUtils.getStr(jsonObj, "searchItemValue");
			if(StringUtils.isNotEmpty(searchItemName)){
				if (StringUtils.equals(searchItemCode, "eq")) {
					wc.andEquals(searchItemName, searchItemValue);
				}else if (StringUtils.equals(searchItemCode, "neq")) {
					wc.andNotEquals(searchItemName, searchItemValue);
				}else if (StringUtils.equals(searchItemCode, "lk")) {
					wc.andFullLike(searchItemName, searchItemValue);
				}else if (StringUtils.equals(searchItemCode, "llk")) {
					wc.andLeftLike(searchItemName, searchItemValue);
				}else if (StringUtils.equals(searchItemCode, "rlk")) {
					wc.andRightLike(searchItemName, searchItemValue);
				}else if (StringUtils.equals(searchItemCode, "isnull")) {
					wc.andIsNull(searchItemName);
				}else if (StringUtils.equals(searchItemCode, "notnull")) {
					wc.andIsNotNull(searchItemName);
				}else if (StringUtils.equals(searchItemCode, "gt")) {
					wc.andGreaterThan(searchItemName, searchItemValue);
				}else if (StringUtils.equals(searchItemCode, "lt")) {
					wc.andLessThan(searchItemName, searchItemValue);
				}else if (StringUtils.equals(searchItemCode, "ge")) {
					wc.andGreaterEquals(searchItemName, searchItemValue);
				}else if (StringUtils.equals(searchItemCode, "le")) {
					wc.andLessEquals(searchItemName, searchItemValue);
				}
				
			}
		}
		} 
	}
}
