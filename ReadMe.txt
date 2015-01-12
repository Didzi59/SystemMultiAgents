Jérémy Bossut et Julia Leven
TP1 : Wator

Le projet Wator est une simulation de l'évolution des populations de requins et poissons dans un environnement fermé afin d'obtenir des statistiques.
L'environnement est un carré de 40 par 40 et contient au départ 50 requins et 250 poissons.

- Pour lancer le projet, il suffit de taper la commande suivante :

	java -jar wator_BOSSUT_LEVEN.jar nbTours

avec nbTours le nombre de tours maximum de la simulation (0 si on ne souhaite pas borner le nombre de tours)

- Pour générer les graphiques du suivi de l'évolution des populations, lancer dans le dossier Wator la commande suivante :
	
	gnuplot gnuplot.txt

Deux graphiques sont générés dans le dossier "graphs" :

- fileWatorEvolution représente l'évolution des populations de poissons et requins en fonction du temps
- fileWatorFishSharks représente le rapport du nombre de poissons et du nombre de requins

Un fichier nommé fileWatorAge.csv est généré dans le dossier FileCSV. Ce fichier contient les données permettant de construire une pyramide des âges tous les 10 tours.
