import { NgFor } from '@angular/common';
import { Component, Input } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Partie, Manche, Contrat, Poignee } from 'src/app/models/partie/partie.component';
import { PartieService } from 'src/app/services/partie.service';

@Component({
  selector: 'app-partie-manche',
  templateUrl: './partie-manche.component.html',
  styleUrls: ['./partie-manche.component.css']
})
export class PartieMancheComponent {

  public partie: Partie = new Partie()
  public manche: Manche = new Manche()
  public attaquant: any
  readonly typeContrat = Contrat
  readonly typePoignee = Poignee

  constructor(
    private partiesService: PartieService,
    private router: Router,
    private route: ActivatedRoute
  ) { }

  /** On regarde si l'id de l'URL est défini, puis s'il existe une partie avec cette id
   * S'il existe et que il n'y a pas de manche alors
   * on attribue la partie
   * Sinon on redirige vers la création de partie
   */
  ngOnInit(): void {
    const idPartie = this.route.snapshot.params["id"]
    if (idPartie === undefined) {
    } else {
      const partie = this.partiesService.getPartie(idPartie)
      if (partie === undefined || partie.manches.length != 0) {
        this.router.navigateByUrl('/create')
      } else {
        this.partie = partie
      }
    }
  }
  /** Permet à l'utilisateur de soumettre la manche à la partie et effectuer une nouvelle manche
   *
   * @param leFormulaire Le formulaire de la création de la manche
   */
  public onSubmit(leFormulaire: NgForm): void {
    if (leFormulaire.valid) {
      const result = this.calculate(leFormulaire)
      const idNewManche = Number(this.route.snapshot.params["idManche"])
      if (idNewManche === undefined) {
      } else {
        const nouvelleManche = new Manche(idNewManche, result)
        if (nouvelleManche === undefined) {
          this.router.navigateByUrl('/create')
        } else {
          this.partie.manches.push(nouvelleManche)
          this.partiesService.updateManchePartie(this.partie, idNewManche)
          const idManche = idNewManche + 1
          this.router.navigateByUrl("/create/" + this.partie.id + "/manche/" + idManche)
          leFormulaire.reset()
        }
      }
    }
  }

  /** Permet à l'utilisateur de soumettre la manche à la partie et de finir la création de la partie
   *
   * @param leFormulaire Le formulaire de la création de la manche
   */
  public onFinish(leFormulaire: NgForm): void {
    if (leFormulaire.valid) {
      const result = this.calculate(leFormulaire)
      const idManche = this.route.snapshot.params["idManche"]
      if (idManche === undefined) {
      } else {
        const nouvelleManche = new Manche(idManche, result)
        if (nouvelleManche === undefined) {
          this.router.navigateByUrl('/create')
        } else {
          this.partie.manches.push(nouvelleManche)
          this.partiesService.updateManchePartie(this.partie, idManche)
          this.router.navigateByUrl("/history")
        }
      }
    }
  }

  /** Permet de faire le calcul des points du tarot
   *
   * @param leFormulaire Le formulaire de la création de la manche
   * @returns le score des joueurs de la manche
   */
  public calculate(leFormulaire: NgForm): number[] {
    const bonus = this.setBonus(leFormulaire.value.bonus)
    const pointsBout = this.getPointsBout(leFormulaire.value.bout)
    const contratMult = this.getContratMult(leFormulaire.value.contrat)
    const poigneePts = this.getPoigneePts(leFormulaire.value.poignee)
    const pointsAtk = (25 + Math.abs(leFormulaire.value.points - pointsBout) + bonus) * contratMult + poigneePts
    const listeFinalResultPoints = this.getFinalResult(pointsAtk, leFormulaire.value.result)
    const scorePoints = new Array<number>

    for (let index = 1; index <= 4; index++) {
      if (leFormulaire.value.attaquant == index) {
        scorePoints.push(listeFinalResultPoints[0])
      }
      else {
        scorePoints.push(listeFinalResultPoints[1])
      }
    }
    return scorePoints
  }

  /** Calcule les points de l'attaquant et les défenseurs en fonction du résultat
   *
   * @param pointsAtk Points de l'attaquant
   * @param result Gagner ou Perdu
   * @returns la liste de score, indice 0 pour l'attaquant et indice 1 pour les défenseurs
   */
  public getFinalResult(pointsAtk: number, result: String): number[] {
    const score = new Array<number>
    if (result == "gagner") {
      score.push(pointsAtk * 3)
      score.push(pointsAtk * -1)
    } else {
      score.push(pointsAtk * -3)
      score.push(pointsAtk * 1)
    }
    return score
  }

  /** Retourne les points bonus
   *
   * @param bonus booléen le bonus du petit au bout
   * @returns les points bonus
   */
  public setBonus(bonus: boolean): number {
    if (bonus) {
      return 10
    }
    return 0
  }

  /** Retourne les points du bout en fonction du bout du formulaire
   *
   * @param bout le nombre de bout
   * @returns les points du bout
   */
  public getPointsBout(bout: String): number {
    switch (bout) {
      case "Zéro":
        return 56
      case "Un":
        return 51
      case "Deux":
        return 41
      case "Trois":
        return 36
      default:
        return 0
    }
  }

  /** Retourne le multiplicateur du contrat choisi
   *
   * @param contrat le contrat choisi dans le formulaire
   * @returns le multiplicateur
   */
  public getContratMult(contrat: String): number {
    switch (contrat) {
      case "Petite":
        return 1
      case "Garde":
        return 2
      case "Garde sans":
        return 4
      case "Garde contre":
        return 6
      default:
        return 0
    }
  }

  /** Retourne les points du poignée
   *
   * @param poignee type de poignée
   * @returns les points du poignée
   */
  public getPoigneePts(poignee: String): number {
    switch (poignee) {
      case "Simple":
        return 20
      case "Double":
        return 30
      case "Triple":
        return 40
      default:
        return 0
    }
  }
}
