angular.module('app', []).controller('indexController', function($scope, $http){
   const contextPath='http://localhost:8189/app';

   console.log(123);

   $scope.loadProducts = function(){
     $http.get(contextPath + '/products')
       .then(function (response){

//         console.log(response.data) - позволяет просматривать данные, поступающие на фронт

         $scope.ProductList = response.data;
       });
   };

   $scope.deleteProduct = function (productId){
       $http.get(contextPath + '/products/delete/' + productId)
              .then(function (response){
                $scope.loadProducts();
              });
   }

      $scope.changePrice = function (productId, delta){
            $http({
              url:contextPath + '/products/changePrice/',
              method: 'GET',
              params: {
                productId: productId,
                delta: delta
              }
            }).then(function(response){
              $scope.loadProducts();
            });

      }

      $scope.createProductJson = function(){
            console.log($scope.newProductJson);
             $http.post(contextPath + '/products', $scope.newProductJson)
                          .then(function (response){
                            $scope.loadProducts();
                          });
      }

       $scope.sumTwoNumbers = function(){
          console.log($scope.calcAdd);
          $http({
              url:contextPath + '/calc/add',
              method: 'get',
              params: {
                a: $scope.calcAdd.a,
                b: $scope.calcAdd.b
              }
          }).then(function(response){
              alert('The summary is ' + response.data.value);
              $scope.calcAdd = null;
          });

       }

   $scope.loadProducts();
});