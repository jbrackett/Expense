expenseApp.directive('alert', function () {
  return {
    restrict:'EA',
    templateUrl:'partials/templates/alert.html',
    transclude:true,
    replace:true,
    scope:{
      type:'=',
      close:'&'
    }
  };
});

expenseApp.directive('policyCheckBox', function() {
  return {
    restrict: 'EA',
    templateUrl:'partials/templates/policyCheckBox.html',
    replace: true,
    scope: {
      policy: '=',
      update: '&'
    }
  }
});