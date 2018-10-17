'use strict';
MetronicApp.factory('changliang_service',['$http', '$q', function ($http,$q) {
    var service={};
    service.query = function(searchForm){
        var res;
        if(isdebug){

        }else{
           res=  $http.post(baseUrl+'/gonggong/changliang/list', searchForm).then(function (response) {
                return response.data;
            });
          //  return [{"createTime":null,"guige":"b","id":"","idpath":"","leixing":"","mingcheng":"a","modifiedTime":null,"namepath":"","page":null,"toporgid":""}];
        }
        return res;
    };
    service.getchangliang = function(leibie){
        var res;
        if(isdebug){

        }else{

            res=  $http.post(baseUrl+'/gonggong/changliang/list/leibie', {"leibie":leibie}).then(function (response) {
                return response.data;
            });
            //  return [{"createTime":null,"guige":"b","id":"","idpath":"","leixing":"","mingcheng":"a","modifiedTime":null,"namepath":"","page":null,"toporgid":""}];
        }
        return res;
    };
    service.getallchanglaing=function(items){
        return  $http.post(baseUrl+'/gonggong/changliang/list/allchangliang',items).then(function (response) {
                    return response.data;
                });
    }
    service.createOrUpdate = function(item){
        var promise;
    	if(isdebug){
    	}else{
            promise=   $http.post(baseUrl+'/gonggong/changliang/edit',item);
    	}
    	return promise;
       
    };
    service.delete = function(items){
        var promise;
        if(isdebug){
        }else{
            promise=   $http.post(baseUrl+'/gonggong/changliang/delete',items);
        }
        return promise;
    };
	 service.softdelete = function(items){
        var promise;
        if(isdebug){
        }else{
            promise=   $http.post(baseUrl+'/gonggong/changliang/softdelete',items);
        }
        return promise;
    };
    service.loadById = function(id){
        var res;
        res=  $http.post(baseUrl+'/gonggong/changliang/list/id', {"id":id }).then(function (response) {
            return response.data;
        })
        return res;
    };

    return service;
}]);
