'use strict';

MetronicApp.controller('DashboardController', function($rootScope, $scope,$state,$stateParams, $http, $timeout) {
    $scope.$on('$viewContentLoaded', function() {   
        // initialize core components
        Metronic.initAjax();

    });
    var dashbord={}
    $scope.dashbord=dashbord;
    dashbord.list=[];
    $scope.initindex=function(){
        $scope.kehudetail=function(d){
            $state.go('kehumydetail',{id: d.id});
        }
        var item={}
        $http.post(baseUrl+'/dashbord/index/list', item).then(function (response) {
            var data=  response.data
//            console.log(data)
            dashbord.sum=data;
            dashbord.cursum=dashbord.sum.xiaoshousum[5]
            dashbord.cursumzhanbi=parseInt(dashbord.cursum.profit/dashbord.cursum.sales*100);
            if(isNaN( dashbord.cursumzhanbi)){
                dashbord.cursumzhanbi=0;
            }

        Index.initCharts( dashbord.sum.xiaoshousum);
        })
        dashbord.huanyue=function(d){
            if(d==1){
                dashbord.cursum=dashbord.sum.xiaoshousum[5]
                dashbord.cursumzhanbi=parseInt(dashbord.cursum.profit/dashbord.cursum.sales*100);
                if(isNaN( dashbord.cursumzhanbi)){
                    dashbord.cursumzhanbi=0;
                }

            }else  if(d==2){
                dashbord.cursum=dashbord.sum.xiaoshousum[4]
                dashbord.cursumzhanbi=parseInt(dashbord.cursum.profit/dashbord.cursum.sales*100);
                if(isNaN( dashbord.cursumzhanbi)){
                    dashbord.cursumzhanbi=0;
                }
            }
        }
    }


$scope.goto=function(){
	$state.go('order-list2',{id: ''});
}

    // set sidebar closed and body solid layout mode
    $rootScope.settings.layout.pageBodySolid = true;
    $rootScope.settings.layout.pageSidebarClosed = false;
});
