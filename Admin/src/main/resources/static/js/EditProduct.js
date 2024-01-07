function submitFormEdit() {
    // Thu thập dữ liệu biểu mẫu
    let formData = new FormData(document.getElementById("editProductForm"));
    fetch('/admin/product-edit', {
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


function validateFormEditProduct() {
    var name = document.getElementById('nameEdit').value;
    var originalPrice = document.getElementById('originalPriceEdit').value;
    var discount = document.getElementById('discountEdit').value;
    var description = document.getElementById('descriptionEdit').value;
    var delivery = document.getElementById('deliveryEdit').value;
    var sub_info = document.getElementById('sub_infoEdit').value;
    var image1 = document.getElementById('image1Edit').value;
    var image2 = document.getElementById('image2Edit').value;
    var image3 = document.getElementById('image3Edit').value;
    var image4 = document.getElementById('image4Edit').value;
    var image5 = document.getElementById('image5Edit').value;

// Reset error messages


    var isValid = true;
// Repeat similar lines for other fields

    document.getElementById('nameEditError').innerText = '';
    document.getElementById('originalPriceEditError').innerText = '';
    document.getElementById('discountEditError').innerText = '';
    document.getElementById('descriptionEditError').innerText = '';
    document.getElementById('deliveryEditError').innerText = '';
    document.getElementById('sub_infoEditError').innerText = '';
    document.getElementById('image1EditError').innerText = '';
    document.getElementById('image2EditError').innerText = '';
    document.getElementById('image3EditError').innerText = '';
    document.getElementById('image4EditError').innerText = '';
    document.getElementById('image5EditError').innerText = '';
    if (!name ) {
        document.getElementById('nameEditError').innerText = 'Vui lòng nhập tên sản phẩm.';
        isValid = false;
    }
    if (!originalPrice ) {
        document.getElementById('originalPriceEditError').innerText = 'Vui lòng nhập giá gốc.';
        isValid = false;
    }
    if (!discount ) {
        document.getElementById('discountEditError').innerText = 'Vui lòng nhập mã giảm giá.';
        isValid = false;
    }
    if (!description) {
        document.getElementById('descriptionEditError').innerText = 'Vui lòng nhập thông tin miêu tả về sản phẩm.';
        isValid = false;
    }
    if (!delivery ) {
        document.getElementById('deliveryEditError').innerText = 'Vui lòng nhập thông tin giao hàng.';
        isValid = false;
    }
    if (!sub_info ) {
        document.getElementById('sub_infoEditError').innerText = 'Vui lòng nhập thông tin liên quan.';
        isValid = false;
    }
    console.log("image: " + typeof image1 + image1.length);
    if (!image1 ) {
        console.log("image: " + typeof image1 + image1.length);
        document.getElementById('image1EditError').innerText = 'Vui lòng chọn ảnh cho sản phẩm.';
        isValid = false;
    }
    if (!image2 ) {
        document.getElementById('image2EditError').innerText = 'Vui lòng chọn ảnh cho sản phẩm.';
        isValid = false;
    }
    if (!image3 ) {
        document.getElementById('image3EditError').innerText = 'Vui lòng chọn ảnh cho sản phẩm.';
        isValid = false;
    }
    if (!image4 ) {
        document.getElementById('image4EditError').innerText = 'Vui lòng chọn ảnh cho sản phẩm.';
        isValid = false;
    }
    if (!image5 ) {
        document.getElementById('image5EditError').innerText = 'Vui lòng chọn ảnh cho sản phẩm.';
        isValid = false;
    }
    return isValid;
}




// function handleFileChange() {
//     var image1 = document.getElementById('image1Edit').value;
//     document.getElementById('image1EditError').innerText = '';
//     if(!image1){
//         console.log("null");
//     }else {
//         console.log("không null");
//     }
// }

