const form = document.querySelector("#order-form");
const selectProvince = form.querySelector("select#province");
const selectWard = form.querySelector("select#ward");
const selectDistrict = form.querySelector("select#district");
const inputNumber = form.querySelector("input#number")
const inputRecipientName = form.querySelector("input#recipientName")
const inputRecipientPhone = form.querySelector("input#recipientPhone")


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

        selectWard.innerHTML += wards.reduce((options, item, index) => {
            return (
                options + `<option value="${item?.name}" >${item?.name}</option>`
            );
        }, "");
    }
}

function displayDistrict(data) {
    console.log(data);
    if (data && data.length > 0) {

        selectDistrict.innerHTML += data.reduce((options, item, index) => {
            return (
                options +
                `<option value="${item?.name}" data-d-code="${item?.code}">${item?.name}</option>`
            );
        }, "");
    }
    form.querySelector("select#district").addEventListener("change", function (ev) {
        const districtName = this.value;
        const districtCode = ev.target
            .querySelector(`option[value='${districtName}']`)
            .getAttribute("data-d-code");
        getDistrict(districtCode);
    });
}

const displayProvince = (data) => {
    if (data && data.length > 0) {

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
fetchProvinceVn();

const locationList = form.querySelector("#locationList")

locationList.onchange = function (ev) {
    const address = locationList.value;
    if (address !== "other") {
        const _add = address.split(", ")

        inputRecipientName.value = _add[0]
        inputRecipientPhone.value = locationList.querySelector("option[selected]").getAttribute("data-phone")
        inputNumber.value = _add[1]
        selectWard.innerHTML = `<option value="${_add[2]}" selected>${_add[2]}</option>` + "\n" + selectWard.innerHTML
        selectDistrict.innerHTML = `<option value="${_add[3]}" selected>${_add[3]}</option>` + "\n" + selectDistrict.innerHTML
        selectProvince.innerHTML = `<option value="${_add[4]}" selected>${_add[4]}</option>` + "\n" + selectProvince.innerHTML
    } else {
        inputRecipientName.value = ""
        inputRecipientPhone.value = ""
        inputNumber.value = ""
        selectWard.innerHTML = `<option value="null" selected>--</option>`
        selectDistrict.innerHTML = `<option value="null" selected>--</option>`
        selectProvince.innerHTML = `<option value="null" selected>--</option>`
        fetchProvinceVn();
    }

}

const orderTotal = form.querySelector(".order__total")
orderTotal.innerText = Array.from(form.querySelectorAll(".order__card")).reduce((total, item) => {
    const stringPrice = item.querySelector(".prod__total-price").innerText
    const price = parseInt(stringPrice.substring(0, stringPrice.length - 1))
    return total + price;
}, 0)

