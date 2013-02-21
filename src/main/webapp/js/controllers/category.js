'use strict';

/* Controllers */

function CategoryCtrl($scope, $http, $location, alertService) {
  var categories = [];
  var types = [];
  $http.get('/Expense/category').success(function(data) {
    categories = $scope.categories = data;
  });
  $http.get('/Expense/category/types').success(function(data) {
    types = $scope.types = data;
  });
  
  $scope.alerts = alertService.getAlerts();

 
  $scope.closeAlert = function(index) {
    alertService.close(index);
  }
  
  $scope.editUrl = function(id) {
    $location.path('/category/edit/' + id);
  }
    
  $scope.newCategory = {
    id: null,
    name: '',
    categoryType: 'STANDARD',
    policyRules: []
  };
  
  $scope.deleteCategory = function($event, category) {
    $event.stopPropagation();
    $http.delete('/Expense/category/' + category.id).success(function() {
      categories.splice(categories.indexOf(category), 1);
    });
  }
  
  $scope.addCategory = function() {
    if (!$scope.newCategory.name.length) {
      return;
    }
   
    var category = {
      id: null,
      name: $scope.newCategory.name,
      categoryType: $scope.newCategory.categoryType,
      policyRules: $scope.newCategory.policyRules
    }
   
    $http.post('/Expense/category', category).success(function(id) {
      category.id = id;
      categories.push(category);
      var alert = {
        type: 'success',
        msg: 'Successfully created category ' + category.name
      }
      alertService.add(alert);
    });

    $scope.newCategory = {
      id: null,
      name: '',
      categoryType: 'STANDARD',
      policyRules: []
    };
  };
}
CategoryCtrl.$inject = ['$scope', '$http', '$location', 'alertService'];

function CategoryEditCtrl($scope, $http, $routeParams, alertService) {
  var category = null;
  $http.get('/Expense/category/' + $routeParams.id).success(function(data) {
    category = $scope.category = data;
  });
  
  $scope.alerts = alertService.getAlerts();
  
  $scope.editCategory = function() {
    $http.put('/Expense/category', category).success(function() {
      var alert = {
        type: 'success',
        msg: 'Successfully saved category ' + category.name
      }
      alertService.add(alert);
    });
  };
  
  $scope.closeAlert = function(index) {
    alertService.close(index);
  }
}
CategoryEditCtrl.$inject = ['$scope', '$http', '$routeParams', 'alertService'];