package jp.tao.nico.live.test;

import android.test.AndroidTestCase;
import jp.tao.nico.live.*;

public class NicoRequestTest extends AndroidTestCase {

	/**
	 * @param name
	 */
	private NicoMessage nicoMessage = null;
	private String mail = "";
	private String password = "";
	
	private String cookieName = "user_session";
	private String user_session = "user_session_18181135_265723068460000908";
	private String cookieDomain = ".nicovideo.jp";
	
	public NicoRequestTest() {
		super();
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
		nicoMessage = new NicoMessage();
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testLoginUnsuccess(){
		NicoRequest nicoRequest = new NicoRequest(nicoMessage);
		nicoRequest.login("amsmdk@aaksasaa.net", "safaf");
		assertFalse(nicoRequest.isLogin());
	}
	public void testLoginSuccess(){
		NicoRequest nicoRequest = new NicoRequest(nicoMessage);
		nicoRequest.login(mail, password);
		assertTrue(nicoRequest.isLogin());
	}
	
	public void testLoginCookie1(){
		NicoRequest nicoRequest = new NicoRequest(nicoMessage);
		nicoRequest.login(mail, password);
		assertEquals(nicoRequest.getLoginCookie(),nicoRequest.getLoginCookie(nicoRequest.getCookieStore()));
	}
	public void testLoginCookie2(){
		NicoRequest nicoRequest = new NicoRequest(nicoMessage);
		nicoRequest.setLoginCookie(cookieName + "=" + user_session + ";domain=" +cookieDomain + ";Path=/");
		assertEquals(nicoRequest.getLoginCookie(),(cookieName + "=" + user_session + ";domain=" + cookieDomain + ";Path=/"));
	}
}
