angular.module('app', []).controller('indexController', function($scope, $http){
   const contextPath='http://localhost:8189/app';

   $scope.loadProducts = function(){
     $http.get(contextPath + '/products')
       .then(function (response){
         $scope.productList = response.data;
       });
   };

   $scope.loadProducts();
});