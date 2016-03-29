package com.example.app;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

public class ManejadorPeticiones {
	public static final String SERVER_ADDRESS = "http://k120.comxa.com/prueba/";
	// public static final String SERVER_ADDRESS =
	// "http://192.168.1.70/prueba/";
	public static final String CONEXION = "CONEXION.php";
	public static final String REGISTRAUSUARIO = "REGISTRAUSUARIO.php";
	public static final String RECUPERAUSUARIO = "RECUPERAUSUARIO.php";
	public static final String GENERADIETAS = "GENERADIETAS.php";
	ProgressDialog progressDialog;
	Context context;
	AlmacenamientoLocalDieta almacenamientolocaldieta;

	public ManejadorPeticiones(Context context) {
		this.context = context;
		progressDialog = new ProgressDialog(context);
		progressDialog.setCancelable(false);
		progressDialog.setTitle("Procesando...");
		progressDialog.setMessage("Porfavor espera...");
		almacenamientolocaldieta = new AlmacenamientoLocalDieta(context);
	}

	public void peticionDietas(RespuestaServidor obtenerrespuestausuario) {
		progressDialog.show();
		new obtenerDietaSemanal(obtenerrespuestausuario).execute();
	}

	public void almacenarInfo(Usuario usuario, RespuestaServidor obtenerrespuestausuario) {
		progressDialog.show();
		new AlmacenarInfoUsuario(usuario, obtenerrespuestausuario).execute();
	}

	public void recuperarInfo(Usuario usuario, RespuestaServidor obtenerrespuestausuario) {
		progressDialog.show();
		new RecuperarInfoUsuario(usuario, obtenerrespuestausuario).execute();

	}

	public class obtenerDietaSemanal extends AsyncTask<Void, Void, DietaSemana> {

		RespuestaServidor obtenerrespuestausuario;
		JSONparser p = new JSONparser();

		public obtenerDietaSemanal(RespuestaServidor obtenerrespuestausuario) {

			this.obtenerrespuestausuario = obtenerrespuestausuario;
		}

		@Override
		protected DietaSemana doInBackground(Void... params) {
			DietaSemana dietaSemanaDevuelta = null;

			try {

				URL url = new URL(SERVER_ADDRESS + GENERADIETAS);
				HttpURLConnection con = (HttpURLConnection) url.openConnection();
				con.setDoOutput(true);
				con.setDoInput(true);
				con.setRequestMethod("POST");
				con.setReadTimeout(10000);
				con.setConnectTimeout(15000);
				StringBuilder sb = new StringBuilder();
				BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
				String line;
				while ((line = reader.readLine()) != null) {
					sb.append(line);
				}
				line = sb.toString();
				reader.close();

				dietaSemanaDevuelta = p.obtenerDietaSemana(new JSONObject(line));
				if (dietaSemanaDevuelta != null)
					almacenamientolocaldieta.guardaDietaSemana(dietaSemanaDevuelta);

				con.disconnect();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return dietaSemanaDevuelta;

		}

		@Override
		protected void onPostExecute(DietaSemana dietaSemanaDevuelta) {
			super.onPostExecute(dietaSemanaDevuelta);
			progressDialog.dismiss();
			obtenerrespuestausuario.hecho(null, dietaSemanaDevuelta, null);

		}
	}

	public class RecuperarInfoUsuario extends AsyncTask<Void, String, Usuario> {
		Usuario usuario;
		RespuestaServidor obtenerrespuestausuario;

		RecuperarInfoUsuario(Usuario usuario, RespuestaServidor obtenerrespuestausuario) {
			this.usuario = usuario;
			this.obtenerrespuestausuario = obtenerrespuestausuario;
		}

