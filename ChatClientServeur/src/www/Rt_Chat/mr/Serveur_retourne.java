package www.Rt_Chat.mr;

import java.io.*;
import java.net.*;
import java.util.*;

public class Serveur_retourne implements Runnable {
	
	Socket SOCK;
	private Scanner INPUT;
	@SuppressWarnings("unused")
	private PrintWriter OUT;
	String message = "";

	public Serveur_retourne(Socket X) {
		this.SOCK = X;
	}
	
	public void CheckConnection() throws IOException
	{
		if(!SOCK.isConnected())
		{
			for(int i=1 ;i<=Serveur.ConnectionArray.size(); i++)
			{
				if(Serveur.ConnectionArray.get(i)== SOCK)
				{
					Serveur.ConnectionArray.remove(i);
				}
			}
			for(int i=1 ;i<= Serveur.ConnectionArray.size(); i++)
			{
				Socket TEMP_SOCK = (Socket) Serveur.ConnectionArray.get(i-1);
				PrintWriter TEMP_OUT = new PrintWriter(TEMP_SOCK.getOutputStream());
				TEMP_OUT.println(TEMP_SOCK.getLocalAddress().getHostName()+"Deconnecter");
				TEMP_OUT.flush();
				
				System.out.println(TEMP_SOCK.getLocalAddress().getHostName()+"Deconnecter");
			}
		}
	}
	
	public void run() {
	
	try
	{
		try
		{
			INPUT = new Scanner(SOCK.getInputStream());
			OUT = new PrintWriter(SOCK.getOutputStream());
			
			while(true)
			{
				CheckConnection();
				if(!INPUT.hasNext()){return;}
				message = INPUT.nextLine();
				System.out.println("Client a dit : "+message);
				
				for(int i = 1; i<= Serveur.ConnectionArray.size();i++) 
				{
					Socket TEMP_SOCK = (Socket) Serveur.ConnectionArray.get(i-1);
					PrintWriter TEMP_OUT = new PrintWriter(TEMP_SOCK.getOutputStream());
					TEMP_OUT.println(message);
					TEMP_OUT.flush();
					System.out.println("envoyer a : "+TEMP_SOCK.getLocalAddress().getHostName());
				}
			}
		}
		finally 
		{
		  //SOCK.close();
		}
	}catch(Exception X){System.out.print(X);}	
	}
}
