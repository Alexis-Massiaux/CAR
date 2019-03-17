package fil.car;

import java.io.IOException;
import java.io.InputStream;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
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

	@POST
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
	
	@DELETE
	@Path("/delete/{filename}")
	@Produces("text/html")
	public String deleteFile(@PathParam("filename") String filename) {
		try {
			if(isDirectory(filename))
				Client.getInstance().removeDirectory(filename);
			else
				Client.getInstance().deleteFile(filename);
			return "[SUCCESS] "+filename+" has been deleted ! ";
		} catch (IOException e) {
			return "[FAILED] "+filename;
			//TODO LOG
		}
	}
	
	
	@POST
	@Path("/rename/{var: .*}")
	@Produces("text/html")
	public String renameFile(@PathParam("var") String rename) {
		String[] split = rename.split("-");
		String from = split[0], to = split[1];
		try {
			Client.getInstance().rename(from, to);
			return "[SUCCESS] "+from+" has been rename to "+to+" ! ";
		} catch (IOException e) {
			//TODO LOG
		}
		return "[FAILED] "+from+" - "+to;
	}
	
	@POST
	@Path("/mkdir/{directory}")
	@Produces("text/html")
	public String createDirectory(@PathParam("directory") String directory) {
		try {
			if(Client.getInstance().makeDirectory(directory)) 
				return "[SUCCESS] "+directory+" has been create ! ";
		} catch (IOException e) {
			// TODO LOG
		}
		return "[FAILED] "+directory;
	}

	public boolean isDirectory(String name) {
		try {
			FTPFile[] files = Client.getInstance().listFiles();
			for(int i=0; i<files.length; i++)
				if(files[i].getName().equals(name) && files[i].isDirectory() )
					return true;
		} catch (IOException e) {
			// TODO LOG
			return false;
		}
		return false;
	}

	public String formatList(FTPFile[] list) {
		String res = "";

		for(int i=0; i<list.length; i++) {
			res+="<p>"+list[i]+"</p>";
		}
		return res;
	}

}
