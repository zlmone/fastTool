package com.lanmosoft.service.lanmobase;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lanmosoft.dao.model.Zuzhi;
import com.lanmosoft.model.WhereCondition;
import com.lanmosoft.service.biz.RenyuanService;
import com.lanmosoft.service.biz.ZuzhiService;
import com.lanmosoft.util.PinyinUtil;
import com.lanmosoft.webapp.webmodel.TreeModel;
/**
 * 机构 jigou 部门 bumen岗位 gangwei，人员renyuan
 * @author su.zhang
 *
 */
@Service
public class ZuzhiManager {
	@Autowired
	SequenceManager sequenceManager;
	@Autowired
	ZuzhiService zuzhiService;
	@Autowired
	RenyuanService renyuanService;
	/**
	 * 添加组织
	 * @param zuzhi
	 */
	public void addZuzhi(Zuzhi zuzhi){
		//TODO 验证
		if(StringUtils.isEmpty(zuzhi.getId())){
			zuzhi.setId(sequenceManager.generateId("zuzhi")); 
		}
		if(StringUtils.isEmpty(zuzhi.getBianma())){
			zuzhi.setBianma(PinyinUtil.convert(zuzhi.getMingcheng())); 
		}
		String fujiedian =zuzhi.getFujiedian();
		if(StringUtils.isEmpty(fujiedian)){
			zuzhi.setQuanlujingming("/"+zuzhi.getMingcheng()+"/");
			zuzhi.setQuanlujingbianma("/"+zuzhi.getBianma()+"/"); 
			zuzhi.setQuanlujingid("/"+zuzhi.getId()+"/");
		}else{
			Zuzhi fu=zuzhiService.loadById(fujiedian);
			zuzhi.setQuanlujingming(filterPath(fu.getQuanlujingming()+zuzhi.getMingcheng()+"/"));
			zuzhi.setQuanlujingbianma(filterPath(fu.getQuanlujingbianma()+zuzhi.getBianma()+"/")); 
			zuzhi.setQuanlujingid(filterPath(fu.getQuanlujingid()+zuzhi.getId()+"/"));
		}
		zuzhi.setXuhao(""+sequenceManager.nextSeq("zuzhi_xuhao"));
		zuzhi.setKeyongzhuangtai(GlobalConstants.activeStatus);
		zuzhiService.insert(zuzhi);
	}
	/**
	 * 修改组织
	 * @param zuzhi
	 */
	public void updateZuzhi(Zuzhi zuzhi){
		//TODO 验证
		WhereCondition wc=new WhereCondition();
		wc.andFullLike("quanlujingid", "/"+zuzhi.getId()+"/");
		List<Zuzhi> list = zuzhiService.query(wc);
		for(Zuzhi z:list){
			String[] arr=StringUtils.split(z.getQuanlujingid(), "/");
			for(int i=0;i<arr.length;i++){
				if(StringUtils.equals(arr[i], zuzhi.getId())){ 
					arr=StringUtils.split(z.getQuanlujingming(), "/");
					arr[i]=zuzhi.getMingcheng();
					z.setQuanlujingming(filterPath("/"+StringUtils.join(arr, "/")));
					arr=StringUtils.split(z.getQuanlujingbianma(), "/");
					arr[i]=zuzhi.getBianma();
					z.setQuanlujingbianma(filterPath("/"+StringUtils.join(arr, "/")));
					zuzhiService.update(z);
					break;
				}
			}
		}
		String []arr=StringUtils.split(zuzhi.getQuanlujingming(), "/");
		arr[arr.length-1]=zuzhi.getMingcheng()+"/";
		zuzhi.setQuanlujingming(filterPath("/"+StringUtils.join(arr, "/")));
		arr=StringUtils.split(zuzhi.getQuanlujingbianma(), "/");
		arr[arr.length-1]=zuzhi.getBianma()+"/";
		zuzhi.setQuanlujingbianma(filterPath("/"+StringUtils.join(arr, "/")));
		zuzhiService.update(zuzhi);
	}
	/**
	 * 禁用组织以及所有下属组织
	 * @param id
	 */
	public void passiveZuzhi(String id){
		WhereCondition wc = new WhereCondition();
		wc.andFullLike("quanlujingid", id);
		List<Zuzhi> list =zuzhiService.query(wc );
		for(Zuzhi z:list){
			z.setKeyongzhuangtai(GlobalConstants.passiveStatus); 
			zuzhiService.update(z);
		}
	}
	/**
	 * 启用组织以及所有下属组织
	 * @param id
	 */
	public void activeZuzhi(String id){
		WhereCondition wc = new WhereCondition();
		wc.andFullLike("quanlujingid", id);
		List<Zuzhi> list =zuzhiService.query(wc );
		for(Zuzhi z:list){
			z.setKeyongzhuangtai(GlobalConstants.activeStatus); 
			zuzhiService.update(z);
		}
	}
	
