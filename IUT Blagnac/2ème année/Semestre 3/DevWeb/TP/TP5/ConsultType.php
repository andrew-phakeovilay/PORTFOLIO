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
            header('location: FormConnexion.php?msgErreur=Tentativedaccesinterdite');
            exit();
        }
    ?>
	<?php include("./include/header.php"); ?>

    <?php include("./include/menus.php"); ?>
    <div style="padding-top: 30px" id="main">
        <div style="text-align: center" class="col-md-12">
		<h1>Consulter les biens par type</h1>
		<br><br>
		<form method='post' action='ConsultType.php'>
			<fieldset>
				<div>
					<select name='LD_Pièces'>
						<?php
							require_once("include/connect.inc.php");
							$requestListe = $con->prepare("SELECT nomType, idType FROM Types");
							$requestListe->execute();
							foreach($requestListe as $ligneListe){
								echo "<option value='".$ligneListe['idType']."'>".$ligneListe['nomType']."</option>";
							}
							$requestListe->closeCursor();
						?>
					</select>
				</div>
				<BR>
				<div>
					<input type='submit' name='Afficher' value='Afficher'/><BR>
				</div>
			</fieldset>
		</form><BR><BR>
				<?php
				
					//ConsultType.php

					IF(isset($_POST['Afficher']) && isset($_POST['LD_Pièces'])){
						if($_POST['LD_Pièces'] == 'F1' || $_POST['LD_Pièces'] == 'F2' || $_POST['LD_Pièces'] == 'F3' || $_POST['LD_Pièces'] == 'F6' || $_POST['LD_Pièces'] == 'FG'){
							echo '<h2>Aucun bien de ce type !</h2>';
						}
						else{
							switch($_POST['LD_Pièces']){
								case('F4'):
									$texte = 'Quatre pièces';
									$request = "SELECT idType, titreBien, idBien, prixBien FROM Biens WHERE idType = 'F4'";
									break;
								case('F5'):
									$texte = 'Cinq pièces';
									$request = "SELECT idType, titreBien, idBien, prixBien FROM Biens WHERE idType = 'F5'";
									break;
								case('F7'):
									$texte = 'Sept pièces';
									$request = "SELECT idType, titreBien, idBien, prixBien FROM Biens WHERE idType = 'F7'";
									break;
							}
							$result = $con->prepare($request);
							$result->execute();
							echo '
							<center><table border="2" style="text-align:center">
							<caption style="text-align:center">'.$texte.'</caption>
							<tr>
								<th>Identifiant</th><th>Titre</th><th>Prix</th><th>Type Bien</th>
							</tr>';
							foreach($result as $value) {
								echo '<tr>';
									echo '<td>'.$value['idType'].'</td>';
									echo '<td>'.$value['titreBien'].'</td>';
									echo '<td>'.$value['prixBien'].'</td>';
									echo '<td>'.$value['idBien'].'</td>';
								echo '</tr>';
							}
							echo '</table></center>';
					}
				}		
				?>
            </div>
        </div>
    </div>

	<?php include("./include/footer.php"); ?>
</body>
</html>