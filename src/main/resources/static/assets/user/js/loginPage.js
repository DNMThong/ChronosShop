const loginForm = document.querySelector("#loginForm");
const usernameInput = document.querySelector("input[name='username']");
const passwordInput = document.querySelector("input[name='password']");

const isValidRequired = (element,message) => {
    const parentElement = element.parentElement
    if(element.value==="") {
        parentElement.querySelector(".message").innerText = message
        element.classList.add("border-red-400")
        return false;
    }else {
        parentElement.querySelector(".message").innerText = ""
        element.classList.remove("border-red-400")
        return true;
    }
}

loginForm.onsubmit = (e) => {
    const isValidUsername = isValidRequired(usernameInput,"Vui lòng nhập email")
    const isValidPassword = isValidRequired(passwordInput,"Vui lòng nhập password")
    console.log(isValidUsername,isValidPassword)
    if(!isValidUsername && !isValidPassword) {
        e.preventDefault();
    }
}

usernameInput.onchange = (e) => {
    const parentElement = usernameInput.parentElement
    if(usernameInput.value==="") {
        parentElement.querySelector(".message").innerText = message
        usernameInput.classList.add("border-red-400")
    }else {
        parentElement.querySelector(".message").innerText = ""
        usernameInput.classList.remove("border-red-400")
    }
}

passwordInput.onchange = (e) => {
    const parentElement = passwordInput.parentElement
    if(passwordInput.value==="") {
        parentElement.querySelector(".message").innerText = message
        passwordInput.classList.add("border-red-400")
    }else {
        parentElement.querySelector(".message").innerText = ""
        passwordInput.classList.remove("border-red-400")
    }
}




