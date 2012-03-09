package jp.tao.nico.live.test;

import android.app.Activity;
import android.app.Instrumentation;
import android.os.Handler;
import android.os.Message;
import android.test.ActivityInstrumentationTestCase2;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import jp.tao.nico.live.*;
import jp.tao.nico.live.R;

public class NicoLivePlayerActivityTest extends ActivityInstrumentationTestCase2<NicoLivePlayerActivity> {
	private NicoLivePlayerActivity activity = null;
	private Instrumentation instrumentation = null;
	
	private String mail = "";
	private String passwd= "";
	
	private NicoRequest nicoRequest = null;
	private NicoMessage nicoMessage = null;
	private NicoSocket nicoSocket = null;
	
	//通常のログインをする
	private EditText email; 
	private EditText password;
	private Button btnLogin;
	//アラート受信用のログインをする（通常のログインしたアカウントはログアウトすることはない）
	private Button btnLoginAlert;
	//番組ID:lv000000000から番組情報を取得してコメントサーバに接続します
	//今後、放送Videoも取得したい
	private Button btnLiveNo;
	//コメントサーバまたはアラートコメントサーバからの接続を切ります
	private Button btnDisconnect;
	//番組ID入力欄、じつはパスワード欄を再利用しています
	private EditText etLiveNo;
	//状態表示、コメント表示	
	private EditText etResponse;
	//表示をPasswordから番組IDに書き換えています
	private TextView tvPassword;
	//ビデオ表示したい
	private WebView video;
	
	public NicoLivePlayerActivityTest() {
		super("jp.tao.nico.live", NicoLivePlayerActivity.class);
	}

	protected void setUp() throws Exception {
		super.setUp();
		activity = getActivity();
		instrumentation = getInstrumentation();
		
		setUI();
	}

	protected void tearDown() throws Exception {
		activity = null;
		instrumentation = null;
		super.tearDown();
	}
	
	public void testPreConditions() {
		assertEquals("", email.getText().toString());
		assertEquals("", password.getText().toString());
	}
	
	private void setUI(){
		if (email==null){
			email = (EditText)activity.findViewById(R.id.et_mail);
			
	        password = (EditText)activity.findViewById(R.id.et_password);
	        btnLogin = (Button)activity.findViewById(R.id.btn_login);

	        btnLoginAlert = (Button)activity.findViewById(R.id.btn_loginAlert);

	        btnLiveNo = (Button)activity.findViewById(R.id.btnLive);

	        btnDisconnect = (Button)activity.findViewById(R.id.btnDisconnect);

	        etLiveNo = (EditText)activity.findViewById(R.id.et_password);
	        etResponse = (EditText)activity.findViewById(R.id.ed_response);
	        tvPassword = (TextView)activity.findViewById(R.id.tv_password);
	        video = (WebView)activity.findViewById(R.id.videoView);
		}
	}

	public void onCreate(){
		
	}
	
	public void testLoginSuccess(){
		
		
		activity.runOnUiThread(new Runnable(){
			public void run(){
				email.setText(mail);
				password.setText(passwd);
				etResponse.setText("");
				btnLogin.performClick();
			}
		});
		
		instrumentation.waitForIdleSync();
		while (etResponse.getText().toString().equals("")){	}
		assertTrue(etResponse.getText().toString().equals("ログインしました"));
	}
	
	public void testLoginUnSuccess(){
		
		activity.runOnUiThread(new Runnable(){
			public void run(){
				email.setText("");
				password.setText("");
				etResponse.setText("");
				btnLogin.dispatchTouchEvent(MotionEvent.obtain(5,0,MotionEvent.ACTION_DOWN,0,0,0));
				btnLogin.dispatchTouchEvent(MotionEvent.obtain(5,0,MotionEvent.ACTION_UP,0,0,0));
			}
		});
		
		instrumentation.waitForIdleSync();
		while (etResponse.getText().toString().equals("")){ }
		assertTrue(etResponse.getText().toString().equals("ログインできませんでした"));
	}
}
