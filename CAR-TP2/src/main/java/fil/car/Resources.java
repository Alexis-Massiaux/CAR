package fil.car;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.net.ftp.FTPFile;

@Path("file")
public class Resources {

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
	
	@GET
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
	
	
	@GET
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
	
	@GET
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
	
	@GET
	@Path("/upload/{var: .*}")
	@Produces("text/html")
	public String upload(@PathParam("var") String filename) {
		File file = new File("C:\\Utilisateurs\\A694672\\Desktop\\Master\\Cours\\upload.txt");
		//File file = new File(filename);
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			// TODO LOG
			return "[FAILED] "+filename+" n'existe pas !";
		}
		try {
			Client.getInstance().storeFile(filename, fis);
			return "[SUCCESS] "+filename;
		} catch (IOException e) {
			// TODO LOG
		}
		return "[FAILED] "+filename;
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
