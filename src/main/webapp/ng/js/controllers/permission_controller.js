'use strict';

MetronicApp.controller('permission_controller', function($scope,$rootScope, $modal, $stateParams, permission_service, changliang_service, $location, filterFilter) {
    $scope.$on('$viewContentLoaded', function () {
        // initialize core components
        Metronic.initAjax();
        ComponentsPickers.init();
    });


    //begin-本业务的全局变量
    var permission = {};
    $scope.permission = permission;
    //初始化controller  TODO
    initController($scope, permission, 'permission', permission_service, filterFilter);//changliang_service,
    permission.searchForm.searchItems = [
        {"name": "wuliaomingcheng", "code": "lk"},
        {"name": "wuliaobianma", "code": "lk"}
    ];
    permission.searchForm.orderGuize="createTime desc";

    //        增加 修改开始
    var id = $stateParams.id;
    if (id != 0 && id != undefined) {
        var promise = permission_service.loadById(id);
        promise.then(function (res) {
            //初始化变量
            permission.item = res;
        });
    }
    // 增加修改保存
    permission.save = function() {
        var submitform={};
        submitform.item = permission.item;
        var promise = permission_service.createOrUpdate(submitform);
        promise.then(function (data) {  // 调用承诺API获取数据 .resolve
            alert('提交成功');
            $location.path('permission-list/0');
        }, function (data) {  // 处理错误 .reject
            alert('提交失败');
        });
    }
    //        执行数据初始化
    var type = $stateParams.type;
    if(type==0){
        permission.search1();
    }
    //        执行数据初始化结束
    //-end功能结束

});


MetronicApp.controller('permission_edit_controller', function($scope,$rootScope, $modal, $stateParams, permission_service,changliang_service, $location, filterFilter) {
    $scope.$on('$viewContentLoaded', function () {
        // initialize core components
        Metronic.initAjax();
        ComponentsPickers.init();
    });

    var id =  $stateParams.id;
    var permission = {};
    $scope.permission = permission;
    permission.chang={};
    permission.getchangliang = function(fenlei){
        if(permission.chang[fenlei]==undefined){
            changliang_service.getchangliang(fenlei).then(function(data){
                permission.chang[fenlei]=data;
            });
        }
    }
    if(id!='0'){
        var promise=  permission_service.loadById(id);
        promise.then(function(data){
            permission.item=data;
        });
    }
    $scope.submit = function () {
        permission.item.files=[];
        initUploadFiles( permission.fileflow.files, permission.item.files);
        var promise = permission_service.createOrUpdate(permission.item);
        promise.then(function (data) {  // 调用承诺API获取数据 .resolve
            alert('提交成功');
            $location.path("permission-list");
        }, function (data) {  // 处理错误 .reject
            alert('提交失败');
        });
    };
    $scope.cancel = function () {
        $location.path("permission-list");
    };
});

