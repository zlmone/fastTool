'use strict';

MetronicApp.controller('org_controller', function($scope,$rootScope,$compile,$state, $modal, $stateParams,alertService, org_service, changliang_service, $location, filterFilter) {
    $scope.$on('$viewContentLoaded', function () {
        // initialize core components
        Metronic.initAjax();
        ComponentsPickers.init();
    });
    //begin-本业务的全局变量
    var org = {};
    $scope.org = org;

    //初始化二级菜单
   // $scope.menu7Click('subMenu3');

    org.userAdd=function(){
        $state.go('user-add',{id: '0'});
    }


    var deptTree = {};

    function listOrg(){
        var treepromise = org_service.listDept();
        treepromise.then(function (data) {
            var orgList=data.data
            if(!isNull(orgList)){
                for(var i=0;i< orgList.length;i++){
                    orgList[i].open=true
                }
            }
            /* function beforeEditName(treeId, treeNode) {
             org.edit(treeNode.id);
             }
             function beforeRemove(treeId, treeNode) {
             return confirm("确认删除 " + treeNode.name + " 吗？");
             }
             function onRemove(e, treeId, treeNode) {
             org.deleteOrg(treeNode.id,treeNode.type);
             }*/

            function addHoverDom(treeId, treeNode) {
                if ($("#edit_"+treeNode.id).length>0) return;
                if ($("#delete_"+treeNode.id).length>0) return;
                var aObj = $("#" + treeNode.tId + IDMark_A);
                var editStr='';
                editStr += "<a id='edit_"+treeNode.id +"'"+" style='margin:0 0 0 5px;color:blue;' " +" ng-click=org.edit(" +"'"+ treeNode.id+"'"+ ")" + ">"+"修改"+"</a>";
                editStr += "<a id='delete_"+treeNode.id +"'"+" style='margin:0 0 0 5px;color:blue;' "+" ng-click=org.deleteOrg(" +"'"+ treeNode.id+"'," +"'"+treeNode.type+"',"+"'"+treeNode.name+"'" + ")" + ">"+"删除"+"</a>";
//                    aObj.append(editStr);
                aObj.append(
                    $compile(editStr)($scope)
                );
            }
            function removeHoverDom(treeId, treeNode) {
                $("#edit_"+treeNode.id).unbind().remove();
                $("#delete_"+treeNode.id).unbind().remove();
            }
            function addDiyDom(treeId, treeNode){

            }
            function beforeDrag(treeId, treeNodes) {
                for (var i=0,l=treeNodes.length; i<l; i++) {
                    var dragId = treeNodes[i].id;
                    if (treeNodes[i].drag === false){
                        return false;
                    }
                }
                return true;
            }
            function beforeDrop(treeId, treeNodes, targetNode, moveType) {
                if(treeNodes[0].pid == targetNode.pid){
                    return true;
                }else{
                    alert("只能进行同级排序！");
                    return false;
                }
            }
            function zTreeOnDrop(event, treeId, treeNodes, targetNode, moveType, isCopy){
                var parentNode = treeNodes[0].getParentNode();
                var nodes;
                if(!isNull(parentNode)){
                    //拿到所有子节点
                    nodes = parentNode.children;
                }else{
                    //获得树对象
                    var treeLeftObj = $.fn.zTree.getZTreeObj(treeId);
                    //拿到所有节点
                    nodes = treeLeftObj.getNodes();
                }
                //保存顺序
                org_service.dragSqueue(nodes).then(function(data){
                    alertService.clearAlert();
                    alertService.add(data.status,data.message);
                },function(data){
                    alertService.add("error","排序失败!");
                });
            }
            deptTree=initZtrees("deptTree",orgList,addHoverDom,removeHoverDom,addDiyDom,null,beforeDrag,beforeDrop,zTreeOnDrop);

        });
    }

    listOrg();
    //初始化controller
    initController($scope, org, 'org',  org_service, filterFilter);//changliang_service,
    org.searchForm.searchItems = [
        {"name": "wuliaomingcheng", "code": "lk"},
        {"name": "wuliaobianma", "code": "lk"}
    ];
    org.searchForm.orderGuize="createTime desc";
    org.searchForm.fn='711';

    //        增加 修改开始
    var id = $stateParams.id;
    if (id != 0 && id != undefined) {
        var promise = org_service.loadById(id);
        promise.then(function (res) {
            //初始化变量
            org.item = res;
        });
    }
    // 增加修改保存
    org.save = function() {
        var submitform={};
        submitform.item = org.item;
        var promise = org_service.createOrUpdate(submitform);
        promise.then(function (data) {  // 调用承诺API获取数据 .resolve
            alertService.clearAlert();
            alertService.add(data.data.status,data.data.message);
            $location.path('org-list/0');
        }, function (data) {  // 处理错误 .reject
            alertService.clearAlert();
            alertService.add("error","提交失败！");
        });
    }
    //添加部门,岗位
    org.add = function(org_type){
        org.item = {};
        org.item.org_type=org_type;
        org.detail();
    }
    org.edit = function(id){
        if(!$rootScope.auth('2045')){
            toastr["warning"]("没有权限","warning");
            return false;
        }

        var pro = org_service.loadById(id);
        pro.then(function(data){ // 调用承诺API获取数据 .resolve
            org.item= data;
            org.detail();
        }, function (data) {  // 处理错误 .reject
        });
    }
    org.detail=function(){
        var templateUrl= '';
        if(org.item.org_type=='dept'){
            templateUrl= 'department-add.html';
        }else if(org.item.org_type=='post'){
            templateUrl= 'position-add.html';
        }
        if(templateUrl==''){
            return;
        }
        //获取部门和岗位信息
        var promise = org_service.getByOrgtype({});
        promise.then(function (data) {  // 调用承诺API获取数据 .resolve
            org.deptList = data.deptList;
            org.postList = data.postList;
        }, function (data) {  // 处理错误 .reject
        });
        var modalInstance = $modal.open({
            templateUrl: templateUrl,
            controller: modelCtl,
            windowClass: 'app-modal-window-detail',
            backdrop: true,
            resolve: {
            }
        });
        modalInstance.result.then(function () {
        }, function () {

        });
        function modelCtl($scope, $modal, $modalInstance) {
            $scope.org = org;
            $scope.submit = function () {
                var submitForm={};
                submitForm.item = org.item;
                var promise  = org_service.createOrUpdate(submitForm);
                promise.then(function (data) {  // 调用承诺API获取数据 .resolve
                    alertService.clearAlert();
                    alertService.add(data.data.status,data.data.message);
                    $modalInstance.dismiss('cancel');
                    listOrg();
                }, function (data) {  // 处理错误 .reject
                    alertService.clearAlert();
                    alertService.add("error","提交失败！");
                });
            }
            $scope.cancel = function () {
                $modalInstance.dismiss('cancel');
            };
        }
    }

    //删除部门，岗位
    org.deleteOrg=function(id,org_type,name){
        if(!$rootScope.auth('2046')){
            /*alert("没有权限");*/
            toastr["warning"]("没有权限","warning");
            return false;
        }

        var message = name;
        if(org_type=='post'){
            message+='岗位及其下属的用户';
        }
        if(org_type=='dept'){
            message+='部门及其下属的用户';
        }
        if(confirm("确认删除" + message + "吗？")){
            var submitForm = {};
            submitForm.id =id;
            submitForm.org_type= org_type;
            var promise = org_service.deleteOrg(submitForm);
            promise.then(function(data){
                alertService.clearAlert();
                alertService.add(data.data.status,data.data.message);
                listOrg();
            });
        }
    }
    //        执行数据初始化
    var type = $stateParams.type;
    if(type==0){
        org.search1();
    }
    //        执行数据初始化结束
    //-end功能结束

});

