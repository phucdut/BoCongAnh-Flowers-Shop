function showPurchaseHistory(button) {
    // Lấy startTime và endTime từ các thuộc tính dữ liệu
    var startTime = button.dataset.startTime;
    var endTime = button.dataset.endTime;

    // Gửi yêu cầu đến API purchase-history
    fetch(`/admin/purchase-history`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({ startTime: startTime, endTime: endTime })
    })
        .then(response => response.json())
        .then(data => {
            console.log(data.url)
            window.location.href = data.url;
        })
        .catch(error => console.error('Lỗi khi lấy đường dẫn chuyển hướng lịch sử mua hàng:', error));
}
function showPurchaseImportGoods(button) {
    // Lấy startTime và endTime từ các thuộc tính dữ liệu
    var startTime = button.dataset.startTime;
    var endTime = button.dataset.endTime;

    // Gửi yêu cầu đến API purchase-history
    fetch(`/admin/purchase-import-goods`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({ startTime: startTime, endTime: endTime })
    })
        .then(response => response.json())
        .then(data => {
            console.log(data.url)
            window.location.href = data.url;
        })
        .catch(error => console.error('Lỗi khi lấy đường dẫn chuyển hướng lịch sử mua hàng:', error));
}
document.addEventListener('DOMContentLoaded', function() {
    document.getElementById('exportBtn').addEventListener('click', function () {
        var element = document.getElementById('report');
        html2pdf(element);
    });
});
