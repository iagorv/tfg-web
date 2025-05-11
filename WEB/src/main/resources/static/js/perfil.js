import { esMayorDe16 } from './validaciones.js';

document.querySelector('.profile-form').addEventListener('submit', function(event) {
    const fechaNacimiento = document.getElementById('fechaNacimiento').value;
    const edadError = document.getElementById('edad-error-perfil');

    if (!esMayorDe16(fechaNacimiento)) {
        event.preventDefault();
        if (!edadError) {
            const errorElement = document.createElement('p');
            errorElement.id = 'edad-error-perfil';
            errorElement.textContent = 'Debes tener al menos 16 años para actualizar tu perfil.';
            errorElement.style.color = '#ff5c5c';
            document.querySelector('.profile-form').appendChild(errorElement);
        } else {
            edadError.style.display = 'block';
        }
    } else if (edadError) {
        edadError.style.display = 'none';
    }
});
