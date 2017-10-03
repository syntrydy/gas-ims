// delete purchase
function deletePurchase() {
	var data = {
		id : $("#modalPurchaseId").val()
	}
	$.ajax({
		url : url + "/api/purchase",
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
//delete purchase item
function deletePurchaseItem() {
	var data = {
		id : $("#modalPurchaseItemId").val()
	}
	$.ajax({
		url : url + "/api/purchaseitem",
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

//add purchaseItem
function addPurchaseItem() {
	var itemData = {
		id : parseInt($("#itemSelect").val())
	}
	var measureData = {
			id : parseInt($("#measureSelect").val())
		}
	var purchaseData = {
			id : parseInt($("#purchaseSelect").val())
		}
	var data = {
		price : $("#priceAdd").val(),
		quantity : $('input[type=number][name=quantityAdd]').val(),
		item : itemData,
		unitOfMeasure :measureData,
		purchase :purchaseData
	}
	$.ajax({
		url : url + "/api/purchaseitem",
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
				showErrorMessage();
			}
		},
		error : function(e) {
			$('#add').modal('hide');
			showServerMessage();
		}
	});
}