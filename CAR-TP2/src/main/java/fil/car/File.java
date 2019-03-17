package fil.car;

import java.io.IOException;
import java.io.InputStream;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.MediaType;

import org.apache.commons.net.ftp.FTPFile;

@Path("file")
public class File {

	@GET
	@Path("/list/{var: .*}")
	@Produces("text/html")
	public String getFileList(@PathParam("var") String directory) throws IOException {
		Client.connexion();
		System.out.println(Client.getInstance().printWorkingDirectory());
		//String path = deletePath(directory);
		//System.out.println("compile3");
		//System.out.println(directory);
		try {
			int cwd = 0; 
			//int cwd = Client.getInstance().cwd(directory);
			if(cwd == 550)
				return Client.getInstance().printWorkingDirectory()+directory+" : Chemin introuvable ! ";
			FTPFile[] fileList = Client.getInstance().listFiles();
			return formatList(fileList);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}

	@GET
	@Path("/download/{filename}")
	@Produces("application/octet-stream")
	public Response getFile(@PathParam("filename") String filename) {
		try {
			InputStream in = Client.getInstance().retrieveFileStream(filename);
			Response response = Response.ok(in, MediaType.APPLICATION_OCTET_STREAM).build();
			return response;
		} catch (IOException e) {
			return null;
			//TODO LOG
		}
	}

	/*
	public String deletePath(String path) throws IOException {
		String pwd = Client.getInstance().printWorkingDirectory();
		int i = 0;
		while(path.charAt(0) == pwd.charAt(i))
			i++;
		return path.substring(i, path.length());

	}
	 */
	public String formatList(FTPFile[] list) {
		String res = "";

		for(int i=0; i<list.length; i++) {
			res+="<p>"+list[i]+"</p>";
		}
		return res;
	}

}
