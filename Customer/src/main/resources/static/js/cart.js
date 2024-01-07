
function addToCart(productId){
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
