package fil.car;

import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 * Récupération du couple username/password du client (/login/{username}:{password})
 * @author massiaux
 *
 */
@Path("login")
public class Login {
	
	/**
	 * Tente de se connecter au serveur avec les identifiants (username:password) reçus
	 * @param identifiants
	 * @return un message de bienvennue si la connexion est établit, un message d'erreur sinon
	 * @throws IOException
	 */
	@GET
	@Produces("text/html")
	@Path("{identifiants}")
	public String send(@PathParam("identifiants") String identifiants) throws IOException {
		Client.connexion();
		String[] split = identifiants.split(":");
		if(split.length != 2) {
			Client.getInstance().disconnect();
			return "Veuillez precisez le couple d'identifiant sous la forme {username}:{password}";
		}
		if(!Client.getInstance().login(split[0], split[1]))
			return "Mauvais identifiant : "+split[0]+"/"+split[1];
		return "Welcome "+split[0]+"/"+split[1];
	}
}
