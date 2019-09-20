package com.cnews.guji.smart.helper.dialog;

import android.animation.ObjectAnimator;
import android.view.View;

import com.flyco.animation.BaseAnimatorSet;

public class FlipVerticalAlphaOuter extends BaseAnimatorSet {
	public FlipVerticalAlphaOuter() {
		duration = 1000;
	}

	@Override
	public void setAnimation(View view) {
		animatorSet.playTogether(//
				ObjectAnimator.ofFloat(view, "alpha", 1, 0.75f,0.5f, 0));
	}
}
