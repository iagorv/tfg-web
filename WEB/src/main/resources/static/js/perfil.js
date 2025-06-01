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
document.addEventListener('DOMContentLoaded', () => {
    const btn = document.querySelector('.btn-premium');

    // Asegúrate de que idUsuario se ha definido en el HTML mediante th:inline="javascript"
    if (!btn || typeof idUsuario === 'undefined') return;

    btn.addEventListener('click', async () => {
        const esPremium = btn.textContent.includes('Anular');

        try {
            await fetch(`http://localhost:8080/api/user/${idUsuario}/premium`, {
                method: 'PATCH',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({ premium: !esPremium })
            });

            location.reload(); // Recargar sin alertas
        } catch (e) {
            console.error('Error al cambiar estado premium:', e);
        }
    });
});

