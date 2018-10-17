package com.lanmosoft.util;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.hssf.util.Region;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;

import com.lanmosoft.webapp.webmodel.LabelValue;

public class ExcelUtil {
	
	public static String getRowCellStr(Row row,int columnIndex){
		Cell cell  =row.getCell(columnIndex);
		if(cell==null){
			return null;
		}
			String strval=null;
			   switch (cell.getCellType()) {
                case Cell.CELL_TYPE_STRING:
                    strval=cell.getRichStringCellValue().getString();
                    break;
                case Cell.CELL_TYPE_NUMERIC:
                    if (DateUtil.isCellDateFormatted(cell)) {
                        SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
                        strval=sdf.format(cell.getDateCellValue());
                    } else {
                        strval=""+cell.getNumericCellValue();
                    }
                    break;
                case Cell.CELL_TYPE_BOOLEAN:
                    strval=""+cell.getBooleanCellValue();
                    break;
                case Cell.CELL_TYPE_FORMULA:
//                    System.out.println(cell.getCellFormula());
                    break;
                default:
//                    System.out.println();
            }
		return strval;
	}
	
	public static List<String> getRowCellStrList(Row row){
		List<String> rowList = new ArrayList<String>();
		for (Cell cell : row) {
			String strval=null;
			   switch (cell.getCellType()) {
                case Cell.CELL_TYPE_STRING:
                    strval=cell.getRichStringCellValue().getString();
                    break;
                case Cell.CELL_TYPE_NUMERIC:
                    if (DateUtil.isCellDateFormatted(cell)) {
                        SimpleDateFormat sdf =new SimpleDateFormat("yyyyMMdd");
                        strval=sdf.format(cell.getDateCellValue());
                    } else {
                        strval=""+cell.getNumericCellValue();
                    }
                    break;
                case Cell.CELL_TYPE_BOOLEAN:
                    strval=""+cell.getBooleanCellValue();
                    break;
                case Cell.CELL_TYPE_FORMULA:
//                    System.out.println(cell.getCellFormula());
                    break;
                default:
//                    System.out.println();
            }
				rowList.add(strval);
		}
		return rowList;
	}
	
	public static Object getRowCellVal2(Row row,int columnIndex){
		Cell cell  =row.getCell(columnIndex);
		if(cell==null){
			return null;
		}
			Object strval=null;
			   switch (cell.getCellType()) {
                case Cell.CELL_TYPE_STRING:
                    strval=cell.getRichStringCellValue().getString();
                    break;
                case Cell.CELL_TYPE_NUMERIC:
                    if (DateUtil.isCellDateFormatted(cell)) {
                        strval=cell.getDateCellValue();
                    } else {
                    	cell.setCellType(Cell.CELL_TYPE_STRING);                                              
                        String temp = cell.getStringCellValue();
                        if (temp.indexOf(".") > -1) {
                        	strval = String.valueOf(new Double(temp)).trim();
                        } else {
                        	strval = temp.trim();
                        }                        
                    }
                    break;
                case Cell.CELL_TYPE_BOOLEAN:
                    strval=""+cell.getBooleanCellValue();
                    break;
                case Cell.CELL_TYPE_FORMULA:
                	strval = cell.getNumericCellValue();
                    break;
                default:
//                    System.out.println();
            }
		return strval;
	}
	
