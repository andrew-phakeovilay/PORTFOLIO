<?php

// =========================================
// Page de traitement du formulaire de login
// =========================================

// si le formulaire n'a pas été soumis, on redirige vers la page de login
if(!isset($_POST['isSubbed'])){
    header('location:formLogin.php');
}
else {
	// on se connecte à la base de données
    require_once("includes/connect.inc.php");


	// on récupère les données du formulaire
	$mdp_client_a_verifier = $_POST['mdpUtil'];
    $emailClient = $_POST['mailUtil'];
	// création de la requete
    $reqVerif = "SELECT idClient, mdpclient FROM Client WHERE mailC = :pMail";

	// on prépare la requete
    $verifMail =  oci_parse($connect, $reqVerif);

    oci_bind_by_name($verifMail, ":pMail", $emailClient);

	// on execute la requete
    $resultVerif = oci_execute($verifMail);

	// si la requete n'a pas pu être executée, on affiche l'erreur
    if (!$resultVerif) {
    	$e = oci_error($resultVerif);  // on récupère l'exception liée au pb d'execution de la requete (violation PK par exemple)
		print htmlentities($e['message']. ' pour cette requete : ' .$e['sqltext']);	
    } else {
    	$statementBD = oci_fetch_assoc($verifMail);		// vérification de l'existence du compte
    	if(empty($statementBD)){
    		header('location:formLogin.php?msgErreur=Compte inexistant');
    	}
		// vérification du mot de passe
    	if (password_verify($mdp_client_a_verifier, $statementBD['MDPCLIENT'])) {
            /*
                si panier cookie existe alors on redirige vers
                une page qui propose soit de garder le panier
                cookie soit le panier BD
                putain                
            */
            if(isset($_COOKIE['tempPanier'])){
                session_start();
                /*
                    cette variable session empechera l'utilisateur d'aller
                    ou bon lui semble tant qu'il n'aura pas choisi
                    s'il veut garder le panier cookie ou le panier
                    existant dans la BD
                */
                $_SESSION['idClientTransiting'] = $statementBD['IDCLIENT'];
                header('location:cookieCart_to_BDCart.php');
            }
            else{
        		session_start(); // démarre la session
        		$_SESSION['idClientIdentifie'] = $statementBD['IDCLIENT']; // on stocke l'id du client dans la session
        		header('location:index.php'); // on redirige vers la page d'accueil
            }
    	}
        else{
            header('location:formLogin.php?msgErreur=Mot de passe erroné');
        }
    } 
}

?>