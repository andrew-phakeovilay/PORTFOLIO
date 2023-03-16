import { Component } from '@angular/core';
import { Manche, Partie } from 'src/app/models/partie/partie.component';
import { PartieService } from 'src/app/services/partie.service';

@Component({
  selector: 'app-partie-list',
  templateUrl: './partie-list.component.html',
  styleUrls: ['./partie-list.component.css']
})
export class PartieListComponent {
  public parties : Partie[] = []

  constructor( private partieService : PartieService ){
  }

  // On récupère la liste des parties avec getParties()
  ngOnInit(){
    this.parties = this.partieService.getParties()
  }
}
