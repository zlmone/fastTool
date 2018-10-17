'use strict';
MetronicApp.factory('payinfo_service',['$http', '$q', function ($http,$q) {
    var service={};
    service.query = function(searchForm){
        var res=  $http.post(baseUrl+'/haikuanxinxi/payinfo/list', searchForm).then(function (response) {
                return response.data;
            });
        return res;
    };
	 service.loadById = function(id){
	     var res;
        res=  $http.post(baseUrl+'/haikuanxinxi/payinfo/list/id', {"id":id }).then(function (response) {
            return response.data;
        })
        return res;
    };
    service.loadById1 = function(id){
	     var res;
       res=  $http.post(baseUrl+'/haikuanxinxi/payinfo/list/id1', {"id":id }).then(function (response) {
           return response.data;
       })
       return res;
   };
    service.createOrUpdate = function(item){
        var promise;
    	if(isdebug){
    	}else{
            promise=   $http.post(baseUrl+'/haikuanxinxi/payinfo/edit',item);
    	}
    	return promise;
       
    };
	  service.daoru = function(item){
        var promise;
        if(isdebug){
        }else{
            promise=   $http.post(baseUrl+'/haikuanxinxi/payinfo/daoru',item);
        }
        return promise;

    };
    service.delete = function(items){
        var promise;
        if(isdebug){
        }else{
            promise=   $http.post(baseUrl+'/haikuanxinxi/payinfo/delete',items);
        }
        return promise;
    };
	 service.softdelete = function(items){
        var promise;
        if(isdebug){
        }else{
            promise=   $http.post(baseUrl+'/haikuanxinxi/payinfo/softdelete',items);
        }
        return promise;
    };

    return service;
}]);
