/**
 * 
 */
package serveur.interaction;

import java.rmi.RemoteException;
import java.util.logging.Level;

import serveur.Arene;
import serveur.element.Caracteristique;
import serveur.element.Personnage;
import serveur.vuelement.VuePersonnage;
import utilitaires.Constantes;


/**
 * @author guillaume
 * Représente le lancement d'une boule de feu d'un personnage vers un autre
 *
 */
public class BouleDeFeu extends Interaction<VuePersonnage> {
	
	/**
	 * Cree une interaction de BouleDeFeu.
	 * @param arene arene
	 * @param attaquant attaquant
	 * @param defenseur defenseur
	 */
	public BouleDeFeu(Arene arene, VuePersonnage attaquant, VuePersonnage defenseur) {
		super(arene, attaquant, defenseur);
	}
	
	@Override
	public void interagit() {
		try {
			Personnage pAttaquant = attaquant.getElement();
			int perteVie = 15;
			int manaAttaquant = pAttaquant.getCaract(Caracteristique.MANA);
			
			if(pAttaquant.getCaract(Caracteristique.INVISIBILITE) != 0) {
				arene.incrementeCaractElement(attaquant, Caracteristique.INVISIBILITE,-pAttaquant.getCaract(Caracteristique.INVISIBILITE));
			}
			
			// verification du mana
			if(manaAttaquant > 19) {
				// perte de mana
				arene.incrementeCaractElement(attaquant, Caracteristique.MANA, -20);

				// degats
				if (perteVie > 0) {
					arene.incrementeCaractElement(defenseur, Caracteristique.VIE, -perteVie);
					
					logs(Level.INFO, Constantes.nomRaccourciClient(attaquant) + " envoie boule de feu ("
							+ perteVie + " points de degats) a " + Constantes.nomRaccourciClient(defenseur));
				}
				
			}
			
			// initiative
			incrementeInitiative(defenseur);
			decrementeInitiative(attaquant);
			
		} catch (RemoteException e) {
			logs(Level.INFO, "\nErreur lors d'une attaque : " + e.toString());
		}
	}

	/**
	 * Incremente l'initiative du defenseur en cas de succes de l'attaque. 
	 * @param defenseur defenseur
	 * @throws RemoteException
	 */
	private void incrementeInitiative(VuePersonnage defenseur) throws RemoteException {
		arene.incrementeCaractElement(defenseur, Caracteristique.INITIATIVE, 
				Constantes.INCR_DECR_INITIATIVE_DUEL);
	}
	
	/**
	 * Decremente l'initiative de l'attaquant en cas de succes de l'attaque. 
	 * @param attaquant attaquant
	 * @throws RemoteException
	 */
	private void decrementeInitiative(VuePersonnage attaquant) throws RemoteException {
		arene.incrementeCaractElement(attaquant, Caracteristique.INITIATIVE, 
				-Constantes.INCR_DECR_INITIATIVE_DUEL);
	}

}
