function submitFormEdit() {
    // Thu thập dữ liệu biểu mẫu
    let formData = new FormData(document.getElementById("editStaffForm"));
    fetch('/admin/staff-edit', {
        method: 'POST',
        body: formData,
    })
        .then(response => response.text())
        .then(redirectUrl => {
            // Xử lý đường dẫn trực tiếp từ server
            if (redirectUrl) {
                window.location.href = redirectUrl;
            } else {
                console.error("Invalid redirect data");
            }
        })
        .catch(error => {
            console.error("Error :", error);
        });
}
function validateFormEditUser() {
    var username = document.getElementById('username').value;
    var fullName = document.getElementById('fullName').value;
    var image = document.getElementById('image').value;
    var phone = document.getElementById('phone').value;
    var address = document.getElementById('address').value;
    var birthday = document.getElementById('birthday').value;
    var salary = document.getElementById('salary').value;
// var role = document.getElementById('role').value;

// Reset error messages
    document.getElementById('usernameError').innerText = '';
    document.getElementById('fullNameError').innerText = '';
    document.getElementById('imageError').innerText = '';
    document.getElementById('phoneError').innerText = '';
    document.getElementById('addressError').innerText = '';
    document.getElementById('birthdayError').innerText = '';
    document.getElementById('salaryError').innerText = '';
// document.getElementById('roleError').innerText = '';
// Repeat similar lines for other fields
    var isValid = true;

    if (!username ) {
        document.getElementById('usernameError').innerText = 'Vui lòng nhập tên đăng nhập.';
        isValid = false;
    }
    if (!fullName ) {
        document.getElementById('fullNameError').innerText = 'Vui lòng nhập họ và tên.';
        isValid = false;
    }
    if (!image ) {
        document.getElementById('imageError').innerText = 'Vui lòng nhập image.';
        isValid = false;
    }
    if (!phone) {
        document.getElementById('phoneError').innerText = 'Vui lòng nhập số điện thoại.';
        isValid = false;
    }
    if (isValidPhoneNumber(phone) === false){
        document.getElementById('phoneError').innerText = 'Vui lòng nhập đúng định dạng.';
        isValid = false;
    }
    if (!address ) {
        document.getElementById('addressError').innerText = 'Vui lòng nhập địa chỉ.';
        isValid = false;
    }
    if (!birthday ) {
        document.getElementById('birthdayError').innerText = 'Vui lòng nhập ngày sinh.';
        isValid = false;
    }
    if (!salary ) {
        document.getElementById('salaryError').innerText = 'Vui lòng nhập lương.';
        isValid = false;
    }
    return isValid;
}
function isValidPhoneNumber(phone) {
    // Sử dụng biểu thức chính quy để kiểm tra
    const phoneRegex = /^0[0-9]{9}$/;
    return phoneRegex.test(phone);
}