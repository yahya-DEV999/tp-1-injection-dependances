package ma.ensa.presentation;

import ma.ensa.dao.DaoImpl;
import ma.ensa.dao.IDao;
import ma.ensa.metier.MetierImpl;

public class PresentationStatique {

    public static void main(String[] args) {

        IDao dao = new DaoImpl();

        MetierImpl metier = new MetierImpl();

        metier.setDao(dao);

        System.out.println("Résultat = " + metier.calcul());
    }
}