function submitFormEditCategory() {
    // Thu thập dữ liệu biểu mẫu
    let formData = new FormData(document.getElementById("editCategoryForm"));
    fetch('/admin/category-edit', {
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