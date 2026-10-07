# TP 1 - Injection des dépendances

## Objectif

L'objectif de ce TP est de comprendre le principe du couplage faible et les différentes méthodes d'injection des dépendances en Java et avec le Framework Spring.

## 1. Création de la couche DAO

Création de l'interface `IDao` contenant la méthode `getData()`.

```java
public interface IDao {
    double getData();
}
```

Création de l'implémentation `DaoImpl`.

```java
public class DaoImpl implements IDao {

    @Override
    public double getData() {
        return 100;
    }
}
```

## 2. Création de la couche Métier

Création de l'interface `IMetier` contenant la méthode `calcul()`.

```java
public interface IMetier {
    double calcul();
}
```

Création de `MetierImpl`.

La classe métier dépend de l'interface `IDao` et non directement de `DaoImpl`. Cela permet d'obtenir un couplage faible.

```java
public class MetierImpl implements IMetier {

    private IDao dao;

    public void setDao(IDao dao) {
        this.dao = dao;
    }

    @Override
    public double calcul() {
        double data = dao.getData();
        return data * 2;
    }
}
```

## 3. Injection des dépendances

### 3.1 Injection par instanciation statique

Dans cette méthode, les objets sont créés directement dans le programme.

```java
IDao dao = new DaoImpl();

MetierImpl metier = new MetierImpl();
metier.setDao(dao);

System.out.println("Résultat = " + metier.calcul());
```

Résultat :

```text
Résultat = 200.0
```

### 3.2 Injection par instanciation dynamique

L'instanciation dynamique permet de charger les classes à partir d'un fichier de configuration.

Le fichier `config.txt` contient :

```text
ma.ensa.dao.DaoImpl
ma.ensa.metier.MetierImpl
```

Les classes sont ensuite chargées dynamiquement avec la réflexion Java en utilisant notamment :

```java
Class.forName(...)
```

et l'injection de la dépendance est réalisée dynamiquement avec :

```java
getMethod(...)
invoke(...)
```

Cette solution permet de changer les implémentations sans modifier directement le code de la présentation.

Résultat :

```text
Résultat = 200.0
```

## 4. Injection avec Spring

### 4.1 Version XML

Les objets sont déclarés dans le fichier `applicationContext.xml`.

```xml
<bean id="dao" class="ma.ensa.dao.DaoImpl"/>

<bean id="metier" class="ma.ensa.metier.MetierImpl">
    <property name="dao" ref="dao"/>
</bean>
```

Spring crée les objets et injecte automatiquement l'objet `dao` dans l'objet `metier`.

Résultat :

```text
Résultat = 200.0
```

### 4.2 Version Annotations

Dans cette version, nous utilisons les annotations Spring.

Pour déclarer les composants :

```java
@Component("dao")
```

et :

```java
@Component("metier")
```

Pour injecter la dépendance :

```java
@Autowired
private IDao dao;
```

Le scan des composants permet à Spring de détecter les classes et de réaliser l'injection automatiquement.

Résultat :

```text
Résultat = 200.0
```

## 5. Structure du projet

```text
src/main/java
└── ma/ensa
    ├── dao
    │   ├── IDao.java
    │   └── DaoImpl.java
    │
    ├── metier
    │   ├── IMetier.java
    │   └── MetierImpl.java
    │
    └── presentation
        ├── PresentationStatique.java
        ├── PresentationDynamique.java
        ├── PresentationSpringXML.java
        └── PresentationSpringAnnotations.java

src/main/resources
├── config.txt
├── applicationContext.xml
└── applicationContext-annotations.xml
```

## Conclusion

Ce TP nous a permis de comprendre le principe du couplage faible et de mettre en œuvre plusieurs méthodes d'injection des dépendances :

- instanciation statique ;
- instanciation dynamique avec la réflexion Java ;
- injection Spring avec XML ;
- injection Spring avec les annotations.

L'utilisation de Spring permet de simplifier la création des objets et la gestion de leurs dépendances.