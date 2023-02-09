<!DOCTYPE html>
<html lang="en">

<?php
    session_start();

    require_once("../includes/connect.inc.php");
    error_reporting(0);
    
    if (isset($_SESSION['idClientIdentifie'])) {
        $reqAdm = "SELECT * FROM CLIENT WHERE IDCLIENT= :pIdClie";
        $prepAdm = oci_parse($connect, $reqAdm);
        oci_bind_by_name($prepAdm, ":pIdClie", $_SESSION['idClientIdentifie']);
        $gotAdmin = oci_execute($prepAdm);

        while (($getInfos = oci_fetch_assoc($prepAdm)) != false) {
            $adm=$getInfos["ADMIN"];
        }

        if($adm == null){
            header('location:../index.php');
        }
    } 
    else {
        header('location:../index.php');
    }

?>

<?php
    if(isset($_POST["ajouter"])) {
        
        $reqAdm = "UPDATE CLIENT SET admin='administrateur' WHERE IDCLIENT= :idCl";
        $prepAdm = oci_parse($connect, $reqAdm);
        oci_bind_by_name($prepAdm, ":idCl", $_POST['idclient']);
        $sendAdm = oci_execute($prepAdm);

        oci_commit($connect);

        if(!$sendAdm){
            header("location:./formAjoutAdmin.php?Erreur=Erreur lors de la mise à jour de l'utilisateur");
        } else {
            header("location:./formAjoutAdmin.php?Succes=L'utilisateur est maintenant un administrateur ! ");
        }
    }
?>

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>BlueGym - dev</title>

    <link rel="stylesheet" href="../includes/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="../assets/css/main.css">

    <link rel="shortcut icon" href="../assets/images/bluegym.ico" type="image/x-icon">

</head>


<body>
    <div class="header bg-dark text-white">
        </br>
        <div style="margin: 50px">
            <h1> Promouvoir un utilisateur au rang d'administrateur</h1>
        </div>
        </br>
    </div>

    <div style="margin: 50px">

        <?php
            if(isset($_GET["Erreur"])){
                echo '<div class="alert alert-warning" role="alert">';
                    echo $_GET['Erreur'];
                echo '</div>';
            }
            if(isset($_GET["Succes"])){
                echo '<div class="alert alert-success" role="alert">';
                    echo $_GET['Succes'];
                echo '</div>';
            }
        ?>

        <a href="./admin.php">Retour aux actions administrateur</a></br>

        </br>
        </br>
        
        <?php
            $reqCli = "SELECT * FROM CLIENT WHERE admin IS null ORDER BY IDCLIENT ASC";
            $prepCli = oci_parse($connect, $reqCli);
            $sendCli = oci_execute($prepCli);
            while (($nomC = oci_fetch_assoc($prepCli)) != false) {

                echo '<div class="col-12">
                    <div class="card mb-3">
                        <div class="row g-0">
                            <div class="col-md-9 pl-3">
                                <div class="card-body text-start">
                                    <p style="color: inherit; text-decoration:none"><h4 class="card-title fw-bold">' . $nomC["NOMC"] .' '. $nomC["PRENOMC"]. '</h4></p>
                                    <p class="card-text">Identifiant : ' . $nomC["IDCLIENT"] . '</p>
                                    <form method="POST">
                                        <button class="btn btn-success" type="submit" name="ajouter" >
                                            Promouvoir au rang d\'administrateur
                                        </button>
                                        <input type="hidden" name="idclient" value="' . $nomC['IDCLIENT'] . '">
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>';
            }
            
        ?>


    </div>
         
    
    


</body>
</html>