		@Override
		protected Usuario doInBackground(Void... params) {
			Usuario UsuarioDevuelto = null;
			Map<String, String> dataToSend = new HashMap<>();
			dataToSend.put("nombreUsuario", usuario.getNombreUsuario());
			dataToSend.put("contrasena", usuario.getContrasena());
			String encodedStr = getEncodedData(dataToSend);
			try {
				HttpURLConnection con = Conexion(RECUPERAUSUARIO, encodedStr);
				StringBuilder sb = new StringBuilder();
				BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
				String line;
				while ((line = reader.readLine()) != null) {
					sb.append(line);
				}
				line = sb.toString();
				reader.close();
				con.disconnect();

				try {

					JSONObject jObject = new JSONObject(line);

					if (jObject.length() != 0) {
						String Nombre = jObject.getString("nombre");
						int Edad = jObject.getInt("edad");
						double Altura = jObject.getDouble("altura");
						double Peso = jObject.getDouble("peso");
						String Objetivo = jObject.getString("objetivo");
						String Sexo = jObject.getString("sexo");
						UsuarioDevuelto = new Usuario(usuario.getNombreUsuario(), usuario.getContrasena(), Nombre, Edad,
								Altura, Peso, Objetivo, Sexo);
					}

				} catch (Exception e) {
					e.printStackTrace();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

			return UsuarioDevuelto;
		}

		@Override
		protected void onPostExecute(Usuario UsuarioDevuelto) {
			super.onPostExecute(UsuarioDevuelto);
			progressDialog.dismiss();
			obtenerrespuestausuario.hecho(UsuarioDevuelto, null, null);

		}

	}

	public class AlmacenarInfoUsuario extends AsyncTask<Void, Void, Integer> {
		Usuario usuario;
		RespuestaServidor obtenerrespuestausuario;

		AlmacenarInfoUsuario(Usuario usuario, RespuestaServidor obtenerrespuestausuario) {
			this.usuario = usuario;
			this.obtenerrespuestausuario = obtenerrespuestausuario;

		}

		@Override
		protected Integer doInBackground(Void... params) {
			int res = 0;
			Map<String, String> dataToSend = new HashMap<>();
			dataToSend.put("nombreUsuario", usuario.getNombreUsuario());
			dataToSend.put("contrasena", usuario.getContrasena());
			dataToSend.put("nombre", usuario.getNombre());
			dataToSend.put("edad", usuario.getEdad() + "");
			dataToSend.put("altura", usuario.getAltura() + "");
			dataToSend.put("peso", usuario.getPeso() + "");
			dataToSend.put("objetivo", usuario.getObjetivo());
			dataToSend.put("sexo", usuario.getSexo());

			String encodedStr = getEncodedData(dataToSend);
			HttpURLConnection con = Conexion(REGISTRAUSUARIO, encodedStr);
			StringBuilder sb = new StringBuilder();
			try {
				BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
				String line;

				while ((line = reader.readLine()) != null) {
					sb.append(line);
				}
				line = sb.toString();
				res = Integer.parseInt(line);
				reader.close();
				con.disconnect();

			} catch (Exception e) {

				e.printStackTrace();
			}

			return res;
		}

		@Override
		protected void onPostExecute(Integer result) {
			super.onPostExecute(result);
			progressDialog.dismiss();
			obtenerrespuestausuario.hecho(null, null, result);

		}
	}

	private HttpURLConnection Conexion(String archivo_php, String encodedStr) {
		URL url;
		HttpURLConnection con = null;
		try {
			url = new URL(SERVER_ADDRESS + archivo_php);
			con = (HttpURLConnection) url.openConnection();
			con.setDoOutput(true);
			con.setDoInput(true);
			con.setRequestMethod("POST");
			con.setReadTimeout(10000);
			con.setConnectTimeout(15000);
			OutputStreamWriter writer = new OutputStreamWriter(con.getOutputStream());
			writer.write(encodedStr);
			writer.flush();
			writer.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	private String getEncodedData(Map<String, String> data) {
		StringBuilder sb = new StringBuilder();
		for (String key : data.keySet()) {
			String value = null;
			try {
				value = URLEncoder.encode(data.get(key), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			if (sb.length() > 0)
				sb.append("&");
			sb.append(key + "=" + value);
		}
		return sb.toString();
	}

}
