'use strict';
MetronicApp.controller('order_controller', function($rootScope, $scope,$state,$stateParams,order_service,alertService,filterFilter,$modal, $http, $timeout) {
    $scope.$on('$viewContentLoaded', function() {
        // initialize core components
        Metronic.initAjax();
        ComponentsPickers.init();
    });
    var order = {};
    order.item={};
    $scope.order = order;
    //初始化controller
    initController($scope, order, 'order',  order_service, filterFilter);
    order.searchForm.orderGuize="addtime desc";
	order.searchForm.fn='107';//功能号
	order.searchForm.type='1'
    order.search1();
	
    $scope.qu=function(action){
    	if(action=="0"){
    		$state.go('order-list');
    	}else if(action=="1"){
    		$state.go('order-list1');
    	}
    	else if(action=="2"){
    		$state.go('order-list2');
    	}
    }
    
    $scope.jiegua = function(d){
    	order.ziliao={};
    	order.ziliao.id = d.id;
		 var submitform={};
	     submitform.item = order.ziliao;
		 var promise = order_service.jiegua(submitform);
		 promise.then(function (data) {  // 调用承诺API获取数据 .resolve
			      if(data.status=='success'){
			    	  order.search1();
	                toastr['success']("您的信息已保存成功！", "")
	            }else{
	                toastr[data.status](data.message, "")
	            }
	        }, function (data) {  // 处理错误 .reject
				Metronic.unblockUI();
	            toastr['error']("您的信息保存出错！", "SORRY！")
	        });
    }
    
	order.changeCondition2=function(){
        if(isNull(order.searchForm.searchItemName)){
            $scope.conditionContent = conditionText;
            $scope.searchItemValue_type='text';
        }else{
           if(order.searchForm.searchItemName.toLowerCase().endWith('_select')){
			    $scope.searchItemValue_type='select';
			    $scope.conditionValues =[];
            }else{
               order.changeCondition();
           }
        }
    }
	
    $scope.detail=function(d){
        var submitform={};
        order.item.id = d.id;
        order.item.shenpiStatus="1";
        order.item.action="1";
        submitform.item = order.item;
        var promise = order_service.createOrUpdate(submitform);
        promise.then(function (data) {  // 调用承诺API获取数据 .resolve
		      if(data.data.status=='success'){
		    	  $state.go('order-edit',{id: d.id});
            }else{
                toastr[data.data.status](data.data.message, "")
            }
        }, function (data) {  // 处理错误 .reject
            toastr['error']("您的信息保存出错！", "SORRY！")
        });
        
    }
    
    $scope.add = function(){
    	$state.go('order-add',{id: ''});
    }
    
    order.deleteData = function(d){   	
    	var con = confirm("删除是不可恢复的，你确认要删除吗？");
    	
    	var ds = [];
    	ds.push(d);
    	if(con){
    		var promise = order_service.delete(ds);	
    		promise.then(function(data){
    			
    			if(data.data.status=='success'){
                    order.searchN();
                    alertService.clearAlert();
                    alertService.add(data.data.status,data.data.message);
                }else{
                    alertService.clearAlert();
                    alertService.add(data.data.status,data.data.message);
                }
    			
    		})
    		
    	}
    }
    
        
    // set sidebar closed and body solid layout mode
    $rootScope.settings.layout.pageBodySolid = true;
    $rootScope.settings.layout.pageSidebarClosed = false;
});

MetronicApp.controller('order_controller1', function($rootScope, $scope,$state,$stateParams,order_service,alertService,filterFilter,$modal, $http, $timeout) {
    $scope.$on('$viewContentLoaded', function() {
        // initialize core components
        Metronic.initAjax();
        ComponentsPickers.init();
    });
    var order = {};
    $scope.order = order;
    //初始化controller
    initController($scope, order, 'order',  order_service, filterFilter);
    order.searchForm.type="2";
    order.searchForm.orderGuize="shenpiTime desc";
	order.searchForm.fn='107';//功能号
    order.search1();
	
	
	order.changeCondition2=function(){
        if(isNull(order.searchForm.searchItemName)){
            $scope.conditionContent = conditionText;
            $scope.searchItemValue_type='text';
        }else{
           if(order.searchForm.searchItemName.toLowerCase().endWith('_select')){
			    $scope.searchItemValue_type='select';
			    $scope.conditionValues =[];
            }else{
               order.changeCondition();
           }
        }
    }
	
	$scope.qu=function(action){
    	if(action=="0"){
    		$state.go('order-list');
    	}else if(action=="1"){
    		$state.go('order-list1');
    	}
    	else if(action=="2"){
    		$state.go('order-list2');
    	}
    }
	
    $scope.detail=function(d){
        $state.go('order-edit',{id: d.id});
    }
    
    order.deleteData = function(d){   	
    	var con = confirm("删除是不可恢复的，你确认要删除吗？");
    	
    	var ds = [];
    	ds.push(d);
    	if(con){
    		var promise = order_service.delete(ds);	
    		promise.then(function(data){
    			
    			if(data.data.status=='success'){
                    order.searchN();
                    alertService.clearAlert();
                    alertService.add(data.data.status,data.data.message);
                }else{
                    alertService.clearAlert();
                    alertService.add(data.data.status,data.data.message);
                }
    			
    		})
    		
    	}
    }
    
        
    // set sidebar closed and body solid layout mode
    $rootScope.settings.layout.pageBodySolid = true;
    $rootScope.settings.layout.pageSidebarClosed = false;
});

MetronicApp.controller('order_controller2', function($rootScope, $scope,$state,$stateParams,order_service,alertService,filterFilter,$modal, $http, $timeout) {
    $scope.$on('$viewContentLoaded', function() {
        // initialize core components
        Metronic.initAjax();
        ComponentsPickers.init();
    });
    var order = {};
    $scope.order = order;
    //初始化controller
    initController($scope, order, 'order',  order_service, filterFilter);
    order.searchForm.orderGuize="addtime desc";
	order.searchForm.fn='107';//功能号
    order.search1();
	
	
	order.changeCondition2=function(){
        if(isNull(order.searchForm.searchItemName)){
            $scope.conditionContent = conditionText;
            $scope.searchItemValue_type='text';
        }else{
           if(order.searchForm.searchItemName.toLowerCase().endWith('_select')){
			    $scope.searchItemValue_type='select';
			    $scope.conditionValues =[];
            }else{
               order.changeCondition();
           }
        }
    }
	
	$scope.qu=function(action){
    	if(action=="0"){
    		$state.go('order-list');
    	}else if(action=="1"){
    		$state.go('order-list1');
    	}
    	else if(action=="2"){
    		$state.go('order-list2');
    	}
    }
	
    $scope.detail=function(d){
        $state.go('order-edit',{id: d.id});
    }
    
    order.deleteData = function(d){   	
    	var con = confirm("删除是不可恢复的，你确认要删除吗？");
    	
    	var ds = [];
    	ds.push(d);
    	if(con){
    		var promise = order_service.delete(ds);	
    		promise.then(function(data){
    			
    			if(data.data.status=='success'){
                    order.searchN();
                    alertService.clearAlert();
                    alertService.add(data.data.status,data.data.message);
                }else{
                    alertService.clearAlert();
                    alertService.add(data.data.status,data.data.message);
                }
    			
    		})
    		
    	}
    }
    
        
    // set sidebar closed and body solid layout mode
    $rootScope.settings.layout.pageBodySolid = true;
    $rootScope.settings.layout.pageSidebarClosed = false;
});

MetronicApp.controller('order_controller3', function($rootScope, $scope,$state,$stateParams,order_service,return_visit_service,alertService,filterFilter,$modal, $http, $timeout) {
    $scope.$on('$viewContentLoaded', function() {
        // initialize core components
        Metronic.initAjax();
        ComponentsPickers.init();
    });
    var order = {};
    $scope.order = order;
    //初始化controller
    initController($scope, order, 'order',  order_service, filterFilter);
    order.searchForm.orderGuize="addtime desc";
	order.searchForm.fn='107';//功能号
	order.searchForm.type="2";
    order.search1();
	
	
	order.changeCondition2=function(){
        if(isNull(order.searchForm.searchItemName)){
            $scope.conditionContent = conditionText;
            $scope.searchItemValue_type='text';
        }else{
           if(order.searchForm.searchItemName.toLowerCase().endWith('_select')){
			    $scope.searchItemValue_type='select';
			    $scope.conditionValues =[];
            }else{
               order.changeCondition();
           }
        }
    }
	
	$scope.qu=function(action){
    	if(action=="0"){
    		$state.go('order-list3');
    	}else if(action=="1"){
    		$state.go('order-list4');
    	}
    }
	
    $scope.detail=function(d){
    	openwindow($modal, 'seller-add.html', 'lg', function ($scope, $modalInstance,order_service) {
         	//begin-本业务的全局变量
             //begin-本业务的全局变量
             var order = {};
             order.item = {};
             $scope.order = order;
             //初始化controller
             order.paylist = [];
             
             initController($scope, order, 'order',order_service,filterFilter);
            
             var return_visit = {};
             $scope.return_visit = return_visit;
             //初始化controller
             initController($scope, return_visit, 'return_visit',  return_visit_service, filterFilter);
             return_visit.searchForm.orderGuize="addtime desc";
             return_visit.searchForm.fn='107';//功能号
             return_visit.searchForm.searchItems.order_id_eq = d.id;
             return_visit.search1();
             
             if(!isNull(d.id)){
            	 var promise = order_service.loadById1(d.id).then(function(res){
            		 order.item = res.item;
            		 order.paylist = res.paylist;
            	 })
             }
             
             $scope.cancel = function () {
                 $modalInstance.dismiss('cancel');
             };
         })
    }
    
    order.deleteData = function(d){   	
    	var con = confirm("删除是不可恢复的，你确认要删除吗？");
    	
    	var ds = [];
    	ds.push(d);
    	if(con){
    		var promise = order_service.delete(ds);	
    		promise.then(function(data){
    			
    			if(data.data.status=='success'){
                    order.searchN();
                    alertService.clearAlert();
                    alertService.add(data.data.status,data.data.message);
                }else{
                    alertService.clearAlert();
                    alertService.add(data.data.status,data.data.message);
                }
    			
    		})
    		
    	}
    }
    
        
    // set sidebar closed and body solid layout mode
    $rootScope.settings.layout.pageBodySolid = true;
    $rootScope.settings.layout.pageSidebarClosed = false;
});

