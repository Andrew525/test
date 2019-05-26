'use strict'

var module = angular.module('demo.controllers', []);
module.controller("EmployeeController", [ "$scope", "EmployeeService",
    function($scope, EmployeeService) {

        $scope.empDto = {
            id : null,
            name : null,
            active : null,
            department : []
        };
        $scope.skills = [];

        EmployeeService.getEmployeeById(1).then(function(value) {
            console.log(value.data);
        }, function(reason) {
            console.log("error occured");
        }, function(value) {
            console.log("no callback");
        });

        $scope.saveEmployee = function() {
            $scope.empDto.department = "hgdjhddgj dgjdg g jh gdh";/*$scope.skills.map(skill => {
                return {id: null, name: skill};
            });*/
            EmployeeService.saveEmployee($scope.empDto).then(function() {
                console.log("works");
                EmployeeService.getPageEmployees().then(function(value) {
                    $scope.allEmployee= value.data;
                }, function(reason) {
                    console.log("error occured");
                }, function(value) {
                    console.log("no callback");
                });

                $scope.skills = [];
                $scope.empDto = {
                    id : null,
                    name : null,
                    active : null,
                    department : []
                };
            }, function(reason) {
                console.log("error occured");
            }, function(value) {
                console.log("no callback");
            });
        }
    } ]);

var app = angular.module("uigridApp", ["ui.grid", "ui.grid.pagination"]);
app.controller("uigridCtrl", function ($scope) {
    $scope.gridOptions = {
        paginationPageSizes: [25, 50, 75],
        paginationPageSize: 5,
        columnDefs: [
            { field: 'name' },
            { field: 'age' },
            { field: 'location' }
        ],
        onRegisterApi: function (gridApi) {
            $scope.grid1Api = gridApi;
        }
    };

    $scope.users = [
        { name: "Madhav Sai", age: 10, location: 'Nagpur' },
        { name: "Suresh Dasari", age: 30, location: 'Chennai' },
        { name: "Rohini Alavala", age: 29, location: 'Chennai' },
        { name: "Praveen Kumar", age: 25, location: 'Bangalore' },
        { name: "Sateesh Chandra", age: 27, location: 'Vizag' },
        { name: "Siva Prasad", age: 38, location: 'Nagpur' },
        { name: "Sudheer Rayana", age: 25, location: 'Kakinada' },
        { name: "Honey Yemineni", age: 7, location: 'Nagpur' },
        { name: "Mahendra Dasari", age: 22, location: 'Vijayawada' },
        { name: "Mahesh Dasari", age: 23, location: 'California' },
        { name: "Nagaraju Dasari", age: 34, location: 'Atlanta' },
        { name: "Gopi Krishna", age: 29, location: 'Repalle' },
        { name: "Sudheer Uppala", age: 19, location: 'Guntur' },
        { name: "Sushmita", age: 27, location: 'Vizag' }
    ];
    $scope.gridOptions.data = $scope.users;
});