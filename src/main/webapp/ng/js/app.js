/***
lanmosoft AngularJS App Main Script
***/

var isdebug = false;
var baseUrl = '../ngres';
/* Metronic App */
var MetronicApp = angular.module("MetronicApp", [
    "ui.router", 
    "ui.bootstrap",
    "ui.select",
    'angularFileUpload',
    "oc.lazyLoad",  
    "ngSanitize",
   /* 'pascalprecht.translgkuate',*/
    'flow',
    'ng.ueditor'
]); 

/* Configure ocLazyLoader(refer: https://github.com/ocombe/ocLazyLoad) */
MetronicApp.config(['$ocLazyLoadProvider', function($ocLazyLoadProvider) {
    $ocLazyLoadProvider.config({
        cssFilesInsertBefore: 'ng_load_plugins_before' // load the above css files before a LINK element with this ID. Dynamic CSS files must be loaded between core and theme css files
    });
}]);

/* Setup global settings */
MetronicApp.factory('settings', ['$rootScope','$http','changliang_service', function($rootScope,$http,changliang_service) {
    // supported languages
    var settings = {
        chang:[],
        menu:1,
        changliangshu:[],
        utils:{},
        layout: {
            pageSidebarClosed: false, // sidebar menu state
            pageBodySolid: false, // solid body color state
            pageAutoScrollOnLoad: 1000 // auto scroll to top on page load
        },
        layoutImgPath: Metronic.getAssetsPath() + 'admin/layout/img/',
        layoutCssPath: Metronic.getAssetsPath() + 'admin/layout/css/'
    };
    settings.getchangliang=function(leibie){
        if (settings.chang[leibie] == undefined) {
            var promise = changliang_service.getchangliang(leibie);
            promise.then(function(d){
                settings.chang[leibie] = d;
            });
        }
    }
    settings.getchangliangshu=function(leibie,initobj,key){
        if (settings.changliangshu[leibie] == undefined) {
            $http.post(baseUrl+'/changliangshu/listztree',{"changliangshuleixing":leibie}).then(function (response) {
                return response.data;
            }).then(function (data) {
                settings.changliangshu[leibie] = data;
            });
        }
    }

    settings.utils.back=function(){
        window.history.back()
    }
    settings.utils.initpicker=function(){
        ComponentsPickers.init();
    }
    settings.utils.initdropdown=function(){
        ComponentsDropdowns.init();
    }

    $rootScope.settings = settings;

    return settings;
}]);

/* Setup App Main Controller */
MetronicApp.controller('AppController', ['$scope', '$rootScope', function($scope, $rootScope) {
    $scope.$on('$viewContentLoaded', function() {
        Metronic.initComponents(); // init core components
        //Layout.init(); //  Init entire layout(header, footer, sidebar, etc) on page load if the partials included in server side instead of loading with ng-include directive 
    });
}]);

/***
Layout Partials.
By default the partials are loaded through AngularJS ng-include directive. In case they loaded in server side(e.g: PHP include function) then below partial 
initialization can be disabled and Layout.init() should be called on page load complete as explained above.
***/

/* Setup Layout Part - Header */
MetronicApp.controller('HeaderController', ['$scope','$rootScope','$location','$modal','$http', function($scope,$rootScope,$location,$modal,$http) {
    $scope.$on('$includeContentLoaded', function() {
        Layout.initHeader(); // init header
    });
    $scope.menuclick=function(value,path){
        $rootScope.settings.menu=value
        $location.path(path)
    }

    $scope.open=function(){
        openwindow($modal,'passwd-update.html','',function($scope, $modal, $modalInstance){
            var mima={};
            $scope.mima=mima;
            $scope.submit = function () {
                var item={"oldpasswd":mima.oldpasswd,"newpasswd":mima.newpasswd};
                var   promise=   $http.post(baseUrl+'/passwd',item);
                promise.then(function(data){
                    var res=data.data;
                    if(res.status=='success'){
                        toastr['success']("您的信息已保存成功！", "恭喜您！")
                        $modalInstance.dismiss('cancel');
                    }else{
                        alert(res.message);
                    }
                })
            };
            $scope.cancel = function () {
                $modalInstance.dismiss('cancel');
            };
        })
    }

}]);

