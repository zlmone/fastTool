package com.lanmosoft.service.lanmobase;

public interface GlobalConstants {
	boolean baoxiao=false;
	boolean org_code=true;//是否启用组织
	

	String activeStatus="1";//可用状态  
	String passiveStatus="0";//不可用状态 
	String deleteStatus="2";//删除状态  
	String defaultPasswd = "123456";      
	boolean createkucundetail = false;//true生成库存明细 false不生成库存明细
	boolean beginfapiaox = false; //先有发票再有应收应付
	
	/* 项目开关 */
	boolean all=true ;//产品
	boolean dongri=false;//东日
	boolean hesheng=false;//合胜
	boolean yuyou=false;//宇由
	boolean xuexiao=false;//学校
}
