<!-- <p>onglet livraison</p>
<p class="text-info">Zone de debeuggage :</p>
<p class="text-warning"> ID de panier = <?php // echo $_GET['cartID']; ?></p> -->

<div class="col-9">
    <div class="card">
        <div class="card-body">
            <form action="shipping-process.php?tab=shipping&cartID=<?php echo $_GET['cartID'];?>" method="POST">
                <div>
                    <h5 class="fw-bold mt-4">Informations de livraison</h5>
                </div>
                <div class="mb-3">
                    <label for="shippingAdress" class="form-label">Adresse complète</label>
                    <input type="text" class="form-control" placeholder="1 place G. Brassens" value="1 place G. Brassens" id="shippingAdress" name="shippingAdress" required> 
                </div>
                <div class="row mb-3">
                    <div class="col-6">
                        <label for="shippingPostal" class="form-label">Code Postal</label>
                        <input type="text" class="form-control" placeholder="31300" value="31300" id="shippingPostal" name="shippingPostal" required>
                    </div>
                    <div class="col-6">
                        <label for="shippingCity" class="form-label">Ville</label>
                        <input type="text" class="form-control" placeholder="Toulouse" value="Toulouse" id="shippingCity" name="shippingCity" required>
                    </div>
                </div>
                <div>
                    <h5 class="fw-bold mt-4">Sélection de la livraison</h5>
                </div>
                <div class="mb-3">
                    <div class="form-check mb-4">
                        <input class="form-check-input" type="radio" name="radioShipping" id="flexRadioDefault1" value="pointRelais">
                        <div class="card">
                            <div class="card-body">
                                <label class="form-check-label" for="flexRadioDefault1" style="width: 100%;">
                                    <div class="row">
                                        <div class="col-9">
                                            <div class="row">
                                                <div class="col-12">
                                                    <h6 class="fw-bold text-uppercase">Livraison en point relais</h6>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="col-12">
                                                    <p class="text-muted">Faites livrer votre colis dans le point relais le plus proche de chez vous. </p>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-2 d-flex align-items-center">
                                            <h3 class="fw-bold">9.99 €</h3>
                                        </div>
                                        <div class="col-1 d-flex align-items-center">
                                            <img src="https://external-content.duckduckgo.com/iu/?u=http%3A%2F%2Fimg.e-marketing.fr%2FImages%2FBreves%2Fbreve36743-0.JPG&f=1&nofb=1&ipt=f71e613c568d29c656e57d7373560602a5fbef2a0118acdbb2cf2f9ae6393e9c&ipo=images" 
                                            width="50px" alt="" class="img-fluid">
                                        </div>
                                    </div>
                                </label>
                            </div>
                        </div>
                    </div>
                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="radioShipping" id="flexRadioDefault2" value="domicile" checked>
                        <div class="card">
                            <div class="card-body">
                                <label class="form-check-label" for="flexRadioDefault2" style="width: 100%;">
                                    <div class="row">
                                        <div class="col-9">
                                            <div class="row">
                                                <div class="col-12">
                                                    <h6 class="fw-bold text-uppercase">Livraison à domicile</h6>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="col-12">
                                                    <p class="text-muted">Nous livrons le colis directement chez vous via La Poste. </p>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-2 d-flex align-items-center">
                                            <h3 class="fw-bold">12.99 €</h3>
                                        </div>
                                        <div class="col-1 d-flex align-items-center">
                                            <img src="https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fwww.saintry-sur-seine.fr%2Fwp-content%2Fuploads%2FLogo-laposte.png&f=1&nofb=1&ipt=5b2936626593e4beb4f7db941008b8c378a6fcc05a11459ba7225d058a3533cf&ipo=images" alt="" class="img-fluid">
                                        </div>
                                    </div>
                                </label>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="mb-3 mt-3 d-flex justify-content-end">
                    <div class="row">
                        <div class="col-12 d-flex justify-content-end">
                            <button class="btn btn-success" type="submit">Etape suivant : Informations de paiement</button>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

<!-- Récapitulatif de commadne -->
<div class="col-3">
    <div class="card">
        <div class="card-body">
            <div>
                <h5>Récapitulatif</h5>
                <hr>
                <div class="text-start ml-5">
                    <?php
                    // Mise à 0 du compteur de total
                    $prixTotalPanier = 0;
                    // Affichage récurrents des produits du panier
                    while (($product = oci_fetch_assoc($productsCartUser)) != false) {

                        // Mise à jour du prix total du panier
                        $prixTotalPanier = $prixTotalPanier + $product['PRIXQTEPRODUIT'];

                        echo '<span class="pt-1">' . $product['NOMP'] . ' <br> x' . $product['NBPRODUIT'] . ' - <span class="fw-bold">' . $product['PRIXQTEPRODUIT'] . ' € </span> <br> </span>';
                    }
                    ?>
                </div>
            </div>
            <hr>
            <h5>Total : <span class="fw-bold"> <?php echo $prixTotalPanier;?> €</span> <span class="text-muted fs-6">TTC</span></h5>
        </div>
        <div class="card-footer d-flex justify-content-center">
            <img width="170" src="https://cdn.shopify.com/s/files/1/0318/5718/0809/t/2/assets/gateways-cart.png?v=fd3b6526858486989ac0" alt="">
        </div>
    </div>
</div>