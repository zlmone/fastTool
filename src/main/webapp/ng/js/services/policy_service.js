'use strict';
MetronicApp.factory('policy_service',['$http', '$q', function ($http,$q) {
    var service={};
    service.query = function(searchForm){
        var res=  $http.post(baseUrl+'/dingdanbiao/order/list', searchForm).then(function (response) {
                return response.data;
            });
        return res;
    };
	
	service.findAll = function(searchForm){
        var res=  $http.post(baseUrl+'/dingdanbiao/order/findAll', searchForm).then(function (response) {
                return response.data;
            });
        return res;
    };
	
	
	 service.loadById = function(id){
	     var res;
        res=  $http.post(baseUrl+'/dingdanbiao/order/list/id', {"id":id }).then(function (response) {
            return response.data;
        })
        return res;
    };
    service.createOrUpdate = function(item){
        var promise;
    	if(isdebug){
    	}else{
            promise=   $http.post(baseUrl+'/dingdanbiao/order/edit',item);
    	}
    	return promise;
       
    };
	  service.daoru = function(item){
        var promise;
        if(isdebug){
        }else{
            promise=   $http.post(baseUrl+'/dingdanbiao/order/daoru',item);
        }
        return promise;

    };
    service.delete = function(items){
        var promise;
        if(isdebug){
        }else{
            promise=   $http.post(baseUrl+'/dingdanbiao/order/delete',items);
        }
        return promise;
    };
	 service.softdelete = function(items){
        var promise;
        if(isdebug){
        }else{
            promise=   $http.post(baseUrl+'/dingdanbiao/order/softdelete',items);
        }
        return promise;
    };

    return service;
}]);
