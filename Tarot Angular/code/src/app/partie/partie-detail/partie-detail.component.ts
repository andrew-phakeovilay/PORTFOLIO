import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Partie } from 'src/app/models/partie/partie.component';
import { PartieService } from 'src/app/services/partie.service';

@Component({
  selector: 'app-partie-detail',
  templateUrl: './partie-detail.component.html',
  styleUrls: ['./partie-detail.component.css']
})
export class PartieDetailComponent {
  partie : Partie = new Partie()

  constructor(
    private partieService : PartieService,
    private router : Router,
    private route : ActivatedRoute
  ){}

  /** Pour regarder les détails d'une partie
   * On prend en compte l'id dans l'URL et cherchons si il y a une partie avec cette id
   * Si la partie n'existe pas alors on redirige vers l'accueil sinon on attribue la partie
   */
  ngOnInit(): void{
    const id = this.route.snapshot.params['id']
    const partie = this.partieService.getPartie(id)
    if(partie===undefined || partie==null){
      this.router.navigateByUrl('/')
    } else {
      this.partie = partie
    }
  }
}
