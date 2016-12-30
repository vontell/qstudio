var app = angular.module('LearnApp', ['ngMaterial']);

/* Main controller */
app.controller('MainController', function MainController($scope, $rootScope) {
    
    // Colors for the badge showing the lesson number
    $scope.badgeColors = ["#F44336", "#9C27B0", "#009688", "#3F51B5"];
  
    // Function to retrieve the appropriate badge background color
    $scope.getBackground = function(index) {
        return $scope.badgeColors[index % $scope.badgeColors.length];
    };
    
    // The list of lessons to display (to be downloaded from a REST service later in development)
    $scope.lessons = [
        {
            name: "Shor's Algorithm",
            description: "An interactive walkthrough of Shor's algorithm, with practice exercises.",
            link: "shor.html"
        }
    ];
    
    // Function which shows the lesson at index in $scope.lessons once a lesson is clicked
    $scope.showLesson = function(index) {
        
    };
    
    
});