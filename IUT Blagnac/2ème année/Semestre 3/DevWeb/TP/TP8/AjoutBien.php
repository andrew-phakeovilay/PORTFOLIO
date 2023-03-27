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
			<p>Veuillez entrer les informations du nouveau bien</p>
			<br>
			<form method='post' action='AjoutBien.php'>
				<fieldset>
					<div>
						Id Bien : <input type='text' name='idBien' value='B0011' required/><BR><BR>
						Titre du Bien : <input type='text' name='titreBien' value='Villa Palace' required/><BR><BR>
						Détail du Bien : <input type='text' name='detailBien' value='Superbe palace en bord de mer' required/><BR><BR>
						Prix du Bien : <input type='text' name='prixBien' value='350000' required/><BR><BR>
						Id Type : <input type='text' name='idType' value='F6' required/><BR><BR>
					</div>
					<div>
						<input type='submit' name='valider' value='Valider'/><BR><BR>
					</div>
				</fieldset>
			</form><BR><BR>
				<?php
				@$idBien=$_POST['idBien'];
				@$idType=$_POST['idType'];
				@$titreBien=$_POST['titreBien'];
				@$detailBien=$_POST['detailBien'];
				@$prixBien=$_POST['prixBien'];
				@$valider=$_POST["valider"];

				if(isset($valider)){
					require_once('./include/connect.inc.php');
					$request = 'INSERT INTO Biens VALUES(:idBien, :idType, :titreBien, :detailBien, :prixBien)';
					$result = $con->prepare($request);
					$result->execute(['idBien' => $idBien, 'idType' => $idType, 'titreBien' => $titreBien, 'detailBien' => $detailBien, 'prixBien' => $prixBien]);
					header("refresh:5;url=ConsultPrix.php");
				}
				?>
            </div>
        </div>
    </div>
	<?php include("./include/footer.php"); ?>
</body>
</html>