package pizzeria;

import pizza.NomPizzas;
import pizza.Pizza;
import pizza.SimpleFabriqueDePizzas;

public class Pizzeria {
	private SimpleFabriqueDePizzas sfp;
	
	public Pizzeria(SimpleFabriqueDePizzas _sfp) {
		this.sfp = _sfp;
	}
	
	public Pizza commanderPizza(NomPizzas _nom) {
		Pizza pizza = sfp.creerPizza(_nom);
		pizza.preparer();
		pizza.cuire();
		pizza.couper();
		pizza.emballer();
		return pizza;
	}
}
