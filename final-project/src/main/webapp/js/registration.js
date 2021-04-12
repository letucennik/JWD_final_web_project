const form=document.getElementById('form');
const username=document.getElementById('username');
const password=document.getElementById('password');
const passwordConfirm=document.getElementById('password-confirm');
const email=document.getElementById('email');
const emptyUsername=document.getElementById('empty_username');
const invalidUsername=document.getElementById('username_hint');
const emptyEmail=document.getElementById('empty_email');
const invalidEmail=document.getElementById('invalid_email')
const emptyPassword=document.getElementById('empty_password')
const passwordHint=document.getElementById('password_hint')
const invalidPasswordConfirm=document.getElementById('invalid_password_confirm')
form.addEventListener('submit',e=>{
    if (!checkInputs()){
        e.preventDefault();
    }
});
function checkInputs(){
    var isCorrect=true;
    const usernameValue = username.value.trim();
    const emailValue = email.value.trim();
    const passwordValue = password.value.trim();
    const passwordConfirmValue=passwordConfirm.value.trim();

    if(usernameValue===''){
        setErrorFor(username,emptyUsername.value);
        isCorrect=false;
    } else if(!isValidUsername(usernameValue)){
        setErrorFor(username,invalidUsername.value);
        isCorrect=false;
    }
    else{
        setSuccessFor(username);
    }

    if(emailValue===''){
        setErrorFor(email,emptyEmail.value);
        isCorrect=false;
    } else if(!isValidEmail(email.value)){
        setErrorFor(email,invalidEmail.value);
        isCorrect=false;
    } else{
        setSuccessFor(email);
    }

    if(passwordValue===''){
        setErrorFor(password,emptyPassword.value);
        isCorrect=false;
    } else if(!isValidPassword(password.value)){
        setErrorFor(password,passwordHint.value);
        isCorrect=false;
    } else{
        setSuccessFor(password);
    }

    if(passwordConfirmValue!==passwordValue){
        setErrorFor(passwordConfirm,invalidPasswordConfirm.value);
        isCorrect=false;
    }  else if(passwordConfirmValue===''){
        setErrorFor(passwordConfirm,emptyPassword.value);
    }
    else{
        setSuccessFor(passwordConfirm);
    }
    return isCorrect;
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
function isValidUsername(username){
    return /^[a-zA-Z_0-9]{3,45}$/.test(username);
}
function isValidEmail(email){
    return /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/.test(email);
}
function isValidPassword(password){
    return password.length>=6&&password.length<=60&&hasUppercaseChar(password)&&hasLowercaseChar(password)&&hasNumber(password);
}
function hasUppercaseChar(str) {
    return str.toLowerCase() !== str.value;
}
function hasLowercaseChar(str) {
    return str.toLowerCase() !== str.value;
}
function hasNumber(str) {
    return /\d/.test(str);
}