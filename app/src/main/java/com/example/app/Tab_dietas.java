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

public class Tab_dietas extends Fragment implements OnClickListener {
	TextView txtInfoDieta;
	Button btnDietas;

	AlmacenamientoLocalDieta almacenamientolocaldieta;

	@Override

	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

	}

	@Override

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_dietas, container, false);
		btnDietas = (Button) rootView.findViewById(R.id.btnDietas);
		btnDietas.setOnClickListener(this);
		almacenamientolocaldieta = new AlmacenamientoLocalDieta(getActivity());
		return rootView;

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnDietas:

			if (almacenamientolocaldieta.existeDB()) {
				Intent IntentDietasDiarias = new Intent(getActivity(), ActivityDietasDiarias.class);
				startActivity(IntentDietasDiarias);
			} else {
				GeneraDietaSemanal();

			}

			break;
		}

	}

	public void GeneraDietaSemanal() {
		ManejadorPeticiones manejador = new ManejadorPeticiones(getContext());
		manejador.peticionDietas(new RespuestaServidor() {
			@Override
			public void hecho(Usuario UsuarioDevuelto, DietaSemana dietaSemana, Integer insertado) {

				if (dietaSemana != null) {
					Intent IntentDietasDiarias = new Intent(getActivity(), ActivityDietasDiarias.class);
					startActivity(IntentDietasDiarias);
				}

			}
		});

	}

}