package client;

import java.awt.Point;
import java.rmi.RemoteException;
import java.util.HashMap;

import logger.LoggerProjet;
import serveur.IArene;
import serveur.element.Caracteristique;
import serveur.element.Element;
import serveur.element.Personnage;
import serveur.element.Teleporteur;
import serveur.element.Potion;
import utilitaires.Calculs;
import utilitaires.Constantes;

public class StrategieTeleporteur extends StrategiePersonnage {

	

	/**
	 * Cree un personnage teleporteur, la console associe et sa strategie.
	 * @param ipArene ip de communication avec l'arene
	 * @param port port de communication avec l'arene
	 * @param ipConsole ip de la console du personnage
	 * @param nom nom du personnage
	 * @param groupe groupe d'etudiants du personnage
	 * @param nbTours nombre de tours pour ce personnage (si negatif, illimite)
	 * @param position position initiale du personnage dans l'arene
	 * @param logger gestionnaire de log
	 */
	
	
	public StrategieTeleporteur(String ipArene, int port, String ipConsole, 
			String nom, String groupe, HashMap<Caracteristique, Integer> caracts,
			int nbTours, Point position, LoggerProjet logger) {
		
		super(ipArene, port, ipConsole, new Teleporteur(nom, groupe), nbTours, position, logger);
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
			arene.deplace(refRMI, 0); // La particularite de ce personnage est qu'il ne peut pas profiter du bonus des potions de vitesse meme si il peut les prendre

		} else {
			int refCible = Calculs.chercheElementProche(position, voisins);
			int distPlusProche = Calculs.distanceChebyshev(position, arene.getPosition(refCible));

			Element elemPlusProche = arene.elementFromRef(refCible);
			Element moi = arene.elementFromRef(refRMI);
			
			//Caracteristique vitesse de l'adversaire
			int invAdv = elemPlusProche.getCaract(Caracteristique.INVISIBILITE); 
			
			//Si je suis deja invisible ou que la reference est un personnage et qu'en plus son invisibilite est a 1 alors je ne l'attaque pas car je ne peux pas attaquer en �tant invisible.
			//De plus il ne peut pas ramasser les potions en etant invisible.			
			if (((invAdv == 1) && (elemPlusProche instanceof Personnage))  || (moi.getCaract(Caracteristique.INVISIBILITE) == 1 )) 
			{
				console.setPhrase("Je ne peux qu'errer.");																	
				arene.deplace(refRMI, 0);	
			} else {
			
			if (distPlusProche <= Constantes.DISTANCE_MIN_INTERACTION) { // si suffisamment proches
				// j'interagis directement
				if (elemPlusProche instanceof Potion) { // potion
					// ramassage
					console.setPhrase("Je ramasse une potion");
					arene.ramassePotion(refRMI, refCible);

				} else if (elemPlusProche.getCaract(Caracteristique.VIE) > arene.elementFromRef(refRMI).getCaract(Caracteristique.VIE)
							&& arene.elementFromRef(refRMI).getCaract(Caracteristique.MANA) > 24) {
					console.setPhrase("Il est trop fort. Je dois me teleporter ailleurs. . " + elemPlusProche.getNom());
					arene.deplaceTeleportation(refRMI, 0);
					arene.regenerationMana(refRMI, -25);
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
							if (arene.elementFromRef(refRMI).getCaract(Caracteristique.MANA) > 24) {
								console.setPhrase("Il est trop fort. Je dois me teleporter ailleurs. . " + elemPlusProche.getNom());
								arene.deplaceTeleportation(refRMI, 0);
								arene.regenerationMana(refRMI, -25);
							} else {
								console.setPhrase("Il est trop fort. Je dois fuir. " + elemPlusProche.getNom());
								arene.Fuite(refRMI, refCible);
							}
						} else {
							if (arene.elementFromRef(refRMI).getCaract(Caracteristique.MANA) > 24) {
								console.setPhrase("Je me teleporte comme un eclair " + elemPlusProche.getNom());
								arene.deplaceTeleportation(refRMI, refCible);
								arene.regenerationMana(refRMI, -25);
							} else {
								console.setPhrase("Je vais vers mon voisin " + elemPlusProche.getNom());
								arene.deplace(refRMI, refCible);
							}
						}

				} else {// il se dirige vers une potion
						console.setPhrase("Je vais vers cette potion " + elemPlusProche.getNom());
						arene.deplace(refRMI, refCible);
					}
				}
			}
			// regeneration passive de mana
			arene.regenerationMana(refRMI, 2);
		}
		
	}
}
	
		
