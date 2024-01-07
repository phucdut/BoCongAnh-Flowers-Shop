function confirmReset() {
    var overlay = document.getElementById("overlay");
    var resetPasswordUrl = overlay.dataset.resetPasswordUrl;
    var userId = overlay.dataset.userId;
    console.log("User ID from overlay: " + userId);

    // Sử dụng SweetAlert để hiển thị hộp thoại xác nhận
    Swal.fire({
        title: 'Are you sure?',
        text: 'You are about to reset the password.',
        icon: 'warning',
        showCancelButton: true,
        confirmButtonText: 'Yes, reset it!',
        cancelButtonText: 'No, cancel!',
        reverseButtons: true
    }).then((result) => {
        if (result.isConfirmed) {
            // Nếu người dùng đồng ý, thực hiện reset password
            fetch('/admin/reset-password/' + userId, {
                method: "POST",
                // ...
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
                    console.error("Error resetting password:", error);
                    Swal.fire('Error!', 'An error occurred while resetting password.', 'error');
                    hideOverlay();
                });
        } else {
            // Nếu người dùng từ chối, ẩn hộp thoại
            hideOverlay();
        }
    });
}
function cancelReset() {
    // Người dùng không muốn reset, ẩn overlay
    hideOverlay();
}

function showOverlay(userId, resetPasswordUrl) {
    // Hiển thị overlay và lưu thông tin ID
    document.getElementById("overlay").style.display = "flex";
    document.getElementById("overlay").dataset.userId = userId;
    document.getElementById("overlay").dataset.resetPasswordUrl = resetPasswordUrl;
    // Gọi hàm confirmReset khi hiển thị overlay
    confirmReset(userId);
}



function hideOverlay() {
    document.getElementById("overlay").style.display = "none";
}
function showConfirmation(button) {
    var resetPasswordUrl = $(button).data("url");
    var userId = $(button).data("userid");

    // Hiển thị hộp thoại và gửi thông tin ID
    showOverlay(userId, resetPasswordUrl);
}

// function showConfirmation(button) {
//     var userId = button.getAttribute("data-userid");
//     console.log("User ID: " + userId);
//
//     // Gọi các hàm khác cần thiết
//     // ...
// }