MetronicApp.controller('order_controller4', function($rootScope, $scope,$state,$stateParams,order_service,alertService,filterFilter,$modal, $http, $timeout) {
    $scope.$on('$viewContentLoaded', function() {
        // initialize core components
        Metronic.initAjax();
        ComponentsPickers.init();
    });
    var order = {};
    order.item={};
    $scope.order = order;
    order.tongjilist = [];
    order.dataList1 = [];
    //初始化controller
    initController($scope, order, 'order',  order_service, filterFilter);
	
    var promise = order_service.query2();
	promise.then(function(res){
		order.dataList1 = res.list;
	})
    
	$scope.qu=function(action){
    	if(action=="0"){
    		$state.go('order-list3');
    	}else if(action=="1"){
    		$state.go('order-list4');
    	}
    }
	
	$scope.jisuan = function(){
		openwindow($modal, 'jisuan.html', 'lg', function ($scope, $modalInstance) {
         	//begin-本业务的全局变量
             //begin-本业务的全局变量
			var daikuan = {};
			daikuan.item = {};
			$scope.daikuan = daikuan;
			
			$scope.jisuan=function(){
				var jine = (daikuan.item.jine*(daikuan.item.lixi/100/12)*Math.pow(1+daikuan.item.lixi/100/12,daikuan.item.month)*100/100).toFixed(2);
				var lilv = ((Math.pow(1+daikuan.item.lixi/100/12,daikuan.item.month)-1)*100/100).toFixed(2);
				daikuan.item.monthpay = ((jine/lilv)*100/100).toFixed(2)*1+(daikuan.item.jine*3.66/100).toFixed(2)*1;
			}
			
			$scope.reset=function(){
				daikuan.item = {};
			}
			
     $scope.cancel = function () {
                 $modalInstance.dismiss('cancel');
             };
         })
	}
	
	function getTime(){	 
		 var myDate = new Date();
		 var time = myDate.getFullYear()+"-"+(myDate.getMonth()+1)+"-"+myDate.getDate()
		 return time;
	 }
	
	var time1 = getTime();
	init(time1,"1");
	
	var myDate = new Date();
	order.item.type="1";
	order.item.time = myDate.getFullYear()+"-"+(myDate.getMonth()+1);
	
	$scope.xuanze=function(){
		if(order.item.time==""){
			order.item.time = myDate.getFullYear()+"-"+(myDate.getMonth()+1);
		}
		init(order.item.time+"-01",order.item.type);
	}
	
function init(time,type){
	
    var option = {
            title:{
                text:'ECharts 数据统计'
            },
            tooltip:{},
            legend:{
                data:['用户来源']
            },
            xAxis:{
                data:["Android","IOS","PC","Ohter"]
            },
            yAxis:{

            },
            series:[{
                name:'访问量',
                type:'bar',
                data:[500,200,360,100]
            }]
        };
        //初始化echarts实例
        var myChart = echarts.init(document.getElementById('chartmain'));
        var myChart1 = echarts.init(document.getElementById('chartmain1'));

        myChart.showLoading();    //数据加载完之前先显示一段简单的loading动画
        myChart1.showLoading();
        
        var names=[];
        var nums = [];
        var jines=[];
        var order={};
        order.item={};
        if(type=="1"){
        	order.name="日期";
        }else if(type=="2"){
        	order.name="周";
        }else{
        	order.name="月份";
        }
        order.item.time = time;
        order.item.type=type;
        var promise = order_service.tongji(order.item);
        promise.then(function(data){
        	order.tongjilist = data.tongjilist;
        	for(var i = 0;i<order.tongjilist.length;i++){
        		names.push(order.tongjilist[i].riqi);
        		nums.push(order.tongjilist[i].amount);
        		jines.push(order.tongjilist[i].jine);
        	}
        	myChart.hideLoading();
        	myChart1.hideLoading();
        	myChart.setOption({
        		//加载数据图表
        		title: {
        		       text: '放款件数统计',
        		       x:"center",
        		   },
        		   tooltip: {
        			   show:true,
        		       trigger: 'axis',
        		       axisPointer: {            // 坐标轴指示器，坐标轴触发有效
        		           type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
        		       },
        		   },
        		   grid: {
        		       left: '3%',
        		       right: '4%',
        		       bottom: '3%',
        		       containLabel: true
        		   },
        		axisLabel :{  
        		    interval:0   
        		},
        		toolbox: {

        			　　show: true,

        			　　feature: {

        			　　　　saveAsImage: {

        			　　　　show:true,

        			　　　　excludeComponents :['toolbox'],

        			　　　　pixelRatio: 2

        			　　　　}

        			　　}

        			},
                xAxis: {
                	splitLine:{  
                        show:false  
                    },
                    type: 'category',
                    name:order.name,
                    data: names
                },
                yAxis:{
                	type: 'value',
                	name:'件数',
                    boundaryGap: [0,0],
                },
                label:{ 
                    normal:{ 
                    show: true, 
                    position: 'inside'} 
                    },
                series: [{
                    // 根据名字对应到相应的系列
                    name: '件数',
                    type: 'bar',
                    barGap: '5%',
                    itemStyle:{  
                    	normal:{color:'#ab78ba',   //柱状颜色
                            label : {
                                show : true,  //柱头数字
                                position : 'top',
                                textStyle : {
                                    fontSize : '14',
                                    fontFamily : '微软雅黑',
                                    fontWeight : 'bold'
                                }
                            }
                        }  
                   },
                    data: nums,
                },
                /*{
                    // 根据名字对应到相应的系列
                    name: '金额',
                    type: 'bar',
                    itemStyle:{  
                        normal:{  
                          color:'#ED7D31',
                          label:{
                        	  show:true,
                        	  position:'top',
                          }
                          }  
                   },
                    data: jines,
                }*/]
            });
        	myChart1.setOption({
        		//加载数据图表
        		title: {
        		       text: '放款金额统计',
        		       x:"center",
        		   },
        		   tooltip: {
        			   show:true,
        		       trigger: 'axis',
        		       axisPointer: {            // 坐标轴指示器，坐标轴触发有效
        		           type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
        		       },
        		   },
        		   grid: {
        		       left: '3%',
        		       right: '4%',
        		       bottom: '3%',
        		       containLabel: true
        		   },
        		axisLabel :{  
        		    interval:0   
        		},
                xAxis: {
                	splitLine:{  
                        show:false  
                    },
                    type: 'category',
                    name:order.name,
                    data: names
                },
                yAxis:{
                	type: 'value',
                	name:'金额(元)',
                    boundaryGap: [0,0],
                },
                label:{ 
                    normal:{ 
                    show: true, 
                    position: 'inside'} 
                    },
                series: [
                {
                    // 根据名字对应到相应的系列
                    name: '金额',
                    type: 'bar',
                    itemStyle:{  
                        normal:{  
                          color:'#ED7D31',
                          label:{
                        	  show:true,
                        	  position:'top',
                          }
                          }  
                   },
                    data: jines,
                }]
            });
        })
}
	
    $scope.detail=function(d){
    	openwindow($modal, 'seller-add.html', 'lg', function ($scope, $modalInstance,order_service) {
         	//begin-本业务的全局变量
             //begin-本业务的全局变量
             var order = {};
             order.item = {};
             $scope.order = order;
             //初始化controller
             order.paylist = [];
             
             initController($scope, order, 'order',order_service,filterFilter);
            
             if(!isNull(d.id)){
            	 var promise = order_service.loadById1(d.id).then(function(res){
            		 order.item = res.item;
            		 order.paylist = res.paylist;
            	 })
             }
             
             $scope.cancel = function () {
                 $modalInstance.dismiss('cancel');
             };
         })
    }
    
    order.deleteData = function(d){   	
    	var con = confirm("删除是不可恢复的，你确认要删除吗？");
    	
    	var ds = [];
    	ds.push(d);
    	if(con){
    		var promise = order_service.delete(ds);	
    		promise.then(function(data){
    			
    			if(data.data.status=='success'){
                    order.searchN();
                    alertService.clearAlert();
                    alertService.add(data.data.status,data.data.message);
                }else{
                    alertService.clearAlert();
                    alertService.add(data.data.status,data.data.message);
                }
    			
    		})
    		
    	}
    }
    
        
    // set sidebar closed and body solid layout mode
    $rootScope.settings.layout.pageBodySolid = true;
    $rootScope.settings.layout.pageSidebarClosed = false;
});

MetronicApp.controller('order_controller5', function($rootScope, $scope,$state,$stateParams,order_service,alertService,filterFilter,$modal, $http, $timeout) {
    $scope.$on('$viewContentLoaded', function() {
        // initialize core components
        Metronic.initAjax();
        ComponentsPickers.init();
    });
    var order = {};
    $scope.order = order;
    //初始化controller
    initController($scope, order, 'order',  order_service, filterFilter);
    order.searchForm.orderGuize="addtime desc";
	order.searchForm.fn='107';//功能号
	order.searchForm.type="3";
    order.search1();
	
    $scope.qu=function(action){
    	if(action=="0"){
    		$state.go('order-list5');
    	}else if(action=="1"){
    		$state.go('payinfo-list');
    	}
    }
    
	order.changeCondition2=function(){
        if(isNull(order.searchForm.searchItemName)){
            $scope.conditionContent = conditionText;
            $scope.searchItemValue_type='text';
        }else{
           if(order.searchForm.searchItemName.toLowerCase().endWith('_select')){
			    $scope.searchItemValue_type='select';
			    $scope.conditionValues =[];
            }else{
               order.changeCondition();
           }
        }
    }
	
    $scope.detail=function(d){
        $state.go('order-edit1',{id: d.id});
    }
    
    order.deleteData = function(d){   	
    	var con = confirm("删除是不可恢复的，你确认要删除吗？");
    	
    	var ds = [];
    	ds.push(d);
    	if(con){
    		var promise = order_service.delete(ds);	
    		promise.then(function(data){
    			
    			if(data.data.status=='success'){
                    order.searchN();
                    alertService.clearAlert();
                    alertService.add(data.data.status,data.data.message);
                }else{
                    alertService.clearAlert();
                    alertService.add(data.data.status,data.data.message);
                }
    			
    		})
    		
    	}
    }
    
        
    // set sidebar closed and body solid layout mode
    $rootScope.settings.layout.pageBodySolid = true;
    $rootScope.settings.layout.pageSidebarClosed = false;
});

