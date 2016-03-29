package com.example.app;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class ManejadorBD extends SQLiteOpenHelper {
	/* Query para crear la tabla */
	String tabla1 = "CREATE TABLE DietaDia1(nombreIngrediente TEXT,porcion INT,unidad TEXT, kcal DOUBLE,proteinas DOUBLE,lipidos DOUBLE,hidrocarburos DOUBLE,tipoComida TEXT)";
	String tabla2 = "CREATE TABLE DietaDia2(nombreIngrediente TEXT,porcion INT,unidad TEXT,kcal DOUBLE,proteinas DOUBLE,lipidos DOUBLE,hidrocarburos DOUBLE,tipoComida TEXT)";
	String tabla3 = "CREATE TABLE DietaDia3(nombreIngrediente TEXT,porcion INT,unidad TEXT,kcal DOUBLE,proteinas DOUBLE,lipidos DOUBLE,hidrocarburos DOUBLE,tipoComida TEXT)";
	String tabla4 = "CREATE TABLE DietaDia4(nombreIngrediente TEXT,porcion INT,unidad TEXT,kcal DOUBLE,proteinas DOUBLE,lipidos DOUBLE,hidrocarburos DOUBLE,tipoComida TEXT)";
	String tabla5 = "CREATE TABLE DietaDia5(nombreIngrediente TEXT,porcion INT,unidad TEXT,kcal DOUBLE,proteinas DOUBLE,lipidos DOUBLE,hidrocarburos DOUBLE,tipoComida TEXT)";
	String tabla6 = "CREATE TABLE DietaDia6(nombreIngrediente TEXT,porcion INT,unidad TEXT, kcal DOUBLE,proteinas DOUBLE,lipidos DOUBLE,hidrocarburos DOUBLE,tipoComida TEXT)";
	String tabla7 = "CREATE TABLE DietaDia7(nombreIngrediente TEXT,porcion INT,unidad TEXT, kcal DOUBLE,proteinas DOUBLE,lipidos DOUBLE,hidrocarburos DOUBLE,tipoComida TEXT)";

	public ManejadorBD(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);

	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		/* Ejecutamos el query */
		db.execSQL(tabla1);
		db.execSQL(tabla2);
		db.execSQL(tabla3);
		db.execSQL(tabla4);
		db.execSQL(tabla5);
		db.execSQL(tabla6);
		db.execSQL(tabla7);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

}
