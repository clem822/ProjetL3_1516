package lanceur;

import java.io.IOException;
import logger.LoggerProjet;
import serveur.IArene;
import serveur.element.PotionInvisibilite;
import utilitaires.Calculs;
import utilitaires.Constantes;

public class LanceInvisibilite{
	
	private static String usage = "USAGE : java " + LanceInvisibilite.class.getName() + " [ port [ ipArene ] ]";

	public static void main(String[] args) {
		String nom = "Invisible";
		
		// TODO remplacer la ligne suivante par votre numero de groupe
		String groupe = "G" + 17; 
		
		// init des arguments
		int port = Constantes.PORT_DEFAUT;
		String ipArene = Constantes.IP_DEFAUT;
		
		if (args.length > 0) {
			if (args[0].equals("--help") || args[0].equals("-h")) {
				ErreurLancement.aide(usage);
			}
			
			if (args.length > 2) {
				ErreurLancement.TROP_ARGS.erreur(usage);
			}
			
			try {
				port = Integer.parseInt(args[0]);
			} catch (NumberFormatException e) {
				ErreurLancement.PORT_NAN.erreur(usage);
			}
			
			if (args.length > 1) {
				ipArene = args[1];
			}
		}
		
		// creation du logger
		LoggerProjet logger = null;
		try {
			logger = new LoggerProjet(true, "potion_"+nom+groupe);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(ErreurLancement.suivant);
		}
		
		// lancement de la potion
		try {
			IArene arene = (IArene) java.rmi.Naming.lookup(Constantes.nomRMI(ipArene, port, "Arene"));

			logger.info("Lanceur", "Lancement de la potion sur le serveur...");
			
			
			// ajout de la potion
			arene.ajoutePotion(new PotionInvisibilite(nom, groupe), Calculs.positionAleatoireArene());
			logger.info("Lanceur", "Lancement de la potion reussi");
			
		} catch (Exception e) {
			logger.severe("Lanceur", "Erreur lancement :\n" + e.getCause());
			e.printStackTrace();
			System.exit(ErreurLancement.suivant);
		}
	}
}
