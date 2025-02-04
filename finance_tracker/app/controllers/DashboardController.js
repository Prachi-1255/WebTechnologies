app.controller('DashboardController', ['$scope', 'financeService', function ($scope, financeService) {
    $scope.totalIncome = financeService.getTotalIncome();
    $scope.totalExpenses = financeService.getTotalExpenses();
}]);
