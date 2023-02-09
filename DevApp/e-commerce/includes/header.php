<?php
//include('include/connect.inc.php');
session_start();
?>


<nav>
	<div class="logo">
		<a href="index.php"><img src="assets/images/logo.png" width="100px"></a>
	</div>
	<div class="website-title">
		<h1>Blue Gym</h1>
	</div>
	<div class="navigation">
		<div class="search-bar">
			<form method="get" action="search.php" name="formSearch">
				<input type="text" name="rechercheUser" placeholder="Rechercher" class="search-input">
				<button type="submit" class="search-button">
					<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-search" viewBox="0 0 16 16">
						<path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001c.03.04.062.078.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1.007 1.007 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0z" />
					</svg>
				</button>
			</form>
		</div>
		<div class="navbar-links">
			<ul class="navbar-link-list">

				<li>
					<a href="cart.php">
						<svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-cart-fill" viewBox="0 0 16 16">
							<path d="M0 1.5A.5.5 0 0 1 .5 1H2a.5.5 0 0 1 .485.379L2.89 3H14.5a.5.5 0 0 1 .491.592l-1.5 8A.5.5 0 0 1 13 12H4a.5.5 0 0 1-.491-.408L2.01 3.607 1.61 2H.5a.5.5 0 0 1-.5-.5zM5 12a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm7 0a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm-7 1a1 1 0 1 1 0 2 1 1 0 0 1 0-2zm7 0a1 1 0 1 1 0 2 1 1 0 0 1 0-2z" />
						</svg>

						<?php

							require_once("./includes/connect.inc.php");
							error_reporting(0);
							// Récupération du idPanier associé à l'utilisateur
							$reqPanier = "SELECT DISTINCT P.idPanier FROM PANIER P, QUANTITEPANIER QP, PRODUIT PR
							WHERE P.idpanier = QP.idpanier AND P.idclient = :idClient AND PR.idproduit = QP.idproduit AND P.encours IS NULL";

							$idPanierUtilisateur = oci_parse($connect, $reqPanier);
							oci_bind_by_name($idPanierUtilisateur, ":idClient", $_SESSION['idClientIdentifie']);
							oci_execute($idPanierUtilisateur);

							// Identifiant panier de l'utilisateur loggé
							$idPanier = oci_fetch_assoc($idPanierUtilisateur);
							
							// Récupération de la quantité panier
							$reqQuantite = "SELECT COUNT(IDPRODUIT) FROM QUANTITEPANIER WHERE IDPANIER = :idPanier";

							$qttPanier = oci_parse($connect, $reqQuantite);
							oci_bind_by_name($qttPanier, ":idPanier", $idPanier['IDPANIER']);
							oci_execute($qttPanier);

							// Quantité d'éléments dans le panier
							$quantitePanier = oci_fetch_assoc($qttPanier);

							$nbElementsPanier = $quantitePanier['COUNT(IDPRODUIT)'];
							if(isset($_COOKIE['tempPanier'])){
								$nbElementsPanier = $nbElementsPanier + count(json_decode($_COOKIE['tempPanier']));
							}
							oci_free_statement($idPanierUtilisateur);
							oci_free_statement($qttPanier)
						?>

						<?php 
							if ($nbElementsPanier != 0) {
								echo '<span class="cart-badge">' . $nbElementsPanier . '</span>';
							}
						?>

					</a>

				</li>
				<li>
					<div>
						<div class="dropdown">
							<button class="btn dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">
								<svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-person-fill" viewBox="0 0 16 16">
									<path d="M3 14s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1H3Zm5-6a3 3 0 1 0 0-6 3 3 0 0 0 0 6Z" />
								</svg>
							</button>
							<ul class="dropdown-menu">
								<?php
								if (!isset($_SESSION['idClientIdentifie'])) {
									echo '<li><a class="dropdown-item" href="./formLogin.php"> Connexion</a></li>';
									echo '<li><a class="dropdown-item text-primary fw-bold" href="./formRegister.php"> Inscription</a></li>';
								} else {
									$reqAdm = "SELECT * FROM CLIENT WHERE IDCLIENT= :pIdClie";
									$prepAdm = oci_parse($connect, $reqAdm);
									oci_bind_by_name($prepAdm, ":pIdClie", $_SESSION['idClientIdentifie']);
									$gotAdmin = oci_execute($prepAdm);

									while (($getInfos = oci_fetch_assoc($prepAdm)) != false) {
										$adm=$getInfos["ADMIN"];
									}
									
									echo '<li><a class="dropdown-item" href="./profile.php"> Mon profil</a></li>';
									if($adm != null){
										echo '<li><a class="dropdown-item" href="./administration/admin.php"> Actions administrateur </a></li>';
									}
									echo '<li><a class="dropdown-item text-danger fw-bold" href="./deconnexion.php"> Déconnexion</a></li>';

									oci_free_statement($prepAdm);
								}
								?>
							</ul>
						</div>
					</div>
				</li>
				<li>
					<div>
						<div class="dropdown">
							<button class="btn dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">
								<img src="https://cdn-icons-png.flaticon.com/512/206/206657.png" alt="" width="20">
							</button>
							<ul class="dropdown-menu">
								<li><img src="https://cdn-icons-png.flaticon.com/512/206/206657.png" alt="" width="20"></li>
								<li><img src="https://cdn-icons-png.flaticon.com/512/4060/4060233.png" alt="" width="20"></li>
								<li><img src="https://cdn-icons-png.flaticon.com/512/3373/3373278.png" alt="" width="20"></li>
							</ul>
						</div>
					</div>
				</li>
			</ul>
		</div>
	</div>
</nav>

<?php include('includes/catHeader.php'); ?>

</nav>