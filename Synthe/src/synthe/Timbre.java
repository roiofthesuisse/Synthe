package synthe;

import java.util.Map;

/**
 * Le timbre est l'information de l'intensit� � jouer pour chaque harmonique d'une note.
 */
public class Timbre {
	Map<Integer, Double> coeffCos;
	Map<Integer, Double> coeffSin;
}
