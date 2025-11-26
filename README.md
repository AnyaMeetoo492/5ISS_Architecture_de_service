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

## Interface Utilisateur

## Screens 

## Conclusion