const cards = document.querySelectorAll(".card-product");
const cardOptions = document.querySelectorAll(".card__option");

cardOptions.forEach((cardOption) => {
    cardOption.onclick = (e) => {
        const card = getParentEl("card-product", e.target);
        const imageTarget = cardOption.querySelector("img").getAttribute("src");
        const cardImageEl = card.querySelector(".card__top > img");
        cardImageEl.setAttribute("th:src", imageTarget);
        cardImageEl.setAttribute("src", imageTarget);
    };
});

function getParentEl(parentClass, el) {
    while (el) {
        if (el.classList.contains(parentClass)) return el;
        el = el.parentElement;
    }
    return null;
}