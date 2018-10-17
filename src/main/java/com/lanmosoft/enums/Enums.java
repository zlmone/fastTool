package com.lanmosoft.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>ClassName: Enums</p>
 * <p>Description: 各种枚举数值的类</p>
 * <p>Author: Jackie</p>
 * <p>Date: 2014年3月5日</p>
 */
public class Enums {
	public static String BASETEMPLATEPATH = "/lanmo/download/template";
	
	public static String TEL_URL = "https://tcc.taobao.com/cc/json/mobile_tel_segment.htm";
	
	public class TelStatus{
		public static final String WEIDIAOCHA = "0";
		public static final String YIHESHI = "1";
	}
	
	public class path{
		public static final String f = "F:";
	}
	
	public class Shenhe{
		public static final String DAISHENHE = "1";//待审核
		public static final String YISHENHE = "2";//已审核
		public static final String BOHUI = "3";//驳回
	}
	
	
	
	public class ProcessStatus {//服务流程
		 public static final String  tanpan= "Id_Process00000001";
		 public static final String  celiang= "Id_Process00000002";
		 public static final String  chutu= "Id_Process00000003";
		 public static final String  shengchanmingxi= "Id_Process00000004";
		 public static final String  churuku= "Id_Process00000005";
		 public static final String  fahuo= "Id_Process00000006";
		 public static final String  anzhuang= "Id_Process00000007";
		 public static final String  weihu= "Id_Process00000008";
	/*	 public static final String  weihu= "Id_Process00000009";
		 public static final String  daidian= "Id_Process00000010";
		 public static final String  qita= "Id_Process00000011";*/
	}
	
	public class Process_mingxiStatus {//服务流程明细
		//"协助谈判"
		     public static final String  xiezhu= "Id_Process_mingxi00000001";
			
		//"测量"
		 	 public static final String  celiang= "Id_Process_mingxi00000002";
		
		//"出图"  
			 public static final String  chutu= "Id_Process_mingxi00000003";
		
	    //效果图确认
			 public static final String  xiaoguotu= "Id_Process_mingxi00000004";

		//生产
			 public static final String  shengchan= "Id_Process_mingxi00000005";
			 
		//出入库
			 public static final String  churuku= "Id_Process_mingxi00000006";
		//发货
			 public static final String  fahuo= "Id_Process_mingxi00000007";
		//安装
			 public static final String  anzhuang= "Id_Process_mingxi00000008";
			 
		//维修维护
			 public static final String  weihu= "Id_Process_mingxi00000009";
	}
	
	//生产明细的出库状态
	public class orderStatus{
			public static final String  chushihua= "0";
			public static final String  tijiao= "1";
		}
	
	//生产明细的出库状态
	public class shengchanmingxi{
		public static final String  chushihua= "0";
		public static final String  bufenruku= "1";
		public static final String  wanquanruku= "2";
	}
	
	
	//出库状态
	public class chukuzhuangtai{
		public static final String  quren= "1";//确认
		public static final String  fahuo= "2";//发货
		public static final String  fahuoqueren= "3";//发货
		
	}
	//订单的审核 类型
		public class order_shenhetype{
			public static final String  celiang= "测量表单";
			public static final String  appfankui= "APP反馈";
			public static final String  chutu= "出图表单";
			public static final String  shejigao= "设计稿件";
			public static final String  xiaoguotu= "效果图确认";
			public static final String  shengchanmingxi= "生产明细";
			public static final String  anzhuang= "安装表单";
			public static final String  anzhuangAPP= "安装APP";
		}
	
	//测量表单的审核
	public class celiangbiaodan{
		public static final String  daishenhe= "1";
		public static final String  shenhe_sucess= "2";
		public static final String  shenhe_shibai= "3";
	}
	//测量表单的App反馈的审核
		public class appfankui{
			public static final String  daishenhe= "1";
			public static final String  shenhe_sucess= "2";
			public static final String  shenhe_shibai= "3";
		}
	//出图表单的审核
		public class chutubiaodan{
			public static final String  daishenhe= "1";
			public static final String  shenhe_sucess= "2";
			public static final String  shenhe_shibai= "3";
		}
	//生产明细的审核
		public class shengchanmingxis{
			public static final String  daishenhe= "1";
			public static final String  shenhe_sucess= "2";
			public static final String  shenhe_shibai= "3";
		}
		//安装表单
		public class anzhuangbiaodan{
			public static final String  daishenhe= "1";
			public static final String  shenhe_sucess= "2";
			public static final String  shenhe_shibai= "3";
		}
		//安装表单
		public class appanzhuang{
			public static final String  daishenhe= "1";
			public static final String  shenhe_sucess= "2";
			public static final String  shenhe_shibai= "3";
				}
		
	
	
	
	public static String SEPARATOR = "/";
	public static String COMMA = ",";
	public class HetongStatus {
	        public static final String NEW = "待审批";
	        public static final String VALIDATING = "审批中";
	        public static final String VALIDATED = "审批通过";
	        public static final String VALIDATED_TERMINATE = "驳回";
	}
	public class Processstatus {
    	public static final String ACTIVITY_STATUS_PROGRESS = "1";// 流程进行中
    	public static final String ACTIVITY_STATUS_END = "2"; // 流程结束
    	public static final String ACTIVITY_STATUS_TERMINATE = "3"; // 流程终结
    }
	public class DelStatus {
    	public static final String UNREMOVED = "0";
    	public static final String REMOVED = "1";
    }
	public class Mokuai {
    	public static final String M_KEHU = "kehu";
    	public static final String M_CHANPIN = "chanpin";
    }
	public static final String TRUE_STRING = "1";
	public static final String FALSE_STRING = "0";
	
