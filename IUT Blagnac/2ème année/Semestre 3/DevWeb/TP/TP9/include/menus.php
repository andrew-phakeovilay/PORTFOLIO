<div id="sidebar" class="sidebar-offcanvas">
    <div style="padding-top: 30px;" class="col-md-12">
        <ul class="nav nav-pills flex-column">
            
			<?php
				// on récupère le nom du script executé sans son chemin
				$page = pathinfo($_SERVER['PHP_SELF'], PATHINFO_BASENAME);
				// echo $page;
				echo '<li class="nav-item">';
				if ($page == 'index.php') {
					echo '<a class="nav-link active" href="index.php">Accueil</a>';
				}
				else {
					echo '<a class="nav-link" href="index.php">Accueil</a>';
				}
				echo '</li>';
				
				
				echo '<li class="nav-item">';
				if ($page == 'ConsultPrix.php') {
					echo '<a class="nav-link active" href="ConsultPrix.php">Consultation des biens par prix</a>';
				}
				else {
					echo '<a class="nav-link" href="ConsultPrix.php">Consultation des biens par prix</a>';
				}
				echo '</li>';
				
				
				echo '<li class="nav-item">';
				if ($page == 'ConsultType.php') {
					echo '<a class="nav-link active" href="ConsultType.php">Consultation des biens par type</a>';
				}
				else {
					echo '<a class="nav-link" href="ConsultType.php">Consultation des biens par type</a>';
				}
				echo '</li>';
				
				echo '<li class="nav-item">';
				if ($page == 'AjoutType.php') {
					echo '<a class="nav-link active" href="AjoutType.php">Ajout d\'un type de biens</a>';
				}
				else {
					echo '<a class="nav-link" href="AjoutType.php">Ajout d\'un type de biens</a>';
				}
				echo '</li>';
				
				echo '<li class="nav-item">';
				if ($page == 'Deconnexion.php') {
					echo '<a class="nav-link active" href="Deconnexion.php">Deconnexion (suppression de la session)</a>';
				}
				else {
					echo '<a class="nav-link" href="Deconnexion.php">Deconnexion (suppression de la session)</a>';
				}
				echo '</li>';
				
				if (isset($_COOKIE['cookIdent'])) {
					echo '<li class="nav-item">';
					if ($page == 'DetruireCookie.php.') {
						echo '<a class="nav-link active" href="DetruireCookie.php">Détruire le cookie</a>';
					}
					else {
						echo '<a class="nav-link" href="DetruireCookie.php">Détruire le cookie</a>';
					}
					echo '</li>';
				}								
			?>
        </ul>
    </div>
</div>

