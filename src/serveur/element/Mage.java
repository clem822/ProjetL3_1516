/**
 * 
 */
package serveur.element;

import java.util.HashMap;

/**
 * @author guillaume
 *
 */
public class Mage extends Personnage{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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

}