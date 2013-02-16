'use strict';

/* Controllers */

function CategoryCtrl($scope, $http, $location) {
  var categories = [];
  var types = [];
  $http.get('/Expense/category').success(function(data) {
    categories = $scope.categories = data;
  });
  $http.get('/Expense/category/types').success(function(data) {
    types = $scope.types = data;
  });
  
  $scope.editUrl = function(id) {
    $location.path('/category/edit/' + id);
  }
    
  $scope.newCategory = {
    id: null,
    name: '',
    categoryType: 'STANDARD',
    policyRules: []
  };
  
  $scope.deleteCategory = function(category) {
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
    });

    $scope.newCategory = {
      id: null,
      name: '',
      categoryType: 'STANDARD',
      policyRules: []
    };
  };
}
CategoryCtrl.$inject = ['$scope', '$http', '$location'];

function CategoryEditCtrl($scope, $http, $routeParams) {
  var category = null;
  $http.get('/Expense/category/' + $routeParams.id).success(function(data) {
    category = $scope.category = data;
  });
  
  $scope.message = "";
  $scope.messageType = "";
  
  $scope.editCategory = function() {
    $http.put('/Expense/category', category).success(function() {
      $scope.message = "Save successful";
      $scope.messageType = "Success!";
    });
  }
  
  $scope.closeMessage = function() {
    $scope.message = "";
    $scope.messageType = "";
  }
}
CategoryEditCtrl.$inject = ['$scope', '$http', '$routeParams'];