/**
 * 
 */
package serveur.interaction;

import java.awt.Point;
import java.rmi.RemoteException;
import java.util.Random;
import java.util.logging.Level;

import serveur.Arene;
import serveur.element.Caracteristique;
import serveur.element.Personnage;
import serveur.vuelement.VuePersonnage;
import utilitaires.Constantes;


/**
 * @author guillaume
 * Represente un coup de hache de la part d'un personnage sur un autre
 *
 */
public class CoupDeHache extends Duel {
	
	/**
	 * Cree une interaction de CoupDeHache.
	 * @param arene arene
	 * @param attaquant attaquant
	 * @param defenseur defenseur
	 */
	public CoupDeHache(Arene arene, VuePersonnage attaquant, VuePersonnage defenseur) {
		super(arene, attaquant, defenseur);
	}
	
	@Override
	public void interagit() {
		try {
			Personnage pAttaquant = attaquant.getElement();
			int forceAttaquant = pAttaquant.getCaract(Caracteristique.FORCE);
			
			if((new Random().nextInt(5)+1) > 4) forceAttaquant *= 2;
			int perteVie = forceAttaquant;
		
			Point positionEjection = positionEjection(defenseur.getPosition(), attaquant.getPosition(), forceAttaquant);

			// ejection du defenseur
			defenseur.setPosition(positionEjection);
			
			if(pAttaquant.getCaract(Caracteristique.INVISIBILITE) != 0) {
				arene.incrementeCaractElement(attaquant, Caracteristique.INVISIBILITE,-pAttaquant.getCaract(Caracteristique.INVISIBILITE));
			}

			// degats
			if (perteVie > 0) {
				arene.incrementeCaractElement(attaquant, Caracteristique.MANA, 10);
				arene.incrementeCaractElement(defenseur, Caracteristique.VIE, -perteVie);
				
				logs(Level.INFO, Constantes.nomRaccourciClient(attaquant) + " colle une beigne ("
						+ perteVie + " points de degats) a " + Constantes.nomRaccourciClient(defenseur));
			}
			
			// initiative
			incrementeInitiative(defenseur);
			decrementeInitiative(attaquant);
			
		} catch (RemoteException e) {
			logs(Level.INFO, "\nErreur lors d'une attaque : " + e.toString());
		}
	}


}
