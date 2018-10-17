/**
 * Created by su.zhang on 2014/8/6.
 */
//angular.module('nglanmo.todotask_controller', []).controller('todotask_controller', ['$scope','$rootScope', '$modal', '$routeParams', 'todotask_service','process_service', 'changliang_service','alertService', '$location', 'filterFilter',
    MetronicApp.controller('todotask_controller',   function ($scope,$rootScope, $modal,$stateParams ,/*$routeParams,*/ todotask_service,process_service, changliang_service,alertService, $location, filterFilter) {
        //begin-本业务的全局变量
        var todotask = {};
        $scope.todotask = todotask;

        /*$scope.menu6Click('subMenu5');*/
        //初始化controller
        initController($scope,todotask,'todotask',changliang_service,todotask_service,filterFilter,$location,alertService,$modal);
        todotask.searchForm.searchItems=[
            {"name": "lianxiren", "code": "lk"},
            {"name": "lianxidianhua", "code": "lk"},
            {"name": "kehumingcheng", "code": "lk"},
            {"name": "zhuangtai", "code": "eq"},

            {"name": "mobanmingcheng", "code": "lk"},
            {"name": "faqizhe", "code": "lk"},
            {"name": "zhixingzhe", "code": "lk"}

        ];
        todotask.searchForm.orderGuize="createTime desc";
        todotask.searchForm.zhuangtai='0';
        //缓存start
        if($rootScope.todotask1!=undefined){
            todotask.searchForm=$rootScope.todotask1;
        }
        $scope.$watch('todotask.searchForm',function(newVal,oldVal,scope){
            $rootScope.todotask1=todotask.searchForm;
        },true);
        //缓存end

        todotask.state='1';

        var type=$stateParams.type;
        if(type==0){
            todotask.searchN = function search() {//查询第N页
                var pageData = todotask_service.queryyido(todotask.searchForm);
                pageData.then(function (data) {  // 调用承诺API获取数据 .resolve
                    todotask.searchForm.page = data.page;
                    todotask.dataList = data.list;
                }, function (data) {  // 处理错误 .reject
                    alert("服务端返回错误");
                });
            }
        }

//        执行数据初始化
        todotask.search1();
//        执行数据初始化结束


        todotask.changeCondition2=function(){
            var conditionValues = [];
            if(todotask.searchForm.searchItemName=='yewuleixing_select'){
                conditionValues.push({"name":"采购订单","value":"33"});
                conditionValues.push({"name":"合同订单","value":"13"});
                conditionValues.push({"name":"报价单","value":"73"});
                conditionValues.push({"name":"询价单","value":"28"});
               // todotask.changeCondition(conditionValues);
            }else if(todotask.searchForm.searchItemName=='zhuangtai_select'){
                conditionValues.push({"name":"待办","value":"0"});
                conditionValues.push({"name":"已结束","value":"1"});
                //todotask.changeCondition(conditionValues);
            }
            todotask.changeCondition(conditionValues);
        }





        todotask.forward = function (data) {
            var selectedItems = [];
            selectedItems.push(data);
           /* var res = confirm("确认审核通过吗？");
            if (res) {   }*/
                openwindow($modal,"views/window/messge.html","",function($scope,$modalInstance){
                    var datamessge={};
                    $scope.datamessge=datamessge;
                    $scope.submit=function(){
                        $rootScope.loadById = true;
                        var promise = process_service.handle(data.zhuti,data.biaodanid,"同意:"+datamessge.messge,"forward");
                        promise.then(function (data) {
                            $rootScope.loadById = false;
                            if(data.data.status=='error'|| data.data.status=='warning'){
                                alertService.clearAlert();
                                alertService.add(data.data.status,data.data.message);
                                return;
                            }else{
                                alertService.clearAlert();
                                alertService.add(data.data.status,data.data.message);
                                todotask.searchN();
                            }
                        });
                        $modalInstance.dismiss();
                    }
                    $scope.cancel=function(){
                        $modalInstance.dismiss();
                    }
                });
        }


        todotask.back = function (data) {
            var selectedItems = [];
            selectedItems.push(data);
            var res = confirm("确认审核不通过吗？");
            if (res) {
                var promise = process_service.handle(data.zhuti,data.biaodanid,"不同意","back");
                promise.then(function (data) {
                    if(data.data.status=='error'|| data.data.status=='warning'){
                        alertService.clearAlert();
                        alertService.add(data.data.status,data.data.message);
                        return;
                    }else{
                        alertService.clearAlert();
                        alertService.add(data.data.status,data.data.message);
                        todotask.searchN();
                    }
                   /* alert('回退成功');
                    todotask.searchN();*/
                });
            }
        }
        todotask.terminate = function (data) {
            var selectedItems = [];
            selectedItems.push(data);
           /* var res = confirm("确认终结流程吗？");
            if (res) { }*/

            openwindow($modal,"views/window/messge.html","",function($scope,$modalInstance){
                var datamessge={};
                $scope.datamessge=datamessge;
                $scope.submit=function(){
                    $rootScope.loadById = true;
                    var promise = process_service.handle(data.zhuti,data.biaodanid,"终结:"+datamessge.messge,"terminate");
                    promise.then(function (data) {
                        $rootScope.loadById = false;
                        if(data.data.status=='error'|| data.data.status=='warning'){
                            alertService.clearAlert();
                            alertService.add(data.data.status,data.data.message);
                            return;
                        }else{
                            alertService.clearAlert();
                            alertService.add(data.data.status,data.data.message);
                            todotask.searchN();
                        }
                    });
                    $modalInstance.dismiss();
                }
                $scope.cancel=function(){
                    $modalInstance.dismiss();
                }
            });
        }
    })



