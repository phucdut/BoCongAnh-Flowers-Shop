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

