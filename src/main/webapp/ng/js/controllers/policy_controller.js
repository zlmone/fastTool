'use strict';
MetronicApp.controller('policy_controller', function($rootScope, $scope,$state,$stateParams,alertService,policy_service,filterFilter,$modal, $http, $timeout) {
    $scope.$on('$viewContentLoaded', function() {
        // initialize core components
        Metronic.initAjax();
        ComponentsPickers.init();
    });
    var policy = {};
    $scope.policy = policy;
    //初始化controller
    initController($scope, policy, 'policy',  policy_service, filterFilter);
	policy.searchForm.searchItems = [
		 {"name": "delstatus", "code":"eq"},
		 {"name": "policyNum", "code":"lk"},
         {"name": "driverName", "code":"lk"}
    ];
    policy.searchForm.orderGuize="createTime desc";
	policy.searchForm.delstatus="0";
	policy.searchForm.fn='107';//功能号
    policy.search1();
	
	
	policy.changeCondition2=function(){
        if(isNull(policy.searchForm.searchItemName)){
            $scope.conditionContent = conditionText;
            $scope.searchItemValue_type='text';
        }else{
           if(policy.searchForm.searchItemName.toLowerCase().endWith('_select')){
			    $scope.searchItemValue_type='select';
			    $scope.conditionValues =[];
            }else{
               policy.changeCondition();
           }
        }
    }
	
	$scope.zhedie=function(action){
		$("#collapseOne").toggle();
		$("#collapseOne").toggleClass("in");
	}
	
    $scope.add=function(){
        $state.go('policy-edit',{id: ''});
    }
    $scope.edit=function(d){
        $state.go('policy-edit',{id: d.id});
    }
    $scope.detail=function(d){
        $state.go('policy-detail',{id: d.id});
    }
    
    policy.deleteData = function(d){   	
    	var con = confirm("删除是不可恢复的，你确认要删除吗？");
    	
    	var ds = [];
    	ds.push(d);
    	if(con){
    		var promise = policy_service.delete(ds);	
    		promise.then(function(data){
    			
    			if(data.data.status=='success'){
                    policy.searchN();
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


MetronicApp.controller('policy-edit_controller', function($rootScope, $scope,$state,$stateParams,user_service,alertService,policy_service,filterFilter,$modal, $http, $timeout) {
    $scope.$on('$viewContentLoaded', function() {
        // initialize core components
        Metronic.initAjax();
        ComponentsPickers.init();
       
    });
    var policy = {};
    $scope.policy = policy;
    var id= $stateParams.id;
	policy.item={};
    if(!isNull(id)){
       policy_service.loadById(id).then(function (res) {
            //初始化变量
           policy.item = res
        });
    }
	
	 $scope.edit=function(d){
        $state.go('policy-edit',{id: d.id});
    }


    // 增加修改保存
    // 增加修改保存
    policy.save = function(action) {
	   Metronic.blockUI();
        var submitform={};
        submitform.item = policy.item;
        var promise = policy_service.createOrUpdate(submitform);
        promise.then(function (data) {  // 调用承诺API获取数据 .resolve
		   Metronic.unblockUI();
		      if(data.data.status=='success'){
                toastr['success']("您的信息已保存成功！", "")
                 policy.item={}
                if(action==1){
                    $state.go('policy-list');
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
