package com.csofcs.ehsanhasin.thebestcleaner;

import android.animation.ValueAnimator;
import android.view.animation.CycleInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;

public class LaunchRocketValueAnimatorAnimationActivity extends BaseAnimationActivity {
  @Override
  protected void onStartAnimation() {

    ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, -mScreenHeight);

    valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
      @Override
      public void onAnimationUpdate(ValueAnimator animation) {
        float value = (float) animation.getAnimatedValue();
        mRocket.setTranslationY(value);
      }
    });

    valueAnimator.setInterpolator(new DecelerateInterpolator());
    valueAnimator.setDuration(DEFAULT_ANIMATION_DURATION);
    valueAnimator.start();
  }
}
