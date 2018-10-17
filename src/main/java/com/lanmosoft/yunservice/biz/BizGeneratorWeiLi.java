package com.lanmosoft.yunservice.biz;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.lanmosoft.yunservice.freemarkertools.FreeMarkerUtil;
import com.lanmosoft.yunservice.xmltools.XmlToBean;

public class BizGeneratorWeiLi {
	private static String projectName = "peixun";
	private static String baseDir = "E:/";
	private static String projectConfigDir = baseDir + "/platform/project/"
			+ projectName;
	private static String platformDir = baseDir + "/platform";
	private static String bizDir = projectConfigDir + "/biz";
	private static String bizTemplateDir = platformDir + "/template/biz";
	private static String actionDir = projectConfigDir + "/action";
	private static String actionTemplateDir = platformDir + "/template/action";
	private static String uiserviceDir = projectConfigDir + "/ui/service";
	private static String uiserviceTemplateDir = platformDir
			+ "/template/ui/service";
	private static String uicontrollerDir = projectConfigDir + "/ui/controller";
	private static String uicontrollerTemplateDir = platformDir
			+ "/template/ui/controller";
	private static String uipageDir = projectConfigDir + "/ui/page";
	private static String uipageTemplateDir = platformDir
			+ "/template/ui/page";
	private static String projectHome ="F:/work/"
			+ "liuxue";
	
	private static String mokuai="运营商账户信息";
	
	private static String 指定模型 = "运营商账户信息";
	private static boolean 模型开关 = true;
	private static boolean uipage开关 = false;
	private static boolean controller开关 = true;

	public static void main(String[] args) throws Exception {
			String configName = mokuai+".xml";
			String bizModelDir = bizDir + "/" + configName;
			BizModel bizModel = XmlToBean.parseToBean(BizModel.class,
					bizModelDir);

			FreeMarkerUtil.init(bizTemplateDir);
			String targetFile = "";
			Map map = new HashMap();
			map.put("tables", bizModel.getModels());
			targetFile = projectHome + "/sql/ddl_" + configName + "_.sql";
			FreeMarkerUtil.process("ddl.sql.ftl", map, targetFile);
			for (Model model : bizModel.getModels()) {
				if (StringUtils.isNotEmpty(指定模型)) {
					if (!StringUtils.equals(model.getName(), 指定模型)) {
						continue;
					}
				} 
				
				/*{//验证模型的合法性
					String bixu="[delstatus][creatorId][creatorName][creatorCode][creatorIdPath][creatorNamePath][creatorCodePath][createTime][modifiedTime]";
					List<Field> fsList = model.getFields();
					for(Field f:fsList){
						bixu=bixu.replace("["+f.getId()+"]", "");
					}
					if(bixu.length()>0){
						throw new RuntimeException("模型缺少必须字段："+bixu);
					}
				}*/
				map = new HashMap();
				map.put("table", model);
				map.put("modelName", bizModel.getName());
				map.put("modelId", bizModel.getId());
				if(模型开关){
					targetFile = projectHome
							+ "/src/main/java/com/lanmosoft/dao/model/"
							+ FreeMarkerUtil.capFirst(model.getId()) + ".java";
					FreeMarkerUtil.process("Model_yunShang.java.ftl", map, targetFile);

					targetFile = projectHome
							+ "/src/main/java/com/lanmosoft/dao/mapper/"
							+ FreeMarkerUtil.capFirst(model.getId())
							+ "Mapper.java";
					FreeMarkerUtil.process("Mapper_yunShang.java.ftl", map, targetFile);

					targetFile = projectHome
							+ "/src/main/resources/com/lanmosoft/dao/mapper/"
							+ FreeMarkerUtil.capFirst(model.getId()) + "Mapper.xml";
					FreeMarkerUtil.process("Mapper_yunShang.xml.ftl", map, targetFile);

					targetFile = projectHome
							+ "/src/main/java/com/lanmosoft/service/biz/"
							+ FreeMarkerUtil.capFirst(model.getId())
							+ "Service.java";
					FreeMarkerUtil.process("BizService_yunShang.java.ftl", map, targetFile);
				}
				if(controller开关){
					targetFile = projectHome
							+ "/src/main/java/com/lanmosoft/webapp/controller/"
							+ FreeMarkerUtil.capFirst(model.getId())
							+ "Controller.java";
					FreeMarkerUtil.process("Controller_yunShang.java.ftl", map, targetFile);
				}
				
				if(uipage开关){
				targetFile = projectHome
						+ "/src/main/webapp/ng/js/controllers/"
						+ model.getId()
						+ "_controller.js";
				FreeMarkerUtil.process("Controller_yunShang.js.ftl", map, targetFile);
				
				targetFile = projectHome
						+ "/src/main/webapp/ng/js/services/"
						+ model.getId()
						+ "_service.js";
				FreeMarkerUtil.process("Service_yunShang.js.ftl", map, targetFile);
				
				targetFile = projectHome
						+ "/src/main/webapp/ng/views/"
						+ model.getId()
						+ "-list.html";
				FreeMarkerUtil.process("List_yunShang1.html.ftl", map, targetFile);
				
				targetFile = projectHome
						+ "/src/main/webapp/ng/views/"
						+ model.getId()
						+ "-edit.html";
				FreeMarkerUtil.process("Edit_yunShang.html.ftl", map, targetFile);
				
				targetFile = projectHome
						+ "/src/main/webapp/ng/views/"
						+ model.getId()
						+ "-detail.html";
				FreeMarkerUtil.process("Detail_yunShang.html.ftl", map, targetFile);
				}
			}

		System.out.println("end");
	}
}
