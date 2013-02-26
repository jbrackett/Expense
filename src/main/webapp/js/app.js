'use strict';

// Declare app level module
var expenseApp = angular.module('expenseApp', []).
  config(['$routeProvider', function($routeProvider) {
    $routeProvider.when('/home', {templateUrl: 'partials/home.html'});
    $routeProvider.when('/categories', {templateUrl: 'partials/category/list.html', controller: CategoryCtrl});
    $routeProvider.when('/category/edit/:id', {templateUrl: 'partials/category/edit.html', controller: CategoryEditCtrl});
    $routeProvider.otherwise({redirectTo: '/home'});
  }]);

expenseApp.factory('AlertService', function($timeout) {
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

expenseApp.factory('CategoryService', function($http, AlertService) {
  var categoryService = {
    findAll: function() {
      return $http.get('/Expense/category').then(function(response) {
        return response.data;
      });
    },
    
    findOne: function(id) {
      return $http.get('/Expense/category/' + id).then(function(response) {
        return response.data;
      });
    },
    
    save: function(category) {
      if(category.id == null) {
        return $http.post('/Expense/category', category).then(function(response) {
          var alert = {
            type: 'success',
            msg: 'Successfully created category ' + category.name
          }
          AlertService.add(alert);
          category.id = response.data;
          return category;
        }, function(response) {
          var alert = {
            type: 'error',
            msg: 'Unable to save category ' + category.name + ' at this time.'
          }
          AlertService.add(alert);
        });
      }
      else {
        return $http.put('/Expense/category', category).then(function(response) {
          var alert = {
            type: 'success',
            msg: 'Successfully saved category ' + category.name
          }
          AlertService.add(alert);
        }, function(response) {
          var alert = {
            type: 'error',
            msg: 'Unable to save category ' + category.name + ' at this time.'
          }
          AlertService.add(alert);
        });
      }
    },
    
    getTypes: function() {
      return $http.get('/Expense/category/types').then(function(response) {
        return response.data;
      });
    }
  };
  
  return categoryService;
});

expenseApp.filter('capitalize', function() {
  return function(input, scope) {
    if(input) {
      return input.substring(0,1).toUpperCase()+input.substring(1).toLowerCase();
    }
  }
});