//angular.module('nglanmo.todotask_success_controller', []).controller('todotask_success_controller', ['$scope','$rootScope', '$modal', '$routeParams', 'todotask_service','process_service', 'changliang_service','alertService', '$location', 'filterFilter',
    MetronicApp.controller('todotask_success_controller', function ($scope,$rootScope, $modal, $stateParams,/*$routeParams,*/ todotask_service,process_service, changliang_service,alertService, $location, filterFilter) {
        //begin-本业务的全局变量
        var todotask = {};
        $scope.todotask = todotask;

       /* $scope.menu6Click('subMenu6');*/
        //初始化controller
        initController($scope,todotask,'todotask',changliang_service,todotask_service,filterFilter,$location,alertService,$modal);
        todotask.searchForm.searchItems=[
            {"name": "lianxiren", "code": "lk"},
            {"name": "lianxidianhua", "code": "lk"},
            {"name": "kehumingcheng", "code": "lk"},
            {"name": "zhuangtai", "code": "eq"}

        ];
        todotask.searchForm.orderGuize="createTime desc";
        todotask.searchForm.zhuangtai='1';
        //缓存start
        if($rootScope.todotask2!=undefined){
            todotask.searchForm=$rootScope.todotask2;
        }
        $scope.$watch('todotask.searchForm',function(newVal,oldVal,scope){
            $rootScope.todotask2=todotask.searchForm;
        },true);

        //缓存end

        todotask.state='0';

        var type=$stateParams.type;
        if(type==0){
            todotask.searchN = function search() {//查询第N页
                var pageData = todotask_service.queryyido(todotask.searchForm);
                pageData.then(function (data) {  // 调用承诺API获取数据 .resolve
                    todotask.searchForm.page = data.page;
                    todotask.dataList = data.list;
                }, function (data) {  // 处理错误 .reject
                    alert("服务端返回错误");
                });
            }
        }

//        执行数据初始化
        todotask.search1();
//        执行数据初始化结束


        todotask.changeCondition2=function(){
            var conditionValues = [];
            if(todotask.searchForm.searchItemName=='yewuleixing_select'){
                conditionValues.push({"name":"采购订单","value":"33"});
                conditionValues.push({"name":"合同订单","value":"13"});
                conditionValues.push({"name":"报价单","value":"73"});
                conditionValues.push({"name":"询价单","value":"28"});
                // todotask.changeCondition(conditionValues);
            }else if(todotask.searchForm.searchItemName=='zhuangtai_select'){
                conditionValues.push({"name":"待办","value":"0"});
                conditionValues.push({"name":"已结束","value":"1"});
                //todotask.changeCondition(conditionValues);
            }
            todotask.changeCondition(conditionValues);
        }





        todotask.forward = function (data) {
            var selectedItems = [];
            selectedItems.push(data);
            /* var res = confirm("确认审核通过吗？");
             if (res) {   }*/
            openwindow($modal,"views/messge.html","",function($scope,$modalInstance){
                var datamessge={};
                $scope.datamessge=datamessge;
                $scope.submit=function(){
                    $rootScope.loadById = true;
                    var promise = process_service.handle(data.zhuti,data.biaodanid,"同意:"+datamessge.messge,"forward");
                    promise.then(function (data) {
                        $rootScope.loadById = false;
                        if(data.data.status=='error'|| data.data.status=='warning'){
                            alertService.clearAlert();
                            alertService.add(data.data.status,data.data.message);
                            return;
                        }else{
                            alertService.clearAlert();
                            alertService.add(data.data.status,data.data.message);
                            todotask.searchN();
                        }
                    });
                    $modalInstance.dismiss();
                }
                $scope.cancel=function(){
                    $modalInstance.dismiss();
                }
            });
        }


        todotask.back = function (data) {
            var selectedItems = [];
            selectedItems.push(data);
            var res = confirm("确认审核不通过吗？");
            if (res) {
                var promise = process_service.handle(data.zhuti,data.biaodanid,"不同意","back");
                promise.then(function (data) {
                    if(data.data.status=='error'|| data.data.status=='warning'){
                        alertService.clearAlert();
                        alertService.add(data.data.status,data.data.message);
                        return;
                    }else{
                        alertService.clearAlert();
                        alertService.add(data.data.status,data.data.message);
                        todotask.searchN();
                    }
                    /* alert('回退成功');
                     todotask.searchN();*/
                });
            }
        }
        todotask.terminate = function (data) {
            var selectedItems = [];
            selectedItems.push(data);
            /* var res = confirm("确认终结流程吗？");
             if (res) { }*/

            openwindow($modal,"views/messge.html","",function($scope,$modalInstance){
                var datamessge={};
                $scope.datamessge=datamessge;
                $scope.submit=function(){
                    $rootScope.loadById = true;
                    var promise = process_service.handle(data.zhuti,data.biaodanid,"终结:"+datamessge.messge,"terminate");
                    promise.then(function (data) {
                        $rootScope.loadById = false;
                        if(data.data.status=='error'|| data.data.status=='warning'){
                            alertService.clearAlert();
                            alertService.add(data.data.status,data.data.message);
                            return;
                        }else{
                            alertService.clearAlert();
                            alertService.add(data.data.status,data.data.message);
                            todotask.searchN();
                        }
                    });
                    $modalInstance.dismiss();
                }
                $scope.cancel=function(){
                    $modalInstance.dismiss();
                }
            });
        }
    })
