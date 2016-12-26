/* Modules, directives, and other code for the IDE */
var app = angular.module('IdeApp', ['ngMaterial']);

/* Color / theming configuration */
app.config(coloring);

/* Main controller with settings within rootScope for cross-pane settings */
app.controller('MainController', function MainController($scope, $rootScope) {
    
    $rootScope.settings = {
        circuitMode: true,                              // True = Circuit Editor, False = Code Editor
        codeStyle: {                                    // ngStyle for the code editor
            background: "#263238",                      // Blue Grey 900
            color: "#FFFFFF",                           // Default font color of white
            'font-family': "'Ubuntu Mono', monospace",  // Default 'Ubuntu' monospace font
            'font-size': "18px"                         // Default 18px font size
        },
        circuitStyle: {                                 // ngStyle for the circuit editor
            background: "#ECEFF1"
        }
    }
    
});

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
app.controller('EditorTabController', function EditorTabController($scope, $rootScope) {
    
    // Updates the setting for which editor the user is viewing
    $scope.gotoCircuit = function(isCircuitView) {
        $rootScope.settings.circuitMode = isCircuitView;
    }
  
});

/* Main controller for the circuit layout editor */
app.controller('CircuitController', function CircuitController($scope, $rootScope) {
  
    $scope.newWireFabOpen = false;
  
    // Method which clears / instantiates elements on the board for a blank project
    $scope.createBoard = function() {
        
        var boardContainer = null;
        
    };
  
});