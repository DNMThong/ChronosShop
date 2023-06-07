const cartBtns = document.querySelectorAll(".btn-cart")
const cartItems = document.querySelectorAll("form.cartItem")

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
        });
    });
}


function updateCartQuantity(cartItem, input, cartId) {
    const quantity = parseInt(input.value);
    if (isNaN(quantity)) {
        return;
    }

    // Tạo đối tượng XMLHttpRequest
    const xhr = new XMLHttpRequest();

    // Xác định phương thức và URL yêu cầu
    xhr.open("POST", "/test/cart/" + cartId + "/update-quantity");

    // Thiết lập tiêu đề yêu cầu (nếu cần)
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

    // Xử lý sự kiện khi yêu cầu hoàn thành
    xhr.onreadystatechange = function() {
        if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
            // Xử lý phản hồi từ máy chủ (nếu cần)
            // Ví dụ: cập nhật lại nội dung trang web
            cartItem.innerHTML = xhr.responseText
            console.log(xhr.responseText);
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

