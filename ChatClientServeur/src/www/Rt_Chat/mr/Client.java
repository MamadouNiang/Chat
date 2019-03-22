package www.Rt_Chat.mr;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Client implements Runnable 
{
    Socket SOCK;
    Scanner INPUT;
    Scanner SEND = new Scanner(System.in);
    PrintWriter OUT;
    
	public Client(Socket X) {
		this.SOCK = X;
	}
	public void run() {
		try{
			try{
				INPUT = new Scanner(SOCK.getInputStream());
				OUT = new PrintWriter(SOCK.getOutputStream());
				OUT.flush();
				CheckStream();
			}
			finally {SOCK.close();}
		}catch(Exception X){System.out.print(X);}
		
	}
	public void DISCONNECT() throws IOException {
		OUT.println(Client_Gui.nom_utilisateurs + " est deconnecté(e)");
		OUT.flush();
		SOCK.close();
		JOptionPane.showMessageDialog(null,"Deconexion reussi !");
		System.exit(0);	
	}
	private void CheckStream() {
      while(true)
      {
    	  RECEIVE();
      }
	}
	@SuppressWarnings("unchecked")
	private void RECEIVE() {
     if((INPUT.hasNext()))
     {
    	 String message = INPUT.nextLine();
    	 
    	 if(message.contains("#?!"))
    	 {
    		 String TEMP1 = message.substring(3);
    		        TEMP1 = TEMP1.replace("[","");
    		        TEMP1 = TEMP1.replace("]","");
    		        
    		        String [] CurrentUsers = TEMP1.split(", ");
    		        Client_Gui.jl_enligne.setListData(CurrentUsers);
    	 }
    	 else{Client_Gui.ta_conversation.append(message +"\n");}
     }
	}
	public void SEND(String X) {
    OUT.println(Client_Gui.nom_utilisateurs +": "+X);
    OUT.flush();
    Client_Gui.tf_message.setText("");
	}
}
