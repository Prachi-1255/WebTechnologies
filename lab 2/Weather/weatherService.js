app.factory('weatherService', ['$http', function ($http) {
    const API_KEY = '6552dada65670bd8758f930a2b25f1d6'; // Replace with your OpenWeatherMap API key
    const BASE_URL = 'https://api.openweathermap.org/data/2.5/weather';

    return {
        getWeather: function (city) {
            const url = `${BASE_URL}?q=${city}&appid=${API_KEY}&units=metric`;
            return $http.get(url);
        }
    };
}]);
