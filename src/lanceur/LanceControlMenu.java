package lanceur;

import javax.swing.JPanel;

import interfacegraphique.controle.Bouton;
import interfacegraphique.controle.Panneau;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
public class LanceControlMenu extends JFrame implements ActionListener{
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static String[] arg;
	Panneau pan = new Panneau();
	Bouton b=new Bouton("Partie local","");
	Bouton b0=new Bouton("Partie reseau","");
	Bouton b11=new Bouton("Perso Tournois","images/Invocateur.png");
	Bouton b1=new Bouton("potion","images/potion.png");
	Bouton b2=new Bouton("poison","images/poison.png");
	Bouton b3=new Bouton("Ninja","images/Ninja.png");
	Bouton b4=new Bouton("Mage","images/Mage.png");
	Bouton b5=new Bouton("Guerrier","images/Guerrier.png");
	Bouton b6=new Bouton("Invocateur","images/Invocateur.png");
	Bouton b10=new Bouton("Vampire","images/Vampire.png");
	Bouton b7=new Bouton("armes","");
	Bouton b8=new Bouton("Voiture","");
	Bouton b9=new Bouton("bouclier","");
	Bouton b12=new Bouton("Teleporteur","");
	private JPanel container = new JPanel();
/*	private JPanel syst = new JPanel();
	private JPanel perso = new JPanel();
	private JPanel item = new JPanel();*/
	
	 public LanceControlMenu(){

		    this.setTitle("Fenêtre de controle");

		    this.setSize(350,350);

		    this.setLocationRelativeTo(null);

		    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);             

		    
		    
		    container.setBackground(Color.white);

		    container.setLayout(new BorderLayout());

		    container.add(pan, BorderLayout.CENTER );
		    
		 /*   pan.add(syst, BorderLayout.NORTH );
		    pan.add(perso, BorderLayout.CENTER );
		    pan.add(item, BorderLayout.SOUTH );*/
		    
		   
		    
		    b.addActionListener(this);
		    b0.addActionListener(this);
		    b11.addActionListener(this);
		    b1.addActionListener(this);
		    b2.addActionListener(this);
		    b3.addActionListener(this);
		    b4.addActionListener(this);
		    b5.addActionListener(this);
		    b6.addActionListener(this);
		    b10.addActionListener(this);
		    b7.addActionListener(this);
		    b8.addActionListener(this);
		    b9.addActionListener(this);
		   
		    b12.addActionListener(this);
		    
		    GridLayout gl = new GridLayout(4, 2, 3, 3);
		    pan.setLayout(gl);
		    pan.add(b);
		    pan.add(b0);
		    pan.add(b3);
		    pan.add(b4);
		    pan.add(b5);
		    pan.add(b6);
		    pan.add(b10);
		    pan.add(b11);
		    pan.add(b12);
		    pan.add(b1);
		    pan.add(b2);
		    pan.add(b7);
		    pan.add(b8);
		    pan.add(b9);
		
		    
		    this.setContentPane(container);               
		    this.setVisible(true);

		  }
	 public static void main(String[] args) {
		 arg=args;
		 LanceControlMenu menu= new LanceControlMenu();
	 }
	 
	 
	 public void actionPerformed(ActionEvent arg0) {

		  if(arg0.getSource() == b){
			  LanceArene.main(arg);
			  LanceIHM.main(arg);
			 
		  }

		  if(arg0.getSource() == b1){
			  LancePotion.main(arg);
			  
		  }  if(arg0.getSource() == b2){
			 LancePoison.main(arg);
		  }

		  if(arg0.getSource() == b3){
			  LanceNinja.main(arg);
			  
		  }  if(arg0.getSource() == b4){
			 LanceMage.main(arg);
		  }

		  if(arg0.getSource() == b5){

			  LanceGuerrier.main(arg);
			  
		  }  if(arg0.getSource() == b6){
			  LanceInvocateur.main(arg);
		  }

		  if(arg0.getSource() == b7){
			//Lancearmes.main(arg);
			  
		  
		}
		  if(arg0.getSource() == b8){
				LanceVoiture.main(arg);  
				  
			  }
		  if(arg0.getSource() == b9){
				//Lancearmes.main(arg);
				  
			  }

		  if(arg0.getSource() == b10){
				LanceVampire.main(arg);
				  
			  }

		  if(arg0.getSource() == b11){
				//Lancearmes.main(arg);
				  
			  }
			   if(arg0.getSource() == b12){
				 LancePersonnageTeleporteur.main(arg);  
			  }
		  
		   

		}
	
	 
}
