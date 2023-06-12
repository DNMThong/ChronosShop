const form = document.querySelector("#order-form");
const selectProvince = form.querySelector("select#province");
const selectWard = form.querySelector("select#ward");
const selectDistrict = form.querySelector("select#district");
const selectNumber = form.querySelector("select#number")

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

        selectDistrict.innerHTML = data.reduce((options, item, index) => {
            return (
                options +
                `<option value="${item?.name}" data-d-code="${item?.code}">${item?.name}</option>`
            );
        }, "<option value='null' selected>--</option>");
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
    if (data && data.length > 0) {

        selectProvince.innerHTML += data.reduce((options, item, index) => {
            return (
                options +
                `<option value="${item?.name}" data-p-code="${item?.code}">${item?.name}</option>`
            );
        }, "<option value='null' selected>--</option>");
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

locationList.onchange = function(ev) {
    const address = this.value;
    const _add = address.split(", ")
    selectNumber.value = _add[0]
    selectWard.value = _add[1]
    selectDistrict.value = _add[2]
    selectProvince.value = _add[3]
}
