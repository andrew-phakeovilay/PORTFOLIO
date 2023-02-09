------------------------------------------------------------------
-- CREATEBASE.SQL_SAE
------------------------------------------------------------------
DROP TABLE DETAILPAIEMENT;
DROP TABLE QUANTITEPANIER;
DROP TABLE COMMANDE;
DROP TABLE PRODUIT;
DROP TABLE CATEGORIE;
DROP TABLE PAIEMENT;
DROP TABLE PANIER;
DROP TABLE CLIENT;

CREATE TABLE CLIENT
(
   idClient   DECIMAL(10),
   mailC VARCHAR(100),
   mdpClient VARCHAR(100),
   nomC VARCHAR(50),
   prenomC VARCHAR(50),
   admin VARCHAR(30),
   CONSTRAINT pk_client PRIMARY KEY (idClient)
);

CREATE TABLE CATEGORIE
(
   idCategorie DECIMAL(10), 
   idCategorieMere DECIMAL (10),
   nomCat VARCHAR(50),
   CONSTRAINT pk_categorie PRIMARY KEY (idCategorie),
   CONSTRAINT fk_categorie_categorie FOREIGN KEY (idCategorieMere ) REFERENCES categorie(idCategorie)
);

CREATE TABLE PRODUIT
(
   idProduit DECIMAL(10), 
   idCategorie DECIMAL(10),
   nomP VARCHAR(30), 
   prixProduit DECIMAL(10,2),
   taille DECIMAL(5), 
   composition VARCHAR(300), 
   description VARCHAR(500),
   CONSTRAINT pk_produit PRIMARY KEY (idProduit),
   CONSTRAINT fk_produit_categorie FOREIGN KEY (idCategorie) REFERENCES categorie(idCategorie)
);

CREATE TABLE PANIER
(
   idPanier DECIMAL(5),
   idClient DECIMAL(5),
   prixPanier DECIMAL(10,2),
   CONSTRAINT pk_panier PRIMARY KEY (idPanier),
    CONSTRAINT fk_panier_client FOREIGN KEY (idClient) REFERENCES client(idClient)
);

CREATE TABLE QUANTITEPANIER
(
   idPanier  DECIMAL(5),
   idProduit DECIMAL(5),
   nbProduit DECIMAL(5),
   prixQteProduit DECIMAL(10,2), 
   CONSTRAINT pk_quantitepanier PRIMARY KEY (idPanier,idProduit),
 CONSTRAINT fk_quantitepanier_produit FOREIGN KEY (idProduit) REFERENCES produit(idProduit),
CONSTRAINT fk_quantitepanier_panier FOREIGN KEY (idPanier) REFERENCES panier(idPanier)
);

CREATE TABLE COMMANDE
(
   idCommande DECIMAL(5),
   idPanier DECIMAL(5),
   idClient DECIMAL(5),
   datec DATE, 
   fraisL DECIMAL (10,2),
   adresseL VARCHAR(100),
   codePostalL DECIMAL(5),
   villeL VARCHAR(50),
   CONSTRAINT pk_commande PRIMARY KEY (idCommande),
    CONSTRAINT fk_commande_client FOREIGN KEY (idClient) REFERENCES client(idClient),
    CONSTRAINT fk_commande_panier FOREIGN KEY (idPanier) REFERENCES panier(idPanier)
);

CREATE TABLE PAIEMENT
(
   idPaiement DECIMAL(5),
   typePaiement VARCHAR(50),
   CONSTRAINT pk_paiement PRIMARY KEY (idPaiement)
);

CREATE TABLE DETAILPAIEMENT
(
   idPaiement DECIMAL(5),
   idClient DECIMAL(5),
   numCarte DECIMAL (16),
   dateValid VARCHAR(50),
   cryptogramme DECIMAL(3),
   CONSTRAINT pk_detailpaiement PRIMARY KEY (idClient, idPaiement),
   CONSTRAINT fk_detailpaiement_paiement FOREIGN KEY (idPaiement) REFERENCES paiement(idPaiement),
CONSTRAINT fk_detailpaiement_client FOREIGN KEY (idClient) REFERENCES CLIENT(idClient)
);

DROP SEQUENCE seqidclient;
CREATE SEQUENCE seqIdClient
START WITH 1
INCREMENT BY 1;

DROP SEQUENCE seqidcategorie;
CREATE SEQUENCE seqIdCategorie
START WITH 1
INCREMENT BY 1;

DROP SEQUENCE seqidproduit;
CREATE SEQUENCE seqIdProduit
START WITH 1
INCREMENT BY 1;

DROP SEQUENCE seqidpanier;
CREATE SEQUENCE seqIdPanier
START WITH 1
INCREMENT BY 1;

DROP SEQUENCE seqidcommande;
CREATE SEQUENCE seqIdCommande
START WITH 1
INCREMENT BY 1;

DROP SEQUENCE seqidpaiement;
CREATE SEQUENCE seqidpaiement
START WITH 1
INCREMENT BY 1;


