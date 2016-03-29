package com.example.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class Tab_infousuario extends Fragment implements OnClickListener {

	TextView txtNombreUsuario, txtNombre, txtContrasena, txtEdad, txtEstatura, txtPeso, txtObjetivo, txtSexo;
	Usuario usuario;
	AlmacenamientoLocalUsuario almacenamientolocalusuario;
	Button btnLogout;
	AlmacenamientoLocalDieta bdDieta;

	@Override

	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		almacenamientolocalusuario = new AlmacenamientoLocalUsuario(getActivity());
		usuario = almacenamientolocalusuario.obtenerUsuarioLogueado();

	}

	@Override

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_infousuario, container, false);
		txtNombreUsuario = (TextView) rootView.findViewById(R.id.txtNombreUsuario);
		txtNombre = (TextView) rootView.findViewById(R.id.txtNombre);
		txtContrasena = (TextView) rootView.findViewById(R.id.txtContrasena);
		txtEdad = (TextView) rootView.findViewById(R.id.txtEdad);
		txtEstatura = (TextView) rootView.findViewById(R.id.txtEstatura);
		txtPeso = (TextView) rootView.findViewById(R.id.txtPeso);
		txtSexo = (TextView) rootView.findViewById(R.id.txtSexo);
		txtObjetivo = (TextView) rootView.findViewById(R.id.txtObjetivo);

		txtNombreUsuario.setText(usuario.getNombreUsuario());
		txtNombre.setText(usuario.getNombre());
		txtContrasena.setText(usuario.getContrasena());
		txtEdad.setText(usuario.getEdad() + "");
		txtEstatura.setText(usuario.getAltura() + "");
		txtPeso.setText(usuario.getPeso() + "");
		txtSexo.setText(usuario.getSexo() + "");
		txtObjetivo.setText(usuario.getObjetivo());

		btnLogout = (Button) rootView.findViewById(R.id.btnLogout);
		// txtInfoDieta = (TextView) rootView.findViewById(R.id.txtInfoDieta);
		btnLogout.setOnClickListener(this);
		bdDieta = new AlmacenamientoLocalDieta(getActivity());
		return rootView;

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnLogout:
			bdDieta.BorrarDietaAlmacenada();
			almacenamientolocalusuario.BorrarDatosUsuario();
			Intent IntentLogin = new Intent(getActivity(), Login.class);
			startActivity(IntentLogin);
			getActivity().finish();
			// Toast.makeText(this.getActivity(), "Datos borrados",
			// Toast.LENGTH_SHORT).show();
			break;
		}

	}

}
