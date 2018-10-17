
MetronicApp.controller('firsttrial-edit_controller', function($rootScope, $scope,$state,$stateParams,user_service,alertService,filterFilter,$modal, $http, $timeout) {
    $scope.$on('$viewContentLoaded', function() {
        // initialize core components
        Metronic.initAjax();
        ComponentsPickers.init();
       
    });
    var order = {};
    if($rootScope.order!=undefined){
    	order.item = $rootScope.order;
    	$scope.order = order;
    }
	
	 $scope.edit=function(d){
        $state.go('car-edit',{id: d.id});
    }

    
    // set sidebar closed and body solid layout mode
    $rootScope.settings.layout.pageBodySolid = true;
    $rootScope.settings.layout.pageSidebarClosed = false;
})
