<!-- Liste des produits -->

<!-- <p>onglet informations de paiement</p> -->

<div class="col-9">
    <div class="card">
        <div class="card-body">

            <!-- ZONE DE LA CARTE BANCAIRE VIRTUELLE hummmm trop sexy -->

            <div class="credit-card">
                <div class="container card-container d-flex align-items-center">
                    <div class="row">
                        <div class="row mb-3 mt-3">
                            <div class="col-10">
                                <p class="carte-numero" id="card-snippet-number">2314 2718 4342 0901</p>
                            </div>
                            <div class="col-2">
                                <img src="https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fwww.pikpng.com%2Fpngl%2Fb%2F81-810129_visa-la-perle-visa-card-logo-white-clipart.png&f=1&nofb=1&ipt=5242ff9961e9cda559f550597da6a71d1c58af0ff2df6034e5ffb8340c3af4bb&ipo=images" alt="" class="img-fluid">
                            </div>
                        </div>
                        <div class="row mt-4">
                            <div class="col-6">
                                <p class="carte-titulaire"><span id="card-snippet-name">Patrick</span> <span id="card-snippet-surname">Balkany</span></p>
                            </div>
                            <div class="col-6 d-flex justify-content-end">
                                <div>
                                    <p class="carte-expiration-titre">expiration</p>
                                    <p class="carte-expiration" id="card-snippet-expiration">XX / XX</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <form action="purchaseOrder.php" method="POST">

                <!-- On remet les données récupérées de l'URL pour pouvoir les exloiter dans le traitement du formulaire -->
                <input type="text" name="shippingAdress" value="<?php echo $_GET['shippingAdress']; ?>" hidden>
                <input type="text" name="shippingPostal" value="<?php echo $_GET['shippingPostal']; ?>" hidden>
                <input type="text" name="shippingCity" value="<?php echo $_GET['shippingCity']; ?>" hidden>
                <input type="text" name="shippingType" value="<?php echo $_GET['shippingType']; ?>" hidden>
                <input type="text" name="cartID" value="<?php echo $_GET['cartID']; ?>" hidden>

                <div>
                    <h5 class="fw-bold mt-4">Titulaire de la carte bancaire</h5>
                </div>

                <div class="mb-3">
                    <div class="row">
                        <div class="col-6">
                            <label for="" class="form-label">Prénom</label>
                            <input type="text" class="form-control" placeholder="Patrick" value="Patrick" name="cardName" id="card-name">
                            <div class="form-text">Prénom du titulaire de la carte.</div>
                        </div>
                        <div class="col-6">
                            <label for="" class="form-label">Nom</label>
                            <input type="text" class="form-control" placeholder="Balkany" value="Balkany" name="cardSurname" id="card-surname">
                            <div class="form-text">Nom du titulaire de la carte.</div>
                        </div>
                    </div>
                </div>

                <div>
                    <h5 class="fw-bold mt-4">Informations de la carte bancaire</h5>
                </div>

                <div class="mb-3">
                    <label for="" class="form-label">Numéro de carte</label>
                    <input type="text" class="form-control" id="input-credit-card" placeholder="XXXX - XXXX - XXXX - XXXX" name="cardNumber" value="2314 2718 4342 0901">
                </div>

                <div class="mb-3">
                    <div class="row">
                        <div class="col-6">
                            <label for="" class="form-label">CVV</label>
                            <input type="text" class="form-control" placeholder="123" value="123" id="card-cvv" name="cardCVV" maxlength="3">
                            <div class="form-text">Présent au dos de la carte.</div>
                        </div>
                        <div class="col-6">
                            <label for="" class="form-label">Date d'expiration</label>
                            <input type="text" class="form-control" placeholder="01 / 24" value="01 / 24" id="card-expiration" name="cardExpiration">
                            <div class="form-text">Sous la forme MM / AA.</div>
                        </div>
                    </div>
                </div>
                <div class="mb-3 mt-3">
                    <div class="row">
                        <div class="col-6 d-flex justify-content-start">
                            <a class="btn btn-secondary" href="payment.php?tab=shipping&cartID=<?php echo $_GET['cartID']; ?>">Etape précédente : Informations de livraison</a>
                        </div>
                        <div class="col-6 d-flex justify-content-end">
                            <button class="btn btn-success" type="submit">Etape suivant : Validation du paiement.</button>
                        </div>
                    </div>
                </div>
            </form>



            <style>
                .credit-card {
                    width: 400px;
                    height: 230px;
                    border-radius: 20px;
                    background-color: #dddddd;
                }

                .carte-numero {
                    text-transform: uppercase;
                    font-size: 25px;
                    font-weight: bold;
                    margin-left: 20px;
                    color: #fff;
                }

                .card-container {
                    height: 100%;
                }

                .carte-titulaire {
                    text-transform: uppercase;
                    margin-left: 25px;
                    color: #f4f4f4;
                }

                .carte-expiration-titre {
                    text-transform: uppercase;
                    font-size: 10px;
                    margin-bottom: 0;
                    color: #f4f4f4;
                }

                .carte-expiration {
                    font-size: 14px;
                    color: #f4f4f4;
                }
            </style>


            <script>
                // Espaces automatique dans le numéro de carte
                const inputCreditCard = document.getElementById("input-credit-card");
                const inputCardName = document.getElementById("card-name");
                const inputCardSurname = document.getElementById("card-surname");
                const inputCardExpiration = document.getElementById("card-expiration");
                const inputCardCVV = document.getElementById("card-cvv");

                inputCreditCard.addEventListener("input", function() {
                    let inputValue = event.target.value;
                    inputValue = inputValue.replace(/[^\d]/g, "");
                    if (inputValue.length > 16) {
                        inputValue = inputValue.substring(0, 16);
                    }
                    inputValue = inputValue.replace(/(\d{4})/g, "$1 ");
                    event.target.value = inputValue.trim();
                });


                const cardSnippetNumber = document.getElementById("card-snippet-number");
                const cardSnippetName = document.getElementById("card-snippet-name");
                const cardSnippetSurname = document.getElementById("card-snippet-surname");
                const cardSnippetExpiration = document.getElementById("card-snippet-expiration");

                // changement du numéro sur la carte en fonction du numéro dans l'input
                inputCreditCard.addEventListener("input", function() {
                    cardSnippetNumber.innerHTML = this.value;
                });
                inputCardName.addEventListener("input", function() {
                    cardSnippetName.innerHTML = this.value;
                });
                inputCardSurname.addEventListener("input", function() {
                    cardSnippetSurname.innerHTML = this.value;
                });

                // Gestion de l'input de date d'expiration
                inputCardExpiration.addEventListener("input", function(event) {
                    let inputValue = event.target.value;
                    inputValue = inputValue.replace(/[^\d]/g, "");
                    inputValue = inputValue.replace(/(\d{2})(\d{2})/, "$1 / $2");
                    event.target.value = inputValue;
                });

                inputCardExpiration.addEventListener("input", function() {
                    cardSnippetExpiration.innerHTML = this.value;
                });

                // Gestion du CVV de la carte
                inputCardCVV.addEventListener("input", function(event) {
                    let inputValue = event.target.value;
                    inputValue = inputValue.replace(/[^\d]/g, "");
                    event.target.value = inputValue;
                });

                // inputCardExpiration.addEventListener("input", function(event) {
                //     let inputValue = event.target.value;
                //     let monthAndYear = inputValue.match(/^\d{2}\/\d{2}/);
                //     if (monthAndYear) {
                //         monthAndYear = monthAndYear[0];
                //         cardSnippetExpiration.innerHTML = monthAndYear.replace("/", "/");
                //     } else {
                //         cardSnippetExpiration.innerHTML = "";
                //     }
                // });
            </script>


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
            <h5>Total : <span class="fw-bold"> <?php echo $prixTotalPanier; ?> €</span> <span class="text-muted fs-6">TTC</span></h5>
        </div>
        <div class="card-footer d-flex justify-content-center">
            <img width="170" src="https://cdn.shopify.com/s/files/1/0318/5718/0809/t/2/assets/gateways-cart.png?v=fd3b6526858486989ac0" alt="">
        </div>
    </div>
</div>