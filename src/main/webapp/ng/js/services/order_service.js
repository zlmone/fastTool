'use strict';
MetronicApp.factory('order_service',['$http', '$q', function ($http,$q) {
    var service={};
    service.query = function(searchForm){
        var res=  $http.post(baseUrl+'/dingdanguanli/order/list', searchForm).then(function (response) {
                return response.data;
            });
        return res;
    };
	
    service.query2 = function(){
        var res=  $http.post(baseUrl+'/dingdanguanli/order/list2').then(function (response) {
                return response.data;
            });
        return res;
    };
    
	service.findAll = function(searchForm){
        var res=  $http.post(baseUrl+'/dingdanguanli/order/findAll', searchForm).then(function (response) {
                return response.data;
            });
        return res;
    };
	
    service.tongji = function(searchForm){
        var res=  $http.post(baseUrl+'/dingdanguanli/order/tongji',searchForm).then(function (response) {
                return response.data;
            });
        return res;
    };
	
	 service.loadById = function(id){
	     var res;
        res=  $http.post(baseUrl+'/dingdanguanli/order/list/id', {"id":id }).then(function (response) {
            return response.data;
        })
        return res;
    };
    
    service.jiegua = function(item){
	     var res;
       res=  $http.post(baseUrl+'/dingdanguanli/order/list/jiegua', item).then(function (response) {
           return response.data;
       })
       return res;
   };
    
   service.zhuanyi = function(item){
	     var res;
     res=  $http.post(baseUrl+'/dingdanguanli/order/list/zhuanyi', item).then(function (response) {
         return response.data;
     })
     return res;
 };
   
    service.loadById1 = function(id){
	     var res;
       res=  $http.post(baseUrl+'/dingdanguanli/order/list/id1', {"id":id }).then(function (response) {
           return response.data;
       })
       return res;
   };
    
   service.loadById_online = function(id){
	     var res;
     res=  $http.post(baseUrl+'/dingdanguanli/order/list/online', {"id":id }).then(function (response) {
         return response.data;
     })
     return res;
 };
 
 service.loadById_yunying = function(id){
     var res;
 res=  $http.post(baseUrl+'/dingdanguanli/order/list/tellog', {"id":id }).then(function (response) {
     return response.data;
 })
 return res;
};
   
    service.loadById_tel = function(id){
	     var res;
       res=  $http.post(baseUrl+'/dianhuaxinxi/telinfo/list/orderid', {"id":id }).then(function (response) {
           return response.data;
       })
       return res;
   };
    
    service.loadById_out = function(id){
	     var res;
       res=  $http.post(baseUrl+'/waibuxinxi/outinfo/list/id', {"id":id }).then(function (response) {
           return response.data;
       })
       return res;
   };
    
   service.loadById_repeat = function(id){
	     var res;
     res=  $http.post(baseUrl+'/dingdanguanli/order/list1', {"id":id }).then(function (response) {
         return response.data;
     })
     return res;
 };
   
    service.createOrUpdate = function(item){
        var promise;
    	if(isdebug){
    	}else{
            promise=   $http.post(baseUrl+'/dingdanguanli/order/edit',item);
    	}
    	return promise;
       
    };
    
    service.add = function(item){
        var promise;
    	if(isdebug){
    	}else{
            promise=   $http.post(baseUrl+'/dingdanguanli/order/add',item);
    	}
    	return promise;
       
    };
    
    service.createOrUpdate_tel = function(item){
        var promise;
    	if(isdebug){
    	}else{
    		promise=  $http.post(baseUrl+'/dianhuaxinxi/telinfo/edit', item).then(function (response) {
    	           return response.data;
    	       })
    	}
    	return promise;
       
    };
    
    service.createOrUpdate_tel1 = function(item){
        var promise;
    	if(isdebug){
    	}else{
    		promise=  $http.post(baseUrl+'/dianhuaxinxi/telinfo/edit1', item).then(function (response) {
    	           return response.data;
    	       })
    	}
    	return promise;
       
    };
    
    service.createOrUpdate_out = function(item){
        var promise;
    	if(isdebug){
    	}else{
            promise=   $http.post(baseUrl+'/waibuxinxi/outinfo/edit',item);
    	}
    	return promise;
       
    };
    
	  service.daoru = function(item){
        var promise;
        if(isdebug){
        }else{
            promise=   $http.post(baseUrl+'/dingdanguanli/order/daoru',item);
        }
        return promise;

    };
    service.delete = function(items){
        var promise;
        if(isdebug){
        }else{
            promise=   $http.post(baseUrl+'/dingdanguanli/order/delete',items);
        }
        return promise;
    };
	 service.softdelete = function(items){
        var promise;
        if(isdebug){
        }else{
            promise=   $http.post(baseUrl+'/dingdanguanli/order/softdelete',items);
        }
        return promise;
    };

    return service;
}]);
