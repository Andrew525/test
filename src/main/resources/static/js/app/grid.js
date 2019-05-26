var app = angular.module('app', ['ngTouch', 'ui.grid', 'ui.grid.pagination', 'ui.bootstrap']);

app.controller('MainCtrl', [
    '$scope', '$http', 'uiGridConstants', '$uibModal', function($scope, $http, uiGridConstants, $modal) {

        var pgOptions = {
            pageNumber: 1,
            pageSize: 25,
            sort: null,
            search: ""
        };

        $scope.gridOptions = {
            paginationPageSizes: [10, 25],
            paginationPageSize: 10,
            useExternalPagination: true,
            rowHeight: 39,
            useExternalSorting: true,
            columnDefs: [
                { name: 'id'},
                { name: 'name', enableSorting: false },
                { name: 'active', enableSorting: false },
                { name: 'department', enableSorting: false },
                { name: 'ACTION', enableSorting: false , cellTemplate:
                        `<div class="container">
                        <div class="btn-group">
	                        <button type="button" class="btn btn-info" ng-click="grid.appScope.viewRecord(row.entity.id)"><i class="glyphicon glyphicon-eye-open"></i></button>
	                        <button type="button" class="btn btn-medium-default" ng-click="grid.appScope.editRecord(row.entity.id)"><i class="glyphicon glyphicon-pencil"></i></button>
	                        <button type="button" class="btn btn-danger" ng-click="grid.appScope.deletRecord(row.entity.id)"><i class="glyphicon glyphicon-trash"></i></button>
                        </div> </div>`}
                        // '<div class="ui-grid-cell-contents" title="TOOLTIP">{{grid.appScope.cumulative(grid, row)}}</div>'},
            ],
            onRegisterApi: function(gridApi) {
                $scope.gridApi = gridApi;
                gridApi.pagination.on.paginationChanged($scope, getPage );
            }
        };


        var getPage = function(newPage, pageSize) {
            var url = '/employees/?page=' + newPage +"&limit="+pageSize;
            if(pgOptions.search) url +="&search=" + pgOptions.search;
            return $http.get(url)
                .then(function (response) {
                    // var firstRow = (newPage - 1) * pageSize;
                    $scope.gridOptions.totalItems = response.data.totalItems;
                    $scope.gridOptions.data = response.data.pageItems//.slice(firstRow, firstRow + pageSize);
                    // return response.data.slice(firstRow, firstRow + pageSize);
                });
        };

        getPage(1, $scope.gridOptions.paginationPageSize);

        $scope.applyFilter = function(searchValue){
            pgOptions.search = searchValue;
        };
        $scope.searchByName = function(searchValue){
            getPage(1, 10);
        };

        // button handlers
        $scope.deletRecord = function(id) {
            if (confirm('Are you sure you want to delete this?')) {
                $http.delete("/employees/"+id)
                    .then(function(response){
                        console.log(response);
                    });
            }

        };
        $scope.addRecord = function(){
            modalInstance = $modal.open({
                animation: false,
                templateUrl: 'view/add_record',
                controller: 'addEmpCtrl',
                scope: $scope,
                size: '',
                resolve: {
                    record: function () {}
                }
            });

        };
        $scope.editRecord = function(id){
                if(id > 0) {
                    $http.get("/employees/"+id)
                        .then(function(response){
                            modalInstance = $modal.open({
                                animation: false,
                                templateUrl: 'view/update_record',
                                controller: 'updateEmpCtrl',
                                scope: $scope,
                                size: '',
                                resolve: {
                                    record: function () {
                                        return response.data;
                                    }
                                }
                            });
                        });
                }
        };

        $scope.viewRecord = function(id){
            if(id > 0) {
                $http.get("/employees/"+id)
                    .then(function(response){
                        modalInstance = $modal.open({
                            animation: false,
                            templateUrl: 'view/view_record',
                            controller: 'empViewCtrl',
                            scope: $scope,
                            size: '',
                            resolve: {
                                record: function () {
                                    return response.data;
                                }
                            }
                        });
                    });

            }
        }
    }
]);

app.controller('empViewCtrl',  ['$scope', '$http', 'record', function($scope, $http, record) {
    console.log(record);
    function init(){ $scope.employee = record; }
    init();
}]);

app.controller('addEmpCtrl',  ['$scope', '$http', 'record', function($scope, $http, record) {
    $scope.departments = [];
        $http.get('/departments').then(function(response){
        $scope.departments = response.data ;
    });
    $scope.saveEmp = function () {
        $scope.datas = {};
        if(!angular.isDefined($scope.name) || $scope.name === '') {
            alert('employee name is empty');
            return;
        }
        // else if(!angular.isDefined($scope.active)) {
        //     alert('employee active is empty');
        //     return;
        // }
        else if(!angular.isDefined($scope.dpId) || $scope.dpId === '') {
            alert('employee department is empty');
            return;
        } else {
            $scope.datas.name = $scope.name;
            $scope.datas.active = !$scope.active ? false : true;
            $scope.datas.dpId = $scope.dpId;
            console.log($scope.datas);
        }
        $scope.$close();
        $http.post('/employees', $scope.datas);
    };

}]);

app.controller('updateEmpCtrl',  ['$scope', '$http', 'record', function($scope, $http, record) {
    $scope.employee = {};
    $scope.departments = [];
        function init(){
        $scope.employee.id = parseInt(record.id);
        $scope.employee.name = record.name;
        $scope.employee.active = record.active;
        $scope.employee.department = record.department;
        $scope.employee.dpId = record.dpId;
        $http.get('/departments').then(function(response){
            $scope.departments = response.data ;
        });
    }
    $scope.updateEmp = function () {
        if(!angular.isDefined($scope.employee.name) || $scope.employee.name === '') {
            alert('employee name is empty');
            return;
        }
        // else if(!angular.isDefined($scope.employee.active) || $scope.employee.active === '') {
        //     alert('employee active is empty');
        //     return;
        else if(!angular.isDefined($scope.employee.department) || $scope.employee.department === '') {
            alert('employee department is empty');
            return;
        }
        $scope.$close();
        $http.put('/employees/'+$scope.employee.id, $scope.employee);
        // $scope.updateRecord($scope.employee);
    }
    init();

}]);