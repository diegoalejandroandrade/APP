package com.example.app;

import java.util.List;
import java.util.Vector;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.PageTransformer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MenuPrincipal extends AppCompatActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_slide_menu_principal);
		inicializarFragmentos();
	}

	private void inicializarFragmentos() {
		List<Fragment> fragments = new Vector<Fragment>();
		fragments.add(Fragment.instantiate(this, Tab_dietas.class.getName()));
		fragments.add(Fragment.instantiate(this, Tab_ejercisios.class.getName()));
		fragments.add(Fragment.instantiate(this, Tab_consejos.class.getName()));
		fragments.add(Fragment.instantiate(this, Tab_infousuario.class.getName()));
		PagerAdapter mPagerAdapter = new PagerAdapter(this.getSupportFragmentManager(), fragments);
		ViewPager pager = (ViewPager) findViewById(R.id.pager);
		pager.setAdapter(mPagerAdapter);
		pager.setPageTransformer(true, new ZoomOutPageTransformer());

	}

	public class ZoomOutPageTransformer implements PageTransformer {
		private static final float MIN_SCALE = 0.85f;
		private static final float MIN_ALPHA = 0.5f;

		@Override
		public void transformPage(View view, float position) {
			int pageWidth = view.getWidth();
			int pageHeight = view.getHeight();

			if (position < -1) {
				view.setAlpha(0);

			} else if (position <= 1) {

				float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
				float vertMargin = pageHeight * (1 - scaleFactor) / 2;
				float horzMargin = pageWidth * (1 - scaleFactor) / 2;
				if (position < 0) {
					view.setTranslationX(horzMargin - vertMargin / 2);
				} else {
					view.setTranslationX(-horzMargin + vertMargin / 2);
				}

				view.setScaleX(scaleFactor);
				view.setScaleY(scaleFactor);

				view.setAlpha(MIN_ALPHA + (scaleFactor - MIN_SCALE) / (1 - MIN_SCALE) * (1 - MIN_ALPHA));

			} else {
				view.setAlpha(0);
			}
		}

	}

}