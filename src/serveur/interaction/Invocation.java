/**
 * 
 */
package serveur.interaction;

import java.awt.Point;
import java.io.IOException;
import java.net.InetAddress;
import java.rmi.RemoteException;

import lanceur.ErreurLancement;
import logger.LoggerProjet;
import serveur.Arene;
import serveur.ThreadInvocation;
import serveur.element.Caracteristique;
import serveur.vuelement.VuePersonnage;
import utilitaires.Calculs;
import utilitaires.Constantes;

/**
 * @author clement
 *
 */
public class Invocation {

	/**
	 * Arene (serveur).
	 */
	protected Arene arene;
	
	/**
	 * Vue du personnage qui veut invoquer.
	 */
	private VuePersonnage personnage;

	/**
	 * Nombre de sbires a� invoquer
	 */
	private int nbSbires;

	public Invocation(Arene arene, VuePersonnage personnage, int nbSbires) {
		super();
		this.arene = arene;
		this.personnage = personnage;
		this.nbSbires = nbSbires;
	}
	
	public void invoquerSbires(int refRMI)
	{
		try {
			arene.incrementeCaractElement(personnage, Caracteristique.MANA, -80);
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String nom = "Sbire De " + personnage.getElement().getNom();
		String groupe = "G" + 17;
		int nbTours = Constantes.NB_TOURS_PERSONNAGE_DEFAUT;
		int port = Constantes.PORT_DEFAUT;
		String ipArene = Constantes.IP_DEFAUT;
		
		for (int i=0 ; i<nbSbires ; ++i)
		{
			// creation du logger
			LoggerProjet logger = null;
			try {
				logger = new LoggerProjet(true, "personnage_" + nom + groupe);
			} catch (IOException e) {
				e.printStackTrace();
				System.exit(ErreurLancement.suivant);
			}
			
			// lancement du serveur
			try {
				String ipConsole = InetAddress.getLocalHost().getHostAddress();
				
				logger.info("Lanceur", "Creation du personnage...");
				
				Point position = arene.getPosition(refRMI);
				position.x += 2;
				position.y += (i*3)-6;
				position = Calculs.restreintPositionArene(position);
				//Calculs.positionAleatoireArene();//a changer pour qu'ils apparaissent pres du perso
				
				new ThreadInvocation(arene, ipArene, port, ipConsole, nom, groupe, nbTours, position, logger, personnage.getRefRMI());
				logger.info("Lanceur", "Creation du personnage reussie");
				
			} catch (Exception e) {
				logger.severe("Lanceur", "Erreur lancement :\n" + e.getCause());
				e.printStackTrace();
				System.exit(ErreurLancement.suivant);
			}
		}	
	}
}
