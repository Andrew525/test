'use strict'

angular.module('demo.services', []).factory('EmployeeService',
    [ "$http", "CONSTANTS", function($http, CONSTANTS) {
        var service = {};
        service.getEmployeeById = function(userId) {
            return $http.get(CONSTANTS.getEmployeeByIdUrl + userId);
        }
        service.getPageEmployees = function() {
            return $http.get(CONSTANTS.getPageEmployees + "/1");
        }
        service.getListEmployees = function(empDto) {
            return $http.get(CONSTANTS.saveEmployee, empDto);
        }
        service.saveEmployee = function(empDto) {
            return $http.post(CONSTANTS.saveEmployee, empDto);
        }
        service.updateEmployee = function(empDto) {
            return $http.put(CONSTANTS.saveEmployee, empDto);
        }
        service.deleteEmployee = function(empDto) {
            return $http.delete(CONSTANTS.saveEmployee, empDto);
        }

        return service;
    } ]);