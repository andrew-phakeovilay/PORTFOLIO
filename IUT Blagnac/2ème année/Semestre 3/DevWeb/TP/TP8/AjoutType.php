<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8"/>
    <link rel="stylesheet" href="include/bootstrap.min.css">
    <link rel="stylesheet" href="include/styles.css">
	<title>Mon site en PHP!</title>
</head>
<body>
	 
	<?php include("./include/header.php"); ?>

    <?php include("./include/menus.php"); ?>
		
	<?php require_once("./include/connect.inc.php"); ?>
	
    <div style="padding-top: 30px" id="main">
        <div style="text-align: center" class="col-md-12">
				
				<?php 
				@$idtype=$_POST["idtype"];
				@$nomtype=$_POST["nomtype"];
				@$valider=$_POST["valider"];
				$message  = "";
				
				if(isset($valider)){
					if (preg_match("#^F([1-8]|[A-Z]*)#",$idtype)){
						if (preg_match("#[a-zA-Z ]{6,25}#",$nomtype)){
							
							$requestSQL = $con->prepare("INSERT INTO Types(idType, nomType) VALUES( :idtype, :nomtype)");
						 
							$requestSQL->execute(['idtype' => $idtype, 'nomtype' => $nomtype]);
							
							header("location: ConsultType.php");
							}
							else{
								$message = "<li style='color:red'>Chaîne invalide</li>";
							}							
					}
					else{
						$message = "<li style='color:red'>Chaîne invalide</li>";
					}
				}	
				?>
				
					
                <h1>Veuillez entrer les informations du nouveau type de biens :</h1>
				<BR><BR>
				<form method="post" action = "AjoutType.php">
					<fieldset>
						Id Type : <input type="idtype" name="idtype" value="FD">	<BR><BR>
						Nom du Type : <input type="nomtype" name="nomtype" value="Duplex"><BR><BR>
						<input type="submit" name="valider" value="Valider">
					</fieldset>
				</form>
				<?php echo $message ?>

            </div>
        </div>

	<?php include("./include/footer.php"); ?>
</body>
</html>