MetronicApp.controller('order_controller6', function($rootScope, $scope,$state,$stateParams,order_service,user_service,alertService,filterFilter,$modal, $http, $timeout) {
    $scope.$on('$viewContentLoaded', function() {
        // initialize core components
        Metronic.initAjax();
        ComponentsPickers.init();
    });
    var order = {};
    order.item={};
	order.userlist = [];
    $scope.order = order;
    //初始化controller
    initController($scope, order, 'order',  order_service, filterFilter);
    order.searchForm.orderGuize="addtime desc";
	order.searchForm.fn='107';//功能号
	order.searchForm.type='1';
	order.searchForm.searchItems.shenpiStatus_eq="0";
    order.search1();
	
    $scope.qu=function(action){
    	if(action=="0"){
    		$state.go('order-list');
    	}else if(action=="1"){
    		$state.go('order-list1');
    	}
    	else if(action=="2"){
    		$state.go('order-list2');
    	}
    }
    
	order.changeCondition2=function(){
        if(isNull(order.searchForm.searchItemName)){
            $scope.conditionContent = conditionText;
            $scope.searchItemValue_type='text';
        }else{
           if(order.searchForm.searchItemName.toLowerCase().endWith('_select')){
			    $scope.searchItemValue_type='select';
			    $scope.conditionValues =[];
            }else{
               order.changeCondition();
           }
        }
    }
	
	$scope.zhuanyi = function(){
		var selecteds = order.getSelectItems();
		if(selecteds==undefined||selecteds.length==0){
			alertService.add('warning','请先选择需要转移的订单！');
		}else{
        openwindow($modal, 'user-list.html', 'lg', function ($scope, $modalInstance,user_service) {
        	//begin-本业务的全局变量
            //begin-本业务的全局变量
            var user = {};
            $scope.user = user;
            //初始化controller
            
            initController($scope, user, 'user',user_service,filterFilter);
            
            user.searchForm.orderGuize="createTime desc";
            user.searchForm.fn="301";
            user.searchForm.searchItems.category_id_eq="2";
            user.search1();

                $scope.submit = function () {
                    var selected = user.getSelectItems();
                    if(selected==undefined||selected.length==0){
                       alertService.add('warning','请选择转移人员！');
                    }
                    order.item.user_id = selected[0].id;
                    order.item.user_name = selected[0].value_name;
                    order.item.list= selecteds;
                    var promise = order_service.zhuanyi(order);
                    promise.then(function (data) {  // 调用承诺API获取数据 .resolve
          		      if(data.status=='success'){
          		    	//选择后需要做的事情
          		    	toastr[data.status]("转移成功", "")
                          $modalInstance.dismiss('cancel');
                      }else{
                          toastr[data.status](data.message, "")
                      }
                  }, function (data) {  // 处理错误 .reject
                      toastr['error']("您的信息保存出错！", "SORRY！")
                  });
                    
                };
            
            $scope.cancel = function () {
                $modalInstance.dismiss('cancel');
            };
       
        })
	}
}

    $scope.detail=function(d){
        var submitform={};
        order.item.id = d.id;
        order.item.shenpiStatus="1";
        order.item.action="1";
        submitform.item = order.item;
        var promise = order_service.createOrUpdate(submitform);
        promise.then(function (data) {  // 调用承诺API获取数据 .resolve
		      if(data.data.status=='success'){
		    	  $state.go('order-edit',{id: d.id});
            }else{
                toastr[data.data.status](data.data.message, "")
            }
        }, function (data) {  // 处理错误 .reject
            toastr['error']("您的信息保存出错！", "SORRY！")
        });
        
    }
    
    order.deleteData = function(d){   	
    	var con = confirm("删除是不可恢复的，你确认要删除吗？");
    	
    	var ds = [];
    	ds.push(d);
    	if(con){
    		var promise = order_service.delete(ds);	
    		promise.then(function(data){
    			
    			if(data.data.status=='success'){
                    order.searchN();
                    alertService.clearAlert();
                    alertService.add(data.data.status,data.data.message);
                }else{
                    alertService.clearAlert();
                    alertService.add(data.data.status,data.data.message);
                }
    			
    		})
    		
    	}
    }
    
        
    // set sidebar closed and body solid layout mode
    $rootScope.settings.layout.pageBodySolid = true;
    $rootScope.settings.layout.pageSidebarClosed = false;
});


MetronicApp.controller('order-add_controller', function($rootScope, $scope,$state,$stateParams,user_service,order_service,return_visit_service,alertService,filterFilter,$modal, $http, $timeout) {
    $scope.$on('$viewContentLoaded', function() {
        // initialize core components
        Metronic.initAjax();
        ComponentsPickers.init();
       
    });
    
    var order = {};
    order.item={};
    $scope.order=order;
    
    order.save = function(){
   		 var submitform={};
   	     submitform.item = order.item;
   		 var promise = order_service.add(submitform);
   		 promise.then(function (data) {  // 调用承诺API获取数据 .resolve
   			      if(data.data.status=='success'){
   			    	  order.out=data.data.item;
   	                toastr['success']("您的信息已保存成功！", "")
   	            }else{
   	                toastr[data.data.status](data.data.message, "")
   	            }
   	        }, function (data) {  // 处理错误 .reject
   				Metronic.unblockUI();
   	            toastr['error']("您的信息保存出错！", "SORRY！")
   	        });
    }
    
});

