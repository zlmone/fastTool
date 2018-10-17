'use strict';
MetronicApp.controller('seller_controller', function($rootScope, $scope,$state,$stateParams,seller_service,alertService,filterFilter,$modal, $http, $timeout) {
    $scope.$on('$viewContentLoaded', function() {
        // initialize core components
        Metronic.initAjax();
        ComponentsPickers.init();
    });
    var seller = {};
    $scope.seller = seller;
    //初始化controller
    initController($scope, seller, 'seller',  seller_service, filterFilter);
    seller.searchForm.orderGuize="addtime desc";
	seller.searchForm.fn='301';//功能号
    seller.search1();
	
	
	seller.changeCondition2=function(){
        if(isNull(seller.searchForm.searchItemName)){
            $scope.conditionContent = conditionText;
            $scope.searchItemValue_type='text';
        }else{
           if(seller.searchForm.searchItemName.toLowerCase().endWith('_select')){
			    $scope.searchItemValue_type='select';
			    $scope.conditionValues =[];
            }else{
               seller.changeCondition();
           }
        }
    }
	
    $scope.add=function(){
    	 openwindow($modal, 'seller-add.html', 'lg', function ($scope, $modalInstance,seller_service) {
         	//begin-本业务的全局变量
             //begin-本业务的全局变量
             var seller = {};
             seller.item = {};
             $scope.seller = seller;
             //初始化controller
             
             initController($scope, seller, 'seller',seller_service,filterFilter);
             
             $scope.submit = function () {
          	   Metronic.blockUI();
               var submitform={};
               submitform.item = seller.item;
               var promise = seller_service.createOrUpdate(submitform);
               promise.then(function (data) {  // 调用承诺API获取数据 .resolve
       		   Metronic.unblockUI();
       		      if(data.data.status=='success'){
                       toastr['success']("您的信息已保存成功！", "")
                       seller.searchN();
                       $modalInstance.dismiss('cancel');  
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
    $scope.edit=function(d){
    	openwindow($modal, 'seller-add.html', 'lg', function ($scope, $modalInstance,seller_service) {
         	//begin-本业务的全局变量
             //begin-本业务的全局变量
             var seller = {};
             seller.item = {};
             $scope.seller = seller;
             //初始化controller
             
             initController($scope, seller, 'seller',seller_service,filterFilter);
             
             if(!isNull(d.id)){
                 seller_service.loadById(d.id).then(function (res) {
                      //初始化变量
                     seller.item = res
                  });
              }
             
             $scope.submit = function () {
          	   Metronic.blockUI();
               var submitform={};
               submitform.item = seller.item;
               var promise = seller_service.createOrUpdate(submitform);
               promise.then(function (data) {  // 调用承诺API获取数据 .resolve
       		   Metronic.unblockUI();
       		      if(data.data.status=='success'){
                       toastr['success']("您的信息已保存成功！", "")
                       seller.searchN();
                       $modalInstance.dismiss('cancel');  
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


MetronicApp.controller('seller-edit_controller', function($rootScope, $scope,$state,$stateParams,seller_service,alertService,filterFilter,$modal, $http, $timeout) {
    $scope.$on('$viewContentLoaded', function() {
        // initialize core components
        Metronic.initAjax();
        ComponentsPickers.init();
       
    });
    var seller = {};
    $scope.seller = seller;
    var id= $stateParams.id;
	seller.item={};
    if(!isNull(id)){
       seller_service.loadById(id).then(function (res) {
            //初始化变量
           seller.item = res
        });
    }
	
	 $scope.edit=function(d){
        $state.go('seller-edit',{id: d.id});
    }


    // 增加修改保存
    // 增加修改保存
    seller.save = function(action) {
	   Metronic.blockUI();
        var submitform={};
        submitform.item = seller.item;
        var promise = seller_service.createOrUpdate(submitform);
        promise.then(function (data) {  // 调用承诺API获取数据 .resolve
		   Metronic.unblockUI();
		      if(data.data.status=='success'){
                toastr['success']("您的信息已保存成功！", "")
                 seller.item={}
                if(action==1){
                    $state.go('seller-list');
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
