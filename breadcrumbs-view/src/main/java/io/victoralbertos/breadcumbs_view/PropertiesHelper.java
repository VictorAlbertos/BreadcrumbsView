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

import android.content.res.TypedArray;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;

final class PropertiesHelper {

  static void init(BreadcrumbsView breadcrumbsView) {
    breadcrumbsView.visitedStepBorderDotColor =
        ContextCompat.getColor(breadcrumbsView.getContext(), R.color.def_visited_step_border_dot_color);
    breadcrumbsView.visitedStepFillDotColor =
        ContextCompat.getColor(breadcrumbsView.getContext(), R.color.def_visited_step_fill_dot_color);
    breadcrumbsView.nextStepBorderDotColor =
        ContextCompat.getColor(breadcrumbsView.getContext(), R.color.def_next_step_border_dot_color);
    breadcrumbsView.nextStepFillDotColor =
        ContextCompat.getColor(breadcrumbsView.getContext(), R.color.def_next_step_fill_dot_color);
    breadcrumbsView.visitedStepSeparatorColor =
        ContextCompat.getColor(breadcrumbsView.getContext(), R.color.def_visited_step_separator_color);
    breadcrumbsView.nextStepSeparatorColor =
        ContextCompat.getColor(breadcrumbsView.getContext(), R.color.def_next_step_separator_color);
    breadcrumbsView.radius = breadcrumbsView.getResources().getDimensionPixelSize(R.dimen.def_radius_dot);
    breadcrumbsView.sizeDotBorder =
        breadcrumbsView.getResources().getDimensionPixelSize(R.dimen.def_size_dot_border);
    breadcrumbsView.heightSeparator =
        breadcrumbsView.getResources().getDimensionPixelSize(R.dimen.def_height_separator);
  }

  static void init(BreadcrumbsView breadcrumbsView, AttributeSet attrs) {
    TypedArray a = breadcrumbsView.getContext().getTheme().obtainStyledAttributes(
        attrs,
        R.styleable.BreadcrumbsView,
        0, 0);

    try {
      breadcrumbsView.nSteps = a.getInt(R.styleable.BreadcrumbsView_numberOfSteps, 0);
      if (breadcrumbsView.nSteps == 0) {
        throw new IllegalStateException(
            "You must set a number of steps. Use 'numberOfSteps' attribute to supply a value greater than 1");
      }

      breadcrumbsView.visitedStepBorderDotColor =
          a.getColor(R.styleable.BreadcrumbsView_visitedStepBorderDotColor,
              ContextCompat.getColor(breadcrumbsView.getContext(),
                  R.color.def_visited_step_border_dot_color));
      breadcrumbsView.visitedStepFillDotColor =
          a.getColor(R.styleable.BreadcrumbsView_visitedStepFillDotColor,
              ContextCompat.getColor(breadcrumbsView.getContext(),
                  R.color.def_visited_step_fill_dot_color));
      breadcrumbsView.nextStepBorderDotColor =
          a.getColor(R.styleable.BreadcrumbsView_nextStepBorderDotColor,
              ContextCompat.getColor(breadcrumbsView.getContext(),
                  R.color.def_next_step_border_dot_color));
      breadcrumbsView.nextStepFillDotColor = a.getColor(R.styleable.BreadcrumbsView_nextStepFillDotColor,
          ContextCompat.getColor(breadcrumbsView.getContext(), R.color.def_next_step_fill_dot_color));
      breadcrumbsView.visitedStepSeparatorColor =
          a.getColor(R.styleable.BreadcrumbsView_visitedStepSeparatorColor,
              ContextCompat.getColor(breadcrumbsView.getContext(),
                  R.color.def_visited_step_separator_color));
      breadcrumbsView.nextStepSeparatorColor =
          a.getColor(R.styleable.BreadcrumbsView_nextStepSeparatorColor,
              ContextCompat.getColor(breadcrumbsView.getContext(),
                  R.color.def_next_step_separator_color));
      breadcrumbsView.radius = a.getDimensionPixelSize(R.styleable.BreadcrumbsView_radiusDot,
          breadcrumbsView.getResources().getDimensionPixelSize(R.dimen.def_radius_dot));
      breadcrumbsView.sizeDotBorder = a.getDimensionPixelSize(R.styleable.BreadcrumbsView_sizeDotBorder,
          breadcrumbsView.getResources().getDimensionPixelSize(R.dimen.def_size_dot_border));
      breadcrumbsView.heightSeparator = a.getDimensionPixelSize(R.styleable.BreadcrumbsView_heightSeparator,
          breadcrumbsView.getResources().getDimensionPixelSize(R.dimen.def_height_separator));
    } finally {
      a.recycle();
    }
  }
}
