package com.example.app;

import android.content.Context;
import android.content.SharedPreferences;

public class AlmacenamientoLocalUsuario {

	float Peso_Aux;
	float Altura_Aux;
	public static final String SP_NAME = "DetallesUsuario";
	SharedPreferences BDLocal;

	public AlmacenamientoLocalUsuario(Context contexto) {
		BDLocal = contexto.getSharedPreferences(SP_NAME, 0);
	}

	public void AlmacenarDatosUsuario(Usuario usuario) {
		SharedPreferences.Editor spEditor = BDLocal.edit();
		spEditor.putString("Usuario", usuario.getNombreUsuario());
		spEditor.putString("Contrasena", usuario.getContrasena());
		spEditor.putString("Nombre", usuario.getNombre());
		spEditor.putInt("Edad", usuario.getEdad());
		spEditor.putFloat("Peso", Peso_Aux = (float) usuario.getPeso());
		spEditor.putFloat("Altura", Altura_Aux = (float) usuario.getAltura());
		spEditor.putString("Sexo", usuario.getSexo());
		spEditor.putString("Objetivo", usuario.getObjetivo());
		spEditor.commit();
	}

	public Usuario obtenerUsuarioLogueado() {
		String Usuario = BDLocal.getString("Usuario", "");
		String Contrasena = BDLocal.getString("Contrasena", "");
		String Nombre = BDLocal.getString("Nombre", "");
		int Edad = BDLocal.getInt("Edad", -1);
		Float Peso = BDLocal.getFloat("Peso", -1);
		Float Altura = BDLocal.getFloat("Altura", -1);
		String Sexo = BDLocal.getString("Sexo", "");
		String Objetivo = BDLocal.getString("Objetivo", "");

		Usuario UsuarioAlmacenado = new Usuario(Usuario, Contrasena, Nombre, Edad, Altura, Peso, Objetivo, Sexo);
		return UsuarioAlmacenado;
	}

	public void setLogueado(boolean Logueado) {
		SharedPreferences.Editor spEdior = BDLocal.edit();
		spEdior.putBoolean("Logueado", Logueado);
		spEdior.commit();
	}

	public boolean getLogueado() {
		if (BDLocal.getBoolean("Logueado", false) == true) {
			return true;
		} else {
			return false;
		}
	}

	public void BorrarDatosUsuario() {
		SharedPreferences.Editor spEditor = BDLocal.edit();
		spEditor.clear();
		spEditor.commit();
	}
}
