<?php
	session_start();
	if (!isset($_SESSION['identifie'])) {
		header('location:FormConnexion.php?msgErreur=Interdiction d\'acceder à l\'espace sécurisé sans identification !!!');
		exit();
	}
	session_destroy();
	header('location:FormConnexion.php?msgErreur=Deconnexion effectuée !');
	exit();
?>
