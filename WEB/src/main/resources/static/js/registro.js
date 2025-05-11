import { esMayorDe16 } from './validaciones.js';

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
