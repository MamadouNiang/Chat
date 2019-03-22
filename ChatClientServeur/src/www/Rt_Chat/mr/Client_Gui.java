package www.Rt_Chat.mr;

import java.awt.Font;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

public class Client_Gui {
	public static Client ChatClient;
	public static String nom_utilisateurs = "anonyme";
	
	public static JFrame  fenetre_principale = new JFrame();
	public static JTextField tf_message = new JTextField(30);
	public static JTextArea ta_conversation = new JTextArea();
	@SuppressWarnings("rawtypes")
	public static JList  jl_enligne = new JList();
	//private static JButton  b_apropos = new JButton();
	private static JButton  b_connexion = new JButton();
	private static JButton b_disconnexion = new JButton();
	private static JButton b_aide = new JButton();
	private static JButton b_envoyer = new JButton();
	@SuppressWarnings("unused")
	private static JLabel l_message = new JLabel("Message");
	private static JLabel l_tete = new JLabel("**RT2_CHAT**");
	private static JLabel l_conversation = new JLabel();
	private static JLabel l_enligne = new JLabel();
	private static JLabel l_loginas = new JLabel();
	private static JLabel l_inasbox = new JLabel();
	private static JScrollPane sp_conversation = new JScrollPane();
	private static JScrollPane sp_enligne = new JScrollPane();
	
	public static JFrame fenetre_login = new JFrame();
	public static JTextField tf_nomutilisateurbox = new JTextField(20);
	private static JButton b_entrer = new JButton();
	private static JLabel l_entrerUtilisateur =new JLabel("Utilisateur : ");
	private static JPanel p_login = new JPanel();
	
	public static void main (String args [])
	{
        contruction_fenetre_principale();
        initialisations();
	}
    public static void connexion()
    {
    	try{
    		final int PORT =444;
    		final String HOST = "localhost";
    		Socket SOCK = new Socket(HOST,PORT);
    		System.out.println("vous etes connectés de : "+HOST);
    		
    		ChatClient = new Client(SOCK);
    		PrintWriter OUT = new PrintWriter(SOCK.getOutputStream());
    		OUT.println(nom_utilisateurs);
    		OUT.flush();
    		
    		Thread X = new Thread(ChatClient);
    		X.start();
    	}catch(Exception X)
    	{
    		System.out.print(X);
    		JOptionPane.showMessageDialog(null, "le serveur ne repond pas !");
    		System.exit(0);
    	}
    }
	private static void initialisations() {
		b_envoyer.setEnabled(false);
		b_disconnexion.setEnabled(false);
		 b_connexion.setEnabled(true);
	}

