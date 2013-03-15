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

expenseApp.factory('CurrencyService', function($http) {
 var currencyService = {
   findAll: function(alert) {
     return $http.get("/Expense/currency").then(function(response) {
       return response.data;
     });
   }
 };

 return currencyService;
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
    
    count: function() {
      return $http.get('/Expense/category/count').then(function(response) {
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
    
    delete: function(category) {
      return $http.delete('/Expense/category/' + category.id).then(function() {
        var alert = {
          type: 'success',
          msg: 'Successfully deleted category ' + category.name
        }
        AlertService.add(alert);
        return true;
      }, function() {
        var alert = {
          type: 'error',
          msg: 'Unable to delete ' + category.name + ' at this time.'
        }
        AlertService.add(alert);
        return false;
      });
    },
    
    getTypes: function() {
      return $http.get('/Expense/category/types').then(function(response) {
        return response.data;
      });
    }
  };
  
  return categoryService;
});

expenseApp.factory('ExpenseService', function($http, AlertService) {
  var expenseService = {
    findAll: function() {
      return $http.get('/Expense/expense').then(function(response) {
        return response.data;
      });
    },
    
    getNew: function() {
      return $http.get('/Expense/expense/new').then(function(response) {
        return response.data;
      });
    },
    
    findOne: function(id) {
      return $http.get('/Expense/expense/' + id).then(function(response) {
        return response.data;
      });
    },
    
    save: function(expense) {
      if(expense.id == null) {
        return $http.post('/Expense/expense', expense).then(function(response) {
          var alert = {
            type: 'success',
            msg: 'Successfully created expense ' + expense.name
          }
          AlertService.add(alert);
          expense.id = response.data;
          return expense;
        }, function(response) {
          var alert = {
            type: 'error',
            msg: 'Unable to save expense ' + expense.name + ' at this time.'
          }
          AlertService.add(alert);
        });
      }
      else {
        return $http.put('/Expense/expense', expense).then(function(response) {
          var alert = {
            type: 'success',
            msg: 'Successfully saved expense ' + expense.name
          }
          AlertService.add(alert);
        }, function(response) {
          var alert = {
            type: 'error',
            msg: 'Unable to save expense ' + expense.name + ' at this time.'
          }
          AlertService.add(alert);
        });
      }
    },
    
    delete: function(expense) {
      return $http.delete('/Expense/expense/' + expense.id).then(function() {
        var alert = {
          type: 'success',
          msg: 'Successfully deleted expense ' + expense.name
        }
        AlertService.add(alert);
        return true;
      }, function() {
        var alert = {
          type: 'error',
          msg: 'Unable to delete ' + expense.name + ' at this time.'
        }
        AlertService.add(alert);
        return false;
      });
    }
  };
  
  return expenseService;
});
