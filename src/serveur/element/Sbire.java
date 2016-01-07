/**
 * 
 */
package serveur.element;

import java.util.HashMap;

import utilitaires.Calculs;

/**
 * @author clement
 *
 */
public class Sbire extends Personnage{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int maitre;

	/**
	 * Cree un sbire avec un nom et un groupe.
	 * @param nom du sbire
	 * @param groupe d'etudiants du personnage
	 * @param maitre ref du maitre
	 */
	public Sbire(String nom, String groupe, int maitre) {
		super(nom, groupe, caract());
		// TODO Auto-generated constructor stub
		this.maitre = maitre;
	}

	
	/**
	 * Etablit les caracteristique du sbire
	 * @return caracts caracteristique du sbire
	 */
	private static HashMap<Caracteristique, Integer> caract (){
		HashMap<Caracteristique, Integer> caracts = new HashMap<Caracteristique, Integer>();
		caracts.put(Caracteristique.VIE, 40);
		caracts.put(Caracteristique.MANA, 0);
		caracts.put(Caracteristique.INITIATIVE, 0);
		caracts.put(Caracteristique.FORCE, Calculs.nombreAleatoire(15,25));
		caracts.put(Caracteristique.ARMURE, 10);
		return caracts;
	}
	
	/**
	 * @return the maitre
	 */
	public int getMaitre() {
		return maitre;
	}
	
	
}
