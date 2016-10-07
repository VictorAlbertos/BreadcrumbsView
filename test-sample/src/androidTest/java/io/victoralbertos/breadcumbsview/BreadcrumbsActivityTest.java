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

import android.os.RemoteException;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.PerformException;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiDevice;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static io.victoralbertos.breadcumbsview.Helpers.nDots;
import static io.victoralbertos.breadcumbsview.Helpers.nDotsVisited;
import static io.victoralbertos.breadcumbsview.Helpers.nSeparators;
import static io.victoralbertos.breadcumbsview.Helpers.nSeparatorsVisited;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@RunWith(AndroidJUnit4.class)
public final class BreadcrumbsActivityTest {
  @Rule public ActivityTestRule<BreadcrumbsActivity> activityRule =
      new ActivityTestRule<>(BreadcrumbsActivity.class);

  //@Test(expected = IndexOutOfBoundsException.class) Actual exception to catch, but espresso wrappers it in a  PerformException
  @Test(expected = PerformException.class)
  public void Verify_Throw_Next_Step_When_Steps_Left() {
    onView(withId(R.id.bt_next)).perform(click());
    onView(withId(R.id.bt_next)).perform(click());
    onView(withId(R.id.bt_next)).perform(click());
    onView(withId(R.id.bt_next)).perform(click());
    onView(withId(R.id.bt_next)).perform(click());
  }

  //@Test(expected = IndexOutOfBoundsException.class) Actual exception to catch, but espresso wrappers it in a  PerformException
  @Test(expected = PerformException.class)
  public void Verify_Throw_Prev_Step_When_Steps_Left() {
    onView(withId(R.id.bt_prev)).perform(click());
  }

  @Test
  public void Verify_Breadcrumbs_Initial_State() {
    onView(withId(R.id.breadcrumbs))
        .check(matches(
            allOf(
                nDots(is(5)),
                nSeparators(is(4)),
                nDotsVisited(is(1)),
                nSeparatorsVisited(is(0))
            )
        ));
  }

  @Test
  public void Verify_Breadcrumbs_Next_Prev() {
    onView(withId(R.id.bt_next)).perform(click());
    onView(withId(R.id.breadcrumbs))
        .check(matches(
            allOf(
                nDotsVisited(is(2)),
                nSeparatorsVisited(is(1))
            )
        ));

    onView(withId(R.id.bt_next)).perform(click());
    onView(withId(R.id.breadcrumbs))
        .check(matches(
            allOf(
                nDotsVisited(is(3)),
                nSeparatorsVisited(is(2))
            )
        ));

    onView(withId(R.id.bt_next)).perform(click());
    onView(withId(R.id.breadcrumbs))
        .check(matches(
            allOf(
                nDotsVisited(is(4)),
                nSeparatorsVisited(is(3))
            )
        ));

    onView(withId(R.id.bt_next)).perform(click());
    onView(withId(R.id.breadcrumbs))
        .check(matches(
            allOf(
                nDotsVisited(is(5)),
                nSeparatorsVisited(is(4))
            )
        ));

    onView(withId(R.id.bt_prev)).perform(click());
    onView(withId(R.id.breadcrumbs))
        .check(matches(
            allOf(
                nDotsVisited(is(4)),
                nSeparatorsVisited(is(3))
            )
        ));

    onView(withId(R.id.bt_prev)).perform(click());
    onView(withId(R.id.breadcrumbs))
        .check(matches(
            allOf(
                nDotsVisited(is(3)),
                nSeparatorsVisited(is(2))
            )
        ));

    onView(withId(R.id.bt_prev)).perform(click());
    onView(withId(R.id.breadcrumbs))
        .check(matches(
            allOf(
                nDotsVisited(is(2)),
                nSeparatorsVisited(is(1))
            )
        ));

    onView(withId(R.id.bt_prev)).perform(click());
    onView(withId(R.id.breadcrumbs))
        .check(matches(
            allOf(
                nDotsVisited(is(1)),
                nSeparatorsVisited(is(0))
            )
        ));
  }

  @Test public void Verify_Survive_Config_Changes() {
    onView(withId(R.id.bt_next)).perform(click());
    onView(withId(R.id.bt_next)).perform(click());
    onView(withId(R.id.bt_next)).perform(click());

    onView(withId(R.id.breadcrumbs))
        .check(matches(
            allOf(
                nDotsVisited(is(4)),
                nSeparatorsVisited(is(3))
            )
        ));

    rotateDevice();

    onView(withId(R.id.breadcrumbs))
        .check(matches(
            allOf(
                nDotsVisited(is(4)),
                nSeparatorsVisited(is(3))
            )
        ));
  }


  private void rotateDevice() {
    try {
      UiDevice uiDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
      uiDevice.setOrientationLeft();
      waitTime();
      uiDevice.setOrientationNatural();
      waitTime();
    } catch (RemoteException e) {
      e.printStackTrace();
    }
  }

  private void waitTime() {
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
