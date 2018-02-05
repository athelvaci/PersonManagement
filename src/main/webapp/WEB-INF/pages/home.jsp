<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script src="resources/js/library/jquery.min.js"></script>
<script src="resources/js/library/bootstrap.min.js"></script>
<script src="resources/js/library/angular.min.1.0.7.js"></script>
<script src="resources/js/person/controller.js"></script>
<script src="resources/js/person/service.js"></script>
<script src="resources/js/person/directive.js"></script>
<script src="resources/js/person/factory.js"></script>
<script src="resources/js/person/app.js"></script>
<script src="resources/js/library/jquery.maskedinput.min.js"></script>
<script src='https://www.google.com/recaptcha/api.js'></script>
<link rel="stylesheet" href="resources/css/library/bootstrap.min.css"
	type="text/css" />
<link rel="stylesheet" href="resources/css/main.css" type="text/css" />

<title>Person Management</title>
</head>
<body ng-app="app">
	<div ng-controller="personController" class="tableField">



		<table class="table table-striped table-dark">
			<thead class="thead-dark">
				<tr>
					<th>Name</th>
					<th>Surname</th>
					<th>Phone</th>
					<th></th>
				</tr>
			</thead>
			<tr ng-repeat="person in persons">
				<td>{{person.name}}</td>
				<td>{{person.surname}}</td>
				<td>{{person.phoneNumber}}</td>
				<td>

					<button ng-click="openEditDialog(person)"
						class="btn btn-block btn-success">Update</button>
					<button ng-click="removePerson(person);"
						class="btn btn-block btn-danger">Delete</button>
				</td>
			</tr>
		</table>

		<modal title="PersonApp" visible="showModal">
		<div data-ng-show="showError" class="alert alert-danger">
			<ul ng-repeat="msg in errorMessages">
				<li>{{msg}}</li>
			</ul>
		</div>

		<form role="form">
			<div class="form-group">
				<label for="name">Name</label> <input class="form-control" id="name"
					value="currentPerson.name" ng-model="currentPerson.name" />
			</div>
			<div class="form-group">
				<label for="surname">Surname</label> <input class="form-control"
					id="surname" value="currentPerson.surname"
					ng-model="currentPerson.surname" />
			</div>
			<div class="form-group">
				<label for="phoneNumber">Phone</label> <input id="phoneNumber"
					class="form-control" type="text" value="currentPerson.phoneNumber"
					ng-model="currentPerson.phoneNumber" />
			</div>
			<div class="form-group" ng-show="dialogMode == 'save'">
				<div class="captcha">

					<div class="g-recaptcha"
						data-sitekey="6LczSEQUAAAAAGFRbjvf5eIKOLW7mMJjA48hArX4" data-callback="enableBtn"></div>
				</div>
			</div>
		
			
			<button type="submit" class="btn btn-primary  btn-block" id="button1"
				ng-click="saveOrUpdatePerson()">{{getDialogButtonStr()}}</button>
					<script>document.getElementById("button1").disabled = true;</script>
				<script>	function enableBtn(){
    document.getElementById("button1").disabled = false;
   }
				</script>
		</form>
		</modal>
		<div id="ajaxLoader">
			<img src="resources/img/loading.gif">
		</div>
		<button ng-click="openSaveDialog()" class="btn btn-block btn-primary">
			Add a new person</button>

	</div>
	


</body>
</html>