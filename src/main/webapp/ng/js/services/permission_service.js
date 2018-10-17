'use strict';

MetronicApp.factory('permission_service',['$http', '$q', function ($http,$q) {
    var service={};
    service.query = function(searchForm){
        var res=  $http.post(baseUrl+'/zuzhijiagou/permission/list', searchForm).then(function (response) {
            return response.data;
        });
        return res;
    };
    service.loadById = function(id){
        var res;
        res=  $http.post(baseUrl+'/zuzhijiagou/permission/list/id', {"id":id }).then(function (response) {
            return response.data;
        })
        return res;
    };
    service.createOrUpdate = function(item){
        var promise;
        if(isdebug){
        }else{
            promise=   $http.post(baseUrl+'/zuzhijiagou/permission/edit',item);
        }
        return promise;

    };
    service.daoru = function(item){
        var promise;
        if(isdebug){
        }else{
            promise=   $http.post(baseUrl+'/zuzhijiagou/permission/daoru',item);
        }
        return promise;

    };
    service.delete = function(items){
        var promise;
        if(isdebug){
        }else{
            promise=   $http.post(baseUrl+'/zuzhijiagou/permission/delete',items);
        }
        return promise;
    };
    service.softdelete = function(items){
        var promise;
        if(isdebug){
        }else{
            promise=   $http.post(baseUrl+'/zuzhijiagou/permission/softdelete',items);
        }
        return promise;
    };
    service.getByRole = function(id){
        var res;
        res=  $http.post(baseUrl+'/zuzhijiagou/permission/list/role', {"position_id":id }).then(function (response) {
            return response.data;
        })
        return res;
    };
    service.getResSelf = function(){
        var promise;
        if(isdebug){
        }else{
            promise=   $http.post(baseUrl+'/zuzhijiagou/permission/res/self');
        }
        return promise;
    };
    return service;

}]);


