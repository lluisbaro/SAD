//import {Message, Messages} from "ClientModel.js";

function ClientController(address){
  this.webSocket = new WebSocket(address);
  this.nick;


  this.webSocket.onmessage = function(event){ onMessage(event); }

  function onMessage(event){
    var dades = event.data.split('&%:');
    var missatge = new Message(dades[0], dades[1]);
    messages.put(missatge);
  }

}
ClientController.prototype.send = function(message){

  this.webSocket.send(this.nick+'&%:'+message);
  return this;
}
ClientController.prototype.setNick = function(nick){
  this.nick = nick;
}

//Al connectar s'envia el nick amb el següent format: &% Nick: <Nick>&%