INSERT INTO Client (idClient, mdpClient, nomC, prenomC, mailC, admin) VALUES (seqIdClient.NEXTVAL, '$2y$10$tkV7KQojw3E09O32reRXE.x/HZqWPMoLznFp4maEPalH8DGl7dJe.' , 'Client0', 'PreClient0', 'client0@gmail', NULL);
INSERT INTO Client (idClient, mdpClient, nomC, prenomC, mailC, admin) VALUES (seqIdClient.NEXTVAL, '$2y$10$EiI.bxYvjyqvhud6wwrLy.U1Vx7aDGd7TESxrajoaYLTLEFAVU2xC' , 'Client1', 'PreClient1', 'client1@gmail', NULL);
INSERT INTO Client (idClient, mdpClient, nomC, prenomC, mailC, admin) VALUES (seqIdClient.NEXTVAL, '$2y$10$c18B2sOmwyaacNs3xEz3c.l4lfmCiHadxQzSkYjyVNI.TEksp6ZCa' , 'Client2', 'PreClient2', 'client2@gmail', NULL);
INSERT INTO Client (idClient, mdpClient, nomC, prenomC, mailC, admin) VALUES (seqIdClient.NEXTVAL, '$2y$10$3S1neYIqUN1DmFuZZH62Eu2l11N3wwNTcJ0ZfLcjihFA3bU0mx/vy' , 'Client3', 'PreClient3', 'client3@gmail', NULL);
INSERT INTO Client (idClient, mdpClient, nomC, prenomC, mailC, admin) VALUES (seqIdClient.NEXTVAL, '$2y$10$o0Qpi1tXgln1YYyVghYUA.iWkDjy6uNXaF6RTZV0W6sXCu1L6zoy2' , 'Client4', 'PreClient4', 'client4@gmail', NULL);
INSERT INTO Client (idClient, mdpClient, nomC, prenomC, mailC, admin) VALUES (seqIdClient.NEXTVAL, '$2y$10$sf22RPu87YBdmDsMfFDWyusw.jx2kSXDn8QXoVVeGeyifEexh2ZK2' , 'Client5', 'PreClient5', 'client5@gmail', NULL);
INSERT INTO Client (idClient, mdpClient, nomC, prenomC, mailC, admin) VALUES (seqIdClient.NEXTVAL, '$2y$10$dOHEvOmHZzYqTaF8o07cOOjsfna4pkp6a/UOfEdF2amUc76.AVf6G' , 'Client6', 'PreClient6', 'client6@gmail', NULL);
INSERT INTO Client (idClient, mdpClient, nomC, prenomC, mailC, admin) VALUES (seqIdClient.NEXTVAL, '$2y$10$vZTeewei5RhQTb.x0BBtS.0kDffBIvmHwEnbSItU5cF30yhlSmIPC' , 'Client7', 'PreClient7', 'client7@gmail', NULL);
INSERT INTO Client (idClient, mdpClient, nomC, prenomC, mailC, admin) VALUES (seqIdClient.NEXTVAL, '$2y$10$5Ak9LLVGc9wl5IlvZzmsweDnvoFmkVL64GPKcds.OZ38L5MDmp2FC' , 'Client8', 'PreClient8', 'client8@gmail', NULL);
INSERT INTO Client (idClient, mdpClient, nomC, prenomC, mailC, admin) VALUES (seqIdClient.NEXTVAL, '$2y$10$nPYKaVrIMJVspwcZOBvaducUpYr0TtVzuhf5k8hi67aZM3qY6eOdK' , 'Client9', 'PreClient9', 'client9@gmail', NULL);
INSERT INTO Client (idClient, mdpClient, nomC, prenomC, mailC, admin) VALUES (seqIdClient.NEXTVAL, '$2y$10$2/6ODqxBoSfvR8QaqplAxebf1ai9xBPWjil8ovJJe4tvSPYz7MdMO' , 'Client10', 'PreClient10', 'client10@gmail', NULL);
INSERT INTO Client (idClient, mdpClient, nomC, prenomC, mailC, admin) VALUES (seqIdClient.NEXTVAL, '$2y$10$K2ClPfyMPevFT.dUGLNOTubKdSQuN7buQKGH9zJQE9S5GWN979RYa' , 'Client11', 'PreClient11', 'client11@gmail', NULL);
INSERT INTO Client (idClient, mdpClient, nomC, prenomC, mailC, admin) VALUES (seqIdClient.NEXTVAL, '$2y$10$pijQt6VIuoAiWWd2HKHGFO9bRzqK8NesRfo3yQqOHrXnigFTL7VO.' , 'Client12', 'PreClient12', 'client12@gmail', NULL);
INSERT INTO Client (idClient, mdpClient, nomC, prenomC, mailC, admin) VALUES (seqIdClient.NEXTVAL, '$2y$10$ajBFEJi6JhzMt9N.bzZw9u9IkCPui0hTbMKL4rxXf5fZS8Tvto5sS' , 'Client13', 'PreClient13', 'client13@gmail', NULL);
INSERT INTO Client (idClient, mdpClient, nomC, prenomC, mailC, admin) VALUES (seqIdClient.NEXTVAL, '$2y$10$EOF7hP.Y4mnHqnI5PfacAO6L2JfN47aULdrj2nts3PHwdihIMKkyW' , 'Client14', 'PreClient14', 'client14@gmail', NULL);
INSERT INTO Client (idClient, mdpClient, nomC, prenomC, mailC, admin) VALUES (seqIdClient.NEXTVAL, '$2y$10$mmSyC/YwtlmUkjdt8zVzX.lLg/1/d3cYZHFstg7nTYZ6tBWfRZqj6' , 'Client15', 'PreClient15', 'client15@gmail', NULL);
INSERT INTO Client (idClient, mdpClient, nomC, prenomC, mailC, admin) VALUES (seqIdClient.NEXTVAL, '$2y$10$.I5HZbDGzD3cLsasB2p7Uec267RpktA.esRFCrrvyeF45TREdWLxW' , 'Client16', 'PreClient16', 'client16@gmail', NULL);
INSERT INTO Client (idClient, mdpClient, nomC, prenomC, mailC, admin) VALUES (seqIdClient.NEXTVAL, '$2y$10$5zV8szMeEyCy.RZv3nEvY.m4Yb7hFNvUbgWqlrOQLd2EeQSljcdEC' , 'Client17', 'PreClient17', 'client17@gmail', NULL);
INSERT INTO Client (idClient, mdpClient, nomC, prenomC, mailC, admin) VALUES (seqIdClient.NEXTVAL, '$2y$10$DWqrMY4ziwAVflaLJ5OG..o2SpQirGwxcVMlrDnRVIlqNT/dPcr.G' , 'Client18', 'PreClient18', 'client18@gmail', NULL);
INSERT INTO Client (idClient, mdpClient, nomC, prenomC, mailC, admin) VALUES (seqIdClient.NEXTVAL, '$2y$10$Epx4DyGLxECo9XfJ/nh80.XqNt2wWwM1jp2DVthDZ.AqWldjn06Uu' , 'Client19', 'PreClient19', 'client19@gmail', NULL);
INSERT INTO Client (idClient, mdpClient, nomC, prenomC, mailC, admin) VALUES (seqIdClient.NEXTVAL, '$2y$10$Mdpo4P6WSE4h/nDk.Ub/UOiBJOjA5XYn4ULvwkDs/C0PPW1SrvWuS' , 'Client20', 'PreClient20', 'client20@gmail', NULL);
INSERT INTO Client (idClient, mdpClient, nomC, prenomC, mailC, admin) VALUES (seqIdClient.NEXTVAL, '$2y$10$ShN1i0CygVd5U61KW34VOeOpPX0Q.IsT0yeft4Ex9p6nGqWEHzLuu' , 'Client21', 'PreClient21', 'client21@gmail', NULL);
INSERT INTO Client (idClient, mdpClient, nomC, prenomC, mailC, admin) VALUES (seqIdClient.NEXTVAL, '$2y$10$Dc4nOjH7v5l6.YaIA/eX..yy/iCFgzZJ/e3hlKriwoebEFg1pKqJ6' , 'Client22', 'PreClient22', 'client22@gmail', NULL);
INSERT INTO Client (idClient, mdpClient, nomC, prenomC, mailC, admin) VALUES (seqIdClient.NEXTVAL, '$2y$10$cTeuMoqS1jwpXPCep5PgYuKrVqVSorT1RawfbIxjVzC7I0hrEXxAq' , 'Client23', 'PreClient23', 'client23@gmail', NULL);
INSERT INTO Client (idClient, mdpClient, nomC, prenomC, mailC, admin) VALUES (seqIdClient.NEXTVAL, '$2y$10$ar9K2Je4OGVOyPYKIyn15.fL8WWL0Gor8N6A8hOqC7fnBzNUDHq0i' , 'Client24', 'PreClient24', 'client24@gmail', NULL);
INSERT INTO Client (idClient, mdpClient, nomC, prenomC, mailC, admin) VALUES (seqIdClient.NEXTVAL, '$2y$10$jtSgxxmpyfdNq3a7JwnivOOhtj5WZIjFaBKnzoNhtVT4kDpoUFtrW' , 'Client25', 'PreClient25', 'client25@gmail', NULL);
INSERT INTO Client (idClient, mdpClient, nomC, prenomC, mailC, admin) VALUES (seqIdClient.NEXTVAL, '$2y$10$Xl1Dai6jeshpnUnIUJLMF.CRw6NGuGlcqs.w/l9oXxRD29b6DXtHq' , 'Client26', 'PreClient26', 'client26@gmail', NULL);
INSERT INTO Client (idClient, mdpClient, nomC, prenomC, mailC, admin) VALUES (seqIdClient.NEXTVAL, '$2y$10$bgjRCxbrV43kCUOBXRRHYueKDi3uGu45R0F54fPe3jACNB7zA2WUu' , 'Client27', 'PreClient27', 'client27@gmail', NULL);
INSERT INTO Client (idClient, mdpClient, nomC, prenomC, mailC, admin) VALUES (seqIdClient.NEXTVAL, '$2y$10$kbub93Qbuu3Gb9MojV0NSOAHVWDvC4Y38.nmp3mJDs4Csv2XXXBTS' , 'Client28', 'PreClient28', 'client28@gmail', NULL);
INSERT INTO Client (idClient, mdpClient, nomC, prenomC, mailC, admin) VALUES (seqIdClient.NEXTVAL, '$2y$10$roUernuCSpMdTj6lOTPhF.0Dr0yqDPmC3u1D.W3MYrTC8X2a8Z.ey' , 'Client29', 'PreClient29', 'client29@gmail', NULL);
INSERT INTO Client (idClient, mdpClient, nomC, prenomC, mailC, admin) VALUES (seqIdClient.NEXTVAL, '$2y$10$ul8rW5kyJBIzJJRlKqz.mOVSCp7DlaqI8t27HHgFjECpTH88HAhpW' , 'Client30', 'PreClient30', 'client30@gmail', NULL);
INSERT INTO Client (idClient, mdpClient, nomC, prenomC, mailC, admin) VALUES (seqIdClient.NEXTVAL, '$2y$10$iMhIiMZZtx93VmcXI6qxNuzSc1WCpFvSrtM4YlOy1nA19W99ELFb.' , 'Client31', 'PreClient31', 'client31@gmail', NULL);
INSERT INTO Client (idClient, mdpClient, nomC, prenomC, mailC, admin) VALUES (seqIdClient.NEXTVAL, '$2y$10$NRAwWLfZc1eq2OHrjTErGOrLTLRJ4.aR8J.6eY1JaxuzBgpoljIeS' , 'Client32', 'PreClient32', 'client32@gmail', NULL);
INSERT INTO Client (idClient, mdpClient, nomC, prenomC, mailC, admin) VALUES (seqIdClient.NEXTVAL, '$2y$10$U.3juf9Sa6XBmWPZgRRLS.9xypcN4RwgD8sH1FLt6zVb/EFxDnx6S' , 'Client33', 'PreClient33', 'client33@gmail', NULL);
INSERT INTO Client (idClient, mdpClient, nomC, prenomC, mailC, admin) VALUES (seqIdClient.NEXTVAL, '$2y$10$8Lfrxrnpmzz1Uk7s5Ic4buaeKlmxSOqYnIGbkjivNDzU7luNr1hvO' , 'Client34', 'PreClient34', 'client34@gmail', NULL);
INSERT INTO Client (idClient, mdpClient, nomC, prenomC, mailC, admin) VALUES (seqIdClient.NEXTVAL, '$2y$10$KWjCknHifXiPshfZsVilyeh2iDOpdldqHfp8sdKedJu4WkKJyU8Sm' , 'Client35', 'PreClient35', 'client35@gmail', NULL);
INSERT INTO Client (idClient, mdpClient, nomC, prenomC, mailC, admin) VALUES (seqIdClient.NEXTVAL, '$2y$10$CgOVUbir9Pn2CAi2zUYUh.rBvEWhu3BrJTsai6t81kFPx3KjTei3u' , 'Client36', 'PreClient36', 'client36@gmail', NULL);
INSERT INTO Client (idClient, mdpClient, nomC, prenomC, mailC, admin) VALUES (seqIdClient.NEXTVAL, '$2y$10$Mflg6HSSAE9Y8PppHzWDSOflVUC89TlYDk3F8AV.PalJZWWid/s/.' , 'Client37', 'PreClient37', 'client37@gmail', NULL);
INSERT INTO Client (idClient, mdpClient, nomC, prenomC, mailC, admin) VALUES (seqIdClient.NEXTVAL, '$2y$10$4JfpFwGSjsRyW2UIo8dwb.UimkRExGSL.9dcPaSJw0KdjdXVPOYiG' , 'Client38', 'PreClient38', 'client38@gmail', NULL);
INSERT INTO Client (idClient, mdpClient, nomC, prenomC, mailC, admin) VALUES (seqIdClient.NEXTVAL, '$2y$10$/Cqxbn6rLVkPCIs4XTydpeAEB4F2L6ImSTRyRRl9XIXIVkgvlFYFK' , 'Client39', 'PreClient39', 'client39@gmail', NULL);
INSERT INTO Client (idClient, mdpClient, nomC, prenomC, mailC, admin) VALUES (seqIdClient.NEXTVAL, '$2y$10$l.a/pCoxptdsnTxO3voBO.X786VpmPG4yx.ShVwPhkuqZvuqd0PjC' , 'Client40', 'PreClient40', 'client40@gmail', NULL);
INSERT INTO Client (idClient, mdpClient, nomC, prenomC, mailC, admin) VALUES (seqIdClient.NEXTVAL, '$2y$10$423dI/Ipm5Z4Pb1zloISOuEkxk2TtR9VmHBc7tT9pmElRjvIYftWS' , 'Client41', 'PreClient41', 'client41@gmail', NULL);
INSERT INTO Client (idClient, mdpClient, nomC, prenomC, mailC, admin) VALUES (seqIdClient.NEXTVAL, '$2y$10$rWDkFWAUxHQKIbqgVM60CeAyWrpo38P0XQOtBuKRX1tklW3afYC72' , 'Client42', 'PreClient42', 'client42@gmail', NULL);
INSERT INTO Client (idClient, mdpClient, nomC, prenomC, mailC, admin) VALUES (seqIdClient.NEXTVAL, '$2y$10$Vm.xr/ZBHiiUgHGxe5FtWeWVcFMcoUWSDm.CEcudmbsnpS7wV3oqK' , 'Client43', 'PreClient43', 'client43@gmail', NULL);
INSERT INTO Client (idClient, mdpClient, nomC, prenomC, mailC, admin) VALUES (seqIdClient.NEXTVAL, '$2y$10$dg9ZcBGZSSD4E778FAuscewwKgArETR9xv5WEFpcBooxeiMm70gSG' , 'Client44', 'PreClient44', 'client44@gmail', NULL);
INSERT INTO Client (idClient, mdpClient, nomC, prenomC, mailC, admin) VALUES (seqIdClient.NEXTVAL, '$2y$10$arzeShZc41zTZsRZGRtq8eg9Blm72A//pAxm7YeHfPLn6XV6eBlhS' , 'Client45', 'PreClient45', 'client45@gmail', NULL);
INSERT INTO Client (idClient, mdpClient, nomC, prenomC, mailC, admin) VALUES (seqIdClient.NEXTVAL, '$2y$10$usljpG50Dw1hL0l03vx0kOs1q0SCgige.slzL/buu1DCzBBgSgONS' , 'Client46', 'PreClient46', 'client46@gmail', NULL);
INSERT INTO Client (idClient, mdpClient, nomC, prenomC, mailC, admin) VALUES (seqIdClient.NEXTVAL, '$2y$10$pdLJEk/BzmDp5XKQ3efYk.EHCE5NUVDW4hGvJVOzhMfDFjndLfQCO' , 'Client47', 'PreClient47', 'client47@gmail', NULL);
INSERT INTO Client (idClient, mdpClient, nomC, prenomC, mailC, admin) VALUES (seqIdClient.NEXTVAL, '$2y$10$T52ue0ZqvF1qItIrhrgD.Oa4J6fUXoorTNwbNQ/bkcySn5z16JZbS' , 'Client48', 'PreClient48', 'client48@gmail', NULL);
INSERT INTO Client (idClient, mdpClient, nomC, prenomC, mailC, admin) VALUES (seqIdClient.NEXTVAL, '$2y$10$nYfFuPTnpAOJRi2tVP6JuOP9hEFl4alySiPTTX5mdfDYT4LwixWAi' , 'Client49', 'PreClient49', 'client49@gmail', NULL);


