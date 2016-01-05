/**
 * 
 */
package client;

import java.awt.Point;
import java.rmi.RemoteException;
import java.util.HashMap;

import logger.LoggerProjet;
import serveur.IArene;
import serveur.element.Caracteristique;
import serveur.element.Element;
import serveur.element.PersoTournois;
import serveur.element.Personnage;
import serveur.element.Potion;
import serveur.element.Sbire;
import utilitaires.Calculs;
import utilitaires.Constantes;

/**
 * @author clement
 *
 */
public class StrategieTournois extends StrategiePersonnage {

	public StrategieTournois(String ipArene, int port, String ipConsole,
			String nom, String groupe, HashMap<Caracteristique, Integer> caracts,
			int nbTours, Point position, LoggerProjet logger) {
		
		super(ipArene, port, ipConsole, new PersoTournois(nom, groupe, caracts), nbTours, position, logger);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see client.StrategiePersonnage#executeStrategie(java.util.HashMap)
	 */
	@Override
	public void executeStrategie(HashMap<Integer, Point> voisins) throws RemoteException {
		// TODO Auto-generated method stub
		// arene
		IArene arene = console.getArene();
		
		// reference RMI de l'element courant
		int refRMI = 0;
		
		// position de l'element courant
		Point position = null;
		
		try {
			refRMI = console.getRefRMI();
			position = arene.getPosition(refRMI);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		if (voisins.isEmpty()) { // je n'ai pas de voisins, j'erre
			console.setPhrase("J'erre...");
			arene.deplace(refRMI, 0); 
			
		} else {
			int refCible = Calculs.chercheElementProche(position, voisins);
			int distPlusProche = Calculs.distanceChebyshev(position, arene.getPosition(refCible));
			System.out.println(distPlusProche);

			Element elemPlusProche = arene.elementFromRef(refCible);
			
			if (distPlusProche % 2 == 0)
			{
				if(distPlusProche <= Constantes.DISTANCE_MIN_INTERACTION) { // si suffisamment proches
					// j'interagis directement
					if(elemPlusProche instanceof Potion) { // potion
						// ramassage
						console.setPhrase("Je ramasse une potion");
						arene.ramassePotion(refRMI, refCible);
	
					} else { // personnage
						// duel
						console.setPhrase("Je fais un duel avec " + elemPlusProche.getNom());
						arene.lanceAttaque(refRMI, refCible);
					}
					
				} else { // si voisins, mais plus eloignes
					// je vais vers le plus proche
					console.setPhrase("Je vais vers mon voisin " + elemPlusProche.getNom());
					arene.deplace(refRMI, refCible);
				}
			}
		}
	}
}
