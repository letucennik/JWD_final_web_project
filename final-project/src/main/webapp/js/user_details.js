const form=document.getElementById('form');
const firstName=document.getElementById('first_name');
const secondName=document.getElementById('second_name');
const phone=document.getElementById('phone');
const address=document.getElementById('address');
const emptyFirstName=document.getElementById('empty_first_name');
const invalidFirstName=document.getElementById('invalid_first_name');
const emptySecondName=document.getElementById('empty_second_name');
const invalidSecondName=document.getElementById('invalid_second_name');
const emptyPhone=document.getElementById('empty_phone');
const invalidPhone=document.getElementById('invalid_phone');
const emptyAddress=document.getElementById('empty_address');
form.addEventListener('submit',e=>{
    if (!checkInputs()){
        e.preventDefault();
    }
});
function checkInputs(){
    var isCorrect=true;
    const firstNameValue=firstName.value.trim();
    const secondNameValue=secondName.value.trim();
    const phoneValue=phone.value.trim();
    const addressValue=address.value.trim();
    if(firstNameValue===''){
        setErrorFor(firstName,emptyFirstName.value);
        isCorrect=false;
    } else if(!isValidName(firstNameValue)){
        setErrorFor(firstName,invalidFirstName.value);
        isCorrect=false;
    }
    else{
        setSuccessFor(firstName);
    }

    if(secondNameValue===''){
        setErrorFor(secondName,emptySecondName.value);
        isCorrect=false;
    } else if(!isValidName(secondNameValue)){
        setErrorFor(secondName,invalidSecondName.value);
        isCorrect=false;
    }
    else{
        setSuccessFor(secondName);
    }
    if(phoneValue===''){
        setErrorFor(phone,emptyPhone.value);
        isCorrect=false;
    } else if(!isValidPhone(phoneValue)){
        setErrorFor(phone,invalidPhone.value);
        isCorrect=false;
    }
    else{
        setSuccessFor(phone);
    }
    if(addressValue===''){
        setErrorFor(address,emptyAddress.value);
        isCorrect=false;
    }
    else{
        setSuccessFor(address);
    }
    return isCorrect;
}

function isValidName(name){
    return /^[а-яА-я]{2,30}$/.test(name);
}
function isValidPhone(phone){
    return /^(375|80)(29|25|44|33)(\d{3})(\d{2})(\d{2})$/.test(phone);
}
function setErrorFor(input, message) {
    const formControl = (input.parentNode).parentNode;
    const small = input.parentNode.querySelector('small');
    formControl.className = 'form-group error';
    small.innerText = message;
}
function setSuccessFor(input) {
    const formControl = (input.parentNode).parentNode;
    formControl.className = 'form-group success';
}