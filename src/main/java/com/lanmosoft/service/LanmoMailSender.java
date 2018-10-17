package com.lanmosoft.service;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang.StringUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
@Service
public class LanmoMailSender {
	public static void main(String[] args) throws MessagingException {
		JavaMailSenderImpl sender = new JavaMailSenderImpl();
		sender.setHost("smtp.mail.aliyun.com");
		sender.setUsername("daihuijia@aliyun.com");
		sender.setPassword("lanmo1");
		sender.setProtocol("smtp");

		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message,true);
		message.setFrom(new InternetAddress("daihuijia@aliyun.com"));
		helper.setTo("303189100@qq.com");
		helper.setText("Thank you for ordering!");
		FileSystemResource file = new FileSystemResource(new File("c:/logo.png"));
		helper.addAttachment("logo.png", file);
		helper.setSubject("北风啊");
//		sender.send(message);
		for(int i=0;i<3;i++){
			send("smtp.mail.aliyun.com","25", "daihuijia@aliyun.com", "lanmo1", new String[]{"daihuijia@aliyun.com"},new String[]{},new String[]{}, "Thank you for ordering!"+i, "北风"+i, null,null); 
		}
		
	}
	/**
	 * 发送邮件服务
	 * @param host 邮箱主机
	 * @param userName 用户名
	 * @param passwd 密码
	 * @param to 接收者
	 * @param bodyText 发送内容
	 * @param subject 主题
	 * @param attachmentName 附件名称
	 * @param attachmentPath 附件
	 */
	public static void send(String host,String port,String userName,String passwd,String []to,String []cc,String []bcc,String bodyText,String subject,String attachmentName,String attachmentPath){
		JavaMailSenderImpl sender = new JavaMailSenderImpl();
		sender.setHost(host);
		sender.setPort(Integer.parseInt(port));
		sender.setUsername(userName);
		sender.setPassword(passwd);
		sender.setProtocol("smtp");
		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper=null;
		try {
			if(StringUtils.isNotEmpty(attachmentName)){
				 helper = new MimeMessageHelper(message,true);
			}else{
				 helper = new MimeMessageHelper(message);
			}
		message.setFrom(new InternetAddress(userName));
		helper.setTo(to);
		helper.setBcc(bcc);
		helper.setCc(cc);
		helper.setText(bodyText);
		if(StringUtils.isNotEmpty(attachmentName)){
			FileSystemResource file = new FileSystemResource(new File(attachmentPath));
			helper.addAttachment(attachmentName, file);
		}
		helper.setSubject(subject);
		sender.send(message);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
