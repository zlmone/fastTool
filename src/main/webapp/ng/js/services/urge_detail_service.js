'use strict';
MetronicApp.factory('urge_detail_service',['$http', '$q', function ($http,$q) {
    var service={};
    service.query = function(searchForm){
        var res=  $http.post(baseUrl+'/cuishouxiangqing/urge_detail/list', searchForm).then(function (response) {
                return response.data;
            });
        return res;
    };
	 service.loadById = function(id){
	     var res;
        res=  $http.post(baseUrl+'/cuishouxiangqing/urge_detail/list/id', {"id":id }).then(function (response) {
            return response.data;
        })
        return res;
    };
    service.createOrUpdate = function(item){
        var promise;
    	if(isdebug){
    	}else{
            promise=   $http.post(baseUrl+'/cuishouxiangqing/urge_detail/edit',item);
    	}
    	return promise;
       
    };
	  service.daoru = function(item){
        var promise;
        if(isdebug){
        }else{
            promise=   $http.post(baseUrl+'/cuishouxiangqing/urge_detail/daoru',item);
        }
        return promise;

    };
    service.delete = function(items){
        var promise;
        if(isdebug){
        }else{
            promise=   $http.post(baseUrl+'/cuishouxiangqing/urge_detail/delete',items);
        }
        return promise;
    };
	 service.softdelete = function(items){
        var promise;
        if(isdebug){
        }else{
            promise=   $http.post(baseUrl+'/cuishouxiangqing/urge_detail/softdelete',items);
        }
        return promise;
    };

    return service;
}]);
