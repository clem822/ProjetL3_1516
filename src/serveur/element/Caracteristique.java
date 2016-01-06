package serveur.element;

import java.util.HashMap;

/**
 * Caracteristiques possibles pour les personnages et les potions. 
 *
 */
public enum Caracteristique {

	
	/**
	 * Vitesse : Vitesse du personnage.
	 */
	VITESSE("Vitesse", "VIT", 0, 4, 1),
	
	
	/**
	 * Vie : nombre de points de vie.
	 */
	VIE("Vie", "VIE", 0, 100, 100),
	
	/**
	 * Armure : nombre de points de armure.
	 */
	ARMURE("Armure", "ARM", 0, 50, 20),
	
	/**
	 * Force : indique les degats infliges. 
	 */
	FORCE("Force", "FOR", 0, 100, 10),
		
	/**
	 * Initiative : definit l'ordre d'action des personnages lors d'un tour de jeu. 
	 */
	INITIATIVE("Initiative", "INIT", 0, 200, 50),
	
	/**
	 * Mana : nombre de points de mana.
	 */
	MANA("Mana", "MANA", 0, 100, 100),
	
	/**
	 * Invisibilité : Définit l'invisibilité. Lorsque la valeur est à 0 alors le personnage est visible.
	 */
	INVISIBILITE("Invisibilite", "INV", 0, 1, 0),
	
	
	/**
	 * Invisibilité : Définit l'invisibilité. Lorsque la valeur est à 0 alors le personnage est visible.
	 */
	DEFENSE("Defense", "DEF", 0, 50, 0);
	
	
	
	/**
	 * Nom complet.
	 */
	private final String nomComplet;
	
	/**
	 * Nom court. 
	 */
	private final String nomCourt;
	
	/**
	 * Valeur minimale.
	 */
	private final int min;
	
	/**
	 * Valeur maximale.
	 */
	private final int max;
	
	/**
	 * Valeur initiale par defaut. 
	 */
	private final int init;
	
	/**
	 * Cree une caracteristique.
	 * @param nomComplet nom complet
	 * @param nomCourt nom raccourci
	 * @param min valeur minimale
	 * @param max valeur maximale
	 * @param init valeur initiale
	 * @param prix prix d'achat
	 */
	private Caracteristique(String nomComplet, String nomCourt, int min, int max, int init) {
		this.nomComplet = nomComplet;
		this.nomCourt = nomCourt;
		this.min = min;
		this.max = max;
		this.init = init;
	}

	/**
	 * Cree un map de caracteristiques contenant toutes les caracteristiques 
	 * avec leur valeur d'initialisation par defaut. 
	 * @return map caracteristique/valeur contenant les valeurs par defaut
	 */
	public static HashMap<Caracteristique,Integer> mapCaracteristiquesDefaut() {
		HashMap<Caracteristique, Integer> caractsValues = new HashMap<Caracteristique, Integer>();
		
		//Va initialiser chaque caracï¿½ristique par la valeur initaliale.
		for (Caracteristique caract : values()) {
			caractsValues.put(caract, caract.init);
		}
		
		return caractsValues;
	}

	/**
	 * Compte le nombre total de caracteristiques.
	 * @return nombre de caracteristiques
	 */
	public static int nbCaracts() {
		return values().length;
	}
	
	public String getNomComplet() {
		return nomComplet;
	}

	public int getMin() {
		return min;
	}

	public int getMax() {
		return max;
	}

	public int getInit() {
		return init;
	}

	@Override
	public String toString() {
		return nomCourt;
	}


	
}
