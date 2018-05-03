function ClientController(address, nick){

  this.webSocket = new WebSocket(address);
  this.nick = nick;
  function send(message){
    this.webSocket.send(message);
  }
  
}
