package com.lightbend.akka.sample;

import java.util.TreeMap;

import akka.actor.ActorRef;

public class Tree {
	
	TreeMap<ActorRef, ActorRef> tree;
	
	public Tree() {
		this.tree = new TreeMap<>();
	}
	
	public TreeMap<ActorRef, ActorRef> getTree() {
		return this.tree;
	}

}