	public class Ziduanleixing {
		public static final String TYPE_TEXT = "text";
		public static final String TYPE_TEXTAREA = "textarea";
    	public static final String TYPE_DECIMAL = "decimal";
    	public static final String TYPE_LONG = "long";
    	public static final String TYPE_DATE = "date";
    	public static final String TYPE_SELECT = "select";
    	public static final String TYPE_SPECIAL = "special";
    	public static final String TYPE_PICTURE = "picture";
	}
	public class SearchType {
		public static final String DAY = "day";
		public static final String WEEK = "week";
    	public static final String MONTH = "month";
    	public static final String LATEST_C = "latest_c";
    	public static final String LATEST_U = "latest_u";
	}
	public class OrgType {
		public static final String DEPARTMENT = "dept";
		public static final String POSITION = "post";
	}
	public class IconSkin{
		public static final String pIcon02 = "pIcon02";
		public static final String POSITION = "post";
	}
	public class ModuleName{
		public static final String F_YINGXIAO = "yingxiao";
		public static final String S_MARKETING_SENDSMS = "sendsms";
		public static final String S_MARKETING_SMSRECORD= "smsrecord";
		
		public static final String F_KEHU = "kehu";
		public static final String S_CUST_CUSTOM= "cust_custom";
		public static final String S_CUST_CONTACT= "cust_contact";
		public static final String S_CUST_LOG= "cust_log";
		
		public static final String F_XIAOSHOU = "xiaoshou";
		public static final String S_SALES_QUOTATION= "sales_quotation";
		public static final String S_SALES_CONTRACT = "sales_contract";
		
		public static final String F_CHANPIN = "chanpin";
		public static final String S_PROD_PROD= "prod_prod";
		public static final String S_PROD_ANALYTICS= "prod_analytics";
		
		public static final String F_CAIWU = "caiwu";
		public static final String S_FINANCE_RECEIVABLES = "finance_receivables";
		public static final String S_FINANCE_RECEIVINGORDER = "finance_receivingorder";
		public static final String S_FINANCE_PAYABLES = "finance_payables";
		public static final String S_FINANCE_PAYMENTORDER = "finance_paymentorder";
		public static final String S_FINANCE_ACCOUNT = "finance_account";
		public static final String S_FINANCE_INVOICE = "finance_invoice";
		public static final String S_FINANCE_TRANSFERS = "finance_transfers";
		public static final String S_FINANCE_LOG = "finance_log";
		
		public static final String F_BANGONG = "bangong";
		public static final String S_OFFICE_ANNOUNCEMENT = "office_announcement";
		public static final String S_OFFICE_SCHEDULE = "office_schedule";
		public static final String S_OFFICE_LOG = "office_log";
		
		public static final String F_SHEZHI = "shezhi";
		public static final String S_SET_DATA = "set_data";
		public static final String S_SET_CUSTOM = "set_custom";
		public static final String S_SET_ORG = "set_org";
		public static final String S_SET_PERMISSION = "set_permission";
		public static final String S_SET_SUPER = "set_super";
		
