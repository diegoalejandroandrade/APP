package com.example.app;

public class Ingrediente {
//Agregue variable tipo String "tipoComida" la cual indica si el ingrediente es del desayuno, comida, etc.
	private String Nombre, Unidad, tipoComida;

	private double Kcal, Proteinas, Lipidos, Hidrocarburos, Racion, Porcion;

	public Ingrediente(String Nombre, double Porcion, String Unidad, double Kcal, double Proteinas, double Lipidos,
			double Hidrocarburos, String tipoComida) {

		this.Nombre = Nombre;
		this.Porcion = Porcion;
		this.Unidad = Unidad;
		this.Kcal = Kcal;
		this.Proteinas = Proteinas;
		this.Lipidos = Lipidos;
		this.Hidrocarburos = Hidrocarburos;
		this.Racion = 0;
		this.tipoComida = tipoComida;

	}

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	public String getUnidad() {
		return Unidad;
	}

	public void setUnidad(String unidad) {
		Unidad = unidad;
	}

	public double getPorcion() {
		return Porcion;
	}

	public void setPorcion(int porcion) {
		Porcion = porcion;
	}

	public double getKcal() {
		return Kcal;
	}

	public void setKcal(double kcal) {
		Kcal = kcal;
	}

	public double getProteinas() {
		return Proteinas;
	}

	public void setProteinas(double proteinas) {
		Proteinas = proteinas;
	}

	public double getLipidos() {
		return Lipidos;
	}

	public void setLipidos(double lipidos) {
		Lipidos = lipidos;
	}

	public double getHidrocarburos() {
		return Hidrocarburos;
	}

	public void setHidrocarburos(double hidrocarburos) {
		Hidrocarburos = hidrocarburos;
	}

	public double getRacion() {
		return Racion;
	}

	public void setRacion(double racion) {
		Racion = racion;
	}

	public String getTipoComida() {
		return tipoComida;
	}

	public void setTipoComida(String tipo) {
		tipoComida = tipo;
	}

}
