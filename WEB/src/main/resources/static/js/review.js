const stars = document.querySelectorAll('.star');
const ratingText = document.querySelector('.rating-text');
const ratingInput = document.getElementById('rating-input');
const increaseBtn = document.getElementById('increase');
const decreaseBtn = document.getElementById('decrease');

let currentRating = 0;

stars.forEach(star => {
    star.addEventListener('mousemove', (e) => {
        const index = parseInt(star.dataset.index);
        const rect = star.getBoundingClientRect();
        const offsetX = e.clientX - rect.left;
        const isHalf = offsetX < rect.width / 2;
        const tempRating = isHalf ? index - 0.5 : index;
        updateStars(tempRating);
    });

    star.addEventListener('click', (e) => {
        const index = parseInt(star.dataset.index);
        const rect = star.getBoundingClientRect();
        const offsetX = e.clientX - rect.left;
        const isHalf = offsetX < rect.width / 2;
        const starRating = isHalf ? index - 0.5 : index;
        currentRating = (starRating / 5 * 10);
        updateStars(starRating);
        updateRatingText(currentRating);
        ratingInput.value = Math.round(currentRating * 10) / 10;
    });

    star.addEventListener('mouseleave', () => {
        updateStars((currentRating / 10) * 5);
    });
});

function updateStars(rating) {
    stars.forEach(star => {
        const index = parseInt(star.dataset.index);
        star.classList.remove('full', 'half');
        if (rating >= index) {
            star.classList.add('full');
        } else if (rating >= index - 0.5) {
            star.classList.add('half');
        }
    });
}

function updateRatingText(rating) {
    const rounded = Math.round(rating * 10) / 10;
    ratingText.textContent = `Calificación: ${rounded}`;
    ratingInput.value = rounded;
}

increaseBtn.addEventListener('click', () => {
    currentRating = Math.min(currentRating + 0.1, 10);
    updateRatingText(currentRating);
    updateStars((currentRating / 10) * 5);
});

decreaseBtn.addEventListener('click', () => {
    currentRating = Math.max(currentRating - 0.1, 0);
    updateRatingText(currentRating);
    updateStars((currentRating / 10) * 5);
});
