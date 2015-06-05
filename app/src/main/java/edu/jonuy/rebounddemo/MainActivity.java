package edu.jonuy.rebounddemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.facebook.rebound.BaseSpringSystem;
import com.facebook.rebound.SimpleSpringListener;
import com.facebook.rebound.Spring;
import com.facebook.rebound.SpringSystem;

public class MainActivity extends Activity implements View.OnTouchListener {

    private final BaseSpringSystem mSpringSystem = SpringSystem.create();
    private Spring mMainSpring;
    private Spring mOuterSpring;

    private ImageView mMainButton;
    private ImageView mButton1;
    private ImageView mButton2;
    private ImageView mButton3;
    private ImageView mButton4;
    private ImageView mButton5;
    private ImageView mButton6;
    private ImageView mButton7;
    private ImageView mButton8;

    private boolean isShowingButtons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMainButton = (ImageView)findViewById(R.id.button_main);
        mButton1 = (ImageView)findViewById(R.id.button1);
        mButton2 = (ImageView)findViewById(R.id.button2);
        mButton3 = (ImageView)findViewById(R.id.button3);
        mButton4 = (ImageView)findViewById(R.id.button4);
        mButton5 = (ImageView)findViewById(R.id.button5);
        mButton6 = (ImageView)findViewById(R.id.button6);
        mButton7 = (ImageView)findViewById(R.id.button7);
        mButton8 = (ImageView)findViewById(R.id.button8);

        mMainButton.setOnTouchListener(this);

        mMainSpring = mSpringSystem.createSpring();
        mMainSpring.addListener(new MainSpringListener());
        mOuterSpring = mSpringSystem.createSpring();
        mOuterSpring.addListener(new OuterSpringListener());

        isShowingButtons = false;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mMainSpring.setEndValue(1f);
                return true;
            case MotionEvent.ACTION_UP:
                mMainSpring.setEndValue(0f);
                if (isShowingButtons) {
                    mOuterSpring.setEndValue(0f);
                }
                else {
                    mOuterSpring.setEndValue(1f);
                }
                return true;
        }

        return false;
    }

    private class MainSpringListener extends SimpleSpringListener {
        @Override
        public void onSpringUpdate(Spring spring) {
            float value = (float)spring.getCurrentValue();
            float scale = 1f - (value * 0.5f);
            mMainButton.setScaleX(scale);
            mMainButton.setScaleY(scale);
        }
    }

    private class OuterSpringListener extends SimpleSpringListener {
        private float radius = 350f;

        @Override
        public void onSpringUpdate(Spring spring) {
            float value = (float)spring.getCurrentValue();

            // Translations
            float r = radius * value;
            float r45 = (float)((double)radius / Math.sqrt(2d)) * value;

            mButton1.setTranslationY(-r);

            mButton2.setTranslationX(r45);
            mButton2.setTranslationY(-r45);

            mButton3.setTranslationX(r);

            mButton4.setTranslationX(r45);
            mButton4.setTranslationY(r45);

            mButton5.setTranslationY(r);

            mButton6.setTranslationX(-r45);
            mButton6.setTranslationY(r45);

            mButton7.setTranslationX(-r);

            mButton8.setTranslationX(-r45);
            mButton8.setTranslationY(-r45);

            // Alpha
            float alpha = value < 0.25f ? 0.25f : value;
            mButton1.setAlpha(alpha);
            mButton2.setAlpha(alpha);
            mButton3.setAlpha(alpha);
            mButton4.setAlpha(alpha);
            mButton5.setAlpha(alpha);
            mButton6.setAlpha(alpha);
            mButton7.setAlpha(alpha);
            mButton8.setAlpha(alpha);

            // Scale
            float scale = value < 0.75f ? 0.75f : value;
            mButton1.setScaleX(scale);
            mButton1.setScaleY(scale);
            mButton2.setScaleX(scale);
            mButton2.setScaleY(scale);
            mButton3.setScaleX(scale);
            mButton3.setScaleY(scale);
            mButton4.setScaleX(scale);
            mButton4.setScaleY(scale);
            mButton5.setScaleX(scale);
            mButton5.setScaleY(scale);
            mButton6.setScaleX(scale);
            mButton6.setScaleY(scale);
            mButton7.setScaleX(scale);
            mButton7.setScaleY(scale);
            mButton8.setScaleX(scale);
            mButton8.setScaleY(scale);
        }

        @Override
        public void onSpringAtRest(Spring spring) {
            if (!isShowingButtons) {
                mButton1.setVisibility(View.INVISIBLE);
                mButton2.setVisibility(View.INVISIBLE);
                mButton3.setVisibility(View.INVISIBLE);
                mButton4.setVisibility(View.INVISIBLE);
                mButton5.setVisibility(View.INVISIBLE);
                mButton6.setVisibility(View.INVISIBLE);
                mButton7.setVisibility(View.INVISIBLE);
                mButton8.setVisibility(View.INVISIBLE);
            }
        }

        @Override
        public void onSpringEndStateChange(Spring spring) {
            if (isShowingButtons) {
                isShowingButtons = false;
            }
            else {
                mButton1.setVisibility(View.VISIBLE);
                mButton2.setVisibility(View.VISIBLE);
                mButton3.setVisibility(View.VISIBLE);
                mButton4.setVisibility(View.VISIBLE);
                mButton5.setVisibility(View.VISIBLE);
                mButton6.setVisibility(View.VISIBLE);
                mButton7.setVisibility(View.VISIBLE);
                mButton8.setVisibility(View.VISIBLE);

                isShowingButtons = true;
            }
        }
    }
}
