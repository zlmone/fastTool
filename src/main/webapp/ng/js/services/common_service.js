'use strict';
MetronicApp.factory('filerecord_service',['$http', '$q', function ($http,$q) {
    var service={};
    service.query = function(refid){
        var res;
        res=  $http.post(baseUrl+'/filerecord/query', {"refid":refid}).then(function (response) {
            return response.data;
        })
        return res;
    };
    service.querygongxiang = function(){
        var res;
        res=  $http.post(baseUrl+'/filerecord/query/gongxiang').then(function (response) {
            return response.data;
        })
        return res;
    };
    service.querygongxiangall = function(){
        var res;
        res=  $http.post(baseUrl+'/filerecord/query/gongxiangall').then(function (response) {
            return response.data;
        })
        return res;
    };
    service.delete = function(id){
        var res;
        res=  $http.post(baseUrl+'/filerecord/delete', {"id":id}).then(function (response) {
            return response.data;
        })
        return res;
    };
    service.createOrUpdate = function(item){
        var promise;
        if(isdebug){
        }else{
            promise=   $http.post(baseUrl+'/filerecord/edit',item);
        }
        return promise;

    };

    return service;
}]);
