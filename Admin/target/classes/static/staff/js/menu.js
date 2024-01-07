const exceptionCode = 417;
var tokenFcm = "";
$(document).ready(function() {

    loadmenu();
    function loadmenu() {
        var content =
            `<a class="nav-link" href="customer">
                <div class="sb-nav-link-icon"><i class="fa fa-database iconmenu"></i></div>
                Customer
            </a>
            <a class="nav-link" href="product">
                <div class="sb-nav-link-icon"><i class="fas fa-user-alt iconmenu"></i></div>
                Product
            </a>
            <a class="nav-link" href="notify">
                <div class="sb-nav-link-icon"><i class="fas fa-table iconmenu"></i></div>
                Notification
            </a>
            <a class="nav-link" href="product.html">
                <div class="sb-nav-link-icon"><i class="fas fa-tshirt iconmenu"></i></div>
                Customer support
            </a>
            <a class="nav-link" href="order">
                <div class="sb-nav-link-icon"><i class="fa fa-shopping-cart iconmenu"></i></div>
                Order
            </a>
            <a class="nav-link" href="importgoods">
                <div class="sb-nav-link-icon"><i class="fa fa-shopping-cart iconmenu"></i></div>
                Importgoods
            </a>
            <a class="nav-link" href="../perform_logout">
                <div class="sb-nav-link-icon"><i class="fas fa-sign-out-alt iconmenu"></i></div>
                Logout
            </a>
           `

        var menu =
            `<nav class="sb-sidenav accordion sb-sidenav-dark" id="sidenavAccordion">
        <div class="sb-sidenav-menu">
            <div class="nav">
                ${content}
            </div>
        </div>
    </nav>`
        document.getElementById("layoutSidenav_nav").innerHTML = menu
    }
    loadtop()

    function loadtop() {
        var top =
            `<a class="navbar-brand ps-3" href="index.html">Quản trị hệ thống</a>
        <button class="btn btn-link btn-sm order-1 order-lg-0 me-4 me-lg-0" id="sidebarToggle" href="#!"><i class="fas fa-bars"></i></button>
        <form class="d-none d-md-inline-block form-inline ms-auto me-0 me-md-3 my-2 my-md-0"></form>
        <ul id="menuleft" class="navbar-nav ms-auto ms-md-0 me-3 me-lg-4">
        </ul>`
        document.getElementById("top").innerHTML = top
    }
    var sidebarToggle = document.getElementById("sidebarToggle");
    sidebarToggle.onclick = function() {
        document.body.classList.toggle('sb-sidenav-toggled');
        localStorage.setItem('sb|sidebar-toggle', document.body.classList.contains('sb-sidenav-toggled'));
    }
});


function formatmoney(money) {
    const VND = new Intl.NumberFormat('vi-VN', {
        style: 'currency',
        currency: 'VND',
    });
    return VND.format(money);
}
