// Dropdown menu toggle
document.querySelectorAll('.sidebar-submenu').forEach(submenu => {
    const dropdownIcon = submenu.querySelector('.sidebar-menu-dropdown .dropdown-icon');
    const dropdownContent = submenu.querySelector('.sidebar-menu-dropdown-content');
    const dropdownContentLis = dropdownContent.querySelectorAll('li');
    const activeHeight = dropdownContentLis[0].clientHeight * dropdownContentLis.length;

    submenu.querySelector('.sidebar-menu-dropdown').onclick = (event) => {
        event.preventDefault();
        dropdownIcon.classList.toggle('active');
        dropdownContent.classList.toggle('active');
        dropdownContent.style.height = dropdownContent.classList.contains('active') ? activeHeight + 'px' : '0';
    };
});

// Category Chart
let category_options = {
    series: [],
    labels: [],
    chart: {
        type: 'donut',
    },
    colors: ['#6ab04c', '#2980b9', '#f39c12', '#d35400']
};

// Fetch category data
fetch('/admin/category-data')
    .then(response => response.json())
    .then(data => {
        const seriesData = data.map(item => item.quantity);
        const labels = data.map(item => item.categoryName);
        category_options.series = seriesData;
        category_options.labels = labels;
        let category_chart = new ApexCharts(document.querySelector("#category-chart"), category_options);
        category_chart.render();
    })
    .catch(error => console.error(error));

// Customer Chart
let customer_options = {
    series: [{
        // name: "Store Customers",
        // data: []
    }, {
        name: "Online Customers",
        data: [20, 30, 10, 20, 16, 40, 20, 51, 10, 20, 15, 23]
    }],
    colors: ['#6ab04c', '#2980b9'],
    chart: {
        height: 350,
        type: 'line',
    },
    dataLabels: {
        enabled: false
    },
    stroke: {
        curve: 'smooth'
    },
    xaxis: {
        categories: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'],
    },
    legend: {
        position: 'top'
    }
};

// Fetch amount data
fetch('/admin/amount-data')
    .then(response => response.json())
    .then(data => {
        const Data = data.map(item => item.amount);
        const categories = data.map(item => item.month);
        customer_options.series[0].data = Data;
        // customer_options.xaxis.categories = categories;
        let customer_chart = new ApexCharts(document.querySelector("#customer-chart"), customer_options);
        customer_chart.render();
    })
    .catch(error => console.error(error));

setDarkChart = (dark) => {
    let theme = {
        theme: {
            mode: dark ? 'dark' : 'light'
        }
    }

    customer_chart.updateOptions(theme)
    category_chart.updateOptions(theme)
}
// Dark mode toggle
let darkModeToggle = document.querySelector('#dark_mode-toggle');

darkModeToggle.onclick = (e) => {
    e.preventDefault();
    document.querySelector('body').classList.toggle('dark');
    darkModeToggle.querySelector('.dark_mode-switch').classList.toggle('active');
    setDarkChart(document.querySelector('body').classList.contains('dark'));
};

// Mobile toggle
let overlay = document.querySelector('.overlay');
let sidebar = document.querySelector('.sidebar');

document.querySelector('#mobile-toggle').onclick = () => {
    sidebar.classList.toggle('active');
    overlay.classList.toggle('active');
};

// document.querySelector('#sidebar-close').onclick = () => {
//     sidebar.classList.toggle('active');
//     overlay.classList.toggle('active');
// };
