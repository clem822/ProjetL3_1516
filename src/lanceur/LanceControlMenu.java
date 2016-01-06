package lanceur;

import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
public class LanceControlMenu extends JFrame implements ActionListener{
	 static String[] arg;
	Panneau pan = new Panneau();
	Bouton b=new Bouton("Demarer partie(serveur+ihm)");
	Bouton b1=new Bouton("potion");
	Bouton b2=new Bouton("poison");
	Bouton b3=new Bouton("Ninja");
	Bouton b4=new Bouton("Mage");
	Bouton b5=new Bouton("Guerrier");
	Bouton b6=new Bouton("Invocateur");
	Bouton b7=new Bouton("armes");
	Bouton b8=new Bouton("vehicules");
	Bouton b9=new Bouton("bouclier");
	Bouton b10=new Bouton("PersonnageTeleporteur");
	private JPanel container = new JPanel();
	
	 public LanceControlMenu(){

		    this.setTitle("Fenêtre de controle");

		    this.setSize(400, 400);

		    this.setLocationRelativeTo(null);

		    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);             

		    this.setVisible(true);
		    
		    
		    container.setBackground(Color.white);

		    container.setLayout(new BorderLayout());

		    container.add(pan, BorderLayout.CENTER);
		    
		   
		    
		    b.addActionListener(this);
		    b1.addActionListener(this);
		    b2.addActionListener(this);
		    b3.addActionListener(this);
		    b4.addActionListener(this);
		    b5.addActionListener(this);
		    b6.addActionListener(this);
		    b7.addActionListener(this);
		    b8.addActionListener(this);
		    b9.addActionListener(this);
		    b10.addActionListener(this);
		    
		    

		  

		  //  pan.setLayout(new GridLayout(2,5,5,5));//5 pixel horizontal et vertical

		
				    //On prévient notre JFrame que notre JPanel sera son content pane
			pan.add(b);
		    pan.add(b1);
		    pan.add(b2);
		    pan.add(b3);
		    pan.add(b4);
		    pan.add(b5);
		    pan.add(b6);
		    pan.add(b7);
		    pan.add(b8);
		    pan.add(b9);
		    pan.add(b10);
		
		    this.setContentPane(pan);               
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

		  
		  if(arg0.getSource() == b8){
			 LanceVoiture.main(arg);  
		  }
		  
		  if(arg0.getSource() == b10){
				 LancePersonnageTeleporteur.main(arg);  
			  }
		  

		   

		}
	
	 
}
