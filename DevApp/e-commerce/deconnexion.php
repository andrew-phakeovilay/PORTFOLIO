<?php
session_start();

if(!isset($_SESSION['idClientIdentifie'])){
    header('location:formLogin.php?msgErreur=Merci de vous connecter en premier lieu');
}
else{
	$_SESSION = array();
	header("location:formLogin.php?msgErreur=Deconnexion effectuée !");
	exit();
}
?>