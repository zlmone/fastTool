'use strict';
/*var services = angular.module('nglanmo.process_service', []);
services.factory('process_service',['$http', '$q', function ($http,$q) {*/
    MetronicApp.factory('process_service',['$http', '$q', function ($http,$q) {
    var service={};

    service.handle = function(biz,biaodanid,beizhu,action){
        var promise;
        if(isdebug){
        }else{
            var item={"biaodanid":biaodanid,"beizhu":beizhu,"action":action};
            promise=   $http.post(baseUrl+'/process/'+biz,item);
        }
        return promise;
    };

    return service;
}]);
