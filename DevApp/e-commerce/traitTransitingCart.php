<?php
	if(isset($_POST['sub'])){
		if ($_POST['selectedCart']=="cookie") {
			/*
				s'il a choisi de garder le panier cookie :
					suppression de l'ancien panier dans la bd
					recuperation du cookie en decodant vers tableau
					insertion nouveau panier
					insertion produits du tableau dans panier
					(l.7 et l.8 sont les memes que dans addingToCart.php)
					affectation session idClientIdentifie
					suppression session idClientTransiting
					redirection vers accueil
			*/
			require('includes/connect.inc.php');
			session_start();
			//suppression de l'ancien panier dans la bd
			$reqDelOldProducts = "DELETE FROM QUANTITEPANIER INNER JOIN PANIER ON QUANTITEPANIER.IDPANIER=PANIER.IDPANIER WHERE PANIER.IDCLIENT = :pIDclient AND PANIER.ENCOURS IS NULL";
			$delOldProducts = oci_parse($connect, $reqDelOldProducts);
			oci_bind_by_name($delOldProducts, ":pIDclient", $_SESSION['idClientTransiting']);
			$result_delOldProducts = oci_execute($delOldProducts);
			if(!$result_delOldProducts){
				$e = oci_error($delOldProducts);  // on récupère l'exception liée au pb d'execution de la requete (violation PK par exemple)
				print htmlentities($e['message']. ' pour cette requete : ' .$e['sqltext']);
			} else{
				oci_commit($connect);

				oci_free_statement($delOldProducts);

				$reqDelOldCart = "DELETE FROM PANIER WHERE IDCLIENT = :pIDclient_delCart AND ENCOURS IS NULL";
				$delOldCart = oci_parse($connect, $reqDelOldCart);
				oci_bind_by_name($delOldCart, ":pIDclient_delCart", $_SESSION['idClientTransiting']);
				$result_delOldCart = oci_execute($delOldCart);
				if(!$result_delOldCart){
					$e = oci_error($delOldCart);  // on récupère l'exception liée au pb d'execution de la requete (violation PK par exemple)
					print htmlentities($e['message']. ' pour cette requete : ' .$e['sqltext']);
				} else{
					oci_commit($connect);

					oci_free_statement($delOldCart);

					//recuperation du cookie en decodant vers tableau
					$arrayCookieCart = json_decode($_COOKIE['tempPanier']);

					$idPanier_returned = 0;

					//insertion nouveau panier
					$reqInsertPanier = "INSERT INTO PANIER(IDPANIER, IDCLIENT, PRIXPANIER) VALUES(SEQIDPANIER.nextval, :pID_CLIENT, 0) RETURNING IDPANIER INTO :IDPANIER_RETURNED";

					$insert_panier = oci_parse($connect, $reqInsertPanier);

					oci_bind_by_name($insert_panier, ":pID_CLIENT", $_SESSION['idClientTransiting']);
					oci_bind_by_name($insert_panier, ":IDPANIER_RETURNED", $idPanier_returned);

					$result_insert_panier = oci_execute($insert_panier);

					if (!$result_insert_panier) {
						$e = oci_error($result_insert_panier);  // on récupère l'exception liée au pb d'execution de la requete (violation PK par exemple)
						print htmlentities($e['message']. ' pour cette requete : ' .$e['sqltext']);
					} else{
						oci_commit($connect);

						oci_free_statement($insert_panier);

						//insertion produits du tableau dans panier
						$reqInsertQuantitePanier = "INSERT INTO QUANTITEPANIER(IDPANIER, IDPRODUIT, NBPRODUIT, PRIXQTEPRODUIT) VALUES(:pID_PANIER, :pID_PRODUIT, :pNB_PRODUIT, :pPRIX_QTEPRODUIT)";

						$ins_new_qte_panier = oci_parse($connect, $reqInsertQuantitePanier);

						oci_bind_by_name($ins_new_qte_panier, ":pID_PANIER", $idPanier_returned);

						foreach ($arrayCookieCart as $unProduit) {
							oci_bind_by_name($ins_new_qte_panier, ":pID_PRODUIT", $unProduit['idProduit']);
							oci_bind_by_name($ins_new_qte_panier, ":pNB_PRODUIT", $unProduit['qteProduit']);
							oci_bind_by_name($ins_new_qte_panier, ":pPRIX_QTEPRODUIT", $unProduit['qtePrixProduit']);

							$result_insertProduit = oci_execute($insert_produit);

							if (!$result_insertProduit) {
								$e = oci_error($result_insert_panier);  // on récupère l'exception liée au pb d'execution de la requete (violation PK par exemple)
								print htmlentities($e['message']. ' pour cette requete : ' .$e['sqltext']);
							} else{
								oci_commit($connect);
								oci_free_statement($insert_produit);
							}
						}
						//affectation session idClientIdentifie
						$_SESSION['idClientIdentifie'] = $_SESSION['idClientTransiting'];
						//suppression session idClientTransiting
						unset($_SESSION['idClientTransiting']);
						//redirection vers accuei
						header('location:index.php');
					}
				}
			}
		} else {
			if ($_POST['selectedCart']=="bd") {
				/*
					suppression du cookie
					affectation session idClientIdentifie
					suppression session idClientTransiting
					redirection vers accueil
				*/
				setcookie("tempPanier", "", time()-3600);
				//affectation session idClientIdentifie
				$_SESSION['idClientIdentifie'] = $_SESSION['idClientTransiting'];
				//suppression session idClientTransiting
				unset($_SESSION['idClientTransiting']);
				header('location:index.php');
			}
			else{
				header('location:index.php?msgError=?');
			}
		}
		
	}
	else{
		header('location:index.php');
	}

?>