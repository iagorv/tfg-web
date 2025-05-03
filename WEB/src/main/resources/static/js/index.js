document.querySelector('.user-menu').addEventListener('click', function () {
    this.classList.toggle('active');
});


document.addEventListener('click', function (e) {
    const userMenu = document.querySelector('.user-menu');
    if (!userMenu.contains(e.target)) {
        userMenu.classList.remove('active');
    }
});