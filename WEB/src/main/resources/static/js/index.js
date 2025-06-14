

document.addEventListener("DOMContentLoaded", function () {
    const toggleBtn = document.getElementById("showMoreBtn");
    const allItems = document.querySelectorAll("#gamesContainer .game-item");
    let expanded = false;

    toggleBtn.addEventListener("click", function () {
        expanded = !expanded; 

        for (let i = 5; i < allItems.length; i++) {
            if (expanded) {
                allItems[i].classList.remove("hidden-game");
            } else {
                allItems[i].classList.add("hidden-game");
            }
        }

        toggleBtn.textContent = expanded ? "Ver menos" : "Ver más";

        if (!expanded) {
            window.scrollTo({ top: document.querySelector(".games-list").offsetTop, behavior: 'smooth' });
        }
    });
});
