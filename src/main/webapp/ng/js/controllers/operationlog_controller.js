'use strict';
MetronicApp.controller('operationlog_controller', function($rootScope, $scope,$state,$stateParams,operationlog_service,alertService,filterFilter,$modal, $http, $timeout) {
    $scope.$on('$viewContentLoaded', function() {
        // initialize core components
        Metronic.initAjax();
        ComponentsPickers.init();
    });
    var operationlog = {};
    $scope.operationlog = operationlog;
    //初始化controller
    initController($scope, operationlog, 'operationlog',  operationlog_service, filterFilter);
    operationlog.searchForm.orderGuize="addTime desc";
	operationlog.searchForm.fn='301';//功能号
    operationlog.search1();
	
	
	operationlog.changeCondition2=function(){
        if(isNull(operationlog.searchForm.searchItemName)){
            $scope.conditionContent = conditionText;
            $scope.searchItemValue_type='text';
        }else{
           if(operationlog.searchForm.searchItemName.toLowerCase().endWith('_select')){
			    $scope.searchItemValue_type='select';
			    $scope.conditionValues =[];
            }else{
               operationlog.changeCondition();
           }
        }
    }
	
    $scope.add=function(){
        $state.go('operationlog-edit',{id: ''});
    }
    $scope.edit=function(d){
        $state.go('operationlog-edit',{id: d.id});
    }
    $scope.detail=function(d){
        $state.go('operationlog-detail',{id: d.id});
    }
    // set sidebar closed and body solid layout mode
    $rootScope.settings.layout.pageBodySolid = true;
    $rootScope.settings.layout.pageSidebarClosed = false;
});


MetronicApp.controller('operationlog-edit_controller', function($rootScope, $scope,$state,$stateParams,operationlog_service,alertService,filterFilter,$modal, $http, $timeout) {
    $scope.$on('$viewContentLoaded', function() {
        // initialize core components
        Metronic.initAjax();
        ComponentsPickers.init();
       
    });
    var operationlog = {};
    $scope.operationlog = operationlog;
    var id= $stateParams.id;
	operationlog.item={};
    if(!isNull(id)){
       operationlog_service.loadById(id).then(function (res) {
            //初始化变量
           operationlog.item = res
        });
    }
	
	 $scope.edit=function(d){
        $state.go('operationlog-edit',{id: d.id});
    }


    // 增加修改保存
    // 增加修改保存
    operationlog.save = function(action) {
	   Metronic.blockUI();
        var submitform={};
        submitform.item = operationlog.item;
        var promise = operationlog_service.createOrUpdate(submitform);
        promise.then(function (data) {  // 调用承诺API获取数据 .resolve
		   Metronic.unblockUI();
		      if(data.data.status=='success'){
                toastr['success']("您的信息已保存成功！", "")
                 operationlog.item={}
                if(action==1){
                    $state.go('operationlog-list');
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
