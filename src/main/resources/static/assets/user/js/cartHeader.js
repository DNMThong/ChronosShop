const cartBtns = document.querySelectorAll(".btn-cart")
const cartItems = document.querySelectorAll("form.cartItem")
const cartDeleteBtns = document.querySelectorAll(".cartItem__delete-btn")
console.log(cartItems)


cartItems.forEach(function (cartItem) {
    const input = cartItem.querySelector("input")
    if (input) {
        input.addEventListener("change", function (ev) {
            updateCartQuantity(cartItem, input, this.id)
            cartItem.querySelector(".cartItem__total").innerHTML = (cartItem.querySelector(".cartItem__price-new").innerHTML * input.value) + ""
            getParentEl("cart__list", cartItem).querySelector(".cart__total").innerHTML = calculateCartTotal();
        })

    }
})

// if (cartDeleteBtns) {
//     cartDeleteBtns.forEach(cartItemBtn => cartItemBtn.addEventListener("click", function (ev) {
//         ev.preventDefault()
//         const cartItem = getParentEl("cartItem", this)
//         deleteCartItem(cartItem, cartItem.querySelector("input").id)
//         getParentEl("cart__list", cartItem).querySelector(".cart__total").innerHTML = calculateCartTotal();
//     }))
// }

if (cartBtns.length > 0) {
    cartBtns.forEach((cartBtn) => {
        cartBtn.addEventListener("click", (ev) => {
            const input = cartBtn.parentElement.querySelector("input");
            switch (cartBtn.innerText) {
                case "+": {
                    input.value = parseInt(input.value) + 1;
                    break;
                }
                case "-": {
                    if (parseInt(input.value) > 0) {
                        input.value = parseInt(input.value) - 1;
                    }
                    break;
                }

            }
            input.setAttribute("value", input.value);
            const cartItem = getParentEl("cartItem", cartBtn)
            cartItem.querySelector(".cartItem__total").innerHTML = cartItem.querySelector(".cartItem__price-new").innerHTML * input.value
            getParentEl("cart__list", cartItem).querySelector(".cart__total").innerHTML = calculateCartTotal();
        });
    });
}

function calculateCartTotal() {
    return Array.from(cartItems).reduce((total, cartItem) => {
        return parseInt(cartItem.querySelector(".cartItem__total").innerHTML) + total
    }, 0)
}

function getParentEl(parentClass, el) {
    while (el) {
        if (el.classList.contains(parentClass)) return el;
        el = el.parentElement;
    }
    return null;
}

function deleteCartItem(cartItem, cartId) {

    // Tạo đối tượng XMLHttpRequest
    const xhr = new XMLHttpRequest();

    // Xác định phương thức và URL yêu cầu
    xhr.open("GET", "/cart/delete/" + cartId);

    // Thiết lập tiêu đề yêu cầu (nếu cần)
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

    // Xử lý sự kiện khi yêu cầu hoàn thành
    xhr.onreadystatechange = function() {
        if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
            // Xử lý phản hồi từ máy chủ (nếu cần)
            // Ví dụ: cập nhật lại nội dung trang web

            console.log(xhr.status);
        }
    };

    // Gửi yêu cầu với dữ liệu form
    xhr.send();
}

function updateCartList(cartItemDeleted) {
    const _cartItems = cartItems
    for (let i = 0; i < _cartItems.length; i++) {
        if (i + 1 !== parseInt(cartItemDeleted))
            _cartItems[i] = null
    }
    return _cartItems;
    // return Array.from(cartItems).filter((item, index) => ((index + 1) !== parseInt(cartItemDeleted)))
}


function updateCartQuantity(cartItem, input, cartId) {
    const quantity = parseInt(input.value);
    if (isNaN(quantity)) {
        return;
    }

    // Tạo đối tượng XMLHttpRequest
    const xhr = new XMLHttpRequest();

    // Xác định phương thức và URL yêu cầu
    xhr.open("POST", "/cart/" + cartId + "/update-quantity");

    // Thiết lập tiêu đề yêu cầu (nếu cần)
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

    // Xử lý sự kiện khi yêu cầu hoàn thành
    xhr.onreadystatechange = function() {
        if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
            // Xử lý phản hồi từ máy chủ (nếu cần)
            // Ví dụ: cập nhật lại nội dung trang web
            console.log(xhr.status);
        }
    };

    // Gửi yêu cầu với dữ liệu form
    xhr.send("quantity=" + quantity);
}

cartItems.forEach(cartItem => {
    cartItem.addEventListener("submit", function(ev) {
        ev.preventDefault()
        const input = cartItem.querySelector("input[name=quantity]")
        const id = input.id
        updateCartQuantity(cartItem, input, id)
    })
})
