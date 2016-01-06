/**
 * 
 */
package serveur.element;

import java.util.HashMap;

import utilitaires.Calculs;

public class Mage extends Personnage{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	/**
	 * Cree un Mage avec un nom et un groupe.
	 * @param nom du personnage
	 * @param groupe d'etudiants du personnage
	 */

	public Mage(String nom, String groupe) {
		super(nom, groupe,caract());
		
	}
	
	/**
	 * Etablit les caracteristique du personnage
	 * @return caracts caracteristique du personnage
	 */
	private static HashMap<Caracteristique, Integer> caract (){
		HashMap<Caracteristique, Integer> caracts = new HashMap<Caracteristique, Integer>();
		caracts.put(Caracteristique.VIE, 70);
		caracts.put(Caracteristique.FORCE, Calculs.nombreAleatoire(10,30));
		return caracts;
	}
	
}
