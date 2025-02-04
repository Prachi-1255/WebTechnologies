const app = angular.module('financeApp', ['ngRoute']);

app.config(['$routeProvider', function ($routeProvider) {
    $routeProvider
        .when('/dashboard', {
            templateUrl: 'app/templates/dashboard.html',
            controller: 'DashboardController'
        })
        .when('/expenses', {
            templateUrl: 'app/templates/expenses.html',
            controller: 'ExpensesController'
        })
        .when('/income', {
            templateUrl: 'app/templates/income.html',
            controller: 'IncomeController'
        })
        .otherwise({
            redirectTo: '/dashboard'
        });
}]);
