package pizzeria;

import pizza.FabriqueDePizzasStrasbourg;

public class PizzeriaStrasbourg extends Pizzeria{
	public PizzeriaStrasbourg() {
		super(new FabriqueDePizzasStrasbourg());
	}
}
