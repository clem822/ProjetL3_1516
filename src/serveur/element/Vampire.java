/**
 * 
 */
package serveur.element;

import java.util.HashMap;

import utilitaires.Calculs;



public class Vampire extends Personnage{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Cree un vampire avec un nom et un groupe.
	 * @param nom du personnage
	 * @param groupe d'etudiants du personnage
	 */

	public Vampire(String nom, String groupe) {
		super(nom, groupe, caract());
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Etablit les caracteristique du personnage
	 * @return caracts caracteristique du personnage
	 */
	private static HashMap<Caracteristique, Integer> caract (){
		HashMap<Caracteristique, Integer> caracts = new HashMap<Caracteristique, Integer>();
		caracts.put(Caracteristique.MANA, 0);
		caracts.put(Caracteristique.INITIATIVE, 40);
		caracts.put(Caracteristique.FORCE, Calculs.nombreAleatoire(20,30));
		caracts.put(Caracteristique.ARMURE, 30);
		return caracts;
	}

}
