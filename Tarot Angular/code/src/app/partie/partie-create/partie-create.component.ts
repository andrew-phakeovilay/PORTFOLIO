import { Component, Input } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Partie, Joueur } from 'src/app/models/partie/partie.component';
import { PartieService } from 'src/app/services/partie.service';

@Component({
  selector: 'app-partie-create',
  templateUrl: './partie-create.component.html',
  styleUrls: ['./partie-create.component.css']
})
export class PartieCreateComponent {
  @Input()
  public partie:Partie = new Partie()

  constructor(
    private partieService : PartieService,
    private router : Router,
    private route : ActivatedRoute
  ){}

  /** Après avoir soumis le formulaire, nous regardons s'il est valide :
   *  - On a bien les noms de joueurs de longueur supérieur à 3
   *  - Pas d'espace, ni de caractère spéciaux
   *  Nous créons ensuite la liste des joueurs avec les id et les noms
   *  Puis nous créons la nouvelle partie avec l'id qui changera, la liste des joueurs et les scores à 0
   *  On appelle ensuite la méthode addPartie qui va ajouté la partie dans la liste des parties
   *  On redirige vers la page de création de la manche 1 de la partie
   * Sinon on redirige vers la page de création de partie
  **/
   public onSubmit(leFormulaire: NgForm) : void{
    if(leFormulaire.valid){
      const listeJoueurs = [new Joueur(1,leFormulaire.value.J1), new Joueur(2,leFormulaire.value.J2), new Joueur(3,leFormulaire.value.J3), new Joueur(4,leFormulaire.value.J4)]
      const nouvellePartie = new Partie(0, listeJoueurs, [0,0,0,0])
      this.partieService.addPartie(nouvellePartie)
      this.router.navigateByUrl('/create/' + nouvellePartie.id + '/manche/1')
    }else{
    this.router.navigateByUrl('/create')
    }
  }
}
