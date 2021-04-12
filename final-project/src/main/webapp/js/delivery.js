const form=document.getElementById('form');
let allDeliveryItems=[];
let allAmountsVal=[];
const deliveryId=document.getElementById('deliveryId').value.trim();
form.addEventListener('submit',e=>{
    var allId=document.getElementsByClassName("allProducts");
    var allAmounts=document.getElementsByClassName('allAmounts');
    for(k=0;k<allId.length;k++){
        allDeliveryItems[k]=allId[k].value;
        allAmountsVal[k]=allAmounts[k].value;
    }
    for(j=0;j<allDeliveryItems.length;j++){
        $.ajax({
            type:'POST',
            url:'Controller?command=savedeliveryitem',
            data:'&delivery='+deliveryId+'&product='+allDeliveryItems[j]+'&amount='+allAmountsVal[j],
        });
    }


});
