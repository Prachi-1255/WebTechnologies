app.factory('financeService', function () {
    const incomes = [];
    const expenses = [];

    return {
        getIncomes: function () {
            return incomes;
        },
        getExpenses: function () {
            return expenses;
        },
        addIncome: function (income) {
            incomes.push(income);
        },
        addExpense: function (expense) {
            expenses.push(expense);
        },
        getTotalIncome: function () {
            return incomes.reduce((total, income) => total + income.amount, 0);
        },
        getTotalExpenses: function () {
            return expenses.reduce((total, expense) => total + expense.amount, 0);
        }
    };
});
