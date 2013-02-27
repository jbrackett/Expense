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

expenseApp.directive('policyCheckBox', function($compile) {
  return {
    restrict: 'EA',
    replace: true,
    scope: {
      policy: '=',
      update: '&'
    },
    compile: function(element, attrs) {
      return function(scope, element, attrs) {
        scope.policy.description = scope.policy.description.replace('%i', '<input type="text" ng-model="policy.ruleValue">');
        var template = '<div class="checkbox"><input type="checkbox" ng-model="policy.active" ng-change="update()">'+scope.policy.description+'</span></div>';
        element.html(template).show();
        $compile(element.contents())(scope);
      };
    }
  }
});

expenseApp.directive('categorySelect', function() {
  return {
    restrict: 'EA',
    templateUrl:'partials/templates/categorySelect.html',
    replace: true,
    scope: {
      categories: '='
    }
  }
});

