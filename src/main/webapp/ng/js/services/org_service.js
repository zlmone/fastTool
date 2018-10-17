'use strict';

MetronicApp.factory('org_service',['$http', '$q', function ($http,$q) {
    var service={};
    service.query = function(searchForm){
        var res=  $http.post(baseUrl+'/zuzhijiagou/org/list', searchForm).then(function (response) {
            return response.data;
        });
        return res;
    };
    service.loadById = function(id){
        var res;
        res=  $http.post(baseUrl+'/zuzhijiagou/org/list/id', {"id":id }).then(function (response) {
            return response.data;
        })
        return res;
    };
    service.createOrUpdate = function(item){
        var promise;
        if(isdebug){
        }else{
            promise=   $http.post(baseUrl+'/zuzhijiagou/org/edit',item);
        }
        return promise;

    };
    service.daoru = function(item){
        var promise;
        if(isdebug){
        }else{
            promise=   $http.post(baseUrl+'/zuzhijiagou/org/daoru',item);
        }
        return promise;

    };
    service.delete = function(items){
        var promise;
        if(isdebug){
        }else{
            promise=   $http.post(baseUrl+'/zuzhijiagou/org/delete',items);
        }
        return promise;
    };
    service.softdelete = function(items){
        var promise;
        if(isdebug){
        }else{
            promise=   $http.post(baseUrl+'/zuzhijiagou/org/softdelete',items);
        }
        return promise;
    };
    service.getByOrgtype = function(item){
        var res=  $http.post(baseUrl+'/zuzhijiagou/org/list/org_type', item).then(function (response) {
            return response.data;
        });
        return res;
    };
    service.listDept = function(item){
        var promise;
        if(isdebug){
        }else{
            promise=   $http.post(baseUrl+'/zuzhijiagou/org/listDept',item);
        }
        return promise;
    };
    service.listDepts = function(item){
        var promise;
        if(isdebug){
        }else{
            promise=   $http.post(baseUrl+'/zuzhijiagou/org/listDeptAndUser',item);
        }
        return promise;
    };
    service.dragSqueue = function(nodes){
        var res = $http.post(baseUrl+'/zuzhijiagou/org/dragSqueue', {"nodes":nodes}).then(function (response) {
            return response.data;
        });
        return res;
    }
    service.deleteOrg = function(item){
        var promise;
        if(isdebug){
        }else{
            promise=   $http.post(baseUrl+'/zuzhijiagou/org/deleteOrg',item);
        }
        return promise;
    };
    service.listPost = function(item){
        var promise;
        if(isdebug){
        }else{
            promise=   $http.post(baseUrl+'/zuzhijiagou/org/listPost',item);
        }
        return promise;
    };
    service.queryPostByDept = function(department_id){
        var res=  $http.post(baseUrl+'/zuzhijiagou/org/queryPostByDept', {"department_id":department_id}).then(function (response) {
            return response.data;
        });
        return res;
    };
    return service;

}]);


