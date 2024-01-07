function validateFormAddFL() {
    var startDateAdd = document.getElementById('startDateAdd').value;
    var endDateAdd = document.getElementById('endDateAdd').value;
    var priceSaleAdd = document.getElementById('priceSaleAdd').value;
    var saleAdd = document.getElementById('saleAdd').value;
    var productIdAdd = document.getElementById('productIdAdd').value;
// var role = document.getElementById('role').value;

// Reset error messages
    document.getElementById('startDateAddError').innerText = '';
    document.getElementById('endDateAddError').innerText = '';
    document.getElementById('priceSaleAddError').innerText = '';
    document.getElementById('saleAddError').innerText = '';
    document.getElementById('productIdAddError').innerText = '';

    var isValid = true;

    if (!startDateAdd ) {
        document.getElementById('startDateAddError').innerText = 'Vui lòng nhập thời gian bắt đầu của FL.';
        isValid = false;
    }
    if (!endDateAdd ) {
        document.getElementById('endDateAddError').innerText = 'Vui lòng nhập thời gian kết thúc của FL.';
        isValid = false;
    }
    if (!priceSaleAdd ) {
        document.getElementById('priceSaleAddError').innerText = 'Vui lòng nhập giá của FL.';
        isValid = false;
    }
    if (!saleAdd) {
        document.getElementById('saleAddError').innerText = 'Vui lòng nhập mã của sản phẩm có FL.';
        isValid = false;
    }
    if (!productIdAdd ) {
        document.getElementById('productIdAddError').innerText = 'Vui lòng nhập số giảm giá.';
        isValid = false;
    }
    return isValid;
}
