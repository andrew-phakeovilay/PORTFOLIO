package main;

import pizza.NomPizzas;
import pizza.Pizza;
import pizzeria.Pizzeria;
import pizzeria.PizzeriaBrest;
import pizzeria.PizzeriaStrasbourg;

public class PizzeriaTest {
	public static void main(String[] args) {
		Pizzeria boutiqueBrest = new PizzeriaBrest();
		Pizzeria boutiqueStrasbourg = new PizzeriaStrasbourg();

		Pizza pizza = boutiqueBrest.commanderPizza(NomPizzas.Fromage);
		System.out.println("JMB a commandé une " + pizza.getNom() + "\n");

		pizza = boutiqueStrasbourg.commanderPizza(NomPizzas.Fromage);
		System.out.println("AP a commandé une " + pizza.getNom() + "\n");
		
		pizza = boutiqueBrest.commanderPizza(NomPizzas.Brestoise);
		System.out.println("1 a commandé une " + pizza.getNom() + "\n");
		
		pizza = boutiqueStrasbourg.commanderPizza(NomPizzas.Strasbourgeoise);
		System.out.println("2 a commandé une " + pizza.getNom() + "\n");
	}
}