MetronicApp.controller('header1Controller', ['$scope','$rootScope','$location','$modal','$http', function($scope,$rootScope,$location,$modal,$http) {
    $scope.$on('$includeContentLoaded', function() {
        Layout.initHeader(); // init header
    });
    
}]);

/* Setup Layout Part - Sidebar */
MetronicApp.controller('SidebarController', ['$scope','$rootScope', function($scope,$rootScope) {
    $scope.$on('$includeContentLoaded', function() {
        Layout.initSidebar(); // init sidebar
    });
    /*$scope.go=function(action){
    	if($rootScope.tabs1==undefined){
    	var tabs1 = [];
    	var tab = {};
    	if(action=="0"){
    		if($rootScope.tabs2==undefined){
    			var tabs2 = [];
    			var tab1 = {};
        		tab1.title="全部审批列表"
        		tab1.action="a";
        		tabs2.push(tab1);
        		$rootScope.tabs2 = tabs2;
    		}	
    		tab.title="我的待审批列表";
    		tab.action = action
    		tabs1.push(tab);
    	}else if(action=="1"){
    		tab.title="我的已审批列表";
    		tab.action=action;
    		tabs1.push(tab);
    		if($rootScope.tabs2==undefined){
    			var tabs2 = [];
    			var tab1 = {};
        		tab1.title="全部审批列表"
        		tab1.action="a";
        		tabs2.push(tab1);
        		$rootScope.tabs2 = tabs2;
    		}
    	}
    	$rootScope.tabs1 = tabs1;
    	}else{
    		var tabs1 = $rootScope.tabs1;
        	if(tabs1[action]==undefined){
        		var tab = {};
        		if(action=="0"){
        			if($rootScope.tabs2==undefined){
            			var tabs2 = [];
            			var tab1 = {};
                		tab1.title="全部审批列表"
                		tab1.action="a";
                		tabs2.push(tab1);
                		$rootScope.tabs2 = tabs2;
            		}
            		tab.title="我的待审批列表";
            		tab.action = action
            		tabs1.push(tab)
            	}else if(action=="1"){
            		if($rootScope.tabs2==undefined){
            			var tabs2 = [];
            			var tab1 = {};
                		tab1.title="全部审批列表"
                		tab1.action="a";
                		tabs2.push(tab1);
                		$rootScope.tabs2 = tabs2;
            		}
            		tab.title="我的已审批列表";
            		tab.action=action;
            		tabs1.push(tab)
            	}
        	}
        	$rootScope.tabs1 = tabs1;
    	}
    }*/
    
}]);

/* Setup Layout Part - Quick Sidebar */
MetronicApp.controller('QuickSidebarController', ['$scope', function($scope) {    
    $scope.$on('$includeContentLoaded', function() {
        setTimeout(function(){
            QuickSidebar.init(); // init quick sidebar        
        }, 2000)
    });
}]);

/* Setup Layout Part - Theme Panel */
MetronicApp.controller('ThemePanelController', ['$scope', function($scope) {    
    $scope.$on('$includeContentLoaded', function() {
        Demo.init(); // init theme panel
    });
}]);

/* Setup Layout Part - Footer */
MetronicApp.controller('FooterController', ['$scope', function($scope) {
    $scope.$on('$includeContentLoaded', function() {
        Layout.initFooter(); // init footer
    });
}]);


