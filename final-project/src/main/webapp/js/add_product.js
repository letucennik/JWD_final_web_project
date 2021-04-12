const nameEn = document.getElementById('name');
const nameRu = document.getElementById('nameRu');
const price=document.getElementById('price');
const image=document.getElementById('image');
const emptyName = document.getElementById('empty');
const invalidNameEn = document.getElementById('invalidNameEn');
const invalidNameRu = document.getElementById('invalidNameRu');
const nameRuHint=document.getElementById('hintNameRu');
const nameEnHint=document.getElementById('hintNameEn');
const emptyPrice=document.getElementById('emptyPrice');
const fileNotChosen=document.getElementById('fileNotChosen');
form.addEventListener('submit', e => {
    if (!checkInputs()) {
        e.preventDefault();
    }
});

function checkInputs() {
    let correct=true;
    const nameEnValue = nameEn.value.trim();
    const nameRuValue = nameRu.value.trim();
    const priceValue=price.value.trim();
    const imageValue=image.value.trim();
    if(nameEnValue===''){
        setErrorFor(nameEn,emptyName.value);
        correct=false;
    } else if(!isValidNameEn(nameEnValue)){
        setErrorFor(nameEn,nameEnHint.value)
        correct=false;
    } else {
        setSuccessFor(nameEn);
    }

    if(nameRuValue===''){
        setErrorFor(nameRu,emptyName.value);
        correct=false;
    } else if(!isValidNameRu(nameRuValue)){
        setErrorFor(nameRu,nameRuHint.value)
        correct=false;
    } else {
        setSuccessFor(nameRu);
    }

    if(priceValue===''){
        setErrorFor(price,emptyPrice.value);
        correct=false;
    }else {
        setSuccessFor(price);
    }
    if(imageValue===''){
        setErrorFor(image,fileNotChosen.value);
        correct=false;
    }else {
        setSuccessFor(image);
    }
    return correct;
}

function setSuccessFor(input) {
    const formControl = (input.parentNode).parentNode;
    formControl.className = 'form-group success';
}

function setErrorFor(input, message) {
    const formControl = (input.parentNode).parentNode;
    const small = input.parentNode.querySelector('small');
    formControl.className = 'form-group error';
    small.innerText = message;
}
function isValidNameEn(nameEn){
    return /^[a-zA-Z\\s]{3,45}$/.test(nameEn);
}
function isValidNameRu(nameRu){
    return /^[а-яА-Я\\s]{3,45}$/.test(nameRu);
}
