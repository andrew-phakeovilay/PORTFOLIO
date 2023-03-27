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
			<h3>Détail Bien</h3>
				<?php
					require_once('./include/connect.inc.php');
					$request = "SELECT idType, titreBien, detailBien, idBien, prixBien FROM Biens WHERE idBien = :idB";
					$result = $con->prepare($request);
					$result->execute(['idB' => $_GET['pIdBien']]);
					echo '
					<center><table border="2" style="text-align:center">
					<tr>
						<th>Identifiant</th><th>Titre</th><th>Détails</th><th>Prix</th><th>Type Bien</th><th>Image Bien</th>
					</tr>';
					foreach($result as $value) {
						echo '<tr>';
							echo '<td>'.$value['idBien'].'</td>';
							echo '<td>'.$value['titreBien'].'</td>';
							echo '<td>'.$value['detailBien'].'</td>';
							echo '<td>'.$value['prixBien'].'</td>';
							echo '<td>'.$value['idType'].'</td>';
							echo '<td><img src="./images/'.$value['idBien'].'".jpg" alt="'.$value['idBien'].'"></td>';
						echo '</tr>';
					}
					echo '</table></center>';
				?>
				<br>
				<form method='post' action='ConsultPrix.php'>
					<input type="submit" value="Retour à la consultation par prix">
				</form>
            </div>
        </div>
    </div>
	<?php include("./include/footer.php"); ?>
</body>
</html>