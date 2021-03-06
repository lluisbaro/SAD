var messages = new Messages();
var clientController;
var textXat;
var confirmed;

//Aquesta funció s'executa un cop carregada la pàgina així podem obtenir tots els elements de la mateixa;
function once_loaded(){
  //Amaguem el xat
  document.getElementById("post").style.display="none";
  clientController = new ClientController('ws://localhost:8080');
}

function main_start(){
  //Agafem el valor del nick i el guardem
  var nick = document.getElementById('nick').value;
  clientController.checkNick(nick);
  confirmed = false;
  //retornem false perquè el formulari no recarregui la pàgina
  return false;
}

function show_answer(){
  if (confirmed){
    //Amaguem el formulari inicial i mostrem el xat
    document.getElementById("inici").style.display="none";
    document.getElementById("post").style.display="block";
  } else{
    document.getElementById('bad_nick').innerHTML = "Aquest nom ja està agafat!";
  }
}

function send(){
  //Agamfem el missatge del view i si té contingut l'enviem
  var missatge = document.getElementById('missatge');
  if (missatge.value != ""){
    clientController.send(missatge.value);
    missatge.value="";
  }
  return false;
}

function refresh(missatge){
  //Cada cop que rebem algun missatge el model avisa mitjançant refresh perquè aparegui el nou missatge
  insertChat(missatge.nick, missatge.message);
}

function formatAMPM(date) {
    //funció que formata l'hora de l'enviament.
    var hours = date.getHours();
    var minutes = date.getMinutes();
    var ampm = hours >= 12 ? 'PM' : 'AM';
    hours = hours % 12;
    hours = hours ? hours : 12; // the hour '0' should be '12'
    minutes = minutes < 10 ? '0'+minutes : minutes;
    var strTime = hours + ':' + minutes + ' ' + ampm;
    return strTime;
}

function insertChat(who, text){
    //Funció que inserta la bombolla de xat.
    var control = "";
    var date = formatAMPM(new Date());

    if (who == clientController.nick){
        control = '<li style="width:100%">' +
                        '<div class="msj-rta macro">' +
                            '<div class="text text-l">' + //'<div class="text text-l"> Jo:'
                                '<p>'+ text +'</p>' +
                                '<p><small>'+date+'</small></p>' +
                            '</div>' +
                        '</div>' +
                    '</li>';
    }else{
        control = '<li style="width:100%;">' +
                        '<div class="msj macro">' +
                            '<div class="text text-r"> <b>'+who+'</b>' +
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

//-- Clear Chat
resetChat();
