var messages = new Messages();
var clientController;
var textXat;
function once_loaded(){
  document.getElementById("post").style.display="none";
}
function main_start(){
  document.getElementById("inici").style.display="none";
  document.getElementById("post").style.display="block";
  var nick = document.getElementById('nick').value;
  clientController = new ClientController('ws://127.0.0.1:8887', nick);
}
function send(){
  var missatge = document.getElementById('missatge');
  clientController.send(missatge.value);
  missatge.value="";
}
function refresh(missatge){
  insertChat(missatge.nick, missatge.message);
}

function formatAMPM(date) {
    var hours = date.getHours();
    var minutes = date.getMinutes();
    var ampm = hours >= 12 ? 'PM' : 'AM';
    hours = hours % 12;
    hours = hours ? hours : 12; // the hour '0' should be '12'
    minutes = minutes < 10 ? '0'+minutes : minutes;
    var strTime = hours + ':' + minutes + ' ' + ampm;
    return strTime;
}

//-- No use time. It is a javaScript effect.
function insertChat(who, text){
    var control = "";
    var date = formatAMPM(new Date());

    if (who == clientController.nick){
        control = '<li style="width:100%">' +
                        '<div class="msj macro">' +
                            '<div class="text text-l">' +
                                '<p>'+ text +'</p>' +
                                '<p><small>'+date+'</small></p>' +
                            '</div>' +
                        '</div>' +
                    '</li>';
    }else{
        control = '<li style="width:100%;">' +
                        '<div class="msj-rta macro">' +
                            '<div class="text text-r">' +
                                '<p>'+text+'</p>' +
                                '<p><small>'+date+'</small></p>' +
                            '</div>' +
                        '</div>' +
                  '</li>';
    }
    $("ul").append(control).scrollTop($("ul").prop('scrollHeight'));

}

function resetChat(){
    $("ul").empty();
}

$(".mytext").on("keydown", function(e){
    if (e.which == 13){
        var text = $(this).val();
        if (text !== ""){
            clientController.send(text);
            $(this).val('');
        }
    }
});

$('body > div > div > div:nth-child(2) > span').click(function(){
    $(".mytext").trigger({type: 'keydown', which: 13, keyCode: 13});
})

//-- Clear Chat
resetChat();
