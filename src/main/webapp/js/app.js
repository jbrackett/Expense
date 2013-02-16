'use strict';

// Declare app level module
var expenseApp = angular.module('expenseApp', []).
  config(['$routeProvider', function($routeProvider) {
    $routeProvider.when('/categories', {templateUrl: 'partials/category/list.html', controller: CategoryCtrl});
    $routeProvider.when('/category/edit/:id', {templateUrl: 'partials/category/edit.html', controller: CategoryEditCtrl});
    $routeProvider.otherwise({redirectTo: '/'});
  }]);

expenseApp.factory('alertService', function() {
  var alerts = [];
  var alertService = {
    add: function(alert) {
      alerts.push(alert);
    },
    
    close: function(index) {
      alerts.splice(index, 1);
    },
    
    getAlerts: function() {
      return alerts;
    }
  };
 
  return alertService;
});