$("#link").click(function(ev) {
    alert("aa");
    if(!confirm("Подтвердите действие")) {
        ev.preventDefault();
    }
});
$('link').addEventListener('click',ev => {
    if(!confirm("Подтвердите действие")) {
        ev.preventDefault();
    }
});