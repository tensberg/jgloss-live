(function() {
    'use strict';

    angular.module('jglossApp', [ 'ngMaterial', 'ngRoute', 'jglossControllers' ]).config(
            [ '$mdThemingProvider', '$routeProvider', function($mdThemingProvider, $routeProvider) {
                $mdThemingProvider
                    .theme('default')
                    .primaryPalette('red')
                    .accentPalette('blue');

                $routeProvider
                    .when('/lookup', {
                        templateUrl : 'partials/lookup.html',
                        controller : 'LookupController',
                        controllerAs : 'lc'
                    })
                    .when('/gloss', {
                        templateUrl : 'partials/gloss.html',
                        controller : 'GlossController',
                        controllerAs : 'gc'
                    })
                    .when('/about', {
                        templateUrl : 'partials/about.html',
                    })
                    ;
            }]);
})();
