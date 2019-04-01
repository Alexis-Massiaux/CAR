package com.lightbend.akka.sample;

import org.junit.Before;

import com.lightbend.akka.sample.Structure.Body;

public class MainTest {
	Body body;
	
	@Before
	public void initiliaze() {
		this.body = new Body(6);
	}
	
	
}
