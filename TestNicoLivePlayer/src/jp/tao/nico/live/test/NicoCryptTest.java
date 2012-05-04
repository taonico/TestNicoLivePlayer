package jp.tao.nico.live.test;

import android.test.AndroidTestCase;
import jp.tao.nico.live.*;

public class NicoCryptTest extends AndroidTestCase {
	private String mail = "testnico@test.nicovideo.jp";
	private String key = null;
	
	public NicoCryptTest() {
		super();
	}

	protected void setUp() throws Exception {
		super.setUp();
		key = new NicoKey().getKey();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testCrypt(){
		byte[] byteCrypt = NicoCrypt.encrypt(key, mail);
		assertTrue(NicoCrypt.decrypt(key, byteCrypt).equals(mail));
	}

}
