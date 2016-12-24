/* Modules, directives, and other code for the IDE */
var app = angular.module('IdeApp', ['ngMaterial']);

/* Color / theming configuration */
app.config(coloring);

/* Configuration for keyboard shortcut string creation */
app.filter('keyboardShortcut', function($window) {
    return function(str) {
      if (!str) return;
      var keys = str.split('-');
      var isOSX = /Mac OS X/.test($window.navigator.userAgent);

      var seperator = (!isOSX || keys.length > 2) ? '+' : '';

      var abbreviations = {
        M: isOSX ? '⌘' : 'Ctrl',
        A: isOSX ? 'Option' : 'Alt',
        S: 'Shift'
      };

      return keys.map(function(key, index) {
        var last = index == keys.length - 1;
        return last ? key : abbreviations[key];
      }).join(seperator);
    };
  });

/* Controller for the open dialog */
app.controller('OpenDialogController', function OpenDialogController($scope, $mdDialog) {
    
    $scope.cancel = function() {
        $mdDialog.cancel();
    };
    
});

/* Controller for main menu bar */
app.controller('MainMenuController', function MainMenuController($mdDialog, $scope) {
  
  this.settings = {} //Use this as a config for toggleable settings

    this.sampleAction = function(name, ev) {
      $mdDialog.show($mdDialog.alert()
        .title(name)
        .textContent('You triggered the "' + name + '" action')
        .ok('Great')
        .targetEvent(ev)
      );
    };
  
  $scope.openFile = function(ev) {
    $mdDialog.show({
      controller: "OpenDialogController",
      templateUrl: 'templates/open-dialog.html',
      parent: angular.element(document.body),
      targetEvent: ev,
      clickOutsideToClose: true,
      fullscreen: false // Only for -xs, -sm breakpoints.
    })
    .then(function(answer) {
      $scope.status = 'You said the information was "' + answer + '".';
    }, function() {
      $scope.status = 'You cancelled the dialog.';
    });
  };
  
});

/* Controller for editor tab controller */
app.controller('EditorTabController', function EditorTabController($scope) {
    $scope.currentNavItem = 'page1';
});