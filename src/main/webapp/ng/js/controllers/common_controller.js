'use strict';
MetronicApp.controller('common_controller', function($rootScope, $scope,$state,$stateParams,filterFilter,$modal, $http, $timeout) {
    $scope.$on('$viewContentLoaded', function() {
        // initialize core components
        Metronic.initAjax();
        ComponentsPickers.init();
    });
    $scope.login={}
    console.info('进入系统！');
    $scope.initlogin=function(){
        var   promise=   $http.post(baseUrl+'/login/info');
        promise.then(function(data){
            $rootScope.renyuan=data.data.renyuan;
            $rootScope.zuzhi=data.data.zuzhi;
        });
    }
    
//    var   promise=   $http.post(baseUrl+'/login/info');
//    promise.then(function(data){
//        $rootScope.renyuan=data.data.renyuan;
//        $rootScope.zuzhi=data.data.zuzhi;
//    });

/*    function  initquanxian(){quanxianbiao_service,
        var promise= quanxianbiao_service.getResButtonSelf();
        promise.then(function(data){
            $rootScope.mybuttons=data.data;
            $rootScope.auth=function(fnid){
                if($rootScope.renyuan==undefined){
                    return false;
                }
                if($rootScope.renyuan.dengluming=='admin'){
                        return true;
                }
                if($rootScope.mybuttons!=undefined){
                    for(var i=0; i<$rootScope.mybuttons.length; i++){
                        if($rootScope.mybuttons[i]==null){
                            return;
                        }
                        if($rootScope.mybuttons[i].gongnengid==fnid){
                            return true;
                        }
                    }
                }
                return false;
            }
        });
    }*/
    //initquanxian();
    // set sidebar closed and body solid layout mode
    $rootScope.settings.layout.pageBodySolid = true;
    $rootScope.settings.layout.pageSidebarClosed = false;
});

