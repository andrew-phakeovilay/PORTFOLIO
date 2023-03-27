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
			<form method='post' action='AjoutBien.php' enctype="multipart/form-data">
				<fieldset>
					<div>
						Id Bien : <input type='text' name='idBien' value='B0011' required/><BR><BR>
						Titre du Bien : <input type='text' name='titreBien' value='Villa Palace' required/><BR><BR>
						Détail du Bien : <input type='text' name='detailBien' value='Superbe palace en bord de mer' required/><BR><BR>
						Prix du Bien : <input type='text' name='prixBien' value='350000' required/><BR><BR>
						Type du Bien : <select name='LD_Pièces'>
						<?php
							require_once("include/connect.inc.php");
							$requestListe = $con->prepare("SELECT nomType, idType FROM Types");
							$requestListe->execute();
							foreach($requestListe as $ligneListe){
									echo "<option  value='".$ligneListe['idType']."'>".$ligneListe['nomType']."</option>";
							}
							$requestListe->closeCursor();
						?>
						</select>
					<BR><BR>
					<input type="file" name="monfichier" accept="image/jpeg">
	  				<BR><BR>
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

				if(isset($valider) && !empty($_FILES['monfichier'] && $_FILES['monfichier']['error'] == 0) && isset($_POST['LD_Pièces'])){
					if (preg_match("#^B[0]{1}[0-9]{3}#",$idBien)){
						require_once('./include/connect.inc.php');
						$request = 'INSERT INTO Biens VALUES(:idBien, :idType, :titreBien, :detailBien, :prixBien)';
						$result = $con->prepare($request);
						$result->execute(['idBien' => $idBien, 'idType' => $_POST['LD_Pièces'], 'titreBien' => $titreBien, 'detailBien' => $detailBien, 'prixBien' => $prixBien]);
						
						$infosfichier = pathinfo($_FILES['monfichier']['name']);
						if($infosfichier['extension'] == 'jpg'){
							$nomImg = htmlentities($_POST['idBien']).".jpg";
							move_uploaded_file($_FILES['monfichier']['tmp_name'],'images/'.$nomImg);
						}

						header("refresh:2;url=ConsultPrix.php");
					}
				}
				?>
            </div>
        </div>
    </div>
	<?php include("./include/footer.php"); ?>
</body>
</html>