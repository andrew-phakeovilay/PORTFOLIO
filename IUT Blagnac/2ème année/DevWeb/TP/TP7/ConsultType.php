<?php
	/********************
		ConsultType.php	
	*********************/
	require("./include/configTwig.php");
	require("./include/connect.inc.php");

	if (!isset($_POST['Afficher']) OR !isset($_POST['LD_Pièces'])) {
		$biens=NULL;
		echo $twig->render('vueConsultType.html.twig', ['biens' => $biens ]);
	}
	else {
		$titreTab = 'Biens de Type '.$_POST['LD_Pièces'].'' ;
		$requete = "SELECT * FROM Biens WHERE idType = :id ";
		$reqBiens = $conn->prepare($requete);
		$reqBiens->execute([ 'id' => $_POST['LD_Pièces']]);
		if($reqBiens->rowCount() >= 1){
			$biensSansImg = $reqBiens->fetchAll();
			$reqBiens->closeCursor();
		
			$biens=array();
			$i=0;
			foreach($biensSansImg as $bienSansImg) {
				$biens[$i]=$bienSansImg;
				$biens[$i]['img']="./vues/images/".$bienSansImg['idBien'].".jpg";
				$i++;
			}
			echo $twig->render('vueConsultType.html.twig', [ 'biens' => $biens, 'titreTab' => $titreTab ] );	
		}
		else{
			echo $twig->render('vueConsultType.html.twig', [ 'biens' => 'vide' ] );
		}
	}	 
?>