package com.lightbend.akka.sample;

import java.io.IOException;
import java.util.Set;

import com.lightbend.akka.sample.Greeter.Greet;
import com.lightbend.akka.sample.Greeter.WhoToGreet;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;

public class AkkaQuickstart {
  public static void main(String[] args) {
    final ActorSystem system = ActorSystem.create("AkkaCar");
    try {
      //#create-actors
      final ActorRef printerActor = 
        system.actorOf(Printer.props(), "printerActor");
      final ActorRef howdyGreeter = 
        system.actorOf(Greeter.props("Howdy", printerActor), "howdyGreeter");
      final ActorRef helloGreeter = 
        system.actorOf(Greeter.props("Hello", printerActor), "helloGreeter");
      final ActorRef goodDayGreeter = 
        system.actorOf(Greeter.props("Good day", printerActor), "goodDayGreeter");
      //#create-actors
      
      Tree tree = new Tree();
      tree.getTree().put(goodDayGreeter, helloGreeter);
      tree.getTree().put(helloGreeter, howdyGreeter);
      tree.getTree().put(howdyGreeter, goodDayGreeter);
      
      Set<ActorRef> set = tree.getTree().keySet();
      
      for(ActorRef ref : set) {
    	  ref.tell(new WhoToGreet(tree.getTree().get(ref).toString()), tree.getTree().get(ref));
          ref.tell(new Greet(), ActorRef.noSender());
      }
      
      /*ActorRef root = tree.getTree().firstKey();
      root.tell(new WhoToGreet(tree.getTree().get(root).toString()), tree.getTree().get(root));
      root.tell(new Greet(), ActorRef.noSender());*/

      //#main-send-messages
      /*howdyGreeter.tell(new WhoToGreet("Akka"), ActorRef.noSender());
      howdyGreeter.tell(new Greet(), ActorRef.noSender());

      howdyGreeter.tell(new WhoToGreet("Lightbend"), ActorRef.noSender());
      howdyGreeter.tell(new Greet(), ActorRef.noSender());

      helloGreeter.tell(new WhoToGreet("Java"), ActorRef.noSender());
      helloGreeter.tell(new Greet(), ActorRef.noSender());

      goodDayGreeter.tell(new WhoToGreet("Play"), ActorRef.noSender());
      goodDayGreeter.tell(new Greet(), ActorRef.noSender());*/
      //#main-send-messages

      System.out.println(">>> Press ENTER to exit <<<");
      System.in.read();
    } catch (IOException ioe) {
    } finally {
      system.terminate();
    }
  }
}
