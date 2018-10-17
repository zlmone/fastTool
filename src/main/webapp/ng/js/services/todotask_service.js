'use strict';
/*var services = angular.module('nglanmo.todotask_service', []);
services.factory('todotask_service',['$http', '$q', function ($http,$q) {*/
    MetronicApp.factory('todotask_service',['$http', '$q', function ($http,$q) {
    var service={};
    service.query = function(searchForm){
        var res;
        if(isdebug){

        }else{
           res=  $http.post(baseUrl+'/zuzhijigou/todotask/list', searchForm).then(function (response) {
                return response.data;
            });
          //  return [{"createTime":null,"guige":"b","id":"","idpath":"","leixing":"","mingcheng":"a","modifiedTime":null,"namepath":"","page":null,"toporgid":""}];
        }
        return res;
    };
    service.querydaiban = function(){
        var res;
        if(isdebug){

        }else{
            res=  $http.post(baseUrl+'/zuzhijigou/todotask/list/daiban').then(function (response) {
                return response.data;
            });
            //  return [{"createTime":null,"guige":"b","id":"","idpath":"","leixing":"","mingcheng":"a","modifiedTime":null,"namepath":"","page":null,"toporgid":""}];
        }
        return res;
    };
    service.queryyido = function(searchForm){
        var res;
        if(isdebug){

        }else{
            res=  $http.post(baseUrl+'/zuzhijigou/todotask/list/success', searchForm).then(function (response) {
                return response.data;
            });
            //  return [{"createTime":null,"guige":"b","id":"","idpath":"","leixing":"","mingcheng":"a","modifiedTime":null,"namepath":"","page":null,"toporgid":""}];
        }
        return res;
    };
    service.queryall = function(biaodanid){
        var res;
        if(isdebug){

        }else{
            res=  $http.post(baseUrl+'/zuzhijigou/todotask/list/all', {"biaodanid":biaodanid}).then(function (response) {
                return response.data;
            });
            //  return [{"createTime":null,"guige":"b","id":"","idpath":"","leixing":"","mingcheng":"a","modifiedTime":null,"namepath":"","page":null,"toporgid":""}];
        }
        return res;
    };
	 service.loadById = function(id){
	     var res;
        res=  $http.post(baseUrl+'/zuzhijigou/todotask/list/id', {"id":id }).then(function (response) {
            return response.data;
        })
        return res;
    };
    service.createOrUpdate = function(item){
        //防止重复提交参数设置
        item.loadingStatus = true;
        var promise;
    	if(isdebug){
    	}else{
            promise=   $http.post(baseUrl+'/zuzhijigou/todotask/edit',item);
    	}
    	return promise;
       
    };
	  service.daoru = function(item){
          //防止重复提交参数设置
          item.loadingStatus = true;
        var promise;
        if(isdebug){
        }else{
            promise=   $http.post(baseUrl+'/zuzhijigou/todotask/daoru',item);
        }
        return promise;

    };
    service.delete = function(items){
        //防止重复提交参数设置
        items.loadingStatus = true;
        var promise;
        if(isdebug){
        }else{
            promise=   $http.post(baseUrl+'/zuzhijigou/todotask/delete',items);
        }
        return promise;
    };
	 service.softdelete = function(items){
         //防止重复提交参数设置
         items.loadingStatus = true;
        var promise;
        if(isdebug){
        }else{
            promise=   $http.post(baseUrl+'/zuzhijigou/todotask/softdelete',items);
        }
        return promise;
    };



    service.dashboard_sort = function(id){
        var res;
        res=  $http.post(baseUrl+'/yibiaopanpaixu/dashboard_sort/list/id', {"id":id }).then(function (response) {
            return response.data;
        })
        return res;
    };

    service.dashboard_sort_createOrUpdate = function(paixu){
        var promise;
        if(isdebug){
        }else{
            promise=   $http.post(baseUrl+'/yibiaopanpaixu/dashboard_sort/edit',{paixu:paixu});
        }
        return promise;

    };




    return service;
}]);
