<?php
try{
    $con = new PDO('mysql:host=localhost;dbname=mysql2023;charset-UTF8', 'mysql2023', 'EfJxi6KeD253', array(PDO::ATTR_ERRMODE => PDO::ERRMODE_EXCEPTION));
}catch(PDOException $e){
    echo "Erreur : ".$e->getMessage()."";
    die();
}
?>