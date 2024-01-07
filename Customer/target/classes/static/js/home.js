
function createCategoryTitle(category) {
    var categoryTitle = document.createElement('h3');
    categoryTitle.className = 'category';
    categoryTitle.textContent = category.name;
    return categoryTitle;
}
function displayCategoryAndProducts(category, products) {
    const categoryTitle = createCategoryTitle(category);
    categoryTitleContainer.appendChild(categoryTitle);

    const categoryList = createCategoryList();

    products.forEach(product => {
        const listItem = document.createElement('li');

        listItem.addEventListener('click', function () {
            redirectToProductDetail(product.id);
        });

        const productLink = document.createElement('a');

        const productImage = document.createElement('img');
        productImage.src = '/images/product/' + product.image1;
        productImage.className = 'imageProduct';
        productLink.appendChild(productImage);

        const productName = document.createElement('p');
        productName.textContent = product.name;
        productName.className = 'nameProduct';
        productLink.appendChild(productName);

        const productPrice = document.createElement('p');
        productPrice.textContent = product.price;
        productPrice.className = 'priceProduct';
        productLink.appendChild(productPrice);

        const productButton = document.createElement('input');
        productButton.type = 'button'
        productButton.value = "ĐẶT HÀNG"
        productLink.appendChild(productButton);

        // <input type="button" value="ĐẶT HÀNG">

        listItem.appendChild(productLink);
        categoryList.appendChild(listItem);
    });

    categoryTitleContainer.appendChild(categoryList);
}

function redirectToProductDetail(productId) {
    fetch(`/api/product/${productId}`)
        .then(response => {
            if (!response.ok) {
                throw new Error(`HTTP error! Status: ${response.status}`);
            }
            return response.json();
        })
        .then(product => {
            window.location.href = `/product/detail/${product.id}`;
        })
        .catch(error => {
            console.error('Lỗi khi gọi API để lấy thông tin sản phẩm:', error);
        });
}
function createCategoryList() {
    const categoryList = document.createElement('ul');
    categoryList.id = 'category';
    return categoryList;
}
