package com.lightbend.akka.sample;

import java.io.IOException;

import com.lightbend.akka.sample.MessageFormats.ReceiveMessage;
import com.lightbend.akka.sample.Structure.Body;

import akka.actor.ActorRef;

public class Main {
	
	/**
	 * Permet de retrouver l'entité d'un noeud à partir de son nom
	 * @param noeuds liste des noeuds créer 
	 * @param noeud nom du noeud recherché
	 * @return un noeud si retrouvé, null sinon
	 */
	public static ActorRef StringToActor(ActorRef[] noeuds, String noeud) {
		ActorRef retour = null;
		
		for(ActorRef n : noeuds) {
			if(noeud.equals(n.path().name())) {
				retour = n;
				break;
			}
		}
		return retour;
	}
	
	public static void pause() throws InterruptedException {
		Thread.sleep(1000);
		System.out.println("******************************\n");
	}
	
	
	public static void main(String[] args) throws IOException, InterruptedException {
		Body body = new Body(6);
		
		//Initialisation des systèmes
		System.out.println("***INTIALISATION DES SYSTEMES***");
		body.generateSystemes("Systeme", 2);
		
		pause();
		
		//Initialisation des noeuds
		System.out.println("***INITIALISATION DES NOEUDS***");
		//Créer 4 noeuds (de 1 à 4) par le systeme 1
		body.generateNoeuds("Noeud", 0, 3, 0);
		//Créer 2 noeuds (de 5 à 6) par le systeme 2
		body.generateNoeuds("Noeud", 4, 5, 1);
		
		pause();
		
		//Mise en relations des noeuds
		System.out.println("***MISE EN RELATION DES NOEUDS***");
		body.connectionParentChildren(0, 1);
		body.connectionParentChildren(0, 4);
		
		body.connectionParentChildren(1, 2);
		body.connectionParentChildren(1, 3);
		
		body.connectionParentChildren(4, 5);
		
		pause();
		
		//DAR - Diffuser un message asynchrone dans un arbre de nœuds à partir de la racine
		System.out.println("***Envoi d'un message depuis la racine***");
		body.getNoeud(0).tell(new ReceiveMessage("Envoi d'un message a partir de la racine"), ActorRef.noSender());
		
		pause();
		
		//DAN - Diffuser un message asynchrone dans un arbre de nœuds à partir de n'importe quel nœud
		System.out.println("***Envoi d'un message depuis n'importe quel noeud***");
		try {
			StringToActor(body.getNoeuds(), args[0]).tell(new ReceiveMessage("Envoi d'un message a partir de n'importe quel noeud"), ActorRef.noSender());
		}catch(NullPointerException e) {
			System.err.println("Le noeud "+args[0]+" est inconnue !");
		}
		
		pause();
		
		//DMG - Diffuser un message dans un graphe de nœuds
		System.out.println("***Envoi d'un message dans un graphe***");
		body.connectionParentChildren(3, 5);
		pause();
		
		body.getNoeud(0).tell(new ReceiveMessage("Envoi d'un message dans un graphe"), ActorRef.noSender());
		
		pause();
		
		//Fermeture des systèmes
		System.out.println("***Fermeture des ActorSystem***");
		body.closeSystemes();
		
		pause();
	}

}
