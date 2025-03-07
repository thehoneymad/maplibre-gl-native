package com.mapbox.mapboxsdk.testapp.offline;

import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import com.mapbox.mapboxsdk.AppCenter;
import com.mapbox.mapboxsdk.testapp.utils.OfflineUtils;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

import static com.mapbox.mapboxsdk.testapp.activity.offline.OfflineActivity.JSON_CHARSET;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4ClassRunner.class)
public class OfflineUtilsTest extends AppCenter {

  private static final String REGION_NAME = "hello world";
  private static final String CONVERTED_REGION_NAME = "{\"FIELD_REGION_NAME\":\"hello world\"}";

  @Test
  public void testOfflineUtilsConvertToBytes() throws UnsupportedEncodingException {
    byte[] expected = CONVERTED_REGION_NAME.getBytes(JSON_CHARSET);
    byte[] actual = OfflineUtils.convertRegionName(REGION_NAME);
    assertTrue("Bytes arrays should match", Arrays.equals(expected, actual));
  }

  @Test
  public void testOfflineUtilsConvertToString() throws UnsupportedEncodingException {
    String actual = OfflineUtils.convertRegionName(CONVERTED_REGION_NAME.getBytes(JSON_CHARSET));
    assertEquals("Strings should match", REGION_NAME, actual);
  }

  @Test
  public void testOfflineUtilsConvertNoOp() {
    String convertNoOp = OfflineUtils.convertRegionName(OfflineUtils.convertRegionName(REGION_NAME));
    assertEquals("Strings should match", REGION_NAME, convertNoOp);
  }
}
