package om.example.stayhydrated;


import android.app.Activity;
import android.widget.ListView;
import android.widget.ProgressBar;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.doubleClick;
import static androidx.test.espresso.matcher.ViewMatchers.assertThat;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.equalTo;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class IntegrationTests {
    // Define the activity to be tested
    @Rule
    public ActivityTestRule<MainActivity> activity = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void ExampleAssertInDataInHistory() {
        onData(anything()) // Select all elements in a list
                .inAdapterView(withId(R.id.historyListView)) // Only in the historyListViewAdapter
                .atPosition(0)  // Select the element at index 0
                .onChildView(withId(R.id.historyDataText))  // Select the view historyDataText in the element
                .check(matches(withText("[TEMP_TEXT]")));  // Assertion
    }

    @Test
    public void ExampleAssertProgressBar() {
        ProgressBar progressBar = activity.getActivity().findViewById(R.id.progressBar);
        assertThat(progressBar.getProgress(), equalTo(50));
    }


    // Feature 1:
    @Test
    public void clickImageIcondroplet() throws InterruptedException {
        onView(withId(R.id.dropletImage)).perform(click());
        Thread.sleep(1000);
        onData(anything()) // Select all elements in a list
                .inAdapterView(withId(R.id.historyListView)) // Only in the historyListViewAdapter
                .atPosition(0)  // Select the element at index 0
                .onChildView(withId(R.id.historyDataText))  // Select the view historyDataText in the element
                .check(matches(withText("50 mL")));  // Assertion
    }

    @Test
    public void clickImageIconBottle() throws InterruptedException {

        onView(withId(R.id.bottleImage)).perform(click());
        Thread.sleep(1000);
        onData(anything()) // Select all elements in a list
                .inAdapterView(withId(R.id.historyListView)) // Only in the historyListViewAdapter
                .atPosition(0)  // Select the element at index 2
                .onChildView(withId(R.id.historyDataText))  // Select the view historyDataText in the element
                .check(matches(withText("350 mL")));  // Assertion
    }

    // Feature 2:

    @Test
    public void ElementTimeStampTest()
    {

        DateTimeFormatter Date = DateTimeFormatter.ofPattern("HH:MM - DD/MM");
        LocalDateTime Local = LocalDateTime.now();
        String Today_Time = Date.format(Local);

        onView(withId(R.id.bottleImage)).perform(click());

        onData(anything()) // Select all elements in a list
                .inAdapterView(withId(R.id.historyListView)) // Only in the historyListViewAdapter
                .atPosition(0)  // Select the element at index 2
                .onChildView(withId(R.id.historyDataText))  // Select the view historyDataText in the element
                .check(matches(withText("350 mL")));  // Assertion

        onData(anything()) // Select all elements in a list
                .inAdapterView(withId(R.id.historyListView)) // Only in the historyListViewAdapter
                .atPosition(0)  // Select the element at index 2
                .onChildView(withId(R.id.historyDataText))  // Select the view historyDataText in the element
                .check(matches(withText(Today_Time)));  // Assertion

    }

  @Test
  public void checkElementTop() throws InterruptedException
  {
      onView(withId(R.id.glassImage)).perform(click());

      Thread.sleep(1000);
      onData(anything()) // Select all elements in a list
              .inAdapterView(withId(R.id.historyListView)) // Only in the historyListViewAdapter
              .atPosition(0)  // Select the element at index 2
              .onChildView(withId(R.id.historyDataText))  // Select the view historyDataText in the element
              .check(matches(withText("200 mL")));  // Assertion

      ProgressBar progressBar = activity.getActivity().findViewById(R.id.progressBar);
      assertThat(progressBar.getProgress(), equalTo(200)); // Progress Bar Updated
  }



    // Feature 3:

    @Test
    public void ProgressBarTest() throws InterruptedException
    {
        onView(withId(R.id.bottleImage)).perform(click());
        Thread.sleep(1000);
        onData(anything()) // Select all elements in a list
                .inAdapterView(withId(R.id.historyListView)) // Only in the historyListViewAdapter
                .atPosition(0)  // Select the element at index 0
                .onChildView(withId(R.id.historyDataText))  // Select the view historyDataText in the element
                .check(matches(withText("350 mL")));  // Assertion
        Thread.sleep(1000);

        ProgressBar progressBar = activity.getActivity().findViewById(R.id.progressBar);
        assertThat(progressBar.getProgress(), equalTo(350));
    }

    @Test
    public void ProgressBarLimitExceeds()
    {
        onView(withId(R.id.bottleImage)).perform(click()); // 350 mL
        onView(withId(R.id.dropletImage)).perform(click()); // 400 mL
        onView(withId(R.id.glassImage)).perform(click()); // 600 mL
        onView(withId(R.id.bottleImage)).perform(click()); // 950 mL
        onView(withId(R.id.glassImage)).perform(click()); // 1150 mL
        onView(withId(R.id.bottleImage)).perform(click()); // 1500 mL
        onView(withId(R.id.dropletImage)).perform(click()); // 1550 mL
        onView(withId(R.id.dropletImage)).perform(click()); // 1600 mL
        onView(withId(R.id.bottleImage)).perform(click()); // 1950 mL
        onView(withId(R.id.glassImage)).perform(click()); // 2150 mL
        onView(withId(R.id.bottleImage)).perform(click()); // 2500 mL
        onView(withId(R.id.bottleImage)).perform(click()); // 2850 mL


        onData(anything()) // Select all elements in a list
                .inAdapterView(withId(R.id.historyListView)) // Only in the historyListViewAdapter
                .atPosition(11)  // Select the element at index 11
                .perform(doubleClick());

        ListView LV = activity.getActivity().findViewById(R.id.historyListView);
        Integer count = LV.getCount();
        assertThat(count,equalTo(10));

        ProgressBar progressBar = activity.getActivity().findViewById(R.id.progressBar);
        assertThat(progressBar.getProgress(), equalTo(2500));

    }


    // Feature 4:

    public void DeleteElementIntakes() throws InterruptedException
    {
        onView(withId(R.id.glassImage)).perform(click());

        Thread.sleep(1000);

        onData(anything()) // Select all elements in a list
                .inAdapterView(withId(R.id.historyListView)) // Only in the historyListViewAdapter
                .atPosition(0)  // Select the element at index 0
                .onChildView(withId(R.id.historyDataText))  // Select the view historyDataText in the element
                .check(matches(withText("200 mL")));  // Assertion

        onData(anything()) // Select all elements in a list
                .inAdapterView(withId(R.id.historyListView)) // Only in the historyListViewAdapter
                .atPosition(0)  // Select the element at index 0
        .perform(doubleClick());

        ListView LV = activity.getActivity().findViewById(R.id.historyListView);
        Integer count = LV.getCount();
        assertThat(count,equalTo(0));


    }

}
