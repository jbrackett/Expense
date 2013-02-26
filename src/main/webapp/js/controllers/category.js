'use strict';

/* Controllers */

function CategoryCtrl($scope, $http, $location, AlertService, CategoryService) {
  var categories = [];
  var types = [];
  
  CategoryService.findAll().then(function(data) {
    categories = $scope.categories = data;
  });
  
  CategoryService.getTypes().then(function(data) {
   types = $scope.types = data;
  });
  
  $scope.alerts = AlertService.getAlerts();

  $scope.closeAlert = function(index) {
    AlertService.close(index);
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
      var alert = {
        type: 'success',
        msg: 'Successfully deleted category ' + category.name
      }
      AlertService.add(alert);
    }).error(function() {
      var alert = {
        type: 'error',
        msg: 'Unable to delete ' + category.name + ' at this time.'
      }
      AlertService.add(alert);
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
   
    CategoryService.save(category).then(function(data) {
      $scope.categories.push(data);
      
      $scope.newCategory = {
        id: null,
        name: '',
        categoryType: 'STANDARD',
        policyRules: []
      };
    });
    
  };
}
CategoryCtrl.$inject = ['$scope', '$http', '$location', 'AlertService', 'CategoryService'];

function CategoryEditCtrl($scope, $http, $routeParams, AlertService, CategoryService) {
  var category = null;
  CategoryService.findOne($routeParams.id).then(function(data) {
    category = $scope.category = data;
  });
  
  $scope.alerts = AlertService.getAlerts();
  
  $scope.editCategory = function() {
    CategoryService.save(category);
  };
  
  $scope.closeAlert = function(index) {
    AlertService.close(index);
  }
  
  $scope.updatePolicy = function(policy) {
    if (policy.active) {
      category.policyRules.push(policy);
      category.availablePolicyRules.splice(category.availablePolicyRules.indexOf(policy), 1);
    }
    else {
      category.policyRules.splice(category.policyRules.indexOf(policy), 1);
      category.availablePolicyRules.push(policy);
    }  
  }
}
CategoryEditCtrl.$inject = ['$scope', '$http', '$routeParams', 'AlertService', 'CategoryService'];