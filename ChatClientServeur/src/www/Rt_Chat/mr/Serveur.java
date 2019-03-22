package www.Rt_Chat.mr;

import java.io.*;
import java.net.*;
import java.util.*;

public class Serveur {
	
	  public static ArrayList<Socket> ConnectionArray = new ArrayList<Socket>();
	  public static ArrayList<String> CurrentUsers = new ArrayList<String>();
	  
	  @SuppressWarnings({ "resource" })
	public static void main (String args []) throws IOException 
	  {
		  try
		  {
			  final int  port =444;
			  ServerSocket serveur = new ServerSocket( port);
			  System.out.println("  j'attend les clients... ");
			  
			  while(true)
			  {
				  Socket  socket = serveur.accept();
				  ConnectionArray.add( socket);
				  
				  System.out.println("Client connecté de : "+ socket.getLocalAddress().getHostName());
				  
				  AddUserName( socket);
				  
				  Serveur_retourne CHAT = new Serveur_retourne( socket);
				  Thread X = new Thread(CHAT);
				  X.start();
			  }
			  
		  }catch(Exception X){System.out.print(X);}
	  }
	  
	public static void AddUserName(Socket X) throws IOException
	  {
		  @SuppressWarnings("resource")
		Scanner entrer = new Scanner(X.getInputStream());
		  String  nom_utilisateur = entrer.nextLine();
		  CurrentUsers.add( nom_utilisateur);
		  
		  for(int i=1 ; i<= Serveur.ConnectionArray.size(); i++)
		  {
			  Socket socket_temporaire = (Socket) Serveur.ConnectionArray.get(i-1);
			  PrintWriter  sorti = new PrintWriter(socket_temporaire.getOutputStream());
			   sorti.println("#?!"+CurrentUsers);
			   sorti.flush();
		  }
	  }
}
