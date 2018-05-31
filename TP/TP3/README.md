# Data Mining sur les données Hearthstone

## Introduction

Pour plus d'informations sur ce repository, veuillez prendre connaisance du _PDF_ dans le directory courant.

On utilise l'algorithme LCM fourni par l'application SPMF disponible [ici](http://www.philippe-fournier-viger.com/spmf/).

#
#

## Getting Started

### Prérequis:

* [SPMF](http://www.philippe-fournier-viger.com/spmf/), Outil pour faire tourner des algorithmes de PatternMining.
* [Un environnement Java](https://java.com/fr/download/), Pour faire tourner SPMF.
* [Et Python](https://www.python.org/downloads/), Pour faire tourner notre parser.

### How to-

L'opération se découpe en 3 parties :

1. Pre-parsing datas.
Parce que les données fournies ne sont pas disposées à être processées par SPMF, il nous faut les rendre fonctionnelles.

2. Passage dans SPMF.
Une fois les données parsées, vous vous retrouverez avec un environnement avec tout ce qu'il faut pour faire tourner SPMF.

3. Post-parsing results.
Parce que les résultats de SPMF sont inutilisable sans quelques modifications, on les rend compréhensibles pour un utilisateur.

#### Pre-Parsing datas

Avant de donner les valeurs à SPMF on doit parser les valeurs. Pour cela entrer la commande:

```Shell
$ python parser.py "pre"
```

Ceci créera un dossier _/tmp_ ainsi que deux fichiers _LCMinput_ et _LCMoutput_ dans le directory courant.
Comme leurs noms l'indique, les deux fichiers seront là pour SPMF, et le dossier /tmp pour la troisième étape: **Post-parsing results**

#### Passage dans SPMF

Lancer SPMF avec:

```Shell
$ java -jar spmf/spmf.jar
```

Un UI apparaitra. Choisissez alors l'Algorithme _LCM_, remplissez les champs respectifs avec _LCMinput_ et _LCMoutput_ files

Choisissez votre Minsup et Run.

#### Post-parsing results

Vous avez désormais dans votre _LCMoutput_ le résultat de SPMF.
Lancer :

```Shell
$ python parser.py "post"
```

Cela reprenderas le fichier _LCMoutput_ le parseras pour rendre un fichier _Results_ compréhensible pour l'utilisateur. Notez que cette étape nécessite le dossier _/tmp_ inchangé.
Dès lors, tous les fichiers désormais inutiles seront supprimés.

#
#

## But

Le but de ce projet est, à partir de données de jeux hearthstone, d'observer les cartes souvent jouées ensemble dans un deck de cartes. C'est pourquoi nous utilisons _LCM_, un algorithme de Pattern-mining, pour en ressortir les itemsets fréquents fermés étant donné un seuil min (ici _Minsup_ choisi pour _LCM_)

L'avantage d'observer les itemsets dans le cas d'hearthstone est qu'il nous permet de faire ressortir les cartes souvent jouées ensemble dans un deck parmis les joueurs. Cela nous donne la possibilité de discerner les combos possibles qui sont puissants dans le cadre du jeu. Et qui sont par conséquent souvent joués. Pour Blizzard, ce serait utile pour distinguer les cartes nécessitant d'être nerf, et pour les joueurs quelles cartes sont surpuissantes. Considérant qu'on possède une large base de données de parties.

### Fonctionnement

Notre parser se découpe donc en 2 parties :

1.Pre-Parsing datas :

  * Il prend _data/all.txt_ qui est une concaténation de toutes les données fournies. 
  * Il écrit ensuite dans _LCMinput_ toutes les cartes jouées par un joueur lors d'une partie sur une même ligne.
  * Pour ensuite convertir tous les noms de carte en un entier les identifiant. Chaque entier correspond au numéro de ligne dans le nouveau fichier _tmp/mapCard_

2.Post-parsing results :

  * Récupérant les résultats mis dans _LCMoutput_ et _tmp/mapCard_ il réattitre à chaque entier d'un itemset son nom originel de carte.
  * Complète un fichier finale _Results_ avec les cartes de chaque itemset et combien de fois ils sont utilisés.
  
### Exemple de _Results_

Voilà par exemple le résultat pour les données fournies de bases et un Minsup pour LCM a 0.25:

```
Used 123 times: 
{Fireblast}

Used 109 times: 
{LesserHeal}

Used 74 times: 
{LifeTap}

Used 67 times: 
{DaggerMastery}

Used 353 times: 
{TheCoin}

Used 239 times: 
{PilotedShredder}

Used 54 times: 
{PilotedShredder, Shapeshift}

Used 82 times: 
{SteadyShot}

Used 86 times: 
{Shapeshift}

Used 70 times: 
{Reinforce}
```
Tout ces set de cartes ont été jouées dans un minimum de 25% des parties par les joueurs observés indépendemment.

On voit par exemple que `PilotedShredder` et `Shapeshift`sont joués ensemble 54 fois.

On note aussi que `TheCoin` est joué 353 fois, une carte extrêmemment présente, car elle est donnée par le jeu automatiquement à celui qui ne joue pas le premier.


#
#


## Authors

* **Posnic Antoine** - *Initial work* - [PosnicAntoine](https://github.com/PosnicAntoine)
* **Cusson Thomas**  - *Initial work* - [Poudingue](https://github.com/Poudingue)
