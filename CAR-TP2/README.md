<p>Programmation du passerelle REST/FTP en JAVA</p>
<p>Massiaux Alexis</p>
<p>18/08/2019</p>

#Prerequis
<p>Lancement d'un serveur FTP.</p>
<p>Specification de l'adresse et du port du serveur dans le fichier configuration.txt .</p>

#Execution
<p>mvn clean compile</p>
<p>mvn exec:java</p>

#Commandes curl

* se connecter curl http://localhost:8080/car/login/username:pass

* accéder à un répertoire et lister son contenu  	curl http://localhost:8080/car/file/list/
* télécharger un fichier texte 				curl http://localhost:8080/car/file/download/{FILENAME}
* télécharger une image
* télécharger un répertoire complet
* mettre en ligne un fichier texte			curl http://localhost:8080/car/file/upload/{chemin du fichier}
* mettre en ligne une image
* mettre en ligne un répertoire complet
* renommer un fichier distant				curl http://localhost:8080/car/file/rename/{FROM}-{TO}
* créer un répertoire distant				curl http://localhost:8080/car/file/mkdir/{FILENAME}
* renommer un répertoire distant			curl http://localhost:8080/car/file/rename/{FROM}-{TO}
* supprimer un répertoire distant			curl http://localhost:8080/car/file/delete/{FILENAME}

#Classe Client

<p>Utilisation de la classe FTPClient d'apache.</p>
<p>Son instanciation utilise un Singleton, lorsqu'un client se connecte son instance est conservé jusqu'à la fin sans possibilité d'être modifié.</p>

#Classe Login

<p>Permet de se connecter au serveur FTP. Nécessaire de s'authentifier avant l'execution de n'importe quelles commandes.</p>

#Classe Resources

<p>Permet d'accèder à un repertoire, lister son contenu, télécharger un fichier texte, mettre en ligne un fichier texte, renomer, créer ou supprimer un fichier ou un repertoire distant.</p>
