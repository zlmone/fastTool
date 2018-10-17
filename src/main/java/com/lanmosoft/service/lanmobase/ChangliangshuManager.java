package com.lanmosoft.service.lanmobase;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lanmosoft.dao.model.Changliangshu;
import com.lanmosoft.model.WhereCondition;
import com.lanmosoft.service.biz.ChangliangshuService;
import com.lanmosoft.util.PinyinUtil;
import com.lanmosoft.webapp.webmodel.TreeModel;
/**
 * 机构 jigou 部门 bumen岗位 gangwei，人员renyuan
 * @author su.zhang
 *
 */
@Service
public class ChangliangshuManager {
	@Autowired
	SequenceManager sequenceManager;
	@Autowired
	ChangliangshuService changliangshuService;
	/**
	 * 添加节点
	 * @param Changliangshu
	 */
	public void addChangliangshu(Changliangshu changliangshu){
		//TODO 验证
		if(StringUtils.isEmpty(changliangshu.getId())){
			changliangshu.setId(sequenceManager.generateId("changliangshu")); 
		}
		String fujiedian =changliangshu.getFujiedian();
		if(StringUtils.isEmpty(changliangshu.getBianma())){
			changliangshu.setBianma(PinyinUtil.convert(changliangshu.getMingcheng()));
		}
		if(StringUtils.isEmpty(fujiedian)){
			changliangshu.setQuanlujingming("/"+changliangshu.getMingcheng());
			changliangshu.setQuanlujingbianma("/"+changliangshu.getBianma());
			changliangshu.setQuanlujingid("/"+changliangshu.getId());
		}else{
			Changliangshu fu=changliangshuService.loadById(fujiedian);
			changliangshu.setQuanlujingming(fu.getQuanlujingming()+"/"+changliangshu.getMingcheng());
			changliangshu.setQuanlujingbianma(fu.getQuanlujingbianma()+"/"+changliangshu.getBianma());
			changliangshu.setQuanlujingid(fu.getQuanlujingid()+"/"+changliangshu.getId());
		}
		changliangshu.setXuhao(""+sequenceManager.nextSeq("changliangshu_xuhao"));
		changliangshuService.insert(changliangshu);
	}
	/**
	 * 修改节点
	 * @param Changliangshu
	 */
	public void updateChangliangshu(Changliangshu changliangshu){
		//TODO 验证
		WhereCondition wc=new WhereCondition();
		wc.andFullLike("quanlujingid", "/"+changliangshu.getId()+"/");
		List<Changliangshu> list = changliangshuService.query(wc);
		for(Changliangshu z:list){
			String[] arr=StringUtils.split(z.getQuanlujingid(), "/");
			for(int i=0;i<arr.length;i++){
				if(StringUtils.equals(arr[i], changliangshu.getId())){ 
					arr=StringUtils.split(z.getQuanlujingming(), "/");
					arr[i]=changliangshu.getMingcheng();
					z.setQuanlujingming("/"+StringUtils.join(arr, "/"));
					arr=StringUtils.split(z.getQuanlujingbianma(), "/");
					arr[i]=changliangshu.getBianma();
					z.setQuanlujingbianma("/"+StringUtils.join(arr, "/"));
					changliangshuService.update(z);
					break;
				}
			}
		}
		String []arr=StringUtils.split(changliangshu.getQuanlujingming(), "/");
		changliangshu.setQuanlujingming("/"+StringUtils.join(arr, "/"));
		arr=StringUtils.split(changliangshu.getQuanlujingbianma(), "/");
		arr[arr.length-1]=changliangshu.getBianma();
		changliangshu.setQuanlujingbianma("/"+StringUtils.join(arr, "/"));
		changliangshuService.update(changliangshu);
	}
	/**
	 * 禁用节点以及所有下属节点
	 * @param id
	 */
	public void passiveChangliangshu(String id){
		WhereCondition wc = new WhereCondition();
		wc.andFullLike("quanlujingid", id);
		List<Changliangshu> list =changliangshuService.query(wc );
		for(Changliangshu z:list){
			z.setKeyongzhuangtai(GlobalConstants.passiveStatus);
			changliangshuService.update(z);
		}
	}
	/**
	 * 启用节点以及所有下属节点
	 * @param id
	 */
	public void activeChangliangshu(String id){
		WhereCondition wc = new WhereCondition();
		wc.andFullLike("quanlujingid", id);
		List<Changliangshu> list =changliangshuService.query(wc );
		for(Changliangshu z:list){
			z.setKeyongzhuangtai(GlobalConstants.activeStatus);
			changliangshuService.update(z);
		}
	}