/* Setup Rounting For All Pages */
MetronicApp.config(['$stateProvider', '$urlRouterProvider', function($stateProvider, $urlRouterProvider) {
    // Redirect any unmatched url
    $urlRouterProvider.otherwise("/dashboard.html");  
    
    $stateProvider
        // Dashboard
        .state('dashboard', {
            url: "/dashboard.html",
            templateUrl: "views/dashboard.html",            
            data: {pageTitle: '控制台'},
            controller: "DashboardController",
            resolve: {
                deps: ['$ocLazyLoad', function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        name: 'MetronicApp',
                        files: [
                            'assets/global/plugins/morris/morris.css',
                            'assets/admin/pages/css/tasks.css',
                            
                            'assets/global/plugins/morris/morris.min.js',
                            'assets/global/plugins/morris/raphael-min.js',
                            'assets/global/plugins/jquery.sparkline.min.js',

                            'assets/admin/pages/scripts/index3.js',
                            'assets/admin/pages/scripts/tasks.js',

                             'js/controllers/DashboardController.js'
                        ] 
                    });
                }]
            }
        })
        // AngularJS plugins
        .state('changliang', {
            url: "/changliang.html",
            templateUrl: "views/changliang.html",
            data: {pageTitle: '常量'},
            controller: "changliang_controller"
        })
        .state('changliangshu', {
            url: "/changliangshu.html",
            templateUrl: "views/changliangshu.html",
            data: {pageTitle: '常量树'},
            controller: "changliangshu_controller"
        })

        /*提示*/
        .state('button-list', {url: "/button-list/{type:.*}",templateUrl: "views/button-list.html",data: {pageTitle: '消息提示'},controller: "reminder_controller"})
        .state('processzhuti-list', {url: "/processzhuti-list/{type:.*}",templateUrl: "views/processzhuti-list.html",data: {pageTitle: '审批设置'},controller: "processzhuti_controller"})
        .state('processzhuti-edit', {url: "/processzhuti-edit/{id:.*}",templateUrl: "views/processzhuti-edit.html",data: {pageTitle: '审批设置'},controller: "processzhuti_controller"})
        .state('processzhuti-detail', {url: "/processzhuti-detail/{id:.*}/{type:.*}",templateUrl: "views/processzhuti-detail.html",data: {pageTitle: '审批设置'},controller: "processzhuti_controller"})
        /*提示*/
        .state('reminder-list', {url: "/reminder-list/{type:.*}",templateUrl: "views/reminder-list.html",data: {pageTitle: '消息提示'},controller: "reminder_controller"})
        /*审核管理*/
        .state('todotask-list', {url: "/todotask-list/{type:.*}",templateUrl: "views/todotask-list.html",data: {pageTitle: '待审核'},controller: "todotask_controller"})
        .state('todotask-success', {url: "/todotask-success/{type:.*}",templateUrl: "views/todotask-list.html",data: {pageTitle: '已审核'},controller: "todotask_success_controller"})
        /*用户 授权*/
        .state('org-list', {url: "/org-list/{type:.*}",templateUrl: "views/org-list.html",data: {pageTitle: '授权管理'},controller: "org_controller"})
        .state('org-role-list', {url: "/org-role-list/{type:.*}", templateUrl: "views/org-role-list.html", data: {pageTitle: '授权管理'},controller: "org_role_controller"})
        .state('user-list', {url: "/user-list/{type:.*}",templateUrl: "views/user-list.html",data: {pageTitle: '授权管理'},controller: "user_controller"})
        .state('user-add', {url: "/user-add/{id:.*}",templateUrl: "views/user-add.html",data: {pageTitle: '授权管理'},controller: "user_controller"})
        .state('user-add1', {url: "/user-add1/{id:.*}",templateUrl: "views/user-add1.html",data: {pageTitle: '授权管理'},controller: "user_controller"})
        .state('user-edit', {url: "/user-edit/{id:.*}",templateUrl: "views/user-edit.html",data: {pageTitle: '授权管理'},controller: "user_controller"})
        .state('user-detail', {url: "/user-detail/{id:.*}",templateUrl: "views/user-detail.html",data: {pageTitle: '授权管理'},controller: "user_controller"})
        .state('user-edit1', {url: "/user-edit1/{id:.*}",templateUrl: "views/user-edit1.html",data: {pageTitle: '授权管理'},controller: "user_controller"})
        .state('user-detail1', {url: "/user-detail1/{id:.*}",templateUrl: "views/user-detail1.html",data: {pageTitle: '授权管理'},controller: "user_controller"})


        /*审批管理*/
        .state('order-list', {url: "/order-list/{type:.*}",templateUrl: "views/order-list.html",data: {pageTitle: '客户设置'},controller: "order_controller"})
        .state('order-list1', {url: "/order-list1/{type:.*}",templateUrl: "views/order-list1.html",data: {pageTitle: '客户设置'},controller: "order_controller1"})
        .state('order-list2', {url: "/order-list2/{type:.*}",templateUrl: "views/order-list2.html",data: {pageTitle: '客户设置'},controller: "order_controller2"})
        .state('order-list3', {url: "/order-list3/{type:.*}",templateUrl: "views/order-list3.html",data: {pageTitle: '客户设置'},controller: "order_controller3"})
        .state('order-list4', {url: "/order-list4/{type:.*}",templateUrl: "views/order-list4.html",data: {pageTitle: '客户设置'},controller: "order_controller4"})
        .state('order-list5', {url: "/order-list5/{type:.*}",templateUrl: "views/order-list5.html",data: {pageTitle: '客户设置'},controller: "order_controller5"})
        .state('order-list6', {url: "/order-list6/{type:.*}",templateUrl: "views/order-list6.html",data: {pageTitle: '客户设置'},controller: "order_controller6"})
        .state('payinfo-list', {url: "/payinfo-list/{type:.*}",templateUrl: "views/yuqi-list.html",data: {pageTitle: '客户设置'},controller: "payinfo_controller"})
        .state('order-edit', {url: "/order-edit/{id:.*}",templateUrl: "views/order-edit.html",data: {pageTitle: '客户编辑'},controller: "order-edit_controller"})
        .state('order-add', {url: "/order-add/{id:.*}",templateUrl: "views/order-add.html",data: {pageTitle: '客户编辑'},controller: "order-add_controller"})
        .state('order-edit1', {url: "/order-edit1/{id:.*}",templateUrl: "views/order_edit2.html",data: {pageTitle: '客户编辑'},controller: "order-edit_controller"})
       /*政策管理*/
        .state('policy-edit', {url: "/policy-edit/{id:.*}",templateUrl: "views/policy-edit.html",data: {pageTitle: '政策管理'},controller: "policy-edit_controller"})
        
        /*渠道管理*/
        .state('seller-list', {url: "/seller-list/{type:.*}",templateUrl: "views/seller-list.html",data: {pageTitle: '渠道管理'},controller: "seller_controller"})
        /*操作日志*/
        .state('operationlog-list', {url: "/operationlog-list/{type:.*}",templateUrl: "views/operationlog-list.html",data: {pageTitle: '操作日志'},controller: "operationlog_controller"})
        
}]);

