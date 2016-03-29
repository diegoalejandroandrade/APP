package com.example.app;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Registro extends AppCompatActivity implements View.OnClickListener {

	Button btnRegistro;
	EditText etNombreUsuario, etNombre, etContrasena, etEdad, etEstatura, etPeso, etrepContrasena;
	Spinner spSexo, spObjetivo;
	Usuario usuario;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registro);

		etNombreUsuario = (EditText) findViewById(R.id.etNombreUsuario);
		etNombre = (EditText) findViewById(R.id.etNombre);
		etContrasena = (EditText) findViewById(R.id.etContrasena);
		etrepContrasena = (EditText) findViewById(R.id.etRepContrasena);
		etEdad = (EditText) findViewById(R.id.etEdad);
		etEstatura = (EditText) findViewById(R.id.etEstatura);
		etPeso = (EditText) findViewById(R.id.etPeso);
		spSexo = (Spinner) findViewById(R.id.spSexo);
		btnRegistro = (Button) findViewById(R.id.btnRegistrarse);
		spObjetivo = (Spinner) findViewById(R.id.spObjetivo);
		btnRegistro.setOnClickListener(this);

	}

	private boolean validaCampos() {

		if (!etNombreUsuario.getText().toString().trim().equalsIgnoreCase("")
				|| !etContrasena.getText().toString().trim().equalsIgnoreCase("")
				|| !etNombre.getText().toString().trim().equalsIgnoreCase("")
				|| !etEdad.getText().toString().trim().equalsIgnoreCase("")
				|| !etEstatura.getText().toString().trim().equalsIgnoreCase("")
				|| !etPeso.getText().toString().trim().equalsIgnoreCase("")
				|| !etrepContrasena.getText().toString().trim().equalsIgnoreCase(""))

			return true;

		else
			return false;

	}

	private boolean validaContrasena() {
		if (etrepContrasena.getText().toString().trim().equals(etContrasena.getText().toString().trim()))
			return true;

		else
			return false;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnRegistrarse:
			if (validaCampos()) {

				if (validaContrasena()) {
					String nombreUsuario = etNombreUsuario.getText().toString();
					String contrasena = etContrasena.getText().toString();
					String nombre = etNombre.getText().toString();
					int edad = Integer.parseInt(etEdad.getText().toString());
					double estatura = Double.parseDouble(etEstatura.getText().toString());
					double peso = Double.parseDouble(etPeso.getText().toString());
					String objetivo = String.valueOf(spObjetivo.getSelectedItem());
					String sexo = String.valueOf(spSexo.getSelectedItem());

					usuario = new Usuario(nombreUsuario, contrasena, nombre, edad, estatura, peso, objetivo, sexo);
					RegistrarUsuario(usuario);
				} else
					Toast.makeText(Registro.this, "Asegurate de que la contraseña sea correcta", Toast.LENGTH_SHORT)
							.show();

			} else
				Toast.makeText(Registro.this, "Porfavor rellena todos los campos", Toast.LENGTH_SHORT).show();
			break;
		}
	}

	private void RegistrarUsuario(Usuario usuario) {
		/* Dialog para confirmar el envio */
		AlertDialog.Builder builder = new AlertDialog.Builder(Registro.this);

		builder.setMessage("Los datos son correctos?").setTitle("Confirmacion")
				.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						ManejadorPeticiones manejador = new ManejadorPeticiones(Registro.this);
						manejador.almacenarInfo(getUsuario(), new RespuestaServidor() {

							@Override
							public void hecho(Usuario UsuarioDevuelto, DietaSemana dietaSemana, Integer insertado) {
								if (insertado.equals(0)) {
									AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(Registro.this);
									dialogBuilder.setMessage("El nombre de usuario no esta disponible " + insertado);
									dialogBuilder.setPositiveButton("Ok", null);
									dialogBuilder.show();

								} else {
									AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(Registro.this);
									dialogBuilder.setMessage("Listo estas registrado\nya puedes loguearte :)");
									dialogBuilder.setPositiveButton("Ok", null);
									dialogBuilder.show();
								}
							}
						});

					}
				}).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {

						dialog.cancel();
					}
				});
		builder.show();

	}

	private Usuario getUsuario() {
		return this.usuario;
	}

}