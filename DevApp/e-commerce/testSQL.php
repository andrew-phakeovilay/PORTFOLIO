<?php
	// on inclut le fichier de connexion � la base Oracle
	require_once("./includes/connect.inc.php");
	error_reporting(0);
	
	// si la connexion a r�ussi...
	// on cr�e une variable pour la d�finition de la requ�te : tous les joueurs fran�ais tri�s par nom, prenom
	$req1 = "SELECT * FROM Client";
		
	$lesClients = oci_parse($connect, $req1);
	
  // on execute la requete
 	$result = oci_execute($lesClients);
	// si erreur de requete alors affichage...
	if (!$result) {
		$e = oci_error($lesClients);  // on r�cup�re l'exception li�e au pb d'execution de la requete
		print htmlentities($e['message'].' pour cette requete : '.$e['sqltext']);		
	}

	// si pas d'erreur alors on parcourt le curseur r�sultat pour affichage en PHP
	echo "<H1> Les Clients</H1>";
	while (($leClient = oci_fetch_assoc($lesClients)) != false) {
		echo $leClient['IDCLIENT']." : ".$leClient['NOMC']." ".$leClient['PRENOMC']; 
	    echo "<br/>";
	}
	
	// Lib�re toutes les ressources r�serv�es par un r�sultat Oracle
	oci_free_statement($lesClients);
 
 
 echo "<br>";
 echo "<br>";
 echo "<br>";
 
    
  $reqProducts = "SELECT * FROM PRODUIT";

  $prepProduits = oci_parse($connect, $reqProducts);

  $gotProduits = oci_execute($prepProduits);
  
  if($gotProduits){
    echo "<H1> Les Produits</H1>";
	  while (($leProduit = oci_fetch_assoc($prepProduits)) != false) {
  		echo $leProduit['IDPRODUIT']." : ".$leProduit['NOMP']." ".$leProduit['DESCRIPTION']." ".$leProduit['IDCATEGORIE'] ; 
      echo "<br/>";
  	}
  }


/*
/////////////////////////////////////////////////////////////////////////////////
	// on va cr�er une requete param�tr�e
	$req2 = "SELECT * FROM Joueur WHERE ne = 'FRA' and pst = :pPst ORDER BY NOMJ, PREJ";
	// on pr�pare la requ�te
    $lesPiliersFrancais = oci_parse($connect, $req2);
		
	// il faut passer par une variable pour contenir la valeur
	$pst = "Pilier"; 
	// on lie la valeur au param�tre de la requ�te
	oci_bind_by_name($lesPiliersFrancais, ":pPst", $pst);
	
	// on execute la requete
 	$result = oci_execute($lesPiliersFrancais);
	// pas d'erreur Oracle ici car un select qui ne ramene rien n'est pas une erreur, c'est un r�sultat...

	//  on parcourt le curseur r�sultat pour affichage en PHP
	echo "<H1> Les Piliers de l'�quipe de France par ordre alphab�tique</H1>";
	while (($lePilierFr = oci_fetch_assoc($lesPiliersFrancais)) != false) {
		echo $lePilierFr['PREJ']." ".$lePilierFr['NOMJ']." est un ".$lePilierFr['PST']." fran�ais"; 
	    echo "<br/>";
	}
	// Lib�re toutes les ressources r�serv�es par un r�sultat Oracle
	oci_free_statement($lesPiliersFrancais);

/*

	//////////////////////////////////////////////////////////////////////////////////
	// on cr�e une autre variable pour la d�finition d'une requ�te param�tr�e d'insertion
	$req3 = "INSERT INTO Joueur (nj, prej, nomj, ne) VALUES(:pNj, :pPrej, :pNomJ, :pNe)";						 
	// on pr�pare la requ�te param�tr�e
	$insertJoueur = oci_parse($connect, $req3);
	// on associe les valeurs aux param�tres de la requ�te via des variables (sinon �a marche pas !)
	$nj = 300; 	$prej = "Patricia"; $nomj = "Stolf" ; $ne = "FRA";
	oci_bind_by_name($insertJoueur, ":pNj", $nj);
	oci_bind_by_name($insertJoueur, ":pPreJ", $prej);
	oci_bind_by_name($insertJoueur, ":pNomJ", $nomj );
	oci_bind_by_name($insertJoueur, ":pNe", $ne);
	// on execute la requete
	$result = oci_execute($insertJoueur);
	// si erreur de requete alors affichage...
	if (!$result) {
		$e = oci_error($insertJoueur);  // on r�cup�re l'exception li�e au pb d'execution de la requete (violation PK par exemple)
		print htmlentities($e['message'].' pour cette requete : '.$e['sqltext']);		
	}
	// v�rifiez l'insertion dans SQL developer avec cette requete : select * from joueur where nj > 299;
	// on commit les modifs faites
	oci_commit($connect);
	
	// Lib�re toutes les ressources r�serv�es par un r�sultat Oracle
	oci_free_statement($insertJoueur);
*/

/*
	// on supprime la ligne pr�c�demment ajout�e (stolf...)
	$req4 = "DELETE FROM Joueur WHERE nj = 300";						 
	// on pr�pare la requ�te param�tr�e
	$deleteJoueur = oci_parse($connect, $req4);
	// on execute la requete
	$result = oci_execute($deleteJoueur);
	// pas d'erreur Oracle ici car un delete qui ne fait rien n'est pas une erreur
	// on commit les modifs faites
	oci_commit($connect);
	// Lib�re toutes les ressources r�serv�es par un r�sultat Oracle
	oci_free_statement($deleteJoueur);
	// v�rifiez l'insertion dans SQL developer avec cette requete : select * from joueur where nj > 299;
*/

/*
	//////////////////////////////////////////////////////////////////////////////////////////////
	// on cr�e une autre variable pour l'appel d'une fonction stock�e dans un package avec passage param et valeur retour
	echo "<H1> Nb points marqu�s par une �quipe </H1>";
	$req = " begin :retour := Gestion_Rugby.retournePointsMarques(:pNe); end; ";
	$appelFunctStock = oci_parse($connect, $req);
	// on d�finit la valeur du param�tre en entr�e de la fonction
	$ne = "Irlande";
	oci_bind_by_name($appelFunctStock, ':pNe', $ne);
	
	// on d�finit la variable qui va r�cup�rer la valeur retourn�e par la fonction stock�e
	// le dernier param�tre definit la longueur maximale pour la variable r�cup�r�e
	oci_bind_by_name($appelFunctStock, ':retour', $retour, 40);
	$result = oci_execute($appelFunctStock);
	
	if (!$result) {
		// on r�cup�re l'exception li�e au pb d'execution de la fonction (no data found pour cette �quipe)
		$e = oci_error($appelFunctStock);  
		print htmlentities($e['message'].' pour la fonction : '.$e['sqltext']);
		echo "</BR></BR></BR></BR>";		
	}
	else {
		echo "</BR></BR> Nb points marqu�s par les joueurs de l'Irlande : ".$retour."</BR>";   // Affiche 168 
		echo "</BR></BR></BR></BR>";
	}
	oci_free_statement($appelFunctStock);
	oci_close($connect);
	
	// D'autres exemples d'utilisation d'OCI ici :  https://www.php.net/manual/fr/oci8.examples.php
	*/
?>
