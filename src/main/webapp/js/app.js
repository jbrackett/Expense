'use strict';

// Declare app level module
var expenseApp = angular.module('expenseApp', []).
  config(['$routeProvider', function($routeProvider) {
    $routeProvider.when('/home', {templateUrl: 'partials/home.html'});
    $routeProvider.when('/categories', {templateUrl: 'partials/category/list.html', controller: CategoryCtrl});
    $routeProvider.when('/category/edit/:id', {templateUrl: 'partials/category/edit.html', controller: CategoryEditCtrl});
    $routeProvider.when('/expenses', {templateUrl: 'partials/expense/list.html', controller: ExpenseCtrl});
    $routeProvider.when('/expense/new', {templateUrl: 'partials/expense/edit.html', controller: ExpenseNewCtrl});
    $routeProvider.when('/expense/:id', {templateUrl: 'partials/expense/edit.html', controller: ExpenseEditCtrl});
    $routeProvider.when('/receipts', {templateUrl: 'partials/receipt/list.html', controller: ReceiptCtrl});
    $routeProvider.otherwise({redirectTo: '/home'});
  }]);

expenseApp.filter('capitalize', function() {
  return function(input, scope) {
    if(input) {
      return input.substring(0,1).toUpperCase()+input.substring(1).toLowerCase();
    }
  }
});

expenseApp.filter('policy', function() {
 return function(description, value) {
   if(description) {
     return description.replace('%i', value);
   }
 }
});