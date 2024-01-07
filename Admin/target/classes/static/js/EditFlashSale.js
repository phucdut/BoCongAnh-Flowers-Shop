function validateFormEditFL() {
    var startDateEdit = document.getElementById('startDateEdit').value;
    var endDateEdit = document.getElementById('endDateEdit').value;
    var priceSaleEdit = document.getElementById('priceSaleEdit').value;
    var productIdEdit = document.getElementById('productIdEdit').value;
    var saleEdit = document.getElementById('saleEdit').value;
// var role = document.getElementById('role').value;

// Reset error messages
    document.getElementById('startDateEditError').innerText = '';
    document.getElementById('endDateEditError').innerText = '';
    document.getElementById('priceSaleEditError').innerText = '';
    document.getElementById('productIdEditError').innerText = '';
    document.getElementById('saleEditError').innerText = '';

// Repeat similar lines for other fields
    var isValid = true;

    if (!startDateEdit ) {
        document.getElementById('startDateEditError').innerText = 'Vui lòng nhập thời gian bắt đầu của FL.';
        isValid = false;
    }
    if (!endDateEdit ) {
        document.getElementById('endDateEditError').innerText = 'Vui lòng nhập thời gian kết thúc của FL.';
        isValid = false;
    }
    if (!priceSaleEdit ) {
        document.getElementById('priceSaleEditError').innerText = 'Vui lòng nhập giá của FL.';
        isValid = false;
    }
    if (!productIdEdit) {
        document.getElementById('productIdEditError').innerText = 'Vui lòng nhập mã của sản phẩm có FL.';
        isValid = false;
    }
    if (!saleEdit ) {
        document.getElementById('saleEditError').innerText = 'Vui lòng nhập số giảm giá.';
        isValid = false;
    }
    return isValid;
}