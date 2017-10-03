//password reset
function resetPassword() {
	$('#mprogress').circleProgress({
		value : 1,
		size : 100,
		fill : {
			gradient : [ "green", "green" ]
		}
	});

	var getUrl = window.location;
	var url = getUrl.protocol + "//" + getUrl.host;
	var userData = {
		email : $("#email").val()
	}
	$.ajax({
		url : url + "/api/reset",
		contentType : "application/json",
		type : 'POST',
		data : JSON.stringify(userData),
		success : function(result) {
			if (result.status) {
				showSuccesMessage();
				$('#mprogress').modal('hide');
				setTimeout("window.location.reload(true)", 1000);
			} else {
				showErrorMessage();
				$('#mprogress').modal('hide');
			}
		},
		error : function(e) {
			showServerMessage();
			$('#mprogress').modal('hide');
		}
	});

}
// sucess message
function showSuccesMessage() {
	$.notify({
		title : "Well done : ",
		message : "Action performed successfully!",
		icon : 'fa fa-check'
	}, {
		type : "success"
	});
}
// error message
function showErrorMessage() {
	$.notify({
		title : "Error: ",
		message : "some error occur when processing your request",
		icon : 'fa fa-check'
	}, {
		type : "warning"
	});
}

//second error message
function showErrorMessage(msg) {
	if (msg === undefined) {
		msg = "Some error occur on server when processing your request";
     } 
	$.notify({
		title : "Error: ",
		message : msg,
		icon : 'fa fa-check'
	}, {
		type : "warning"
	});
}
// server error message

function showServerMessage() {
	$.notify({
		title : "Oups Oups: ",
		message : "The ressource you request may not exist on server",
		icon : 'fa fa-check'
	}, {
		type : "warning"
	});
}
// delete user
function deleteUser() {
	var userData = {
		id : $("#userId").val()
	}
	$.ajax({
		url : url + "/api/user",
		contentType : "application/json",
		type : 'DELETE',
		data : JSON.stringify(userData),
		success : function(result) {
			if (result.status) {
				$('#delete').modal('hide');
				showSuccesMessage();
				setTimeout("window.location.reload(true)", 1000);
			} else {
				$('#delete').modal('hide');
				showErrorMessage();
			}
		},
		error : function(e) {
			$('#delete').modal('hide');
			showServerMessage();
		}
	});
}
// delete role
function deleteRole() {
	var roleData = {
		id : $("#modalRoleId").val()
	}
	$.ajax({
		url : url + "/api/role",
		contentType : "application/json",
		type : 'DELETE',
		data : JSON.stringify(roleData),
		success : function(result) {
			if (result.status) {
				$('#delete').modal('hide');
				showSuccesMessage();
				setTimeout("window.location.reload(true)", 1000);
			} else {
				$('#delete').modal('hide');
				showErrorMessage();
			}
		},
		error : function(e) {
			$('#delete').modal('hide');
			showServerMessage();
		}
	});
}
// delete customer
function deleteCustomer() {
	var customerData = {
		id : $("#modalCustomerId").val()
	}
	$.ajax({
		url : url + "/api/customer",
		contentType : "application/json",
		type : 'DELETE',
		data : JSON.stringify(customerData),
		success : function(result) {
			if (result.status) {
				$('#delete').modal('hide');
				showSuccesMessage();
				setTimeout("window.location.reload(true)", 1000);
			} else {
				$('#delete').modal('hide');
				showErrorMessage();
			}
		},
		error : function(e) {
			$('#delete').modal('hide');
			showServerMessage();
		}
	});
}

