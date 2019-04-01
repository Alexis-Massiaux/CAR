package com.lightbend.akka.sample.Structure;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class Systeme {
	
	public ActorSystem systeme;
	
	public Systeme(String systemeName) {
		this.systeme = ActorSystem.create(systemeName);
		System.out.println("Creation d'un ActorSytem : "+systemeName);
	}
	
	/**
	 * Crée un noeud name avec le system courant et le retourne
	 * @param name nom du noeud
	 * @return
	 */
	public ActorRef createNode(String name) {
		System.out.println("Creation d'un noeud : "+name+" par le systeme : "+systeme.name());
		return this.systeme.actorOf(Props.create(Noeud.class, name), name);
	}
	
	/**
	 * Permet de fermer proprement un ActorSysteme
	 */
	public void close() {
		this.systeme.terminate();
		System.out.println("Interruption du systeme : "+this.systeme.name());
	}
	
	public ActorSystem getSysteme() {
		return this.systeme;
	}
	
	public String toString() {
		return this.systeme.name();
	}

}
