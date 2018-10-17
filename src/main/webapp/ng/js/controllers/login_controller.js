'use strict';

MetronicApp.controller('login_controller', function($rootScope, $scope,$state,$stateParams,alertService,permission_service,filterFilter,$modal, $http, $timeout) {
    $scope.$on('$viewContentLoaded', function() {
        // initialize core components
        Metronic.initAjax();
        ComponentsPickers.init();
    });

    var promise=   $http.post(baseUrl+'/login/info');
     promise.then(function(data){
         $scope.user=data.data.user;
         $rootScope.user=data.data.user;
         window.localStorage.isLogin='1';
     });

    $scope.logout=function(){
        window.localStorage.isLogin ='0';
    }

    function showDiv(){
        document.getElementById('popDiv').style.display='block';
        document.getElementById('popIframe').style.display='block';
        document.getElementById('bg').style.display='block';
    }
    $scope.closeDiv = function(){
        document.getElementById('popDiv').style.display='none';
        document.getElementById('bg').style.display='none';
        document.getElementById('popIframe').style.display='none';
    }
    //权限判定
    if(true){
        var promise= permission_service.getResSelf();
        promise.then(function(data){
            $rootScope.myRes=data.data.permissionList;
            $rootScope.auth=function(fnid){
                if($rootScope.user.category_id=='1'){
                    return true;
                }
                if($rootScope.user.category_id=='0'){
                    return true;
                }
                if($rootScope.myRes!=undefined){
                    for(var i=0; i<$rootScope.myRes.length; i++){
                        if($rootScope.myRes[i]==null){
                            return;
                        }
                        if($rootScope.myRes[i].function_id==fnid){
                            return true;
                        }
                    }
                }
                return false;
            }


            $rootScope.authbutton=function(fnid,e){
                if($rootScope.user.category_id=='1'){
                    return true;
                }
                if($rootScope.myRes!=undefined){
                    var flag = false;
                    for(var i=0; i<$rootScope.myRes.length; i++){
                        if($rootScope.myRes[i].function_id==fnid){
                            flag = true;
                            break;
                        }
                    }
                    if(!flag){
                        alertService.clearAlert();
                        alertService.add('warning',"你没有此权限");
                        e.preventDefault();
                        return false;
                    }
                    return true;
                }else{
                    alertService.clearAlert();
                    alertService.add('warning',"你没有此权限");
                    e.preventDefault();
                    return false;
                }
            }
        });
    }

    $scope.forward = function(){
        history.go(1);
    }
    $scope.back = function(){
        history.go(-1);
    }
});


