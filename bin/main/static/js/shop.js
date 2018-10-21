// delete category
function deleteCategory() {
	var categoryData = {
		id : $("#modalCategoryId").val()
	}
	$.ajax({
		url : url + "/api/category",
		contentType : "application/json",
		type : 'DELETE',
		data : JSON.stringify(categoryData),
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

// delete item
function deleteItem() {
	var itemData = {
		id : $("#modalItemId").val()
	}
	$.ajax({
		url : url + "/api/item",
		contentType : "application/json",
		type : 'DELETE',
		data : JSON.stringify(itemData),
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

// delete measure
function deleteMeasure() {
	var data = {
		id : $("#modalMeasureId").val()
	}
	$.ajax({
		url : url + "/api/measure",
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

// add category
function addCategory() {
	var data = {
		name : $("#nameAdd").val(),
		description : $("#descriptionAdd").val()
	}
	$.ajax({
		url : url + "/api/category",
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

// add item
function addItem() {
	var catData = {
		id : parseInt($("#categorySelect").val())
	}
	var data = {
		name : $("#nameAdd").val(),
		description : $("#descriptionAdd").val(),
		threshold : $('input[type=number][name=thresholdAdd]').val(),
		category : catData
	}
	$.ajax({
		url : url + "/api/item",
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

// add measure
function addMeasure() {
	var itemData = {
		id : parseInt($("#itemSelect").val())
	}
	var data = {
		name : $("#nameAdd").val(),
		quantity : $('input[type=number][name=quantityAdd]').val(),
		item : itemData
	}
	$.ajax({
		url : url + "/api/measure",
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

// get item measure
function getMeasureOf(itemId) {
	var data = {
		id : itemId
	}
	$.ajax({
		url : url + "/api/itemmeasure",
		contentType : "application/json",
		type : 'POST',
		data : JSON.stringify(data),
		success : function(result) {
			$("#measureSelect").html("<option></option>");
			var options = '';
			$.each(result, function(i, unit) {
				options += '<option value="' + unit.id + '">' + unit.name + '('
						+ unit.quantity + ')' + '</option>';
			});
			$("#measureSelect").html(options);
		},
		error : function(e) {
			console.log(e);
			$('#add').modal('hide');
			showServerMessage();
		}
	});
}
