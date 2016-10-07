/*
 * Copyright 2016 Victor Albertos
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.victoralbertos.breadcumbs_view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.widget.RelativeLayout;

@SuppressLint("ViewConstructor")
final class SeparatorView extends RelativeLayout {
  private final View separatorViewVisitedStep;

  SeparatorView(Context context, boolean visited, int visitedStepLineColor, int nextStepLineColor,
      int width,
      int height) {
    super(context);

    setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT));

    addView(createSeparator(nextStepLineColor, width, height));

    separatorViewVisitedStep = createSeparator(visitedStepLineColor, width, height);
    separatorViewVisitedStep.setPivotX(0);

    if (!visited) {
      separatorViewVisitedStep.setScaleX(0);
    }

    addView(separatorViewVisitedStep);

    //For testing purpose
    setTag("separatorView");
    separatorViewVisitedStep.setTag("separatorViewVisitedStep");
  }

  private View createSeparator(int lineColor, int width, int height) {
    View lineView = new View(getContext());
    RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(width, height);
    params.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
    lineView.setLayoutParams(params);

    GradientDrawable border = new GradientDrawable();
    border.setShape(GradientDrawable.RECTANGLE);
    border.setColor(lineColor);

    lineView.setBackground(border);

    return lineView;
  }

  void animateFromNextStepToVisitedStep(Runnable endAnim) {
    separatorViewVisitedStep
        .animate()
        .scaleX(1)
        .withEndAction(endAnim);
  }

  void animateFromVisitedStepToNextStep(Runnable endAnim) {
    separatorViewVisitedStep
        .animate()
        .scaleX(0)
        .withEndAction(endAnim);
  }
}

