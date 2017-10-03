// delete movement
function deleteMovement() {
	var data = {
		id : $("#modalMovementId").val()
	}
	$.ajax({
		url : url + "/api/movement",
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
//delete item movement
function deleteItemMovement() {
	var data = {
		id : $("#modalItemMovementId").val()
	}
	$.ajax({
		url : url + "/api/itemmovement",
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

//add itemMovement
function addItemMovement() {
	var purchaseItemData = {
		id : parseInt($("#purchaseItemSelect").val())
	}
	var stockMovementData = {
			id : parseInt($("#movementSelect").val())
		}
	var data = {
		quantity : $('input[type=number][name=quantityAdd]').val(),
		purchaseItem : purchaseItemData,
		stockMovement : stockMovementData
	}
	$.ajax({
		url : url + "/api/itemmovement",
		contentType : "application/json",
		type : 'POST',
		data : JSON.stringify(data),
		success : function(result) {
			if (result.status) {
				$('#add').modal('hide');
				showSuccesMessage();
				setTimeout("window.location.reload(true)", 1000);
			} else {
				$('#add').modal('hide');
				showErrorMessage(result.data);
			}
		},
		error : function(e) {
			$('#add').modal('hide');
			showServerMessage();
		}
	});
}