//import {Message, Messages} from "ClientModel.js";

function ClientController(address, nick){

  this.webSocket = new WebSocket(address);
  this.nick = nick;
  //this.messages = new Messages();		esta dins de a funcio onmessage


  function send(message){
    this.webSocket.send(this.nick+'&%:'+message);
  }

  this.webSocket.onmessage = function(event){
    //Aqui s'implementen funcions de control;
  	this.messages = new Messages();	
    //Aqui guardem el missatge rebut al model;
    var dades = event.data.split('&%:');
    var missatge = new Message(dades[0], dades[1]);
    this.messages.push(missatge);
    refresh();
  }
  //this.webSocket.onclose = function(event){

  //}

}

//Al connectar s'envia el nick amb el següent format: &% Nick: <Nick>&%

//Client MOdel

function Message(nick, message){
  this.message = message;
  this.nick = nick;

  function toString(){
    return this.nick+': '+this.message;
  }
}

function Messages(){

  this.messages = new Array();

  function put(message){
    this.messages.push(message);
    //Despres de l'actualitzacio hem d'avisar a la vista
    refresh();
  }
}
