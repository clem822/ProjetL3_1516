/**
 * 
 */
package serveur.element;

import java.util.HashMap;

/**
 * @author clement
 *
 */
public class Sbire extends Personnage{
	int maitre;

	public Sbire(String nom, String groupe, HashMap<Caracteristique, Integer> caracts, int maitre) {
		super(nom, groupe, caracts);
		// TODO Auto-generated constructor stub
		this.maitre = maitre;
	}

	/**
	 * @return the maitre
	 */
	public int getMaitre() {
		return maitre;
	}
	
	
}