	/**
	 * 软删除组织以及所有下属组织
	 * @param id
	 */
	public void deleteZuzhi(String id){
		WhereCondition wc = new WhereCondition();
		wc.andFullLike("quanlujingid", id);
		List<Zuzhi> list =zuzhiService.query(wc );
		for(Zuzhi z:list){
			zuzhiService.delete(z.getId());
			if(StringUtils.equals(z.getZuzhileixing(), "renyuan")){
				renyuanService.delete(z.getRenyuanid());
			}
		}
	}
	/**
	 * 列出所有组织
	 * @param isIncludePassive 是否包含禁用的组织
	 * @param jiedianleixingList 节点类型
	 * @param id 
	 * @param onlyXiaji 是否只列出下级
	 * @return
	 */
	public List<Zuzhi> listZuzhi(WhereCondition wc ,String id,List<String> zuzhileixingList,boolean isIncludePassive,boolean onlyXiaji){
		if(!isIncludePassive){
			wc.andEquals("keyongzhuangtai", GlobalConstants.activeStatus) ;
		}else{
		wc.andNotEquals("keyongzhuangtai", GlobalConstants.deleteStatus);
		}
		if(StringUtils.isNotEmpty(id)){
			wc.andFullLike("quanlujingid", "/"+id+"/");
		}
		if(onlyXiaji && StringUtils.isNotEmpty(id)){
			wc.andEquals("fujiedian", id);
		}else if(onlyXiaji){
			wc.andIsNull("fujiedian");
		}
		wc.andNotEquals("keyongzhuangtai", GlobalConstants.deleteStatus);
		if(CollectionUtils.isNotEmpty(zuzhileixingList)){
			wc.andIn("zuzhileixing", zuzhileixingList);
		}
		
		List<Zuzhi> list =zuzhiService.query(wc );
		return list;
	}
	public List<TreeModel> refactorZuzhi(List<Zuzhi> list){
		List<TreeModel> trees = new ArrayList<TreeModel>();
		for(Zuzhi z:list){
			if(StringUtils.isEmpty(z.getFujiedian())){
				TreeModel t = new TreeModel();
				t.setId(z.getId());
				t.setTitle(z.getMingcheng());
				t.setObj(z);
				trees.add(t);
			}
		}
		for(TreeModel t:trees){
			doRefactorZuzhi( t, list);
		}
		
		return trees;
	}
	private void doRefactorZuzhi(TreeModel t,List<Zuzhi> list){
		List<TreeModel> nodes = new ArrayList<TreeModel>();
		for(Zuzhi z:list){
			if(StringUtils.equals(z.getFujiedian(), t.getId())){
				TreeModel tn = new TreeModel();
				tn.setId(z.getId());
				tn.setTitle(z.getMingcheng());
				tn.setObj(z);
				nodes.add(tn);
				doRefactorZuzhi(tn,list);
			}
		}
		t.setNodes(nodes);
	}
	/**
	 * 移动组织
	 * @param sourceId
	 * @param targetId
	 */
	public void moveZuzhi(String sourceId,String targetId){
		Zuzhi sZuzhi = zuzhiService.loadById(sourceId);
		Zuzhi tZuzhi = zuzhiService.loadById(targetId);
		sZuzhi.setFujiedian(tZuzhi.getId());
		sZuzhi.setQuanlujingid(filterPath(tZuzhi.getQuanlujingid()+sZuzhi.getId()+"/")); 
		sZuzhi.setQuanlujingbianma(filterPath(tZuzhi.getQuanlujingbianma()+sZuzhi.getBianma()+"/"));
		sZuzhi.setQuanlujingming(filterPath(tZuzhi.getQuanlujingming()+sZuzhi.getMingcheng()+"/"));
		zuzhiService.update(sZuzhi);
		WhereCondition wc = new WhereCondition();
		wc.andFullLike("quanlujingid", sZuzhi.getId());
		List<Zuzhi> list =zuzhiService.query(wc );
		for(Zuzhi z:list){
			String quanlujingid = z.getQuanlujingid();
			quanlujingid=quanlujingid.substring(quanlujingid.indexOf(sZuzhi.getId()));
			quanlujingid=quanlujingid.substring(quanlujingid.indexOf("/"));
			quanlujingid=sZuzhi.getQuanlujingid()+quanlujingid;
			z.setQuanlujingid(filterPath(quanlujingid) );
			String quanlujingbianma = z.getQuanlujingbianma();
			quanlujingbianma=quanlujingbianma.substring(quanlujingbianma.indexOf(sZuzhi.getBianma()));
			quanlujingbianma=quanlujingbianma.substring(quanlujingbianma.indexOf("/"));
			quanlujingbianma=sZuzhi.getQuanlujingbianma()+quanlujingbianma;
			z.setQuanlujingbianma(filterPath(quanlujingbianma) );
			String quanlujingming = z.getQuanlujingming();
			quanlujingming=quanlujingming.substring(quanlujingming.indexOf(sZuzhi.getMingcheng()));
			quanlujingming=quanlujingming.substring(quanlujingming.indexOf("/"));
			quanlujingming=sZuzhi.getQuanlujingming()+quanlujingming;
			z.setQuanlujingming(filterPath(quanlujingming) );
			zuzhiService.update(z);
		}
	}
	public static void main(String[] args) {
		String a="/a/b/c32423/d/e/f/g";
		String []ar=StringUtils.split(a,"/");
		System.out.println(StringUtils.join(ar,"/"));   
	}
	private String filterPath(String s){
		return s.replace("//", "/");
	}
	
}
