'use strict';
MetronicApp.controller('payinfo_controller', function($rootScope, $scope,$state,$stateParams,payinfo_service,alertService,filterFilter,$modal, $http, $timeout) {
    $scope.$on('$viewContentLoaded', function() {
        // initialize core components
        Metronic.initAjax();
        ComponentsPickers.init();
    });
    var payinfo = {};
    $scope.payinfo = payinfo;
    //初始化controller
    initController($scope, payinfo, 'payinfo',  payinfo_service, filterFilter);
    payinfo.searchForm.orderGuize="addtime desc";
	payinfo.searchForm.fn='301';//功能号
    payinfo.search1();
	
	
	payinfo.changeCondition2=function(){
        if(isNull(payinfo.searchForm.searchItemName)){
            $scope.conditionContent = conditionText;
            $scope.searchItemValue_type='text';
        }else{
           if(payinfo.searchForm.searchItemName.toLowerCase().endWith('_select')){
			    $scope.searchItemValue_type='select';
			    $scope.conditionValues =[];
            }else{
               payinfo.changeCondition();
           }
        }
    }
	
	$scope.qu=function(action){
    	if(action=="0"){
    		$state.go('order-list5');
    	}else if(action=="1"){
    		$state.go('payinfo-list');
    	}
    }
	
    $scope.add=function(){
        $state.go('payinfo-edit',{id: ''});
    }
    $scope.edit=function(d){
        $state.go('payinfo-edit',{id: d.id});
    }
    $scope.detail=function(d){
    	openwindow($modal, 'seller-add.html', 'lg', function ($scope, $modalInstance,payinfo_service) {
         	//begin-本业务的全局变量
             //begin-本业务的全局变量
             var payinfo = {};
             payinfo.item = {};
             $scope.payinfo = payinfo;
             //初始化controller
             payinfo.yuqilist = [];
             
             initController($scope, payinfo, 'payinfo',payinfo_service,filterFilter);
            
             if(!isNull(d.id)){
            	 var promise = payinfo_service.loadById1(d.uid).then(function(res){
            		 payinfo.item = res.item;
            		 payinfo.yuqilist = res.yuqilist;
            	 })
             }
             
             $scope.cancel = function () {
                 $modalInstance.dismiss('cancel');
             };
         })
    }
    
    $scope.detail1=function(d){
        openwindow($modal, 'urge_detail-list.html', 'lg', function ($scope, $modalInstance,urge_detail_service) {
        	//begin-本业务的全局变量
            //begin-本业务的全局变量
            var urge_detail = {};
            urge_detail.item = {};
            //huopin.huopin_list = [];
            $scope.urge_detail = urge_detail;
            //初始化controller
            //alert(222);
            initController($scope, urge_detail, 'urge_detail',urge_detail_service,filterFilter);
            
            urge_detail.searchForm.orderGuize="addTime desc";
            urge_detail.searchForm.searchItems.order_id_eq = d.uid;
            urge_detail.searchForm.fn="301";
            urge_detail.search1();
            
            $scope.submit = function () {
            	Metronic.blockUI();
                var submitform={};
                urge_detail.item.order_id = d.uid;
                submitform.item = urge_detail.item;
                var promise = urge_detail_service.createOrUpdate(submitform);
                promise.then(function (data) {  // 调用承诺API获取数据 .resolve
        		   Metronic.unblockUI();
        		      if(data.data.status=='success'){
                        toastr['success']("您的信息已保存成功！", "")
                         urge_detail.item={}
                        urge_detail.search1();
                    }else{
                        toastr[data.data.status](data.data.message, "")
                    }
                }, function (data) {  // 处理错误 .reject
        			Metronic.unblockUI();
                    toastr['error']("您的信息保存出错！", "SORRY！")
                });
            };
            
            $scope.cancel = function () {
                $modalInstance.dismiss('cancel');
            };
        })
   
    }
    
    // set sidebar closed and body solid layout mode
    $rootScope.settings.layout.pageBodySolid = true;
    $rootScope.settings.layout.pageSidebarClosed = false;
});


MetronicApp.controller('payinfo-edit_controller', function($rootScope, $scope,$state,$stateParams,payinfo_service,alertService,filterFilter,$modal, $http, $timeout) {
    $scope.$on('$viewContentLoaded', function() {
        // initialize core components
        Metronic.initAjax();
        ComponentsPickers.init();
       
    });
    var payinfo = {};
    $scope.payinfo = payinfo;
    var id= $stateParams.id;
	payinfo.item={};
    if(!isNull(id)){
       payinfo_service.loadById(id).then(function (res) {
            //初始化变量
           payinfo.item = res
        });
    }
	
	 $scope.edit=function(d){
        $state.go('payinfo-edit',{id: d.id});
    }


    // 增加修改保存
    // 增加修改保存
    payinfo.save = function(action) {
	   Metronic.blockUI();
        var submitform={};
        submitform.item = payinfo.item;
        var promise = payinfo_service.createOrUpdate(submitform);
        promise.then(function (data) {  // 调用承诺API获取数据 .resolve
		   Metronic.unblockUI();
		      if(data.data.status=='success'){
                toastr['success']("您的信息已保存成功！", "")
                 payinfo.item={}
                if(action==1){
                    $state.go('payinfo-list');
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
