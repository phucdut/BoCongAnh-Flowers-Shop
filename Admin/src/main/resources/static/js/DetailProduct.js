document.addEventListener('DOMContentLoaded', function () {
    var detailLink = document.getElementById('detailLink'); // Chắc chắn bạn đã chọn phần tử đúng
    if (detailLink) {
        detailLink.addEventListener('click', function () {
            console.log('Clicked');
            var modal = new bootstrap.Modal(document.getElementById('viewdetail'));
            modal.show();
        });
    }
});