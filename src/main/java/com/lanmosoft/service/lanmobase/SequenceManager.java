package com.lanmosoft.service.lanmobase;

import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lanmosoft.dao.model.Xulie;
import com.lanmosoft.service.biz.XulieService;
@Service
public class SequenceManager {
	@Autowired
	XulieService xulieService;

	/**
	 * 获取当前序列号
	 * 
	 * @param type
	 * @return
	 */
	public synchronized long currentSeq(String type) {
		if (StringUtils.isEmpty(type)) {
			throw new RuntimeException("序列类型不能为空");
		}
		Xulie xulie = xulieService.loadById(type);
		if (xulie == null) {
			xulie = new Xulie();
			xulie.setId(type);
			xulie.setDangqian(0L);
			xulie.setShijian(LanDateUtils.currentDateStr());
			xulieService.insert(xulie);
			return 0L;
		} else {
			return xulie.getDangqian();
		}
	}
	
	
	
	public String getUUID() {
		String uuid = UUID.randomUUID().toString();
		return uuid.substring(0, 8) + uuid.substring(9, 13) + uuid.substring(14, 18) + uuid.substring(19, 23) + uuid.substring(24);
	}

	/**
	 * 获取当前序列号，隔天序列号清零
	 * 
	 * @param type
	 * @return
	 */
	public synchronized long currentSeqLimitDay(String type) {
		if (StringUtils.isEmpty(type)) {
			throw new RuntimeException("序列类型不能为空");
		}
		Xulie xulie = xulieService.loadById(type);
		if (xulie == null) {
			xulie = new Xulie();
			xulie.setId(type);
			xulie.setDangqian(0L);
			xulie.setShijian(LanDateUtils.currentDateStr());
			xulieService.insert(xulie);
			return 0L;
		} else {
			if (StringUtils.equals(LanDateUtils.currentDateStr(),
					xulie.getShijian())) {
				return xulie.getDangqian();
			} else {
				xulie.setDangqian(0L);
				xulie.setShijian(LanDateUtils.currentDateStr());
				xulieService.update(xulie);
				return xulie.getDangqian();
			}

		}
	}

	/**
	 * 获取下一个序列号
	 * 
	 * @param type
	 * @return
	 */
	public synchronized long nextSeq(String type) {
		long a = currentSeq(type);
		a = a + 1;
		Xulie xulie = new Xulie();
		xulie.setId(type);
		xulie.setDangqian(a);
		xulie.setShijian(LanDateUtils.currentDateStr());
		xulieService.update(xulie);
		return a;
	}

	/**
	 * 获取下一个序列号，隔天清零
	 * 
	 * @param type
	 * @return
	 */
	public synchronized long nextSeqLimitDay(String type) {
		long a = currentSeqLimitDay(type);
		a = a + 1;
		Xulie xulie = new Xulie();
		xulie.setId(type);
		xulie.setDangqian(a);
		xulie.setShijian(LanDateUtils.currentDateStr());
		xulieService.update(xulie);
		return a;
	}

	/**
	 * ID生成器
	 * 
	 * @param type
	 * @return
	 */
	public String generateId(String type) {
		/*if (StringUtils.isEmpty(type)) {
			throw new RuntimeException("序列类型不能为空");
		}
		type = "Id_" + type;
		long idseq = nextSeq(type);
		String id = type+getFixStr(""+idseq,8);
		return id;*/
		
		String uuid = UUID.randomUUID().toString();
		return uuid.substring(0, 8) + uuid.substring(9, 13) + uuid.substring(14, 18) + uuid.substring(19, 23) + uuid.substring(24);
	}
	
	private static String getFixStr(String s,int size){
		if(StringUtils.isEmpty(s)){
			return null;
		}
		if(s.length()==size){  
			return s;
		}
		
		if(s.length()<size){
			return "000000000".substring(0, size-s.length())+s;
		}
		
		if(s.length()>size){
			return RandomUtil.produceStringAndNumber(size);
		}
		
		return null;
	}
	public static void main(String[] args) {
		System.out.println(getFixStr("94",8));
	}

	/**
	 * 单据凭证号生成
	 * 
	 * @param type
	 * @param prefix
	 *            前缀
	 * @param suffix
	 *            后缀
	 * @param isLimitDay
	 *            是否隔天清零序列号
	 * @return
	 */
	public String generateNO(String type, String prefix, String suffix,
			boolean isLimitDay) {
		if (StringUtils.isEmpty(prefix)) {
			prefix = "";
		}
		if (StringUtils.isEmpty(suffix)) {
			suffix = "";
		}
		if (isLimitDay) {
			long idseq = nextSeqLimitDay("NO_" + type);
			long b = 1000000 + (1000000 + idseq) % 1000000;
			String s = prefix
					+ (Long.parseLong(LanDateUtils.currentDateStr() + b) - 1000000)
					+ suffix;
			return s;
		} else {
			long idseq = nextSeq("NO_" + type);
			long b = 1000000 + (1000000 + idseq) % 1000000;
			String s = prefix + ((100000000L + b) + suffix).substring(1);
			return s;
		}
	}

	/**
	 * 单据凭证号生成
	 * @param type 类型
	 * @param length 长度
	 * @param prefix 前缀
	 * @param suffix 后缀
	 * @param isLimitDay 是否隔天清零序列号
	 * @return
	 */
	public String generateNOWithLength(String type, Integer length, String prefix, String suffix,
			boolean isLimitDay) {
		if(length==null){
			length = 4;
		}
		int baseNum = (int)Math.pow(10, length);
		if (StringUtils.isEmpty(prefix)) {
			prefix = "";
		}
		if (StringUtils.isEmpty(suffix)) {
			suffix = "";
		}
		if (isLimitDay) {
			long idseq = nextSeqLimitDay("NO_" + type);
			String prefixZero = "";
			if(String.valueOf(idseq).length()<length){
				for(int i=0; i<length-String.valueOf(idseq).length(); i++){
					prefixZero += "0";
				}
			}
			String s = prefix + prefixZero+idseq+ suffix;
			return s;
		} else {
			long idseq = nextSeq("NO_" + type);
			String prefixZero = "";
			if(String.valueOf(idseq).length()<length){
				for(int i=0; i<length-String.valueOf(idseq).length(); i++){
					prefixZero += "0";
				}
			}
			String s = prefix + prefixZero+idseq+ suffix;
			return s;
		}
	}
	
}
