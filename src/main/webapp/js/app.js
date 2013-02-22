'use strict';

// Declare app level module
var expenseApp = angular.module('expenseApp', []).
  config(['$routeProvider', function($routeProvider) {
    $routeProvider.when('/home', {templateUrl: 'partials/home.html'});
    $routeProvider.when('/categories', {templateUrl: 'partials/category/list.html', controller: CategoryCtrl});
    $routeProvider.when('/category/edit/:id', {templateUrl: 'partials/category/edit.html', controller: CategoryEditCtrl});
    $routeProvider.otherwise({redirectTo: '/home'});
  }]);

expenseApp.factory('alertService', function($timeout) {
  var alerts = [];
  var alertService = {
    add: function(alert) {
      alerts.push(alert);
      $timeout(function() {
        alerts.splice(alerts.indexOf(alert), 1);
      }, 2000);
    },
    
    close: function(index) {
      alerts.splice(index, 1);
    },
    
    getAlerts: function() {
      return alerts;
    },
    
    clear: function() {
      alerts = [];
    }
  };
 
  return alertService;
});