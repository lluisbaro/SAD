//import {Message, Messages} from "ClientModel.js";

function ClientController(address, nick){

  this.webSocket = new WebSocket(address);
  this.nick = nick;
  this.messages = new Messages();

  function send(message){
    this.webSocket.send(this.nick+'&%:'+message);
  }

  this.webSocket.onmessage = function(event){
    //Aqui s'implementen funcions de control;

    //Aqui guardem el missatge rebut al model;
    var dades = this.event.data.split('&%:');
    var missatge = new Message(dades[0], dades[1]);
    this.messages.put(missatge);
  }
  //this.webSocket.onclose = function(event){

  //}

}

//Al connectar s'envia el nick amb el següent format: &% Nick: <Nick>&%
