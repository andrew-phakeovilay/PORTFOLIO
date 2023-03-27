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
								if ($_POST['LD_Pièces'] == $ligneListe['idType']){
									echo "<option  value='".$ligneListe['idType']."' selected = 'true'>".$ligneListe['nomType']."</option>";
								}
								else{
									echo "<option  value='".$ligneListe['idType']."'>".$ligneListe['nomType']."</option>";
								}
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
			if (!empty($_POST['Afficher']) and !empty($_POST['LD_Pièces'])) {
				$req2 = $con->prepare("SELECT idBien,	titreBien,	prixBien,	idType FROM Biens WHERE idType = ?");
				$req2->execute(array($_POST['LD_Pièces']));
				$i=false;
				foreach ($req2 as $ligne) {
					echo '<center><table border="2" style="text-align:center">';
					echo '<caption style="text-align:center">Villa par tranche de prix</caption><tr>';
					echo '<th>Identifiant</th>';
					echo '<th>Titre</th>';
					echo '<th>Prix</th>';
					echo '<th>Type bien</th>';
					echo '</tr>';
					$i=true;
					break;
				}
				$req2->closeCursor();
				$req2 = $con->prepare("SELECT idBien, titreBien, prixBien, idType FROM Biens WHERE idType = ?");
				$req2->execute(array($_POST['LD_Pièces']));
				foreach ($req2 as $ligne) {
					echo '<tr>';
					echo '<td>'.$ligne["idBien"].'</td>';
					echo '<td>'.$ligne["titreBien"].'</td>';
					echo '<td>'.$ligne["prixBien"].'</td>';
					echo '<td>'.$ligne["idType"].'</td>';
					echo '</tr>';
				}
				$req2->closeCursor();
				if(!$i){
				echo '<h2>Aucun bien de ce type !</h2>';
				}else{
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