	private static void contruction_fenetre_login() {
		fenetre_login.setTitle("Entrer votre nom d'utilisateur ");
		fenetre_login.setSize(400,100);
		fenetre_login.setLocation(250,200);
		fenetre_login.setResizable(false);
		p_login = new JPanel();
		p_login.add(l_entrerUtilisateur);
		p_login.add(tf_nomutilisateurbox);
		p_login.add(b_entrer);
		b_entrer.setText("Valider");
		b_entrer.setBackground(new java.awt.Color(0,140,255));
		b_entrer.setForeground(new java.awt.Color(255,255,255));
		fenetre_login.add(p_login);
		fenetre_login.setIconImage(new ImageIcon("ressources/Add User Group Woman Man_96px.png").getImage());

		
		Login_Action ();
		fenetre_login.setVisible(true);
	}
	private static void contruction_fenetre_principale() {
		 fenetre_principale.setTitle(nom_utilisateurs);
		 fenetre_principale.setSize(950,800);
		 fenetre_principale.setLocation(220,180);
		 fenetre_principale.setResizable(false);
		 fenetre_principale.setDefaultCloseOperation(3);
		configuaration_fenetre_principale();
		fenetre_principale_Action();
		 fenetre_principale.setVisible(true);
		 fenetre_principale.setIconImage(new ImageIcon("ressources/User Group Man Woman_96px_1.png").getImage());
	}
	private static void configuaration_fenetre_principale() {
		fenetre_principale.setBackground(new java.awt.Color(255,255,255));
		fenetre_principale.setSize(500,350);
		fenetre_principale.getContentPane().setLayout(null);
		
		b_envoyer.setBackground(new java.awt.Color(0,140,255));
		b_envoyer.setForeground(new java.awt.Color(255,255,255));
		b_envoyer.setText("Envoyer");
		 fenetre_principale.getContentPane().add(b_envoyer);
		b_envoyer.setBounds(250,80,81,25);
		
		b_disconnexion.setBackground(new java.awt.Color(0,140,255));
		b_disconnexion.setForeground(new java.awt.Color(255,255,255));
		b_disconnexion.setText("Deconnexion");
		 fenetre_principale.getContentPane().add(b_disconnexion);
		b_disconnexion.setBounds(10,80,110,25);
		
		
		 b_connexion.setBackground(new java.awt.Color(0,140,255));
		 b_connexion.setForeground(new java.awt.Color(255,255,255));
		 b_connexion.setText("Connexion");
		 fenetre_principale.getContentPane().add( b_connexion);
		 b_connexion.setBounds(130,80,110,25);
		
		b_aide.setBackground(new java.awt.Color(0,140,255));
		b_aide.setForeground(new java.awt.Color(255,255,255));
		b_aide.setText("Aide");
		 fenetre_principale.getContentPane().add(b_aide);
		b_aide.setBounds(390,80,70,25);
				
		tf_message.setForeground(new java.awt.Color(0,140,255));
		tf_message.requestFocus();
		 fenetre_principale.getContentPane().add(tf_message);
		tf_message.setBounds(10,39,320,30);
		
		l_conversation.setHorizontalAlignment(SwingConstants.CENTER);
		l_conversation.setText("Message");
		 fenetre_principale.getContentPane().add(l_conversation);
		l_conversation.setBounds(100,107,140,16);
		
		ta_conversation.setColumns(20);
		ta_conversation.setFont(new java.awt.Font("tahoma",0,12));
		ta_conversation.setForeground(new java.awt.Color(0,140,255));
		ta_conversation.setLineWrap(true);
		ta_conversation.setRows(5);
		ta_conversation.setEditable(false);

		sp_conversation.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		sp_conversation.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		sp_conversation.setViewportView(ta_conversation);
		 fenetre_principale.getContentPane().add(sp_conversation);
		sp_conversation.setBounds(10,130,330,180);
		
		l_enligne.setHorizontalAlignment(SwingConstants.CENTER);
		l_enligne.setText("Utilisateur en ligne");
		l_enligne.setToolTipText("");
		 fenetre_principale.getContentPane().add(l_enligne);
		l_enligne.setBounds(350,107,130,16);
		
		sp_enligne.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		sp_enligne.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		sp_enligne.setViewportView( jl_enligne);
		 fenetre_principale.getContentPane().add(sp_enligne);
		sp_enligne.setBounds(348,130,130,180);

		l_loginas.setText("Connecté en tant que ");
		 fenetre_principale.getContentPane().add(l_loginas);
		l_loginas.setBounds(348,26,140,15);
		
		l_tete.setFont(new java.awt.Font("Times New Roman", Font.BOLD,22));
		l_tete.setText("* ISCAE_RT.2_CHAT  *");
		 fenetre_principale.getContentPane().add(l_tete);
		l_tete.setBounds(60,13,340,15);
		
		l_inasbox.setHorizontalAlignment(SwingConstants.CENTER);
		l_inasbox.setFont(new java.awt.Font("tahoma",0,12));
		l_inasbox.setForeground(new java.awt.Color(0,140,255));
		l_inasbox.setBorder(BorderFactory.createLineBorder(new java.awt.Color(0,140,255)));
		 fenetre_principale.getContentPane().add(l_inasbox);
		l_inasbox.setBounds(340,45,150,20);
		
	}
	
  public static void Login_Action() 
  {
	   b_entrer.addActionListener(new java.awt.event.ActionListener()
			{
			public void actionPerformed(java.awt.event.ActionEvent evt) 
			{	
				ACTION_B_ENTER();
				
			}
			});
 }
	
	protected static void ACTION_B_ENTER() 
{
	if(!tf_nomutilisateurbox.getText().equals(""))
	{
		nom_utilisateurs = tf_nomutilisateurbox.getText().trim();
		l_inasbox.setText(nom_utilisateurs);
		Serveur.CurrentUsers.add(nom_utilisateurs);
		 fenetre_principale.setTitle(nom_utilisateurs);
		fenetre_login.setVisible(false);
		b_envoyer.setEnabled(true);
		b_disconnexion.setEnabled(true);
		 b_connexion.setEnabled(false);
		connexion();
	}
	else{JOptionPane.showMessageDialog(null, "veillez entrer un nom utilisateur !!");}
}
private static void fenetre_principale_Action() 
{
 b_envoyer.addActionListener(new java.awt.event.ActionListener(){public void actionPerformed(java.awt.event.ActionEvent evt) {ACTION_B_SEND();}});
 b_disconnexion.addActionListener(new java.awt.event.ActionListener(){public void actionPerformed(java.awt.event.ActionEvent evt) {ACTION_B_DISCONNECT();}});
  b_connexion.addActionListener(new java.awt.event.ActionListener(){public void actionPerformed(java.awt.event.ActionEvent evt) {contruction_fenetre_login();}});
 b_aide.addActionListener(new java.awt.event.ActionListener(){public void actionPerformed(java.awt.event.ActionEvent evt) {ACTION_B_HELP();}});
  //b_apropos.addActionListener(new java.awt.event.ActionListener(){public void actionPerformed(java.awt.event.ActionEvent evt) {ACTION_B_ABOUT();}});
}
protected static void ACTION_B_SEND() {
	if(!tf_message.getText().equals(""))
	{
		ChatClient.SEND(tf_message.getText());
		tf_message.requestFocus();
	}
}
protected static void ACTION_B_DISCONNECT() {	
	try{
		ChatClient.DISCONNECT();
	}catch(Exception Y){Y.printStackTrace();}
}
protected static void ACTION_B_HELP() {
	JOptionPane.showMessageDialog(null, "Reaseaux informatique et telecom 2\n **Projet Java Orienté Objet**\n Mamadou Niang ");
}
	protected static void ACTION_B_ABOUT() {	
}
}
