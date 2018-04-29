package cn.bluemobi.dylan.step.step.utils;

import android.util.Log;

import com.orhanobut.logger.Logger;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TimeUtilsTest {

//    @Before
//    public void setUp() throws Exception {
//    }

    @Test
    public void dateToString() {
        long currentTime = System.currentTimeMillis();
        System.out.println(currentTime);
        String time = TimeUtils.dateToString(currentTime);
        int a = 1;
        int b = 1;
        Assert.assertEquals(a, b);
    }
}
