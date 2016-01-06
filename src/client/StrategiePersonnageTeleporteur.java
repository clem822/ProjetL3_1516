package client;

import java.awt.Point;
import java.rmi.RemoteException;
import java.util.HashMap;

import logger.LoggerProjet;
import serveur.IArene;
import serveur.element.Caracteristique;
import serveur.element.Element;
import serveur.element.Personnage;
import serveur.element.PersonnageTeleporteur;
import serveur.element.Potion;
import serveur.element.Voiture;
import utilitaires.Calculs;
import utilitaires.Constantes;

public class StrategiePersonnageTeleporteur extends StrategiePersonnage {

	

	/**
	 * Cree un personnage t�l�port, la console associe et sa strategie.
	 * @param ipArene ip de communication avec l'arene
	 * @param port port de communication avec l'arene
	 * @param ipConsole ip de la console du personnage
	 * @param nom nom du personnage
	 * @param groupe groupe d'etudiants du personnage
	 * @param nbTours nombre de tours pour ce personnage (si negatif, illimite)
	 * @param position position initiale du personnage dans l'arene
	 * @param logger gestionnaire de log
	 */
	public StrategiePersonnageTeleporteur(String ipArene, int port, String ipConsole, 
			String nom, String groupe, HashMap<Caracteristique, Integer> caracts,
			int nbTours, Point position, LoggerProjet logger) {
		
		super(ipArene, port, ipConsole, new PersonnageTeleporteur(nom, groupe, caracts), nbTours, position, logger);
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
			arene.deplace(refRMI, 0); // La particularit� de ce personnage est qu'il ne peut pas profiter du bonus des potions de vitesse m�me si il peut les prendre

		} else {
			int refCible = Calculs.chercheElementProche(position, voisins);
			int distPlusProche = Calculs.distanceChebyshev(position, arene.getPosition(refCible));

			Element elemPlusProche = arene.elementFromRef(refCible);
			Element moi = arene.elementFromRef(refRMI);
			
			//Caract�ristique vitesse de l'adversaire
			int invAdv = elemPlusProche.getCaract(Caracteristique.INVISIBILITE); 
			
			//Si je suis d�j� invisible ou que la r�f�rence est un personnage et qu'en plus son invisibilit� est � 1 alors je ne l'attaque pas car je ne peux pas attaquer en �tant invisible.
			//De plus il ne peut pas ramasser les potions en �tant invisible.			
			if (((invAdv == 1) && (elemPlusProche instanceof Personnage))  || (moi.getCaract(Caracteristique.INVISIBILITE) == 1 )) 
			{
				console.setPhrase("Je ne peux qu'errer.");																	
				arene.deplaceRapidement(refRMI, 0);	
			}
			
			else if (distPlusProche <= Constantes.DISTANCE_MIN_INTERACTION) { // si suffisamment proches
				// j'interagis directement
				if (elemPlusProche instanceof Potion) { // potion
					// ramassage
					console.setPhrase("Je ramasse une potion");
					arene.ramassePotion(refRMI, refCible);

				} else { // personnage
					// duel
					console.setPhrase("Je fais un duel avec " + elemPlusProche.getNom());
					arene.lanceAttaque(refRMI, refCible);
				}

			} else { // si voisins, mais plus eloignes
				
				
				// je vais vers le plus proche personnage
				if (elemPlusProche instanceof Personnage) {
		
						// Il se base selon la vie de l'adversaie et non pas sa
						// force.
						if (elemPlusProche.getCaract(Caracteristique.VIE) > arene.elementFromRef(refRMI).getCaract(Caracteristique.VIE)) {
							console.setPhrase("Il est trop fort. Je dois me t�l�porter ailleurs. . " + elemPlusProche.getNom());
							arene.deplaceTeleportation(refRMI, 0);
						} else {
							console.setPhrase("Je me t�l�porte comme un �clair " + elemPlusProche.getNom());
							arene.deplaceTeleportation(refRMI, refCible);
						}

				} else {// il se dirige vers une potion
					
					//Si je suis d�j� invisible, je ne vais pas vers la potion
					if (moi.getCaract(Caracteristique.INVISIBILITE) == 1)
					{
						console.setPhrase("Je ne peux qu'errer.");
						arene.deplace(refRMI, 0);
					}
					//sinon j'y vais
					else {
						console.setPhrase("Je vais vers cette potion " + elemPlusProche.getNom());
						arene.deplace(refRMI, refCible);
					}
				}
			}
		}
	}
}
	
		
