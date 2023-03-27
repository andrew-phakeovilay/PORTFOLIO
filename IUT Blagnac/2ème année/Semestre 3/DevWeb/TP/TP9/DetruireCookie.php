<?php
session_start(); // active la session
// redirige vers le formulaire de connexion en cas de tentative d'accès direct à une page sécurisée
if (!isset($_SESSION['identifie']) OR isset($_SESSION['identifie'])!='OK') {
	header('location: FormConnexion.php?msgErreur=Tentative d\'acces interdite');
	exit();
}

	// pour détruire le cookie on le renvoie avec une date d'expiration "dans le passé"
	$valCookie='Contenu d\'identification';
	setcookie('cookIdent', $valCookie, time()-60);
	// on renvoie vers la page index.php car la session existe toujours (mais plus le cookie)
	header('location: index.php');
	exit();
?>
