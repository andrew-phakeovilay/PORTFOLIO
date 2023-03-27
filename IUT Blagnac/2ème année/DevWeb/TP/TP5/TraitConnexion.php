<?php
    session_start();
    if(isset($_POST["valider"]) &&  isset($_POST["login"]) && isset($_POST["password"]) && $_POST["login"] == "Achille" && $_POST["password"] == "Talon"){
        $_SESSION["identifie"] = "OK";
        
        if(isset($_POST['souvenir'])){
            setcookie('cookIdent', htmlentities($_POST['login']), time()+182*24*60*60); // Jours*Heures*Minutes*Secondes
        }
        header('location: index.php'); // redirection de page
        exit();
    }
    else{
        header("location: FormConnexion.php?msgErreur=Tentative d'acces interdite");
        exit();
    }
?>