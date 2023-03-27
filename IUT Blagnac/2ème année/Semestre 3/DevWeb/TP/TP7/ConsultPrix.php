<?php
	/********************
		ConsultPrix.php	
	*********************/
	require("./include/configTwig.php");
	require("./include/connect.inc.php");

	// si formulaire NON SOUMIS alors on appelle la vueFormPrix en lui donnant un parametre vide
	if (!isset($_POST['Afficher']) OR !isset($_POST['BR_choix'])) {
		// on initialise le tableau biens à NULL afin que vueConsultPrix.html.twig le teste et n'inclut pas la sous-vue listeBiens.html.twig
		$biens=NULL;
		echo $twig->render('vueConsultPrix.html.twig', ['biens' => $biens ]);
	}
	// si le formulaire a été soumis
	else {
		//  on sélectionne les biens recherchés					
		switch($_POST['BR_choix']) { 
			case "moins200": 
				$titreTab="Biens de prix inférieur à 200 000 €";
				// on recherche les biens de moins de 200 000€
				$requete = "SELECT * FROM Biens WHERE prixBien < 200000";
				break;			
			case "entre200et300": 
				$titreTab="Biens de prix compris entre 200 000 € et 300 000 €";
				$requete = "SELECT * FROM Biens WHERE prixBien < 300000 AND prixBien >= 200000";
				break;
			case "plus300": 
				$titreTab="Biens de prix supérieur à 300 000 €";
				$requete = "SELECT * FROM Biens WHERE prixBien >= 300000";
				break;
		}
		// on execute la requete sur la BD
		$reqBiens = $conn->prepare($requete);
		$reqBiens->execute();
		// on récupère toutes les lignes du PDOStatement dans un tableau biensSansImg
		$biensSansImg = $reqBiens->fetchAll();
		// on libère l'espace mémoire du PDOStatement
		$reqBiens->closeCursor();
	
		// on recopie toutes les lignes du tableau $biensSansImg dans un nouveau tableau $biens
		// dans lequel on rajoute une colonne img qui va contenir le nom 'dynamique' du fichier image associé
		// à ce bien : pour idBien = B0001 on crée ainsi une valeur img = "./vues/images/B0001.jpg"
		$biens=array();
		$i=0;
		foreach($biensSansImg as $bienSansImg) {
			$biens[$i]=$bienSansImg;
			$biens[$i]['img']="./vues/images/".$bienSansImg['idBien'].".jpg";
			$i++;
		}
		// on appelle la vue en lui passant en paramètre les biens et le titre à afficher au-dessus de la table HTML
		echo $twig->render('vueConsultPrix.html.twig', [ 'biens' => $biens, 'titreTab' => $titreTab ] );	
	}	
?>