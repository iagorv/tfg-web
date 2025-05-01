document.getElementById('registro-form').addEventListener('submit', function(event) {
    const password = document.getElementById('register-password').value;
    const confirmPassword = document.getElementById('confirm-password').value;
    const fechaNacimiento = document.getElementById('fechaNacimiento').value;
    const passwordError = document.getElementById('password-error');
    const edadError = document.getElementById('edad-error');

    let hayError = false;

    if (password !== confirmPassword) {
        passwordError.style.display = 'block';
        hayError = true;
    } else {
        passwordError.style.display = 'none';
    }

    if (!esMayorDe16(fechaNacimiento)) {
        edadError.style.display = 'block';
        hayError = true;
    } else {
        edadError.style.display = 'none';
    }

    if (hayError) {
        event.preventDefault();
    }
});

function esMayorDe16(fechaStr) {
    if (!fechaStr) return false;
    const fechaNacimiento = new Date(fechaStr);
    const hoy = new Date();
    const edad = hoy.getFullYear() - fechaNacimiento.getFullYear();
    const mes = hoy.getMonth() - fechaNacimiento.getMonth();
    const dia = hoy.getDate() - fechaNacimiento.getDate();

    return edad > 16 || (edad === 16 && (mes > 0 || (mes === 0 && dia >= 0)));
}