MetronicApp.controller('order-edit_controller', function($rootScope, $scope,$state,$stateParams,user_service,order_service,return_visit_service,alertService,filterFilter,$modal, $http, $timeout) {
    $scope.$on('$viewContentLoaded', function() {
        // initialize core components
        Metronic.initAjax();
        ComponentsPickers.init();
       
    });
    var order = {};
    order.out={};
    $scope.order = order;
    var id= $stateParams.id;
    $rootScope.orderId = $stateParams.id;
	order.item={};
    if(!isNull(id)){
       order_service.loadById(id).then(function (res) {
            //初始化变量
           order.item = res;
           order.item.ismodified1 = true;
           order.item.ismodified2 = true;
           order.item.ismodified3 = true;
           $rootScope.order = res;
           if(!isNull(order.item.id)){
    	       order_service.loadById_out(order.item.id).then(function (res) {
    	            //初始化变
    	           if(res!="null"){
    	        	   order.out=res;
    	        	   if(order.out.business_status=="1"){
    	        		   $("#business_status_1").attr("checked",true);
    	        	   }else if(order.out.business_status=="3"){
    	        		   $("#business_status_3").attr("checked",true);
    	        	   }else if(order.out.business_status=="2"){
    	        		   $("#business_status_2").attr("checked",true);
    	        		   $("#collapseSix").show();
    	        		   $("#collapseSix").addClass("in");
    	        	   }
    	        	   if(order.out.network_status=="1"){
    	        		   $("#network_status_1").attr("checked",true);
    	        	   }else if(order.out.network_status=="3"){
    	        		   $("#network_status_3").attr("checked",true);
    	        	   }else if(order.out.network_status=="2"){
    	        		   $("#network_status_2").attr("checked",true);
    	        		  
    	        	   }
    	        	   if(order.out.manlaw_status=="1"){
    	        		   $("#manlaw_status_1").attr("checked",true);
    	        	   }else if(order.out.manlaw_status=="3"){
    	        		   $("#manlaw_status_3").attr("checked",true);
    	        	   }else if(order.out.manlaw_status=="2"){
    	        		   $("#manlaw_status_2").attr("checked",true);
    	        		  
    	        	   }
    	        	   if(order.out.discredit_status=="1"){
    	        		   $("#discredit_status_1").attr("checked",true);
    	        	   }else if(order.out.discredit_status=="3"){
    	        		   $("#discredit_status_3").attr("checked",true);
    	        	   }else if(order.out.discredit_status=="2"){
    	        		   $("#discredit_status_2").attr("checked",true);
    	        		   
    	        	   }
    	           }
    	        });
    	    }
        });
    }
    
    var return_visit = {};
    $scope.return_visit = return_visit;
    //初始化controller
    initController($scope, return_visit, 'return_visit',  return_visit_service, filterFilter);
    return_visit.searchForm.orderGuize="addtime desc";
    return_visit.searchForm.fn='107';//功能号
    return_visit.searchForm.searchItems.order_id_eq = id;
    return_visit.search1();
    
    $scope.save_return_visit = function() {
 	   Metronic.blockUI();
         var submitform={};
         return_visit.item.order_id = order.item.id;
         submitform.item = return_visit.item;
         var promise = return_visit_service.createOrUpdate(submitform);
         promise.then(function (data) {  // 调用承诺API获取数据 .resolve
 		   Metronic.unblockUI();
 		      if(data.data.status=='success'){
                 toastr['success']("您的信息已保存成功！", "")
                 return_visit.search1();
             }else{
                 toastr[data.data.status](data.data.message, "")
             }
         }, function (data) {  // 处理错误 .reject
 			Metronic.unblockUI();
             toastr['error']("您的信息保存出错！", "SORRY！")
         });
     }
    
    $scope.bianji = function(action){
    	if(action=="2"){
    		order.item.ismodified2 = false;
    	}else if(action=="1"){
    		order.item.ismodified1 = false;
    	}else if(action=="3"){
    		order.item.ismodified3 = false;
    	}
    }
    
    $scope.$watch("order",function(newValue,oldValue,$scope) {
        order = newValue;
        $("#unit1").html(order.item.unit);
        $("#first_realname").html(order.item.first_realname);
        $("#first_cellphone").html(order.item.first_cellphone);
        $("#first_relation").html(order.item.first_relation);
        $("#second_realname").html(order.item.second_realname);
        $("#second_cellphone").html(order.item.second_cellphone);
        $("#second_relation").html(order.item.second_relation);
        $("#third_realname").html(order.item.third_realname);
        $("#third_cellphone").html(order.item.third_cellphone);
        $("#third_relation").html(order.item.third_relation);
        $("#fourth_realname").html(order.item.fourth_realname);
        $("#fourth_cellphone").html(order.item.fourth_cellphone);
        $("#fourth_relation").html(order.item.fourth_relation);
        $("#unitphone").html(order.item.unitphone);
        $scope.order = order;
        $scope.$apply;
       }, true)
    
	 $scope.edit=function(d){
        $state.go('order-edit',{id: d.id});
    }

    $scope.tijiao = function(){
         var submitform={};
         submitform.item = order.item;
         var promise = order_service.createOrUpdate(submitform);
         promise.then(function (data) {  // 调用承诺API获取数据 .resolve
 		      if(data.data.status=='success'){
                 toastr['success']("您的信息已保存成功！", "")
                  order.item={}
                     $state.go('order-list5');
             }else{
                 toastr[data.data.status](data.data.message, "")
             }
         }, function (data) {  // 处理错误 .reject
             toastr['error']("您的信息保存出错！", "SORRY！")
         });
    }
    
    $scope.zhedie1=function(){
    	$("#collapseOne1").toggle();
		 $("#collapseOne1").toggleClass("in");
    }
    
	 $scope.zhedie=function(action){
		 if(action=="1"){
			 $("#collapseOne").toggle();
			 $("#collapseOne").toggleClass("in");
			 if($("#collapseOne").hasClass("in")){
				 if(order.out.business_status=="2"){
					 $("#collapseSix").show();
					 $("#collapseSix").addClass("in");
				 }
			 }else{
				 $("#collapseSix").hide();
				 $("#collapseSix").removeClass("in");
			 }
		 }else if(action=="2"){
			 $("#collapseTwo").toggle();
			 $("#collapseTwo").toggleClass("in");
			 if($("#collapseTwo").hasClass("in")){
				 if(order.out.network_status=="2"){
					 $("#collapseSeven").show();
					 $("#collapseSeven").addClass("in");
				 }
			 }else{
				 $("#collapseSeven").hide();
				 $("#collapseSeven").removeClass("in");
			 }
		 }else if(action=="3"){
			 $("#collapseThree").toggle();
			 $("#collapseThree").toggleClass("in");
			 if($("#collapseThree").hasClass("in")){
				 if(order.out.manlaw_status=="2"){
					 $("#collapseEight").show();
					 $("#collapseEight").addClass("in");
				 }
			 }else{
				 $("#collapseEight").hide();
				 $("#collapseEight").removeClass("in");
			 }
		 }else if(action=="4"){
			 $("#collapseFour").toggle();
			 $("#collapseFour").toggleClass("in");
			 if($("#collapseFour").hasClass("in")){
				 if(order.out.discredit_status=="2"){
					 $("#collapseNine").show();
					 $("#collapseNine").addClass("in");
				 }
			 }else{
				 $("#collapseNine").hide();
				 $("#collapseNine").removeClass("in");
			 }
		 }else{
			 $("#collapseFive").toggle();
			 $("#collapseFive").toggleClass("in");
		 }
		 
	 }

	 $scope.yichang=function(action){
		 if(order.item.shenpiStatus=="0"||order.item.shenpiStatus=="1"){	 
		 if(action=="1"){
			 $("#collapseSix").show();
			 $("#collapseSix").addClass("in");
			 order.out.business_status="2";
			 order.out.action="1";
		 }else if(action=="2"){
			 $("#collapseSeven").show();
			 $("#collapseSeven").addClass("in");
			 order.out.network_status="2";
			 order.out.action="3";
		 }else if(action=="3"){
			 $("#collapseEight").show();
			 $("#collapseEight").addClass("in");
			 order.out.manlaw_status="2";
			 order.out.action="5";
		 }else{
			 $("#collapseNine").show();
			 $("#collapseNine").addClass("in");
			 order.out.discredit_status="2";
			 order.out.action="7";
		 }
		 order.out.order_id = order.item.id;
		 var submitform={};
	     submitform.item = order.out;
		 var promise = order_service.createOrUpdate_out(submitform);
		 promise.then(function (data) {  // 调用承诺API获取数据 .resolve
			      if(data.data.status=='success'){
			    	  order.out=data.data.item;
	                toastr['success']("您的信息已保存成功！", "")
	            }else{
	                toastr[data.data.status](data.data.message, "")
	            }
	        }, function (data) {  // 处理错误 .reject
				Metronic.unblockUI();
	            toastr['error']("您的信息保存出错！", "SORRY！")
	        });
		 }
	 }
	 
	 $scope.save1 = function(action){
		 if(order.item.shenpiStatus=="0"||order.item.shenpiStatus=="1"){ 
		 order.out.order_id = order.item.id;
		 var submitform={};
	     submitform.item = order.out;
		 var promise = order_service.createOrUpdate_out(submitform);
		 promise.then(function (data) {  // 调用承诺API获取数据 .resolve
			      if(data.data.status=='success'){
			    	  order.out=data.data.item;
	                toastr['success']("您的信息已保存成功！", "")
	            }else{
	                toastr[data.data.status](data.data.message, "")
	            }
	        }, function (data) {  // 处理错误 .reject
				Metronic.unblockUI();
	            toastr['error']("您的信息保存出错！", "SORRY！")
	        });
		 }
	 }
	 
	 $scope.zhengchang=function(action,status){
		 if(order.item.shenpiStatus=="0"||order.item.shenpiStatus=="1"){
		 if(action=="1"){
			 $("#collapseSix").hide();
			 $("#collapseSix").removeClass("in");
			 order.out.business_status=status;
		 }else if(action=="2"){
			 $("#collapseSeven").hide();
			 $("#collapseSeven").removeClass("in");
			 order.out.network_status=status;
		 }else if(action=="3"){
			 $("#collapseEight").hide();
			 $("#collapseEight").removeClass("in");
			 order.out.manlaw_status = status;
		 }else{
			 $("#collapseNine").hide();
			 $("#collapsNine").removeClass("in");
			 order.out.discredit_status=status;
		 }
		 order.out.order_id = order.item.id;
		 var submitform={};
	     submitform.item = order.out;
		 var promise = order_service.createOrUpdate_out(submitform);
		 promise.then(function (data) {  // 调用承诺API获取数据 .resolve
			      if(data.data.status=='success'){
			    	  order.out=data.data.item;
	                toastr['success']("您的信息已保存成功！", "")
	            }else{
	                toastr[data.data.status](data.data.message, "")
	            }
	        }, function (data) {  // 处理错误 .reject
				Metronic.unblockUI();
	            toastr['error']("您的信息保存出错！", "SORRY！")
	        });
		 }
	 }
	 
    // 增加修改保存
    // 增加修改保存
    order.save = function(action) {
	   Metronic.blockUI();
        var submitform={}
        order.ziliao = {};
        order.ziliao.type = action;
        order.ziliao.id = order.item.id;
        submitform.item = order.ziliao;
        var promise = order_service.createOrUpdate(submitform);
        promise.then(function (data) {  // 调用承诺API获取数据 .resolve
		   Metronic.unblockUI();
		      if(data.data.status=='success'){
                toastr['success']("您的信息已保存成功！", "")
                 order.item={}
                    $state.go('order-list');
            }else{
                toastr[data.data.status](data.data.message, "")
            }
        }, function (data) {  // 处理错误 .reject
			Metronic.unblockUI();
            toastr['error']("您的信息保存出错！", "SORRY！")
        });
    }
    
    //编辑保存
    $scope.savebianji = function(action) {
 	   Metronic.blockUI();
         var submitform={}
         submitform.item = order.item;
         var promise = order_service.createOrUpdate(submitform);
         promise.then(function (data) {  // 调用承诺API获取数据 .resolve
 		   Metronic.unblockUI();
 		      if(data.data.status=='success'){
                 toastr['success']("您的信息已保存成功！", "")
                  if(action=="2"){
                	  order.item.ismodified2 = true;
                  }else if(action=="1"){
                	  order.item.ismodified1 = true;
                  }else if(action=="3"){
                	  order.item.ismodified3 = true;
                  }
             }else{
                 toastr[data.data.status](data.data.message, "")
             }
         }, function (data) {  // 处理错误 .reject
 			Metronic.unblockUI();
             toastr['error']("您的信息保存出错！", "SORRY！")
         });
     }
    
    // set sidebar closed and body solid layout mode
    $rootScope.settings.layout.pageBodySolid = true;
    $rootScope.settings.layout.pageSidebarClosed = false;
})