INSERT INTO Client (idClient, mailC, mdpClient, nomC, prenomC,  admin)
VALUES (51, 'igor@gmail', '$2y$10$GljFvHJW12qTYVqf9YpseeKD5gC0sR4Z4LmkMoqJjxu57lXup47OO', 'tapis', 'igor', 'administrateur');
INSERT INTO Client (idClient, mailC, mdpClient, nomC, prenomC,  admin)
VALUES (52, 'lettre@gmail', '$2y$10$4sZzkd/FWjSr3cYcCQIikOwF5PrzNW6BuqMvuHrQXs02XnMOmyI5i', 'B', 'A',NULL);


INSERT INTO CATEGORIE (idCategorie, idCategorieMere, nomCat)
VALUES(seqIdCategorie.NEXTVAL, NULL,'Bar traction');
INSERT INTO CATEGORIE (idCategorie, idCategorieMere, nomCat)
VALUES(seqIdCategorie.NEXTVAL, NULL, 'Corde_a_sauter');
INSERT INTO CATEGORIE (idCategorie, idCategorieMere, nomCat)
VALUES(seqIdCategorie.NEXTVAL, NULL, 'Poulie');
INSERT INTO CATEGORIE (idCategorie, idCategorieMere, nomCat)
VALUES(seqIdCategorie.NEXTVAL, NULL, 'elastique de musculation');
INSERT INTO CATEGORIE (idCategorie, idCategorieMere, nomCat)
VALUES(seqIdCategorie.NEXTVAL, NULL, 'Poid Boule');
INSERT INTO CATEGORIE (idCategorie, idCategorieMere, nomCat)
VALUES(seqIdCategorie.NEXTVAL, NULL, 'Disque poid');
INSERT INTO CATEGORIE (idCategorie, idCategorieMere, nomCat)
VALUES(seqIdCategorie.NEXTVAL, NULL, 'tapis de course');
INSERT INTO CATEGORIE (idCategorie, idCategorieMere, nomCat)
VALUES(seqIdCategorie.NEXTVAL, NULL, 'rameur');
INSERT INTO CATEGORIE (idCategorie, idCategorieMere, nomCat)
VALUES(seqIdCategorie.NEXTVAL, NULL, 'velo appartement');
INSERT INTO CATEGORIE (idCategorie, idCategorieMere, nomCat)
VALUES(seqIdCategorie.NEXTVAL, NULL, 'tapis de sol');


