function submitFormAddVoucher() {
    // Thu thập dữ liệu biểu mẫu
    let formData = new FormData(document.getElementById("addVoucherForm"));
    fetch('/admin/voucher-add', {
        method: 'POST',
        body: formData,
    })
        .then(response => {
            if (!response.ok) {
                throw new Error(`HTTP Error! Status: ${response.status}`);
            }
            return response.json();
        })
        .then(data => {
            if (data.success) {
                // This line redirects to '/login'
                console.log('Xác nhận thêm voucher thành công. Đang chuyển hướng đến trang xem voucher.');
                window.location.replace('/admin/voucher');
            }else {
                console.error('Xác nhận thêm danh mục thất bại:', data.message);
                alert("Thêm voucher thất bại - voucher đã tồn tại \nHoặc bạn cần nhập đầy đủ thông tin");

            }
        })
        .catch(error => {
            console.error('Error:', error);
            alert("Bạn cần nhập đầy đủ thông tin");
            console.error('Error:', error);
        });
}

function submitFormEditVoucher() {
    // Thu thập dữ liệu biểu mẫu
    let formData = new FormData(document.getElementById("editVoucherForm"));
    fetch('/admin/voucher-edit', {
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