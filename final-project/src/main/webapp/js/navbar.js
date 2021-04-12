function setVisible(id){
    var megaBox=document.getElementById("mega-box_"+id.toString());
    megaBox.style.visibility="visible";
    megaBox.style.opacity="1";
}
function setInvisible(id){
    var megaBox=document.getElementById("mega-box_"+id.toString());
    megaBox.style.visibility="hidden";
    megaBox.style.opacity="0";
}