// delete sale
function deleteSale() {
	var data = {
		id : $("#modalSaleId").val()
	}
	$.ajax({
		url : url + "/api/sale",
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
//delete sale item
function deleteSaleItem() {
	var data = {
		id : $("#modalSaleItemId").val()
	}
	$.ajax({
		url : url + "/api/saleitem",
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

//add sale item
function addSaleItem() {
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
		url : url + "/api/saleitem",
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