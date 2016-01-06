package serveur.element;

import java.util.HashMap;
import java.util.Random;

public class Guerrier extends Personnage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Guerrier(String nom, String groupe) {
		super(nom, groupe,caract());
		
	}
	
	// caracteristiques du personnage
	private static HashMap<Caracteristique, Integer> caract (){
		HashMap<Caracteristique, Integer> caracts = new HashMap<Caracteristique, Integer>();
		caracts.put(Caracteristique.VIE, 
				100);
		caracts.put(Caracteristique.FORCE, 
				(new Random().nextInt(20)+30));
		caracts.put(Caracteristique.ARMURE, 
				30);
		return caracts;
	}
	
	

}
