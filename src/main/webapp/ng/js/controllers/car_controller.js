'use strict';
MetronicApp.controller('car_controller', function($rootScope, $scope,$state,$stateParams,car_service,alertService,filterFilter,$modal, $http, $timeout) {
    $scope.$on('$viewContentLoaded', function() {
        // initialize core components
        Metronic.initAjax();
        ComponentsPickers.init();
    });
    var car = {};
    $scope.car = car;
    //初始化controller
    initController($scope, car, 'car',  car_service, filterFilter);
	car.searchForm.searchItems = [
		 {"name": "delstatus", "code":"eq"},
		 {"name": "carNum", "code":"lk"},
         {"name": "driverName", "code":"lk"}
    ];
    car.searchForm.orderGuize="createTime desc";
	car.searchForm.delstatus="0";
	car.searchForm.fn='107';//功能号
    car.search1();
	
	
	car.changeCondition2=function(){
        if(isNull(car.searchForm.searchItemName)){
            $scope.conditionContent = conditionText;
            $scope.searchItemValue_type='text';
        }else{
           if(car.searchForm.searchItemName.toLowerCase().endWith('_select')){
			    $scope.searchItemValue_type='select';
			    $scope.conditionValues =[];
            }else{
               car.changeCondition();
           }
        }
    }
	
    $scope.add=function(){
        $state.go('car-edit',{id: ''});
    }
    $scope.edit=function(d){
        $state.go('car-edit',{id: d.id});
    }
    $scope.detail=function(d){
        $state.go('car-detail',{id: d.id});
    }
    
    car.deleteData = function(d){   	
    	var con = confirm("删除是不可恢复的，你确认要删除吗？");
    	
    	var ds = [];
    	ds.push(d);
    	if(con){
    		var promise = car_service.delete(ds);	
    		promise.then(function(data){
    			
    			if(data.data.status=='success'){
                    car.searchN();
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


MetronicApp.controller('car-edit_controller', function($rootScope, $scope,$state,$stateParams,user_service,car_service,alertService,filterFilter,$modal, $http, $timeout) {
    $scope.$on('$viewContentLoaded', function() {
        // initialize core components
        Metronic.initAjax();
        ComponentsPickers.init();
       
    });
    var car = {};
    $scope.car = car;
    var id= $stateParams.id;
	car.item={};
    if(!isNull(id)){
       car_service.loadById(id).then(function (res) {
            //初始化变量
           car.item = res
        });
    }
	
	 $scope.edit=function(d){
        $state.go('car-edit',{id: d.id});
    }


    // 增加修改保存
    // 增加修改保存
    car.save = function(action) {
	   Metronic.blockUI();
        var submitform={};
        submitform.item = car.item;
        var promise = car_service.createOrUpdate(submitform);
        promise.then(function (data) {  // 调用承诺API获取数据 .resolve
		   Metronic.unblockUI();
		      if(data.data.status=='success'){
                toastr['success']("您的信息已保存成功！", "")
                 car.item={}
                if(action==1){
                    $state.go('car-list');
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
    
    $scope.openWindowCust=function(){
    	//alert(1111);
        openwindow($modal, 'user-list.html', 'lg', function ($scope, $modalInstance,user_service) {
        	//begin-本业务的全局变量
            //begin-本业务的全局变量
            var user = {};
            $scope.user = user;
            //初始化controller
            
            initController($scope, user, 'user',user_service,filterFilter);
            
            user.searchForm.searchItems = [
            	{"name": "delstatus", "code":"eq"},
            	{"name":"name","code":"lk"},
            	{"name":"value_name","code":"lk"},
            	{"name":"telephone","code":"lk"},
            	{"name":"category_id","code":"eq"}
            ];
            
            user.searchForm.category_id="4";
            user.searchForm.orderGuize="createTime desc";
            user.searchForm.delstatus="0";
            user.searchForm.fn="301";
            user.search1();
            $scope.submit = function () {
                var selected = user.getSelectItems();
                if(selected==undefined||selected.length==0){
                   alertService.add('warning','请选择责任民警！');
                }
                if(car.item==undefined){
                    car.item={};
                }
                car.item.userId = selected[0].id;
                car.item.userName= selected[0].value_name;
                car.item.owner_fulldept_id=selected[0].fulldept_id;
                car.item.owner_fulldept_name=selected[0].fulldept_name;
                car.item.owner_fullrukudanst_id=selected[0].fullrukudanst_id;
                car.item.owner_fullrukudanst_name=selected[0].fullrukudanst_name;
                //选择后需要做的事情
                $modalInstance.dismiss('cancel');
            };
            $scope.cancel = function () {
                $modalInstance.dismiss('cancel');
            };
        })
    }
    
    
    $scope.updateStatus=function(){
    	//alert(1111);
        openwindow($modal, 'carStatus.html', 'lg', function ($scope, $modalInstance,car_service) {
        	//begin-本业务的全局变量
            //begin-本业务的全局变量
            var car = {};
            $scope.car = car;
            //初始化controller
            
            var id= $stateParams.id;
        	car.item={};
            if(!isNull(id)){
               car_service.loadById(id).then(function (res) {
                    //初始化变量
                   car.item = res
                });
            }
            
            initController($scope, car, 'car',car_service,filterFilter);
            
            
            
            
            $scope.submit = function () {
            	//alert(222);
            	 Metronic.blockUI();
                 var submitform={};
                 submitform.item = car.item;
                 var promise = car_service.createOrUpdate(submitform);
                 promise.then(function (data) {  // 调用承诺API获取数据 .resolve
                	 //alert(333);
         		   Metronic.unblockUI();
         		      if(data.data.status=='success'){
                         toastr['success']("您的信息已保存成功！", "")
                       
                     }else{
                         toastr[data.data.status](data.data.message, "")
                     }
                 }, function (data) {  // 处理错误 .reject
         			Metronic.unblockUI();
                     toastr['error']("您的信息保存出错！", "SORRY！")
                 });
                //选择后需要做的事情
                $modalInstance.dismiss('cancel');
            };
            $scope.cancel = function () {
                $modalInstance.dismiss('cancel');
            };
        })
    }
    
    // set sidebar closed and body solid layout mode
    $rootScope.settings.layout.pageBodySolid = true;
    $rootScope.settings.layout.pageSidebarClosed = false;
})
