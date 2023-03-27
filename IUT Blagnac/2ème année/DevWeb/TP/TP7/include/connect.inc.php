<?php
try{
 $user = 'mysql2023';
 $pass = 'EfJxi6KeD253';
 
  
 $conn = new PDO('mysql:host=localhost;dbname=mysql2023;charset=UTF8'  
				,$user, $pass, [PDO::ATTR_ERRMODE =>PDO::ERRMODE_EXCEPTION]);
}
catch (PDOException $e){
  echo "Erreur: ".$e->getMessage()."<br/>";
  die() ;
}
?>