		public static final String F_TONGJI = "tongji";
		public static final String S_STATISTICS_CUST = "STATISTICS_CUST";
		public static final String S_STATISTICS_ORDER = "STATISTICS_ORDER";
		public static final String S_STATISTICS_PROD = "STATISTICS_PROD";
		public static final String S_STATISTICS_FINANCE = "STATISTICS_FINANCE";
	}
	public class DataRole{
		public static final String DISABLED="0";
		public static final String ALL = "1";
		public static final String POST = "2";
		public static final String DEPT = "3";
		public static final String SELF = "4";
	}
	public class ReceivableType{
		public static final String BUSINESS = "1";
		public static final String OTHER = "2";
	}
	public class ReceivableStatus{
		public static final String INIT = "0";
		public static final String PART = "1";
		public static final String ALL = "2";
	}
	public class AccountType{
		public static final String ZICHAN_ACCOUNT = "1";//资产
		public static final String FUZHAI_ACCOUNT = "2";//负债
		public static final String SUNYI_ACCOUNT = "3";//损益
		public static final String QUANYI_ACCOUNT = "4";//所有者权益
	}
	public class MoveType{
		public static final String MOVEUP = "up";//上移
		public static final String MOVEDOWN = "down";//下移
		public static final String MOVETOP = "top";//置顶
		public static final String MOVEBOTTOM = "bottom";//置底
	}
	public class TradeType{
		public static final String INCOME = "1";
		public static final String EXPENDITURE = "2";
	}
	public class ReceiptType{
		public static final String BUSINESS_INCOME = "1";//业务收款
		public static final String OTHER_INCOME = "2";//其他收款
		public static final String BUSINESS_EXPENDITURE = "3";//业务付款
		public static final String OTHER_EXPENDITURE = "4";//其他付款
	}
	public class Module{
		public static final String CONTACTS = "contacts";//联系人
		public static final String CUSTOMER = "customer";//客户
	}
	public class UserType{
		public static final String SUPER = "0";//超级管理员
		public static final String ADMIN = "1";//管理员
		public static final String GENERAL = "2";//普通用户
	}
	public class Operating{
		public static final String EDITANDDELETE = "0";//修改，删除
		public static final String EDIT = "1";//修改
		public static final String DELETE = "2";//删除
		public static final String NONE = "3";//无
	}
	public class MsgType{
		public static final String SENDBOX = "0";
		public static final String RECEIVEDBOX = "1";
	}
	public class LogType{
		public static final String DAY_LOG = "1";
		public static final String WEEK_LOG = "2";
		public static final String MONTH_LOG = "3";
	}
	public class CustLaiyuan{
		public static final String DIANHUALAIFANG = "电话来访";
		public static final String KEHUJIESHAO = "客户介绍";
		public static final String DULIKAIFA = "独立开发";
		public static final String WANGLUO = "网络";
		public static final String DAILISHANG = "代理商";
		public static final String EMAIL = "Email群发";
		public static final String KOUBEI = "口碑";
		public static final String QITA = "其他";
	}
	public class CustType{
		public static final String SHIBAIKEHU = "失败客户";
		public static final String QIANZAIKEHU = "潜在客户";
		public static final String ZHONGDIANKEHU = "重点客户";
		public static final String CHENGJIAOKEHU = "成交客户";
	}
	public class SMSStatusCode{
		public static final String Code__1 = "-1";//用户名或密码不正确
		public static final String Code_0 = "0";//发送失败
		public static final String Code_1 = "1";//发送成功
		public static final String Code_2 = "2";//扣费
		public static final String Code_3 = "3";
		public static final String Code_5 = "5";
		public static final String Code_6 = "6";
		public static final String Code_7 = "7";
		public static final String Code_8 = "8";
		public static final String Code_9 = "9";
		public static final String Code_10 = "10";
		public static final String Code_11 = "11";
		public static final String Code_12 = "12";
	}
	public class TemplatePage{
		public static final String prod_list = "prod-list";
		public static final String prod_edit = "prod-edit";
		public static final String prod_detail = "prod-detail";
		public static final String cust_list = "cust-list";
		public static final String cust_edit = "cust-edit";
		public static final String cust_detail = "cust-detail";
		public static final String cust_search_list = "cust-search-list";
		public static final String baojiadan_edit = "baojiadan-edit";
		public static final String baojiadan_detail = "baojiadan-detail";
		public static final String order_edit = "order-edit";
		public static final String order_detail = "order-detail";
	}
	public class PayInvoiceStatus{
		public static final String INIT = "0";
		public static final String PART = "1";
		public static final String ALL = "2";
	}
	public class OwnerType{
		public static final String MINE = "mine";
		public static final String SUB = "sub";
		public static final String ALL = "all";
	}
	public class MobanType{
		public static final String RICHTEXT_MOBAN = "1";
		public static final String WORD_MOBAN = "2";
	}

    /**
     * <p>ClassName: HetongStatus</p>
     * <p>Description: 合同状态</p>
     * <p>Author: Jackie</p>
     * <p>Date: 2014年3月5日</p>
     */
  
    public class DingdanStatus {
    	public static final String CAOGAO = "草稿";
    	public static final String YITIJIAO = "已提交";
    	public static final String YIPAIDAN = "已派单";
    	public static final String YIJIEDAN = "已接单";
    	public static final String YIDAODA = "已到达";
    	public static final String YIQUXIAO = "已取消";
    	public static final String YIWANCHENG = "已完成";
    	public static final String YIJIESHU = "已结束";
    }
    public class JiesuanStatus {
    	public static final String WEIJIESUAN = "0";//未结算
    	public static final String YIJIESUAN = "1";//已结算
    }
}