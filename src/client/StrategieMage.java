package client;

import java.awt.Point;
import java.rmi.RemoteException;
import java.util.HashMap;

import logger.LoggerProjet;
import serveur.IArene;
import serveur.element.Caracteristique;
import serveur.element.Element;
import serveur.element.Personnage;
import utilitaires.Calculs;
import utilitaires.Constantes;

public class StrategieMage extends StrategiePersonnage {

	

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
	public StrategieMage(String ipArene, int port, String ipConsole, 
			String nom, String groupe, HashMap<Caracteristique, Integer> caracts,
			int nbTours, Point position, LoggerProjet logger) {
		
		super(ipArene, port, ipConsole, new Personnage(nom, groupe, caracts), nbTours, position, logger);
		
		
	}
	
	// TODO etablir une strategie afin d'evoluer dans l'arene de combat
	/** 
	 * Decrit la strategie du mage
	 * Les methodes pour evoluer dans le jeu doivent etre les methodes RMI
	 * de Arene et de ConsolePersonnage. 
	 * @param voisins element voisins de cet element (elements qu'il voit)
	 * @throws RemoteException
	 */
	@Override
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
			
			if (voisins.isEmpty()) { // je n'ai pas de voisins, j'erre
				console.setPhrase("J'erre...");
				arene.deplace(refRMI, 0); 
				
			} else {
				int refCible = Calculs.chercheElementProche(position, voisins);
				int distPlusProche = Calculs.distanceChebyshev(position, arene.getPosition(refCible));

				Element elemPlusProche = arene.elementFromRef(refCible);
				
					
				if(distPlusProche <= Constantes.DISTANCE_MIN_INTERACTION_DIST) { // si suffisamment proches
					// j'interagis directement
					if(elemPlusProche instanceof Personnage) { // personnage
						if(distPlusProche <= Constantes.DISTANCE_MIN_INTERACTION) { // Porte d'attaque au corps a corps
							console.setPhrase("Je fais un duel avec " + elemPlusProche.getNom());
							arene.lanceAttaque(refRMI, refCible);
						} else {
							/*if (monMage.getElement().getCaract(Caracteristique.MANA) > 19){ // Envoie d'une boule de feu a distance si mana suffisant*/
								console.setPhrase("Tu vas faire bruler  " + elemPlusProche.getNom());
								arene.lanceAttaqueBouleDeFeu(refRMI, refCible);
							/*} else { // Se dirige vers le voisin le plus proche
								console.setPhrase("Je vais vers mon voisin " + elemPlusProche.getNom());
								arene.deplace(refRMI, refCible);
							}*/
						}
						
					} else if(distPlusProche <= Constantes.DISTANCE_MIN_INTERACTION) { // potion
						// ramassage
						console.setPhrase("Je ramasse une potion");
						arene.ramassePotion(refRMI, refCible);
					} else { // si voisins, mais plus eloignes
						// je vais vers le plus proche
						console.setPhrase("Je vais vers mon voisin " + elemPlusProche.getNom());
						arene.deplace(refRMI, refCible);
					}
				} else { // si voisins, mais plus eloignes
					// je vais vers le plus proche
					console.setPhrase("Je vais vers mon voisin " + elemPlusProche.getNom());
					arene.deplace(refRMI, refCible);
				}
			}
		}

	
}
