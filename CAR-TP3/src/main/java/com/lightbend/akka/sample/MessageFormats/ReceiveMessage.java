package com.lightbend.akka.sample.MessageFormats;

import java.util.HashSet;
import java.util.Set;

import akka.actor.ActorRef;

public class ReceiveMessage {
	
	public String message;
	public Set<String> visitedActors;
	
	
	public ReceiveMessage(String message) {
		this.message = message;
		this.visitedActors = new HashSet<>();
	}
	
	/**
	 * Ajoute, si il n'est pas déjà présent, actor dans le set visitedActors
	 * @param actor
	 * @return vrai si l'acteur a été ajouté, faux sinon
	 */
	public boolean addToVisited(ActorRef actor) {
		return visitedActors.add(actor.path().name());
	}
	
	/**
	 * Permet la vérification : un noeud n'a pas déjà reçu ce message
	 * @param actor
	 * @return vrai si actor est dans le set visitedActors
	 */
	public boolean hasAlreadyVisited(ActorRef actor) {
		return visitedActors.contains(actor.path().name());
	}
	
	public void visitedString() {
		System.out.println(this.visitedActors.toString());
	}
	
	public String toString() {
		return this.message;
	}

}
