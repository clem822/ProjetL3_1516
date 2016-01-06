package serveur.element;

import java.util.HashMap;

import utilitaires.Calculs;

/**
 * Une voiture sera une extension de la classe Potion. On utilisera l'upcasting
 */
public class Voiture extends Potion {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructeur d'une potion avec un nom, le groupe qui l'a envoyee et ses 
	 * caracteristiques (ajoutees lorsqu'un Personnage ramasse cette potion).
	 * @param nom nom de la potion
	 * @param groupe groupe d'etudiants de la potion
	 * @param caracts caracteristiques de la potion
	 */
	public Voiture(String nom, String groupe) {
		super(nom, groupe, caract());
	}
	
	
	// caracteristiques de la voiture
	private static HashMap<Caracteristique, Integer> caract (){
		HashMap<Caracteristique, Integer> caracts = new HashMap<Caracteristique, Integer>();
		//nombre aleatoire entre 2 et 4(vitesse max)
		caracts.put(Caracteristique.VITESSE, Calculs.nombreAleatoire(2,4));
		caracts.put(Caracteristique.FORCE, 0);
		caracts.put(Caracteristique.VIE, 0);
		caracts.put(Caracteristique.MANA, 0);
		caracts.put(Caracteristique.INITIATIVE, 0);
		return caracts;
	}
}
