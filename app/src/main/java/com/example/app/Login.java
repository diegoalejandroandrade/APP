package com.example.app;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity implements OnClickListener {

	Button btnLogin, btnRegistrarse;
	EditText etUsuario, etContrasena;
	AlmacenamientoLocalUsuario almacenamientolocalusuario;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		etUsuario = (EditText) findViewById(R.id.etUsuario);
		etContrasena = (EditText) findViewById(R.id.etContrasena);
		btnLogin = (Button) findViewById(R.id.btnLogin);
		btnRegistrarse = (Button) findViewById(R.id.btnRegistrarse);
		almacenamientolocalusuario = new AlmacenamientoLocalUsuario(this);
		btnLogin.setOnClickListener(this);
		btnRegistrarse.setOnClickListener(this);

	}

	public boolean validaCampos() {

		if (!etUsuario.getText().toString().trim().equalsIgnoreCase("")
				|| !etContrasena.getText().toString().trim().equalsIgnoreCase(""))
			return true;
		else
			return false;

	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.btnLogin:
			if (validaCampos()) {
				String NombreUsuario = etUsuario.getText().toString();
				String Contrasena = etContrasena.getText().toString();
				Usuario usuario = new Usuario(NombreUsuario, Contrasena);
				Autentificar(usuario);
			} else
				Toast.makeText(Login.this, "Hay informacion por rellenar", Toast.LENGTH_SHORT).show();
			break;

		case R.id.btnRegistrarse:

			ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
			if (networkInfo != null && networkInfo.isConnected()) {

				Intent IntentRegistro = new Intent(Login.this, Registro.class);
				startActivity(IntentRegistro);

			} else {
				/* Dialog para mensaje de falla de conectividad */
				AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(Login.this);
				dialogBuilder.setMessage("Error-Verifica que tu conexion a Internet\n funcione correctamente");
				dialogBuilder.setPositiveButton("Ok", null);
				dialogBuilder.show();
			}

			break;
		}
	}

	/*
	 * 
	 * public void onClickRegistrarse(View view){ Intent intent = new
	 * Intent(this,advertencia.class); startActivity(intent);
	 */

	private void Autentificar(Usuario usuario) {
		/*
		 * El conectivity manager se encarga de revisar si tenemos conexion a
		 * internet
		 */
		ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
		if (networkInfo != null && networkInfo.isConnected()) {
			ManejadorPeticiones Manejador = new ManejadorPeticiones(this);
			Manejador.recuperarInfo(usuario, new RespuestaServidor() {
				@Override
				public void hecho(Usuario UsuarioDevuelto, DietaSemana dietaSemana, Integer insertado) {
					if (UsuarioDevuelto == null) {
						showErrorMessage();
					} else {
						LoguearUsuario(UsuarioDevuelto);
					}
				}

			});

		} else {
			/* Dialog para mensaje de falla de conectividad */
			AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(Login.this);
			dialogBuilder.setMessage("Error-Verifica que tu conexion a Internet\n funcione correctamente");
			dialogBuilder.setPositiveButton("Ok", null);
			dialogBuilder.show();
		}
	}

	private void showErrorMessage() {
		AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(Login.this);
		dialogBuilder.setMessage("Usuario o contraseña no validos");
		dialogBuilder.setPositiveButton("Ok", null);
		dialogBuilder.show();
	}

	private void LoguearUsuario(Usuario UsuarioDevuelto) {
		almacenamientolocalusuario.AlmacenarDatosUsuario(UsuarioDevuelto);
		almacenamientolocalusuario.setLogueado(true);
		// startActivity(new Intent(this, MainActivity.class));

		Intent IntentMenuPrincipal = new Intent(Login.this, MenuPrincipal.class);
		startActivity(IntentMenuPrincipal);
		finish();

	}

}
