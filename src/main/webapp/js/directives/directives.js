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