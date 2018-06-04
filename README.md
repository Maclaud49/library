# library

Site communautaire pour les amoureux de library

## Pour commencer

Les instructions ci-dessous vous permettront de lancer le projet sur votre environnement local.
### Prérequis

JDK 1.8
Apache Tomcat 9
PostgreSQL 9.x.
Apache Maven
Git
JAVA IDE (IntlliJ/ Eclipse...)
Un administrateur de base de données (pgAdmin 4 par exemple)


```
http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html
https://tomcat.apache.org/download-90.cgi
https://www.postgresql.org/download/
https://maven.apache.org/download.cgi
https://git-scm.com/book/fr/v2/D%C3%A9marrage-rapide-Installation-de-Git
https://www.pgadmin.org/download/
```

### Installation

Importer le projet via votre IDE à partir du lien GitHub suivant :
https://github.com/Maclaud49/library.git

Depuis le répertoire library>bdd, utiliser le fichier
library_BDD_Master pour créer la structure de la base de données PosgreSQL

Ensuite utiliser le fichier 
library_BDD_Data pour injecter les données inititales de l'application

## Déploiement

## Built avec 

* [Maven](https://maven.apache.org/) 

Utiliser le profil : target-dev

commande Maven : maven install -P target-dev

Le war ainsi créé est alors dépoyable sur le Tomcat

## Auteur

* **Mickaël PArlow** - *Application initiale* - 

## Remerciements

* OpenClassrooms

