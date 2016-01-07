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
import serveur.element.Poison;
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

		
	}

	/** 
	 * Decrit la strategie. Un ninja ne peut �tre ralenti par les potions.
	 *  Sa vitesse sera toujours au moins 2.
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
		
		//si il n'a pas de voisins
		 if (voisins.isEmpty()) { // je n'ai pas de voisins, j'erre
			console.setPhrase("J'erre...");
			arene.deplaceRapidement(refRMI, 0); 
		} else {//si un element est dans son champ de vision
			int refCible = Calculs.chercheElementProche(position, voisins);
			int distPlusProche = Calculs.distanceChebyshev(position, arene.getPosition(refCible));

			Element elemPlusProche = arene.elementFromRef(refCible);
			Element moi = arene.elementFromRef(refRMI);
						
			//Caract�ristique invisibilit� de l'adversaire
			int invAdv = elemPlusProche.getCaract(Caracteristique.INVISIBILITE); 
			
			
			// Si le voisin est invisible, on se dirige al�atoirement
			if (voisinEstInvisible(invAdv, elemPlusProche, arene,refRMI));
			else if (distPlusProche <= Constantes.DISTANCE_MIN_INTERACTION) { // si suffisamment proches
				// j'interagis directement
				if (elemPlusProche instanceof Poison){//si c'est un poison je'erre
					console.setPhrase("Je n'aime pas ce poison");
					arene.deplaceRapidement(refRMI, 0);	
				}//si ce n'est pas un poison et que prendre l'�l�ment me tue pas, je le ramasse
				else if(elemPlusProche instanceof Potion && elemPlusProche.getCaract(Caracteristique.VIE)>-(moi.getCaract(Caracteristique.VIE))) { // potion
					// ramassage
					console.setPhrase("Je ramasse une potion");
					arene.ramassePotion(refRMI, refCible);

				}else { // personnage
					// duel
						console.setPhrase("Je fais un duel avec " + elemPlusProche.getNom());
						arene.lanceAttaque(refRMI, refCible);						
				}
			
			
			
			} else { // si voisins, mais plus eloignes
				
				if (elemPlusProche instanceof Personnage ){// si en fonction de l'initiative et du nombre de case et de la vitesse ,je serai le premier attaquant
					if ((distPlusProche%2 !=0 && moi.getCaract(Caracteristique.VITESSE)%2!=0)||
						(elemPlusProche.getCaract(Caracteristique.INITIATIVE )>moi.getCaract(Caracteristique.INITIATIVE))
						|| (distPlusProche%2 ==0 && elemPlusProche.getCaract(Caracteristique.INITIATIVE )<moi.getCaract(Caracteristique.INITIATIVE))){
						
						console.setPhrase("Je vais vers mon voisin " + elemPlusProche.getNom());
						arene.deplaceRapidement(refRMI, refCible);
						
					}else{
							
							if (moi.getCaract(Caracteristique.MANA )>=30){
								console.setPhrase("Je me t�l�porte, il est trop fort pour moi . " + elemPlusProche.getNom());
								arene.deplaceTeleportation(refRMI, 0);
								arene.regenerationMana(refRMI, -30);
								
							}
							else{
								console.setPhrase("Je m'echappe, il est trop fort pour moi . " + elemPlusProche.getNom());
								arene.Fuite(refRMI, refCible);
							}
						}					
				}
				else{
						if (elemPlusProche instanceof Poison ){
							console.setPhrase("Je fuis le poison " + elemPlusProche.getNom());
							arene.Fuite(refRMI, refCible);
						}
						else if(elemPlusProche instanceof Potion && elemPlusProche.getCaract(Caracteristique.VIE)>-(moi.getCaract(Caracteristique.VIE))) { // potion
							// ramassage
							console.setPhrase("J'arrive potion");
							arene.deplaceRapidement(refRMI, refCible);
						} 
						else{
							console.setPhrase("Je fuis cette potion, elle n'est pas bonne pour moi ! " + elemPlusProche.getNom());
							arene.Fuite(refRMI, refCible);
						}
						
					
				}
			
		}	
		}
		 if (arene.elementFromRef(refRMI).getCaract(Caracteristique.INVISIBILITE) != 0 )
				arene.incrINVISIBILITE(refRMI, -1);
		
	}
		
}
