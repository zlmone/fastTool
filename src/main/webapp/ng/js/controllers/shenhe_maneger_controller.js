'use strict';
MetronicApp.controller('shenhe_maneger_controller', function($rootScope, $scope,$state,$stateParams,shenhe_maneger_service,filterFilter,$modal, $http, $timeout) {
    $scope.$on('$viewContentLoaded', function() {
        // initialize core components
        Metronic.initAjax();
        ComponentsPickers.init();
    });
    var shenhe_maneger = {};
    $scope.shenhe_maneger = shenhe_maneger;
    //初始化controller
    initController($scope, shenhe_maneger, 'shenhe_maneger',  shenhe_maneger_service, filterFilter);
    shenhe_maneger.searchForm.orderGuize="id desc";

    shenhe_maneger.searchForm.searchItems.order_id=$scope.gongxu.item.id;
	//shenhe_maneger.searchForm.searchItems.delstatus="0";
    shenhe_maneger.search1();

    $scope.add=function(){
        $state.go('shenhe_manegeredit',{id: ''});
    }
    $scope.edit=function(d){
        $state.go('shenhe_manegeredit',{id: d.id});
    }
    $scope.detail=function(d){
        $state.go('shenhe_manegerdetail',{id: d.id});
    }
    // set sidebar closed and body solid layout mode
    $rootScope.settings.layout.pageBodySolid = true;
    $rootScope.settings.layout.pageSidebarClosed = false;
});


MetronicApp.controller('shenhe_manegeredit_controller', function($rootScope, $scope,$state,$stateParams,shenhe_maneger_service,filterFilter,$modal, $http, $timeout) {
    $scope.$on('$viewContentLoaded', function() {
        // initialize core components
        Metronic.initAjax();
        ComponentsPickers.init();
       
    });
    var shenhe_maneger = {};
    $scope.shenhe_maneger = shenhe_maneger;
    var id= $stateParams.id;
    if(!isNull(id)){
       shenhe_maneger_service.loadById(id).then(function (res) {
            //初始化变量
           shenhe_maneger.item = res
        });
    }


    // 增加修改保存
    // 增加修改保存
    shenhe_maneger.save = function(action) {
        var submitform={};
        submitform.item = shenhe_maneger.item;
        var promise = shenhe_maneger_service.createOrUpdate(submitform);
        promise.then(function (data) {  // 调用承诺API获取数据 .resolve
            toastr['success']("您的信息已保存成功！", "恭喜您！")
            shenhe_maneger.item={}
            if(action==1){
                $state.go('shenhe_maneger');
            }
        }, function (data) {  // 处理错误 .reject
            toastr['error']("您的信息保存出错！", "SORRY！")
        });
    }
    // set sidebar closed and body solid layout mode
    $rootScope.settings.layout.pageBodySolid = true;
    $rootScope.settings.layout.pageSidebarClosed = false;
})
