<p>Programmation d'un système de transfert de données - AKKA</p>
<p>Massiaux Alexis</p>
<p>01/04/2019</p>

#Execution
<p>mvn clean compile</p>
<p>mvn exec:exec</p>

#Questions
<p>2. L'arbre donnée en exemple est construit en brut dans la fonction Main.java.</p>
<p>Sur la sortie standard on peut observer le cheminement des messages et vérifier le bon déroulement.</p>
</br>
<p>3. Un nouveau parcours est lancé depuis le noeud n°2, on observe que les messages sont envoyés seulement aux noeud 3 et 4.</p>
<p>Le noeud selectionné est préciser dans le pom.xml en argument via les balises "<argument>".</p>
</br>
<p>4. On ajoute un nouveau ActorSystem "systeme2". Ici il est en charge des noeuds 5 et 6.</p>
<p>On observe un déroulement similaire que précédemment.</p>
<p>5. On ajoute une liaison entre les noeuds 4 et 6.</p>
<p>Lorsque les noeuds 5 et 6 sont gérés par un second système, on observe que 6 reçoit un message du noeud 5.</p>
<p>Les premiers noeuds à recevoir le message sont 2 et 5, le transfert étant parallélisé par la présence d'un second système, lorsque le noeud n°2 continue son parcours le noeud n°5 envoi le message à son fils avant qu'il ne parvienne au noeud n°4 et qu'il puisse transmettre le message au n°6 par sa nouvelle relation</p
<p>Lorsque le second système n'est pas ajouté, le noeud n°4 transfert le message au noeud n°6 avant que n°5 le fasse.</p>
