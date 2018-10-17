'use strict';
MetronicApp.controller('filerecord_controller', function($rootScope, $scope,$state,$stateParams,filerecord_service2,filerecord_service,/*zuzhi_service,*/filterFilter,$modal, $http, $timeout) {
    $scope.$on('$viewContentLoaded', function() {
        // initialize core components
        Metronic.initAjax();
        ComponentsPickers.init();
    });
    var filerecord = {};
    $scope.filerecord = filerecord;
    var id= $stateParams.id;
    //alert(id);
    //初始化controller
    initController($scope, filerecord, 'filerecord',  filerecord_service2, filterFilter);
    filerecord.searchForm.orderGuize="id desc";
    filerecord.searchForm.searchItems.refid_eq=id;
    //var qtype= $stateParams.qtype;
    //filerecord.searchForm.qtype=qtype
    filerecord.search1();

    
    $scope.download=function(d){
    	
    	window.location.href = '../ngres/downloadfile/'+d.id;
    }
    
    $scope.add=function(){
        $state.go('filerecordedit',{id: ''});
    }
    $scope.edit=function(d){
        $state.go('filerecordedit',{id: d.id});
    }
    $scope.detail=function(d){
        $state.go('filerecorddetail',{id: d.id});
    }
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
    var ztree
    var zNodes
  /*  zuzhi_service.listTree({"leixing":["jigou","bumen","gangwei","renyuan"]}).then(function (data) {
        zNodes=data.data;
    })*/
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
//                submitform.item =d;
//                submitform.edit = true;
                var promise =  filerecord_service.createOrUpdate($scope.wenjian)
                promise.then(function (data) {  // 调用承诺API获取数据 .resolve
                    if(data.data.status == 'error') {
                        toastr['error'](data.data.message, "SORRY！")
                    } else {
                        $modalInstance.dismiss('cancel');
                        toastr['success']("共享成功！", "恭喜您！")
                        filerecord.search1();
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
    }
    // set sidebar closed and body solid layout mode
    $rootScope.settings.layout.pageBodySolid = true;
    $rootScope.settings.layout.pageSidebarClosed = false;
});