	/**
	 * 软删除节点以及所有下属节点
	 * @param id
	 */
	public void softDeleteChangliangshu(String id){
		WhereCondition wc = new WhereCondition();
		wc.andFullLike("quanlujingid", id);
		List<Changliangshu> list =changliangshuService.query(wc );
		for(Changliangshu z:list){
			z.setDelstatus("2");
			changliangshuService.update(z);
		}
	}
	/**
	 * 列出所有节点
	 * @param isIncludePassive 是否包含禁用的节点
	 * @param jiedianleixingList 节点类型
	 * @param id 
	 * @param onlyXiaji 是否只列出下级
	 * @return
	 */
	public List<Changliangshu> listChangliangshu(WhereCondition wc ,String id,List<String> ChangliangshuleixingList,boolean isIncludePassive,boolean onlyXiaji){
		if(StringUtils.isNotEmpty(id)){
			wc.andFullLike("quanlujingid", id);
		}
		if(onlyXiaji && StringUtils.isNotEmpty(id)){
			wc.andEquals("fujiedian", id);
		}else if(onlyXiaji){
			wc.andIsNull("fujiedian");
		}
		if(CollectionUtils.isNotEmpty(ChangliangshuleixingList)){
			wc.andIn("changliangshuleixing", ChangliangshuleixingList);
		}
		List<Changliangshu> list =changliangshuService.query(wc );
		return list;
	}
	public List<TreeModel> refactorChangliangshu(List<Changliangshu> list){
		List<TreeModel> trees = new ArrayList<TreeModel>();
		for(Changliangshu z:list){
			if(StringUtils.isEmpty(z.getFujiedian())){
				TreeModel t = new TreeModel();
				t.setId(z.getId());
				t.setTitle(z.getMingcheng());
				t.setObj(z);
				trees.add(t);
			}
		}
		for(TreeModel t:trees){
			doRefactorChangliangshu( t, list);
		}

		return trees;
	}
	private void doRefactorChangliangshu(TreeModel t,List<Changliangshu> list){
		List<TreeModel> nodes = new ArrayList<TreeModel>();
		for(Changliangshu z:list){
			if(StringUtils.equals(z.getFujiedian(), t.getId())){
				TreeModel tn = new TreeModel();
				tn.setId(z.getId());
				tn.setTitle(z.getMingcheng());
				tn.setObj(z);
				nodes.add(tn);
				doRefactorChangliangshu(tn,list);
			}
		}
		t.setNodes(nodes);
	}
	/**
	 * 移动节点
	 * @param sourceId
	 * @param targetId
	 */
	public void moveChangliangshu(String sourceId,String targetId){
		Changliangshu sChangliangshu = changliangshuService.loadById(sourceId);
		Changliangshu tChangliangshu = changliangshuService.loadById(targetId);
		sChangliangshu.setFujiedian(tChangliangshu.getId());
		sChangliangshu.setQuanlujingid(tChangliangshu.getQuanlujingid()+"/"+sChangliangshu.getId()); 
		sChangliangshu.setQuanlujingbianma(tChangliangshu.getQuanlujingbianma()+"/"+sChangliangshu.getBianma());
		sChangliangshu.setQuanlujingming(tChangliangshu.getQuanlujingming()+"/"+sChangliangshu.getMingcheng());
		changliangshuService.update(sChangliangshu);
		WhereCondition wc = new WhereCondition();
		wc.andFullLike("quanlujingid", sChangliangshu.getId());
		List<Changliangshu> list =changliangshuService.query(wc );
		for(Changliangshu z:list){
			String quanlujingid = z.getQuanlujingid();
			quanlujingid=quanlujingid.substring(quanlujingid.indexOf(sChangliangshu.getId()));
			quanlujingid=quanlujingid.substring(quanlujingid.indexOf("/"));
			quanlujingid=sChangliangshu.getQuanlujingid()+quanlujingid;
			z.setQuanlujingid(quanlujingid );
			String quanlujingbianma = z.getQuanlujingbianma();
			quanlujingbianma=quanlujingbianma.substring(quanlujingbianma.indexOf(sChangliangshu.getBianma()));
			quanlujingbianma=quanlujingbianma.substring(quanlujingbianma.indexOf("/"));
			quanlujingbianma=sChangliangshu.getQuanlujingbianma()+quanlujingbianma;
			z.setQuanlujingbianma(quanlujingbianma );
			String quanlujingming = z.getQuanlujingming();
			quanlujingming=quanlujingming.substring(quanlujingming.indexOf(sChangliangshu.getMingcheng()));
			quanlujingming=quanlujingming.substring(quanlujingming.indexOf("/"));
			quanlujingming=sChangliangshu.getQuanlujingming()+quanlujingming;
			z.setQuanlujingming(quanlujingming );
			changliangshuService.update(z);
		}
	}
}
