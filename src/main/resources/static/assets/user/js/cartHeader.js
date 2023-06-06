const cartBtns = document.querySelectorAll(".btn-cart")

cartBtns.length > 0 && cartBtns.forEach(cartBtn => {
    cartBtn.addEventListener("click", (ev) => {
        const input = cartBtn.parentElement.querySelector("input")
        switch (cartBtn.innerText) {
            case "+": {
                input.value = parseInt(input.value) + 1 + ""
                return;
            }
            case "-": {
                input.value = parseInt(input.value) - 1 + ""
                return;
            }
        }
    })

})