/**
 * 
 */
package serveur.interaction;

import java.rmi.RemoteException;

import serveur.Arene;
import serveur.element.Caracteristique;
import serveur.vuelement.VuePersonnage;


/**
 * @author guillaume
 * Effectue une Regeneartion de Mana sur un personnage
 *
 */
public class RegenerationMana {
	
	/**
	 * Arene (serveur).
	 */
	protected Arene arene;
	
	/**
	 * Vue du personnage qui veut invoquer.
	 */
	private VuePersonnage personnage;
	
	/**
	 * RegenerationMana
	 * @param arene arene
	 * @param pCible personnage cible
	 */
	public RegenerationMana(Arene arene, VuePersonnage pCible) {
		super();
		this.arene = arene;
		this.personnage = pCible;
	}
	
	
	/**
	 * Regeneration de mana du personnage
	 * @param mana
	 */
	public void regenMana(int mana){
		
		try {
			arene.incrementeCaractElement(personnage, Caracteristique.MANA, mana);
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}
		
	}


}
