'use strict';

// Declare app level module
var expenseApp = angular.module('expenseApp', []).
  config(['$routeProvider', function($routeProvider) {
    $routeProvider.when('/categories', {templateUrl: 'partials/category/list.html', controller: CategoryCtrl});
    $routeProvider.when('/category/edit/:id', {templateUrl: 'partials/category/edit.html', controller: CategoryEditCtrl});
    $routeProvider.otherwise({redirectTo: '/'});
}]);