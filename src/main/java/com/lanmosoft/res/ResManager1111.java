package com.lanmosoft.res;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import com.lanmosoft.model.Ztree;

public class ResManager1111 {
	private static List<Ztree> resList = new ArrayList<Ztree>();
	private static List<Ztree> shujuresList = new ArrayList<Ztree>();
	static{
		
		//1、客户模块
		resList.add(new Ztree("100","","BugTool","menu"));
		//BUG池
		resList.add(new Ztree("101","100","Bug池","menu"));
		resList.add(new Ztree("105","101","新增Bug","menu"));
		resList.add(new Ztree("106","101","Bug","menu"));
		
		resList.add(new Ztree("106a","106","批量分配","button"));
		resList.add(new Ztree("106b","106","分配","button"));
		resList.add(new Ztree("106c","106","打回","button"));
		resList.add(new Ztree("106d","106","编辑","button"));
		

		//我的Bug列表
		resList.add(new Ztree("102","100","我的Bug列表","menu"));
		resList.add(new Ztree("107","102","Bug列表","menu"));
		
		resList.add(new Ztree("107a","107","反馈","button"));
		resList.add(new Ztree("107b","107","报告人反馈","button"));
		resList.add(new Ztree("107c","107","已解决待确认","button"));
		resList.add(new Ztree("107d","107","已确认","button"));
		resList.add(new Ztree("107e","107","已关闭","button"));
		
		//项目管理
		resList.add(new Ztree("103","100","项目管理","menu"));
		resList.add(new Ztree("108","103","新增项目","menu"));
		resList.add(new Ztree("109","103","项目","menu"));
		
		//基础数据
		resList.add(new Ztree("104","100","基础数据","menu"));
		resList.add(new Ztree("110","104","状态管理","menu"));
		resList.add(new Ztree("114","110","状态名称","menu"));
		resList.add(new Ztree("115","110","状态值","menu"));
		
		resList.add(new Ztree("111","104","组织管理","menu"));
		resList.add(new Ztree("112","104","角色管理","menu"));
		resList.add(new Ztree("113","104","授权管理","menu"));
		
		
		
		
		/*resList.add(new Ztree("100a","","蓝茂软件"));
		//1、CRM
		resList.add(new Ztree("101a","100a","crm"));
		resList.add(new Ztree("102a","101a","客户管理"));
		resList.add(new Ztree("103b","102a","所有客户"));
		resList.add(new Ztree("103a","102a","公海客户"));
		resList.add(new Ztree("104a","103a","新增"));
		resList.add(new Ztree("105a","103a","领用"));
		resList.add(new Ztree("106a","103a","分配"));
		resList.add(new Ztree("107a","103a","编辑"));
		resList.add(new Ztree("108a","103a","删除"));
		resList.add(new Ztree("109a","102a","我的客户"));
		resList.add(new Ztree("110a","109a","新增"));
		resList.add(new Ztree("111a","109a","领用"));
		resList.add(new Ztree("112a","109a","删除"));
		resList.add(new Ztree("113a","109a","编辑"));
		resList.add(new Ztree("114a","109a","跟进"));
		resList.add(new Ztree("115a","109a","机会"));
		resList.add(new Ztree("116a","109a","报价"));
		resList.add(new Ztree("117a","109a","合同"));
		resList.add(new Ztree("118a","109a","共享"));
		resList.add(new Ztree("119a","109a","转移"));
		resList.add(new Ztree("120a","102a","客户跟进"));
		resList.add(new Ztree("121a","120a","新增"));
		resList.add(new Ztree("122a","120a","编辑"));
		resList.add(new Ztree("123a","120a","删除"));
		resList.add(new Ztree("124a","120a","点评"));
		resList.add(new Ztree("125a","102a","延期审批"));
		resList.add(new Ztree("126a","101a","销售管理"));
		resList.add(new Ztree("127a","126a","销售机会"));
		resList.add(new Ztree("128a","127a","新增"));
		resList.add(new Ztree("129a","127a","编辑"));
		resList.add(new Ztree("130a","127a","删除"));
		resList.add(new Ztree("131a","126a","销售报价"));
		resList.add(new Ztree("132a","131a","新增"));
		resList.add(new Ztree("133a","131a","编辑"));
		resList.add(new Ztree("134a","131a","删除"));
		resList.add(new Ztree("135a","131a","转成合同"));
		resList.add(new Ztree("136a","126a","销售合同"));
		resList.add(new Ztree("137a","136a","新增"));
		resList.add(new Ztree("138a","136a","编辑"));
		resList.add(new Ztree("139a","136a","删除"));
		resList.add(new Ztree("140a","126a","销售退货"));
		resList.add(new Ztree("141a","140a","新增"));
		resList.add(new Ztree("142a","140a","编辑"));
		resList.add(new Ztree("143a","140a","删除"));
		resList.add(new Ztree("102a1","102a","计划事项"));
		//采购
		resList.add(new Ztree("144a","100a","采购"));
		resList.add(new Ztree("145a","144a","采购管理"));
		resList.add(new Ztree("145a1","145a","采购询价"));
		resList.add(new Ztree("145a11","145a1","新增"));
		resList.add(new Ztree("145a12","145a1","编辑"));
		resList.add(new Ztree("145a13","145a1","删除"));
		resList.add(new Ztree("145a2","145a","采购申请明细"));
		resList.add(new Ztree("145a21","145a2","转采购合同"));
		resList.add(new Ztree("146a","145a","采购申请"));
		resList.add(new Ztree("147a","146a","新增"));
		resList.add(new Ztree("148a","146a","编辑"));
		resList.add(new Ztree("149a","146a","删除"));
		resList.add(new Ztree("150a","145a","采购合同"));
		resList.add(new Ztree("151a","150a","新增"));
		resList.add(new Ztree("152a","150a","编辑"));
		resList.add(new Ztree("153a","150a","删除"));
		resList.add(new Ztree("154a","145a","采购退货"));
		resList.add(new Ztree("155a","154a","新增"));
		resList.add(new Ztree("156a","154a","编辑"));
		resList.add(new Ztree("157a","154a","删除"));
		//仓库
		resList.add(new Ztree("158a","100a","仓库"));
		resList.add(new Ztree("159a","158a","仓库管理"));
		resList.add(new Ztree("160a","159a","仓库查询"));
		
		resList.add(new Ztree("159a1","159a","库存变更"));
		resList.add(new Ztree("159a2","159a","盘点管理"));
		resList.add(new Ztree("159a21","159a2","盘点录入"));
		resList.add(new Ztree("159a22","159a2","盘点单查询"));
		resList.add(new Ztree("159a3","159a","占位管理"));
		resList.add(new Ztree("159a31","159a3","占位录入"));
		resList.add(new Ztree("159a32","159a3","占位查询"));
		
		resList.add(new Ztree("161a","159a","入库管理"));
		resList.add(new Ztree("162a","161a","新增"));
		resList.add(new Ztree("163a","161a","编辑"));
		resList.add(new Ztree("164a","161a","删除"));
		
		resList.add(new Ztree("159ab1","159a","销售待出库"));
		
		resList.add(new Ztree("165a","159a","出库管理"));
		resList.add(new Ztree("166a","165a","新增"));
		resList.add(new Ztree("167a","165a","编辑"));
		resList.add(new Ztree("168a","165a","删除"));
		//财务
		resList.add(new Ztree("169a","100a","财务"));
		resList.add(new Ztree("169a1","169a","期初设置"));
		resList.add(new Ztree("169a2","169a","账户查询"));
		resList.add(new Ztree("169a3","169a","提现调账管理"));
		resList.add(new Ztree("169a4","169a","暂支暂收管理"));
		resList.add(new Ztree("169a41","169a","暂支管理"));
		resList.add(new Ztree("169a42","169a","暂收管理"));
		
		resList.add(new Ztree("170a","169a","应收管理"));
		resList.add(new Ztree("171a","170a","应收款项"));
		resList.add(new Ztree("172a","171a","收款"));
		resList.add(new Ztree("173a","170a","已收款项"));
		resList.add(new Ztree("170a1","170a","其他收款项"));
		
		resList.add(new Ztree("174a","169a","销售发票"));
		resList.add(new Ztree("175a","174a","应开票据"));
		resList.add(new Ztree("176a","175a","开票"));
		resList.add(new Ztree("177a","174a","已开票据"));
		resList.add(new Ztree("178a","169a","应付管理"));
		resList.add(new Ztree("179a","178a","应付款项"));
		resList.add(new Ztree("180a","179a","付款"));
		resList.add(new Ztree("181a","178a","已付款项"));
		resList.add(new Ztree("178a1","178a","其他付款项"));
		
		resList.add(new Ztree("182a","169a","采购发票"));
		resList.add(new Ztree("183a","182a","应收票据"));
		resList.add(new Ztree("184a","183a","收票"));
		resList.add(new Ztree("185a","182a","已收票据"));
		//管理员
		resList.add(new Ztree("186a","100a","管理员"));
		resList.add(new Ztree("187a","186a","系统管理"));
		resList.add(new Ztree("188a","187a","基础数据管理"));
		resList.add(new Ztree("189a","187a","全局唯一常量"));
		resList.add(new Ztree("190a","187a","领用客户超期设置"));
		resList.add(new Ztree("191a","186a","业务基础数据"));
		resList.add(new Ztree("192a","191a","仓库设置"));
		resList.add(new Ztree("193a","191a","产品管理"));
		resList.add(new Ztree("194a","191a","供应商管理"));
		resList.add(new Ztree("195a","186a","用户管理"));
		resList.add(new Ztree("196a","195a","组织管理"));
		resList.add(new Ztree("197a","195a","角色管理"));
		resList.add(new Ztree("198a","195a","授权管理"));
		resList.add(new Ztree("199a","186a","流程管理"));
		
		resList.add(new Ztree("200a","100a","报表"));
		resList.add(new Ztree("200a1","200a","客户管理报表"));
		resList.add(new Ztree("200a11","200a1","客户跟单分类报表"));
		resList.add(new Ztree("200a12","200a1","客户询单率报表"));
		resList.add(new Ztree("200a13","200a1","跟单统计报表"));
		resList.add(new Ztree("200a2","200a","销售管理报表"));
		resList.add(new Ztree("200a21","200a2","销售业绩月度分析"));
		resList.add(new Ztree("200a22","200a2","销售员业绩分析"));
		
		resList.add(new Ztree("200a3","200a","财务报表"));
		resList.add(new Ztree("200a31","200a3","收入统计"));
		resList.add(new Ztree("200a32","200a3","支出统计"));
		resList.add(new Ztree("200a32","200a3","净利润统计"));
		
		resList.add(new Ztree("201a","100a","文件柜"));*/
		
		for(Ztree z:resList){
			setParents(z);
		}
	}
	static{//数据权限
		shujuresList.add(new Ztree("103a","","公海客户","function"));
		shujuresList.add(new Ztree("125a","","客户延期审批","function"));
		shujuresList.add(new Ztree("109a","","我的客户","function"));
		shujuresList.add(new Ztree("120a","","跟进记录","function"));
		shujuresList.add(new Ztree("127a","","销售机会","function"));
		shujuresList.add(new Ztree("131a","","报价查询","function"));
		shujuresList.add(new Ztree("136a","","销售合同","function"));
		shujuresList.add(new Ztree("140a","","销售退货","function"));
		
		shujuresList.add(new Ztree("146a","","采购申请","function"));
		shujuresList.add(new Ztree("150a","","采购合同","function"));
		shujuresList.add(new Ztree("154a","","采购退货单","function"));
		//
		shujuresList.add(new Ztree("171a","","应收单","function"));
		shujuresList.add(new Ztree("173a","","收款单","function"));
		shujuresList.add(new Ztree("179a","","应付单","function"));
		shujuresList.add(new Ztree("181a","","付款单","function"));
		shujuresList.add(new Ztree("175a","","需开票","function"));
		shujuresList.add(new Ztree("177a","","开票记录","function"));
		shujuresList.add(new Ztree("183a","","应收票据","function"));
		shujuresList.add(new Ztree("185a","","已收票据","function"));
		
		shujuresList.add(new Ztree("158a","","仓库查询","function"));
		shujuresList.add(new Ztree("161a","","入库查找","function"));
		shujuresList.add(new Ztree("165a","","出库查找","function"));
		shujuresList.add(new Ztree("159a32","","占位查询","function"));

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
