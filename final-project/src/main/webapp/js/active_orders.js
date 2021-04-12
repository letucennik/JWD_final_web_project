const changedOrders=[];
let i=0;
$('a').click(function (){
    updateReadyDate();
});
function updateReadyDate(){
    for(j=0;j<changedOrders.length;j++){
        let id=changedOrders[j];
        var stringId=id.toString();
        var stringReadyDateId="ready_date"+stringId;
        var newReadyDate=document.getElementById(stringReadyDateId).value;
        $.ajax({
            type:'POST',
            url:'Controller?command=updateorderreadydate',
            data:'&orderId='+id+'&date='+newReadyDate+' 00:00:00.000000',
        });
    }
}
function updateDate(id,input){
    changedOrders[i]=id;
}