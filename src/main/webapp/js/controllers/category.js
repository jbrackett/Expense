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