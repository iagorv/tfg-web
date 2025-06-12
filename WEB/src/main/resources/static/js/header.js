document.addEventListener('DOMContentLoaded', function () {
    const userMenu = document.querySelector('.user-menu');

    if (userMenu) {
        // Al hacer clic en el menú del usuario, se activa o desactiva la clase 'active'
        userMenu.addEventListener('click', function () {
            this.classList.toggle('active');
        });

        // Si se hace clic en cualquier parte fuera del menú, se elimina la clase 'active'
        document.addEventListener('click', function (e) {
            if (!userMenu.contains(e.target)) {
                userMenu.classList.remove('active');
            }
        });
    }
});

    document.addEventListener('DOMContentLoaded', () => {
    function changeLang(lang) {
        const url = new URL(window.location.href);
        url.searchParams.set('lang', lang);
        window.location.href = url.toString();
    }

    const langEs = document.getElementById('lang-es');
    const langEn = document.getElementById('lang-en');

    if (langEs && langEn) {
    langEs.addEventListener('click', e => {
    e.preventDefault();
    changeLang('es');
});

    langEn.addEventListener('click', e => {
    e.preventDefault();
    changeLang('en');
});
}
});


