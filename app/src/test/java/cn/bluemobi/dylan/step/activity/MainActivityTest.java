package cn.bluemobi.dylan.step.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.test.ActivityInstrumentationTestCase2;
import android.test.ActivityUnitTestCase;
import android.test.suitebuilder.annotation.MediumTest;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowActivity;
import org.robolectric.shadows.ShadowTextView;
import org.robolectric.util.ActivityController;

import java.util.logging.Logger;

import cn.bluemobi.dylan.step.BuildConfig;
import cn.bluemobi.dylan.step.R;


@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class MainActivityTest {
    private MainActivity mainActivity;
    private TextView tv_set;
    private TextView tv_data;
    private Button feeling_button;
    private Button visual_button;
    private Button achievement_button;

    @Before
    public void setUp() {
        mainActivity = Robolectric.setupActivity(MainActivity.class);
        tv_set = (TextView) mainActivity.findViewById(R.id.tv_set);
        tv_data = (TextView) mainActivity.findViewById(R.id.tv_data);
        feeling_button = (Button) mainActivity.findViewById(R.id.feeling_button);
        visual_button = (Button) mainActivity.findViewById(R.id.visual_button);
        achievement_button = (Button) mainActivity.findViewById(R.id.achievement_button);

    }

    @Test
    public void testInit() {
        Assert.assertNotNull(mainActivity);//判断是否为空
        Assert.assertNotNull(tv_set);
        Assert.assertNotNull(tv_data);
        Assert.assertNotNull(feeling_button);
        Assert.assertNotNull(visual_button);
        Assert.assertNotNull(achievement_button);
        Assert.assertEquals(mainActivity.getTitle(), "精准计步");//获取Activity的标题
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
    public void testTextView() {
        ShadowTextView stv_set = Shadows.shadowOf(tv_set);
        ShadowTextView stv_data = Shadows.shadowOf(tv_data);
        String innerSetText = stv_set.innerText();
        String innerDataText = stv_data.innerText();
        Assert.assertEquals("设置锻炼计划", innerSetText);
        Assert.assertEquals("查看历史步数", innerDataText);
    }

    @Test
    public void testLifecycle() {
        ActivityController controller = Robolectric.buildActivity(MainActivity.class).create().start();
        Activity activity = (Activity) controller.get();
        Assert.assertNotNull(activity);
        controller.resume();
        Assert.assertEquals("设置锻炼计划", tv_set.getText().toString());
        visual_button.performClick();
        Assert.assertEquals("设置锻炼计划", tv_set.getText().toString());
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
