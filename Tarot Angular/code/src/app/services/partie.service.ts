import { Injectable } from '@angular/core';
import { Manche, Partie, Joueur } from '../models/partie/partie.component';

@Injectable({
  providedIn: 'root'
})
export class PartieService {

  private parties : Partie[] = []

  /**
   * Permet d'initialiser le JSON du serveur local
   */
  constructor() {
    const jsonParties = localStorage.getItem('listeParties')
    if(jsonParties){
      this.parties = JSON.parse(jsonParties)
    } else{
      this.saveAllParties()
    }
  }

  /**
   * Enregistre les parties dans le stockage local
   */
  private saveAllParties() : void{
    localStorage['listeParties'] = JSON.stringify(this.parties)
  }

  /** Retourne les parties
   *
   * @returns les parties
   */
  getParties() : Partie[]{
    return this.parties;
  }

  /** Retourne la partie en fonction de l'id
   *
   * @param id id de partie
   * @returns la partie
   */
  getPartie(id : number) : Partie | undefined {
    return this.parties.find(t => t.id == id)
  }

  /** Ajoute dans les parties la nouvelle partie
   *
   * @param nouvellePartie la nouvelle partie
   * @returns la nouvelle partie
   */
  addPartie(nouvellePartie:Partie) : Partie{
    if(this.parties.length==0){
      nouvellePartie.id = 1
    }else{
      nouvellePartie.id = 1 + this.parties[this.parties.length-1].id
    }
    this.parties.push(nouvellePartie)
    this.saveAllParties()
    return nouvellePartie
  }

  /** Met à jour la partie avec la nouvelle manche
   *
   * @param partie la partie
   * @param idManche id de la manche
   */
  updateManchePartie(partie:Partie, idManche:number){
    const partieIn = this.getPartie(partie.id)
    if(partieIn){
      for (let index = 0; index < partieIn.scores.length; index++) {
        partieIn.scores[index] = partieIn.scores[index] + partie.manches[idManche-1].listeScores[index]
      }
      Object.assign(partieIn, partie)
      this.saveAllParties()
    }
  }
}
