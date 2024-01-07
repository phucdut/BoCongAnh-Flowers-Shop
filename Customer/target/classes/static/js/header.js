

var categoryApiUrl = 'api/category';
var productApiUrl = 'api/product/search-category';
var categoryTitleContainer = document.getElementById('categoryTitleContainer');
var categoryList = document.getElementById('categoryList');
var productListContainer = document.getElementById('productList');

function handleSelection(selectElement) {
    var selectedValue = selectElement.value;
    if (selectedValue === 'login') {
        window.location.href = '/login';
    } else if (selectedValue === 'register') {
        window.location.href = '/register';
    } else {
        // Xử lý các trường hợp khác khi cần thiết
    }
}

function fetchAndDisplayAllProducts() {
    fetch(categoryApiUrl)
        .then(response => response.json())
        .then(categories => {
            categories.forEach(category => {
                var categoryListItem = createCategoryListItem(category);
                categoryList.appendChild(categoryListItem);

                fetch(`${productApiUrl}/${category.id}`)
                    .then(response => response.json())
                    .then(products => {
                        displayCategoryAndProducts(category, products);
                    })
                    .catch(error => console.error('Lỗi khi lấy sản phẩm:', error));
            });
        })
        .catch(error => console.error('Lỗi khi lấy dữ liệu danh mục:', error));
}

function createCategoryListItem(category) {
    var listItem = document.createElement('li');
    listItem.className = 'nav-item';

    var link = document.createElement('a');
    link.className = 'nav-link';
    link.href = '#';
    link.textContent = category.name;

    // Sự kiện để fetch và hiển thị sản phẩm cho danh mục đã chọn
    link.addEventListener('click', function (event) {
        event.preventDefault();
        categoryTitleContainer.innerHTML = '';
        productListContainer.innerHTML = '';

        var categoryTitle = createCategoryTitle(category);
        categoryTitleContainer.appendChild(categoryTitle);

        fetchProductsAndDisplay(category.id);
    });

    listItem.appendChild(link);
    return listItem;
}
document.addEventListener('DOMContentLoaded', fetchAndDisplayAllProducts);

document.addEventListener('DOMContentLoaded', function() {

    function addToCart(productId) {
        const apiUrl = '/api/cart/add/';

        return fetch(apiUrl + productId, {
            method: 'GET',
        })
            .then(response => response.json())
            .then(data => {
                if (data.success) {
                    console.log('Item added to the cart successfully');
                } else {
                    console.log('Failed to add item to the cart');
                }
                viewCart();
            })
            .catch(error => {
                console.error('Error:', error);
                console.log('An error occurred while adding the item to the cart');
            });
    }

    document.getElementById('cartButton').addEventListener('click', function() {
        var productId = document.getElementById('id').value;
        console.log("id:", productId);
        addToCart(productId);

        viewCart();
    });
    function viewCart() {
        const apiUrl = '/api/cart';

        return fetch(apiUrl, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
            },
        })
            .then(response => {
                if (response.ok) {
                    return response.json();
                }
                throw new Error('Failed to fetch cart data');
            })
            .then(cartItems => {
                console.log('Cart items:', cartItems);
                const totalQuantity = cartItems.reduce((total, item) => total + item.quantity, 0);

                document.getElementById('cartItemCount').innerText = totalQuantity.toString();
                console.log('Tổng số lượng sản phẩm trong giỏ hàng: ' + totalQuantity);
            })
            .catch(error => {
                console.error('Error:', error);
            });
    }
    viewCart();

    // document.getElementById('cartButton').addEventListener('click', function() {
    //     var productId = document.getElementById('id').value;
    //     console.log("id:", productId);
    //     addToCart(productId);
    //     viewCart();
    // });
    document.getElementById('cartIcon').addEventListener('click', function() {
        viewCart();
        window.location.href = '/cart';
    });
});