	@SuppressWarnings({"unchecked"})
	public static HSSFWorkbook exportExcelRiLi(List<String> listKey, List<Map> listValue,String sl,
			List<String> listKeyR,List<Map> listValueR,List<String> listKeyD,List<Map> listValueD) {
	      // 声明一个工作薄
	      HSSFWorkbook workbook = new HSSFWorkbook();
	   // 生成一个表格
	      HSSFSheet sheet = workbook.createSheet(sl);
	      // 设置表格默认列宽度为30个字节
	      sheet.setDefaultColumnWidth((int) 30);
	      sheet.setColumnWidth(0, 8 * 256);
	      // 生成一个样式
	      HSSFCellStyle style = workbook.createCellStyle();
	      // 设置这些样式
	      style.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);
	      style.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);
	      style.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
	      style.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);
	      style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	      style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); 
	      // 生成一个字体
	      HSSFFont font = workbook.createFont();
	      font.setColor(HSSFColor.VIOLET.index);
	      font.setFontHeightInPoints((short) 12);
	      font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
	      // 把字体应用到当前的样式
	      style.setFont(font);
	      // 生成并设置另一个样式
	      HSSFCellStyle style2 = workbook.createCellStyle();
	      style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
	      style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
	      style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
	      style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
	      style2.setAlignment(HSSFCellStyle.ALIGN_LEFT);
	      style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	      // 生成另一个字体
	      HSSFFont font2 = workbook.createFont();
	      font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
	      // 把字体应用到当前的样式
	      style2.setFont(font2);
	      
	      // 生成并设置另一个样式
	      HSSFCellStyle style3 = workbook.createCellStyle();
	      style3.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);
	      style3.setBorderLeft(HSSFCellStyle.BORDER_THIN);
	      style3.setBorderRight(HSSFCellStyle.BORDER_THIN);
	      style3.setBorderTop(HSSFCellStyle.BORDER_THIN);
	      style3.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	      style3.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	      // 生成另一个字体
	      HSSFFont font3 = workbook.createFont();
	      font3.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
	      // 把字体应用到当前的样式
	      style3.setFont(font3);
	      
	      // 生成并设置另一个样式
	      HSSFCellStyle style4 = workbook.createCellStyle();
	      style4.setBorderBottom(HSSFCellStyle.BORDER_THIN);
	      style4.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);
	      style4.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
	      style4.setBorderTop(HSSFCellStyle.BORDER_THIN);
	      style4.setAlignment(HSSFCellStyle.ALIGN_LEFT);
	      style4.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	      // 生成另一个字体
	      HSSFFont font4 = workbook.createFont();
	      font4.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
	      // 把字体应用到当前的样式
	      style4.setFont(font4);
	      
	   // 生成并设置另一个样式
	      HSSFCellStyle style5 = workbook.createCellStyle();
	      style5.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);
	      style5.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);
	      style5.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
	      style5.setBorderTop(HSSFCellStyle.BORDER_THIN);
	      style5.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	      style5.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

	      // 把字体应用到当前的样式
	      style5.setFont(font4);
	      
	      
	   // 生成一个样式
	      HSSFCellStyle style6 = workbook.createCellStyle();
	      // 设置这些样式
	      style6.setBorderBottom(HSSFCellStyle.BORDER_THIN);
	      style6.setBorderLeft(HSSFCellStyle.BORDER_THIN);
	      style6.setBorderRight(HSSFCellStyle.BORDER_THIN);
	      style6.setBorderTop(HSSFCellStyle.BORDER_THIN);
	      style6.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	      style6.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); 
	      // 生成一个字体
	      HSSFFont font6 = workbook.createFont();
	      font6.setFontHeightInPoints((short) 10);
	      font6.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
	      // 把字体应用到当前的样式
	      style6.setFont(font6);
	      
	      
	      HSSFCellStyle cellStyle = workbook.createCellStyle();    //创建一个样式
	      //cellStyle.setFillForegroundColor(HSSFColor.LIGHT_BLUE.index);    //设置颜色为蓝色
	      
	      HSSFPalette palette = workbook.getCustomPalette();  //wb HSSFWorkbook对象
	      palette.setColorAtIndex((short)9, (byte) (0xff & 155), (byte) (0xff & 194), (byte) (0xff & 230));//设置颜色为蓝色
	      cellStyle.setFillForegroundColor((short) 9);
	      
	      cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
	      cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
	      cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
	      cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
	      cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
	      cellStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
	      cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	      // 把字体应用到当前的样式
	      cellStyle.setFont(font2);

	      int index = 0;
	      //产生表格标题行
	      HSSFRow row = sheet.createRow(index);
	      for (int i = 0; i < listKey.size(); i++) {
	         HSSFCell cell = row.createCell(i);
	         cell.setCellStyle(style);
	         HSSFRichTextString text = new HSSFRichTextString(listKey.get(i));
	         cell.setCellValue(text);
	      }
	      //遍历集合数据，产生数据行
	      for(Map m:listValue){
	    	  index++;
		      row = sheet.createRow(index);
		      for(int i = 0; i < listKey.size(); i++){
				  HSSFCell cell = row.createCell(i);
				  if(i==0){
					  if(index%8==0){
						  cell.setCellStyle(style5);
					  }else{
						  cell.setCellStyle(style4);
					  } 
				  }else{
					  if(index%8==0){
						  cell.setCellStyle(style3);
					  }else{
						  cell.setCellStyle(style2);
					  }
				  }
				  String value =m.get(listKey.get(i))!=null?m.get(listKey.get(i)).toString():"";
				  if(value!=null&&value.contains("Draft")){
					  cell.setCellStyle(cellStyle);
					  cell.setCellValue(value.substring(0, value.length()-6));
				  }else{
					  if(value!=null&&value.contains("day")){
						  cell.setCellStyle(style6);
						  cell.setCellValue(value.substring(0, value.length()-3));
					  }else{
						  cell.setCellValue(value);
					  }
					  
				  }
				 
			  }	
		  }
	      index++;
	      row = sheet.createRow(index);
	      //产生表格标题行
	      for (int i = 1; i <=listKeyD.size(); i++) {
		      HSSFCell cell = row.createCell(i);
		      cell.setCellStyle(style);
		      HSSFRichTextString text = new HSSFRichTextString(listKeyD.get(i-1));
		      cell.setCellValue(text);
		  }
	    //遍历集合数据，产生数据行
	      if(listValueD!=null){
	    	  for(Map m:listValueD){
		    	  index++;
			      row = sheet.createRow(index);
			      for(int i = 1; i <= listKeyD.size(); i++){
					  HSSFCell cell = row.createCell(i);
		              cell.setCellStyle(style2);
					  String value =m.get(listKeyD.get(i-1))!=null?m.get(listKeyD.get(i-1)).toString():"";
					  cell.setCellValue(value);
				  }	
			  }
	      }
	      index = 0;
	      //产生表格标题行
	      row = sheet.getRow(index);
	      int k=listKey.size();
	      for (int i = 0; i < listKeyR.size(); i++) {
	    	  int r=i+k;
		      HSSFCell cell = row.createCell(r);
		      cell.setCellStyle(style);
		      HSSFRichTextString text = new HSSFRichTextString(listKeyR.get(i));
		      cell.setCellValue(text);
		  }
	      //遍历集合数据，产生数据行
	      for(Map m:listValueR){
	    	  index++;
		      row = sheet.getRow(index);
		      for(int i = 0; i < listKeyR.size(); i++){
		    	  int r=i+k;
				  HSSFCell cell = row.createCell(r);
	              cell.setCellStyle(style2);
				  String value =m.get(listKeyR.get(i))!=null?m.get(listKeyR.get(i)).toString():"";
				  cell.setCellValue(value);
			  }	
		  }
	      return workbook;
	}
	
	@SuppressWarnings({"unchecked"})
	public static HSSFWorkbook exportExcelMx(String title, List<String> listKey, List<Map> listValue, String pattern,String title1,
			String title21,String title22,String title31,String title32,String title41,String title42,String end01,String end02,
			String end03,String end1,String end2) {
	      // 声明一个工作薄
	      HSSFWorkbook workbook = new HSSFWorkbook();
	      // 生成一个表格
	      HSSFSheet sheet = workbook.createSheet(title);
	      // 设置表格默认列宽度为20个字节
	      sheet.setDefaultColumnWidth((int) 20);
	      // 生成一个样式
	      HSSFCellStyle style = workbook.createCellStyle();
	      // 设置这些样式
	      style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
	      style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
	      style.setBorderRight(HSSFCellStyle.BORDER_THIN);
	      style.setBorderTop(HSSFCellStyle.BORDER_THIN);
	      style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	      style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); 
	      // 生成一个字体
	      HSSFFont font = workbook.createFont();
	      font.setColor(HSSFColor.BLACK.index);
	      font.setFontHeightInPoints((short) 12);
	      font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
	      // 把字体应用到当前的样式
	      style.setFont(font);
	      
	   // 生成一个样式
	      HSSFCellStyle style0 = workbook.createCellStyle();
	      // 设置这些样式
	      style0.setAlignment(HSSFCellStyle.ALIGN_LEFT);
	      style0.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); 
	      // 生成一个字体
	      HSSFFont font0 = workbook.createFont();
	      font0.setColor(HSSFColor.BLACK.index);
	      font0.setFontHeightInPoints((short) 12);
	      font0.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
	      // 把字体应用到当前的样式
	      style0.setFont(font0);
	      
	      // 生成一个样式
	      HSSFCellStyle style1 = workbook.createCellStyle();
	      // 设置这些样式
	      style1.setAlignment(HSSFCellStyle.ALIGN_LEFT);
	      style1.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); 
	      // 生成一个字体
	      HSSFFont font1 = workbook.createFont();
	      font1.setColor(HSSFColor.BLACK.index);
	      font1.setFontHeightInPoints((short) 24);
	      font1.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
	      // 把字体应用到当前的样式
	      style1.setFont(font1);
	      // 生成并设置另一个样式
	      HSSFCellStyle style2 = workbook.createCellStyle();
	      style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
	      style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
	      style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
	      style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
	      style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	      style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	      // 生成另一个字体
	      HSSFFont font2 = workbook.createFont();
	      font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
	      // 把字体应用到当前的样式
	      style2.setFont(font2);
	   // 生成并设置另一个样式
	      HSSFCellStyle style3 = workbook.createCellStyle();
	      style3.setAlignment(HSSFCellStyle.ALIGN_LEFT);
	      style3.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	      // 生成另一个字体
	      HSSFFont font3 = workbook.createFont();
	      font3.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
	      // 把字体应用到当前的样式
	      style3.setFont(font3);
	      
	      HSSFRow row = sheet.createRow(0);
	      Region region = new Region((short)0,(short)0,(short)0,(short)4);//合并从第rowFrom行columnFrom列 
	      sheet.addMergedRegion(region);
	      HSSFCell cell = row.createCell(0);
	      cell.setCellStyle(style1);
	      HSSFRichTextString text1 = new HSSFRichTextString(title1);
	      cell.setCellValue(text1);
	      
	      row = sheet.createRow(2);
	      cell = row.createCell(0);
	      cell.setCellStyle(style0);
	      HSSFRichTextString text21 = new HSSFRichTextString(title21);
	      cell.setCellValue(text21);
	      cell = row.createCell(1);
	      cell.setCellStyle(style3);
	      HSSFRichTextString text22 = new HSSFRichTextString(title22);
	      cell.setCellValue(text22);
	      
	      row = sheet.createRow(3);
	      cell = row.createCell(0);
	      cell.setCellStyle(style0);
	      HSSFRichTextString text31 = new HSSFRichTextString(title31);
	      cell.setCellValue(text31);
	      cell = row.createCell(1);
	      cell.setCellStyle(style3);
	      HSSFRichTextString text32 = new HSSFRichTextString(title32);
	      cell.setCellValue(text32);
	      
	      row = sheet.createRow(4);
	      cell = row.createCell(0);
	      cell.setCellStyle(style0);
	      HSSFRichTextString text41 = new HSSFRichTextString(title41);
	      cell.setCellValue(text41);
	      cell = row.createCell(1);
	      cell.setCellStyle(style3);
	      HSSFRichTextString text42 = new HSSFRichTextString(title42);
	      cell.setCellValue(text42);
	      
	      
	      
	      int index = 6;
	      //产生表格标题行
	      row = sheet.createRow(index);
	      for (int i = 0; i < listKey.size(); i++) {
	         cell = row.createCell(i);
	         cell.setCellStyle(style);
	         HSSFRichTextString text = new HSSFRichTextString(listKey.get(i));
	         cell.setCellValue(text);
	      }
	      
	      //遍历集合数据，产生数据行
	      for(Map m:listValue){
	    	  index++;
			  List<String> list2=new ArrayList<String>();
		      row = sheet.createRow(index);
		      for(int i = 0; i < listKey.size(); i++){
				  cell = row.createCell(i);
	              cell.setCellStyle(style2);
				  String value =m.get(listKey.get(i))!=null?m.get(listKey.get(i)).toString():"";
				  cell.setCellValue(value);
			  }	
		  }
	      index++;
	      row = sheet.createRow(index);
	      region = new Region((short)index,(short)0,(short)index,(short)1);//合并从第rowFrom行columnFrom列 
	      sheet.addMergedRegion(region);
	      cell = row.createCell(0);
	      cell.setCellStyle(style);
	      HSSFRichTextString value01 = new HSSFRichTextString(end01);
	      cell.setCellValue(value01);
	      cell = row.createCell(1);
	      cell.setCellStyle(style);
	      cell = row.createCell(2);
	      cell.setCellStyle(style);
	      HSSFRichTextString value02 = new HSSFRichTextString(end02);
	      cell.setCellValue(value02);
	      cell = row.createCell(3);
	      cell.setCellStyle(style);
	      HSSFRichTextString value = new HSSFRichTextString("");
	      cell.setCellValue(value);
	      cell = row.createCell(4);
	      cell.setCellStyle(style);
	      HSSFRichTextString value03 = new HSSFRichTextString(end03);
	      cell.setCellValue(value03);
	      
	      index++;index++;
	      row = sheet.createRow(index);
	      region = new Region((short)index,(short)0,(short)index,(short)4);//合并从第rowFrom行columnFrom列 
	      sheet.addMergedRegion(region);
	      cell = row.createCell(0);
	      cell.setCellStyle(style3);
	      HSSFRichTextString value1 = new HSSFRichTextString(end1);
	      cell.setCellValue(value1);
	      
	      index++;index++;
	      row = sheet.createRow(index);
	      cell = row.createCell(3);
	      cell.setCellStyle(style0);
	      HSSFRichTextString value2 = new HSSFRichTextString(end2);
	      cell.setCellValue(value2);
	      
	      return workbook;
	}//1927
	

	@SuppressWarnings({"unchecked"})
	public static void exportExcelTj(String title, List<String> listKey, 
			List<Map> listValue, OutputStream out, String pattern) {
	      // 声明一个工作薄
	      HSSFWorkbook workbook = new HSSFWorkbook();
	      // 生成一个表格
	      HSSFSheet sheet = workbook.createSheet(title);
	      // 设置表格默认列宽度为15个字节
	      sheet.setDefaultColumnWidth((int) 15);
	      // 生成一个样式
	      HSSFCellStyle style = workbook.createCellStyle();
	      // 设置这些样式
	      style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
	      style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
	      style.setBorderRight(HSSFCellStyle.BORDER_THIN);
	      style.setBorderTop(HSSFCellStyle.BORDER_THIN);
	      style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	      style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); 
	      // 生成一个字体
	      HSSFFont font = workbook.createFont();
	      font.setColor(HSSFColor.VIOLET.index);
	      font.setFontHeightInPoints((short) 12);
	      font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
	      // 把字体应用到当前的样式
	      style.setFont(font);
	      // 生成并设置另一个样式
	      HSSFCellStyle style2 = workbook.createCellStyle();
	      style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
	      style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
	      style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
	      style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
	      style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	      style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	      // 生成另一个字体
	      HSSFFont font2 = workbook.createFont();
	      font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
	      // 把字体应用到当前的样式
	      style2.setFont(font2);
	      int index = 0;
	      //产生表格标题行
	      HSSFRow row = sheet.createRow(index);
	      for (int i = 0; i < listKey.size(); i++) {
	         HSSFCell cell = row.createCell(i);
	         cell.setCellStyle(style);
	         HSSFRichTextString text = new HSSFRichTextString(listKey.get(i));
	         cell.setCellValue(text);
	      }
	      
	      //遍历集合数据，产生数据行
	      for(Map m:listValue){
	    	  index++;
			  List<String> list2=new ArrayList<String>();
		      row = sheet.createRow(index);
		      for(int i = 0; i < listKey.size(); i++){
				  HSSFCell cell = row.createCell(i);
	              cell.setCellStyle(style2);
				  String value =m.get(listKey.get(i))!=null?m.get(listKey.get(i)).toString():"";
				  cell.setCellValue(value);
			  }	
		  }
        try {
           workbook.write(out);
        } catch (IOException e) {
           e.printStackTrace();
        }
	}
	
	
	@SuppressWarnings({"unchecked"})
	public static void exportExcel(String title, List<LabelValue> headers, 
			List<?> dataList, OutputStream out, String pattern) {

      // 声明一个工作薄
      HSSFWorkbook workbook = new HSSFWorkbook();
      // 生成一个表格
      HSSFSheet sheet = workbook.createSheet(title);

      // 设置表格默认列宽度为15个字节

      sheet.setDefaultColumnWidth((int) 15);
      // 生成一个样式
      HSSFCellStyle style = workbook.createCellStyle();
      // 设置这些样式
//      style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
//      style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
      style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
      style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
      style.setBorderRight(HSSFCellStyle.BORDER_THIN);
      style.setBorderTop(HSSFCellStyle.BORDER_THIN);
      style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
      style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
      
      // 生成一个字体
      HSSFFont font = workbook.createFont();
      font.setColor(HSSFColor.VIOLET.index);
      font.setFontHeightInPoints((short) 12);
      font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
      
      
      // 把字体应用到当前的样式
      style.setFont(font);

      // 生成并设置另一个样式
      HSSFCellStyle style2 = workbook.createCellStyle();
//      style2.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
//      style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
      style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
      style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
      style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
      style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
      style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
      style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

      // 生成另一个字体
      HSSFFont font2 = workbook.createFont();
      font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
      // 把字体应用到当前的样式
      style2.setFont(font2);

      // 声明一个画图的顶级管理器
      HSSFPatriarch patriarch = sheet.createDrawingPatriarch();

    /*  // 定义注释的大小和位置,详见文档
      HSSFComment comment = patriarch.createComment(new HSSFClientAnchor(0, 0, 0, 0, (short) 4, 2, (short) 6, 5));

      // 设置注释内容
      comment.setString(new HSSFRichTextString("可以在POI中添加注释！"));

      // 设置注释作者，当鼠标移动到单元格上是可以在状态栏中看到该内容.
      comment.setAuthor("lanmo");*/

      //产生表格标题行
      HSSFRow row = sheet.createRow(0);

      for (int i = 0; i < headers.size(); i++) {
         HSSFCell cell = row.createCell(i);
         cell.setCellStyle(style);
         HSSFRichTextString text = new HSSFRichTextString(headers.get(i).getLabel());
         cell.setCellValue(text);
      }

      //遍历集合数据，产生数据行
      Iterator<?> it = dataList.iterator();
      int index = 0;
      while (it.hasNext()) {
         index++;
         row = sheet.createRow(index);
         Object obj = it.next();
         Class tCls = obj.getClass();
         //利用反射，根据javabean属性的先后顺序，动态调用getXxx()方法得到属性值
         Field[] fields = obj.getClass().getDeclaredFields();
         for (int i = 0; i < fields.length; i++) {
        	 Field field = fields[i];
             String fieldName = field.getName();
             for (int j = 0; j < headers.size(); j++) {
            	 if(StringUtils.equals(headers.get(j).getValue(), fieldName)){
            		 String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() 
                    		 + fieldName.substring(1);
            		  HSSFCell cell = row.createCell(j);
                      cell.setCellStyle(style2);
                      try {
                          Method getMethod = tCls.getMethod(getMethodName,
                                new Class[] {});
                          Object value = getMethod.invoke(obj, new Object[] {});
                          if(value ==null)
                          	value = "";
                          //判断值的类型后进行强制类型转换
                          String textValue = null;

//          	              if (value instanceof Integer) {

//          	                 int intValue = (Integer) value;

//          	                 cell.setCellValue(intValue);

//          	              } else if (value instanceof Float) {

//          	                 float fValue = (Float) value;

//          	                 textValue = new HSSFRichTextString(

//          	                       String.valueOf(fValue));

//          	                 cell.setCellValue(textValue);

//          	              } else if (value instanceof Double) {

//          	                 double dValue = (Double) value;

//          	                 textValue = new HSSFRichTextString(

//          	                       String.valueOf(dValue));

//          	                 cell.setCellValue(textValue);

//          	              } else if (value instanceof Long) {

//          	                 long longValue = (Long) value;

//          	                 cell.setCellValue(longValue);

//          	              } 

                          if (value instanceof Boolean) {
                             boolean bValue = (Boolean) value;
                             textValue = "1";
                             if (!bValue) {
                                textValue ="0";
                             }

                          } else if (value instanceof Date) {
                             Date date = (Date) value;
                             SimpleDateFormat sdf = new SimpleDateFormat(pattern);
                              textValue = sdf.format(date);
                          }  else if (value instanceof byte[]) {
                             // 有图片时，设置行高为60px;
                             row.setHeightInPoints(60);
                             // 设置图片所在列宽度为80px,注意这里单位的一个换算
                             sheet.setColumnWidth(i, (short) (35.7 * 80));
                              sheet.autoSizeColumn(i);
                             byte[] bsValue = (byte[]) value;
                             HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0,
                                   1023, 255, (short) 6, index, (short) 6, index);
                             anchor.setAnchorType(2);
                             patriarch.createPicture(anchor, workbook.addPicture(
                                   bsValue, HSSFWorkbook.PICTURE_TYPE_JPEG));
                          } else{
                             //其它数据类型都当作字符串简单处理
                             textValue = value.toString();
                          }
                          //如果不是图片数据，就利用正则表达式判断textValue是否全部由数字组成
                          if(textValue!=null){
                             Pattern p = Pattern.compile("^//d+(//.//d+)?$");   
                             Matcher matcher = p.matcher(textValue);
                             if(matcher.matches()){
                                //是数字当作double处理
                                cell.setCellValue(Double.parseDouble(textValue));
                             }else{
                                HSSFRichTextString richString = new HSSFRichTextString(textValue);
                                HSSFFont font3 = workbook.createFont();
                                font3.setColor(HSSFColor.BLUE.index);
                                richString.applyFont(font3);
                                cell.setCellValue(richString);
                             }
                          }
                      } catch (SecurityException e) {
                          // TODO Auto-generated catch block
                          e.printStackTrace();

                      } catch (NoSuchMethodException e) {
                          // TODO Auto-generated catch block
                          e.printStackTrace();
                      } catch (IllegalArgumentException e) {
                          // TODO Auto-generated catch block
                          e.printStackTrace();

                      } catch (IllegalAccessException e) {
                          // TODO Auto-generated catch block
                          e.printStackTrace();
                      } catch (InvocationTargetException e) {
                          // TODO Auto-generated catch block
                          e.printStackTrace();
                      } finally {
                          //清理资源
                      }
            	 }
              }
         }
      }

      try {
         workbook.write(out);
      } catch (IOException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
	}
	public static CellStyle generatorHSSFStyle(Workbook workbook){
		CellStyle style = workbook.createCellStyle();
	      // 设置这些样式
//	      style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
//	      style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
	      style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
	      style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
	      style.setBorderRight(HSSFCellStyle.BORDER_THIN);
	      style.setBorderTop(HSSFCellStyle.BORDER_THIN);
	      style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		return style;
	}
	
	public static HSSFCellStyle generatorBodyHSSFStyle1(HSSFWorkbook workbook){
		HSSFCellStyle style = workbook.createCellStyle();
		HSSFDataFormat df = workbook.createDataFormat();
	    style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
	    style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
	    style.setBorderRight(HSSFCellStyle.BORDER_THIN);
	    style.setBorderTop(HSSFCellStyle.BORDER_THIN);
	    style.setDataFormat(df.getFormat("#,##0"));
		return style;
	}
	public static HSSFCellStyle generatorBodyHSSFStyle2(HSSFWorkbook workbook){
		HSSFCellStyle style = workbook.createCellStyle();
		HSSFDataFormat df = workbook.createDataFormat();
	    style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
	    style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
	    style.setBorderRight(HSSFCellStyle.BORDER_THIN);
	    style.setBorderTop(HSSFCellStyle.BORDER_THIN);
	    style.setDataFormat(df.getFormat("#,##0.00"));
		return style;
	}
	public static HSSFCellStyle generatorFirstHSSFStyle(HSSFWorkbook workbook){
		 // 生成一个样式
		HSSFCellStyle style = workbook.createCellStyle();
//      style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
//      style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
      style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
      style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
      style.setBorderRight(HSSFCellStyle.BORDER_THIN);
      style.setBorderTop(HSSFCellStyle.BORDER_THIN);
      style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
      style.setVerticalAlignment(HSSFCellStyle.ALIGN_CENTER);
      
      // 生成一个字体
      HSSFFont font = workbook.createFont();
      font.setColor(HSSFColor.VIOLET.index);
      font.setFontHeightInPoints((short) 12);
      font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
      // 把字体应用到样式
      style.setFont(font);
      return style;
	}
}
