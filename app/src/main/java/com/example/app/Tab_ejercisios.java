package com.example.app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class Tab_ejercisios extends Fragment implements OnClickListener {
	Button btnEjercisios;
	// TextView txtInfoDieta;
	// String infoDieta = "En esta seccion encontraras \n tu rutina de
	// ejercisios semanal";

	@Override

	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

	}

	@Override

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_ejercisios, container, false);
		btnEjercisios = (Button) rootView.findViewById(R.id.btnEjercisios);
		btnEjercisios.setOnClickListener(this);
		// txtInfoDieta = (TextView) rootView.findViewById(R.id.txtInfoDieta);
		// txtInfoDieta.setText(infoDieta);
		return rootView;

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnEjercisios:

			Toast.makeText(this.getActivity(), "Presionaste Ejercisios", Toast.LENGTH_SHORT).show();
			break;
		}

	}

}