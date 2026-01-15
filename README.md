5ISS > Projet Architecture de service

# System de monitoring et control d'une cave à vin

Auteurs : Julie Revelli et Anya Meetoo
Tuteur : Prof. Nawal Guermouche

## Contexte

Le but de ce projet est de concevoir une architecture de service à base de microservice.

Notre projet à nous sera la gestion et l'automatisation d'une cave à vin. Nous avons plusieurs citernes contenant du vin et à l'aide de plusieurs capteurs on vient détecter plusieurs paramètres permettant de garder le vin dans les meilleurs conditions possible.

## Architecture physique

![Architecture physique](Images/Architecture-physique.png)

Nous avons :
- Une citerne main qui contient du raisin fraîchement pressé.
- Plusieurs citernes où le liquide  va rester longtemps pour devenir du vin 

Chaque citerne aura ses capteurs et ses actionnneurs.

### Monitoring / Capteurs

- Capteur de température : Pour vérifier si la température ne dépasse pas le niveau 
- Capteur d'humidité : Afin d'assurer une humidité convenable pour bien conserver le vin 
- Capteur de niveau : Donne le niveau des citernes

### Controlling / Actionneurs 

- Si la température dépasse le seuil on actionne un refroidissement
- Si l'humidité dépasse le seuile on fait une extraction
- L'utilisateur a également la possibilité de d'actionné les vannes entre les différentes citernes

## Microservices et Bases de données associées 

![Architecture services](Images/Architecture_Services.png)
![Base de données](Images/Database.png)

## Démarrage - Exécution des Microservices

### Prérequis
- Java 21 ou supérieur
- Maven 3.6+

### Construire tous les microservices

Construire tous les microservices dans l'ordre :
```bash
mvn clean package
```

Installer tous les modules (y compris les dépendances) :
```bash
mvn clean install
```

### Mettre à jour les dépendances

Pour mettre à jour les dépendances de tous les projets :
```bash
mvn clean install -U
```

La flag `-U` force Maven à télécharger les dernières versions des dépendances depuis les dépôts.

### Utiliser le script de démarrage automatique

Pour démarrer tous les microservices automatiquement en ordre avec un seul script :
```bash
./start-services.sh
```

Ce script :
- **Construit d'abord tous les packages** avec `mvn clean package` (automatique)
- Vérifie qu'il n'y a pas d'erreurs de compilation
- Démarre tous les services dans l'ordre avec des délais entre chaque
- Exécute chaque service en arrière-plan
- Crée des fichiers logs pour chaque service dans le dossier `logs/`
- Affiche le statut et les emplacements des fichiers logs
- Arrête tous les services quand vous appuyez sur **Ctrl+C**

**Avantage :** Un seul script pour tout faire - construction + démarrage des services !

### Exécuter des microservices individuels

Exécuter un microservice spécifique :
```bash
cd Microservices/Configuration
mvn spring-boot:run
```

Remplacer `Configuration` par :
- `Decouverte` - Service de découverte
- `Temperature` - Surveillance de température
- `Refroidissement` - Contrôle de refroidissement
- `Orchestrateur` - Service d'orchestration

### Exécuter tous les services

Démarrer tous les microservices (chacun dans un terminal séparé ou un processus en arrière-plan) :
```bash
# Terminal 1 - Service Configuration
cd Microservices/Configuration && mvn spring-boot:run

# Terminal 2 - Service Découverte
cd Microservices/Decouverte && mvn spring-boot:run

# Terminal 3 - Service Température
cd Microservices/Temperature && mvn spring-boot:run

# Terminal 4 - Service Refroidissement
cd Microservices/Refroidissement && mvn spring-boot:run

# Terminal 5 - Service Orchestrateur
cd Microservices/Orchestrateur && mvn spring-boot:run
```

### Construire un module spécifique

Construire un seul microservice sans construire tous les autres :
```bash
mvn -pl Microservices/Configuration clean package
```

## Interface Utilisateur

## Screens 

## Conclusion