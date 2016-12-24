/* Coloring configurations / theming for the application */

/* Function to be passed into module.config() */
var coloring = function($mdThemingProvider) {

  $mdThemingProvider.theme('default')
    .primaryPalette('orange')
    .accentPalette('teal');
  
}