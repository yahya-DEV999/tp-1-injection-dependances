package ma.ensa.presentation;

import ma.ensa.dao.IDao;
import ma.ensa.metier.IMetier;
import ma.ensa.metier.MetierImpl;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PresentationDynamique {

    public static void main(String[] args) throws Exception {

        Scanner scanner = new Scanner(new File("src/main/resources/config.txt"));

        String daoClassName = scanner.nextLine();

        Class<?> daoClass = Class.forName(daoClassName);

        IDao dao = (IDao) daoClass.getDeclaredConstructor().newInstance();

        IMetier metier = new MetierImpl(dao);

        System.out.println("Résultat = " + metier.calcul());
    }
}