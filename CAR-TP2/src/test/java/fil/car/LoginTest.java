package fil.car;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.BeforeClass;
import org.junit.Test;

import fil.car.Login;

public class LoginTest {
	
	private static Login login;
	
	@BeforeClass
	public static void initialisation() {
		login = new Login();
	}

	@Test
	public void loginTest() throws IOException {
		String identifiants = "username:pass";
		String[] split = identifiants.split(":");
		String response = login.send(identifiants);
		assertEquals("Welcome "+split[0]+"/"+split[1], response);
		fail("LoginTest failed" );
	}

}
