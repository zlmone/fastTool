'use strict';
/* services.js */
MetronicApp.factory('alertService',['$http', '$q','$rootScope','$timeout', function ($http,$q,$rootScope,$timeout) {
    var alertService = {};

    // 创建一个全局的 alert 数组
    $rootScope.alerts = [];

    alertService.add = function(type, msg) {
       /* $rootScope.alerts.push({'type': type, 'msg': msg, 'close': function(){ alertService.closeAlert(this); }});
        $timeout(function(){
            alertService.clearAlert();
        },3000)*/
        toastr[type](msg,type)
    };

    alertService.closeAlert = function(alert) {
        alertService.closeAlertIdx($rootScope.alerts.indexOf(alert));
    };

    alertService.closeAlertIdx = function(index) {
        $rootScope.alerts.splice(index, 1);
    };

    alertService.clearAlert = function(){
        $rootScope.alerts = [];
    };

    return alertService;

}]);
