package client;

import java.awt.Point;
import java.rmi.RemoteException;
import java.util.HashMap;

import logger.LoggerProjet;
import serveur.IArene;
import serveur.element.Caracteristique;
import serveur.element.Element;
import serveur.element.Ninja;
import serveur.element.Personnage;
import serveur.element.Potion;
import utilitaires.Calculs;
import utilitaires.Constantes;

public class StrategieNinja extends StrategiePersonnage {

	

	/**
	 * Cree un Ninja, la console associe et sa strategie.
	 * @param ipArene ip de communication avec l'arene
	 * @param port port de communication avec l'arene
	 * @param ipConsole ip de la console du personnage
	 * @param nom nom du personnage
	 * @param groupe groupe d'etudiants du personnage
	 * @param nbTours nombre de tours pour ce personnage (si negatif, illimite)
	 * @param position position initiale du personnage dans l'arene
	 * @param logger gestionnaire de log
	 */
	public StrategieNinja(String ipArene, int port, String ipConsole, 
			String nom, String groupe, int nbTours, Point position, LoggerProjet logger) {
		
		super(ipArene, port, ipConsole, new Ninja(nom, groupe), nbTours, position, logger);
		// TODO Auto-generated constructor stub
	}

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
		
		if (voisins.isEmpty()) { // je n'ai pas de voisins, j'erre
			console.setPhrase("J'erre...");
			arene.deplaceRapidement(refRMI, 0); 
			
		} else {
			int refCible = Calculs.chercheElementProche(position, voisins);
			int distPlusProche = Calculs.distanceChebyshev(position, arene.getPosition(refCible));

			Element elemPlusProche = arene.elementFromRef(refCible);
			Element moi = arene.elementFromRef(refRMI);
						
			//Caractéristique vitesse de l'adversaire
			int invAdv = elemPlusProche.getCaract(Caracteristique.INVISIBILITE); 
			
			//Si je suis déjà invisible ou que la référence est un personnage et qu'en plus son invisibilité est à 1 alors je ne l'attaque pas car je ne peux pas attaquer en étant invisible.
			//De plus il ne peut pas ramasser les potions en étant invisible.			
			if (((invAdv == 1) && (elemPlusProche instanceof Personnage))  || (moi.getCaract(Caracteristique.INVISIBILITE) == 1 )) 
			{
				console.setPhrase("Je ne peux qu'errer.");																	
				arene.deplaceRapidement(refRMI, 0);	
			}
				
					
			else if (distPlusProche <= Constantes.DISTANCE_MIN_INTERACTION) { // si suffisamment proches
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
				if (elemPlusProche instanceof Personnage ){
						if(elemPlusProche.getCaract(Caracteristique.FORCE ) > arene.elementFromRef(refRMI).getCaract(Caracteristique.VIE )){
							console.setPhrase("Je m'echappe, il est trop fort pour moi . " + elemPlusProche.getNom());
							
							arene.Fuite(refRMI, refCible);
						}
						else{
							console.setPhrase("Je vais vers mon voisin " + elemPlusProche.getNom());
							arene.deplaceRapidement(refRMI, refCible);
					}
					
				}
				else{
						console.setPhrase("Je vais vers cette potion " + elemPlusProche.getNom());
						arene.deplaceRapidement(refRMI, refCible);
				}
		}	}
	}
		
}
