/**
 * 
 */
package serveur.element;

import java.util.HashMap;

import utilitaires.Calculs;

public class Teleporteur extends Personnage{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Cree un teleporteur avec un nom et un groupe.
	 * @param nom du personnage
	 * @param groupe d'etudiants du personnage
	 */

	public Teleporteur(String nom, String groupe) {
		super(nom, groupe, caract());
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Etablit les caracteristique du personnage
	 * @return caracts caracteristique du personnage
	 */
	private static HashMap<Caracteristique, Integer> caract (){
		HashMap<Caracteristique, Integer> caracts = new HashMap<Caracteristique, Integer>();
		caracts.put(Caracteristique.VIE, 80);
		caracts.put(Caracteristique.MANA, 75);
		caracts.put(Caracteristique.INITIATIVE, 100);
		caracts.put(Caracteristique.FORCE, Calculs.nombreAleatoire(40,50));
		return caracts;
	}

}