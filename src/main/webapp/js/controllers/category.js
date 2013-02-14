'use strict';

/* Controllers */

function CategoryCtrl($scope, $http) {
  var categories = [];
  var types = [];
  $http.get('/Expense/category').success(function(data) {
    categories = $scope.categories = data;
  });
  $http.get('/Expense/category/types').success(function(data) {
    types = $scope.types = data;
  });
    
  $scope.newCategory = {
    id: null,
    name: '',
    categoryType: 'STANDARD',
    policyRules: []
  };
  
  $scope.editedCategory = null;
  
  $scope.editCategory = function(category) {
    $scope.editedCategory = category;
  }
  
  $scope.doneEditing = function(category) {
    $scope.editedCategory = null;
    $http.put("/Expense/category", category);
  };
  
  $scope.cancelEditing = function(category) {
    $scope.editedCategory = null;
    $http.get("/Expense/category/" + category.id).success(function(data) {
     categories.splice(categories.indexOf(category), 1, data);
    });
  }
  
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
CategoryCtrl.$inject = ['$scope', '$http'];