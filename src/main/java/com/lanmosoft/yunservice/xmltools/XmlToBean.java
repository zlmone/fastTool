package com.lanmosoft.yunservice.xmltools;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.ValidationEvent;
import javax.xml.bind.ValidationEventHandler;

import org.apache.commons.io.FileUtils;

public class XmlToBean {

	public static void main(String[] args) {
		Bean t= parseToBean(Bean.class,"/workspace/lanmosvn/xinpingtai2_0/generated/pm/test.xml");
		System.out.println(t.getName());
		System.out.println(t.getCode());
	}
	 //xml转换成bean
    public static <T>  T parseToBean( Class<T> type,String filepath)  {  
    	File f= new File(filepath);
    	String xmlstr;
		try {
			xmlstr = FileUtils.readFileToString(f);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return null;
		}
    	T requestXml = null;  
        try {  
            JAXBContext jaxbContext = JAXBContext.newInstance(type);  
            Unmarshaller um = jaxbContext.createUnmarshaller();  
            um.setEventHandler(
            	    new ValidationEventHandler() {
            	        public boolean handleEvent(ValidationEvent event ) {
            	        	System.out.println("解析xml有错误:"+event.getMessage());
            	        	  event.getLinkedException().printStackTrace();
            	            throw new RuntimeException(event.getMessage(),
            	                                       event.getLinkedException());
            	          
            	        }
            	});
            requestXml = (T)um.unmarshal(new ByteArrayInputStream(xmlstr.getBytes()));  
        } catch (JAXBException e) {  
            e.getMessage();  
        }  
        return requestXml;  
    }
}
