<!-- Récapitulatif de commadne -->
<div class="col-12">
    <div class="card mb-3">
        <div class="card-body">
            <?php
            if ($_GET['message'] == 'success') {
                echo '<h4 class="text-success fw-bold">Commande confirmée !</h4>';
            }
            ?>

            <hr>

            <p>Vous recevrez votre colis d'ici 4 jours ouvrés.</p>


            <p>Votre commande porte le numéro : <?php echo $_GET['commandID']; ?></p>

            <a href="./profile.php?tab=orders" class="btn btn-primary">Voir toutes mes commandes</a>

            <p class="text-muted fst-italic mt-4">Toute l'équipe Bluegym vous remercie pour votre commande. Les retours sont gratuits jusqu'à 30 jours après votre commande. Le SAV Bluegym reste à votre disposition
                pour toute aide concernant l'utilisation/le montage de votre matériel.
            </p>

        </div>
    </div>
    <div class="card">
        <div class="card-body">
            <div>
                <h5>Récapitulatif de votre commande</h5>

                <hr>
                <div class="text-start ml-5">
                    <?php
                    // Mise à 0 du compteur de total
                    $prixTotalPanier = 0;
                    // Affichage récurrents des produits du panier
                    while (($product = oci_fetch_assoc($productsCartUser)) != false) {

                        // Mise à jour du prix total du panier
                        $prixTotalPanier = $prixTotalPanier + $product['PRIXQTEPRODUIT'];

                        //echo $product["IDPRODUIT"];

                        // echo '<span>' . $product['NOMP'] . ' - x' . $product['NBPRODUIT'] . ' - <span class="fw-bold">' . $product['PRIXQTEPRODUIT'] . ' € </span> <br> </span>';

                        echo '
                        <div class="card border-0">
                            <div class="card-body">
                                <div class="row">
                                    <div class="col-2">
                                        <div style="width: auto; height: 100%; overflow: hidden;">
                                            <img src="./assets/images/product-img/' . $product["IDPRODUIT"] .  '.png" style="width: 100%; height: auto;">
                                        </div>
                                    </div>
                                    <div class="col-10 d-flex align-items-center">
                                        <div class="row">
                                            <h5 class="fw-semibold">
                                            ' . $product['NOMP'] . ' - <span class="text-muted fw-light">x' . $product['NBPRODUIT'] . '</span>
                                            </h5>
                                            <br>
                                            <h4>
                                            ' . $product['PRIXQTEPRODUIT'] . '€
                                            </h4>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <hr>
                                </div>
                            </div>
                        </div>
                        
                        ';
                    }
                    ?>
                    
                </div>
            </div>
            <hr>
            <h5>Total : <span class="fw-bold"> <?php echo $prixTotalPanier; ?> €</span> <span class="text-muted fs-6">TTC</span></h5>
        </div>
        <div class="card-footer d-flex justify-content-center">
            <img width="170" src="https://cdn.shopify.com/s/files/1/0318/5718/0809/t/2/assets/gateways-cart.png?v=fd3b6526858486989ac0" alt="">
        </div>
    </div>
    <div class="row m-2">
        <div class="col-12 d-flex justify-content-end">
            <a href="./index.php" class="btn btn-success">Retour à l' accueil</a>
        </div>
    </div>
</div>