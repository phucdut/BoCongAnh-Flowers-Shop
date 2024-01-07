function submitFormAddCatgory() {
    // Thu thập dữ liệu biểu mẫu
    let formData = new FormData(document.getElementById("addCategoryForm"));
    fetch('/admin/category-add', {
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
                console.log('Xác nhận thêm danh mục thành công. Đang chuyển hướng đến trang xem danh mục.');
                window.location.replace('/admin/category');
            }else {
                console.error('Xác nhận thêm danh mục thất bại:', data.message);
                alert("Thêm danh mục thất bại - danh mục đã tồn tại \nHoặc bạn cần nhập đầy đủ thông tin");

            }
        })
        .catch(error => {
            console.error('Error:', error);
            alert("Bạn cần nhập đầy đủ thông tin");
            console.error('Error:', error);
        });
}