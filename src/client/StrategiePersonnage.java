package client;

import java.awt.Point;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Hashtable;

import serveur.IArene;
import serveur.Arene;
import serveur.element.Element;
import client.controle.Console;
import logger.LoggerProjet;
import serveur.element.Personnage;
import serveur.vuelement.VuePersonnage;

/**
 * Strategie d'un personnage. 
 */
public abstract class StrategiePersonnage {
	
	/**
	 * Console permettant d'ajouter une phrase et de recuperer le serveur 
	 * (l'arene).
	 */
	protected Console console;
	

	
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
	public StrategiePersonnage(String ipArene, int port, String ipConsole, 
			Personnage personnage, int nbTours, Point position, LoggerProjet logger) {
		
		
		logger.info("Lanceur", "Creation de la console...");
		
		try {
			console = new Console(ipArene, port, ipConsole, this, 
					personnage, nbTours, position, logger);
			logger.info("Lanceur", "Creation de la console reussie");
			
		} catch (Exception e) {
			logger.info("Personnage", "Erreur lors de la creation de la console : \n" + e.toString());
			e.printStackTrace();
		}
	}
	
	/**
	 * @throws RemoteException 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
	//Si je suis deja invisible ou que la reference est un personnage et qu'en plus son invisibilite est a 1 alors je ne l'attaque pas car je ne peux pas attaquer en �tant invisible.
	//De plus il ne peut pas ramasser les potions en etant invisible.		
	public void voisinEstInvisible(int invisibilite, Element elemPlusProche, IArene uneArene,  int refRMI) throws RemoteException {

		
		
		if ((invisibilite == 1) && (elemPlusProche instanceof Personnage)) {	
			uneArene.deplaceRapidement(refRMI, 0);
			console.setPhrase("Je ne peux qu'errer.");
		}

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
	public abstract void executeStrategie(HashMap<Integer, Point> voisins) throws RemoteException;
	
}
