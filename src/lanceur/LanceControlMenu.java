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
	Bouton b=new Bouton("Partie locale","");

	Bouton b1=new Bouton("potion","images/potion.png");
	Bouton b2=new Bouton("poison","images/poison.png");
	Bouton b3=new Bouton("Ninja","images/Ninja.png");
	Bouton b4=new Bouton("Mage","images/Mage.png");
	Bouton b5=new Bouton("Guerrier","images/Guerrier.png");
	Bouton b6=new Bouton("Invocateur","images/Invocateur.png");
	Bouton b10=new Bouton("Vampire","images/Vampire.png");

	Bouton b7=new Bouton("armes","images/epee.png");
	Bouton b8=new Bouton("Cheval","images/cheval.png");
	Bouton b9=new Bouton("bouclier","images/Bouclier.png");
	Bouton b12=new Bouton("Teleporteur","images/Teleporteur.png");
	Bouton b13=new Bouton("Invisibilite","images/invisibilite.png");
	Bouton b14=new Bouton("Match","images/Match.png");
	


	private JPanel container = new JPanel();
	
	 public LanceControlMenu(){

		    this.setTitle("Fenetre de controle");

		    this.setSize(350,350);

		    this.setLocationRelativeTo(null);

		    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);             

		    
		    
		    container.setBackground(Color.white);

		    container.setLayout(new BorderLayout());

		    container.add(pan, BorderLayout.CENTER );
		    
		
		    
		   
		    //bouton systeme
		    b.addActionListener(this);
		    //boutons potions 
		    b1.addActionListener(this);
		    b2.addActionListener(this);
		    b13.addActionListener(this);
		    //boutons personnage
		    b3.addActionListener(this);
		    b4.addActionListener(this);
		    b5.addActionListener(this);
		    b6.addActionListener(this);
		    b10.addActionListener(this);
		    b12.addActionListener(this);		  
		    //boutons objets
		    b7.addActionListener(this);
		    b8.addActionListener(this);
		    b9.addActionListener(this);
		    //bouton match
		    b14.addActionListener(this);
		    
		    
		
		    
		    GridLayout gl = new GridLayout(4, 2, 3, 3);
		    pan.setLayout(gl);
		    pan.add(b);

		    pan.add(b3);
		    pan.add(b4);
		    pan.add(b5);
		    pan.add(b6);
		    pan.add(b10);
	
		    pan.add(b12);
		    pan.add(b1);
		    pan.add(b2);
		    pan.add(b7);
		    pan.add(b8);
		    pan.add(b9);
		    pan.add(b13);
		    
		    pan.add(b14);
		
		    
		    this.setContentPane(container);               
		    this.setVisible(true);

		  }
	 public static void main(String[] args) {
		 arg=args;
		 @SuppressWarnings("unused")
		LanceControlMenu menu= new LanceControlMenu();
	 }
	 
	 
	 public void actionPerformed(ActionEvent arg0) {
		 

		 if(arg0.getSource() == b){
			  LanceArene.main(arg);
			  LanceIHM.main(arg);
		 }			 
		 if(arg0.getSource() == b1){
			  LancePotion.main(arg);
			  
		 }
		 
		 if(arg0.getSource() == b2){
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
			  LanceArme.main(arg);
			  
		  
		}
		  if(arg0.getSource() == b8){
				LanceVoiture.main(arg);  
				  
			  }
		  if(arg0.getSource() == b9){
				LanceBouclier.main(arg);
				  
			  }

		  if(arg0.getSource() == b10){
				LanceVampire.main(arg);
				  
			  }
		  if(arg0.getSource() == b12){
			  LanceTeleporteur.main(arg);  
		  }
		  if(arg0.getSource() == b13){
			  LanceInvisibilite.main(arg);  
		  }
		  if(arg0.getSource() == b14){
			  LancePotion.main(arg);
			  LancePotion.main(arg);
			  LancePotion.main(arg);
			  LancePotion.main(arg);
			  LancePotion.main(arg);
			  LancePotion.main(arg);
			  LancePotion.main(arg);
			  LanceInvisibilite.main(arg);  
			  LanceInvisibilite.main(arg);  
			  LanceInvisibilite.main(arg);  
			  LanceInvisibilite.main(arg);  
			
				
				LanceBouclier.main(arg);
				LanceBouclier.main(arg);
				LanceBouclier.main(arg);
				LanceBouclier.main(arg);
				LanceVoiture.main(arg);  
				LanceVoiture.main(arg);  
				LanceVoiture.main(arg);  
				LanceVoiture.main(arg);  
				 LanceArme.main(arg);
				 LanceArme.main(arg);
				 LanceArme.main(arg);
				 LanceArme.main(arg);
				 LanceArme.main(arg);
				 LanceArme.main(arg);
				 
				 LanceInvocateur.main(arg);
				  LanceTeleporteur.main(arg);
				  LanceVampire.main(arg);
				  LanceGuerrier.main(arg);
				  LanceMage.main(arg);
				  LanceNinja.main(arg);
			  
			  
			  
		  }
	  
	  
		   

		}
	
	 
}
