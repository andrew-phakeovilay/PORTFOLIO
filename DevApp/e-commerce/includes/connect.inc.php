<?php
// Ce fichier connect.inc.php sera inclus dans
// chaque page PHP qui travaille avec la BD
$user = "SAEBD04";
$pass = "1g0rtapis";

	$db = "(DESCRIPTION =
			(ADDRESS = (PROTOCOL = TCP)(HOST = oracle.iut-blagnac.fr)(PORT = 1521))
			(CONNECT_DATA =
			  (SERVER = DEDICATED)
			  (SID = db11g)
			)
		  )" ;
	$connect = oci_connect($user, $pass, $db, 'AL32UTF8');
	
	// si la connexion a échoué, on affiche le message d'erreur
	if (!$connect) {
		$e = oci_error();
		trigger_error(htmlentities($e['message'], ENT_QUOTES), E_USER_ERROR);
	}

?>