MetronicApp.controller('order-edit_controller1', function($rootScope, $scope,$state,$stateParams,user_service,order_service,alertService,filterFilter,$modal, $http, $timeout) {
    $scope.$on('$viewContentLoaded', function() {
        // initialize core components
        Metronic.initAjax();
        ComponentsPickers.init();
       
    });
    var order = {};
    order.out={};
    order.item={};
if($rootScope.order!=undefined){
	order.item = $rootScope.order;
	$scope.order = order;
	if(!isNull(order.item.id)){
	       order_service.loadById_out(order.item.id).then(function (res) {
	            //初始化变量
	           order.out = res
	        });
	    }
}
	
	 $scope.edit=function(d){
        $state.go('order-edit',{id: d.id});
    }

	 $scope.zhedie=function(action){
		 if(action=="1"){
			 $("#collapseOne").toggle();
			 $("#collapseOne").toggleClass("in");
			 $("#collapseSix").hide();
			 $("#collapseSix").removeClass("in");
		 }else if(action=="2"){
			 $("#collapseTwo").toggle();
			 $("#collapseTwo").toggleClass("in");
			 $("#collapseSeven").hide();
			 $("#collapseSeven").removeClass("in");
		 }else if(action=="3"){
			 $("#collapseThree").toggle();
			 $("#collapseThree").toggleClass("in");
			 $("#collapseEight").hide();
			 $("#collapseEight").removeClass("in");
		 }else if(action=="4"){
			 $("#collapseFour").toggle();
			 $("#collapseFour").toggleClass("in");
			 $("#collapseNine").hide();
			 $("#collapseNine").removeClass("in");
		 }else{
			 $("#collapseFive").toggle();
			 $("#collapseFive").toggleClass("in");
		 }
		 
	 }

	 $scope.yichang=function(action){
		 if(action=="1"){
			 $("#collapseSix").show();
			 $("#collapseSix").addClass("in");
		 }else if(action=="2"){
			 $("#collapseSeven").show();
			 $("#collapseSeven").addClass("in");
		 }else if(action=="3"){
			 $("#collapseEight").show();
			 $("#collapseEight").addClass("in");
		 }else{
			 $("#collapseNine").show();
			 $("#collapseNine").addClass("in");
		 }
	 }
	 
	 $scope.zhengchang=function(action,status){
		 if(action=="1"){
			 $("#collapseSix").hide();
			 $("#collapseSix").removeClass("in");
			 order.out.business_status=status;
		 }else if(action=="2"){
			 $("#collapseSeven").hide();
			 $("#collapseSeven").removeClass("in");
		 }else if(action=="3"){
			 $("#collapseEight").hide();
			 $("#collapseEight").removeClass("in");
		 }else{
			 $("#collapseNine").hide();
			 $("#collapsNine").removeClass("in");
		 }
		 order.out.order_id = order.item.id;
		 var submitform={};
	     submitform.item = order.out;
		 var promise = order_service.createOrUpdate_out(submitform);
		 promise.then(function (data) {  // 调用承诺API获取数据 .resolve
			      if(data.data.status=='success'){
			    	  order.out=data.data.item;
	                toastr['success']("您的信息已保存成功！", "")
	            }else{
	                toastr[data.data.status](data.data.message, "")
	            }
	        }, function (data) {  // 处理错误 .reject
				Metronic.unblockUI();
	            toastr['error']("您的信息保存出错！", "SORRY！")
	        });
	 }
	 
    // 增加修改保存
    // 增加修改保存
    order.save = function(action) {
	   Metronic.blockUI();
        var submitform={};
        submitform.item = order.item;
        var promise = order_service.createOrUpdate(submitform);
        promise.then(function (data) {  // 调用承诺API获取数据 .resolve
		   Metronic.unblockUI();
		      if(data.data.status=='success'){
                toastr['success']("您的信息已保存成功！", "")
                 order.item={}
                if(action==1){
                    $state.go('order-list');
                }else if(action==3){
                	window.history.back();
                }
            }else{
                toastr[data.data.status](data.data.message, "")
            }
        }, function (data) {  // 处理错误 .reject
			Metronic.unblockUI();
            toastr['error']("您的信息保存出错！", "SORRY！")
        });
    }
    
    
    // set sidebar closed and body solid layout mode
    $rootScope.settings.layout.pageBodySolid = true;
    $rootScope.settings.layout.pageSidebarClosed = false;
})

