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
import serveur.element.Guerrier;
import serveur.element.Potion;
import utilitaires.Calculs;
import utilitaires.Constantes;

/**
 * @author guillaume
 *
 */
public class StrategieGuerrier extends StrategiePersonnage {
	


	/**
	 * Cree un personnage, la console associe et sa strategie.
	 * @param ipArene ip de communication avec l'arene
	 * @param port port de communication avec l'arene
	 * @param ipConsole ip de la console du personnage
	 * @param nom nom du personnage
	 * @param groupe groupe d'etudiants du personnage
	 * @param nbTours nombre de tours pour ce personnage (si negatif, illimite)
	 * @param position position initiale du personnage dans l'arene
	 * @param logger gestionnaire de log
	 */
	public StrategieGuerrier(String ipArene, int port, String ipConsole, 
			String nom, String groupe,
			int nbTours, Point position, LoggerProjet logger) {
		
		super(ipArene, port, ipConsole, new Guerrier(nom, groupe), nbTours, position, logger);
	}

	// TODO etablir une strategie afin d'evoluer dans l'arene de combat
	// une proposition de strategie (simple) est donnee ci-dessous
	/** 
	 * Decrit la strategie.
	 * Les methodes pour evoluer dans le jeu doivent etre les methodes RMI
	 * de Arene et de ConsolePersonnage. 
	 * @param voisins element voisins de cet element (elements qu'il voit)
	 * @throws RemoteException
	 */
	public void executeStrategie(HashMap<Integer, Point> voisins) throws RemoteException {
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
		
		
		
		Element moi = arene.elementFromRef(refRMI);
		
		if (voisins.isEmpty()) { // je n'ai pas de voisins, j'erre
			console.setPhrase("J'erre...");
			arene.deplaceRapidement(refRMI, 0);
			
		} else {
			int refCible = Calculs.chercheElementProche(position, voisins);
			int distPlusProche = Calculs.distanceChebyshev(position, arene.getPosition(refCible));

			Element elemPlusProche = arene.elementFromRef(refCible);
			
			//Caracteristique vitesse de l'adversaire
			int invAdv = elemPlusProche.getCaract(Caracteristique.INVISIBILITE); 
			
			// Si le voisin est invisible, on se dirige aleatoirement
			if (voisinEstInvisible(invAdv, elemPlusProche, arene,refRMI));
					
			else if (distPlusProche <= Constantes.DISTANCE_MIN_INTERACTION) { // si suffisamment proches
				// j'interagis directement
				if(elemPlusProche instanceof Potion) { // potion
					// ramassage
					console.setPhrase("Je ramasse une potion");
					arene.ramassePotion(refRMI, refCible);

				} else { // personnage
					//peut faire passer l'armure d'un personnage a 10 
					if (elemPlusProche.getCaract(Caracteristique.ARMURE) > 10 && moi.getCaract(Caracteristique.MANA) > 14) {
						arene.ajouteArmure(refCible, -(elemPlusProche.getCaract(Caracteristique.ARMURE)-10));
						arene.regenerationMana(refRMI,-15);
					}else {
						// Coup de Hache
						console.setPhrase("Je vais ecraser " + elemPlusProche.getNom());
						arene.lanceCoupDeHache(refRMI, refCible);
					}
				}
				
			} else { // si voisins, mais plus eloignes
				// je vais vers le plus proche
				console.setPhrase("Je vais vers mon voisin " + elemPlusProche.getNom());
				arene.deplaceRapidement(refRMI, refCible);
			}
			
		}
		if(moi.getCaract(Caracteristique.MANA) > 40 && moi.getCaract(Caracteristique.VIE) < 80){//peut se soigner
				console.setPhrase("Un plaisir!");
				arene.soin(refRMI, 40, 20);
		}
		
		if (arene.elementFromRef(refRMI).getCaract(Caracteristique.INVISIBILITE) != 0 )
			arene.incrINVISIBILITE(refRMI, -1);
	}

}
