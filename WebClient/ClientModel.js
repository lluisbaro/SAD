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
