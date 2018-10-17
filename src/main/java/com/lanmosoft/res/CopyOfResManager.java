package com.lanmosoft.res;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import com.lanmosoft.model.Ztree;

public class CopyOfResManager {
	private static List<Ztree> resList = new ArrayList<Ztree>();
	private static List<Ztree> shujuresList = new ArrayList<Ztree>();
	static{
	
		
		
		//1、客户模块
		resList.add(new Ztree("crm","","crm管理","menu"));
		resList.add(new Ztree("267","crm","客户管理","menu"));
		resList.add(new Ztree("277","267","增加客户","menu"));
		resList.add(new Ztree("268","267","公共客户池","menu"));
		resList.add(new Ztree("271","268","删除","button"));
		resList.add(new Ztree("272","268","领用","button"));
		resList.add(new Ztree("273","268","分配","button"));
		resList.add(new Ztree("270","268","编辑","button"));
		
		resList.add(new Ztree("274","267","我的客户","menu"));
		resList.add(new Ztree("275","274","编辑","button"));
		resList.add(new Ztree("276","274","共享","button"));
		resList.add(new Ztree("278","274","删除","button"));
		resList.add(new Ztree("813","274","领用","button"));
		resList.add(new Ztree("274a","274","导出","button"));
		resList.add(new Ztree("298","274","跟进","button"));
		resList.add(new Ztree("298a","274","转移","button"));
		resList.add(new Ztree("298b","274","机会添加","button"));
		resList.add(new Ztree("298c","274","合同添加","button"));
		resList.add(new Ztree("298d","274","报价添加","button"));
		
		resList.add(new Ztree("809","267","客户延期审批","menu"));
		
		resList.add(new Ztree("279","crm","客户跟进记录","menu"));
		resList.add(new Ztree("280","279","跟进记录管理","menu"));
		resList.add(new Ztree("280a","280","选择客户","button"));
		resList.add(new Ztree("281","280","新建","button"));
		resList.add(new Ztree("282","280","查看","button"));
		resList.add(new Ztree("283","280","编辑","button"));
		resList.add(new Ztree("284","280","点评","button"));
		resList.add(new Ztree("285","280","删除","button"));
		
		
		resList.add(new Ztree("286","crm","客户提醒","menu"));
		resList.add(new Ztree("287","286","回收提醒","menu"));
		resList.add(new Ztree("308","287","标记为已读","button"));
		resList.add(new Ztree("309","287","全部标记为已读","button"));
		resList.add(new Ztree("310","287","删除","button"));
		resList.add(new Ztree("307","286","跟进提醒","menu"));
		resList.add(new Ztree("311","307","标记为已读","button"));
		resList.add(new Ztree("312","307","全部标记为已读","button"));
		resList.add(new Ztree("288","307","删除","button"));
		
		resList.add(new Ztree("289","crm","客户转移","menu"));
		resList.add(new Ztree("290","289","客户转移","menu"));
		
		resList.add(new Ztree("292","crm","共享管理","menu"));
		resList.add(new Ztree("293","292","我共享的客户","menu"));
		resList.add(new Ztree("294","293","查看","button"));
		resList.add(new Ztree("295","293","解除共享","button"));
		resList.add(new Ztree("296","292","别人共享给我的客户","menu"));
		resList.add(new Ztree("297","296","查看","button"));
		
		resList.add(new Ztree("825","crm","销售机会管理","menu"));
		resList.add(new Ztree("826","825","销售机会","menu"));
		resList.add(new Ztree("826a","826","增加","button"));
		resList.add(new Ztree("826b","826","修改","button"));
		resList.add(new Ztree("826c","826","删除","button"));
		resList.add(new Ztree("826d","826","销售机会-选择客户","button"));
		resList.add(new Ztree("830","825","解决方案","menu"));
		resList.add(new Ztree("830a","830","增加","button"));
		resList.add(new Ztree("830b","830","修改","button"));
		resList.add(new Ztree("830c","830","删除","button"));
		resList.add(new Ztree("830d","830","审核","button"));
		
		resList.add(new Ztree("2671","crm","销售合同管理","menu"));
		resList.add(new Ztree("2671a","2671","增加报价单","menu"));
		resList.add(new Ztree("2671aa","2671a","增加报价-选择客户","button"));
		resList.add(new Ztree("2671b","2671","报价查询","menu"));
		resList.add(new Ztree("2671ba","2671b","修改","button"));
		resList.add(new Ztree("2671bb","2671b","删除","button"));
		resList.add(new Ztree("2671bc","2671b","打印","button"));
		resList.add(new Ztree("2671c","2671","增加销售合同","menu"));
		resList.add(new Ztree("2671ca","2671c","增加销售合同-选择客户","button"));
		resList.add(new Ztree("2671cb","2671c","增加销售合同-选择报价","button"));
		resList.add(new Ztree("2671d","2671","销售合同查询","menu"));
		resList.add(new Ztree("2671da","2671d","修改","button"));
		resList.add(new Ztree("2671db","2671d","删除","button"));
		resList.add(new Ztree("2671dc","2671d","打印","button"));
		
		resList.add(new Ztree("2672","crm","销售报表","menu"));
		resList.add(new Ztree("2672a","2672","合同报表","menu"));
		resList.add(new Ztree("2672b","2672","客户类型报表","menu"));
		resList.add(new Ztree("2672c","2672","客户业绩报表","menu"));
		resList.add(new Ztree("2672d","2672","客户跟进饼图","menu"));
	
		//2、进销存模块
		resList.add(new Ztree("jxc","","进销存","menu"));
		resList.add(new Ztree("22a","jxc","采购管理","menu"));
		resList.add(new Ztree("23","22a","待处理询价查询","menu"));
		resList.add(new Ztree("23a","23","修改","button"));
		resList.add(new Ztree("23b","23","删除","button"));
		resList.add(new Ztree("26","22a","已处理询价查询","menu"));
		resList.add(new Ztree("28","22a","增加采购申请","menu"));
		resList.add(new Ztree("29","22a","采购申请查询","menu"));
		resList.add(new Ztree("29a","29","修改","button"));
		resList.add(new Ztree("29b","29","删除","button"));
		resList.add(new Ztree("33","22a","增加采购合同","menu"));
		resList.add(new Ztree("34","22a","采购合同查询","menu"));
		resList.add(new Ztree("34a","34","修改","button"));
		resList.add(new Ztree("34b","34","删除","button"));
		resList.add(new Ztree("38","22a","增加采购退货单","menu"));
		resList.add(new Ztree("39","22a","采购退货单查询","menu"));
		resList.add(new Ztree("39a","39","修改","button"));
		resList.add(new Ztree("39b","39","删除","button"));
		
		resList.add(new Ztree("22b","jxc","销售管理","menu"));
		resList.add(new Ztree("2","22b","增加询价","menu"));
		resList.add(new Ztree("3","22b","待处理询价查询","menu"));
		resList.add(new Ztree("3a","3","修改","button"));
		resList.add(new Ztree("3b","3","删除","button"));
		
		resList.add(new Ztree("6","22b","已处理询价查询","menu"));
		resList.add(new Ztree("8","22b","增加报价单","menu"));
		resList.add(new Ztree("9","22b","报价查询","menu"));
		resList.add(new Ztree("9a","9","修改","button"));
		resList.add(new Ztree("9b","9","删除","button"));
		
		resList.add(new Ztree("13","22b","增加销售合同","menu"));
		resList.add(new Ztree("14","22b","销售合同查询","menu"));
		resList.add(new Ztree("14a","14","修改","button"));
		resList.add(new Ztree("14b","14","删除","button"));
		
		resList.add(new Ztree("18","22b","增加销售退货单","menu"));
		resList.add(new Ztree("19","22b","销售退货查询","menu"));
		resList.add(new Ztree("19a","19","修改","button"));
		resList.add(new Ztree("19b","19","删除","button"));
		
		resList.add(new Ztree("22c","jxc","仓库管理","menu"));
		resList.add(new Ztree("22c1","22c","增加入库单","menu"));
		resList.add(new Ztree("22c2","22c","入库管理","menu"));
		resList.add(new Ztree("22c2a","22c2","修改","button"));
		resList.add(new Ztree("22c2b","22c2","删除","button"));
		
		resList.add(new Ztree("22c3","22c","增加出库单","menu"));
		resList.add(new Ztree("22c4","22c","出库管理","menu"));
		resList.add(new Ztree("22c4a","22c4","修改","button"));
		resList.add(new Ztree("22c4b","22c4","删除","button"));
		
		resList.add(new Ztree("22c5","22c","增加调拨单","menu"));
		resList.add(new Ztree("22c6","22c","调拨单管理","menu"));
		resList.add(new Ztree("22c6a","22c6","修改","button"));
		resList.add(new Ztree("22c6b","22c6","删除","button"));
		
		resList.add(new Ztree("22c7","22c","增加盘点单","menu"));
		resList.add(new Ztree("22c8","22c","盘点单管理","menu"));
		resList.add(new Ztree("22c8a","22c8","修改","button"));
		resList.add(new Ztree("22c8b","22c8","删除","button"));
		resList.add(new Ztree("22c9","22c","库存管理","menu"));
		resList.add(new Ztree("22c10","22c","占位查询","menu"));
		resList.add(new Ztree("22c10a","22c10","解除占位","button"));
		resList.add(new Ztree("22c10b","22c10","修改","button"));
		resList.add(new Ztree("22c10c","22c10","删除","button"));
		
		resList.add(new Ztree("22d","jxc","进销存报表","menu"));
		resList.add(new Ztree("22d1","22d","采购入库查询","menu"));
		resList.add(new Ztree("22d2","22d","采购报表视图","menu"));
		
		resList.add(new Ztree("22e","jxc"," 客户管理","menu"));
		resList.add(new Ztree("22e1","22e","增加客户","menu"));
		resList.add(new Ztree("22e2","22e","客户池","menu"));
		
		
		//3、库存模块
		resList.add(new Ztree("cangku","","仓库管理","menu"));
		resList.add(new Ztree("67","cangku","入库管理","menu"));
		resList.add(new Ztree("68","67","增加入库单","menu"));
		resList.add(new Ztree("69","67","入库管理","menu"));
		resList.add(new Ztree("70","69","修改","button"));
		resList.add(new Ztree("71","69","删除","button"));
		resList.add(new Ztree("72","","出库管理","menu"));
		resList.add(new Ztree("73","72","增加出库单","menu"));
		resList.add(new Ztree("74","72","出库管理","menu"));
		resList.add(new Ztree("75","74","修改","button"));
		resList.add(new Ztree("76","74","删除","button"));
		
		resList.add(new Ztree("77","cangku","调拨管理","menu"));
		resList.add(new Ztree("78","77","增加调拨单","menu"));
		resList.add(new Ztree("79","77","调拨单管理","menu"));
		resList.add(new Ztree("80","79","修改","button"));
		resList.add(new Ztree("81","79","删除","button"));
		
		resList.add(new Ztree("82","cangku","借用归还管理","menu"));
		resList.add(new Ztree("83","82","增加借用记录","menu"));
		resList.add(new Ztree("84","82","借用记录管理","menu"));
		resList.add(new Ztree("85","82","增加归还记录","menu"));
		resList.add(new Ztree("86","82","归还记录管理","menu"));
		
		resList.add(new Ztree("87","cangku","拆装管理","menu"));
		resList.add(new Ztree("88","87","增加拆分单","menu"));
		resList.add(new Ztree("89","87","拆分单管理","menu"));
		resList.add(new Ztree("90","87","增加组装单","menu"));
		resList.add(new Ztree("91","87","组装单管理","menu"));
		
		resList.add(new Ztree("92","cangku","盘点管理","menu"));
		resList.add(new Ztree("93","92","增加盘点单","menu"));
		resList.add(new Ztree("94","92","盘点单管理","menu"));
		resList.add(new Ztree("95","94","修改","button"));
		resList.add(new Ztree("96","94","删除","button"));
		
		resList.add(new Ztree("97","cangku","占位管理","menu"));
		resList.add(new Ztree("98","97","增加占位管理","menu"));
		resList.add(new Ztree("99","97","占位查询","menu"));
		resList.add(new Ztree("99a","99","解除占位","button"));
		resList.add(new Ztree("100","99","修改","button"));
		resList.add(new Ztree("101","99","删除","button"));
		
		resList.add(new Ztree("102","cangku","库存管理","menu"));
		resList.add(new Ztree("104","102","库存管理","menu"));
		
		//生产管理
		resList.add(new Ztree("sc","","生产管理","menu"));
		resList.add(new Ztree("42","sc","生产订单","menu"));
		resList.add(new Ztree("43","42","增加生产订单","menu"));
		resList.add(new Ztree("44","42","生产订单查询","menu"));
		resList.add(new Ztree("47","sc","生产计划","menu"));
		resList.add(new Ztree("48","47","增加生产计划","menu"));
		resList.add(new Ztree("49","47","生产计划查询","menu"));
		resList.add(new Ztree("52","sc","加工单管理","menu"));
		resList.add(new Ztree("53","52","增加加工单","menu"));
		resList.add(new Ztree("54","52","加工单查询","menu"));
		resList.add(new Ztree("57","sc","领料单管理","menu"));
		resList.add(new Ztree("58","57","增加领料单","menu"));
		resList.add(new Ztree("59","57","领料单查询","menu"));
		resList.add(new Ztree("62","sc","BOM管理","menu"));
		resList.add(new Ztree("63","62","增加BOM信息","menu"));
		resList.add(new Ztree("64","62","BOM信息查询","menu"));
		//财务管理
		resList.add(new Ztree("cw","","财务管理","menu"));
		resList.add(new Ztree("105","cw","应收管理","menu"));
		resList.add(new Ztree("106","105","手工增加应收单","menu"));
		resList.add(new Ztree("107","105","应收单查询","menu"));
		resList.add(new Ztree("110","105","手工增加收款单","menu"));
		resList.add(new Ztree("111","105","收款单查询","menu"));
		resList.add(new Ztree("114","cw","应付管理","menu"));
		resList.add(new Ztree("115","114","手工增加应付单","menu"));
		resList.add(new Ztree("116","114","应付单查询","menu"));
		resList.add(new Ztree("119","114","手工增加付款单","menu"));
		resList.add(new Ztree("120","114","付款单查询","menu"));
		resList.add(new Ztree("123","cw","开票管理","menu"));
		resList.add(new Ztree("124","123","手工增加需开票记录","menu"));
		resList.add(new Ztree("125","123","开票查询","menu"));
		resList.add(new Ztree("128","123","手工增加开票记录","menu"));
		resList.add(new Ztree("129","123","开票记录查询","menu"));
		resList.add(new Ztree("140","cw","收票管理","menu"));
		resList.add(new Ztree("141","140","手工增加需收发票","menu"));
		resList.add(new Ztree("142","140","需收发票查询","menu"));
		resList.add(new Ztree("145","140","手工增加收到发票记录","menu"));
		resList.add(new Ztree("146","140","收到发票记录查询","menu"));
		//售后管理
		resList.add(new Ztree("shouhou","","售后管理","menu"));
		resList.add(new Ztree("149","shouhou","客服接单","menu"));
		resList.add(new Ztree("150","149","增加电话接单","menu"));
		resList.add(new Ztree("151","149","电话接单查询","menu"));
		resList.add(new Ztree("154","shouhou","派工管理","menu"));
		resList.add(new Ztree("155","154","增加派工单","menu"));
		resList.add(new Ztree("156","154","派工单查询","menu"));
		resList.add(new Ztree("159","shouhou","客户回访管理","menu"));
		resList.add(new Ztree("160","159","增加客户回访","menu"));
		resList.add(new Ztree("161","159","客户回访查询","menu"));
		
		//系统管理
		resList.add(new Ztree("xitong","","系统管理","menu"));
		resList.add(new Ztree("164","xitong","基础信息维护","menu"));
		resList.add(new Ztree("164a","164","客户来源","menu"));
		resList.add(new Ztree("164b","164","客户类型","menu"));
		resList.add(new Ztree("164c","164","销售方式","menu"));
		resList.add(new Ztree("164d","164","合同状态","menu"));
		resList.add(new Ztree("164e","164","系统超期天数","menu"));
		resList.add(new Ztree("164f","164","个人超期天数","menu"));
		resList.add(new Ztree("164g","164","提前天数","menu"));
		resList.add(new Ztree("164h","164","每天领用客户数量","menu"));
		resList.add(new Ztree("164j","164","最多领用客户数量","menu"));
		resList.add(new Ztree("205","164","入库类型","menu"));
		resList.add(new Ztree("209","164","出库类型","menu"));
		resList.add(new Ztree("217","164","采购类型","menu"));
		resList.add(new Ztree("815","164","拆分原因","menu"));
		resList.add(new Ztree("174","164","产品分类","menu"));
		resList.add(new Ztree("197","164","产品单位","menu"));
		resList.add(new Ztree("201","164","产品状态","menu"));
		resList.add(new Ztree("221","164","发票类型","menu"));
		resList.add(new Ztree("213","164","接收人维护","menu"));
		resList.add(new Ztree("164a1","164"," 产品管理","menu"));
		resList.add(new Ztree("182","164a1","增加","button"));
		resList.add(new Ztree("183","164a1","修改","button"));
		resList.add(new Ztree("184","164a1","删除","button"));
		resList.add(new Ztree("164a2","164","供应商管理","menu"));
		resList.add(new Ztree("186","164a2","增加","button"));
		resList.add(new Ztree("187","164a2","修改","button"));
		resList.add(new Ztree("188","164a2","删除","button"));
	
		
		resList.add(new Ztree("165","164","仓库设置","menu"));
		resList.add(new Ztree("166","165","新建","button"));
		resList.add(new Ztree("167","165","转移","button"));
		resList.add(new Ztree("168","165","修改","button"));
		resList.add(new Ztree("169","165","启用","button"));
		resList.add(new Ztree("170","165","禁用","button"));
		resList.add(new Ztree("171","165","删除","button"));
		resList.add(new Ztree("172","165","锁仓","button"));
		resList.add(new Ztree("173","165","解仓","button"));

		resList.add(new Ztree("174","164","物料分类","menu"));
		resList.add(new Ztree("175","174","新建","button"));
		resList.add(new Ztree("176","174","转移","button"));
		resList.add(new Ztree("177","174","修改","button"));
		resList.add(new Ztree("178","174","启用","button"));
		resList.add(new Ztree("179","174","禁用","button"));
		resList.add(new Ztree("180","174","删除","button"));
		
		resList.add(new Ztree("181","164","物料信息管理","menu"));
		resList.add(new Ztree("182","181","增加","button"));
		resList.add(new Ztree("183","181","修改","button"));
		resList.add(new Ztree("184","181","删除","button"));
		
		resList.add(new Ztree("185","164","供应商信息管理","menu"));
		resList.add(new Ztree("186","185","增加","button"));
		resList.add(new Ztree("187","185","修改","button"));
		resList.add(new Ztree("188","185","删除","button"));
		
		resList.add(new Ztree("197","164","物料单位","menu"));
		resList.add(new Ztree("198","197","增加","button"));
		resList.add(new Ztree("199","197","修改","button"));
		resList.add(new Ztree("200","197","删除","button"));
		

		
		resList.add(new Ztree("233","164","审批人设置","menu"));
		resList.add(new Ztree("234","233","增加","button"));
		resList.add(new Ztree("235","233","修改","button"));
		resList.add(new Ztree("236","233","删除","button"));
		
		
		resList.add(new Ztree("237","164","系统账号管理","menu"));
		resList.add(new Ztree("238","237","组织","menu"));
		resList.add(new Ztree("239","238","新建","button"));
		resList.add(new Ztree("240","238","转移","button"));
		resList.add(new Ztree("241","238","修改","button"));
		resList.add(new Ztree("242","238","启用","button"));
		resList.add(new Ztree("243","238","禁用","button"));
		resList.add(new Ztree("244","238","删除","button"));
		
		resList.add(new Ztree("245","164","角色","menu"));
		resList.add(new Ztree("246","245","新增","button"));
		resList.add(new Ztree("247","245","修改","button"));
		resList.add(new Ztree("248","245","删除","button"));
		resList.add(new Ztree("249","245","分配功能权限","button"));
		resList.add(new Ztree("250","245","分配数据权限","button"));
		
		resList.add(new Ztree("252","","授权","menu"));
		resList.add(new Ztree("253","252","增加","button"));
		resList.add(new Ztree("254","252","删除","button"));
		resList.add(new Ztree("255","252","修改","button"));
		
		for(Ztree z:resList){
			setParents(z);
		}
	}
	static{//数据权限
		shujuresList.add(new Ztree("268","","公共客户池","function"));
		shujuresList.add(new Ztree("809","","客户延期审批","function"));
		
		shujuresList.add(new Ztree("274","","我的客户","function"));
		
		shujuresList.add(new Ztree("280","","跟进记录管理","function"));
		shujuresList.add(new Ztree("290","","客户转移","function"));
		shujuresList.add(new Ztree("280a","","跟踪记录-选择客户","function"));
		shujuresList.add(new Ztree("826","","销售机会","function"));
		shujuresList.add(new Ztree("830","","解决方案","function"));
		shujuresList.add(new Ztree("826d","","销售机会-选择客户","function"));
		shujuresList.add(new Ztree("19","","销售退货查询","function"));
		
		shujuresList.add(new Ztree("2671b","","报价查询","function"));
		shujuresList.add(new Ztree("2671aa","","增加报价-选择客户","function"));
		shujuresList.add(new Ztree("2671d","","销售合同查询","function"));
		shujuresList.add(new Ztree("2671ca","","增加销售合同-选择客户","function"));
		shujuresList.add(new Ztree("29","","采购申请查询","function"));
		shujuresList.add(new Ztree("34","","采购合同查询","function"));
		shujuresList.add(new Ztree("39","","采购退货单查询","function"));
		//
		shujuresList.add(new Ztree("107","","应收单查询","function"));
		shujuresList.add(new Ztree("111","","收款单查询","function"));
		shujuresList.add(new Ztree("116","","应付单查询","function"));
		shujuresList.add(new Ztree("120","","付款单查询","function"));
		shujuresList.add(new Ztree("125","","需开票查询","function"));
		shujuresList.add(new Ztree("129","","开票记录查询","function"));
		shujuresList.add(new Ztree("142","","需收发票查询","function"));
		shujuresList.add(new Ztree("146","","收到发票记录查询","function"));
		
		
		shujuresList.add(new Ztree("67","","入库查找","function"));
		shujuresList.add(new Ztree("72","","出库查找","function"));
		shujuresList.add(new Ztree("79","","调拨单管理","function"));
		shujuresList.add(new Ztree("84","","借用记录管理","function"));
		shujuresList.add(new Ztree("86","","归还记录管理","function"));
		shujuresList.add(new Ztree("90","","拆分单管理","function"));
		shujuresList.add(new Ztree("91","","组装单管理","function"));
		shujuresList.add(new Ztree("94","","盘点单管理","function"));
		shujuresList.add(new Ztree("99","","占位查询","function"));
		shujuresList.add(new Ztree("104","","库存管理","function"));
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public static List<Ztree> getAllRes(){
		 List<Ztree> dest=new ArrayList<Ztree>();
		for(Ztree z:resList){
			Ztree zt =new Ztree();
			try {
				BeanUtils.copyProperties(zt, z);
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			dest.add(zt);
		}
		return  dest;
	}
	public static Ztree getZtreeNode(String id){
		for(Ztree z:resList){
			if(StringUtils.equals(id, z.getId())){
				return z;
			}
		}
		
		return null;
	}
	public static Ztree getShujuZtreeNode(String id){
		for(Ztree z:shujuresList){
			if(StringUtils.equals(id, z.getId())){
				return z;
			}
		}
		
		return null;
	}
	public static List<Ztree> getAllShujuRes(){
		 List<Ztree> dest=new ArrayList<Ztree>();
			for(Ztree z:shujuresList){
				Ztree zt =new Ztree();
				try {
					BeanUtils.copyProperties(zt, z);
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				dest.add(zt);
			}
			return  dest;
	}
	
	private static void setParents(Ztree z){
		String pid =z.getPid();
		if(StringUtils.isEmpty(pid)){
			z.setIdpath("/"+z.getId());
			z.setNamepath("/"+z.getName()); 
			return ;
		}
		Ztree p=getZtreeNode(pid);
		z.setIdpath(p.getIdpath()+"/"+z.getId());
		z.setNamepath(p.getNamepath()+"/"+z.getName());
		
	}

}
