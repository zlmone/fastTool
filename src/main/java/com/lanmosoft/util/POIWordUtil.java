package com.lanmosoft.util;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.hwpf.usermodel.Table;
import org.apache.poi.hwpf.usermodel.TableIterator;
import org.apache.poi.hwpf.usermodel.TableRow;
public class POIWordUtil {
	 public static void main(String[] args) throws Exception {
	        Map<String, String> map = new HashMap<String, String>();  
	        map.put("a", "rongzhi_li");
	        map.put("b", "22");
	        map.put("c", "3343");
	        generate("/lanmo/muban/1.doc", "/lanmo/muban/g/t2.doc", map);
	    }

	 /**
	  * @param sourceFile 原始模板文件
	  * @param newFile 目标文件
	  * @param map 替换map
	  * @throws Exception
	  */
	 public static void generate(String sourceFile, String newFile,
	            Map<String, String> map) throws Exception {  
	        FileInputStream in = new FileInputStream(sourceFile);
	        HWPFDocument hwpf = new HWPFDocument(in);
	        Range range = hwpf.getRange();// 得到文档的读取范围
	        for(String s :map.keySet()){
	        	String key = "${"+s+"}";
	            range.replaceText(key, map.get(s));
	        }
	        TableIterator it = new TableIterator(range);
	        // 迭代文档中的表格
	        while (it.hasNext()) {
	            Table tb = (Table) it.next();
	            // 迭代行，默认从0开始
	            for (int i = 0; i < tb.numRows(); i++) {
	                TableRow tr = tb.getRow(i);
	               String trtext= tr.text();
	               System.out.println("trtext:"+trtext);  
	               if(trtext.indexOf("${")>=0){
	            	   tr.delete();
	               }
	            } // end for
	        } // end while
	        FileOutputStream out = new FileOutputStream(newFile);
	        hwpf.write(out);
	        out.flush();
	        out.close();
	    }
	
}

