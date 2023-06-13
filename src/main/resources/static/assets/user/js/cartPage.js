const prodsCost = document.querySelectorAll(".cartprod-total")
const total = document.querySelector(".carttotal")

if (prodsCost && total) {
    console.log(total)

    const totalPrice = Array.from(prodsCost).reduce((tot, item) => {
        const price = item.innerText.substring(0, item.innerHTML.length - 1)
        return tot + parseInt(price)
    }, 0)

    total.innerText = totalPrice + "đ"
}