package com.example.viewtest;

import java.util.ResourceBundle;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

public class MainActivity extends FragmentActivity {

	private LinearLayout firstlayout, secondLayout, thirdLayout;
	private ImageView firstImageView, secondImageView, thirdImageView,
			fourthImageView, fifthImageView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initViews();

		getScreenWidthHeight();
		getViewTopBottomLeftRight(firstlayout, "First LinearLayout");
		getViewTopBottomLeftRight(secondLayout, "Second LinearLayout");
		getViewTopBottomLeftRight(firstImageView, "First ImageView");
		getViewTopBottomLeftRight(secondImageView, "Second ImageView");
		getViewTopBottomLeftRight(thirdImageView, "Third ImageView");
		getViewTopBottomLeftRight(fourthImageView, "Fourth ImageView");
		getViewTopBottomLeftRight(fifthImageView, "Fifth ImageView");
		getViewTopBottomLeftRight(thirdLayout, "Third Layout");

	}

	private void initViews() {
		firstImageView = (ImageView) findViewById(R.id.firstImageView);
		secondImageView = (ImageView) findViewById(R.id.secondImageImageView);
		thirdImageView = (ImageView) findViewById(R.id.thirdImageView);
		firstlayout = (LinearLayout) findViewById(R.id.firstLayout);
		secondLayout = (LinearLayout) findViewById(R.id.secondLayout);
		fourthImageView = (ImageView) findViewById(R.id.fourthImageView);
		fifthImageView = (ImageView) findViewById(R.id.fifthImageView);
		thirdLayout = (LinearLayout) findViewById(R.id.thirdLayout);
	}

	public static float convertPixelsToDp(float px) {
		DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
		float dp = px / (metrics.densityDpi / 160f);
		return Math.round(dp);
	}

	public static float convertDpToPixel(float dp) {
		DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
		float px = dp * (metrics.densityDpi / 160f);
		return Math.round(px);
	}

	@SuppressLint("NewApi")
	private void getViewTopBottomLeftRight(final View view, final String tag) {

		ViewTreeObserver viewTreeObserver = view.getViewTreeObserver();
		viewTreeObserver
				.addOnGlobalLayoutListener(new OnGlobalLayoutListener() {

					@Override
					public void onGlobalLayout() {
						ViewTreeObserver obs = view.getViewTreeObserver();

						if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN)
							obs.removeOnGlobalLayoutListener(this);
						else
							obs.removeGlobalOnLayoutListener(this);
						Log.i("VIEW=", tag);

						Log.i("is view observer alive",
								String.valueOf(obs.isAlive()));

						Log.i("View width:height",
								String.valueOf(view.getWidth()) + "::"
										+ String.valueOf(view.getHeight()));
						Log.i("view measured width:height",
								String.valueOf(view.getMeasuredWidth()) + "::"
										+ view.getMeasuredHeight());
						Log.i("view top", String.valueOf(view.getTop()));
						Log.i("view bottom", String.valueOf(view.getBottom()));
						Log.i("view left", String.valueOf(view.getLeft()));
						Log.i("view right", String.valueOf(view.getRight()));
						Log.i("view x:y", String.valueOf(view.getLeft()) + "::"
								+ String.valueOf(view.getTop()));
						Log.i("view padding top:bottom:left:right",
								String.valueOf(view.getPaddingTop())
										+ "::"
										+ String.valueOf(view
												.getPaddingBottom()
												+ "::"
												+ String.valueOf(view
														.getPaddingLeft())
												+ "::"
												+ String.valueOf(view
														.getPaddingRight())));
						int[] xy = new int[2];

						view.getLocationOnScreen(xy);
						Log.i("get Location on screen x:y",
								String.valueOf(xy[0]) + "::"
										+ String.valueOf(xy[1]));

						Log.i("status bar height",
								String.valueOf(getStatusBarHeight()));
						Log.i("action bar height",
								String.valueOf(getActionBarHeight()));
						Log.i("###########", "#########");

					}
				});
	}

	public void displayViewMargin(View view) {
		// make sure the view layoutParams is of LinearLayout.LayoutParams
		// otherwise it will force close the app
		LinearLayout.LayoutParams layoutParams = (LayoutParams) view
				.getLayoutParams();
		Log.i("view margin left::right::top::bottom",
				String.valueOf(convertPixelsToDp(layoutParams.leftMargin))
						+ "::"
						+ String.valueOf(convertPixelsToDp(layoutParams.rightMargin))
						+ "::"
						+ String.valueOf(convertPixelsToDp(layoutParams.topMargin))
						+ "::"
						+ String.valueOf(convertPixelsToDp(layoutParams.bottomMargin)));

	}

	private void getScreenWidthHeight() {
		WindowManager windowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics displayMetrics = new DisplayMetrics();
		windowManager.getDefaultDisplay().getMetrics(displayMetrics);
		int screenWidth = displayMetrics.widthPixels;
		int screenHeight = displayMetrics.heightPixels;
		Log.i("width x height",
				String.valueOf(screenWidth) + " x "
						+ String.valueOf(screenHeight));
	}

	private int getStatusBarHeight() {
		int statusBarHeight = 0;
		int resourceId = getResources().getIdentifier("status_bar_height",
				"dimen", "android");
		if (resourceId > 0)
			statusBarHeight = getResources().getDimensionPixelSize(resourceId);
		return statusBarHeight;
	}

	public int getActionBarHeight() {
		int actionBarHeight = 0;
		TypedValue tv = new TypedValue();
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			if (getTheme().resolveAttribute(android.R.attr.actionBarSize, tv,
					true))
				actionBarHeight = TypedValue.complexToDimensionPixelSize(
						tv.data, getResources().getDisplayMetrics());
		} else {
			actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data,
					getResources().getDisplayMetrics());
		}
		return actionBarHeight;
	}

}
