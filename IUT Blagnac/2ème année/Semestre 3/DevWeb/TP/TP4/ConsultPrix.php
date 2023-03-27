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
			<h1>Consulter les biens par tranche de prix</h1>
			<br><br>
			<form method='post' action='ConsultPrix.php'>
				<fieldset>
					<div>
						<input type='radio' name='BR_Prix' value='moins200k' /> < 200 000€<BR><BR>
						<input type='radio' name='BR_Prix' value='entre200k300k'/> de 200 000 € à 300 000 €<BR><BR>
						<input type='radio' name='BR_Prix' value='plus300k'/> > 300 000 €<BR><BR>
					</div>
					<div>
						<input type='submit' name='Afficher' value='Afficher'/><BR><BR>
					</div>
				</fieldset>
			</form><BR><BR>
				<?php
					// ConsultPrix.php
					$tabType = ["F1"=> "Une pièce",
					"F2"=> "Deux pièces",
					"F3"=> "Trois pièces",
					"F4"=> "Quatre pièces",
					"F5"=> "Cinq pièces",
					"F6"=> "Six pièces",
					"F7"=> "Sept pièces",
					"FG"=> "Plus de 7 pièces"
					];
					
					$tabBien[0] = 	[			'idBien' => 'B0001',
								'titreBien' => 'Villa Rieumes',
								'detailBien' => 'Jolie maison de plain-pied',
								'prixBien' => 270000,
								'idType' => 'F7'
					];	
							
					$tabBien[1] = 	[			'idBien' => 'B0002',
								'titreBien' => 'Villa Garidech',
								'detailBien' => 'Villa de plain-pied, cuisine équipée',
								'prixBien' => 320000,
								'idType' => 'F5'
					];		
							
					$tabBien[2] =	[			'idBien' => 'B0003',
								'titreBien' => 'Villa Plaisance',
								'detailBien' => 'Belle maison de caractère',
								'prixBien' => 250000,
								'idType' => 'F5'
					];	
							
					$tabBien[3] = [			'idBien' => 'B0004',
								'titreBien' => 'Villa Beaumont',
								'detailBien' => 'Ferme authentique',
								'prixBien' => 175000,
								'idType' => 'F4'
							];			
							
					$tabBien[4] = 	[			'idBien' => 'B0005',
								'titreBien' => 'Villa Auterive',
								'detailBien' => 'Villa neuve avec terrain',
								'prixBien' => 215000,
								'idType' => 'F4'
					];	
							
							
					$tabBien[5] = 	[			'idBien' => 'B0006',
								'titreBien' => 'Villa St Rustice',
								'detailBien' => 'Maison ancienne',
								'prixBien' => 245000,
								'idType' => 'F4'
					];		
							
					$tabBien[6] = 	[			'idBien' => 'B0007',
								'titreBien' => 'Villa L’Union',
								'detailBien' => 'Charmante maison de village',
								'prixBien' => 195000,
								'idType' => 'F4'
					];		
							
					$tabBien[7] = 	[			'idBien' => 'B0008',
								'titreBien' => 'Villa Léguevin',
								'detailBien' => 'Maison avec beaux volumes',
								'prixBien' => 250000,
								'idType' => 'F5'
					];		
						
					$tabBien[8] = 	[			'idBien' => 'B0009',
								'titreBien' => 'Villa Bessieres',
								'detailBien' => 'Agréable maison de plain pied',
								'prixBien' => 275000,
								'idType' => 'F5'
					];			
							
					$tabBien[9] =	[			'idBien' => 'B0010',
								'titreBien' => 'Villa St-Lys',
								'detailBien' => 'Belle maison d’architecte',
								'prixBien' => 245000,
								'idType' => 'F4'
					];
					IF(isset($_POST['Afficher']) && isset($_POST['BR_Prix'])){
						$tabAfficher = [];
						$texte;
						switch($_POST['BR_Prix']){
							case('moins200k'):
								$texte = 'Biens de prix inférieur à 200 000 €';
								foreach($tabBien as $cle => $val){
									foreach($val as $sousCle => $sousVal) {
										if($sousCle =='prixBien' && $sousVal < 200000){
											array_push($tabAfficher, $tabBien[$cle]);
										}
									}
								}
								break;
							case('entre200k300k'):
								$texte = 'Biens de prix compris entre 200 000 € et 300 000 €';
								foreach($tabBien as $cle => $val){
									foreach($val as $sousCle => $sousVal) {
										if($sousCle =='prixBien' && $sousVal >= 200000 && $sousVal <=300000){
											array_push($tabAfficher, $tabBien[$cle]);
										}
									}
								}
								break;
							case('plus300k'):
								$texte = 'Biens de prix supérieur à 300 000 €';
								foreach($tabBien as $cle => $val){
									foreach($val as $sousCle => $sousVal) {
										if($sousCle =='prixBien' && $sousVal > 300000){
											array_push($tabAfficher, $tabBien[$cle]);
										}
									}
								}
								break;
						}
						echo '
						<center><table border="2" style="text-align:center">
						<caption style="text-align:center">'.$texte.'</caption>
						<tr>
							<th>Identifiant</th><th>Titre</th><th>Prix</th><th>Type Bien</th>
						</tr>';
						foreach($tabAfficher as $key => $value) {
							echo '<tr>';
							foreach($value as $subKey => $subValue) {
								if($subKey == 'detailBien'){
									continue;
								}
								echo '<td>'.$subValue.'</td>';
							}
							echo '</tr>';
						}
						echo '</table></center>';
					}
				?>
            </div>
        </div>
    </div>
	<?php include("./include/footer.php"); ?>
</body>
</html>