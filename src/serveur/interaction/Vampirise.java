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
 * Interaction de vampirisme utilisé par les vampires
 *
 */
public class Vampirise extends Interaction<VuePersonnage> {
	
	/**
	 * Cree une interaction de vampirisme.
	 * @param arene arene
	 * @param attaquant attaquant
	 * @param defenseur defenseur
	 */
	public Vampirise(Arene arene, VuePersonnage attaquant, VuePersonnage defenseur) {
		super(arene, attaquant, defenseur);
	}
	
	@Override
	public void interagit() {
		try {
			Personnage pAttaquant = attaquant.getElement();
			Personnage pDefenseur = defenseur.getElement();
			int vieAttaquant = pAttaquant.getCaract(Caracteristique.VIE);
			int vieDefenseur = pDefenseur.getCaract(Caracteristique.VIE);
			int forceDefenseur = pDefenseur.getCaract(Caracteristique.FORCE);
			
			
			
			
			//si sa vie est inférieure à celle du défenseur mais sa force supérieure à celle de l'adversaire
			if ((vieAttaquant < pDefenseur.getCaract(Caracteristique.VIE))
				&& (pAttaquant.getCaract(Caracteristique.FORCE) > pDefenseur.getCaract(Caracteristique.FORCE))) {
				// il gagne 1/3 de la vie actuelle du défenseur et le défenseur perd 2/3 de la vie de l'attaquant
				arene.incrementeCaractElement(attaquant, Caracteristique.VIE, vieDefenseur / 3);
				arene.incrementeCaractElement(defenseur, Caracteristique.VIE, - vieAttaquant / 2);
				
				
				
				logs(Level.INFO, Constantes.nomRaccourciClient(attaquant) + " vole la vie("
						+ (vieDefenseur / 3) + " de vol de vie) de " + Constantes.nomRaccourciClient(defenseur)
						 + "qui perd "+(vieAttaquant / 3)+" points de vie" );
				
				
			}
			
			
			//Si il a moins de vie et moins de force, il vole 1/3 de la vie et 1/3 de la force.
			else if ((vieAttaquant < pDefenseur.getCaract(Caracteristique.VIE))
					&& (pAttaquant.getCaract(Caracteristique.FORCE) < pDefenseur.getCaract(Caracteristique.FORCE))) {
					// Le défenseur perd 1/3 de sa force et 1/3 de sa vie
				
					//Attaquant
					arene.incrementeCaractElement(attaquant, Caracteristique.VIE, vieDefenseur / 3);
					arene.incrementeCaractElement(attaquant, Caracteristique.FORCE, forceDefenseur / 3);
					
					//Defenseur
					arene.incrementeCaractElement(defenseur, Caracteristique.VIE,  - vieDefenseur / 3);
					arene.incrementeCaractElement(defenseur, Caracteristique.FORCE, - forceDefenseur / 3); 
					
					
					logs(Level.INFO, Constantes.nomRaccourciClient(attaquant) + " vole la vie et la force(vie: "+
							+ (vieDefenseur / 3) + " et force:"+ (forceDefenseur / 3) + " ) de" + Constantes.nomRaccourciClient(defenseur)
							 + "qui perd "+(vieDefenseur / 3)+" points de vie et "+(forceDefenseur / 3)+" de force" );

				}
			
			
			
			//Dans le cas où il a plus de vie il volera 1/3 de la vie et le défenseur ne perdra que 1/3
			else
			{	
				
				arene.incrementeCaractElement(attaquant, Caracteristique.VIE, vieDefenseur / 3);
				arene.incrementeCaractElement(defenseur, Caracteristique.VIE, - vieAttaquant / 3);
				
				
				
				logs(Level.INFO, Constantes.nomRaccourciClient(attaquant) + " vole la vie("
						+ (vieDefenseur / 3) + " de vol de vie) de " + Constantes.nomRaccourciClient(defenseur)
						 + "qui perd "+(vieAttaquant / 3)+" points de vie" );	
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