// delete currency
function deleteCurrency() {
	var currencyData = {
		id : $("#modalCurrencyId").val()
	}
	$.ajax({
		url : url + "/api/currency",
		contentType : "application/json",
		type : 'DELETE',
		data : JSON.stringify(currencyData),
		success : function(result) {
			if (result.status) {
				$('#delete').modal('hide');
				showSuccesMessage();
				setTimeout("window.location.reload(true)", 1000);
			} else {
				$('#delete').modal('hide');
				showErrorMessage();
			}
		},
		error : function(e) {
			$('#delete').modal('hide');
			showServerMessage();
		}
	});
}
// delete provider
function deleteProvider() {
	var providerData = {
		id : $("#modalProviderId").val()
	}
	$.ajax({
		url : url + "/api/provider",
		contentType : "application/json",
		type : 'DELETE',
		data : JSON.stringify(providerData),
		success : function(result) {
			if (result.status) {
				$('#delete').modal('hide');
				showSuccesMessage();
				setTimeout("window.location.reload(true)", 1000);
			} else {
				$('#delete').modal('hide');
				showErrorMessage();
			}
		},
		error : function(e) {
			$('#delete').modal('hide');
			showServerMessage();
		}
	});
}
// delete store
function deleteStore() {
	var storeData = {
		id : $("#modalStoreId").val()
	}
	$.ajax({
		url : url + "/api/store",
		contentType : "application/json",
		type : 'DELETE',
		data : JSON.stringify(storeData),
		success : function(result) {
			if (result.status) {
				$('#delete').modal('hide');
				showSuccesMessage();
				setTimeout("window.location.reload(true)", 1000);
			} else {
				$('#delete').modal('hide');
				showErrorMessage();
			}
		},
		error : function(e) {
			$('#delete').modal('hide');
			showServerMessage();
		}
	});
}
//delete point of sale
function deletePointOfSale() {
	var data = {
		id : $("#modalPointOfSaleId").val()
	}
	$.ajax({
		url : url + "/api/pofsale",
		contentType : "application/json",
		type : 'DELETE',
		data : JSON.stringify(data),
		success : function(result) {
			if (result.status) {
				$('#delete').modal('hide');
				showSuccesMessage();
				setTimeout("window.location.reload(true)", 1000);
			} else {
				$('#delete').modal('hide');
				showErrorMessage();
			}
		},
		error : function(e) {
			$('#delete').modal('hide');
			showServerMessage();
		}
	});
}
// delete group
function deleteGroup() {
	var groupData = {
		id : $("#modalGroupId").val()
	}
	$.ajax({
		url : url + "/api/group",
		contentType : "application/json",
		type : 'DELETE',
		data : JSON.stringify(groupData),
		success : function(result) {
			if (result.status) {
				$('#delete').modal('hide');
				showSuccesMessage();
				setTimeout("window.location.reload(true)", 1000);
			} else {
				$('#delete').modal('hide');
				showErrorMessage();
			}
		},
		error : function(e) {
			$('#delete').modal('hide');
			showServerMessage();
		}
	});
}

// add user
function addUser() {
	var userData = {
			id : -1,
			phone : $("#phoneAdd").val(),
			fullName : $("#fullNameAdd").val(),
			password : $("#passwordAdd").val(),
			passwordConfirm : $("#repasswordAdd").val(),
			email : $("#emailAdd").val(),
			address : $("#addressAdd").val()
	}
	$.ajax({
		url : url + "/api/user",
		contentType : "application/json",
		type : 'POST',
		data : JSON.stringify(userData),
		success : function(result) {
			if (result.status) {
				$('#add').modal('hide');
				showSuccesMessage();
				setTimeout("window.location.reload(true)", 1000);
			} else {
				$('#add').modal('hide');
				showErrorMessage();
			}
		},
		error : function(e) {
			$('#add').modal('hide');
			showServerMessage();
		}
	});
}

// add role
function addRole() {
	var roleData = {
		name : $("#nameAdd").val(),
		description : $("#descriptionAdd").val()
	}
	$.ajax({
		url : url + "/api/role",
		contentType : "application/json",
		type : 'POST',
		data : JSON.stringify(roleData),
		success : function(result) {
			if (result.status) {
				$('#add').modal('hide');
				showSuccesMessage();
				setTimeout("window.location.reload(true)", 1000);
			} else {
				$('#add').modal('hide');
				showErrorMessage();
			}
		},
		error : function(e) {
			$('#add').modal('hide');
			showServerMessage();
		}
	});
}

// add currency
function addCurrency() {
	var currencyData = {
		code : $("#codeAdd").val(),
		value : $("#valueAdd").val()
	}
	$.ajax({
		url : url + "/api/currency",
		contentType : "application/json",
		type : 'POST',
		data : JSON.stringify(currencyData),
		success : function(result) {
			if (result.status) {
				$('#add').modal('hide');
				showSuccesMessage();
				setTimeout("window.location.reload(true)", 1000);
			} else {
				$('#add').modal('hide');
				showErrorMessage();
			}
		},
		error : function(e) {
			$('#add').modal('hide');
			showServerMessage();
		}
	});
}

// add group
function addGroup() {
	var groupData = {
		name : $("#nameAdd").val(),
		description : $("#descriptionAdd").val()
	}
	$.ajax({
		url : url + "/api/group",
		contentType : "application/json",
		type : 'POST',
		data : JSON.stringify(groupData),
		success : function(result) {
			if (result.status) {
				$('#add').modal('hide');
				showSuccesMessage();
				setTimeout("window.location.reload(true)", 1000);
			} else {
				$('#add').modal('hide');
				showErrorMessage();
			}
		},
		error : function(e) {
			$('#add').modal('hide');
			showServerMessage();
		}
	});
}
