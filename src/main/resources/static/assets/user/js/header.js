const burgerBtn = document.querySelector("#burger-btn");
const megaMenu = document.querySelector("#megaMenu");

burgerBtn !== undefined &&
(burgerBtn.onclick = (e) => {
    const lines = document.querySelector("#lines");
    const text = document.querySelector("#btn-text");
    if (!burgerBtn.classList.contains("open")) {
        burgerBtn.classList.add("open");
        lines.classList.add("hidden");
        text.classList.remove("hidden");
    } else if (burgerBtn.classList.contains("open")) {
        burgerBtn.classList.remove("open");
        lines.classList.remove("hidden");
        text.classList.add("hidden");
    }
    openMegaMenu();
});

function openMegaMenu() {
    if (burgerBtn && burgerBtn.classList.contains("open")) {
        megaMenu.classList.remove("hidden", "-translate-x-full");
        megaMenu.classList.add("flex", "bg-white");
    } else {
        megaMenu.classList.add("hidden", "-translate-x-full");
        megaMenu.classList.remove("flex", "bg-white");
    }
}