package cn.bluemobi.dylan.step.step.utils;

import android.util.Log;

import com.orhanobut.logger.Logger;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

public class TimeUtilsTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void dateToString() {
        long currentTime = System.currentTimeMillis();
        String time = TimeUtils.dateToString(currentTime);
        Date day=new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Assert.assertEquals(df.format(day).replace("-","."), time);
    }
}
