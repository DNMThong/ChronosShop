
const splide = new Splide(".splide", {
    type: "loop",
    speed: 150,
    perPage: 1,
    pagination: false,
});
const paginationItems = document.querySelectorAll(
    ".custom-pagination label"
);

paginationItems.forEach((item) => {
    item.addEventListener("click", () => {
        const page = Number(item.getAttribute("data-splide-page"));
        splide.go(page);
    });
});

splide.on("moved", () => {
    const currentSlide = splide.index;
    paginationItems.forEach((item, index) => {
        if (index === currentSlide) {
            item.querySelector('input[type="radio"]').checked = true;
        }
    });
});
splide.mount();

const amountIncrement = document.querySelector(".amount-increment");
const amountValue = document.querySelector(".amount-value");
const amountDencrement = document.querySelector(".amount-decrement");

amountIncrement.onclick = (e) => {
    const value = Number(amountValue.value) + 1;
    if (value <= 1) {
        amountDencrement.disabled = true;
        amountValue.value = 1;
    } else {
        amountDencrement.disabled = false;
        amountValue.value = value;
    }
    if (value >= 100) {
        amountIncrement.disabled = true;
        amountValue.value = 100;
    } else {
        amountIncrement.disabled = false;
        amountValue.value = value;
    }
};

amountDencrement.onclick = (e) => {
    const value = Number(amountValue.value) - 1;

    if (value <= 1) {
        amountDencrement.disabled = true;
        amountValue.value = 1;
    } else {
        amountDencrement.disabled = false;
        amountValue.value = value;
    }
    if (value >= 100) {
        amountIncrement.disabled = true;
        amountValue.value = 100;
    } else {
        amountIncrement.disabled = false;
        amountValue.value = value;
    }
};

amountValue.onchange = (e) => {
    const value = Number(amountValue.value);
    if (value <= 1) {
        amountDencrement.disabled = true;
        amountValue.value = 1;
    } else {
        amountDencrement.disabled = false;
    }
    if (value >= 100) {
        amountIncrement.disabled = true;
        amountValue.value = 100;
    } else {
        amountIncrement.disabled = false;
    }
};

const radioSize = document.querySelectorAll("input[name='size']");

radioSize.forEach((item) => {
    item.onchange = (e) => {
        document.querySelector(".size-text").innerText = item.value;
    };
});