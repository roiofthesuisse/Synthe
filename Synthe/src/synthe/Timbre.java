package synthe;

import java.util.Map;

/**
 * Le timbre est l'information de l'intensité à jouer pour chaque harmonique d'une note.
 */
public class Timbre {
	Map<Integer, Double> coeffCos;
	Map<Integer, Double> coeffSin;
	
	public int[] produireSon(int dureeSon, double frequence) {
		// Reconstitution du sugnal à partir des coefficients de Fourier
		double[] resultat = new double[dureeSon];
		for (int i=0; i<dureeSon; i++) {
			resultat[i] = 0;
			for (Integer k : coeffCos.keySet()) {
				resultat[i] += coeffCos.get(k) * Math.cos(k * i * 2 * Math.PI * frequence);
				resultat[i] += coeffSin.get(k) * Math.sin(k * i * 2 * Math.PI * frequence);
			}
		}
		
		// Conversion double vers int
		int[] resultatInt = new int[dureeSon];
		for (int i=0; i<dureeSon; i++) {
			resultatInt[i] = (int) resultat[i];
		}
		return resultatInt;
	}
}
