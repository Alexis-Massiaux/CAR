package com.lightbend.akka.sample;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;

import java.io.IOException;

import com.lightbend.akka.sample.Actor.Greeting;

public class Start {
	
	public static void main(String[] args) throws IOException {
	
		ActorSystem system = ActorSystem.create("MySystem");
		ActorRef greeter0, greeter1, greeter2, greeter3;
		
		greeter0 = system.actorOf(Printer.props(), "zero");
		//greeter1 = system.actorOf(Actor.props("Begin", greeter0), "one");
		greeter2 = system.actorOf(Actor.props(), "two");
		greeter3 = system.actorOf(Actor.props(), "three");
		
		//greeter1.tell(new Greeting("TRON"), greeter3);
		//greeter2.tell(new Greeting("PASS"), greeter1);
		greeter3.tell(new Greeting("USER"), greeter2);
		
		System.out.println(">>> Press ENTER to exit <<<");
	     
		System.in.read();
		
		system.terminate();
		
		
	}
}
