//import {Message, Messages} from "ClientModel.js";

function ClientController(address){
  this.webSocket = new WebSocket(address);
  this.nick;


  this.webSocket.onmessage = function(event){ onMessage(event); }
  function onMessage(event){
    //Primer parsejem el missatge
    var dades = event.data;
    if(dades.match("&%")!= undefined){
      if(dades.match("&%OK")=="&%OK"){
        clientController.setNick();
      }
      else if (dades.match("&%BAD_NICKNAME")=="&%BAD_NICKNAME") {
        wait = false;
      }
      else{
        dades = dades.split('&%:');
        var missatge = new Message(dades[0], dades[1]);
        messages.put(missatge);
      }
    }
  }

}
ClientController.prototype.send = function(message){
  this.webSocket.send(this.nick+'&%:'+message);
  return this;
}
ClientController.prototype.setNick = function(){
  wait = false;
  var missatge = new Message("Benvingut al Xat!", "" );
  messages.put(missatge);
}

ClientController.prototype.checkNick = function(nick){
  this.webSocket.send("&%hasNick'"+nick+"'");
  this.nick = nick;
}
//Al connectar s'envia el nick amb el següent format: &% Nick: <Nick>&%
