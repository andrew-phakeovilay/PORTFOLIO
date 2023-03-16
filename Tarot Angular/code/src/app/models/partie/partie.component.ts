// La classe Manche
export class Manche{
  constructor(
    public id : number = 0,
    public listeScores : number[] = new Array<number>
  ){}
}

// La classe Partie
export class Partie {
  constructor(
    public id : number = 0,
    public listeJoueurs : Joueur[] = new Array<Joueur>,
    public scores : number[] = new Array<number>,
    public manches : Manche[] = new Array<Manche>
  ){}
}


// La classe Joueur
export class Joueur {
  constructor(
    public id : number,
    public nom : string
  ){}
}

// Enumération des types de contrat
export enum Contrat{
  petite   = "Petite",
  garde  = "Garde",
  gardeSans = "Garde sans",
  gardeContre = "Garde contre"
}

// Enumération des types de poignée
export enum Poignee{
  zero = "Zéro",
  simple = "Simple",
  double = "Double",
  triple = "Triple"
}
