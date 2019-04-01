package com.lightbend.akka.sample.Structure;

import com.lightbend.akka.sample.MessageFormats.ConnectionMessage;

import akka.actor.ActorRef;

public class Body {
	
	public ActorRef[] noeuds;
	public Systeme[] systeme;
	public int size;
	
	public Body(int size) {
		this.size = size;
		this.noeuds = new ActorRef[this.size];
		
		//On considere ici que l'on ne déclarera pas plus de systèmes qu'il y'a de noeuds
		this.systeme = new Systeme[this.size];
	}
	
	/**
	 * Genere (fin-debut)+1 ActorRef
	 * @param name 
	 * @param debut index de depart (position dans le tableau noeuds)
	 * @param fin index de fin (position dans le tableau noeuds)
	 * @param indexSysteme index du système utilisé stocké dans le tableau systeme
	 */
	public void generateNoeuds(String name, int debut, int fin, int indexSysteme) {
		String currentName = "";
		
		for(int i=debut; i<=fin; i++) {
			currentName = name+(i+1);
			noeuds[i] = this.systeme[indexSysteme].createNode(currentName);
		}
	}
	
	public void generateSystemes(String name, int nb) {
		String currentName = "";
		
		for(int i=0; i<nb; i++) {
			currentName = name+(i+1);
			this.systeme[i] = new Systeme(currentName);
		}
	}
	
	/**
	 * Établit une connexion entre un noeud parent et un noeud enfant
	 * @param parent
	 * @param children
	 */
	public void connectionParentChildren(int parent, int children) {
		this.getNoeud(parent).tell(new ConnectionMessage(this.getNoeud(children)), this.getNoeud(parent));
		
	}
	
	public ActorRef[] getNoeuds() {
		return this.noeuds;
	}
	
	public ActorRef getNoeud(int index) {
		return this.noeuds[index];
	}
	
	public Systeme getSysteme(int index) {
		return systeme[index];
	}
	
	/**
	 * Permet de fermer proprement tout les systèmes
	 */
	public void closeSystemes() {
		Systeme current = null;
		for(int i=0; i<this.size; i++) {
			current = this.systeme[i];
			if (current != null) {
				this.systeme[i].close();
			}
		}
	}

}
