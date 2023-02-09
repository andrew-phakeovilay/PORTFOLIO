<?php
if(isset($_POST['sub'])){
	require('includes/connect.inc.php');
	$qteSelectionne = $_POST['quantiteSelectionne'];
	$idArticle = $_POST['idProduit'];
	session_start();
	if(!empty($qteSelectionne) or $qteSelectionne != ""){
		//recuperer prix produit
			$req_prix_produit = "SELECT PRIXPRODUIT FROM PRODUIT WHERE IDPRODUIT=:p_PRIX_ID_PRODUIT";

			$prix_produit = oci_parse($connect, $req_prix_produit);

			oci_bind_by_name($prix_produit, ":p_PRIX_ID_PRODUIT", $idArticle);

			$result_prix_produit = oci_execute($prix_produit);

			if (!$result_prix_produit) {
				$e = oci_error($result_prix_produit);  // on récupère l'exception liée au pb d'execution de la requete (violation PK par exemple)
				print htmlentities($e['message']. ' pour cette requete : ' .$e['sqltext']);
			} else {
				$statementBD_prix_produit = oci_fetch_assoc($prix_produit);
				$prix_qte_produit = $qteSelectionne * $statementBD_prix_produit['PRIXPRODUIT'];
			}
		//fin recuperer prix produit


		//si l'utilisateur est connecte
			if(isset($_SESSION['idClientIdentifie'])){
			//sql
			/*
				recuperer prix produit
				recuperer idpanier
				si panier deja existant
					si produit deja dans panier
						update de quantitepanier
					sinon
						insert produit dans quantitepanier
				sinon
					insert panier dans panier en recuperant idpanier crée
					insert produit dans quantitepanier
			*/
			//---------------------------------------------------------
			//recuperer id panier
				$identifiantClient = $_SESSION['idClientIdentifie'];
				$req_is_panier = "SELECT IDPANIER FROM PANIER WHERE IDCLIENT = :pIDCLIENT AND ENCOURS IS NULL";

				$is_panier = oci_parse($connect, $req_is_panier);

				oci_bind_by_name($is_panier, ":pIDCLIENT", $identifiantClient);

				$result_is_panier = oci_execute($is_panier);

				if (!$result_is_panier) {
					$e = oci_error($result_is_panier);  // on récupère l'exception liée au pb d'execution de la requete (violation PK par exemple)
					print htmlentities($e['message']. ' pour cette requete : ' .$e['sqltext']);
				} else {
					$statementBD_is_panier = oci_fetch_assoc($is_panier);
				}
			//fin recuperer id panier
		 //---------------------------------------------------------
			//si panier deja existant
				if(isset($statementBD_is_panier['IDPANIER'])){
					//si article deja dans panier
						$req_is_already_in_cart  = "SELECT NBPRODUIT FROM QUANTITEPANIER WHERE IDPANIER = :pID_PANIER_is_in_cart AND IDPRODUIT = :pID_PRODUIT_is_in_cart";

						$is_already_in_cart = oci_parse($connect, $req_is_already_in_cart);

						oci_bind_by_name($is_already_in_cart, ":pID_PANIER_is_in_cart", $statementBD_is_panier['IDPANIER']);
						oci_bind_by_name($is_already_in_cart, ":pID_PRODUIT_is_in_cart", $idArticle);

						$result_is_already_in_cart = oci_execute($is_already_in_cart);

						if (!$result_is_already_in_cart) {
							$e = oci_error($result_is_already_in_cart);  // on récupère l'exception liée au pb d'execution de la requete (violation PK par exemple)
							print htmlentities($e['message']. ' pour cette requete : ' .$e['sqltext']);
						} else {
							$statementBD_is_already_in_cart = oci_fetch_assoc($is_already_in_cart);
						}
						/*
						si cet element a une valeur cela veut dire que la requete a renvoye
						un resultat non vide, donc que le produit est deja dans le panier
						*/
						if (isset($statementBD_is_already_in_cart['NBPRODUIT'])) {
							/*
								il a ete decide qu un produit ne peut pas etre commande plus de 10 fois
								en une commande, il faut donc verifier que l'utilisateur qui essaye de
								rajouter un produit qui est deja dans le panier ne fera pas exceder la
								quantite maximale de ce produit dans le panier
							*/
							if ($statementBD_is_already_in_cart['NBPRODUIT']+$qteSelectionne<=10) {
								$req_update_already_in_cart = "UPDATE QUANTITEPANIER SET NBPRODUIT = NBPRODUIT+:pNB_PRODUIT_ALREADY_IN_CART WHERE IDPANIER = :pID_PANIER_ALREADY_IN_CART AND IDPRODUIT = :pID_PRODUIT_ALRDY_CART";
						
								$up_alrdy_in_cart = oci_parse($connect, $req_update_already_in_cart);
							
								oci_bind_by_name($up_alrdy_in_cart, ":pNB_PRODUIT_ALREADY_IN_CART", $qteSelectionne);
								oci_bind_by_name($up_alrdy_in_cart, ":pID_PANIER_ALREADY_IN_CART", $statementBD_is_panier['IDPANIER']);
								oci_bind_by_name($up_alrdy_in_cart, ":pID_PRODUIT_ALRDY_CART", $idArticle);
							
								$result_update_already_in_cart = oci_execute($up_alrdy_in_cart);
							
								oci_commit($connect);
							
								oci_free_statement($up_alrdy_in_cart);
							} 
							//si cette valeur va etre excede on renvoie l'utilisateur vers l'accueil avec un message d'erreur
							else {
								header('location:index.php?msgError=Ce produit ne peut pas être ajouté plus de 10 fois dans le panier');
							}
							
							
						}
					//fin si article deja dans panier
				//----------------------------------------------------------
					//si article PAS deja dans panier
						else{
							$reqInsertQuantitePanier = "INSERT INTO QUANTITEPANIER(IDPANIER, IDPRODUIT, NBPRODUIT, PRIXQTEPRODUIT) VALUES(:pID_PANIER, :pID_PRODUIT, :pNB_PRODUIT, :pPRIX_QTEPRODUIT)";

							$insert_qte_panier = oci_parse($connect, $reqInsertQuantitePanier);

							oci_bind_by_name($insert_qte_panier, ":pID_PANIER", $statementBD_is_panier['IDPANIER']);
							oci_bind_by_name($insert_qte_panier, ":pID_PRODUIT", $idArticle);
							oci_bind_by_name($insert_qte_panier, ":pNB_PRODUIT", $qteSelectionne);
							oci_bind_by_name($insert_qte_panier, ":pPRIX_QTEPRODUIT", $prix_qte_produit);

							$result_insert_qtepanier = oci_execute($insert_qte_panier);

							if (!$result_insert_qtepanier) {
								$e = oci_error($result_insert_qtepanier);  // on récupère l'exception liée au pb d'execution de la requete (violation PK par exemple)
								print htmlentities($e['message']. ' pour cette requete : ' .$e['sqltext']);
							}

							oci_commit($connect);

							oci_free_statement($insert_qte_panier);
						}
					//fin si article PAS deja dans panier
				}
			//fin si panier deja existant
			//sinon
				else{
					/*
						Si le panier n'existe pas encore on va devoir le creer puis y
						rajouter le produit
						A noter que pour y rajouter le produit il faut l'idPanier qui est
						genere grace au sequenceur a l'insertion du panier
						On indique donc a l insertion du panier de nous renvoyer l'idPanier
						genere pour eviter d'avoir une requete de plus a faire pour aller chercher
						l'idPanier
					*/
					$idPanier_returned = 0;

					$reqInsertPanier = "INSERT INTO PANIER(IDPANIER, IDCLIENT, PRIXPANIER) VALUES(SEQIDPANIER.nextval, :pID_CLIENT, 0) RETURNING IDPANIER INTO :IDPANIER_RETURNED";

					$insert_panier = oci_parse($connect, $reqInsertPanier);

					oci_bind_by_name($insert_panier, ":pID_CLIENT", $_SESSION['idClientIdentifie']);
					oci_bind_by_name($insert_panier, ":IDPANIER_RETURNED", $idPanier_returned);

					$result_insert_panier = oci_execute($insert_panier);

					if (!$result_insert_panier) {
						$e = oci_error($result_insert_panier);  // on récupère l'exception liée au pb d'execution de la requete (violation PK par exemple)
						print htmlentities($e['message']. ' pour cette requete : ' .$e['sqltext']);
					}
					oci_commit($connect);

					oci_free_statement($insert_panier);

					echo "Nouveau id panier = ".$idPanier_returned;

					//idem que l.114 à 132 sauf qu'on utilise les nouvelles variables de l'insertion du panier
					$reqInsertQuantitePanier = "INSERT INTO QUANTITEPANIER(IDPANIER, IDPRODUIT, NBPRODUIT, PRIXQTEPRODUIT) VALUES(:pID_PANIER, :pID_PRODUIT, :pNB_PRODUIT, :pPRIX_QTEPRODUIT)";

					$ins_new_qte_panier = oci_parse($connect, $reqInsertQuantitePanier);

					oci_bind_by_name($ins_new_qte_panier, ":pID_PANIER", $idPanier_returned);
					oci_bind_by_name($ins_new_qte_panier, ":pID_PRODUIT", $idArticle);
					oci_bind_by_name($ins_new_qte_panier, ":pNB_PRODUIT", $qteSelectionne);
					oci_bind_by_name($ins_new_qte_panier, ":pPRIX_QTEPRODUIT", $prix_qte_produit);

					$result_insert_qtepanier_inexistant = oci_execute($ins_new_qte_panier);

					if (!$result_insert_qtepanier_inexistant) {
						$e = oci_error($result_insert_qtepanier_inexistant);  // on récupère l'exception liée au pb d'execution de la requete (violation PK par exemple)
						print htmlentities($e['message']. ' pour cette requete : ' .$e['sqltext']);
					}

					oci_commit($connect);

					oci_free_statement($ins_new_qte_panier);
				}
		}
		//fin si l'utilisateur est connecte
		else{
			//cookie
			/*
				si cookie de panier existe deja
					recherche produit deja dans panier
					si produit deja dans panier
						setcookie en ajoutant seulement la nouvelle qte
					sinon
						setcookie en ajoutant produit,qte,prixqte
				sinon
					setcookie en creant avec produit,qte,prixqte

			Le panier en cookie est sous forme de tableau multi dimensionnel de la forme :
				Array(Array(String x String x String)?)
			*/
			if (isset($_COOKIE['tempPanier'])) {
				$oldArray_tempPanier = json_decode($_COOKIE['tempPanier'], true);
				$isNotFound = true;
				foreach ($oldArray_tempPanier as $indexTableau  => $tableauAssociatifProduit) {
					if ($tableauAssociatifProduit['idProduit'] == $idArticle) {
						$oldArray_tempPanier[$indexTableau]['qteProduit'] = $tableauAssociatifProduit['qteProduit'] + $qteSelectionne;
						$oldArray_tempPanier[$indexTableau]['qtePrixProduit'] = $oldArray_tempPanier[$indexTableau]['qteProduit'] * $statementBD_prix_produit['PRIXPRODUIT'];
						$isNotFound = false;
						break;
					}
				}
				//Si le produit n'a pas ete trouve dans le panier, on construit le tableau
				if ($isNotFound) {
					$newProduct_in_tempPanier = array('idProduit' => $idArticle, 'qteProduit' => $qteSelectionne,  'qtePrixProduit' => $prix_qte_produit);
					array_push($oldArray_tempPanier, $newProduct_in_tempPanier);
				}
				//destruction de l'ancien panier devenu obsolete
				setcookie("tempPanier", "", time()-3600);
				//definition du nouveau panier pour 2h
				$new_tempPanier_json_encoded = json_encode($oldArray_tempPanier);
				setcookie("tempPanier", $new_tempPanier_json_encoded, time()+7200);
			}
			else{
				$newTempPanier = array();
				$newProduct_in_tempPanier = array('idProduit' => $idArticle, 'qteProduit' => $qteSelectionne,  'qtePrixProduit' => $prix_qte_produit);
				array_push($newTempPanier, $newProduct_in_tempPanier);
				$newTempPanier_json_encoded = json_encode($newTempPanier);
				setcookie("tempPanier", $newTempPanier_json_encoded, time()+7200);
			}
			/*debug (la nouvelle valeur du cookie ne peut pas etre vu directement apres l avoir defini pour je ne sais quelle raison)
			echo "Ancienne valeur du cookie : ";
			echo print_r($_COOKIE['tempPanier']);
			*/
		}
	}
	header("location:product.php?idProduit=$idArticle&msgConfirm=Produit ajouté dans le panier");
}
else{
	header('location:index.php');
}
?>