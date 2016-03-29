package com.example.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
	Button btnLogout;
	TextView etNombre, etNombreUsuario, etEdad;
	AlmacenamientoLocalUsuario almacenamientolocalusuario;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		etNombre = (TextView) findViewById(R.id.etNombreMain);
		etNombreUsuario = (TextView) findViewById(R.id.etNombreUsuarioMain);
		etEdad = (TextView) findViewById(R.id.etEdadMain);
		almacenamientolocalusuario = new AlmacenamientoLocalUsuario(this);
	}

	@Override
	protected void onStart() {
		super.onStart();
		if (Autentificar() == true) {

			Intent IntentMenuPrincipal = new Intent(getApplicationContext(), MenuPrincipal.class);
			startActivity(IntentMenuPrincipal);
			finish();

		}
	}

	private boolean Autentificar() {
		if (almacenamientolocalusuario.getLogueado() == false) {
			Intent IntentLogin = new Intent(getApplicationContext(), Login.class);
			startActivity(IntentLogin);
			finish();
			return false;
		}
		return true;
	}
	/*
	 * private void MostrarDetallesUsuario() { Usuario usuario =
	 * almacenamientolocalusuario.obtenerUsuarioLogueado();
	 * etNombreUsuario.setText(usuario.getNombreUsuario());
	 * etNombre.setText(usuario.getNombre()); etEdad.setText(usuario.getEdad() +
	 * ""); }
	 */

	public void onClickCerrarSesion(View view) {
		almacenamientolocalusuario.BorrarDatosUsuario();
		almacenamientolocalusuario.setLogueado(false);
		Intent IntentLogin = new Intent(this, Login.class);
		startActivity(IntentLogin);
		finish();
	}

}
