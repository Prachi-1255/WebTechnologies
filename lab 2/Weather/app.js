const app = angular.module('weatherApp', []);

// Controller Definition
app.controller('WeatherController', ['$scope', 'weatherService', function ($scope, weatherService) {
    $scope.city = '';
    $scope.weatherData = null;
    $scope.error = '';
    $scope.favoriteCities = [];

    // Function to Get Weather for a City
    $scope.getWeather = function (cityName) {
        const city = cityName || $scope.city;
        $scope.error = '';

        weatherService.getWeather(city)
            .then(function (response) {
                $scope.weatherData = response.data;
            })
            .catch(function () {
                $scope.error = 'City not found or an error occurred.';
            });
    };

    // Function to Add City to Favorites
    $scope.addFavorite = function () {
        if ($scope.city && !$scope.favoriteCities.includes($scope.city)) {
            $scope.favoriteCities.push($scope.city);
        }
    };
}]);
