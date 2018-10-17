'use strict';

MetronicApp.controller('user_controller', function($scope,$rootScope,$state, $modal,$http, $stateParams, user_service, org_service, alertService, changliang_service, $location, filterFilter) {
    $scope.$on('$viewContentLoaded', function () {//provinces_service,citys_service,area_service,worker_service,
        // initialize core components
        Metronic.initAjax();
        ComponentsPickers.init();
    });


    //begin-本业务的全局变量
    var user = {};
    $scope.user = user;
    user.item={};
    //初始化controller  TODO
    initController($scope, user, 'user',user_service, filterFilter);// changliang_service,

    user.searchForm.searchItems = [
        {"name": "category_id", "code": "eq"}
    ];
    user.searchForm.orderGuize="createTime desc";
    user.searchForm.fn="203";

    user.userAdd=function(){
        $state.go('user-add',{id: '0'});
    }
    //初始化二级菜单
   // $scope.menu7Click('subMenu3');

    //获取部门和岗位信息
    var promise = org_service.getByOrgtype({});
    promise.then(function (data) {  // 调用承诺API获取数据 .resolve
        user.deptList = data.deptList;
//            user.postList = data.postList;
    }, function (data) {  // 处理错误 .reject
    });


    $scope.$watch('user.item.department_id',function(){
        if(user.item==undefined||isNull(user.item.department_id)){
            return;
        }
        org_service.queryPostByDept(user.item.department_id).then(function(data){
            user.postList = data.postList;
        })
    });


    $scope.change=function(){
        var s=[];
        if(user.item.city1==true){
            s.push("全国");
        }
        if(user.item.city2==true){
            s.push("上海市");
        }
        if(user.item.city3==true){
            s.push("杭州市");
        }
        if(user.item.city4==true){
            s.push("北京市");
        }
        if(user.item.city5==true){
            s.push("天津市");
        }
        if(user.item.city6==true){
            s.push("广州市");
        }
        if(user.item.city7==true){
            s.push("南京市");
        }
        if(user.item.city8==true){
            s.push("义乌市");
        }
        if(user.item.city9==true){
            s.push("宁波市");
        }
        var da='';
        for(var i=0;i< s.length;i++){
            if(i==s.length-1){
                da+=s[i];
            }else{
                da+=s[i]+",";
            }
        }
        return  da;
    }

    var  changebutte=function(d){
        var s= d.split(",");
        for(var i=0;i< s.length;i++){
            if(s[i]=='全国'){
                user.item.city1=true
            }
            if(s[i]=='上海市'){
                user.item.city2=true
            }
            if(s[i]=='杭州市'){
                user.item.city3=true
            }
            if(s[i]=='北京市'){
                user.item.city4=true
            }
            if(s[i]=='天津市'){
                user.item.city5=true
            }
            if(s[i]=='广州市'){
                user.item.city6=true
            }
            if(s[i]=='南京市'){
                user.item.city7=true
            }
            if(s[i]=='义乌市'){
                user.item.city8=true
            }
            if(s[i]=='宁波市'){
                user.item.city9=true
            }
        }
    }

    var worker = {};
    $scope.worker = worker;
    worker.list=[];
    worker.typeList=[];
    worker.item={};

    //        增加 修改开始
    var id = $stateParams.id;
    //alert(id);
    if (id !=0 && id != undefined) {
        //标记状态   新建还是修改
        $scope.status=0;
        var promise = user_service.loadById(id);
        promise.then(function (res) {
            //初始化变量
            user.item = res.item;
            user.addson = res.addson;
            worker.list=res.list;
            worker.typeList=res.typeList;
            changebutte(user.item.kejian_city);


        });
    }else{
        //标记状态   新建还是修改
        $scope.status=1;
        user.item={};
       /* worker_service.worker_typeList().then(function(data){
            worker.typeList=data.list;
        });*/
    }


 /*   $scope.quyuadd=function(){
        openwindow($modal,"quyu_checked.html","lg",function($scope,$modalInstance){
            var project = {};
            $scope.project = project;
            //查询 省份    province_service    下拉所有正常的省份
            provinces_service.findAll("",0).then(function(data){
                project.provinceList=data.list;
            })

            $scope.initCity=function(d){
                project.item.city_id="";
                project.item.region_id="";
                if(project.item.province_id!=""){
                    for(var i=0;i<project.provinceList.length;i++){
                        if(project.provinceList[i].code==project.item.province_id){
                            project.item.province_name= project.provinceList[i].name;
                        }
                    }
                }else{
                    project.item.province_name="";
                }

                //查询 城市
                citys_service.findAll(project.item.province_id,0).then(function(data){
                    project.cityList=data.list;
                });
            }
            $scope.initRegoin=function(d){
                project.item.region_id="";

                if(project.item.city_id!=""){
                    for(var i=0;i<project.cityList.length;i++){
                        if(project.cityList[i].code==project.item.city_id){
                            project.item.city_name= project.cityList[i].name;
                        }
                    }
                }else{
                    project.item.city_name="";
                }
                //查询  地区
                area_service.findAll(project.item.city_id,0).then(function(data){
                    project.regoinList=data.list;
                });
            }

            $scope.initArea=function(d){
                //project.item.region_name= d.name;
                if(project.item.region_id!=""){
                    for(var i=0;i<project.regoinList.length;i++){
                        if(project.regoinList[i].code==project.item.region_id){
                            project.item.area_name= project.regoinList[i].name;
                        }
                    }
                }else{
                    project.item.area_name="";
                }

            }

            $scope.submit=function(){
                if(worker.list.length>0){
                    for(var q=0;q<worker.list.length;q++){
                        if(project.item.region_id==worker.list[q].region_id&&project.item.departure==worker.list[q].departure){//重复了
                            break;
                        }
                        if(q+1==worker.list.length){
                            var su=angular.copy(project.item);
                            su.id="0";
                            su.delstatus='0';
                            worker.list.push(su);
                        }
                    }
                }else{
                    var su=angular.copy(project.item);
                    su.id="0";
                    su.delstatus='0';
                    worker.list.push(su);
                }
                $modalInstance.dismiss();
            }

            $scope.cancel=function(){
                $modalInstance.dismiss();
            }
        })
    }*/

    $scope.fujiandelete=function(d,status){
        if(!isNull(user.item.id)){

            var submitform={};
            submitform.item = user.item;
            submitform.selectedItems=[];
            submitform.selectedItems.push(d);
            submitform.status=status;
            var promise=$http.post(baseUrl+'/gongrenguanli/user/deletestatus',submitform);
            promise.then(function(data){
                var promise = user_service.loadById(id);
                promise.then(function (res) {
                    //初始化变量
                    user.item = res.item;
                    user.addson = res.addson;
                    worker.list=res.list;
                    worker.typeList=res.typeList;
                });
            },function(err){

            });
        }else{
            //还没提交   从worker.list移除  d对象
            var  s_data=[];
            for(var q=0;q<worker.list.length;q++){
                if(d.departure==worker.list[q].departure&&d.region_id==worker.list[q].region_id){
                    //continue;
                }else{
                    s_data.push(worker.list[q]);
                }

            }
            worker.list=s_data;
        }
    }

    // 增加修改保存
    user.save = function(action) {
        var submitform={};
        submitform.status=$scope.status;

        submitform.list=worker.list;
        submitform.typeList=worker.typeList;

        submitform.item = user.item;
        var  s=$scope.change();
        submitform.item.kejian_city=s;
        if(user.fileflowname!=undefined){
            user.item.files = [];
            initUploadFiles(user.fileflowname.files,user.item.files);
        }
        submitform.files = user.item.files;
        var promise = user_service.createOrUpdate(submitform);
        promise.then(function (data) {  // 调用承诺API获取数据 .resolve
            alertService.clearAlert();
            alertService.add(data.data.status,data.data.message);
            if(action==0){
                $location.path('user-list/0');
            }else if(action==1){
                user.item={};
            }else if(action==3){
            	window.history.back();
            }
        }, function (data) {  // 处理错误 .reject
            alertService.clearAlert();
            alertService.add('error',data.data.message);
        });
    }


    // 增加修改保存
    user.save12 = function(action) {
        var submitform={};
        submitform.status=$scope.status;

        submitform.list=worker.list;
        submitform.typeList=worker.typeList;

        submitform.item = user.item;
        if(user.fileflowname!=undefined){
            user.item.files = [];
            initUploadFiles(user.fileflowname.files,user.item.files);
        }
        submitform.files = user.item.files;
        var promise = user_service.createOrUpdate(submitform);
        promise.then(function (data) {  // 调用承诺API获取数据 .resolve
            alertService.clearAlert();
            alertService.add(data.data.status,data.data.message);
            if(action==0){
                $location.path('user-detail1/'+user.item.id);
            }else if(action==1){
                user.item={};
            }
        }, function (data) {  // 处理错误 .reject
            alertService.clearAlert();
            alertService.add('error',data.data.message);
        });
    }




    //添加部门,岗位
    user.add = function(org_type){
        user.item = {};
        user.item.org_type=org_type;
        user.detail();
    }
    user.detail=function(){
        var templateUrl= '';
        if(user.item.org_type=='dept'){
            templateUrl= 'department-add.html';
        }else if(user.item.org_type=='post'){
            templateUrl= 'position-add.html';
        }
        if(templateUrl==''){
            return;
        }
        //获取部门和岗位信息
        var promise = org_service.getByOrgtype({});
        promise.then(function (data) {  // 调用承诺API获取数据 .resolve
            user.deptList = data.deptList;
            user.postList = data.postList;
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
            $scope.user = user;
            $scope.submit = function () {
                var submitForm={};
                submitForm.item = user.item;
                var promise  = org_service.createOrUpdate(submitForm);
                promise.then(function (data) {  // 调用承诺API获取数据 .resolve
                    alertService.clearAlert();
                    alertService.add(data.data.status,data.data.message);
                    $modalInstance.dismiss('cancel');
                }, function (data) {  // 处理错误 .reject
                    alertService.clearAlert();
                    alertService.add('error',data.data.message);
                });
            }
            $scope.cancel = function () {
                $modalInstance.dismiss('cancel');
            };
        }
    }
    user.searchByType=function(type){
        if(type!='all'){
            user.searchForm.category_id=type;
        }else{
            user.searchForm.category_id='';
        }
        user.search1();
    }
    user.deleteData=function(item){
//            if(!$scope.authbutton(fnid, e)) return false;
        var selectedItems = [];
        selectedItems.push(item);
        var res = confirm("删除是不可恢复的，你确认要删除吗？");
        if (res) {
            var promise = user_service.delete(selectedItems);
            promise.then(function (data) {
                if(data.data.status=='error'|| data.data.status=='warning'){
                    alertService.clearAlert();
                    alertService.add(data.data.status,data.data.message);
                    return;
                }else{
                    alertService.clearAlert();
                    alertService.add(data.data.status,data.data.message);
                    user.search1();
                }
            });
        }
    }
    //        执行数据初始化
    var type = $stateParams.type;
    if(type==0){
        user.searchForm.status="0"
        user.search1();
    }
    //        执行数据初始化结束
    //-end功能结束
});



MetronicApp.controller('user_edit_controller', function($scope,$rootScope, $modal, $stateParams, alertService, user_service,changliang_service, $location, filterFilter) {
    $scope.$on('$viewContentLoaded', function () {
        // initialize core components
        Metronic.initAjax();
        ComponentsPickers.init();
    });

    var id =  $stateParams.id;
    var user = {};
    $scope.user = user;
    user.chang={};
    user.getchangliang = function(fenlei){
        if(user.chang[fenlei]==undefined){
            changliang_service.getchangliang(fenlei).then(function(data){
                user.chang[fenlei]=data;
            });
        }
    }

    if(id!='0'){
        var promise=  user_service.loadById(id);
        promise.then(function(data){
            user.item=data;
        });
    }







    $scope.submit = function () {
        user.item.files=[];
        initUploadFiles( user.fileflow.files, user.item.files);
        var promise = user_service.createOrUpdate(user.item);
        promise.then(function (data) {  // 调用承诺API获取数据 .resolve
            alertService.clearAlert();
            alertService.add(data.data.status,data.data.message);
            $location.path("user-list");
        }, function (data) {  // 处理错误 .reject
            alertService.clearAlert();
            alertService.add('error','提交失败！');
        });
    };
    $scope.cancel = function () {
        $location.path("user-list");
    };
});

