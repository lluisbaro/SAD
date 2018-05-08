function Message(nick, message){
  this.message = message;
  this.nick = nick;
//Comprovar que tenen nick diferent
  function toString(){
    return this.nick+': '+this.message;
  }
}

function Messages(){
  this.messages = new Array();
  this.messages.push(new Message('Missatge', 'Inicial'));
}
Messages.prototype.put = function(message){
  this.messages.push(message);
  refresh(message);
}
