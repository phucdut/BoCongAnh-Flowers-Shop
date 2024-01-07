function validateForm() {
    // Lấy giá trị từ trường input
    var nameItem = document.getElementById('nameItem').value;

    // Reset thông báo lỗi
    document.getElementById('nameError').innerText = '';

    // Kiểm tra hợp lệ
    var isValid = true;

    if (!nameItem) {
        document.getElementById('nameError').innerText = 'Vui lòng nhập Name.';
        isValid = false;
    }

    return isValid;
}