INSERT INTO Produit(idProduit, idCategorie, nomP, prixProduit, taille, composition,description)
VALUES (seqIdProduit.NEXTVAL, seqIdCategorie.currval, 'Altere', '10', '5', 'fer', 'Test');
INSERT INTO Produit(idProduit, idCategorie, nomP, prixProduit, taille, composition,description)
VALUES (seqIdProduit.NEXTVAL, seqIdCategorie.currval, 'Altere2', '10', '5', 'fer', 'Test'); 
INSERT INTO Produit(idProduit, idCategorie, nomP, prixProduit, taille, composition,description)
VALUES (seqIdProduit.NEXTVAL, seqIdCategorie.currval, 'Altere3', '10', '5', 'fer', 'Test');
INSERT INTO Produit(idProduit, idCategorie, nomP, prixProduit, taille, composition,description)
VALUES (seqIdProduit.NEXTVAL, seqIdCategorie.currval, 'Altere4', '10', '5', 'fer', 'Test'); 
INSERT INTO Produit(idProduit, idCategorie, nomP, prixProduit, taille, composition,description)
VALUES (seqIdProduit.NEXTVAL, seqIdCategorie.currval, 'Altere5', '10', '5', 'fer', 'Test'); 
INSERT INTO Produit(idProduit, idCategorie, nomP, prixProduit, taille, composition,description)
VALUES (seqIdProduit.NEXTVAL, seqIdCategorie.currval, 'Altere6', '10', '5', 'fer', 'Test');
INSERT INTO Produit(idProduit, idCategorie, nomP, prixProduit, taille, composition,description)
VALUES (seqIdProduit.NEXTVAL, seqIdCategorie.currval, 'Altere7', '10', '5', 'fer', 'Test'); 
INSERT INTO Produit(idProduit, idCategorie, nomP, prixProduit, taille, composition,description)
VALUES (seqIdProduit.NEXTVAL, seqIdCategorie.currval, 'Altere8', '10', '5', 'fer', 'Test');
INSERT INTO Produit(idProduit, idCategorie, nomP, prixProduit, taille, composition,description)
VALUES (seqIdProduit.NEXTVAL, seqIdCategorie.currval, 'Altere9', '10', '5', 'fer', 'Test'); 
INSERT INTO Produit(idProduit, idCategorie, nomP, prixProduit, taille, composition,description)
VALUES (seqIdProduit.NEXTVAL, seqIdCategorie.currval, 'Altere10', '10', '5', 'fer', 'Test'); 
INSERT INTO Produit(idProduit, idCategorie, nomP, prixProduit, taille, composition,description)
VALUES (seqIdProduit.NEXTVAL, seqIdCategorie.currval, 'Altere11', '10', '5', 'fer', 'Test');
INSERT INTO Produit(idProduit, idCategorie, nomP, prixProduit, taille, composition,description)
VALUES (seqIdProduit.NEXTVAL, seqIdCategorie.currval, 'Altere12', '10', '5', 'fer', 'Test'); 
INSERT INTO Produit(idProduit, idCategorie, nomP, prixProduit, taille, composition,description)
VALUES (seqIdProduit.NEXTVAL, seqIdCategorie.currval, 'Altere13', '10', '5', 'fer', 'Test');
INSERT INTO Produit(idProduit, idCategorie, nomP, prixProduit, taille, composition,description)
VALUES (seqIdProduit.NEXTVAL, seqIdCategorie.currval, 'Altere14', '10', '5', 'fer', 'Test'); 
INSERT INTO Produit(idProduit, idCategorie, nomP, prixProduit, taille, composition,description)
VALUES (seqIdProduit.NEXTVAL, seqIdCategorie.currval, 'Altere15', '10', '5', 'fer', 'Test'); 
INSERT INTO Produit(idProduit, idCategorie, nomP, prixProduit, taille, composition,description)
VALUES (seqIdProduit.NEXTVAL, seqIdCategorie.currval, 'Altere16', '10', '5', 'fer', 'Test');
INSERT INTO Produit(idProduit, idCategorie, nomP, prixProduit, taille, composition,description)
VALUES (seqIdProduit.NEXTVAL, seqIdCategorie.currval, 'Altere17', '10', '5', 'fer', 'Test'); 
INSERT INTO Produit(idProduit, idCategorie, nomP, prixProduit, taille, composition,description)
VALUES (seqIdProduit.NEXTVAL, seqIdCategorie.currval, 'Altere18', '10', '5', 'fer', 'Test');
INSERT INTO Produit(idProduit, idCategorie, nomP, prixProduit, taille, composition,description)
VALUES (seqIdProduit.NEXTVAL, seqIdCategorie.currval, 'Altere19', '10', '5', 'fer', 'Test'); 
INSERT INTO Produit(idProduit, idCategorie, nomP, prixProduit, taille, composition,description)
VALUES (seqIdProduit.NEXTVAL, seqIdCategorie.currval, 'Altere20', '10', '5', 'fer', 'Test'); 
INSERT INTO Produit(idProduit, idCategorie, nomP, prixProduit, taille, composition,description)
VALUES (seqIdProduit.NEXTVAL, seqIdCategorie.currval, 'Altere21', '10', '5', 'fer', 'Test');
INSERT INTO Produit(idProduit, idCategorie, nomP, prixProduit, taille, composition,description)
VALUES (seqIdProduit.NEXTVAL, seqIdCategorie.currval, 'Altere22', '10', '5', 'fer', 'Test'); 
INSERT INTO Produit(idProduit, idCategorie, nomP, prixProduit, taille, composition,description)
VALUES (seqIdProduit.NEXTVAL, seqIdCategorie.currval, 'Altere23', '10', '5', 'fer', 'Test');
INSERT INTO Produit(idProduit, idCategorie, nomP, prixProduit, taille, composition,description)
VALUES (seqIdProduit.NEXTVAL, seqIdCategorie.currval, 'Altere24', '10', '5', 'fer', 'Test'); 
INSERT INTO Produit(idProduit, idCategorie, nomP, prixProduit, taille, composition,description)
VALUES (seqIdProduit.NEXTVAL, seqIdCategorie.currval, 'Altere25', '10', '5', 'fer', 'Test'); 
INSERT INTO Produit(idProduit, idCategorie, nomP, prixProduit, taille, composition,description)
VALUES (seqIdProduit.NEXTVAL, seqIdCategorie.currval, 'Altere26', '10', '5', 'fer', 'Test');
INSERT INTO Produit(idProduit, idCategorie, nomP, prixProduit, taille, composition,description)
VALUES (seqIdProduit.NEXTVAL, seqIdCategorie.currval, 'Altere27', '10', '5', 'fer', 'Test'); 
INSERT INTO Produit(idProduit, idCategorie, nomP, prixProduit, taille, composition,description)
VALUES (seqIdProduit.NEXTVAL, seqIdCategorie.currval, 'Altere28', '10', '5', 'fer', 'Test');
INSERT INTO Produit(idProduit, idCategorie, nomP, prixProduit, taille, composition,description)
VALUES (seqIdProduit.NEXTVAL, seqIdCategorie.currval, 'Altere29', '10', '5', 'fer', 'Test'); 
INSERT INTO Produit(idProduit, idCategorie, nomP, prixProduit, taille, composition,description)
VALUES (seqIdProduit.NEXTVAL, seqIdCategorie.currval, 'Altere30', '10', '5', 'fer', 'Test'); 
INSERT INTO Produit(idProduit, idCategorie, nomP, prixProduit, taille, composition,description)
VALUES (seqIdProduit.NEXTVAL, seqIdCategorie.currval, 'Altere31', '10', '5', 'fer', 'Test');
INSERT INTO Produit(idProduit, idCategorie, nomP, prixProduit, taille, composition,description)
VALUES (seqIdProduit.NEXTVAL, seqIdCategorie.currval, 'Altere32', '10', '5', 'fer', 'Test'); 
INSERT INTO Produit(idProduit, idCategorie, nomP, prixProduit, taille, composition,description)
VALUES (seqIdProduit.NEXTVAL, seqIdCategorie.currval, 'Altere33', '10', '5', 'fer', 'Test');
INSERT INTO Produit(idProduit, idCategorie, nomP, prixProduit, taille, composition,description)
VALUES (seqIdProduit.NEXTVAL, seqIdCategorie.currval, 'Altere34', '10', '5', 'fer', 'Test'); 
INSERT INTO Produit(idProduit, idCategorie, nomP, prixProduit, taille, composition,description)
VALUES (seqIdProduit.NEXTVAL, seqIdCategorie.currval, 'Altere35', '10', '5', 'fer', 'Test'); 
INSERT INTO Produit(idProduit, idCategorie, nomP, prixProduit, taille, composition,description)
VALUES (seqIdProduit.NEXTVAL, seqIdCategorie.currval, 'Altere36', '10', '5', 'fer', 'Test');
INSERT INTO Produit(idProduit, idCategorie, nomP, prixProduit, taille, composition,description)
VALUES (seqIdProduit.NEXTVAL, seqIdCategorie.currval, 'Altere37', '10', '5', 'fer', 'Test'); 
INSERT INTO Produit(idProduit, idCategorie, nomP, prixProduit, taille, composition,description)
VALUES (seqIdProduit.NEXTVAL, seqIdCategorie.currval, 'Altere38', '10', '5', 'fer', 'Test');
INSERT INTO Produit(idProduit, idCategorie, nomP, prixProduit, taille, composition,description)
VALUES (seqIdProduit.NEXTVAL, seqIdCategorie.currval, 'Altere39', '10', '5', 'fer', 'Test'); 
INSERT INTO Produit(idProduit, idCategorie, nomP, prixProduit, taille, composition,description)
VALUES (seqIdProduit.NEXTVAL, seqIdCategorie.currval, 'Altere40', '10', '5', 'fer', 'Test'); 
INSERT INTO Produit(idProduit, idCategorie, nomP, prixProduit, taille, composition,description)
VALUES (seqIdProduit.NEXTVAL, seqIdCategorie.currval, 'Altere41', '10', '5', 'fer', 'Test');
INSERT INTO Produit(idProduit, idCategorie, nomP, prixProduit, taille, composition,description)
VALUES (seqIdProduit.NEXTVAL, seqIdCategorie.currval, 'Altere42', '10', '5', 'fer', 'Test'); 
INSERT INTO Produit(idProduit, idCategorie, nomP, prixProduit, taille, composition,description)
VALUES (seqIdProduit.NEXTVAL, seqIdCategorie.currval, 'Altere43', '10', '5', 'fer', 'Test');
INSERT INTO Produit(idProduit, idCategorie, nomP, prixProduit, taille, composition,description)
VALUES (seqIdProduit.NEXTVAL, seqIdCategorie.currval, 'Altere44', '10', '5', 'fer', 'Test'); 
INSERT INTO Produit(idProduit, idCategorie, nomP, prixProduit, taille, composition,description)
VALUES (seqIdProduit.NEXTVAL, seqIdCategorie.currval, 'Altere45', '10', '5', 'fer', 'Test'); 
INSERT INTO Produit(idProduit, idCategorie, nomP, prixProduit, taille, composition,description)
VALUES (seqIdProduit.NEXTVAL, seqIdCategorie.currval, 'Altere46', '10', '5', 'fer', 'Test');
INSERT INTO Produit(idProduit, idCategorie, nomP, prixProduit, taille, composition,description)
VALUES (seqIdProduit.NEXTVAL, seqIdCategorie.currval, 'Altere47', '10', '5', 'fer', 'Test'); 
INSERT INTO Produit(idProduit, idCategorie, nomP, prixProduit, taille, composition,description)
VALUES (seqIdProduit.NEXTVAL, seqIdCategorie.currval, 'Altere48', '10', '5', 'fer', 'Test');
INSERT INTO Produit(idProduit, idCategorie, nomP, prixProduit, taille, composition,description)
VALUES (seqIdProduit.NEXTVAL, seqIdCategorie.currval, 'Altere49', '10', '5', 'fer', 'Test'); 
INSERT INTO Produit(idProduit, idCategorie, nomP, prixProduit, taille, composition,description)
VALUES (seqIdProduit.NEXTVAL, seqIdCategorie.currval, 'Altere50', '10', '5', 'fer', 'Test'); 

