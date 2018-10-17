package com.lanmosoft.res;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import com.lanmosoft.enums.Enums.ModuleName;
import com.lanmosoft.model.Function;
import com.lanmosoft.model.Ztree;

public class ResManager {
	private static List<Function> resList = new ArrayList<Function>();
	
	static{
		//客户设置
		resList.add(new Function("101", "列表", "complex", "客户设置","kehu_list", "menu"));
		resList.add(new Function("1011", "新增", "simple", "客户设置", "kehu_add", "button"));
		resList.add(new Function("1012", "编辑", "simple", "客户设置","kehu_edit", "button"));
		resList.add(new Function("1014", "删除", "simple", "客户设置","kehu_delete", "button"));
		resList.add(new Function("1015", "查看", "simple", "客户设置","kehu_detail", "button"));
	
		//货位设置
		resList.add(new Function("102", "列表", "complex", "货位设置","huowei_list", "menu"));
		resList.add(new Function("1021", "新增", "simple", "货位设置", "huowei_add", "button"));
		resList.add(new Function("1022", "编辑", "simple", "货位设置","huowei_edit", "button"));
		resList.add(new Function("1024", "删除", "simple", "货位设置","huowei_delete", "button"));
		resList.add(new Function("1025", "查看", "simple", "货位设置","huowei_detail", "button"));
		
		//货品维护
		resList.add(new Function("103", "列表", "complex", "货品维护","huopin_list", "menu"));
		resList.add(new Function("1031", "新增", "simple", "货品维护", "huopin_add", "button"));
		resList.add(new Function("1032", "编辑", "simple", "货品维护","huopin_edit", "button"));
		resList.add(new Function("1034", "删除", "simple", "货品维护","huopin_delete", "button"));
		resList.add(new Function("1035", "查看", "simple", "货品维护","huopin_detail", "button"));
		
		//货品科目维护
		resList.add(new Function("106", "列表", "complex", "货品科目维护","leimu_list", "menu"));
		resList.add(new Function("1061", "新增一级", "simple", "货品科目维护", "leimu_add", "button"));
		resList.add(new Function("1062", "新增二级", "simple", "货品科目维护", "leimu_add2", "button"));
		resList.add(new Function("1063", "新增三级", "simple", "货品科目维护", "leimu_add3", "button"));
		resList.add(new Function("1064", "编辑", "simple", "货品科目维护","leimu_edit", "button"));
		resList.add(new Function("1065", "删除", "simple", "货品科目维护","leimu_delete", "button"));
		resList.add(new Function("1066", "查看", "simple", "货品科目维护","leimu_detail", "button"));
		
		//入库管理
		resList.add(new Function("104", "列表", "complex","入库", "ruku_list", "menu"));
		resList.add(new Function("1041", "新增", "simple", "入库", "ruku_add", "button"));
		resList.add(new Function("1042", "编辑", "simple","入库","ruku_edit", "button"));
		resList.add(new Function("1043", "确认入库", "simple", "入库","ruku_queren", "button"));
		resList.add(new Function("1044", "删除", "simple", "入库","ruku_delete", "button"));
		resList.add(new Function("1045", "查看", "simple", "入库","ruku_detail", "button"));
		resList.add(new Function("1046","打印","simple","入库","ruku_print","button"));
		//出库管理
		resList.add(new Function("105", "列表", "complex","出库", "chuku_list", "menu"));
		resList.add(new Function("1051", "新增", "simple", "出库", "chuku_add", "button"));
		resList.add(new Function("1052", "编辑", "simple","出库","chuku_edit", "button"));
		resList.add(new Function("1053", "确认入库", "simple", "出库","chuku_queren", "button"));
		resList.add(new Function("1054", "删除", "simple", "出库","chuku_delete", "button"));
		resList.add(new Function("1055", "查看", "simple", "出库","chuku_detail", "button"));
		resList.add(new Function("1056","打印","simple","出库","chuku_print","button"));
		
		//车辆管理
		resList.add(new Function("107", "列表", "complex", "车辆管理","car_list", "menu"));
		resList.add(new Function("1071", "新增", "simple", "车辆管理", "car_add", "button"));
		resList.add(new Function("1072", "编辑", "simple", "车辆管理","car_edit", "button"));
		resList.add(new Function("1074", "删除", "simple", "车辆管理","car_delete", "button"));
		resList.add(new Function("1075", "查看", "simple", "车辆管理","car_detail", "button"));
		
		//培训管理
		resList.add(new Function("108", "列表", "complex", "培训管理","peixun_list", "menu"));
		resList.add(new Function("1081", "新增", "simple", "培训管理", "peixun_add", "button"));
		resList.add(new Function("1082", "编辑", "simple", "培训管理","peixun_edit", "button"));
		resList.add(new Function("1084", "删除", "simple", "培训管理","peixun_delete", "button"));
		resList.add(new Function("1085", "查看", "simple", "培训管理","peixun_detail", "button"));
		resList.add(new Function("1086", "导出Excel", "simple", "培训管理","peixun_daochu", "button"));
		
		//进入计划
		resList.add(new Function("301", "列表", "complex", "进入计划","enterplan_list", "menu"));
		resList.add(new Function("3011", "新增", "simple", "进入计划", "enterplan_add", "button"));
		resList.add(new Function("3012", "编辑", "simple", "进入计划","enterplan_edit", "button"));
		resList.add(new Function("3014", "删除", "simple", "进入计划","enterplan_delete", "button"));
		resList.add(new Function("3015", "查看", "simple", "进入计划","enterplan_detail", "button"));
		resList.add(new Function("3016", "打印", "simple", "进入计划","enterplan_print", "button"));
		
		//出去计划
		resList.add(new Function("201", "列表", "complex", "出去计划","outplan_list", "menu"));
		resList.add(new Function("2011", "新增", "simple", "出去计划","outplan_add", "button"));
		resList.add(new Function("2012", "编辑", "simple", "出去计划","outplan_edit", "button"));
		resList.add(new Function("2024", "删除", "simple", "出去计划","outplan_delete", "button"));
		resList.add(new Function("2025", "查看", "simple", "出去计划","outplan_detail", "button"));
		resList.add(new Function("2026", "打印", "simple", "出去计划","outplan_print", "button"));
		
		//库存盘点
		resList.add(new Function("109", "列表", "complex","库存盘点", "pandian_list", "menu"));
		resList.add(new Function("1091", "新增", "simple", "库存盘点", "pandian_add", "button"));
		resList.add(new Function("1092", "编辑", "simple","库存盘点","pandian_edit", "button"));
		resList.add(new Function("1093", "确认完成", "simple","库存盘点","pandian_queren", "button"));
		resList.add(new Function("1094", "删除", "simple", "库存盘点","pandian_delete", "button"));
		resList.add(new Function("1095", "查看", "simple", "库存盘点","pandian_detail", "button"));
		resList.add(new Function("1096","打印","simple","库存盘点","pandian_print","button"));

		
		//库存管理
		resList.add(new Function("209", "列表", "complex", "库存一览", "kucun_list", "menu"));
		
		
		//安检信息
		resList.add(new Function("202", "列表", "complex", "安检信息", "anjian_data_list", "menu"));
		resList.add(new Function("2021", "导出Excel", "simple", "安检信息", "anjian_data_daochu", "button"));
		
		//入库报表
		resList.add(new Function("205", "列表", "complex", "入库报表", "ruku_data_list", "menu"));
		resList.add(new Function("2051", "导出Excel", "simple", "入库报表", "ruku_data_daochu", "button"));
		
		//出库报表
		resList.add(new Function("206", "列表", "complex", "出库报表", "chuku_data_list", "menu"));
		resList.add(new Function("2061", "导出Excel", "simple", "出库报表", "chuku_data_daochu", "button"));
		
		//计划实际比对表
		resList.add(new Function("207", "列表", "complex", "计划实际比对表", "planreal_data_list", "menu"));
		resList.add(new Function("2071", "导出Excel", "simple", "计划实际比对表", "planreal_daochu", "button"));
		
		//车辆一览表
		resList.add(new Function("208", "列表", "complex", "车辆一览", "car_data_list", "menu"));
		resList.add(new Function("2081", "导出Excel", "simple", "车辆一览", "car_data_daochu", "button"));		
		
		//组织结构
		resList.add(new Function("203", "列表", "complex","组织结构", "zuzhi_list", "menu"));
		resList.add(new Function("2031", "新增", "simple", "组织结构", "zuzhi_add", "button"));
		resList.add(new Function("2032", "编辑", "simple","组织结构","zuzhi_edit", "button"));
		resList.add(new Function("2034", "删除", "simple", "组织结构","zuzhi_delete", "button"));
		resList.add(new Function("2035", "查看", "simple", "组织结构","zuzhi_detail", "button"));
		
		//权限管理
		resList.add(new Function("204", "列表", "complex","权限管理", "quanxian_list", "menu"));
		resList.add(new Function("2041", "添加部门", "simple", "权限管理", "quanxian_add1", "button"));
		resList.add(new Function("2042", "添加岗位", "simple","权限管理","quanxian_add2", "button"));
		resList.add(new Function("2044", "添加人员", "simple", "权限管理","quanxian_add3", "button"));
		resList.add(new Function("2045", "修改", "simple", "权限管理","zuzhi_edit", "button"));
		resList.add(new Function("2046", "删除", "simple", "权限管理","zuzhi_delete", "button"));
		resList.add(new Function("2047", "授权", "simple", "权限管理","zuzhi_shouquan", "button"));
		
		//仓库设置
		resList.add(new Function("401","列表","complex","仓库设置","cangku_list","menu"));
		resList.add(new Function("4011","新增","simple","仓库设置","cangku_add","button"));
		resList.add(new Function("4012","编辑","simple","仓库设置","cangku_edit","button"));
		resList.add(new Function("4013","删除","simple","仓库设置","cangku_delete","button"));
		resList.add(new Function("4014","查看","simple","仓库设置","cangku_detail","button"));
/*		
	

		//7.系统设置
		//7.1数据维护
		resList.add(new Function("701", "列表", "complex", ModuleName.F_SHEZHI, ModuleName.S_SET_DATA, "menu"));
		resList.add(new Function("702", "详情", "complex", ModuleName.F_SHEZHI, ModuleName.S_SET_DATA , "button"));
		resList.add(new Function("703", "创建", "simple", ModuleName.F_SHEZHI, ModuleName.S_SET_DATA, "button"));
		resList.add(new Function("704", "编辑", "complex", ModuleName.F_SHEZHI, ModuleName.S_SET_DATA, "button"));
		resList.add(new Function("705", "删除", "complex", ModuleName.F_SHEZHI, ModuleName.S_SET_DATA, "button"));
		//7.2字段自定义
		resList.add(new Function("706", "列表", "complex", ModuleName.F_SHEZHI, ModuleName.S_SET_CUSTOM, "menu"));
		resList.add(new Function("707", "详情", "complex", ModuleName.F_SHEZHI, ModuleName.S_SET_CUSTOM , "button"));
		resList.add(new Function("708", "创建", "simple", ModuleName.F_SHEZHI, ModuleName.S_SET_CUSTOM, "button"));
		resList.add(new Function("709", "编辑", "complex", ModuleName.F_SHEZHI, ModuleName.S_SET_CUSTOM, "button"));
		resList.add(new Function("710", "删除", "complex", ModuleName.F_SHEZHI, ModuleName.S_SET_CUSTOM, "button"));
		//7.3组织结构
		resList.add(new Function("711", "列表", "complex", ModuleName.F_SHEZHI, ModuleName.S_SET_ORG, "menu"));
		resList.add(new Function("712", "详情", "complex", ModuleName.F_SHEZHI, ModuleName.S_SET_ORG , "button"));
		resList.add(new Function("713", "创建", "simple", ModuleName.F_SHEZHI, ModuleName.S_SET_ORG, "button"));
		resList.add(new Function("714", "编辑", "complex", ModuleName.F_SHEZHI, ModuleName.S_SET_ORG, "button"));
		resList.add(new Function("715", "删除", "complex", ModuleName.F_SHEZHI, ModuleName.S_SET_ORG, "button"));
		//7.4权限管理
		resList.add(new Function("716", "列表", "complex", ModuleName.F_SHEZHI, ModuleName.S_SET_PERMISSION, "menu"));
		resList.add(new Function("717", "详情", "complex", ModuleName.F_SHEZHI, ModuleName.S_SET_PERMISSION , "button"));
		resList.add(new Function("718", "创建", "simple", ModuleName.F_SHEZHI, ModuleName.S_SET_PERMISSION, "button"));
		resList.add(new Function("719", "编辑", "complex", ModuleName.F_SHEZHI, ModuleName.S_SET_PERMISSION, "button"));
		resList.add(new Function("720", "删除", "complex", ModuleName.F_SHEZHI, ModuleName.S_SET_PERMISSION, "button"));
		//7.5内部使用
		resList.add(new Function("721", "列表", "complex", ModuleName.F_SHEZHI, ModuleName.S_SET_SUPER, "menu"));
		resList.add(new Function("722", "详情", "complex", ModuleName.F_SHEZHI, ModuleName.S_SET_SUPER , "button"));
		resList.add(new Function("723", "创建", "simple", ModuleName.F_SHEZHI, ModuleName.S_SET_SUPER, "button"));
		resList.add(new Function("724", "编辑", "complex", ModuleName.F_SHEZHI, ModuleName.S_SET_SUPER, "button"));
		resList.add(new Function("725", "删除", "complex", ModuleName.F_SHEZHI, ModuleName.S_SET_SUPER, "button"));
		
		//8.统计图表
		//8.1客户统计
		resList.add(new Function("801", "客户统计", "simple", ModuleName.F_TONGJI, ModuleName.S_STATISTICS_CUST, "menu"));
		resList.add(new Function("802", "销售统计", "simple", ModuleName.F_TONGJI, ModuleName.S_STATISTICS_ORDER, "menu"));
		resList.add(new Function("803", "产品统计", "simple", ModuleName.F_TONGJI, ModuleName.S_STATISTICS_PROD, "menu"));
//		resList.add(new Function("804", "财务统计", "simple", ModuleName.F_TONGJI, ModuleName.S_STATISTICS_FINANCE, "menu"));
*/	}
	public static List<Function> getAllRes(){
		 List<Function> dest=new ArrayList<Function>();
		for(Function z:resList){
			Function zt =new Function();
			try {
				BeanUtils.copyProperties(zt, z);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
			dest.add(zt);
		}
		return  dest;
	}
	public static Function getFunction(String id){
		for(Function function:resList){
			if(StringUtils.equals(id, function.getId())){
				return function;
			}
		}
		return null;
	}
}
