'use strict';

// Declare app level module
angular.module('expenseApp', []).
  config(['$routeProvider', function($routeProvider) {
    $routeProvider.when('/categories', {templateUrl: 'partials/categories.html', controller: CategoryCtrl});
    $routeProvider.otherwise({redirectTo: '/'});
}]);