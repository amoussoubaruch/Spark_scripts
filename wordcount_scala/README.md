
> Telechargement des données 

```sh 
$ wget http://www.gutenberg.org/files/4300/4300.zip      # telechager le fichier tar
$ unzip 4300.zip                                         # dezipper le fichier
$ rm 4300.zip                                            # supprimer le fichier tar 
$ cat 4300.txt | head -50                                # Affciher les 50 première ligne du fichiers
$ more 4300.txt                                          # Affiche tout le document : entrer pour passer à page suivante 
$ cat 4300.txt | grep silence | wc                       # Afficher les parties qui contiennent silence 
```

> Transferer le fichier txt dans hdfs

```sh
$ hdfs dfs -mkdir /tmp/datafiles                         # Creer un nouveau repertoire dans hdfs 
$ hdfs dfs -put 4300.txt /tmp/datafiles                  # Copie d'un fichier dans HDFS 
$ hdfs dfs -ls /tmp/datafiles                            # Lister tous les fichiers du repertoire
$ hdfs dfs -chown -R hdfs:hdfs /tmp/datafiles            # Changer les droits sur le fichier root --> hdfs 
$ hdfs dfs -rm r /tmp/datafiles                          # Suppression du repertoire
$ hdfs dfs -rmdir  /repertoire                           # Suppression d'un repertroire 
$ hadoop fs -chmod -R 777  /repertoire                   # Rajouter tous les droits sur un fichier 
```

 

> Avec les commandes précédentes problèmes de droits : passer enn utilisateur hdfs pour poursuive

```sh 
$ su hdfs 
$ hdfs dfs -mkdir /user/datafile                          # Creer un nouveau repertoire dans hdfs 
$ hdfs dfs -put 4300.txt /user/datafile                   # Copie d'un fichier dans HDFS 
$ hdfs dfs -ls /user/datafile                             # Lister tous les fichiers du repertoire
```


> L’exécution d’une action comme count sur un très grand RDD se déroule de la manière suivante : 
> le RDD est composé de fragments, chacun stocké sur un nœud de calcul ; chaque fragment contient 
> un (grand) nombre d’items ; le programme driver de Spark envoie à chaque nœud le traitement à faire ; 
> chaque nœud de calcul exécute le traitement sur le fragment local et transmet les résultats au driver. 
> Pour d’autres actions (comme first) seul un fragment est nécessaire donc seul un nœud est sollicité.


> Si vous souhaitez supprimer les nombreuses lignes d’information qui précèdent la réponse, dans le répertoire 
> conf copiez log4j.properties.template en log4j.properties et, dans ce dernier fichier, ligne log4j.rootCategory=INFO, remplacez INFO par WARN

```sh
$ cd /usr/lib/spark/conf
$ sudo cp log4j.properties.template log4j.properties
$ sudo gedit log4j.properties        
```

> Dans gedit, ligne log4j.rootCategory=INFO, remplacez INFO par WARN
