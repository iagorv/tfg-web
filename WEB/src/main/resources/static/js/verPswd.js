document.addEventListener('DOMContentLoaded', function () {
    document.querySelectorAll('.password-wrapper').forEach(function(wrapper) {
        const input = wrapper.querySelector('input[type="password"]');
        const button = wrapper.querySelector('.toggle-password');

        if (input && button) {
            button.addEventListener('click', function () {
                const isPassword = input.type === 'password';
                input.type = isPassword ? 'text' : 'password';
                button.textContent = isPassword ? 'Ocultar' : 'Mostrar';
            });
        }
    });
});