INSERT INTO PANIER (idPanier, idClient,prixpanier)
VALUES (seqIdPanier.NEXTVAL, 1,10);
INSERT INTO PANIER (idPanier, idClient,prixpanier)
VALUES (seqIdPanier.NEXTVAL, 1,20);
INSERT INTO PANIER (idPanier, idClient,prixpanier)
VALUES (seqIdPanier.NEXTVAL, 1,30);
INSERT INTO PANIER (idPanier, idClient,prixpanier)
VALUES (seqIdPanier.NEXTVAL, 2,80);
INSERT INTO PANIER (idPanier, idClient,prixpanier)
VALUES (seqIdPanier.NEXTVAL, 3,50);
INSERT INTO PANIER (idPanier, idClient,prixpanier)
VALUES (seqIdPanier.NEXTVAL, 4,60);
INSERT INTO PANIER (idPanier, idClient,prixpanier)
VALUES (seqIdPanier.NEXTVAL, 4,90);
INSERT INTO PANIER (idPanier, idClient,prixpanier)
VALUES (seqIdPanier.NEXTVAL, 4,70);
INSERT INTO PANIER (idPanier, idClient,prixpanier)
VALUES (seqIdPanier.NEXTVAL, 4,80);
INSERT INTO PANIER (idPanier, idClient,prixpanier)
VALUES (seqIdPanier.NEXTVAL, 4,50);
INSERT INTO PANIER (idPanier, idClient,prixpanier)
VALUES (seqIdPanier.NEXTVAL, 5,50);
INSERT INTO PANIER (idPanier, idClient,prixpanier)
VALUES (seqIdPanier.NEXTVAL, 5,60);
INSERT INTO PANIER (idPanier, idClient,prixpanier)
VALUES (seqIdPanier.NEXTVAL, 6,30);
INSERT INTO PANIER (idPanier, idClient,prixpanier)
VALUES (seqIdPanier.NEXTVAL, 7,80);
INSERT INTO PANIER (idPanier, idClient,prixpanier)
VALUES (seqIdPanier.NEXTVAL, 7,50);
INSERT INTO PANIER (idPanier, idClient,prixpanier)
VALUES (seqIdPanier.NEXTVAL, 7,60);
INSERT INTO PANIER (idPanier, idClient,prixpanier)
VALUES (seqIdPanier.NEXTVAL, 8,90);
INSERT INTO PANIER (idPanier, idClient,prixpanier)
VALUES (seqIdPanier.NEXTVAL, 8,70);
INSERT INTO PANIER (idPanier, idClient,prixpanier)
VALUES (seqIdPanier.NEXTVAL, 8,80);
INSERT INTO PANIER (idPanier, idClient,prixpanier)
VALUES (seqIdPanier.NEXTVAL, 8,50);

