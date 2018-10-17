'use strict';
MetronicApp.factory('reminder_service',['$http', '$q', function ($http,$q) {
    var service={};
    service.query = function(searchForm){
        var res=  $http.post(baseUrl+'/tishi/reminder/list', searchForm).then(function (response) {
                return response.data;
            });
        return res;
    };
    service.query1 = function(searchForm){
        var res=  $http.post(baseUrl+'/tishi/reminder/list1', searchForm).then(function (response) {
            return response.data;
        });
        return res;
    };
	 service.loadById = function(id){
	     var res;
        res=  $http.post(baseUrl+'/tishi/reminder/list/id', {"id":id }).then(function (response) {
            return response.data;
        })
        return res;
    };
    service.createOrUpdate = function(item){
        var promise;
    	if(isdebug){
    	}else{
            promise=   $http.post(baseUrl+'/tishi/reminder/edit',item);
    	}
    	return promise;
       
    };
	  service.daoru = function(item){
        var promise;
        if(isdebug){
        }else{
            promise=   $http.post(baseUrl+'/tishi/reminder/daoru',item);
        }
        return promise;

    };
    service.delete = function(items){
        var promise;
        if(isdebug){
        }else{
            promise=   $http.post(baseUrl+'/tishi/reminder/delete',items);
        }
        return promise;
    };
	 service.softdelete = function(items){
        var promise;
        if(isdebug){
        }else{
            promise=   $http.post(baseUrl+'/tishi/reminder/softdelete',items);
        }
        return promise;
    };

    return service;
}]);
