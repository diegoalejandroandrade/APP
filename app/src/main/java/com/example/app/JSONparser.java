package com.example.app;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

public class JSONparser {

	public JSONparser() {

	}

	/* Metodo para obtener la dieta de una semana */
	public DietaSemana obtenerDietaSemana(JSONObject json) throws JSONException {
		return new DietaSemana(obtenerDietaDia(json.getJSONObject("dia1")), obtenerDietaDia(json.getJSONObject("dia2")),
				obtenerDietaDia(json.getJSONObject("dia3")), obtenerDietaDia(json.getJSONObject("dia4")),
				obtenerDietaDia(json.getJSONObject("dia5")), obtenerDietaDia(json.getJSONObject("dia6")),
				obtenerDietaDia(json.getJSONObject("dia7")));

	}

	/* Metodo para obtener la dieta de un dia */
	private DietaDia obtenerDietaDia(JSONObject json) throws JSONException {
		return new DietaDia(obtenerIngredientes(json.getJSONObject("desayuno"),"desayuno"),
				obtenerIngredientes(json.getJSONObject("colacion1"),"colacion1"), obtenerIngredientes(json.getJSONObject("comida"),"comida"),
				obtenerIngredientes(json.getJSONObject("colacion2"),"colacion2"), obtenerIngredientes(json.getJSONObject("cena"),"cena"));
	}

	/* Metodo para obtener ingredientes */
	private ArrayList<Ingrediente> obtenerIngredientes(JSONObject json, String cad) throws JSONException {
		ArrayList<Ingrediente> ingredientes = new ArrayList<Ingrediente>();
		for (int i = 0; i < json.length(); i++) {
			ingredientes.add(new Ingrediente(json.getJSONObject("ingrediente" + i).getString("NombreIngrediente"),
					json.getJSONObject("ingrediente" + i).getDouble("Porcion"),
					json.getJSONObject("ingrediente" + i).getString("Unidad"),
					json.getJSONObject("ingrediente" + i).getDouble("Kcal"),
					json.getJSONObject("ingrediente" + i).getDouble("Proteinas"),
					json.getJSONObject("ingrediente" + i).getDouble("Lipidos"),
					json.getJSONObject("ingrediente" + i).getDouble("Hidrocarburos"),
					cad
					));

		}
		return ingredientes;
	}
}
