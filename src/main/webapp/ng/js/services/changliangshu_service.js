'use strict';
MetronicApp.factory('changliangshu_service',['$http', '$q', function ($http,$q) {
    var service={};
    service.query = function(searchForm){
        var res;
        res=  $http.post(baseUrl+'/changliangshu/list', searchForm).then(function (response) {
            return response.data;
        });
        return res;
    };
    service.loadById = function(id){
        var res;
        res=  $http.post(baseUrl+'/changliangshu/list/id', {"id":id }).then(function (response) {
            return response.data;
        })
        return res;
    };
    service.createOrUpdate = function(item){
        var promise;
        if(isdebug){
        }else{
            promise=   $http.post(baseUrl+'/changliangshu/edit',item);
        }
        return promise;

    };
    service.daoru = function(item){
        var promise;
        if(isdebug){
        }else{
            promise=   $http.post(baseUrl+'/changliangshu/daoru',item);
        }
        return promise;

    };
    service.listTree = function(treetype){
        var promise;
        if(isdebug){
        }else{
            promise=   $http.post(baseUrl+'/changliangshu/listtree',{"changliangshuleixing":treetype});
        }
        return promise;

    };
    service.delete = function(items){
        var promise;
        if(isdebug){
        }else{
            promise=   $http.post(baseUrl+'/changliangshu/delete',items);
        }
        return promise;
    };
    service.zhuanyi = function(zhuan){
        var promise;
        if(isdebug){
        }else{
            promise=   $http.post(baseUrl+'/changliangshu/zhuanyi',zhuan);
        }
        return promise;
    };
    service.softdelete = function(items){
        var promise;
        if(isdebug){
        }else{
            promise=   $http.post(baseUrl+'/changliangshu/softdelete',items);
        }
        return promise;
    };
    service.updateStatus = function(id,status){
        var promise;
        var json={"id":id,"status":status};
        if(isdebug){
        }else{
            promise=   $http.post(baseUrl+'/changliangshu/status',json);
        }
        return promise;
    };
    service.jiesuocang = function(item){
        var promise;
        if(isdebug){
        }else{
            promise=   $http.post(baseUrl+'/changliangshu/jiesuocang',item);
        }
        return promise;
    }
    service.queryselect = function(item){
        var promise;
        if(isdebug){
        }else{
            promise=   $http.post(baseUrl+'/changliangshu/select',item);
        }
        return promise;
    }
    service.listztree = function(item){
        var promise;
        if(isdebug){
        }else{
            promise=   $http.post(baseUrl+'/changliangshu/listztree',item);
        }
        return promise;
    }

    return service;
}]);
