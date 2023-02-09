<?php
if (!isset($_GET)) {
    header('location:index.php');
} else {
    require('includes/connect.inc.php');
    error_reporting(0);
    $idProduit = $_GET['idProduit'];

    $reqProduit = "SELECT * FROM Produit WHERE IDPRODUIT = :pIDproduit";

    $produitInfos = oci_parse($connect, $reqProduit);

    oci_bind_by_name($produitInfos, ":pIDproduit", $idProduit);

    $resultInfosProduit = oci_execute($produitInfos);

    // si la requete n'a pas pu être executée, on affiche l'erreur
    if (!$resultInfosProduit) {
        $e = oci_error($resultVerif);  // on récupère l'exception liée au pb d'execution de la requete (violation PK par exemple)
        print htmlentities($e['message'] . ' pour cette requete : ' . $e['sqltext']);
    } else {
        $statementBD = oci_fetch_assoc($produitInfos);
        if (empty($statementBD)) {
            header('location:index.php?msgErreur=Produit existant mais aucune info trouvée');
        }

        $reqCategorie = "SELECT nomcat FROM Categorie WHERE idcategorie = :pIDcat";

        $idCategorie = $statementBD['IDCATEGORIE'];

        $categorieInfos = oci_parse($connect, $reqCategorie);

        oci_bind_by_name($categorieInfos, ":pIDcat", $idCategorie);

        $resultCategorie = oci_execute($categorieInfos);
        // si la requete n'a pas pu être executée, on affiche l'erreur
        if (!$resultInfosProduit) {
            $e = oci_error($resultVerif);  // on récupère l'exception liée au pb d'execution de la requete (violation PK par exemple)
            print htmlentities($e['message'] . ' pour cette requete : ' . $e['sqltext']);
        } else {
            $statementBD_CAT = oci_fetch_assoc($categorieInfos);
        }
    }
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
    <link rel="stylesheet" href="assets/css/product-page.css">

    <link rel="shortcut icon" href="./assets/images/bluegym.ico" type="image/x-icon">

</head>

<body>

    <?php include('includes/header.php'); ?>

    <div class="container">

        <!-- Première ligne image + présentation produit -->
        <div class="row">
            <!-- Left side product image displayer -->
            <div class="col-6">
                <div class="product-image-displayer">
                    <img src="./assets/images/product-img/<?php echo $_GET['idProduit']; ?>" alt="">
                </div>
                <div class="product-image-switcher">
                    <div class="button-group">
                        <button class="btn">
                            <img height="50px" src="./assets/images/product-img/<?php echo $_GET['idProduit']; ?>" alt="">
                        </button>
                        <button class="btn">
                            <img height="50px" src="./assets/images/product-img/<?php echo $_GET['idProduit']; ?>" alt="">
                        </button>
                    </div>
                </div>
            </div>
            <!-- Right side product infos section -->
            <div class="col-6 pl-5 pt-4">
                <div class="row">
                    <div class="col-9">
                        <h1 class="product-title">
                            <?php echo $statementBD['NOMP']; ?>
                        </h1>
                        <h3>
                            <?php echo $statementBD['PRIXPRODUIT'] . " €"; ?>
                        </h3>
                    </div>

                    <!-- Section notation -->
                    <div class="col-3">
                        <div class="rate-section">
                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-star-fill" viewBox="0 0 16 16">
                                <path d="M3.612 15.443c-.386.198-.824-.149-.746-.592l.83-4.73L.173 6.765c-.329-.314-.158-.888.283-.95l4.898-.696L7.538.792c.197-.39.73-.39.927 0l2.184 4.327 4.898.696c.441.062.612.636.282.95l-3.522 3.356.83 4.73c.078.443-.36.79-.746.592L8 13.187l-4.389 2.256z" />
                            </svg>
                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-star-fill" viewBox="0 0 16 16">
                                <path d="M3.612 15.443c-.386.198-.824-.149-.746-.592l.83-4.73L.173 6.765c-.329-.314-.158-.888.283-.95l4.898-.696L7.538.792c.197-.39.73-.39.927 0l2.184 4.327 4.898.696c.441.062.612.636.282.95l-3.522 3.356.83 4.73c.078.443-.36.79-.746.592L8 13.187l-4.389 2.256z" />
                            </svg>
                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-star-fill" viewBox="0 0 16 16">
                                <path d="M3.612 15.443c-.386.198-.824-.149-.746-.592l.83-4.73L.173 6.765c-.329-.314-.158-.888.283-.95l4.898-.696L7.538.792c.197-.39.73-.39.927 0l2.184 4.327 4.898.696c.441.062.612.636.282.95l-3.522 3.356.83 4.73c.078.443-.36.79-.746.592L8 13.187l-4.389 2.256z" />
                            </svg>
                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-star-fill" viewBox="0 0 16 16">
                                <path d="M3.612 15.443c-.386.198-.824-.149-.746-.592l.83-4.73L.173 6.765c-.329-.314-.158-.888.283-.95l4.898-.696L7.538.792c.197-.39.73-.39.927 0l2.184 4.327 4.898.696c.441.062.612.636.282.95l-3.522 3.356.83 4.73c.078.443-.36.79-.746.592L8 13.187l-4.389 2.256z" />
                            </svg>
                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-star" viewBox="0 0 16 16">
                                <path d="M2.866 14.85c-.078.444.36.791.746.593l4.39-2.256 4.389 2.256c.386.198.824-.149.746-.592l-.83-4.73 3.522-3.356c.33-.314.16-.888-.282-.95l-4.898-.696L8.465.792a.513.513 0 0 0-.927 0L5.354 5.12l-4.898.696c-.441.062-.612.636-.283.95l3.523 3.356-.83 4.73zm4.905-2.767-3.686 1.894.694-3.957a.565.565 0 0 0-.163-.505L1.71 6.745l4.052-.576a.525.525 0 0 0 .393-.288L8 2.223l1.847 3.658a.525.525 0 0 0 .393.288l4.052.575-2.906 2.77a.565.565 0 0 0-.163.506l.694 3.957-3.686-1.894a.503.503 0 0 0-.461 0z" />
                            </svg>
                        </div>
                    </div>
                </div>

                <!-- Section description -->
                <div class="row mt-5">
                    <div class="col-12">
                        <h5>Description rapide</h5>
                        <p class="product-description">
                            <?php
                            /*
                                la description rapide est constitue
                                des 150 premiers caracteres
                            */
                            if (strlen($statementBD['DESCRIPTION']) > 150) {
                                echo substr($statementBD['DESCRIPTION'], 0, 150) . "<a href=#description>...</a>";
                            } else {
                                echo $statementBD['DESCRIPTION'];
                            }
                            ?>
                        </p>
                    </div>
                </div>
                
                <!-- Section Taille -->
                <?php
                    if($statementBD['TAILLE']){
                    echo'<div class="row mt-5">
                        <div class="col-12">
                            <h5>Taille</h5>
                            <p class="product-description">
                            '.$statementBD['TAILLE'].'
                            </p>
                        </div>
                    </div>';   
                    }
                ?>
                
                <!-- Section actions sur le produit -->
                <div class="row mt-5">
                    <div class="col-12">
                        <!-- abandon de cette option pour l'instant -->
                        <!--<div class="dropdown">
                            <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
                                Choix des matériaux
                            </button>
                            <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
                                <li><a class="dropdown-item" href="#">Bois</a></li>
                                <li><a class="dropdown-item" href="#">Métal</a></li>
                                <li><a class="dropdown-item" href="#">Aluminium</a></li>
                                <li><a class="dropdown-item" href="#">Netherite</a></li>
                            </ul>
                        </div>-->
                        <p>
                            Composition :
                            <span class="fw-bold">
                                <?php echo $statementBD['COMPOSITION']; ?>
                            </span>
                        </p>

                        <!-- message de confirmation -->
                        <?php
                        if (isset($_GET['msgConfirm'])) {
                            echo '
                                <div class="alert alert-success" role="alert">
                                    ' . $_GET['msgConfirm'] . '
                                </div>
                                ';
                        };
                        ?>
                    </div>

                    <div class="col-12 d-flex justify-content-center mt-3">
                        <form id="formAjoutPanier" method="POST" action="addingToCart.php">
                            <div class="row">
                                <div class="col-6">
                                    <input type="number" class="form-control" name="quantiteSelectionne" min="1" max="10" value="1"><br>
                                </div>
                                <div class="col-6">
                                    <button class="btn btn-primary p-2" type="submit" name="sub" onclick="validateFormAddCart()">
                                        Ajouter au panier
                                    </button>
                                    <input type="hidden" name="idProduit" value="<?php echo $idProduit; ?>">
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <!-- Affichage des infos produit en détail -->
        <div class="row">

            <!-- Description produit -->
            <div class="col-12 mt-4" id='description'>
                <h2> Description </h2>
                <hr>
                <p>
                    <?php echo $statementBD['DESCRIPTION']; ?>
                </p>
            </div>

            <!-- Avantages produit -->
            <?php
            if ($statementBD['AVANTAGES']) {
                echo '<div class="col-12 mt-4">
                <h2>
                    Avantages
                </h2>
                <hr>
                <p>'.$statementBD["AVANTAGES"].'</p>
            </div>';
            }
            ?>
            <!-- Avis produit -->
            <div class="col-12 mt-4">
                <h2>
                    Avis
                </h2>
                <hr>
                <!-- Carte d'avis utilisateur -->
                <!-- avis utilisateur 1 -->
                <div class="card m-3">
                    <div class="card-body">
                        <div class="row">
                            <div class="col">
                                <div class="rate-section">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-star-fill" viewBox="0 0 16 16">
                                        <path d="M3.612 15.443c-.386.198-.824-.149-.746-.592l.83-4.73L.173 6.765c-.329-.314-.158-.888.283-.95l4.898-.696L7.538.792c.197-.39.73-.39.927 0l2.184 4.327 4.898.696c.441.062.612.636.282.95l-3.522 3.356.83 4.73c.078.443-.36.79-.746.592L8 13.187l-4.389 2.256z" />
                                    </svg>
                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-star-fill" viewBox="0 0 16 16">
                                        <path d="M3.612 15.443c-.386.198-.824-.149-.746-.592l.83-4.73L.173 6.765c-.329-.314-.158-.888.283-.95l4.898-.696L7.538.792c.197-.39.73-.39.927 0l2.184 4.327 4.898.696c.441.062.612.636.282.95l-3.522 3.356.83 4.73c.078.443-.36.79-.746.592L8 13.187l-4.389 2.256z" />
                                    </svg>
                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-star-fill" viewBox="0 0 16 16">
                                        <path d="M3.612 15.443c-.386.198-.824-.149-.746-.592l.83-4.73L.173 6.765c-.329-.314-.158-.888.283-.95l4.898-.696L7.538.792c.197-.39.73-.39.927 0l2.184 4.327 4.898.696c.441.062.612.636.282.95l-3.522 3.356.83 4.73c.078.443-.36.79-.746.592L8 13.187l-4.389 2.256z" />
                                    </svg>
                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-star-fill" viewBox="0 0 16 16">
                                        <path d="M3.612 15.443c-.386.198-.824-.149-.746-.592l.83-4.73L.173 6.765c-.329-.314-.158-.888.283-.95l4.898-.696L7.538.792c.197-.39.73-.39.927 0l2.184 4.327 4.898.696c.441.062.612.636.282.95l-3.522 3.356.83 4.73c.078.443-.36.79-.746.592L8 13.187l-4.389 2.256z" />
                                    </svg>
                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-star" viewBox="0 0 16 16">
                                        <path d="M2.866 14.85c-.078.444.36.791.746.593l4.39-2.256 4.389 2.256c.386.198.824-.149.746-.592l-.83-4.73 3.522-3.356c.33-.314.16-.888-.282-.95l-4.898-.696L8.465.792a.513.513 0 0 0-.927 0L5.354 5.12l-4.898.696c-.441.062-.612.636-.283.95l3.523 3.356-.83 4.73zm4.905-2.767-3.686 1.894.694-3.957a.565.565 0 0 0-.163-.505L1.71 6.745l4.052-.576a.525.525 0 0 0 .393-.288L8 2.223l1.847 3.658a.525.525 0 0 0 .393.288l4.052.575-2.906 2.77a.565.565 0 0 0-.163.506l.694 3.957-3.686-1.894a.503.503 0 0 0-.461 0z" />
                                    </svg>
                                </div>
                            </div>
                            <div class="col d-flex justify-content-end">
                                <small class="text-muted">le 07/08/10 par : Igor Carpetto</small>
                            </div>
                        </div>
                        <h4 class="mt-3">Titre de l'avis</h4>
                        <p>Oui oui baguette. </p>
                    </div>
                </div>
                <!-- avis utilisateur 2s -->
                <div class="card m-3">
                    <div class="card-body">
                        <div class="row">
                            <div class="col">
                                <div class="rate-section">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-star-fill" viewBox="0 0 16 16">
                                        <path d="M3.612 15.443c-.386.198-.824-.149-.746-.592l.83-4.73L.173 6.765c-.329-.314-.158-.888.283-.95l4.898-.696L7.538.792c.197-.39.73-.39.927 0l2.184 4.327 4.898.696c.441.062.612.636.282.95l-3.522 3.356.83 4.73c.078.443-.36.79-.746.592L8 13.187l-4.389 2.256z" />
                                    </svg>
                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-star-fill" viewBox="0 0 16 16">
                                        <path d="M3.612 15.443c-.386.198-.824-.149-.746-.592l.83-4.73L.173 6.765c-.329-.314-.158-.888.283-.95l4.898-.696L7.538.792c.197-.39.73-.39.927 0l2.184 4.327 4.898.696c.441.062.612.636.282.95l-3.522 3.356.83 4.73c.078.443-.36.79-.746.592L8 13.187l-4.389 2.256z" />
                                    </svg>
                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-star-fill" viewBox="0 0 16 16">
                                        <path d="M3.612 15.443c-.386.198-.824-.149-.746-.592l.83-4.73L.173 6.765c-.329-.314-.158-.888.283-.95l4.898-.696L7.538.792c.197-.39.73-.39.927 0l2.184 4.327 4.898.696c.441.062.612.636.282.95l-3.522 3.356.83 4.73c.078.443-.36.79-.746.592L8 13.187l-4.389 2.256z" />
                                    </svg>
                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-star" viewBox="0 0 16 16">
                                        <path d="M2.866 14.85c-.078.444.36.791.746.593l4.39-2.256 4.389 2.256c.386.198.824-.149.746-.592l-.83-4.73 3.522-3.356c.33-.314.16-.888-.282-.95l-4.898-.696L8.465.792a.513.513 0 0 0-.927 0L5.354 5.12l-4.898.696c-.441.062-.612.636-.283.95l3.523 3.356-.83 4.73zm4.905-2.767-3.686 1.894.694-3.957a.565.565 0 0 0-.163-.505L1.71 6.745l4.052-.576a.525.525 0 0 0 .393-.288L8 2.223l1.847 3.658a.525.525 0 0 0 .393.288l4.052.575-2.906 2.77a.565.565 0 0 0-.163.506l.694 3.957-3.686-1.894a.503.503 0 0 0-.461 0z" />
                                    </svg>
                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-star" viewBox="0 0 16 16">
                                        <path d="M2.866 14.85c-.078.444.36.791.746.593l4.39-2.256 4.389 2.256c.386.198.824-.149.746-.592l-.83-4.73 3.522-3.356c.33-.314.16-.888-.282-.95l-4.898-.696L8.465.792a.513.513 0 0 0-.927 0L5.354 5.12l-4.898.696c-.441.062-.612.636-.283.95l3.523 3.356-.83 4.73zm4.905-2.767-3.686 1.894.694-3.957a.565.565 0 0 0-.163-.505L1.71 6.745l4.052-.576a.525.525 0 0 0 .393-.288L8 2.223l1.847 3.658a.525.525 0 0 0 .393.288l4.052.575-2.906 2.77a.565.565 0 0 0-.163.506l.694 3.957-3.686-1.894a.503.503 0 0 0-.461 0z" />
                                    </svg>
                                </div>
                            </div>
                            <div class="col d-flex justify-content-end">
                                <small class="text-muted">le 07/08/10 par : Karim ZOuli</small>
                            </div>
                        </div>

                        <h4 class="mt-3">Avis un peu moins bien </h4>
                        <p>Oui oui baguette. </p>

                    </div>
                </div>
            </div>
        </div>

        <!-- Suggestion autres produits -->
        <div class="row">
            <div class="col-12 mt-4">
                <h2>
                    Produits qui pourraient vous plaire
                </h2>
                <hr>
            </div>
            <div class="col-12">
                <div class="card-displayer-suggestion">
                    <?php
                    // Suggère 4 produits ou moins similaires (la même catégorie)
                    $reqOthers = 'SELECT * FROM (SELECT * FROM PRODUIT WHERE idCategorie = :pIdCat AND idProduit != :pIdProduit ORDER BY dbms_random.value) WHERE rownum <= 4';
                    $cardsProductOthers = oci_parse($connect, $reqOthers);
                    oci_bind_by_name($cardsProductOthers, ":pIdCat", $statementBD['IDCATEGORIE']);
                    oci_bind_by_name($cardsProductOthers, ":pIdProduit", $idProduit);
                    $resultOther = oci_execute($cardsProductOthers);
                    while (($cardProductOther = oci_fetch_assoc($cardsProductOthers)) != false) {
                        echo '
                        <div class="product-card" style="transform: none;"><a href="product.php?idProduit=' . $cardProductOther["IDPRODUIT"] . '" style="color: inherit; text-decoration:none">
                            <div class="product-card-img">
                                <img src="https://contents.mediadecathlon.com/p2097113/k$6aec1f7948846ee1fd98ae4a58dd1fb0/sq/barre-de-traction-murale-compacte.jpg?format=auto&f=646x646" alt="">
                            </div>
                            <div class="product-card-content">
                                <h5 class="title">' . $cardProductOther['NOMP'] . '</h5>
                                <h5 class="price">' . $cardProductOther['PRIXPRODUIT'] . ' €</h5>
                            </div>
                        </a></div>
                            ';
                    }
                    oci_free_statement($categorieInfos);
                    oci_free_statement($produitInfos);
                    oci_free_statement($cardsProductOthers);
                    ?>
                </div>
            </div>
        </div>
    </div>


    <!-- Javascript -->
    <script type="text/javascript">
        /*
            Cette fonction verifie que l'utilisateur n'a pas mis
            une quantite valide en modifiant le code source
            */
        function validateFormAddCart() {
            let qteProduitUser = document.querySelector('input[name="quantiteSelectionne"]').value;
            let formAddCart = document.forms['formAddToCart'];
            if ((qteProduitUser > 10) || (qteProduitUser < 1)) {
                alert('Quantite incorrecte !');
            } else {
                formAddCart.submit();
            }
        }
    </script>

    <!-- <script src="includes/bootstrap/js/bootstrap.min.js"></script> -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
    <script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>

    <?php include('includes/footer.php'); ?>

</body>

</html>