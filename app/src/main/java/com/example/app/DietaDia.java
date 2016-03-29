package com.example.app;

import java.util.ArrayList;

public class DietaDia {
	ArrayList<Ingrediente> desayuno, comida, cena, colacion1, colacion2, dietaDiaCompleta;

	public DietaDia(ArrayList<Ingrediente> desayuno, ArrayList<Ingrediente> colacion1, ArrayList<Ingrediente> comida,
			ArrayList<Ingrediente> colacion2, ArrayList<Ingrediente> cena) {

		this.desayuno = desayuno;
		this.comida = comida;
		this.cena = cena;
		this.colacion1 = colacion1;
		this.colacion2 = colacion2;

	}

	public DietaDia(ArrayList<Ingrediente> dietaDiaCompleta) {
		this.dietaDiaCompleta = dietaDiaCompleta;
	}

	public ArrayList<Ingrediente> getDietaDiaCompleta() {
		return dietaDiaCompleta;
	}

	public ArrayList<Ingrediente> getDesayuno() {
		return desayuno;
	}

	public ArrayList<Ingrediente> getComida() {
		return comida;
	}

	public ArrayList<Ingrediente> getCena() {
		return cena;
	}

	public ArrayList<Ingrediente> getColacion1() {
		return colacion1;
	}

	public ArrayList<Ingrediente> getColacion2() {
		return colacion2;
	}

}
