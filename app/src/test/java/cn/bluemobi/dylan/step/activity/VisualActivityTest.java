package cn.bluemobi.dylan.step.activity;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import cn.bluemobi.dylan.step.BuildConfig;

import static org.junit.Assert.*;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = 21, constants = BuildConfig.class)
public class VisualActivityTest {

    private VisualActivity visualActivity;

    @Before
    public void setUp() {
        visualActivity = Robolectric.setupActivity(VisualActivity.class);

    }

    @Test
    public void testInit() {
        Assert.assertNull(visualActivity);
    }
}