INSERT INTO PANIER (idPanier, idClient,prixpanier)
VALUES (seqIdPanier.NEXTVAL, 9,10);
INSERT INTO PANIER (idPanier, idClient,prixpanier)
VALUES (seqIdPanier.NEXTVAL, 9,20);
INSERT INTO PANIER (idPanier, idClient,prixpanier)
VALUES (seqIdPanier.NEXTVAL, 9,30);
INSERT INTO PANIER (idPanier, idClient,prixpanier)
VALUES (seqIdPanier.NEXTVAL, 10,80);
INSERT INTO PANIER (idPanier, idClient,prixpanier)
VALUES (seqIdPanier.NEXTVAL, 11,50);
INSERT INTO PANIER (idPanier, idClient,prixpanier)
VALUES (seqIdPanier.NEXTVAL, 12,60);
INSERT INTO PANIER (idPanier, idClient,prixpanier)
VALUES (seqIdPanier.NEXTVAL, 12,90);
INSERT INTO PANIER (idPanier, idClient,prixpanier)
VALUES (seqIdPanier.NEXTVAL, 12,70);
INSERT INTO PANIER (idPanier, idClient,prixpanier)
VALUES (seqIdPanier.NEXTVAL, 12,80);
INSERT INTO PANIER (idPanier, idClient,prixpanier)
VALUES (seqIdPanier.NEXTVAL, 12,50);
INSERT INTO PANIER (idPanier, idClient,prixpanier)
VALUES (seqIdPanier.NEXTVAL, 13,50);
INSERT INTO PANIER (idPanier, idClient,prixpanier)
VALUES (seqIdPanier.NEXTVAL, 13,60);
INSERT INTO PANIER (idPanier, idClient,prixpanier)
VALUES (seqIdPanier.NEXTVAL, 14,30);
INSERT INTO PANIER (idPanier, idClient,prixpanier)
VALUES (seqIdPanier.NEXTVAL, 15,80);
INSERT INTO PANIER (idPanier, idClient,prixpanier)
VALUES (seqIdPanier.NEXTVAL, 15,50);
INSERT INTO PANIER (idPanier, idClient,prixpanier)
VALUES (seqIdPanier.NEXTVAL, 15,60);
INSERT INTO PANIER (idPanier, idClient,prixpanier)
VALUES (seqIdPanier.NEXTVAL, 16,90);
INSERT INTO PANIER (idPanier, idClient,prixpanier)
VALUES (seqIdPanier.NEXTVAL, 16,70);
INSERT INTO PANIER (idPanier, idClient,prixpanier)
VALUES (seqIdPanier.NEXTVAL, 16,80);
INSERT INTO PANIER (idPanier, idClient,prixpanier)
VALUES (seqIdPanier.NEXTVAL, 16,50);



INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(1,1,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(2,2,2,20);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(3,2,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(3,3,2,20);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(4,15,2,20);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(4,10,5,50);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(4,48,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(5,4,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(5,50,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(5,12,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(5,18,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(5,30,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(6,30,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(6,31,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(6,32,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(6,33,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(6,37,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(6,3,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(7,22,2,20);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(7,17,3,30);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(7,19,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(7,49,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(7,39,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(7,10,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(8,10,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(8,11,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(8,12,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(8,13,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(8,14,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(8,15,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(8,16,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(9,16,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(9,17,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(9,18,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(9,19,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(9,20,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(9,21,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(9,22,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(9,23,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(9,24,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(10,25,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(10,26,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(10,27,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(10,28,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(10,29,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(11,30,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(11,31,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(11,32,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(11,33,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(11,34,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(12,35,2,20);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(12,36,2,20);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(12,37,2,20);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(13,38,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(13,39,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(13,40,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(14,41,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(14,42,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(14,43,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(14,44,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(14,45,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(14,46,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(14,47,2,20);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(15,48,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(15,49,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(15,50,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(15,1,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(15,2,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(16,3,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(16,4,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(16,5,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(16,6,2,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(16,7,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(17,8,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(17,9,2,20);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(17,10,2,20);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(17,11,2,20);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(17,12,2,20);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(18,13,2,20);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(18,14,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(18,15,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(18,16,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(18,17,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(18,18,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(19,18,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(19,19,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(19,20,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(19,21,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(19,22,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(19,23,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(19,24,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(19,25,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(20,26,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(20,27,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(20,28,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(20,29,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(20,30,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(20,1,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(21,2,2,20);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(22,2,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(23,3,2,20);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(24,15,2,20);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(24,10,5,50);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(24,48,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(25,4,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(25,50,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(25,12,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(25,18,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(25,30,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(26,30,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(26,31,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(26,32,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(26,33,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(26,37,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(26,3,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(27,22,2,20);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(27,17,3,30);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(27,19,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(27,49,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(27,39,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(27,10,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(28,10,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(28,11,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(28,12,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(28,13,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(28,14,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(28,15,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(28,16,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(29,16,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(29,17,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(29,18,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(29,19,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(29,20,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(29,21,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(29,22,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(29,23,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(29,24,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(30,25,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(30,26,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(30,27,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(30,28,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(30,29,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(31,30,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(31,31,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(31,32,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(31,33,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(31,34,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(32,35,2,20);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(32,36,2,20);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(32,37,2,20);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(33,38,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(33,39,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(33,40,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(34,41,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(34,42,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(34,43,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(34,44,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(34,45,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(34,46,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(34,47,2,20);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(35,48,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(35,49,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(35,50,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(35,1,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(35,2,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(36,3,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(36,4,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(36,5,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(36,6,2,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(36,7,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(37,8,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(37,9,2,20);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(37,10,2,20);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(37,11,2,20);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(37,12,2,20);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(38,13,2,20);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(38,14,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(38,15,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(38,16,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(38,17,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(38,18,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(39,18,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(39,19,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(39,20,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(39,21,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(39,22,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(39,23,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(39,24,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(39,25,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(40,26,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(40,27,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(40,28,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(40,29,1,10);
INSERT INTO QUANTITEPANIER (idpanier,idproduit,nbproduit,prixqteproduit)
VALUES(40,30,1,10);



INSERT INTO COMMANDE(idCommande, idPanier, idClient, datec, fraisL, adresseL, codePostalL, villeL)  
VALUES (seqIdCommande.NEXTVAL, 1, 1, '14/12/2022', 5, '11 rue marc chagal', 31700, 'Blagnac');
INSERT INTO COMMANDE(idCommande, idPanier, idClient, datec, fraisL, adresseL, codePostalL, villeL)  
VALUES (seqIdCommande.NEXTVAL, 2, 1, '14/12/2022', 5, '10 rue marc chagal', 31700, 'Blagnac');
INSERT INTO COMMANDE(idCommande, idPanier, idClient, datec, fraisL, adresseL, codePostalL, villeL)  
VALUES (seqIdCommande.NEXTVAL, 3, 1, '14/12/2022', 5, '10 rue marc chagal', 31700, 'Blagnac');
INSERT INTO COMMANDE(idCommande, idPanier, idClient, datec, fraisL, adresseL, codePostalL, villeL)  
VALUES (seqIdCommande.NEXTVAL, 1, 1, '16/12/2022', 5, '10 rue marc chagal', 31700, 'Blagnac');
INSERT INTO COMMANDE(idCommande, idPanier, idClient, datec, fraisL, adresseL, codePostalL, villeL)  
VALUES (seqIdCommande.NEXTVAL, 4, 2, '11/12/2022', 9, '9 rue de l église', 12250, 'Tournemire');
INSERT INTO COMMANDE(idCommande, idPanier, idClient, datec, fraisL, adresseL, codePostalL, villeL)  
VALUES (seqIdCommande.NEXTVAL, 5, 3, '15/12/2022', 5, '2 avenue françois galthier', 12250, 'Roquefort');
INSERT INTO COMMANDE(idCommande, idPanier, idClient, datec, fraisL, adresseL, codePostalL, villeL)  
VALUES (seqIdCommande.NEXTVAL, 6, 4, '14/12/2022', 6, '10 rue marc chagal', 31700, 'Blagnac');
INSERT INTO COMMANDE(idCommande, idPanier, idClient, datec, fraisL, adresseL, codePostalL, villeL)  
VALUES (seqIdCommande.NEXTVAL, 7, 4, '15/12/2022', 7, '10 rue marc chagal', 31700, 'Blagnac');
INSERT INTO COMMANDE(idCommande, idPanier, idClient, datec, fraisL, adresseL, codePostalL, villeL)  
VALUES (seqIdCommande.NEXTVAL, 8, 4, '16/12/2022', 5, '10 rue marc chagal', 31700, 'Blagnac');
INSERT INTO COMMANDE(idCommande, idPanier, idClient, datec, fraisL, adresseL, codePostalL, villeL)  
VALUES (seqIdCommande.NEXTVAL, 9, 4, '11/12/2022', 9, '9 rue de l église', 12250, 'Tournemire');
INSERT INTO COMMANDE(idCommande, idPanier, idClient, datec, fraisL, adresseL, codePostalL, villeL)  
VALUES (seqIdCommande.NEXTVAL, 10, 4, '18/12/2022', 4, '10 rue marc chagal', 31700, 'Blagnac');
INSERT INTO COMMANDE(idCommande, idPanier, idClient, datec, fraisL, adresseL, codePostalL, villeL)  
VALUES (seqIdCommande.NEXTVAL, 7, 4, '19/12/2022', 7, '10 rue marc chagal', 31700, 'Blagnac');
INSERT INTO COMMANDE(idCommande, idPanier, idClient, datec, fraisL, adresseL, codePostalL, villeL)  
VALUES (seqIdCommande.NEXTVAL, 11, 5, '14/12/2022', 5, '15 rue marc chagal', 31700, 'Blagnac');
INSERT INTO COMMANDE(idCommande, idPanier, idClient, datec, fraisL, adresseL, codePostalL, villeL)  
VALUES (seqIdCommande.NEXTVAL, 12, 5, '16/12/2022', 5, '15 rue marc chagal', 31700, 'Blagnac');
INSERT INTO COMMANDE(idCommande, idPanier, idClient, datec, fraisL, adresseL, codePostalL, villeL)  
VALUES (seqIdCommande.NEXTVAL, 13, 6, '11/12/2022', 9, '9 rue de l église', 12250, 'Tournemire');
INSERT INTO COMMANDE(idCommande, idPanier, idClient, datec, fraisL, adresseL, codePostalL, villeL)  
VALUES (seqIdCommande.NEXTVAL, 14, 7, '14/12/2022', 5, '10 rue marc chagal', 31700, 'Blagnac');
INSERT INTO COMMANDE(idCommande, idPanier, idClient, datec, fraisL, adresseL, codePostalL, villeL)  
VALUES (seqIdCommande.NEXTVAL, 15, 7, '14/12/2022', 5, '10 rue marc chagal', 31700, 'Blagnac');
INSERT INTO COMMANDE(idCommande, idPanier, idClient, datec, fraisL, adresseL, codePostalL, villeL)  
VALUES (seqIdCommande.NEXTVAL, 16, 7, '14/12/2022', 5, '10 rue marc chagal', 31700, 'Blagnac');
INSERT INTO COMMANDE(idCommande, idPanier, idClient, datec, fraisL, adresseL, codePostalL, villeL)  
VALUES (seqIdCommande.NEXTVAL, 17, 8, '16/12/2022', 5, '7 rue marc chagal', 31700, 'Blagnac');
INSERT INTO COMMANDE(idCommande, idPanier, idClient, datec, fraisL, adresseL, codePostalL, villeL)  
VALUES (seqIdCommande.NEXTVAL, 18, 8, '11/12/2022', 9, '9 rue de l église', 12250, 'Tournemire');
INSERT INTO COMMANDE(idCommande, idPanier, idClient, datec, fraisL, adresseL, codePostalL, villeL)  
VALUES (seqIdCommande.NEXTVAL, 19, 8, '14/12/2022', 5, '10 rue marc chagal', 31700, 'Blagnac');
INSERT INTO COMMANDE(idCommande, idPanier, idClient, datec, fraisL, adresseL, codePostalL, villeL)  
VALUES (seqIdCommande.NEXTVAL, 20, 8, '14/12/2022', 5, '10 rue marc chagal', 31700, 'Blagnac');
INSERT INTO COMMANDE(idCommande, idPanier, idClient, datec, fraisL, adresseL, codePostalL, villeL)  
VALUES (seqIdCommande.NEXTVAL, 13, 6, '21/12/2022', 5, '10 rue marc chagal', 31700, 'Blagnac');
INSERT INTO COMMANDE(idCommande, idPanier, idClient, datec, fraisL, adresseL, codePostalL, villeL)  
VALUES (seqIdCommande.NEXTVAL, 5, 3, '21/12/2022', 5, '10 rue marc chagal', 31700, 'Blagnac');
INSERT INTO COMMANDE(idCommande, idPanier, idClient, datec, fraisL, adresseL, codePostalL, villeL)  
VALUES (seqIdCommande.NEXTVAL, 11, 5, '21/12/2022', 5, '10 rue marc chagal', 31700, 'Blagnac');
INSERT INTO COMMANDE(idCommande, idPanier, idClient, datec, fraisL, adresseL, codePostalL, villeL)  
VALUES (seqIdCommande.NEXTVAL, 21, 9, '14/12/2022', 5, '11 rue marc chagal', 31700, 'Blagnac');
INSERT INTO COMMANDE(idCommande, idPanier, idClient, datec, fraisL, adresseL, codePostalL, villeL)  
VALUES (seqIdCommande.NEXTVAL, 2, 9, '14/12/2022', 5, '10 rue marc chagal', 31700, 'Blagnac');
INSERT INTO COMMANDE(idCommande, idPanier, idClient, datec, fraisL, adresseL, codePostalL, villeL)  
VALUES (seqIdCommande.NEXTVAL, 3, 9, '14/12/2022', 5, '10 rue marc chagal', 31700, 'Blagnac');
INSERT INTO COMMANDE(idCommande, idPanier, idClient, datec, fraisL, adresseL, codePostalL, villeL)  
VALUES (seqIdCommande.NEXTVAL, 1, 9, '16/12/2022', 5, '10 rue marc chagal', 31700, 'Blagnac');
INSERT INTO COMMANDE(idCommande, idPanier, idClient, datec, fraisL, adresseL, codePostalL, villeL)  
VALUES (seqIdCommande.NEXTVAL, 4, 10, '11/12/2022', 9, '9 rue de l église', 12250, 'Tournemire');
INSERT INTO COMMANDE(idCommande, idPanier, idClient, datec, fraisL, adresseL, codePostalL, villeL)  
VALUES (seqIdCommande.NEXTVAL, 5, 11, '15/12/2022', 5, '2 avenue françois galthier', 12250, 'Roquefort');
INSERT INTO COMMANDE(idCommande, idPanier, idClient, datec, fraisL, adresseL, codePostalL, villeL)  
VALUES (seqIdCommande.NEXTVAL, 6, 12, '14/12/2022', 6, '10 rue marc chagal', 31700, 'Blagnac');
INSERT INTO COMMANDE(idCommande, idPanier, idClient, datec, fraisL, adresseL, codePostalL, villeL)  
VALUES (seqIdCommande.NEXTVAL, 7, 12, '15/12/2022', 7, '10 rue marc chagal', 31700, 'Blagnac');
INSERT INTO COMMANDE(idCommande, idPanier, idClient, datec, fraisL, adresseL, codePostalL, villeL)  
VALUES (seqIdCommande.NEXTVAL, 8, 12, '16/12/2022', 5, '10 rue marc chagal', 31700, 'Blagnac');
INSERT INTO COMMANDE(idCommande, idPanier, idClient, datec, fraisL, adresseL, codePostalL, villeL)  
VALUES (seqIdCommande.NEXTVAL, 9, 12, '11/12/2022', 9, '9 rue de l église', 12250, 'Tournemire');
INSERT INTO COMMANDE(idCommande, idPanier, idClient, datec, fraisL, adresseL, codePostalL, villeL)  
VALUES (seqIdCommande.NEXTVAL, 10, 12, '18/12/2022', 4, '10 rue marc chagal', 31700, 'Blagnac');
INSERT INTO COMMANDE(idCommande, idPanier, idClient, datec, fraisL, adresseL, codePostalL, villeL)  
VALUES (seqIdCommande.NEXTVAL, 7, 12, '19/12/2022', 7, '10 rue marc chagal', 31700, 'Blagnac');
INSERT INTO COMMANDE(idCommande, idPanier, idClient, datec, fraisL, adresseL, codePostalL, villeL)  
VALUES (seqIdCommande.NEXTVAL, 11, 13, '14/12/2022', 5, '15 rue marc chagal', 31700, 'Blagnac');
INSERT INTO COMMANDE(idCommande, idPanier, idClient, datec, fraisL, adresseL, codePostalL, villeL)  
VALUES (seqIdCommande.NEXTVAL, 12, 13, '16/12/2022', 5, '15 rue marc chagal', 31700, 'Blagnac');
INSERT INTO COMMANDE(idCommande, idPanier, idClient, datec, fraisL, adresseL, codePostalL, villeL)  
VALUES (seqIdCommande.NEXTVAL, 13, 14, '11/12/2022', 9, '9 rue de l église', 12250, 'Tournemire');
INSERT INTO COMMANDE(idCommande, idPanier, idClient, datec, fraisL, adresseL, codePostalL, villeL)  
VALUES (seqIdCommande.NEXTVAL, 14, 15, '14/12/2022', 5, '10 rue marc chagal', 31700, 'Blagnac');
INSERT INTO COMMANDE(idCommande, idPanier, idClient, datec, fraisL, adresseL, codePostalL, villeL)  
VALUES (seqIdCommande.NEXTVAL, 15, 15, '14/12/2022', 5, '10 rue marc chagal', 31700, 'Blagnac');
INSERT INTO COMMANDE(idCommande, idPanier, idClient, datec, fraisL, adresseL, codePostalL, villeL)  
VALUES (seqIdCommande.NEXTVAL, 16, 15, '14/12/2022', 5, '10 rue marc chagal', 31700, 'Blagnac');
INSERT INTO COMMANDE(idCommande, idPanier, idClient, datec, fraisL, adresseL, codePostalL, villeL)  
VALUES (seqIdCommande.NEXTVAL, 17, 16, '16/12/2022', 5, '7 rue marc chagal', 31700, 'Blagnac');
INSERT INTO COMMANDE(idCommande, idPanier, idClient, datec, fraisL, adresseL, codePostalL, villeL)  
VALUES (seqIdCommande.NEXTVAL, 18, 16, '11/12/2022', 9, '9 rue de l église', 12250, 'Tournemire');
INSERT INTO COMMANDE(idCommande, idPanier, idClient, datec, fraisL, adresseL, codePostalL, villeL)  
VALUES (seqIdCommande.NEXTVAL, 19, 16, '14/12/2022', 5, '10 rue marc chagal', 31700, 'Blagnac');
INSERT INTO COMMANDE(idCommande, idPanier, idClient, datec, fraisL, adresseL, codePostalL, villeL)  
VALUES (seqIdCommande.NEXTVAL, 20, 16, '14/12/2022', 5, '10 rue marc chagal', 31700, 'Blagnac');
INSERT INTO COMMANDE(idCommande, idPanier, idClient, datec, fraisL, adresseL, codePostalL, villeL)  
VALUES (seqIdCommande.NEXTVAL, 13, 14, '21/12/2022', 5, '10 rue marc chagal', 31700, 'Blagnac');
INSERT INTO COMMANDE(idCommande, idPanier, idClient, datec, fraisL, adresseL, codePostalL, villeL)  
VALUES (seqIdCommande.NEXTVAL, 5, 11, '21/12/2022', 5, '10 rue marc chagal', 31700, 'Blagnac');
INSERT INTO COMMANDE(idCommande, idPanier, idClient, datec, fraisL, adresseL, codePostalL, villeL)  
VALUES (seqIdCommande.NEXTVAL, 11, 13, '21/12/2022', 5, '10 rue marc chagal', 31700, 'Blagnac');

INSERT INTO PAIEMENT (idPaiement, typePaiement)
VALUES(seqidpaiement.NEXTVAL, 'carte bleu');


INSERT INTO DETAILPAIEMENT (idPaiement, idClient, numCarte, dateValid, cryptogramme)
VALUES (1, 1, 4990758425489624, 06/23, 045);
INSERT INTO DETAILPAIEMENT (idPaiement, idClient, numCarte, dateValid, cryptogramme)
VALUES (1, 2, 4990758425489624, '30/24', 045);
INSERT INTO DETAILPAIEMENT (idPaiement, idClient, numCarte, dateValid, cryptogramme)
VALUES (1, 3, 4990158725482324, '07/23', 185);
INSERT INTO DETAILPAIEMENT (idPaiement, idClient, numCarte, dateValid, cryptogramme)
VALUES (1, 4, 4990358921469321, '01/25', 485);
INSERT INTO DETAILPAIEMENT (idPaiement, idClient, numCarte, dateValid, cryptogramme)
VALUES (1, 5, 4990259723481699, '24/24', 072);
INSERT INTO DETAILPAIEMENT (idPaiement, idClient, numCarte, dateValid, cryptogramme)
VALUES (1, 6, 4990758422469825, '12/24', 596);
INSERT INTO DETAILPAIEMENT (idPaiement, idClient, numCarte, dateValid, cryptogramme)
VALUES (1, 7, 4990754425989121, '08/23', 021);
INSERT INTO DETAILPAIEMENT (idPaiement, idClient, numCarte, dateValid, cryptogramme)
VALUES (1, 8, 4990158895439622, '06/25', 420);
INSERT INTO DETAILPAIEMENT (idPaiement, idClient, numCarte, dateValid, cryptogramme)
VALUES (1, 9, 4990798425387223, '06/24', 157);
INSERT INTO DETAILPAIEMENT (idPaiement, idClient, numCarte, dateValid, cryptogramme)
VALUES (1, 10, 4990458535788626, '06/23', 123);	