MetronicApp.controller('order-edit_controller3', function($rootScope, $scope,$state,$stateParams,user_service,order_service,alertService,filterFilter,$modal, $http, $timeout) {
    $scope.$on('$viewContentLoaded', function() {
        // initialize core components
        Metronic.initAjax();
        ComponentsPickers.init();
       
    });
    var order = {};
    order.tellist = [];
    order.tellist1=[];
    order.reasonlist = [];
    order.reasonlist1 = [];
    order.reasonlist2 = [];
    order.reasonlist3 = [];
    order.reasonlist4 = [];
    order.reasonlist5 = [];
    order.reasonlist6 = [];
    order.reasonlist7 = [];
    order.reasonlist8 = [];
    order.reasonlist9 = [];
    order.reasonlist10 = [];
    order.reasonlist11 = [];
    order.reasonlist12 = [];
    order.reasonlist13 = [];
    order.tel = {};
    order.xin = {};
    $scope.order = order;
    var id = $rootScope.orderId;
    
	 function init(){
		 var reason1 = {};
		 reason1.value="D101伪冒申请";
		 reason1.name="D101伪冒申请";
		 var reason2 = {};
		 reason2.value="D102代办包装";
		 reason2.name="D102代办包装";
		 var reason3 = {};
		 reason3.value="D103团伙骗贷";
		 reason3.name="D103团伙骗贷";
		 var reason4 = {};
		 reason4.value="D104涉及欺诈型销售违规";
		 reason4.name="D104涉及欺诈型销售违规";
		 var reason5 = {};
		 reason5.value="D105黑名单";
		 reason5.name="D105黑名单";
		 var reason6 = {};
		 reason6.value="D106其他";
		 reason6.name="D106其他";
		 var reason7 = {};
		 reason7.value="D107人脸相似度小于75分";
		 reason7.name="D107人脸相似度小于75分";
		 order.reasonlist1.push(reason1);
		 order.reasonlist1.push(reason2);
		 order.reasonlist1.push(reason3);
		 order.reasonlist1.push(reason4);
		 order.reasonlist1.push(reason5);
		 order.reasonlist1.push(reason6);
		 order.reasonlist1.push(reason7);
		 var reason8 = {};
		 reason8.value="D201虚假身份证";
		 reason8.name="D201虚假身份证";
		 order.reasonlist2.push(reason8);
		 var reason9 = {};
		 reason9.value="D301我司借款还款不良";
		 reason9.name="D301我司借款还款不良";
		 order.reasonlist3.push(reason9);
		 var reason10 = {};
		 reason10.value="D401单位信息不真实";
		 reason10.name="D401单位信息不真实";
		 var reason11 = {};
		 reason11.value="D402电核了解到提供的信息前后矛盾/不符合逻辑";
		 reason11.name="D402电核了解到提供的信息前后矛盾/不符合逻辑";
		 var reason12 = {};
		 reason12.value="D403联系人信息不真实";
		 reason12.name="D403联系人信息不真实";
		 var reason13 = {};
		 reason13.value="D404其他";
		 reason13.name="D404其他";
		 order.reasonlist4.push(reason10);
		 order.reasonlist4.push(reason11);
		 order.reasonlist4.push(reason12);
		 order.reasonlist4.push(reason13);
		 var reason14 = {};
		 reason14.value="D501内匹到客户有关联的负面信息";
		 reason14.name="D501内匹到客户有关联的负面信息";
		 var reason15 = {};
		 reason15.value="D502电核了解单位有负面信息";
		 reason15.name="D502电核了解单位有负面信息";
		 var reason16 = {};
		 reason16.value="D503电核了解客户有负面信息";
		 reason16.name="D503电核了解客户有负面信息";
		 var reason17 = {};
		 reason17.value="D504电核了解联系人有负面信息";
		 reason17.name="D504电核了解联系人有负面信息";
		 var reason18 = {};
		 reason18.value="D505资料显示客户/单位有负面信息";
		 reason18.name="D505资料显示客户/单位有负面信息";
		 var reason19 = {};
		 reason19.value="D506人法网/失信网查询异常";
		 reason19.name="D506人法网/失信网查询异常";
		 var reason20 = {};
		 reason20.value="D507其他";
		 reason20.name="D507其他";
		 order.reasonlist5.push(reason14);
		 order.reasonlist5.push(reason15);
		 order.reasonlist5.push(reason16);
		 order.reasonlist5.push(reason17);
		 order.reasonlist5.push(reason18);
		 order.reasonlist5.push(reason19);
		 order.reasonlist5.push(reason20);
		 var reason21 = {};
		 reason21.value="D601工作信息无法核实";
		 reason21.name="D601工作信息无法核实";
		 var reason22 = {};
		 reason22.value="D602身份信息无法核实";
		 reason22.name="D602身份信息无法核实";
		 var reason23 = {};
		 reason23.value="D603其他";
		 reason23.name="D603其他";
		 var reason24 = {};
		 order.reasonlist6.push(reason21);
		 order.reasonlist6.push(reason22);
		 order.reasonlist6.push(reason23);
		 reason24.value="D701无法联络申请人";
		 reason24.name="D701无法联络申请人";
		 var reason25 = {};
		 reason25.value="D702无法联络申请人公司";
		 reason25.name="D702无法联络申请人公司";
		 var reason26 = {};
		 reason26.value="D703无法联络上联系人";
		 reason26.name="D703无法联络上联系人";
		 var reason27 = {};
		 reason27.value="D704其他";
		 reason27.name="D704其他";
		 order.reasonlist7.push(reason24);
		 order.reasonlist7.push(reason25);
		 order.reasonlist7.push(reason26);
		 order.reasonlist7.push(reason27);
		 var reason28 = {};
		 reason28.value="D801不愿配合或无法提供联系人/单位工作信息";
		 reason28.name="D801不愿配合或无法提供联系人/单位工作信息";
		 var reason29 = {};
		 reason29.value="D802补件无效";
		 reason29.name="D802补件无效";
		 var reason30 = {};
		 reason30.value="D803其他";
		 reason30.name="D803其他";
		 order.reasonlist8.push(reason28);
		 order.reasonlist8.push(reason29);
		 order.reasonlist8.push(reason30);
		 var reason31 = {};
		 reason31.value="D901外部负债核实过高";
		 reason31.name="D901外部负债核实过高";
		 order.reasonlist9.push(reason31);
		 var reason32 = {};
		 reason32.value="D1001工作单位工商网未注册";
		 reason32.name="D1001工作单位工商网未注册";
		 var reason33 = {};
		 reason33.value="D1002单位吊销/注销/迁出/停业";
		 reason33.name="D1002单位吊销/注销/迁出/停业";
		 var reason34 = {};
		 reason34.value="D1003其他";
		 reason34.name="D1003其他";
		 order.reasonlist10.push(reason32);
		 order.reasonlist10.push(reason33);
		 order.reasonlist10.push(reason34);
		 var reason35 = {};
		 reason35.value="D1101工龄不符合最低要求";
		 reason35.name="D1101工龄不符合最低要求";
		 var reason36 = {};
		 reason36.value="D1102年龄不符合要求";
		 reason36.name="D1102年龄不符合要求";
		 var reason37 = {};
		 reason37.value="D1103非准入地区";
		 reason37.name="D1103非准入地区";
		 var reason38 = {};
		 reason38.value="D1104禁止客群";
		 reason38.name="D1104禁止客群";
		 var reason39 = {};
		 reason39.value="D1105商户高风险";
		 reason39.name="D1105商户高风险";
		 var reason40 = {};
		 reason40.value="D1106手机详单异常";
		 reason40.name="D1106手机详单异常";
		 var reason41 = {};
		 reason41.value="D1107核实手机号码归属人非本人/直系亲属";
		 reason41.name="D1107核实手机号码归属人非本人/直系亲属";
		 var reason42 = {};
		 reason42.value="D1108手机号码连续在网时长小于6个月";
		 reason42.name="D1108手机号码连续在网时长小于6个月";
		 var reason43 = {};
		 reason43.value="D1109近三个月内手机月消费最小值小于25元";
		 reason43.name="D1109近三个月内手机月消费最小值小于25元";
		 var reason44 = {};
		 reason44.value="D1110手机号非实名认证";
		 reason44.name="D1110手机号非实名认证";
		 var reason45 = {};
		 reason45.value="D1111在本地连续工作小于6个月";
		 reason45.name="D1111在本地连续工作小于6个月";
		 var reason46 = {};
		 order.reasonlist11.push(reason35);
		 order.reasonlist11.push(reason36);
		 order.reasonlist11.push(reason37);
		 order.reasonlist11.push(reason38);
		 order.reasonlist11.push(reason39);
		 order.reasonlist11.push(reason40);
		 order.reasonlist11.push(reason41);
		 order.reasonlist11.push(reason42);
		 order.reasonlist11.push(reason43);
		 order.reasonlist11.push(reason44);
		 order.reasonlist11.push(reason45);
		 reason46.value="D302个人不良";
		 reason46.name="D302个人不良";
		 var reason47 = {};
		 reason47.value="D303法院失信";
		 reason47.name="D303法院失信";
		 var reason48 = {};
		 reason48.value="D304法院被执行中";
		 reason48.name="D304法院被执行中";
		 var reason49 = {};
		 reason49.value="D305芝麻信用分小于500";
		 reason49.name="D305芝麻信用分小于500";
		 var reason50 = {};
		 reason50.value="D306内部逾期大于30天";
		 reason50.name="D306内部逾期大于30天";
		 var reason51 = {};
		 reason51.value="D307历史逾期";
		 reason51.name="D307历史逾期";
		 var reason52 = {};
		 reason52.value="D308当前逾期";
		 reason52.name="D308当前逾期";
		 var reason53 = {};
		 reason53.value="D309申请次数超限";
		 reason53.name="D309申请次数超限";
		 var reason54 = {};
		 reason54.value="D310同盾建议拒绝";
		 reason54.name="D310同盾建议拒绝";
		 var reason55 = {};
		 reason55.value="D311外部信用不良";
		 reason55.name="D311外部信用不良";
		 order.reasonlist3.push(reason46);
		 order.reasonlist3.push(reason47);
		 order.reasonlist3.push(reason48);
		 order.reasonlist3.push(reason49);
		 order.reasonlist3.push(reason50);
		 order.reasonlist3.push(reason51);
		 order.reasonlist3.push(reason52);
		 order.reasonlist3.push(reason53);
		 order.reasonlist3.push(reason54);
		 order.reasonlist3.push(reason55);
		 var reason56 = {};
		 reason56.value="D1201综合评分偿债不足";
		 reason56.name="D1201综合评分偿债不足";
		 var reason57 = {};
		 reason57.value="D1202其他";
		 reason57.name="D1202其他";
		 var reason58 = {};
		 reason58.value="D1203直系亲属重复进件";
		 reason58.name="D1203直系亲属重复进件";
		 var reason59 = {};
		 reason59.value="D1204关联重复进件";
		 reason59.name="D1204关联重复进件";
		 order.reasonlist13.push(reason56);
		 order.reasonlist13.push(reason57);
		 order.reasonlist13.push(reason58);
		 order.reasonlist13.push(reason59);
	 }

	 init();
    
    if(!isNull(id)){
        order_service.loadById(id).then(function (res) {
             //初始化变量
            order.item = res
            $rootScope.order = res;
            if(order.item.jielun=="0"){
            	$("#reason1").show();
            	$("#jielun1").attr("checked","true");
            }else if(order.item.jielun=="1"){
            	$("#reason").show();
            	$("#reason3").show();
            	$("#jielun2").attr("checked","true");
            }else if(order.item.jielun=="2"){
            	$("#reason2").show();
            	$("#jielun3").attr("checked","true");
            }
            if(order.item.firstreason=="0"){
            	order.reasonlist = order.reasonlist1;
            }else if(order.item.firstreason=="1"){
            	order.reasonlist = order.reasonlist2;
            }else if(order.item.firstreason=="2"){
            	order.reasonlist = order.reasonlist3;
            }else if(order.item.firstreason=="3"){
            	order.reasonlist = order.reasonlist4;
            }else if(order.item.firstreason=="4"){
            	order.reasonlist = order.reasonlist5;
            }else if(order.item.firstreason=="5"){
            	order.reasonlist = order.reasonlist6;
            }else if(order.item.firstreason=="6"){
            	order.reasonlist = order.reasonlist7;
            }else if(order.item.firstreason=="7"){
            	order.reasonlist = order.reasonlist8;
            }else if(order.item.firstreason=="8"){
            	order.reasonlist = order.reasonlist9;
            }else if(order.item.firstreason=="9"){
            	order.reasonlist = order.reasonlist10;
            }else if(order.item.firstreason=="10"){
            	order.reasonlist = order.reasonlist11;
            }else if(order.item.firstreason=="12"){
            	order.reasonlist = order.reasonlist13;
            }
        })
        order_service.loadById_tel(id).then(function (res) {
             //初始化变量
            order.tellist = res.tellist;
        })
    }   
	
	 $scope.edit=function(d){
        $state.go('order-edit',{id: d.id});
    }
	 
	 $scope.xuanze1=function(){
		 if(order.item.shenpiStatus=="0"||order.item.shenpiStatus=="1"){ 
		 if(order.item.firstreason=="0"){
			 order.reasonlist = order.reasonlist1;
		 }else if(order.item.firstreason=="1"){
			 order.reasonlist = order.reasonlist2;
		 }else if(order.item.firstreason=="2"){
			 order.reasonlist = order.reasonlist3;
		 }else if(order.item.firstreason=="3"){
			 order.reasonlist = order.reasonlist4;
		 }else if(order.item.firstreason=="4"){
			 order.reasonlist = order.reasonlist5;
		 }else if(order.item.firstreason=="5"){
			 order.reasonlist = order.reasonlist6;
		 }else if(order.item.firstreason=="6"){
			 order.reasonlist = order.reasonlist7;
		 }else if(order.item.firstreason=="7"){
			 order.reasonlist = order.reasonlist8;
		 }else if(order.item.firstreason=="8"){
			 order.reasonlist = order.reasonlist9;
		 }else if(order.item.firstreason=="9"){
			 order.reasonlist = order.reasonlist10;
		 }else if(order.item.firstreason=="10"){
			 order.reasonlist = order.reasonlist11;
		 }else if(order.item.firstreason=="12"){
			 order.reasonlist = order.reasonlist13;
		 }
		 } 
	 }
	 
	 $scope.savetel=function(){
		 order.tel.order_id=id;
		 order.tellist.push(order.tel);
		 order_service.createOrUpdate_tel(order.tellist).then(function(res){
			 order.tellist = res.tellist;
			 $scope.$apply;
		 })
	 }
	 
	 function getTime(){	 
		 var myDate = new Date();
		 var time = myDate.toLocaleDateString()+" "+myDate.getHours()+":"+myDate.getMinutes()+":"+myDate.getSeconds();
		 return time;
	 }
	 
	 $scope.jujue=function(action){
		 order.ziliao = {};
		 if(order.item.shenpiStatus=="0"||order.item.shenpiStatus=="1"){ 
		 if(action=="0"){
			 order.ziliao.note1 = order.item.note1;
			 $("#reason1").show();
			 $("#reason").hide();
			 $("#reason2").hide();
			 $("#reason3").hide();
		 }else if(action=="1"){
			 order.ziliao.firstreason = order.item.firstreason;
			 order.ziliao.jujue_reason = order.item.jujue_reason;
			 order.ziliao.note2 = order.item.note2;
			 $("#reason1").hide();
			 $("#reason").show();
			 $("#reason2").hide();
			 $("#reason3").show();
		 }else{
			 order.ziliao.note3 = order.item.note3;
			 $("#reason1").hide();
			 $("#reason").hide();
			 $("#reason2").show();
			 $("#reason3").hide();
		 }
		 order.item.jielun = action;
		 order.ziliao.jielun = order.item.jielun;
		 order.ziliao.id = order.item.id;
		 var submitform={};
	        submitform.item = order.ziliao;
	        var promise = order_service.createOrUpdate(submitform);
	        promise.then(function (data) {  // 调用承诺API获取数据 .resolve
			      if(data.data.status=='success'){
	                toastr['success']("您的信息已保存成功！", "")
	            }else{
	                toastr[data.data.status](data.data.message, "")
	            }
	        }, function (data) {  // 处理错误 .reject
				Metronic.unblockUI();
	            toastr['error']("您的信息保存出错！", "SORRY！")
	        });
		 }  
	 }
	 
	 $scope.save_reason = function(){
		 if(order.item.shenpiStatus=="0"||order.item.shenpiStatus=="1"){
		 var submitform={};
	        submitform.item = order.item;
	        var promise = order_service.createOrUpdate(submitform);
	        promise.then(function (data) {  // 调用承诺API获取数据 .resolve
			      if(data.data.status=='success'){
	                toastr['success']("您的信息已保存成功！", "")
	            }else{
	                toastr[data.data.status](data.data.message, "")
	            }
	        }, function (data) {  // 处理错误 .reject
				Metronic.unblockUI();
	            toastr['error']("您的信息保存出错！", "SORRY！")
	        });
		 }  
	 }
	 
	 $scope.save_note=function(){
		 if(order.item.shenpiStatus=="0"||order.item.shenpiStatus=="1"){
		 var submitform={};
	        submitform.item = order.item;
	        var promise = order_service.createOrUpdate(submitform);
	        promise.then(function (data) {  // 调用承诺API获取数据 .resolve
			      if(data.data.status=='success'){
	                toastr['success']("您的信息已保存成功！", "")
	            }else{
	                toastr[data.data.status](data.data.message, "")
	            }
	        }, function (data) {  // 处理错误 .reject
				Metronic.unblockUI();
	            toastr['error']("您的信息保存出错！", "SORRY！")
	        });
		 } 
	 }
	 
	 $scope.zhedie1=function(d){
		 $("#"+d.id).toggle();
		 $("#"+d.id).toggleClass("in");
		 if($("#"+d.id).hasClass("in")){
			 if(d.status=="1"){
				 $("#"+d.id+"1").show();
				 $("#"+d.id+"1").addClass("in");
			 }
		 }else{
			 $("#"+d.id+"1").hide();
			 $("#"+d.id+"1").removeClass("in");
		 }
	 }
	 
	 $scope.zhedie=function(action){
		 if(action=="1"){
			 $("#collapse1").toggle();
			 $("#collapse1").toggleClass("in");
		 }else if(action=="2"){
			 $("#collapse2").toggle();
			 $("#collapse2").toggleClass("in");
			 if($("#collapse2").hasClass("in")){
				 if(order.item.self_check_status=="1"){
					 $("#collapse11").show();
					 $("#collapse11").addClass("in");
				 }
			 }else{
				 $("#collapse11").hide();
				 $("#collapse11").removeClass("in");
			 }
		 }else if(action=="3"){
			 $("#collapse3").toggle();
			 $("#collapse3").toggleClass("in");
			 if($("#collapse3").hasClass("in")){
				 if(order.item.com_check_status=="1"){
					 $("#collapse21").show();
					 $("#collapse21").addClass("in");
				 }
			 }else{
				 $("#collapse21").hide();
				 $("#collapse21").removeClass("in");
			 }
		 }else if(action=="4"){
			 $("#collapse4").toggle();
			 $("#collapse4").toggleClass("in");
			 if($("#collapse4").hasClass("in")){
				 if(order.item.rela_check_status1=="1"){
					 $("#collapse31").show();
					 $("#collapse31").addClass("in");
				 }
			 }else{
				 $("#collapse31").hide();
				 $("#collapse31").removeClass("in");
			 }
		 }else if(action=="5"){
			 $("#collapse5").toggle();
			 $("#collapse5").toggleClass("in");
			 if($("#collapse5").hasClass("in")){
				 if(order.item.rela_check_status2=="1"){
					 $("#collapse41").show();
					 $("#collapse41").addClass("in");
				 }
			 }else{
				 $("#collapse41").hide();
				 $("#collapse41").removeClass("in");
			 }
		 }else if(action=="6"){
			 $("#collapse6").toggle();
			 $("#collapse6").toggleClass("in");
			 if($("#collapse6").hasClass("in")){
				 if(order.item.rela_check_status3=="1"){
					 $("#collapse51").show();
					 $("#collapse51").addClass("in");
				 }
			 }else{
				 $("#collapse51").hide();
				 $("#collapse51").removeClass("in");
			 }
		 }else{
			 $("#collapse7").toggle();
			 $("#collapse7").toggleClass("in");
			 if($("#collapse7").hasClass("in")){
				 if(order.item.rela_check_status4=="1"){
					 $("#collapse61").show();
					 $("#collapse61").addClass("in");
				 }
			 }else{
				 $("#collapse61").hide();
				 $("#collapse61").removeClass("in");
			 }
		 }
		 
	 }
	 
	 $scope.check1 = function(d){
		 if(d.status=="1"){
			 $("#"+d.id+"1").show();
			 $("#"+d.id+"1").addClass("in");
		 }else{
			 $("#"+d.id+"1").hide();
			 $("#"+d.id+"1").removeClass("in");
		 }
	 }

	$scope.check=function(action){
		
		if(order.item.shenpiStatus=="0"||order.item.shenpiStatus=="1"){
		if(action=="1"){
			if(order.item.self_check_status=="1"){
				$("#collapse11").show();
				$("#collapse11").addClass("in");
			}else{
				$("#collapse11").hide();
				$("#collapse11").removeClass("in");
			}
		}
		if(action=="2"){
			if(order.item.com_check_status=="1"){
				$("#collapse21").show();
				$("#collapse21").addClass("in");
			}else{
				$("#collapse21").hide();
				$("#collapse21").removeClass("in");
			}
		}
		if(action=="3"){
			if(order.item.rela_check_status1=="1"){
				$("#collapse31").show();
				$("#collapse31").addClass("in");
			}else{
				$("#collapse31").hide();
				$("#collapse31").removeClass("in");
			}
		}
		if(action=="4"){
			if(order.item.rela_check_status2=="1"){
				$("#collapse41").show();
				$("#collapse41").addClass("in");
			}else{
				$("#collapse41").hide();
				$("#collapse41").removeClass("in");
			}
		}
		if(action=="5"){
			if(order.item.rela_check_status3=="1"){
				$("#collapse51").show();
				$("#collapse51").addClass("in");
			}else{
				$("#collapse51").hide();
				$("#collapse51").removeClass("in");
			}
		}
		if(action=="6"){
			if(order.item.rela_check_status4=="1"){
				$("#collapse61").show();
				$("#collapse61").addClass("in");
			}else{
				$("#collapse61").hide();
				$("#collapse61").removeClass("in");
			}
		}
		}	
	}
	 
    // 增加修改保存
    // 增加修改保存
	
	$scope.save1 = function(action) {
		order.ziliao = {};
		if(action=="1"){
			order.item.self_check_time = getTime();
			order.ziliao.self_check_time = order.item.self_check_time;
			order.ziliao.self_phone_addr = order.item.self_phone_addr;
			order.ziliao.self_check_status = order.item.self_check_status;
			order.ziliao.self_check_result = order.item.self_check_result;
		}else if(action=="2"){
			order.item.com_check_time = getTime();
			order.ziliao.com_check_time = order.item.com_check_time;
			order.ziliao.com_phone_addr = order.item.com_phone_addr;
			order.ziliao.com_check_status = order.item.com_check_status;
			order.ziliao.com_check_result = order.item.com_check_result;
		}else if(action=="3"){
			order.item.rela_check_time1 = getTime();
			order.ziliao.rela_check_time1 = order.item.rela_check_time1;
			order.ziliao.rela_phone_addr1 = order.item.rela_phone_addr1;
			order.ziliao.rela_check_status1 = order.item.rela_check_status1;
			order.ziliao.rela_check_result1 = order.item.rela_check_result1;
		}else if(action=="4"){
			order.item.rela_check_time2 = getTime();
			order.ziliao.rela_check_time2 = order.item.rela_check_time2;
			order.ziliao.rela_phone_addr2 = order.item.rela_phone_addr2;
			order.ziliao.rela_check_status2 = order.item.rela_check_status2;
			order.ziliao.rela_check_result2 = order.item.rela_check_result2;
		}else if(action=="5"){
			order.item.rela_check_time3 = getTime();
			order.ziliao.rela_check_time3 = order.item.rela_check_time3;
			order.ziliao.rela_phone_addr3 = order.item.rela_phone_addr3;
			order.ziliao.rela_check_status3 = order.item.rela_check_status3;
			order.ziliao.rela_check_result3 = order.item.rela_check_result3;
		}else if(action=="6"){
			order.item.rela_check_time4 = getTime();
			order.ziliao.rela_check_time4 = order.item.rela_check_time4;
			order.ziliao.rela_phone_addr4 = order.item.rela_phone_addr4;
			order.ziliao.rela_check_status4 = order.item.rela_check_status4;
			order.ziliao.rela_check_result4 = order.item.rela_check_result4;
		}
			order.ziliao.id =  order.item.id;
	        var submitform={};
	        submitform.item = order.ziliao;
	        var promise = order_service.createOrUpdate(submitform);
	        promise.then(function (data) {  // 调用承诺API获取数据 .resolve
			      if(data.data.status=='success'){
	                toastr['success']("您的信息已保存成功！", "")
	                if(action=="1"){
	                	$("#collapse2").hide();
	                	$("#collapse2").removeClass("in");
	                	$("#collapse11").hide();
	                	$("#collapse11").removeClass("in");
	                }else if(action=="2"){
	                	$("#collapse3").hide();
	                	$("#collapse3").removeClass("in");
	                	$("#collapse21").hide();
	                	$("#collapse21").removeClass("in");
	                }else if(action=="3"){
	                	$("#collapse4").hide();
	                	$("#collapse4").removeClass("in");
	                	$("#collapse31").hide();
	                	$("#collapse31").removeClass("in");
	                }else if(action=="4"){
	                	$("#collapse5").hide();
	                	$("#collapse5").removeClass("in");
	                	$("#collapse41").hide();
	                	$("#collapse41").removeClass("in");
	                }else if(action=="5"){
	                	$("#collapse6").hide();
	                	$("#collapse6").removeClass("in");
	                	$("#collapse51").hide();
	                	$("#collapse51").removeClass("in");
	                }else if(action=="6"){
	                	$("#collapse7").hide();
	                	$("#collapse7").removeClass("in");
	                	$("#collapse61").hide();
	                	$("#collapse61").removeClass("in");
	                }
	            }else{
	                toastr[data.data.status](data.data.message, "")
	            }
	        }, function (data) {  // 处理错误 .reject
				Metronic.unblockUI();
	            toastr['error']("您的信息保存出错！", "SORRY！")
	        });
	    }
	
	$scope.save2 = function(d) {
            d.telTime = getTime();
	        var promise = order_service.createOrUpdate_tel1(d);
	        promise.then(function (data) {  // 调用承诺API获取数据 .resolve
			      if(data.status=='success'){
	                toastr['success']("您的信息已保存成功！", "")
	                $("#"+d.id).hide();
	                $("#"+d.id).removeClass("in");
	                $("#"+d.id+"1").hide();
	                $("#"+d.id+"1").removeClass("in");
	            }else{
	                toastr[data.status](data.message, "")
	            }
	        }, function (data) {  // 处理错误 .reject
				Metronic.unblockUI();
	            toastr['error']("您的信息保存出错！", "SORRY！")
	        });
	    }
	
    order.save = function(action) {
        var submitform={};
        submitform.item = order.item;
        var promise = order_service.createOrUpdate(submitform);
        promise.then(function (data) {  // 调用承诺API获取数据 .resolve
		      if(data.data.status=='success'){
                toastr['success']("您的信息已保存成功！", "")
            }else{
                toastr[data.data.status](data.data.message, "")
            }
        }, function (data) {  // 处理错误 .reject
			Metronic.unblockUI();
            toastr['error']("您的信息保存出错！", "SORRY！")
        });
    }
    
    
    // set sidebar closed and body solid layout mode
    $rootScope.settings.layout.pageBodySolid = true;
    $rootScope.settings.layout.pageSidebarClosed = false;
})


