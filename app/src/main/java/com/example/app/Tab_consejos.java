package com.example.app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class Tab_consejos extends Fragment implements OnClickListener {
	Button btnConsejos;

	@Override

	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

	}

	@Override

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_consejos, container, false);
		btnConsejos = (Button) rootView.findViewById(R.id.btnConsejos);
		btnConsejos.setOnClickListener(this);
		return rootView;

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnConsejos:
			Toast.makeText(this.getActivity(), "Presionaste Consejos", Toast.LENGTH_SHORT).show();
			break;
		}

	}

}