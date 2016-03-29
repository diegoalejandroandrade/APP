package com.example.app;

import java.util.List;
import java.util.Vector;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class ActivityDietasDiarias extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_slide_dietas_diarias);
		inicializarFragmentos();
	}

	private void inicializarFragmentos() {
		List<Fragment> fragments = new Vector<Fragment>();
		fragments.add(Fragment.instantiate(this, FragmentoDia1.class.getName()));
		fragments.add(Fragment.instantiate(this, FragmentoDia2.class.getName()));
		fragments.add(Fragment.instantiate(this, FragmentoDia3.class.getName()));
		fragments.add(Fragment.instantiate(this, FragmentoDia4.class.getName()));
		fragments.add(Fragment.instantiate(this, FragmentoDia5.class.getName()));
		fragments.add(Fragment.instantiate(this, FragmentoDia6.class.getName()));
		fragments.add(Fragment.instantiate(this, FragmentoDia7.class.getName()));

		PagerAdapter mPagerAdapter = new PagerAdapter(this.getSupportFragmentManager(), fragments);
		ViewPager pager = (ViewPager) findViewById(R.id.pager);
		pager.setAdapter(mPagerAdapter);

		pager.setPageTransformer(true, new DepthPageTransformer());

	}

	public class DepthPageTransformer implements ViewPager.PageTransformer {
		private static final float MIN_SCALE = 0.75f;

		public void transformPage(View view, float position) {
			int pageWidth = view.getWidth();

			if (position < -1) { // [-Infinity,-1)
				// This page is way off-screen to the left.
				view.setAlpha(0);

			} else if (position <= 0) { // [-1,0]
				// Use the default slide transition when moving to the left page
				view.setAlpha(1);
				view.setTranslationX(0);
				view.setScaleX(1);
				view.setScaleY(1);

			} else if (position <= 1) { // (0,1]
				// Fade the page out.
				view.setAlpha(1 - position);

				// Counteract the default slide transition
				view.setTranslationX(pageWidth * -position);

				// Scale the page down (between MIN_SCALE and 1)
				float scaleFactor = MIN_SCALE + (1 - MIN_SCALE) * (1 - Math.abs(position));
				view.setScaleX(scaleFactor);
				view.setScaleY(scaleFactor);

			} else { // (1,+Infinity]
				// This page is way off-screen to the right.
				view.setAlpha(0);
			}
		}
	}

}
