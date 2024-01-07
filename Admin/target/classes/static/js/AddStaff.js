function submitFormAdd() {
    // Thu thập dữ liệu biểu mẫu
    let formData = new FormData(document.getElementById("addStaffForm"));
    fetch('/admin/staff-add', {
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
                console.log('Xác nhận thêm nhân viên thành công. Đang chuyển hướng đến trang xem nhân viên.');
                window.location.replace('/admin/staff');
            }else {
                console.error('Xác nhận thêm nhân viên thất bại:', data.message);
                alert("Thêm nhân viên thất bại - nhân viên đã tồn tại \nHoặc bạn cần nhập đầy đủ thông tin");

            }
        })
        .catch(error => {
            console.error('Error:', error);
            alert("Bạn cần nhập đầy đủ thông tin");
            console.error('Error:', error);
        });
}