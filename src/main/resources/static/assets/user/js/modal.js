const modal = document.querySelector(".modal")
const form = modal.querySelector("#form-update");
const closeBtns = document.querySelectorAll(".modal .modal__close-btn")
const addAddressBtn = document.querySelector(".btn__add-address")

let countries = [];
const fetchCountriesData = () => {
    fetch("https://restcountries.com/v3.1/all")
        .then((res) => res.json())
        .then((data) => display(data));
};

const fetchProvinceVn = () => {
    fetch("https://provinces.open-api.vn/api/p/?depth=2")
        .then((res) => res.json())
        .then((data) => displayProvince(data));
};

const fetchDistrictVn = (code) => {
    fetch(`https://provinces.open-api.vn/api/p/${code}?depth=3`)
        .then((res) => res.json())
        .then((data) => displayDistrict(data?.districts));
};

const getDistrict = (code) => {
    fetch(`https://provinces.open-api.vn/api/d/${code}?depth=2`)
        .then((res) => res.json())
        .then((data) => displayWard(data?.wards));
};

function displayWard(wards) {
    console.log(wards);
    if (wards && wards.length > 0) {
        const selectWard = form.querySelector("select#ward");
        selectWard.innerHTML = wards.reduce((options, item, index) => {
            return (
                options + `<option value="${item?.name}" >${item?.name}</option>`
            );
        }, "<option value='null' selected>--</option>");
    }
}

function displayDistrict(data) {
    console.log(data);
    if (data && data.length > 0) {
        const selectDistrict = form.querySelector("select#district");
        selectDistrict.innerHTML = data.reduce((options, item, index) => {
            return (
                options +
                `<option value="${item?.name}" data-d-code="${item?.code}">${item?.name}</option>`
            );
        }, "");
    }
    document
        .querySelector(".modal #form-update select#district")
        .addEventListener("change", function (ev) {
            const districtName = this.value;
            const districtCode = ev.target
                .querySelector(`option[value='${districtName}']`)
                .getAttribute("data-d-code");
            getDistrict(districtCode);
        });
}

const displayProvince = (data) => {
    console.log(data);
    if (data && data.length > 0) {
        const selectProvince = form.querySelector("select#province");
        selectProvince.innerHTML += data.reduce((options, item, index) => {
            return (
                options +
                `<option value="${item?.name}" data-p-code="${item?.code}">${item?.name}</option>`
            );
        }, "");
        selectProvince.addEventListener("change", function (ev) {
            const provinceName = this.value;
            const provinceCode = ev.target
                .querySelector(`option[value='${provinceName}']`)
                .getAttribute("data-p-code");
            fetchDistrictVn(provinceCode);
        });
    }
};

const display = (c) => {
    countries = c;
    const selectInput = form.querySelector("select#country");
    if (countries && countries.length > 0) {
        selectInput.innerHTML = countries.reduce((options, item) => {
            return (
                options +
                `<option value="${item?.name?.common}">${item?.name?.common}</option>`
            );
        }, "`<option value='Việt Nam' selected>Việt Nam</option>`");
    }
    selectInput.addEventListener("change", function(ev) {
        console.log(this.value === "Việt Nam")
        if (this.value !== "Việt Nam")
            form.querySelector(".vn__location").classList.add("hidden")
        else form.querySelector(".vn__location").classList.remove("hidden")
    })

};
fetchCountriesData();
fetchProvinceVn();



if (addAddressBtn) {
    addAddressBtn.addEventListener("click", function(ev) {
        ev.preventDefault()
        modal.classList.replace("hidden", "flex")
    })
}

if (closeBtns && closeBtns.length > 0) {
    closeBtns.forEach(closeBtn => closeBtn.addEventListener("click", function(ev) {
        ev.preventDefault()
        modal.classList.replace("flex", "hidden")
    }))
}

