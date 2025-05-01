document.getElementById('registro-form').addEventListener('submit', function(event) {
    const password = document.getElementById('register-password').value;
    const confirmPassword = document.getElementById('confirm-password').value;
    const errorText = document.getElementById('password-error');

    if (password !== confirmPassword) {
        event.preventDefault();
        errorText.style.display = 'block';
    } else {
        errorText.style.display = 'none';
    }
});