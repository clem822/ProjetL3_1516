package serveur.element;

import java.util.HashMap;

import utilitaires.Calculs;

public class Guerrier extends Personnage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Cree un Guerrier avec un nom et un groupe.
	 * @param nom du personnage
	 * @param groupe d'etudiants du personnage
	 */
	public Guerrier(String nom, String groupe) {
		super(nom, groupe,caract());
		
	}
	
	/**
	 * Etablit les caracteristique du personnage
	 * @return caracts caracteristique du personnage
	 */
	private static HashMap<Caracteristique, Integer> caract (){
		HashMap<Caracteristique, Integer> caracts = new HashMap<Caracteristique, Integer>();
		caracts.put(Caracteristique.MANA, 0);
		caracts.put(Caracteristique.INITIATIVE, 80);
		caracts.put(Caracteristique.FORCE, Calculs.nombreAleatoire(35,55));
		caracts.put(Caracteristique.ARMURE, 30);
		return caracts;
	}
	
	

}