MetronicApp.controller('file_controller', function($rootScope, $scope,$state,$stateParams,filerecord_service,filterFilter,$modal, $http, $timeout,FileUploader) {
    $scope.opens = function (leixing,refid,closecall) {
        openwindow( $modal,'views/window/fileuploadwindow.html','lg',function ($scope, $modalInstance) {
            //begin-本业务的全局变量
            var uploader = $scope.uploader = new FileUploader({
                url: '../ngres/uploadYn',
                formData:[{'leixing':leixing},{'refid':refid}]
            });
            // FILTERS
            uploader.filters.push({
                name: 'customFilter',
                fn: function(item /*{File|FileLikeObject}*/ , options) {
                    return this.queue.length < 10;
                }
            });
            $scope.ok = function () {
                $modalInstance.close();
            };
            $scope.cancel = function () {
                $modalInstance.dismiss('cancel');
                closecall(uploader.queue);
            };
        });
    };


    $scope.open = function (leixing,refid) {
        openwindow( $modal,'views/window/fileuploadwindow.html','lg',function ($scope, $modalInstance) {
            //begin-本业务的全局变量
            var uploader = $scope.uploader = new FileUploader({
                url: '../ngres/upload',
                formData:[{'leixing':leixing},{'refid':refid}]
            });
            // FILTERS
            uploader.filters.push({
                name: 'customFilter',
                fn: function(item /*{File|FileLikeObject}*/ , options) {
                    return this.queue.length < 10;
                }
            });
            $scope.ok = function () {
                $modalInstance.close();
            };
            $scope.cancel = function () {
                $modalInstance.dismiss('cancel');
                queryfiles(leixing,refid)
            };
        });
    };
    var queryfiles=function(leixing,refid){
      var promise=  filerecord_service.query(refid);
        promise.then(function(d){
                $scope.filelist=d
        });
    }

    $scope.queryfiles=queryfiles;
    $scope.delete=function(leixing,refid,id){
        filerecord_service.delete(id).then(function(d){
            toastr['success']("您的信息已删除成功！", "恭喜您！")
            queryfiles(leixing,refid);
        });
    }


    $scope.openwenjiangui = function (closecall) {
        openwindow( $modal,'views/window/fileuploadwindow.html','lg',function ($scope, $modalInstance) {
            //begin-本业务的全局变量
            var uploader = $scope.uploader = new FileUploader({
                url: '../ngres/upload',
                formData:[{'leixing':'wenjiangui'},{'refid':'wenjiangui'}]
            });
            // FILTERS
            uploader.filters.push({
                name: 'customFilter',
                fn: function(item /*{File|FileLikeObject}*/ , options) {
                    return this.queue.length < 10;
                }
            });
            $scope.ok = function () {
                $modalInstance.close();
            };
            $scope.cancel = function () {
                $modalInstance.dismiss('cancel');
                closecall()
            };
        });
    };
    var querywenjiangui=function(){
        var promise=  filerecord_service.query('wenjiangui');
        promise.then(function(d){
            $scope.filelist=d
        });
    }

    $scope.querywenjiangui=querywenjiangui;
    $scope.deletewenjiangui=function(id){
        filerecord_service.delete(id).then(function(d){
            toastr['success']("您的信息已删除成功！", "恭喜您！")
            querywenjiangui();
        });
    }
    var ztree
    var zNodes


  /*  zuzhi_service.listTree({"leixing":["jigou","bumen","gangwei","renyuan"]}).then(function (data) {
        zNodes=data.data;
    })
    $scope.gongxiang= function(d){
        openwindow( $modal,"gongxiang.html",'',function ($scope, $modalInstance) {
            $scope.wenjian = d;
            $scope.zuzhitreeshow=function(da){
                da.show2=true;
                var tmpnodes=angular.copy(zNodes)
                var checkboxtype = { "Y" : " ", "N" : " " };
                ztree=initZtree("zuzhiztree2",tmpnodes,null,true,checkboxtype);
            }
            $scope.zuzhitreeconfirm=function(da){
                var nodes= ztree.getCheckedNodes(true);
                if(nodes.length<1){
                    alert('请选择共享人');
                    return
                }else{
                    var ids="";
                    var names=""
                    for(var i=0;i<nodes.length;i++){
                        ids =ids+ nodes[i].id.split("LANMO")[1]+","
                        names =names+ nodes[i].name+","
                    }
                    d.gongxiangid=ids;
                    d.gongxiangname=names;
                }
                da.show2=false;

            }
            $scope.zuzhitreeclose=function(da){
                da.show2=false;
            }

            $scope.submit = function () {
//                console.log('$scope.suoyouren:'+$scope.suoyouren);
                if(isNull(d.gongxiangname)&&$scope.suoyouren==false){
                    alert("请选择共享人");
                    return;
                }
                if($scope.suoyouren){
                    d.gongxiangname='所有人'
                    d.gongxiangid='suoyouren'
                }
                console.log('共享了：'+d.gongxiangname);
//                submitform.item =d;
//                submitform.edit = true;
                var promise =  filerecord_service.createOrUpdate($scope.wenjian)
                promise.then(function (data) {  // 调用承诺API获取数据 .resolve
                    if(data.data.status == 'error') {
                        toastr['error'](data.data.message, "SORRY！")
                    } else {
                        $modalInstance.dismiss('cancel');
                        toastr['success']("共享成功！", "恭喜您！")
                        querywenjiangui();
                    }
                }, function (data) {  // 处理错误 .reject
                    alert('共享失败');
                });
            };
            $scope.cancel = function () {
                d.gongxiangid='';
                d.gongxiangname='';
                $modalInstance.dismiss('cancel');
            };
        });
    }*/

    $scope.querywenjianguigongxiang=function(){
        var promise=  filerecord_service.querygongxiang();
        promise.then(function(d){
            $scope.filelist=d
        });
    }
    $scope.querywenjianguigongxiangall=function(){
        var promise=  filerecord_service.querygongxiangall();
        promise.then(function(d){
            $scope.filelist=d
        });
    }

});