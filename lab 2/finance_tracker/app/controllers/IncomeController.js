app.controller('IncomeController', ['$scope', 'financeService', function ($scope, financeService) {
    $scope.incomes = financeService.getIncomes();

    $scope.addIncome = function () {
        financeService.addIncome($scope.income);
        $scope.income = {}; // Clear the form
    };
}]);
