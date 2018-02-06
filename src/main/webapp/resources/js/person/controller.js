
var app = angular.module('app', ['service', 'sharedServices']);

	app.controller('personController', ['$scope', '$timeout', 'generalUtility', 'personService',
	    function($scope, $timeout, generalUtility, personService) {
			// initialization
			$scope.showModal = false;
			$scope.currentPerson = {};
			$scope.showError = false;
			$scope.errorMessages = [];
			$scope.persons = [];
			

			$timeout(function() {
				loadAllPersons();
			});
			
			//public functions
			$scope.openEditDialog = function(person) {
				var clonePerson = jQuery.extend({}, person);
				$scope.dialogMode = "edit";
				$scope.showError = false;
				$scope.currentPerson = clonePerson;
				openDialog();
			};
			
			$scope.openSaveDialog = function() {
				$scope.dialogMode = "save";
				$scope.showError = false;
				$scope.currentPerson = {};
				
				openDialog();
			};
			
			$scope.removePerson = function(person) {
				if(window.confirm("Are you sure?")) {
					personService.remove(person.id)
						.then(function(data) {
							generalUtility.removeItemById($scope.persons, person);
						}, handleException);
				}
			};
			
			$scope.saveOrUpdatePerson = function() {
				var person = $scope.currentPerson;
				person.phoneNumber = $('#phoneNumber').val();
				if(!person.id) {
					//save mode
					personService.save(person)
						.then(function(data) {
							person.id = data.params.id;
							$scope.persons.push(person);
							closeDialog();
						}, handleExceptionWithDialog);
				} else {
					//update mode
					personService.update(person)
						.then(function(data) {
							generalUtility.updateItemById($scope.persons, person);
							closeDialog();
						}, handleExceptionWithDialog);
				}
			};
			
			$scope.getDialogButtonStr = function() {
				if($scope.dialogMode == "save"){
					return "Save";
				} else if($scope.dialogMode == "edit") {
					return "Update";
				}
			};
			
			
			function openDialog() {
				$scope.showModal = true;
			}
			
			function closeDialog() {
				$scope.showModal = false;
			}
			
			function handleException(data) {
				alert(data.content[0]);
			}
			
			function handleExceptionWithDialog(data) {
				showError(data.content);
			}
			
			function showError(msgArr) {
				$scope.showError = true;
				$scope.errorMessages = msgArr;
			}
			
			
			
			function loadAllPersons() {
				personService.findAll()
					.then(function(data) {
						$scope.persons = data.params;
					}, handleException);
			}
	}]);