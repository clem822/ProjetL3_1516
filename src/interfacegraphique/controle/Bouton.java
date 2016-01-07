package interfacegraphique.controle;

import java.awt.Color;

import java.awt.GradientPaint;

import java.awt.Graphics;

import java.awt.Graphics2D;

import java.awt.Image;

import java.awt.event.MouseEvent;

import java.awt.event.MouseListener;

import java.io.File;

import java.io.IOException; 

import javax.imageio.ImageIO;

import javax.swing.JButton;

public class Bouton extends JButton implements MouseListener{

	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	  private Image img;

	

	  public Bouton(String str,String image){

	    super(str);

	    this.name = str;
	    if(image.length()!=0){
	    try {

	        img = ImageIO.read(new File(image));

	      } catch (IOException e) {

	        e.printStackTrace();

	      }
	    }

	    //Grâce à cette instruction, notre objet va s'écouter

	    //Dès qu'un événement de la souris sera intercepté, il en sera averti

	    this.addMouseListener(this);

	  }

	  
	  public void paintComponent(Graphics g){

		    Graphics2D g2d = (Graphics2D)g;

		    GradientPaint gp = new GradientPaint(0, 0, Color.white, 0, 20, Color.white, true);

		    g2d.setPaint(gp);
		   
		    g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
		    g2d.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
		    g2d.setColor(Color.black);
		    g2d.fillRect(0, this.getHeight()-13, this.getWidth(), this.getHeight());
		    g2d.setColor(Color.white);
		    g2d.drawString(this.name, 2, this.getHeight()-2);
		    
		    if (name=="Partie locale" || name=="Partie reseau"){
		    	  g2d.setColor(Color.black);
		    	 g2d.drawString("SERVEUR", this.getWidth() / 2 -25 , (this.getHeight() / 2) -10);
		    	 g2d.drawString("ET", this.getWidth() / 2 - (this.getWidth() / 2 /4), (this.getHeight() / 2) );
		    	 g2d.drawString("IHM", this.getWidth() / 2 - (this.getWidth() / 2 /3), (this.getHeight() / 2) +10);
		    }

		  }


	  //Méthode appelée lors du clic de souris

	  public void mouseClicked(MouseEvent event) { 
		 
	  }


	  //Méthode appelée lors du survol de la souris

	  public void mouseEntered(MouseEvent event) { }


	  //Méthode appelée lorsque la souris sort de la zone du bouton

	  public void mouseExited(MouseEvent event) { }


	  //Méthode appelée lorsque l'on presse le bouton gauche de la souris

	  public void mousePressed(MouseEvent event) { 
		 
	  }


	  //Méthode appelée lorsque l'on relâche le clic de souris

	  public void mouseReleased(MouseEvent event) { }



	}