package com.lightbend.akka.sample.Structure;

import java.util.LinkedList;
import java.util.List;

import com.lightbend.akka.sample.MessageFormats.ConnectionMessage;
import com.lightbend.akka.sample.MessageFormats.ReceiveMessage;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;

@SuppressWarnings("deprecation")
public class Noeud extends UntypedActor{

	public List<ActorRef> childrens;
	String name;

	/**
	 * Constructeur de la classe Noeud
	 * @param noeudName Nom du noeud
	 */
	public Noeud(String noeudName) {
		this.childrens = new LinkedList<>();
		this.name = noeudName;
	}

	public void sendMessages(ReceiveMessage message) {
		for(ActorRef children : childrens) {
			if(!message.hasAlreadyVisited(children)) {
				//message.visitedString();
				System.out.println(getSelf().path().name() +" envoi du message : \""+message.toString()+"\" vers "+ children.path().name());
				message.addToVisited(children);
				//forward permet de connaître l'origine de l'envoi du message / tell s'arrête au dernier qui l'a transmis
				children.forward(message, getContext());
			}
		}
	}

	@Override
	public void onReceive(Object message) throws Throwable {
		if(message instanceof ConnectionMessage) {
			ConnectionMessage cm = (ConnectionMessage) message;
			childrens.add(cm.getChildren());
			System.out.println(this.name+" receptionne (ConnectionMessage) : "+cm.toString());

			return;
		}
		if(message instanceof ReceiveMessage) {
			ReceiveMessage rm = (ReceiveMessage) message;
			sendMessages(rm);
			//rm.visitedString();
			return;
		}
	}

}
