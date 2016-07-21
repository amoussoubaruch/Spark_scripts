// Afficher la version de Spark
sc.version

// ou
sc.appName

// Quitter le shell scala
:quit


// Importer spark context : module neccéssaire au démarage de chaque application spark
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._

// sc est un objet SparkContext qui indique à Spark comment accéder à un cluster pour exécuter les commandes


// Créer une variable document et lui donner le lien vers le fichier : pour rappel utiliser hdfs://namenodehost/parent/child/file pour lire un fichier
val document = "hdfs://dgop146.gop.link//user/datafile/4300.txt"

// Création d'un rdd à partir de la variable txtFile
val DataDocument = sc.textFile(document)

// La fonction cache est appelée pour stocker les RDD créés en cache, de façon à ce que Spark n’ait pas à les recalculer à chaque fois, à chaque requête suivante. 
// Notez que cache() est une opération lazy, Spark ne stocke pas la donnée immédiatement en mémoire, en fait, ceci sera fait lorsque l’action sera invoquée sur un RDD. 
DataDocument.cache()  // Pas néccessaire pour appliquer des transformations ou des actions 

// Compter le nombre de lignes dans le fichiers 
DataDocument.count()

// Premier item de ce RDD
DataDocument.first() 

// 5 premiers items de ce RDD
DataDocument.take(5)

// retourne le contenu de ce RDD
DataDocument.collect() 

// Réaliser le décompte des mots et afficher le compte à côté de chaque mot présent dans le fichier.
val wcData = DataDocument.flatMap(l => l.split(" ")).map(word => (word, 1)).reduceByKey(_ + _)

// Affichzer le résultat 
wcData.collect().foreach(println)


// Construire un RDD comprenant seulement les lignes de DataDocument qui contiennent “silence” et retourner un tableau avec ses 2 premiers items :

val lignesAvecsilence = DataDocument.filter(line => line.contains("silence"))

// tableau avec les 2 premiers items de ce RDD
lignesAvecsilence.take(2) 

// Construire un RDD composé des longueurs des lignes de texteLicence
val longueursLignes = DataDocument.map(l => l.length)

// Retourner un tableau avec ses 5 premiers items :
longueursLignes.take(5)

// Nombre de caractères que contient le fichier
DataDocument.map(l => l.length).reduce((a, b) => a + b)

// Important : l’évaluation est “paresseuse” : les opérations sont effectuées seulement quand un résultat doit être retourné





