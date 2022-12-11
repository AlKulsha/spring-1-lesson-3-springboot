angular.module('app', []).controller('indexController', function($scope, $http){
   const contextPath='http://localhost:8189/app/api/v1';

   console.log(123);

       $scope.loadProducts = function (pageIndex = 1) {
           $http({
               url: contextPath + '/products',
               method: 'GET',
               params: {
                   title_part: $scope.filter ? $scope.filter.title_part : null,
                   min_price: $scope.filter ? $scope.filter.min_price : null,
                   max_price: $scope.filter ? $scope.filter.max_price : null
               }
           }).then(function (response) {
               $scope.ProductList = response.data.content;
           });
       };

   $scope.deleteProduct = function (productId){
       $http.delete(contextPath + '/products/' + productId)
              .then(function (response){
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
//
//       $scope.sumTwoNumbers = function(){
//          console.log($scope.calcAdd);
//          $http({
//              url:contextPath + '/calc/add',
//              method: 'get',
//              params: {
//                a: $scope.calcAdd.a,
//                b: $scope.calcAdd.b
//              }
//          }).then(function(response){
//              alert('The summary is ' + response.data.value);
//              $scope.calcAdd = null;
//          });
//
//       }

   $scope.loadProducts();
});