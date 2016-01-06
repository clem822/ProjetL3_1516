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
 * Effectue un soin sur un personnage contre une quantite de mana
 *
 */
public class Soin {
	
	/**
	 * Arene (serveur).
	 */
	protected Arene arene;
	
	/**
	 * Vue du personnage qui veut invoquer.
	 */
	private VuePersonnage personnage;
	
	/**
	 * Soin
	 * @param arene arene
	 * @param pASoigner personnage a soigner
	 */
	public Soin(Arene arene, VuePersonnage pASoigner) {
		super();
		this.arene = arene;
		this.personnage = pASoigner;
	}
	
	/**
	 * Soigne le personnage contre une quantite de mana
	 * @param mana
	 * @param pv
	 */
	
	public void soigner(int mana, int pv){
		
		try {
			arene.incrementeCaractElement(personnage, Caracteristique.MANA, -mana);
			arene.incrementeCaractElement(personnage, Caracteristique.VIE, pv);
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}
		
	}


}