MetronicApp.controller('order-edit_controller4', function($rootScope, $scope,$state,$stateParams,user_service,order_service,alertService,filterFilter,$modal, $http, $timeout) {
    $scope.$on('$viewContentLoaded', function() {
        // initialize core components
        Metronic.initAjax();
        ComponentsPickers.init();
       
    });
    var order = {};
    $scope.order = order;
    var id = $rootScope.orderId;
	order.item={};
    if(!isNull(id)){
       order_service.loadById(id).then(function (res) {
            //初始化变量
           order.item = res
        });
    }
	
    $(function(){
    	$("[data-magnify=gallery]").magnify();
    })
    
	 $scope.edit=function(d){
        $state.go('order-edit',{id: d.id});
    }

	 $scope.zhedie=function(action){
		 if(action=="1"){
			 $("#collapseOne").toggle();
			 $("#collapseOne").toggleClass("in");
			 $("#collapseSix").hide();
			 $("#collapseSix").removeClass("in");
		 }else if(action=="2"){
			 $("#collapseTwo").toggle();
			 $("#collapseTwo").toggleClass("in");
			 $("#collapseSeven").hide();
			 $("#collapseSeven").removeClass("in");
		 }else if(action=="3"){
			 $("#collapseThree").toggle();
			 $("#collapseThree").toggleClass("in");
			 $("#collapseEight").hide();
			 $("#collapseEight").removeClass("in");
		 }else if(action=="4"){
			 $("#collapseFour").toggle();
			 $("#collapseFour").toggleClass("in");
			 $("#collapseNine").hide();
			 $("#collapseNine").removeClass("in");
		 }else{
			 $("#collapseFive").toggle();
			 $("#collapseFive").toggleClass("in");
		 }
		 
	 }

	 $scope.yichang=function(action){
		 if(action=="1"){
			 $("#collapseSix").show();
			 $("#collapseSix").addClass("in");
		 }else if(action=="2"){
			 $("#collapseSeven").show();
			 $("#collapseSeven").addClass("in");
		 }else if(action=="3"){
			 $("#collapseEight").show();
			 $("#collapseEight").addClass("in");
		 }else{
			 $("#collapseNine").show();
			 $("#collapseNine").addClass("in");
		 }
	 }
	 
	 $scope.zhengchang=function(action){
		 if(action=="1"){
			 $("#collapseSix").hide();
			 $("#collapseSix").removeClass("in");
		 }else if(action=="2"){
			 $("#collapseSeven").hide();
			 $("#collapseSeven").removeClass("in");
		 }else if(action=="3"){
			 $("#collapseEight").hide();
			 $("#collapseEight").removeClass("in");
		 }else{
			 $("#collapseNine").hide();
			 $("#collapsNine").removeClass("in");
		 }
	 }
	 
	    $scope.juhe=function(){
	    	//alert(1111);
	        openwindow($modal, 'juhe.html', 'lg', function ($scope, $modalInstance,order_service) {
	        	//begin-本业务的全局变量
	            //begin-本业务的全局变量
	            var order = {};
	            $scope.order = order;
	            //初始化controller
	        
	            
	            initController($scope, order, 'order',order_service,filterFilter);
	            
	            
	            $scope.cancel = function () {
	                $modalInstance.dismiss('cancel');
	            };
	        })
	    }
	 
	    $scope.dasheng=function(){
	    	//alert(1111);
	        openwindow($modal, 'dasheng.html', 'lg', function ($scope, $modalInstance,order_service) {
	        	//begin-本业务的全局变量
	            //begin-本业务的全局变量
	            var order = {};
	            order.item={};
	            $scope.order = order;
	            //初始化controller
	        
	            
	            initController($scope, order, 'order',order_service,filterFilter);
	            
	            if(!isNull(id)){
	                order_service.loadById_online(id).then(function (res) {
	                     //初始化变量
	                    order.item = res.item
	                 });
	             }
	            
	            $scope.cancel = function () {
	                $modalInstance.dismiss('cancel');
	            };
	        })
	    }
	    
	    $scope.yunying=function(){
	    	//alert(1111);
	        openwindow($modal, 'yunying.html', 'lg', function ($scope, $modalInstance,order_service) {
	        	//begin-本业务的全局变量
	            //begin-本业务的全局变量
	            var order = {};
	            order.item={};
	            $scope.order = order;
	            //初始化controller
	        
	            Metronic.blockUI();
	            initController($scope, order, 'order',order_service,filterFilter);
	            
	            if(!isNull(id)){
	                order_service.loadById_yunying(id).then(function (res) {
	                     //初始化变量
	                    order.item = res;
	                    Metronic.unblockUI();
	                 });
	             }
	            
	            $scope.cancel = function () {
	                $modalInstance.dismiss('cancel');
	            };
	        })
	    }
	    
    // 增加修改保存
    // 增加修改保存
    order.save = function(action) {
	   Metronic.blockUI();
        var submitform={};
        submitform.item = order.item;
        var promise = order_service.createOrUpdate(submitform);
        promise.then(function (data) {  // 调用承诺API获取数据 .resolve
		   Metronic.unblockUI();
		      if(data.data.status=='success'){
                toastr['success']("您的信息已保存成功！", "")
                 order.item={}
                if(action==1){
                    $state.go('order-list');
                }else if(action==3){
                	window.history.back();
                }
            }else{
                toastr[data.data.status](data.data.message, "")
            }
        }, function (data) {  // 处理错误 .reject
			Metronic.unblockUI();
            toastr['error']("您的信息保存出错！", "SORRY！")
        });
    }
    
    
    // set sidebar closed and body solid layout mode
    $rootScope.settings.layout.pageBodySolid = true;
    $rootScope.settings.layout.pageSidebarClosed = false;
})


