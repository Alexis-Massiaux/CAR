package com.lightbend.akka.sample.MessageFormats;

import akka.actor.ActorRef;

public class ConnectionMessage {
	
	ActorRef children;
	
	public ConnectionMessage(ActorRef children) {
		this.children=children;
	}
	
	public ActorRef getChildren() {
		return this.children;
	}
	
	public String toString() {
		return "Connexion avec le noeud : "+children.path().name();
	}

}
