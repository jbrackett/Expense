'use strict';

function ExpenseCtrl($scope, $location, AlertService, CategoryService, ExpenseService) {
  var expenses = [];
  $scope.categoryCount = 0;
  
  CategoryService.count().then(function(data) {
    $scope.categoryCount = data;
  });
  
  ExpenseService.findAll().then(function(data) {
   expenses = $scope.expenses = data;
  });
  
  $scope.alerts = AlertService.getAlerts();

  $scope.closeAlert = function(index) {
    AlertService.close(index);
  }
  
  $scope.editUrl = function(id) {
    $location.path('/expense/' + id);
  } 
  
  $scope.deleteExpense = function($event, expense) {
    $event.stopPropagation();
    ExpenseService.delete(expense).then(function(success) {
      if (success) {
        expenses.splice(expenses.indexOf(expense), 1);
      }
    });
  }

}
ExpenseEditCtrl.$inject = ['$scope', '$location', 'AlertService', 'CategoryService', 'ExpenseService'];

function ExpenseNewCtrl($scope, AlertService, CategoryService, ExpenseService) {
  var expense = null;
  var categories = [];
  ExpenseService.getNew().then(function(data) {
    expense = $scope.expense = data;
  });
  
  CategoryService.findAll().then(function(data) {
    categories = $scope.categories = data;
  });
  
  $scope.alerts = AlertService.getAlerts();
  
  $scope.updateCategory = function(category) {
    expense.category = category;
  }
  
  $scope.editExpense = function() {
    ExpenseService.save(expense);
  };
  
  $scope.closeAlert = function(index) {
    AlertService.close(index);
  }
  
}
ExpenseNewCtrl.$inject = ['$scope', 'AlertService', 'CategoryService', 'ExpenseService'];

function ExpenseEditCtrl($scope, $routeParams, AlertService, CategoryService, ExpenseService) {
  var expense = null;
  var categories = [];
  ExpenseService.findOne($routeParams.id).then(function(data) {
    expense = $scope.expense = data;
  });
  
  CategoryService.findAll().then(function(data) {
    categories = $scope.categories = data;
  });
  
  $scope.alerts = AlertService.getAlerts();
  
  $scope.updateCategory = function(category) {
    expense.category = category;
  }
  
  $scope.editExpense = function() {
    ExpenseService.save(category);
  }
  
  $scope.closeAlert = function(index) {
    AlertService.close(index);
  }

}
ExpenseEditCtrl.$inject = ['$scope', '$routeParams', 'AlertService', 'CategoryService', 'ExpenseService'];