package com.lanmosoft.enums;

import java.util.HashMap;
import java.util.Map;

public class ProcessType {
	
	public static Map<String,String> map= new HashMap<String,String>();
	public static Map<String,String> map2= new HashMap<String,String>();
	
	static{
		map.put("73", "baoxiaoshenpi");
		map.put("75", "wuzishenpi");
		
		map.put("33", "lizhishenpi");
		map.put("13", "qingjiashenpi");
		map.put("28", "qingjiashenpi");
		
		
		map.put("100", "genggaiwei1shenpi");
	}

	static{
		map2.put("73", "报销审批");
		map2.put("75", "物资审批");
		
		map2.put("33", "离职审批");
		map2.put("13", "请假(7天以内)审批");
		map2.put("28", "请假(7天以上)审批");
		
		map2.put("100", "更改为1审批");
	}
	
	public static String getZhutiByCode(String code){
		return map.get(code);
	}
	public static String getMingchengByCode(String code){
		return map2.get(code);
	}

}
