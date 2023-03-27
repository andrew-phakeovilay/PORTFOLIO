package pizzeria;

import pizza.FabriqueDePizzasBrest;

public class PizzeriaBrest extends Pizzeria {
	public PizzeriaBrest() {
		super(new FabriqueDePizzasBrest());
	}
}
