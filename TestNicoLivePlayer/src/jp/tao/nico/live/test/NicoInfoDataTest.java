package jp.tao.nico.live.test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.test.ActivityUnitTestCase;
import android.test.AndroidTestCase;
import jp.tao.nico.live.*;

public class NicoInfoDataTest extends ActivityInstrumentationTestCase2<NicoLivePlayerActivity> {
	private NicoInfoData nicoinfodata = null;
	private String mail = "testnico@niconoco.jp";
	private String passwd = "nico4e9";
	private String cookie = "user_session_rjaija939824270";
	private String lasturl =  "http://live.nicovideo.jp/watch/lv9403989";
	private String filename = "test.ser";
	
	private NicoLivePlayerActivity activity = null;
	
	public NicoInfoDataTest() {
		super("jp.tao.nico.live", NicoLivePlayerActivity.class);
	}

	protected void setUp() throws Exception {
		super.setUp();
		activity = getActivity();
		nicoinfodata = new NicoInfoData();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testSerialize(){
		nicoinfodata.mail = mail.getBytes();
		nicoinfodata.password = passwd.getBytes();
		nicoinfodata.sessionCookie = cookie.getBytes();
		nicoinfodata.lastUrl = lasturl;
		nicoinfodata.isStore = true;
		
		NicoInfoData result = null;
		
		try {
			ObjectOutputStream oos =new ObjectOutputStream(activity.openFileOutput(filename, activity.MODE_PRIVATE));
			oos.writeObject(nicoinfodata);
			oos.flush();
			oos.close();
			
			FileInputStream fis = activity.openFileInput(filename);
            ObjectInputStream ois = new ObjectInputStream(fis);
            result = (NicoInfoData) ois.readObject();
            ois.close();
		} catch (Exception e) {
		
		}
		
		assertEquals(new String(result.mail), mail);
		assertEquals(new String(result.password), passwd);
		assertEquals(new String(result.sessionCookie), cookie);
		assertEquals(result.lastUrl, lasturl);
		assertEquals(result.isStore, true);
		
	}
}
