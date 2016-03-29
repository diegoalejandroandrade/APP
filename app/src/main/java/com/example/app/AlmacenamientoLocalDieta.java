package com.example.app;

import java.io.File;
import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class AlmacenamientoLocalDieta {
	Context c;
	ManejadorBD manejadorBD;
	private final static String DB_NAME = "dietaBD";

	public AlmacenamientoLocalDieta(Context contexto) {
		this.c = contexto;
	}

	public DietaSemana cargar() {
		DietaSemana dietaSemana = new DietaSemana(cargarDia("DietaDia1"), cargarDia("DietaDia2"),
				cargarDia("DietaDia3"), cargarDia("DietaDia4"), cargarDia("DietaDia5"), cargarDia("DietaDia6"),
				cargarDia("DietaDia7"));
		// Toast.makeText(c, "Dieta Semanal almacenada",
		// Toast.LENGTH_SHORT).show();
		return dietaSemana;

	}

	public DietaDia cargarDia(String tabla) {
		manejadorBD = new ManejadorBD(c, DB_NAME, null, 1);
		SQLiteDatabase bd = manejadorBD.getWritableDatabase();
		ArrayList<Ingrediente> ingredientes = new ArrayList<>();

		Cursor c = bd.rawQuery("select * from " + tabla, null);

		if (c.moveToFirst()) {
			do {

				ingredientes.add(new Ingrediente(c.getString(0), c.getDouble(1), c.getString(2), c.getDouble(3),
						c.getDouble(4), c.getDouble(5), c.getDouble(6), c.getString(7)));

			} while (c.moveToNext());
		}

		bd.close();
		return new DietaDia(ingredientes);

	}

	public void guardaDietaSemana(DietaSemana dietaSemana) {

		guardaDietaDia(dietaSemana.getDietaDia1(), "DietaDia1");
		guardaDietaDia(dietaSemana.getDietaDia2(), "DietaDia2");
		guardaDietaDia(dietaSemana.getDietaDia3(), "DietaDia3");
		guardaDietaDia(dietaSemana.getDietaDia4(), "DietaDia4");
		guardaDietaDia(dietaSemana.getDietaDia5(), "DietaDia5");
		guardaDietaDia(dietaSemana.getDietaDia6(), "DietaDia6");
		guardaDietaDia(dietaSemana.getDietaDia7(), "DietaDia7");

	}

	public void guardaDietaDia(DietaDia dietaDia, String tabla) {
		guardaIngredientesDietaDia(dietaDia.getDesayuno(), tabla,"desayuno");
		guardaIngredientesDietaDia(dietaDia.getColacion1(), tabla,"colacion1");
		guardaIngredientesDietaDia(dietaDia.getComida(), tabla,"comida");
		guardaIngredientesDietaDia(dietaDia.getColacion2(), tabla,"colacion2");
		guardaIngredientesDietaDia(dietaDia.getCena(), tabla,"cena");

	}
//Agregue parametro String tipoComida para guardar en la base de datos local si el ingrediente es del desayuno,
//Comida, etc.

	public void guardaIngredientesDietaDia(ArrayList<Ingrediente> ingredientes, String tabla, String tipoComida) {
		for (int i = 0; i < ingredientes.size(); i++) {
			guardaIngrediente(tabla, ingredientes.get(i).getNombre(), ingredientes.get(i).getPorcion(),
					ingredientes.get(i).getUnidad(), ingredientes.get(i).getKcal(), ingredientes.get(i).getProteinas(),
					ingredientes.get(i).getLipidos(), ingredientes.get(i).getHidrocarburos(),tipoComida);
		}
	}

	public void guardaIngrediente(String tabla, String nombre, double porcion, String unidad, double kcal,
			double proteinas, double lipidos, double hidrocarburos,String tipoComida) {

		manejadorBD = new ManejadorBD(c, DB_NAME, null, 1);
		SQLiteDatabase bd = manejadorBD.getWritableDatabase();

		ContentValues dietaDia = new ContentValues();
		dietaDia.put("nombreIngrediente", nombre);
		dietaDia.put("porcion", porcion);
		dietaDia.put("unidad", unidad);
		dietaDia.put("kcal", kcal);
		dietaDia.put("proteinas", proteinas);
		dietaDia.put("lipidos", lipidos);
		dietaDia.put("hidrocarburos", hidrocarburos);
        dietaDia.put("tipoComida",tipoComida);
		long i = bd.insert(tabla, null, dietaDia);
		if (i > 0) {
			// Toast.makeText(c, "Dieta Dia almacenada",
			// Toast.LENGTH_SHORT).show();
		}

		bd.close();
	}

	public void BorrarDietaAlmacenada() {
		c.deleteDatabase(DB_NAME);
	}

	public boolean existeDB() {
		File fileDB = null;
		fileDB = c.getApplicationContext().getDatabasePath(DB_NAME);
		boolean checkDB = fileDB.exists();
		return (checkDB);
	}

}