MetronicApp.controller('order-edit_controller5', function($rootScope, $scope,$state,$stateParams,user_service,order_service,alertService,filterFilter,$modal, $http, $timeout) {
    $scope.$on('$viewContentLoaded', function() {
        // initialize core components
        Metronic.initAjax();
        ComponentsPickers.init();
       
    });
    var order = {};
    order.out={};
    $scope.order = order;
    var id= $rootScope.orderId;
	order.item={};
	order.unitlist = [];
	order.idnamelist = [];
	order.phonelist = [];
	order.fphonelist = [];
	order.frenamelist = [];
	order.uphonelist = [];
	order.chachong = {};
	
	if(!isNull(id)){
		order_service.loadById_repeat(id).then(function (res) {
            //初始化变量
			order.chachong = res.chachong;
           order.unitlist = res.unitlist;
           order.idnamelist = res.idnamelist;
           order.phonelist = res.phonelist;
           order.fphonelist = res.fphonelist;
           order.frenamelist = res.frenamelist;
           order.uphonelist = res.uphonelist;
        });
	}
	
	$scope.detail=function(d){
		window.open('http://192.168.6.85:8080/fastTool/ng/index.html#/order-edit/'+d.id);
    }
	
	 $scope.zhedie=function(action){
		 if(action=="1"){
			 $("#collapseOneOne").toggle();
			 $("#collapseOneOne").toggleClass("in");

		 }else if(action=="2"){
			 $("#collapseTwoTwo").toggle();
			 $("#collapseTwoTwo").toggleClass("in");

		 }else if(action=="3"){
			 $("#collapseThreeThree").toggle();
			 $("#collapseThreeThree").toggleClass("in");

		 }else if(action=="4"){
			 $("#collapseFourFour").toggle();
			 $("#collapseFourFour").toggleClass("in");
		 }else if(action=="5"){
			 $("#collapseFiveFive").toggle();
			 $("#collapseFiveFive").toggleClass("in");
		 }else{
			 $("#collapseSixSix").toggle();
			 $("#collapseSixSix").toggleClass("in");
		 }
		 
	 }
	
    // 增加修改保存
    // 增加修改保存
    order.save = function(action) {
	   Metronic.blockUI();
        var submitform={};
        submitform.item = order.item;
        var promise = order_service.createOrUpdate(submitform);
        promise.then(function (data) {  // 调用承诺API获取数据 .resolve
		   Metronic.unblockUI();
		      if(data.data.status=='success'){
                toastr['success']("您的信息已保存成功！", "")
                 order.item={}
                if(action==1){
                    $state.go('order-list');
                }else if(action==3){
                	window.history.back();
                }
            }else{
                toastr[data.data.status](data.data.message, "")
            }
        }, function (data) {  // 处理错误 .reject
			Metronic.unblockUI();
            toastr['error']("您的信息保存出错！", "SORRY！")
        });
    }
    
    
    // set sidebar closed and body solid layout mode
    $rootScope.settings.layout.pageBodySolid = true;
    $rootScope.settings.layout.pageSidebarClosed = false;
})
