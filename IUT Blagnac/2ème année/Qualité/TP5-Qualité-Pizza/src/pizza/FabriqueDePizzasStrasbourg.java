package pizza;

public class FabriqueDePizzasStrasbourg extends SimpleFabriqueDePizzas {

	@Override
	public Pizza creerPizza(NomPizzas _nom) {
		switch(_nom) {
		case Strasbourgeoise:
			return new PizzaStrasbourg("strasbourgeoise", "épaisse", "tomate");
		case Poivron:
			return new PizzaPoivron("poivron", "craquante", "tomate");
		case Fromage:
			return new PizzaFromage("fromage", "fine", "crème");
		case Brestoise:
			return new PizzaBrestoise("brestoise", "craquante", "tomate");
		default:
			return new PizzaGrecque("grecque", "fine", "crème");
		}
	}

}
