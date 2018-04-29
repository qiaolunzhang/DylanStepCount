package cn.bluemobi.dylan.step.activity;

import android.content.Intent;
import android.os.Build;
import android.test.ActivityInstrumentationTestCase2;
import android.test.ActivityUnitTestCase;
import android.test.suitebuilder.annotation.MediumTest;
import android.view.View;
import android.widget.Button;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowActivity;

import java.util.logging.Logger;

import cn.bluemobi.dylan.step.BuildConfig;
import cn.bluemobi.dylan.step.R;


@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class MainActivityTest {
    private MainActivity mainActivity;

    @Before
    public void setUp() {
         mainActivity = Robolectric.setupActivity(MainActivity.class);

    }

    @Test
    public void testTVSetButton() {
        mainActivity.findViewById(R.id.tv_set).performClick();
        Intent expectedIntent = new Intent(mainActivity, SetPlanActivity.class);
        ShadowActivity shadowActivity = Shadows.shadowOf(mainActivity);
        Intent actualIntent = shadowActivity.getNextStartedActivity();
        Assert.assertEquals(expectedIntent.toString().trim().replace("\r",""), actualIntent.toString().trim().replace("\r",""));
    }

    @Test
    public void testTVDataButton() {
        mainActivity.findViewById(R.id.tv_data).performClick();
        Intent expectedIntent = new Intent(mainActivity, HistoryActivity.class);
        ShadowActivity shadowActivity = Shadows.shadowOf(mainActivity);
        Intent actualIntent = shadowActivity.getNextStartedActivity();
        Assert.assertEquals(expectedIntent.toString().trim().replace("\r",""), actualIntent.toString().trim().replace("\r",""));
    }

    @Test
    public void testVisualButton() {
        mainActivity.findViewById(R.id.visual_button).performClick();
        Intent expectedIntent = new Intent(mainActivity, VisualActivity.class);
        ShadowActivity shadowActivity = Shadows.shadowOf(mainActivity);
        Intent actualIntent = shadowActivity.getNextStartedActivity();
        Assert.assertEquals(expectedIntent.toString().trim().replace("\r",""), actualIntent.toString().trim().replace("\r",""));
    }

    @Test
    public void testFeelingButton() {
        mainActivity.findViewById(R.id.feeling_button).performClick();
        Intent expectedIntent = new Intent(mainActivity, FeelingActivity.class);
        ShadowActivity shadowActivity = Shadows.shadowOf(mainActivity);
        Intent actualIntent = shadowActivity.getNextStartedActivity();
        Assert.assertEquals(expectedIntent.toString().trim().replace("\r",""), actualIntent.toString().trim().replace("\r",""));
    }

    @Test
    public void testAchievementButton() {
        mainActivity.findViewById(R.id.achievement_button).performClick();
        Intent expectedIntent = new Intent(mainActivity, AchievementActivity.class);
        ShadowActivity shadowActivity = Shadows.shadowOf(mainActivity);
        Intent actualIntent = shadowActivity.getNextStartedActivity();
        Assert.assertEquals(expectedIntent.toString().trim().replace("\r",""), actualIntent.toString().trim().replace("\r",""));
    }
}
