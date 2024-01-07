var selectedCategories = [];
function toggleCategory(categoryId) {
    let categoryButton = document.getElementById("category_" + categoryId);
    console.log(categoryId);
    categoryButton.classList.toggle("selected");

    if (categoryButton.classList.contains("selected")) {
        selectedCategories.push(categoryId);
    } else {
        let index = selectedCategories.indexOf(categoryId);
        if (index !== -1) {
            selectedCategories.splice(index, 1);
        }
    }
    console.log(selectedCategories);
    return selectedCategories;
}

function submitForm() {
    // Cập nhật giá trị của input ẩn với các danh mục đã chọn
    document.getElementById("categoryIdsInput").value = selectedCategories.join(',');
    // Thu thập dữ liệu biểu mẫu
    let formData = new FormData(document.getElementById("addProductForm"));
    fetch('/admin/product-add', {
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
                console.log('Xác nhận thêm sản phẩm thành công. Đang chuyển hướng đến trang xem sản phẩm.');
                window.location.replace('/admin/product');
            }else {
                console.error('Xác nhận thêm sản phẩm thất bại:', data.message);
                alert("Thêm sản phẩm thất bại - Sản phẩm đã tồn tại");

            }
        })
        .catch(error => {
            console.error('Error:', error);
            alert("Bạn cần nhập đầy đủ thông tin");
            console.error('Error:', error);
            // window.location.replace('/admin/product/add');
        });
}

window.addEventListener('unhandledrejection', event => {
    console.error('Unhandled Promise Rejection:', event.reason);
});