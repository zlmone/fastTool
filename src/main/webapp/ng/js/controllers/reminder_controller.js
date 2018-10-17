'use strict';
MetronicApp.controller('reminder_controller', function($rootScope, $scope,$state,$stateParams,reminder_service,alertService,filterFilter,$modal, $http, $timeout) {
    $scope.$on('$viewContentLoaded', function() {
        // initialize core components
        Metronic.initAjax();
        ComponentsPickers.init();
    });
    var reminder = {};
    $scope.reminder = reminder;
    //初始化controller
    initController($scope, reminder, 'reminder',  reminder_service, filterFilter);
	reminder.searchForm.searchItems = [
		 {"name": "delstatus", "code":"eq"},
        {"name": "type", "code":"eq"},
        {"name": "state", "code":"eq"},
        {"name": "isRead", "code":"eq"}
		 /*{"name": "owner_id", "code":"eq"},
         {"name": "owner_fullpost_id", "code":"lk"}*/
    ];
    reminder.searchForm.orderGuize="createTime desc";
	reminder.searchForm.delstatus="0";
    //缓存start
    if($rootScope.reminder!=undefined){
        reminder.searchForm=$rootScope.reminder;
    }
    $scope.$watch('reminder.searchForm',function(newVal,oldVal,scope){
        $rootScope.reminder=reminder.searchForm;
    },true);
    //缓存end
	reminder.searchForm.fn='301';//功能号
    reminder.search1();


    var go=function(d){
        if(d.type=='1'){        //物料管理
            if(d.state=='2'|| d.state=='3'){
                $state.go('materiel_Apply-detail',{id: d.form_id});
            }else{//审核list
                $state.go('todotask-list',{type:0});
            }
        }else if(d.type=='2'){//报销管理
            if(d.state=='2'|| d.state=='3'){
                $state.go('baoxiao_application-detail',{id: d.form_id});
            }else{//审核list
                $state.go('todotask-list',{type:0});
            }
        }else if(d.type=='3'){//离职管理
            if(d.state=='2'|| d.state=='3'){
                $state.go('quit-detail',{id: d.form_id});
            }else{//审核list
                $state.go('todotask-list',{type:0});
            }
        }else if(d.type=='4'){//请假管理
            if(d.state=='2'|| d.state=='3'){
                $state.go('leave1-detail',{id: d.form_id});
            }else{//审核list
                $state.go('todotask-list',{type:0});
            }
        }else if(d.type=='5'){//更改为1审批
            if(d.state=='2'|| d.state=='3'){
                toastr['error']("没有查看页面", "SORRY！")
            }else{//审核list
                $state.go('todotask-list',{type:0});
            }
        }
    }

    reminder.detail=function(d){
        Metronic.blockUI();
        var submitform={};
        submitform.item =angular.copy(d);
        submitform.item.isRead='1';
        var promise = reminder_service.createOrUpdate(submitform);
        promise.then(function (data) {  // 调用承诺API获取数据 .resolve
            Metronic.unblockUI();
            if(data.data.status=='success'){
                go(d);
            }else{
                toastr[data.data.status](data.data.message, "")
            }
        }, function (data) {  // 处理错误 .reject
            Metronic.unblockUI();
            toastr['error']("您的信息保存出错！", "SORRY！")
        });
    }

    reminder.yidu=function(d){
        Metronic.blockUI();
        var submitform={};
        submitform.item =angular.copy(d);
        submitform.item.isRead='1';
        var promise = reminder_service.createOrUpdate(submitform);
        promise.then(function (data) {  // 调用承诺API获取数据 .resolve
            Metronic.unblockUI();
            if(data.data.status=='success'){
                toastr['success']("您的信息已保存成功！", "")
                reminder.searchN();
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
});


MetronicApp.controller('reminder-edit_controller', function($rootScope, $scope,$state,$stateParams,reminder_service,alertService,filterFilter,$modal, $http, $timeout) {
    $scope.$on('$viewContentLoaded', function() {
        // initialize core components
        Metronic.initAjax();
        ComponentsPickers.init();
       
    });
    var reminder = {};
    $scope.reminder = reminder;
    var id= $stateParams.id;
	reminder.item={};
    if(!isNull(id)){
       reminder_service.loadById(id).then(function (res) {
            //初始化变量
           reminder.item = res
        });
    }
	
	 $scope.edit=function(d){
        $state.go('reminder-edit',{id: d.id});
    }


    // 增加修改保存
    // 增加修改保存
    reminder.save = function(action) {
	   Metronic.blockUI();
        var submitform={};
        submitform.item = reminder.item;
        var promise = reminder_service.createOrUpdate(submitform);
        promise.then(function (data) {  // 调用承诺API获取数据 .resolve
		   Metronic.unblockUI();
		      if(data.data.status=='success'){
                toastr['success']("您的信息已保存成功！", "")
                 reminder.item={}
                if(action==1){
                    $state.go('reminder-list');
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
