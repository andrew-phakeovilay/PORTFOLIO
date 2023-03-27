<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8"/>
    <link rel="stylesheet" href="include/bootstrap.min.css">
    <link rel="stylesheet" href="include/styles.css">
	<title>Mon site en PHP!</title>
</head>
<body>
	<?php
        session_start();
        if(!empty($_SESSION['identifie'])){
        }
        else{
            header('location: FormConnexion.php?msgErreur=Tentative d\'acces interdite');
            exit();
        }
    ?>
	<?php include("./include/header.php"); ?>

    <?php include("./include/menus.php"); ?>
    <div style="padding-top: 30px" id="main">
        <div style="text-align: center" class="col-md-12">
			<h1>Consulter les biens par tranche de prix</h1>
			<br><br>
			<form method='post' action='ConsultPrix.php'>
				<fieldset>
					<div>
						<input type='radio' name='BR_Prix' value='moins200k' <?php if(isset($_POST['BR_Prix']) && $_POST['BR_Prix'] == 'moins200k')  echo ' checked="checked"';?> /> < 200 000€<BR><BR>
						<input type='radio' name='BR_Prix' value='entre200k300k' <?php if(isset($_POST['BR_Prix']) && $_POST['BR_Prix'] == 'entre200k300k')  echo ' checked="checked"';?> /> de 200 000 € à 300 000 €<BR><BR>
						<input type='radio' name='BR_Prix' value='plus300k' <?php if(isset($_POST['BR_Prix']) && $_POST['BR_Prix'] == 'plus300k')  echo ' checked="checked"';?> /> > 300 000 €<BR><BR>
					</div>
					<div>
						<input type='submit' name='Afficher' value='Afficher'/><BR><BR>
					</div>
				</fieldset>
			</form><BR><BR>
				<?php
					// ConsultPrix.php
					IF(isset($_POST['Afficher']) && isset($_POST['BR_Prix'])){
						require_once('./include/connect.inc.php');

						switch($_POST['BR_Prix']){
							case('moins200k'):
								$texte = 'Biens de prix inférieur à 200 000 €';
								$request = "SELECT idType, titreBien, idBien, prixBien FROM Biens WHERE prixBien < 200000";
								break;
							case('entre200k300k'):
								$texte = 'Biens de prix compris entre 200 000 € et 300 000 €';
								$request = "SELECT idType, titreBien, idBien, prixBien FROM Biens WHERE prixBien >= 200000 AND prixBien <= 300000";
								break;
							case('plus300k'):
								$texte = 'Biens de prix supérieur à 300 000 €';
								$request = "SELECT idType, titreBien, idBien, prixBien FROM Biens WHERE prixBien > 300000";
								break;
						}
						$result = $con->prepare($request);
						$result->execute();
						echo '
						<center><table border="2" style="text-align:center">
						<caption style="text-align:center">'.$texte.'</caption>
						<tr>
							<th>Identifiant</th><th>Titre</th><th>Prix</th><th>Type Bien</th><th>Image Bien</th>
						</tr>';
						foreach($result as $value) {
							echo '<tr>';
								echo '<td><a href="ConsultBien.php?pIdBien='.$value['idBien'].'">'.$value['idBien'].'</a></td>';
								echo '<td>'.$value['titreBien'].'</td>';
								echo '<td>'.$value['prixBien'].'</td>';
								echo '<td>'.$value['idType'].'</td>';
								echo '<td><img src="./images/'.$value['idBien'].'".jpg" alt="'.$value['idBien'].'"></td>';
								echo '<td><img src="./images/'.$value['idBien'].'".jpg" alt="'.$value['idBien'].'"></td>';
								echo '<td><img src="./images/'.$value['idBien'].'".jpg" alt="'.$value['idBien'].'"></td>';
							echo '</tr>';
						}
						echo '</table></center>';
					}
				?>
				<a href="AjoutBien.php"><img src="./images/ajouter.jpg"></a>
            </div>
        </div>
    </div>
	<?php include("./include/footer.php"); ?>
</body>
</html>