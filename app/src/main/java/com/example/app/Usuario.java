package com.example.app;

public class Usuario {

	private String nombreUsuario, nombre, contrasena, objetivo, sexo;
	private int edad;
	private double altura, peso;

	public Usuario(String nombreUsuario, String contrasena, String nombre, int edad, double altura, double peso,
			String objetivo, String sexo) {
		this.nombreUsuario = nombreUsuario;
		this.contrasena = contrasena;
		this.nombre = nombre;
		this.edad = edad;
		this.altura = altura;
		this.peso = peso;
		this.objetivo = objetivo;
		this.sexo = sexo;
	}

	public Usuario(String nombreUsuario, String contrasena) {
		this.nombreUsuario = nombreUsuario;
		this.contrasena = contrasena;
		this.edad = -1;
		this.nombre = "";
		this.altura = -1;
		this.peso = -1;
		this.objetivo = "";
		this.sexo = "";
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public String getNombre() {
		return nombre;
	}

	public String getObjetivo() {
		return objetivo;
	}

	public String getSexo() {
		return sexo;
	}

	public int getEdad() {
		return edad;
	}

	public double getAltura() {
		return altura;
	}

	public double getPeso() {
		return peso;
	}

	public String getContrasena() {
		return contrasena;
	}

}
