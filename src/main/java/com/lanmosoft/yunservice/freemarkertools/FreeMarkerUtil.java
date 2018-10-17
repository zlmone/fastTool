package com.lanmosoft.yunservice.freemarkertools;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Map;

import org.apache.commons.io.FileUtils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.utility.StringUtil;

public class FreeMarkerUtil {

	private static Configuration cfg;

	public Configuration getCfg() {
		return cfg;
	}

	public static void init(String assemble) {
		cfg = new Configuration();
		try {
			cfg.setDirectoryForTemplateLoading(new File(assemble));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void process(String tmplate, Map root, String targetFile)
			throws Exception {
		if (new File(targetFile).exists()) {
			String s = FileUtils.readFileToString(new File(targetFile));
			if (s.indexOf("bak the file by lanmosoft.com") >= 0) {
				System.out.println("已锁定" + targetFile);
				return;
			}
		} else {
			FileUtils.writeStringToFile(new File(targetFile), "");
		}

		Template t = cfg.getTemplate(tmplate);
		Writer out = new OutputStreamWriter(new FileOutputStream(targetFile),
				"UTF-8");
		t.process(root, out);
		out.flush();
		out.close();
	}
	
	public static String capFirst(String a) {
		try {
			return StringUtil.capitalize(a.substring(0, 1)) + a.substring(1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
