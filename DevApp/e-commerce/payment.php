<?php
session_start();

// si il n'y a pas d'onglets ni de panier, on redirige vers le panier
if (!isset($_GET)) {
    header('location:cart.php');
}
//  si l'utilisateur n'est pas loggé, il n'a pas le droit de venir sur cette page, il est redirigé
if (!isset($_SESSION['idClientIdentifie'])) {
    header('location:index.php');
}

// si il y avait des paramètres lors de la consultation de cette page, alors l'utilisateur venait du cart légitimement, on l'envoi vers la page de connexion
if (!isset($_SESSION['idClientIdentifie']) AND (isset($_GET['cartID']))) {
    header('location:formLogin.php?cartID='.$_GET['cartID']);
}
?>

<!DOCTYPE html>
<html lang="fr">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>BlueGym - dev</title>
    <link rel="stylesheet" href="includes/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="assets/css/main.css">

</head>

<body>


    <!-- Page d'achat -->
    <!-- Fonctionnement :
    étape 1 -> vérification de la connexion de l'utilisateur
    étape 2 -> saisie des informations de livraison
    étapes 3 -> saisie des informations de paiement

    paramètres de la page :
        étapes actuelle (connection/livraison/paiement)
        id du panier de la commande (cartId)

     -->

    <?php

    require_once("./includes/connect.inc.php");

    // Récupération des produits dans le panier de l'utilisateur, avec l'ID de panier passé en paramètre
    // on récupère le nom du produit via une jointure, ainsi que sa quantité et son prix
    $productsCartUser = oci_parse($connect, '
SELECT P.NOMP, P.IDPRODUIT, P.PRIXPRODUIT, Q.NBPRODUIT, Q.PRIXQTEPRODUIT FROM QUANTITEPANIER Q, PRODUIT P 
WHERE Q.IDPANIER = :idPanier
AND Q.IDPRODUIT = P.IDPRODUIT
');
    oci_bind_by_name($productsCartUser, ':idPanier', $_GET['cartID']);
    oci_execute($productsCartUser);

    // echo print_r($product); //debuggage

    // On pourra utiliser la liste des produits partout dans le process de paiement. 

    ?>

    <style>
        .progresses {
            display: flex;
            align-items: center;
        }

        .line {

            width: 120px;
            height: 6px;
            background: #63d19e;
        }

        .line-empty {
            background: #cccccc;
        }


        .steps {

            display: flex;
            background-color: #63d19e;
            color: #fff;
            font-size: 14px;
            width: 40px;
            height: 40px;
            align-items: center;
            justify-content: center;
            border-radius: 50%;

        }

        .step-empty {
            background-color: #cccccc;
        }
    </style>

    <?php include('includes/header.php'); ?>

    <div class="container">

        <!-- Progressbar -->
        <div class="container mb-5 mt-5 d-flex justify-content-center align-items-center">
            <div class="progresses">
                <div class="steps">
                    <span>
                        <svg xmlns="http://www.w3.org/2000/svg" width="25" height="25" fill="currentColor" class="bi bi-check" viewBox="0 0 16 16">
                            <path d="M10.97 4.97a.75.75 0 0 1 1.07 1.05l-3.99 4.99a.75.75 0 0 1-1.08.02L4.324 8.384a.75.75 0 1 1 1.06-1.06l2.094 2.093 3.473-4.425a.267.267 0 0 1 .02-.022z" />
                        </svg>
                    </span>
                </div>
                <!-- Affichage de la ligne sur le fil d'arianne si l'étape correspond -->
                <?php if (($_GET['tab'] == "bill")) {
                    echo '
                    <span class="line"></span>
                    <div class="steps">
                        <span class="fw-bold">2</span>
                    </div>
                    <span class="line line-empty"></span>
                <div class="steps step-empty">
                    <span class="fw-bold">3</span>
                </div>
                    ';
                } else if (($_GET['tab'] == "checkout")){
                    echo '
                    <span class="line"></span>
                    <div class="steps">
                        <span class="fw-bold">2</span>
                    </div>
                    <span class="line"></span>
                    <div class="steps">
                        <span class="fw-bold">3</span>
                    </div>';
                }
                else {
                    echo '
                    <span class="line line-empty"></span>
                    <div class="steps step-empty">
                        <span class="fw-bold">2</span>
                    </div>
                    <span class="line line-empty"></span>
                    <div class="steps step-empty">
                        <span class="fw-bold">3</span>
                    </div>';
                }; ?>


            </div>
        </div>

        <!-- Bannière de titre -->
        <div class="row mb-4">
            <div class="col-12 text-center">
                <h1 class="fw-bold">Votre commande</h1>
                <h3 class="text-muted">
                    <!-- Titre de la section en fonction de l'onglet -->
                    <?php if ($_GET['tab'] == "shipping") {
                        echo 'Informations de livraison';
                    }; ?>
                    <?php if ($_GET['tab'] == "bill") {
                        echo 'Informations de paiement';
                    }; ?>
                    <?php if ($_GET['tab'] == "checkout") {
                        echo 'Confirmation de commande';
                    }; ?>
                </h3>
            </div>
        </div>

        <!-- Contenu principal -->
        <div class="row mb-4">
            <!-- Sélection du contenu en fonction du paramètre d'URL -->
            <?php
            if ($_GET['tab'] == "shipping") {
                include('./includes/order-includes/order-shipping.php');
            } else if ($_GET['tab'] == "bill") {
                include('./includes/order-includes/order-bill.php');
            } else if ($_GET['tab'] == "checkout") {
                include('./includes/order-includes/order-checkout.php');
            } else {
                echo 'ya un problème';
            }
            ?>
        </div>


    </div>






    <?php include('includes/footer.php'); ?>
</body>

</html>