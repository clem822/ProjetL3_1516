/**
 * 
 */
package serveur.element;

import java.util.HashMap;

public class Mage extends Personnage{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int Bouclier = 0;

	/**
	 * Cree un Mage avec un nom et un groupe.
	 * @param nom du personnage
	 * @param groupe d'etudiants du personnage
	 * @param caracts caracteristiques du personnage
	 */

	public Mage(String nom, String groupe, HashMap<Caracteristique, Integer> caracts) {
		super(nom, groupe, caracts);
		// TODO Auto-generated constructor stub
	}
	
	public int getBouclier() {
		return Bouclier;
	}

	public void setBouclier(int bouclier) {
		Bouclier = bouclier;
	}

}