/* Init global settings and run the app */
MetronicApp.run(["$rootScope", "settings", "$state", function($rootScope, settings, $state) {
    $rootScope.$state = $state; // state to be accessed from view
}]);
MetronicApp.config(['$httpProvider', function ($httpProvider) {
    $httpProvider.responseInterceptors.push(function ($q) {
        return function (promise) {
            var deferred = $q.defer();
            promise.then(
                function (response) {
                    if (response.data == undefined || response.data.loginstatus == 'error') {
                        window.location.href = '../login';
                        return false;
                    }
//                  alert('success:'+JSON.stringify(response)); return response;
                },
                function (error) {
                    try{
                        $q.reject(error);
                    }catch(e){

                    }

                }
            );
            return promise;
        };
    });
}]);


var dataRoles = [
    {"name":"自己和下属","value":"2"},
    {"name":"所有人","value":"1"},
    {"name":"仅自己","value":"4"},
    {"name":"部门所有人","value":"3"}
];

var IDMark_Switch = "_switch",
    IDMark_Icon = "_ico",
    IDMark_Span = "_span",
    IDMark_Input = "_input",
    IDMark_Check = "_check",
    IDMark_Edit = "_edit",
    IDMark_Remove = "_remove",
    IDMark_Ul = "_ul",
    IDMark_A = "_a";


function initZtrees(treeid,nodes,addHoverDom,removeHoverDom,addDiyDom,onClick,beforeDrag,beforeDrop,zTreeOnDrop){
    var setting = {
        view: {
            dblClickExpand: true,
            showLine: false,
            selectedMulti: false,
            addDiyDom: addDiyDom,
            addHoverDom: addHoverDom,
            removeHoverDom: removeHoverDom
        },
        edit: {
            enable: true,
            editNameSelectAll: false,
            renameTitle : "编辑",
            removeTitle : "删除",
            showRenameBtn  : false,
            showRemoveBtn : false,
            drag: {
                prev: true,
                next: true,
                inner: false,
                isCopy : false,
                isMove: true
            }
        },
        data: {
            simpleData: {
                enable:true,
                idKey: "id",
                pIdKey: "pid",
                rootPId: ""
            }
        },
        callback: {
            onClick: onClick,
            beforeDrag: beforeDrag,
            beforeDrop: beforeDrop,
            onDrop: zTreeOnDrop
        }
    };
    return $.fn.zTree.init($("#"+treeid), setting, nodes);
}