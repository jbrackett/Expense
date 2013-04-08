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

expenseApp.directive('receipt', function ($compile) {
 return {
   restrict:'EA',
   templateUrl:'partials/templates/receipt.html',
   replace:true,
   scope:{
     url:'=',
   },
   compile: function postLink(element, attrs) {
     return function(scope, element, attrs) {
       var canvas = element.find('canvas')[0];
       var ctx = canvas.getContext('2d');
       
       var animSpeed = 16;
      
       var width = canvas.clientWidth;
       var height = canvas.clientHeight;
       
       var img = new Image();   
       img.onload = function() {      
         ctx.drawImage(img, 0, 0, width, height);
       }
       img.src = attrs.url;
       
       var count = 0;
       var drawInterval;
       var draw = function() {
         var oneDegreeInRadians = 0.0174532925;
        
         var xpos = width/2;
         var ypos = height/2;
         
         ctx.clearRect(0, 0, width, height)
         ctx.translate(xpos, ypos);
         ctx.rotate(oneDegreeInRadians);
         ctx.translate(-xpos, -ypos);
         ctx.drawImage(img, 0, 0, width, height);
         
         count++;
         if (count === 90) {
           clearInterval(drawInterval);
           count = 0;
         }
       }
       
       scope.rotateImage = function(event) {
         event.preventDefault();
         if (drawInterval) {
           clearInterval(drawInterval);
         }
         drawInterval = setInterval(draw, animSpeed);
       }
     }
   }
 };
});

