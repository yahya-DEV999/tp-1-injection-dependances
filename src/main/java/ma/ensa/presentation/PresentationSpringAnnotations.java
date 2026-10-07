package ma.ensa.presentation;

import ma.ensa.metier.IMetier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PresentationSpringAnnotations {

    public static void main(String[] args) {

        ApplicationContext context =
                new ClassPathXmlApplicationContext(
                        "applicationContext-annotations.xml"
                );

        IMetier metier = context.getBean("metier", IMetier.class);

        System.out.println("Résultat = " + metier.calcul());
    }}
