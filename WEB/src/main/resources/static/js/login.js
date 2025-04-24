document.addEventListener("DOMContentLoaded", () => {
    const form = document.getElementById("login-form");
    const errorMsg = document.getElementById("error-msg");

    form.addEventListener("submit", async (e) => {
        e.preventDefault();

        const email = document.getElementById("email").value;
        const password = document.getElementById("password").value;

        const loginDTO = {
            email: email,
            password: password
        };

        try {
            const response = await fetch("http://localhost:8080/api/login", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(loginDTO)
            });

            if (response.ok) {
                const user = await response.json();
                console.log("Login correcto:", user);
                // Guardar usuario en localStorage o cookie si hace falta
                window.location.href = "/juegos"; // redirigir a otra página
            } else {
                const errorText = await response.text();
                errorMsg.textContent = errorText || "Error al iniciar sesión";
            }
        } catch (err) {
            console.error("Error:", err);
            errorMsg.textContent = "Error en el servidor. Intenta más tarde.";
        }
    });
});