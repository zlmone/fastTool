'use strict';
MetronicApp.controller('return_visit_controller', function($rootScope, $scope,$state,$stateParams,return_visit_service,alertService,filterFilter,$modal, $http, $timeout) {
    $scope.$on('$viewContentLoaded', function() {
        // initialize core components
        Metronic.initAjax();
        ComponentsPickers.init();
    });
    var return_visit = {};
    $scope.return_visit = return_visit;
    //初始化controller
    initController($scope, return_visit, 'return_visit',  return_visit_service, filterFilter);

    return_visit.searchForm.orderGuize="addTime desc";
	return_visit.searchForm.fn='301';//功能号
    return_visit.search1();
	
	
	return_visit.changeCondition2=function(){
        if(isNull(return_visit.searchForm.searchItemName)){
            $scope.conditionContent = conditionText;
            $scope.searchItemValue_type='text';
        }else{
           if(return_visit.searchForm.searchItemName.toLowerCase().endWith('_select')){
			    $scope.searchItemValue_type='select';
			    $scope.conditionValues =[];
            }else{
               return_visit.changeCondition();
           }
        }
    }
	
    $scope.add=function(){
        $state.go('return_visit-edit',{id: ''});
    }
    $scope.edit=function(d){
        $state.go('return_visit-edit',{id: d.id});
    }
    $scope.detail=function(d){
        $state.go('return_visit-detail',{id: d.id});
    }
    // set sidebar closed and body solid layout mode
    $rootScope.settings.layout.pageBodySolid = true;
    $rootScope.settings.layout.pageSidebarClosed = false;
});


MetronicApp.controller('return_visit-edit_controller', function($rootScope, $scope,$state,$stateParams,return_visit_service,alertService,filterFilter,$modal, $http, $timeout) {
    $scope.$on('$viewContentLoaded', function() {
        // initialize core components
        Metronic.initAjax();
        ComponentsPickers.init();
       
    });
    var return_visit = {};
    $scope.return_visit = return_visit;
    var id= $stateParams.id;
	return_visit.item={};
    if(!isNull(id)){
       return_visit_service.loadById(id).then(function (res) {
            //初始化变量
           return_visit.item = res
        });
    }
	
	 $scope.edit=function(d){
        $state.go('return_visit-edit',{id: d.id});
    }


    // 增加修改保存
    // 增加修改保存
    return_visit.save = function(action) {
	   Metronic.blockUI();
        var submitform={};
        submitform.item = return_visit.item;
        var promise = return_visit_service.createOrUpdate(submitform);
        promise.then(function (data) {  // 调用承诺API获取数据 .resolve
		   Metronic.unblockUI();
		      if(data.data.status=='success'){
                toastr['success']("您的信息已保存成功！", "")
                 return_visit.item={}
                if(action==1){
                    $state.go('return_visit-list');
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
