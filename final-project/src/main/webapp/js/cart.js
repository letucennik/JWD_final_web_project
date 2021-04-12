let changedOrderItems=[];
let allOrderItems=[];
var i=0;
var elements = document.getElementsByTagName('a');

$('a').click(function (){
    updateNumberOfProducts();
});
function updateNumberOfProducts(){
    var allId=document.getElementsByClassName("allId");
    for(k=0;k<allId.length;k++){
        allOrderItems[k]=allId[k].value;
    }
    for(j=0;j<changedOrderItems.length;j++){
        let id=changedOrderItems[j];
        var stringId=id.toString();
        var stringFixedPriceId="quantity"+stringId;
        var newAmount=parseInt(document.getElementById(stringFixedPriceId).value);

        $.ajax({
            type:'POST',
            url:'Controller?command=editproductamount',
            data:'&id='+id+'&amount='+newAmount,
        });
    }
}


function updateQuantity(id,quantityInput){
    changedOrderItems[i]=id;
    var newQuantity=$(quantityInput).val();
    var stringId=id.toString();
    var stringFixedPriceId="fixed_price"+stringId;
    var currentPriceField=document.getElementById(stringId);
    var fixedProductPrice=document.getElementById(stringFixedPriceId);
    var overall=document.getElementById('overall_price');

    var oldCurrentPrice=parseFloat(currentPriceField.innerText);

    currentPriceField.innerText=(newQuantity*parseFloat(fixedProductPrice.innerText)).toString()+" $";

    var totalPrice=parseFloat(overall.innerText);
    totalPrice-=oldCurrentPrice;
    totalPrice+=parseFloat(currentPriceField.innerText);

    overall.innerText=totalPrice.toString()+" $";
    i++;
}