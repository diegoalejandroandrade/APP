package com.example.app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FragmentoDia6 extends Fragment {

	TextView desayuno, colacion1, comida, colacion2, cena;
	AlmacenamientoLocalDieta bdDieta;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

	}

	@Override

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragmento_dia6, container, false);
		desayuno = (TextView) rootView.findViewById(R.id.txt_desayuno_frag6);
		colacion1 = (TextView) rootView.findViewById(R.id.txt_colacion1_frag6);
		comida = (TextView) rootView.findViewById(R.id.txt_comida_frag6);
		colacion2 = (TextView) rootView.findViewById(R.id.txt_colacion2_frag6);
		cena = (TextView) rootView.findViewById(R.id.txt_cena_frag6);
		bdDieta = new AlmacenamientoLocalDieta(getActivity());

		desayuno.setText(getDietaDia("DietaDia1","desayuno"));
		colacion1.setText(getDietaDia("DietaDia1","colacion1"));
		comida.setText(getDietaDia("DietaDia1","comida"));
		colacion2.setText(getDietaDia("DietaDia1","colacion2"));
		cena.setText(getDietaDia("DietaDia1","cena"));
		// getDietaDia("DietaDia1")
		return rootView;

	}

	public String getDietaDia(String tabla, String comida) {
		DietaDia dieta = bdDieta.cargarDia(tabla);
		String dietaTXT = comida.toUpperCase() + "\n";

		for (int i = 0; i < dieta.getDietaDiaCompleta().size(); i++) {
			if(dieta.getDietaDiaCompleta().get(i).getTipoComida().equals(comida)) {
				dietaTXT += "Ingrediente: " + dieta.getDietaDiaCompleta().get(i).getNombre() + " Kcal:"
						+ dieta.getDietaDiaCompleta().get(i).getKcal() + "\n";
			}
		}

		return dietaTXT;

	}

}
