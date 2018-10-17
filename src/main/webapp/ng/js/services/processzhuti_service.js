'use strict';
//var services = angular.module('nglanmo.processzhuti_service', []);
//services.factory('processzhuti_service',['$http', '$q', function ($http,$q) {
    MetronicApp.factory('processzhuti_service',['$http', '$q', function ($http,$q) {
    var service={};
    service.query = function(searchForm){
        var res;
        if(isdebug){
            res= [
                {"id":"20","lastName":"su0","firstName":"zhang0"},
                {"id":"21","lastName":"su1","firstName":"zhang1"},
                {"id":"22","lastName":"su2","firstName":"zhang2"},
                {"id":"23","lastName":"su3","firstName":"zhang3"},
                {"id":"24","lastName":"su4","firstName":"zhang4"},
                {"id":"25","lastName":"su5","firstName":"zhang5"},
                {"id":"26","lastName":"su6","firstName":"zhang6"},
                {"id":"27","lastName":"su7","firstName":"zhang7"},
                {"id":"28","lastName":"su8","firstName":"zhang8"},
                {"id":"29","lastName":"su9","firstName":"zhang9"}
            ];
        }else{
           res=  $http.post(baseUrl+'/shenpi/processzhuti/list', searchForm).then(function (response) {
                return response.data;
            });
          //  return [{"createTime":null,"guige":"b","id":"","idpath":"","leixing":"","mingcheng":"a","modifiedTime":null,"namepath":"","page":null,"toporgid":""}];
        }
        return res;
    };
	 service.loadById = function(id){
	     var res;
        res=  $http.post(baseUrl+'/shenpi/processzhuti/list/id', {"id":id }).then(function (response) {
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
            promise=   $http.post(baseUrl+'/shenpi/processzhuti/edit',item);
    	}
    	return promise;
    };

    service.editStatus = function(item){
        //防止重复提交参数设置
        item.loadingStatus = true;
        var promise;
        if(isdebug){
        }else{
            promise=   $http.post(baseUrl+'/shenpi/processzhuti/editStatus',item);
        }
        return promise;
    };

	  service.daoru = function(item){
          //防止重复提交参数设置
          item.loadingStatus = true;
        var promise;
        if(isdebug){
        }else{
            promise=   $http.post(baseUrl+'/shenpi/processzhuti/daoru',item);
        }
        return promise;

    };
    service.delete = function(items){
        //防止重复提交参数设置
        items.loadingStatus = true;
        var promise;
        if(isdebug){
        }else{
            promise=   $http.post(baseUrl+'/shenpi/processzhuti/delete',items);
        }
        return promise;
    };
	 service.softdelete = function(items){
         //防止重复提交参数设置
         items.loadingStatus = true;
        var promise;
        if(isdebug){
        }else{
            promise=   $http.post(baseUrl+'/shenpi/processzhuti/softdelete',items);
        }
        return promise;
    };
    service.queryshenpiren = function(item){
        //防止重复提交参数设置
        item.loadingStatus = true;
        var promise;
        if(isdebug){
        }else{
            promise=   $http.post(baseUrl+'/shenpi/processzhuti/queryshenpiren',item);
        }
        return promise;
    }

    return service;
}]);
