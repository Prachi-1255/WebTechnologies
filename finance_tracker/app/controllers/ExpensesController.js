app.controller('ExpensesController', ['$scope', 'financeService', function ($scope, financeService) {
    $scope.expenses = financeService.getExpenses();

    $scope.addExpense = function () {
        financeService.addExpense($scope.expense);
        $scope.expense = {}; // Clear the form
    };
}]);
