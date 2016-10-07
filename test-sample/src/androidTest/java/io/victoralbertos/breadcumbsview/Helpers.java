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

package io.victoralbertos.breadcumbsview;

import android.support.test.espresso.matcher.BoundedMatcher;
import android.view.View;
import android.view.ViewGroup;
import io.victoralbertos.breadcumbs_view.BreadcrumbsView;
import org.hamcrest.Description;
import org.hamcrest.Matcher;

final class Helpers {

  static Matcher<View> nDots(final Matcher<Integer> nDotsMatcher) {
    return breadcrumbsMatcher(new Func() {
      @Override public boolean call(ViewGroup view) {
        return view.getTag().equals("dotView");
      }
    }, nDotsMatcher, "number of dots");
  }

  static Matcher<View> nDotsVisited(final Matcher<Integer> nDotsMatcher) {
    return breadcrumbsMatcher(new Func() {
      @Override public boolean call(ViewGroup view) {
        boolean isDotView = view.getTag().equals("dotView");
        if (!isDotView) return false;
        View dotViewVisitedStep = view.findViewWithTag("dotViewVisitedStep");
        return dotViewVisitedStep.getScaleX() == 1;
      }
    }, nDotsMatcher, "number of dots visited");
  }

  static Matcher<View> nSeparators(final Matcher<Integer> nSeparatorsMatcher) {
    return breadcrumbsMatcher(new Func() {
      @Override public boolean call(ViewGroup view) {
        return view.getTag().equals("separatorView");
      }
    }, nSeparatorsMatcher, "number of separators");
  }

  static Matcher<View> nSeparatorsVisited(final Matcher<Integer> nSeparatorsMatcher) {
    return breadcrumbsMatcher(new Func() {
      @Override public boolean call(ViewGroup view) {
        boolean isSeparatorView = view.getTag().equals("separatorView");
        if (!isSeparatorView) return false;
        View dotViewVisitedStep = view.findViewWithTag("separatorViewVisitedStep");
        return dotViewVisitedStep.getScaleX() == 1;
      }
    }, nSeparatorsMatcher, "number of separators visited");
  }

  private static Matcher<View> breadcrumbsMatcher(final Func func, final Matcher<Integer> expectedCount,
      final String msgDescription) {
    return new BoundedMatcher<View, BreadcrumbsView>(BreadcrumbsView.class) {
      @Override protected boolean matchesSafely(BreadcrumbsView view) {
        int nDots = 0;
        for (int i = 0; i < view.getChildCount(); i++) {
          View viewChild = view.getChildAt(i);
          if (viewChild.getTag() != null) {
            if (func.call((ViewGroup) viewChild)) nDots++;
          }
        }
        return expectedCount.matches(nDots);
      }

      @Override public void describeTo(Description description) {
        description.appendText(msgDescription);
        expectedCount.describeTo(description);
      }
    };
  }

  private interface Func {
    boolean call(ViewGroup view);
  }
}
