'use strict';
/* Controllers */
//angular.module('nglanmo.processzhuti_controller', []).controller('processzhuti_controller', ['$scope','$rootScope', '$modal', '$routeParams','alertService','user_service','org_service','processzhuti_service', 'zuzhi_service', 'changliang_service', '$location', 'filterFilter',
MetronicApp.controller('processzhuti_controller', function ($scope,$rootScope, $modal, $stateParams,alertService,user_service,org_service,processzhuti_service, /*zuzhi_service,*/ changliang_service, $location, filterFilter) {
        $scope.$on('$viewContentLoaded', function() {
            // initialize core components
            Metronic.initAjax();
            ComponentsPickers.init();
        });
        //begin-本业务的全局变量
        var processzhuti = {};
        $scope.processzhuti = processzhuti;
        //初始化二级菜单
        //$scope.menu7Click('subMenu1');
        //初始化controller
        initController($scope, processzhuti, 'processzhuti',processzhuti_service, filterFilter);// changliang_service,
        processzhuti.searchForm.searchItems = [
            {"name": "shenpileixing", "code": "eq"}
        ];
        processzhuti.searchForm.ownerType='all';    //查询组织下的所有信息
        processzhuti.searchForm.orderGuize="createTime desc";

        processzhuti.searchForm.fn='107';//功能号

        //        增加 修改开始
        var id = $stateParams.id;
        if (id != 0 && id != undefined) {
            var promise = processzhuti_service.loadById(id);
            promise.then(function (res) {
                //初始化变量
                processzhuti.item = res.item;
                if(processzhuti.list == undefined) {
                    processzhuti.list = [];
                }
                if(res.list!=undefined){
                    for(var i=0; i<res.list.length; i++) {
                        var shenpirenids = res.list[i].shenpirenid.split(",");
                        var shenpirens = res.list[i].shenpiren.split(",");
                        var shenpirenlist = [];
                        var item = {"tiaojian": res.list[i].tiaojian,"shenpirenlist":shenpirenlist};
                       if(shenpirenids!=undefined){
                           for(var j=0; j<shenpirenids.length; j++) {
                               var renyuan = {"shenpirenid": shenpirenids[j],"shenpiren":shenpirens[j]};
                               item.shenpirenlist.push(renyuan);
                           }
                       }
                        processzhuti.list.push(item);
                    }
                }
            });
        }else if(id==0){
            processzhuti.item={};
            processzhuti.item.status='1';

        }


        org_service.listPost().then(function(data){
            processzhuti.postListS=data.data;
        })

        // 增加修改保存
        processzhuti.save = function() {
            if(processzhuti.item == undefined) {
                processzhuti.item = {};
            }
            if(processzhuti.item.shenpileixing == "" ||processzhuti.item.shenpileixing==null) {
                alert("请选择审批类型！");
                return false;
            }
            var submitform={};
            submitform.item = processzhuti.item;
            var list = [];
            if(processzhuti.list == undefined || processzhuti.list.length ==0) {
                alert("请添加审批条件及审批人！");
                return false;
            } else {
                if(processzhuti.list!=undefined){
                    for(var i=0; i<processzhuti.list.length; i++) {
                        var item = {"tiaojian":processzhuti.list[i].tiaojian, "shenpirenid":"", "shenpiren":""};
                        var renyuanlist = processzhuti.list[i].shenpirenlist;
                        if(renyuanlist!=undefined){
                            for(var j=0; j<renyuanlist.length; j++) {
                                item.shenpirenid += renyuanlist[j].shenpirenid+",";
                                item.shenpiren += renyuanlist[j].shenpiren+",";
                            }
                        }
                        item.shenpirenid = item.shenpirenid.substring(0,item.shenpirenid.length-1);
                        item.shenpiren = item.shenpiren.substring(0,item.shenpiren.length-1);
                        list.push(item);
                    }
                }
            }
            submitform.list = list;
            var promise = processzhuti_service.createOrUpdate(submitform);
            promise.then(function (data) {  // 调用承诺API获取数据 .resolve
                alertService.clearAlert();
                alertService.add(data.data.status,data.data.message);
                if(data.data.status=='success'){
                    $location.path('processzhuti-list/0');
                }
            }, function (data) {  // 处理错误 .reject
                alert('提交失败');
            });
        }

        processzhuti.zengjiahang = function() {
            var shenpirenlist = [];
            if(processzhuti.list==undefined) {
                processzhuti.list = [];
            }
            var item = {"tiaojian":"","shenpirenlist":shenpirenlist}
            processzhuti.list.push(item);
        }

        processzhuti.tianjiashenpiren = function(d) {
            var item = {"shenpirenid": "", "shenpiren":""};
            d.shenpirenlist.push(item);
        }

        processzhuti.changeStatus=function(d,status){
            var submitform={};
            submitform.item = d;
            submitform.item.status=status;
            var promise = processzhuti_service.editStatus(submitform);
            promise.then(function (data) {  // 调用承诺API获取数据 .resolve
                alertService.clearAlert();
                alertService.add(data.data.status,data.data.message);
                if(data.data.status=='success'){
                    processzhuti.search1();
                }
            }, function (data) {  // 处理错误 .reject
                alert('提交失败');
            });
        }
        processzhuti.tianjiashenpirens=function(d){
            var modalInstance = $modal.open({
                templateUrl: 'warehouse-add.html',
                controller: modelCtl,
                windowClass: 'app-modal-window-category',
                backdrop: true,
                resolve: {
                }
            });
            modalInstance.result.then(function () {
            }, function () {
            });
            function modelCtl($scope, $modal, $modalInstance) {
                var warehouse={};
                $scope.warehouse = warehouse;
                warehouse.postList = angular.copy(processzhuti.postListS);
                //初始化变量
                if(!isNull(d.shenpirenlist)){
                    for(var i=0; i<warehouse.postList.length; i++){
                        for(var j=0; j<warehouse.postList[i].users.length; j++){
                            for(var k=0; k<d.shenpirenlist.length; k++){
                                if(warehouse.postList[i].users[j].id == d.shenpirenlist[k].shenpirenid){
                                    warehouse.postList[i].users[j].checked=true;
                                    break;
                                }
                            }

                        }
                    }
                }else{
                    d.shenpirenlist=[];
                }
                warehouse.checkPost=function(post){
                    if(post==undefined || post.users==undefined){
                        return;
                    }
                    if(post.checked){
                        for(var i=0; i<post.users.length; i++){
                            post.users[i].checked=false;
                        }
                    }else{
                        for(var i=0; i<post.users.length; i++){
                            post.users[i].checked=true;
                        }
                    }
                }

                $scope.submit = function () {
                    d.shenpirenlist=[];
                for(var i=0; i<warehouse.postList.length; i++) {
                    for (var j = 0; j<warehouse.postList[i].users.length; j++) {
                        if(warehouse.postList[i].users[j].checked){
                            var item = {"shenpirenid":warehouse.postList[i].users[j].id, "shenpiren":warehouse.postList[i].users[j].value_name};
                            d.shenpirenlist.push(item);
                        }
                    }
                }
                    $modalInstance.dismiss('cancel');
                }

                $scope.cancel = function () {
                    $modalInstance.dismiss('cancel');
                };
            }





        }

        processzhuti.openUser=function(p){
            openwindow($modal,"user-list.html","fu",function($scope,$modalInstance){
                var user = {};
                $scope.user = user;
                //初始化controller  TODO
                initController($scope, user, 'user', changliang_service, user_service,filterFilter,$location,alertService,$modal);
                user.searchForm.searchItems = [
                    {"name": "category_id", "code": "eq"}
                ];
                user.searchForm.orderGuize="createTime desc";
                user.search1();

                user.searchByType=function(type){
                    if(type!='all'){
                        user.searchForm.category_id=type;
                    }else{
                        user.searchForm.category_id='';
                    }
                    user.search1();
                }

                user.xuan=function(d){
                    p.shenpirenid = d.id;
                    p.shenpiren = d.value_name;
                    $modalInstance.dismiss();
                }

                $scope.cancel=function(){
                    $modalInstance.dismiss();
                }
            })
        }



        /*processzhuti.openxiaoshouren = function(p) {
            var leixingarr=['jigou','bumen','gangwei','renyuan'];
            var treepromise = zuzhi_service.listTree({"leixing":leixingarr});
            treepromise.then(function (data) {
                processzhuti.treedata =data.data;
            });
            var templateUrl= 'shenpiren.html';
            var modalInstance = $modal.open({
                templateUrl:templateUrl,
                controller: modelCtl,
                windowClass: 'app-modal-window',
                backdrop: false,
                resolve: {
                }
            });
            modalInstance.result.then(function () {
            }, function () {
            });

            function modelCtl($scope, $modal, $modalInstance) {
                $scope.processzhuti=processzhuti;
                $scope.submit = function () {
                };
                $scope.selectNode = function (node) {
                    //选择后的动作
                    p.shenpirenid = node.node.obj.renyuanid
                    p.shenpiren = node.node.obj.mingcheng;
                    $modalInstance.dismiss('cancel');
                };
                $scope.cancel = function () {
                    $modalInstance.dismiss('cancel');
                };
            }
        }*/


        //////////////////////
        //弹出树
  /*      var curinput;
        var ztree
        var zNodes
        var treepromise = org_service.listDepts();
        treepromise.then(function (data) {
            zNodes=data.data;
        });
        var clickcallback = function(event, treeId, treeNode, clickFlag) {
        }
        $scope.zuzhitreeshow=function(da,index){
            curinput=da;
            da.show=true;
            var tmpnodes=angular.copy(zNodes)
            for(var i=0;i<tmpnodes.length;i++){
                if(tmpnodes[i].id==curinput.shenpirenid){
                    tmpnodes[i].checked=true
                }
            }
            var checkboxtype = { "Y" : " ", "N" : " " };
            ztree=initZtree("zuzhiztree"+index,tmpnodes,clickcallback,true,checkboxtype);
        }
        $scope.zuzhitreeclose=function(da){
            var nodes= ztree.getCheckedNodes(true);
            console.log(nodes)
            if(nodes.length!=1){
                alert('请选择一条数据');
                return;
            }else{
                if(nodes[0].type!="renyuan"){
                    alert('请选择一个人员');
                    return;
                }
            }
            da.show=false;
            var allname
            var allid
            for(var i=0;i<nodes.length;i++){
                if(i==0){
                    allname= nodes[i].name;
                    allid= nodes[i].id;
                }else{
                    allname=allname+","+  nodes[i].name
                    allid=allid+","+  nodes[i].id
                }

            }
            curinput.shenpiren=allname
            curinput.shenpirenid=allid
        }*/
        //弹出树结束


       ///////////////////////////////////

        processzhuti.cancel = function() {
            history.go(-1);
        }

        processzhuti.deletehang = function (index) {
            var arrlen=processzhuti.list.length;
            for(var i=arrlen-1; i>=0;i-- ){
                if(index == i){
                    processzhuti.list.splice(i,1);
                    return;
                }
            }
        }
        //        执行数据初始化
        var type = $stateParams.type;
        if(type==0){
            processzhuti.search1();
        }
        //        执行数据初始化结束
    });