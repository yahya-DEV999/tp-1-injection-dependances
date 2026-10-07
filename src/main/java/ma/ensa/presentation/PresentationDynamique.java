package ma.ensa.presentation;

import ma.ensa.dao.IDao;
import ma.ensa.metier.IMetier;
import java.io.File;
import java.lang.reflect.Method;
import java.util.Scanner;

public class PresentationDynamique {

    public static void main(String[] args) throws Exception {

        Scanner scanner =
                new Scanner(new File("src/main/resources/config.txt"));

        // Lecture du nom de la classe DAO
        String daoClassName = scanner.nextLine();

        Class<?> daoClass = Class.forName(daoClassName);

        IDao dao =
                (IDao) daoClass.getDeclaredConstructor().newInstance();


        // Lecture du nom de la classe Metier
        String metierClassName = scanner.nextLine();

        Class<?> metierClass = Class.forName(metierClassName);

        IMetier metier =
                (IMetier) metierClass.getDeclaredConstructor().newInstance();


        // Injection dynamique avec le setter
        Method setDao =
                metierClass.getMethod("setDao", IDao.class);

        setDao.invoke(metier, dao);


        System.out.println("Résultat = " + metier.calcul());

        scanner.close();
    }
}