'use strict';
MetronicApp.factory('filerecord_service2',['$http', '$q', function ($http,$q) {
    var service={};
    service.query = function(searchForm){
        var res=  $http.post(baseUrl+'/gonggong/filerecord/list', searchForm).then(function (response) {
                return response.data;
            });
        return res;
    };
	 service.loadById = function(id){
	     var res;
        res=  $http.post(baseUrl+'/gonggong/filerecord/list/id', {"id":id }).then(function (response) {
            return response.data;
        })
        return res;
    };

    service.loadByRefid = function(id){
        var promise;
        if(isdebug){
        }else{
            promise=   $http.post(baseUrl+'/gonggong/filerecord/list/bugList', {"id":id });
        }
        return promise;
    };

    service.byIdAndBeizhu = function(item){
        var promise;
        if(isdebug){
        }else{
            promise=   $http.post(baseUrl+'/file/findAll/byIdAndBeizhu',item);
        }
        return promise;
    };

    service.createOrUpdate = function(item){
        var promise;
    	if(isdebug){
    	}else{
            promise=   $http.post(baseUrl+'/gonggong/filerecord/edit',item);
    	}
    	return promise;
       
    };
	  service.daoru = function(item){
        var promise;
        if(isdebug){
        }else{
            promise=   $http.post(baseUrl+'/gonggong/filerecord/daoru',item);
        }
        return promise;

    };
    service.delete = function(items){
        var promise;
        if(isdebug){
        }else{
            promise=   $http.post(baseUrl+'/gonggong/filerecord/delete',items);
        }
        return promise;
    };
    service.queryFile=function(items){
    	
    	var promise;
    	
    	if(isdebug){
    		
    	}else{
    		promise= $http.post(baseUrl+'/ngres/filerecord/query',items);
    	}
    	
    }
	 service.softdelete = function(items){
        var promise;
        if(isdebug){
        }else{
            promise=   $http.post(baseUrl+'/gonggong/filerecord/softdelete',items);
        }
        return promise;
    };

    return service;
}]);