MetronicApp.controller('org_role_controller', function($scope,$rootScope,$state, $compile, $modal, $stateParams, alertService,org_service, permission_service,changliang_service, $location, filterFilter) {
    $scope.$on('$viewContentLoaded', function () {
        // initialize core components
        Metronic.initAjax();
        ComponentsPickers.init();
    });

    var org = {};
    $scope.org = org;
    org.dataRoles =dataRoles;



    //初始化二级菜单
  //  $scope.menu7Click('subMenu4');

    var postTree = {};

    org.userAdd=function(){
        $state.go('user-add',{id: '0'});
    }

    //创建岗位树
    function listPost(){
        var treepromise = org_service.listPost();
        treepromise.then(function (data) {
            var postList=data.data;
            if(!isNull(postList)){
                for(var i=0;i< postList.length;i++){
                    postList[i].open=true;
                }
            }
            /* function beforeEditName(treeId, treeNode) {
             org.edit(treeNode.id);
             }
             function beforeRemove(treeId, treeNode) {
             return confirm("确认删除 " + treeNode.name + " 吗？");
             }
             function onRemove(e, treeId, treeNode) {
             org.deleteOrg(treeNode.id,treeNode.type);
             }*/
            function addHoverDom(treeId, treeNode) {
                if ($("#edit_"+treeNode.id).length>0) return;
                if ($("#delete_"+treeNode.id).length>0) return;
                if ($("#permission_"+treeNode.id).length>0) return;
                if ($("#extends_permission_"+treeNode.id).length>0) return;
                var aObj = $("#" + treeNode.tId + IDMark_A);
                var editStr='';
                editStr += "<a id='edit_"+treeNode.id +"'"+" style='margin:0 0 0 5px;color:blue;' "+" ng-click=org.edit(" +"'"+ treeNode.id+"'"+ ")" + ">"+"修改"+"</a>";
                editStr += "<a id='delete_"+treeNode.id +"'"+" style='margin:0 0 0 5px;color:blue;' "+" ng-click=org.deleteOrg(" +"'"+ treeNode.id+"'," +"'"+treeNode.type+"',"+"'"+treeNode.name+"'" + ")" + ">"+"删除"+"</a>";
                editStr += "<a id='permission_"+treeNode.id +"'"+" style='margin:0 0 0 5px;color:blue;' " +" ng-click=org.permission(" +"'"+ treeNode.id+"',"+"'"+treeNode.name+"'" + ")" + ">"+"授权"+"</a>";
//                    editStr += "<a id='extends_permission_"+treeNode.id +"'"+" style='margin:0 0 0 5px;color:blue;' "+" ng-click=org.extends_permission(" +"'"+ treeNode.id+"'," +"'"+treeNode.type+"',"+"'"+treeNode.name+"'" + ")" + ">"+"权限继承"+"</a>";
//                    aObj.append(editStr);
                aObj.append(
                    $compile(editStr)($scope)
                );
            }
            function removeHoverDom(treeId, treeNode) {
                $("#edit_"+treeNode.id).unbind().remove();
                $("#delete_"+treeNode.id).unbind().remove();
                $("#permission_"+treeNode.id).unbind().remove();
                $("#extends_permission_"+treeNode.id).unbind().remove();
            }
            function addDiyDom(treeId, treeNode){
                if(treeNode.users!=undefined&&treeNode.users.length>0){
                    var aObj = $("#" + treeNode.tId + IDMark_A);
                    var userlink='';
                    for(var i=0;i<treeNode.users.length;i++){
                        userlink += "<a href='#user-detail/"+treeNode.users[i].id+ "'>"+ treeNode.users[i].value_name+"</a>"
                    }
                    userlink = '('+userlink +')';
                    aObj.append(userlink);
                }
            }
            function beforeDrag(treeId, treeNodes) {
                for (var i=0,l=treeNodes.length; i<l; i++) {
                    var dragId = treeNodes[i].id;
                    if (treeNodes[i].drag === false){
                        return false;
                    }
                }
                return true;
            }
            postTree=initZtrees("postTree",postList,addHoverDom,removeHoverDom,addDiyDom,null,beforeDrag,null,null);
        });
    }
    listPost();
    //添加部门,岗位
    org.add = function(org_type){
        org.item = {};
        org.item.org_type=org_type;
        org.detail();
    }
    //修改部门,岗位
    org.edit = function(id){
        if(!$rootScope.auth('2045')){
            toastr["warning"]("没有权限","warning");
            return false;
        }
        var pro = org_service.loadById(id);
        pro.then(function(data){ // 调用承诺API获取数据 .resolve
            org.item= data;
            org.detail();
        }, function (data) {  // 处理错误 .reject
        });
    }
    org.detail=function(){
        var templateUrl= '';
        if(org.item.org_type=='dept'){
            templateUrl= 'department-add.html';
        }else if(org.item.org_type=='post'){
            templateUrl= 'position-add.html';
        }
        if(templateUrl==''){
            return;
        }
        //获取部门和岗位信息
        var promise = org_service.getByOrgtype({});
        promise.then(function (data) {  // 调用承诺API获取数据 .resolve
            org.deptList = data.deptList;
            org.postList = data.postList;
        }, function (data) {  // 处理错误 .reject
        });
        var modalInstance = $modal.open({
            templateUrl: templateUrl,
            controller: modelCtl,
            windowClass: 'app-modal-window-detail',
            backdrop: true,
            resolve: {
            }
        });
        modalInstance.result.then(function () {
        }, function () {

        });
        function modelCtl($scope, $modal, $modalInstance) {
            $scope.org = org;
            $scope.submit = function () {
                var submitForm={};
                submitForm.item = org.item;
                var promise  = org_service.createOrUpdate(submitForm);
                promise.then(function (data) {  // 调用承诺API获取数据 .resolve
                    alertService.clearAlert();
                    alertService.add(data.data.status,data.data.message);
                    $modalInstance.dismiss('cancel');
                    listPost();
                }, function (data) {  // 处理错误 .reject
                    alertService.clearAlert();
                    alertService.add("error","提交失败！");
                });
            }
            $scope.cancel = function () {
                $modalInstance.dismiss('cancel');
            };
        }
    }

    //删除部门，岗位
    org.deleteOrg=function(id,org_type,name){
        if(!$rootScope.auth('2046')){
            toastr["warning"]("没有权限","warning");
            return false;
        }
        if(confirm("确认删除 " + name + " 吗？")){
            var submitForm = {};
            submitForm.id =id;
            submitForm.org_type= org_type;
            var promise = org_service.deleteOrg(submitForm);
            promise.then(function(data){
                listPost();
            });
        }
    }
    //授权
    org.permission= function(role_id,role_name){
        if(!$rootScope.auth('2047')){
            toastr["warning"]("没有权限","warning");
            return false;
        }
        var pro = permission_service.getByRole(role_id);
        pro.then(function(data){ // 调用承诺API获取数据 .resolve
            org.functionList= data;
            org.permission_detail(role_id,role_name);
        }, function (data) {  // 处理错误 .reject
        });
    }
    org.permission_detail=function(role_id,role_name){
        var templateUrl= 'permission-add.html';
        var modalInstance = $modal.open({
            templateUrl: templateUrl,
            controller: modelCtl,
            windowClass: 'app-modal-window-permission',
            backdrop: true,
            resolve: {
            }
        });
        modalInstance.result.then(function () {
        }, function () {

        });
        function modelCtl($scope, $modal, $modalInstance) {
            $scope.org = org;
            $scope.submit = function () {
                var submitForm={};
                submitForm.position_id = role_id;
                submitForm.position_name = role_name;
                submitForm.permissionList = getCheckedFun(org.functionList);
                var promise  = permission_service.createOrUpdate(submitForm);
                promise.then(function (data) {  // 调用承诺API获取数据 .resolve
                    alertService.clearAlert();
                    alertService.add(data.data.status,data.data.message);
                    $modalInstance.dismiss('cancel');
                }, function (data) {  // 处理错误 .reject
                    alertService.clearAlert();
                    alertService.add("error","提交失败！");
                });
            }
            $scope.cancel = function () {
                $modalInstance.dismiss('cancel');
            };
        }
    }
    org.extends_permission= function(){

    }
    function getCheckedFun(functionList){
        var results=[];
        for(var i=0;i< functionList.length;i++){
            if(functionList[i].checked) {
                var item = angular.copy(functionList[i]);
                if(item.dataRole==undefined){
                    item.dataRole='0';
                }
                results.push(functionList[i]);
            }
        }
        return results;
    }
    org.checkAll = function (headcheck,module) {
        if (!headcheck) {
            if(org.functionList!=undefined){
                for (var i=0; i<org.functionList.length; i++) {
                    if(org.functionList[i].firstModule==module){
                        org.functionList[i].checked = true;
                    }
                }
            }
        } else {
            if(org.functionList!=undefined){
                for (var i=0; i<org.functionList.length; i++) {
                    if(org.functionList[i].firstModule==module){
                        org.functionList[i].checked = false;
                    }
                }
            }
        }
    }


});

