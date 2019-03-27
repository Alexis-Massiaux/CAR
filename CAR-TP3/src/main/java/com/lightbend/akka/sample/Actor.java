package com.lightbend.akka.sample;

import akka.actor.AbstractActor;
import akka.actor.Props;

public class Actor extends AbstractActor {
	
	private static String message = "";
	
	public Actor(String debut) {
		this.message = "Debut message : ";
	}
	
	public static Props props() {
	    return Props.create(Actor.class, () -> new Actor(message));
	  }

	//#printer-messages
	public static class Greeting {
		public final String message;

		public Greeting(String message) {
			this.message = message;
		}
	}

	@Override
	public Receive createReceive() {
		return receiveBuilder().match(Greeting.class, greeting -> { this.message += " [TRANSMISSION] "+greeting.message; }).build();
	}
}
