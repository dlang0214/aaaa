package android.barcode;

import java.util.concurrent.locks.ReentrantLock;

import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.Vibrator;
import android.provider.Settings;
import android.util.Log;
import android.view.KeyEvent;
import android.provider.Settings;

public class ScanUtil {

	public static final String TAG = ScanUtil.class.getSimpleName();
	
	static int mSound = 0;
	public static SoundPool scanSound = new SoundPool(1, AudioManager.STREAM_RING, 0);
	static {
		mSound = scanSound.load("/system/media/audio/notifications/Argon.ogg", 1);
		try {
			Thread.sleep(150);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/** scan settings left enable 0 : no; 1 : yes*/
	public static final String SCAN_SETTINGS_LEFT = "scan_settings_left";
	/** scan settings right enable 0 : no; 1 : yes*/
	public static final String SCAN_SETTINGS_RIGHT = "scan_settings_right";
	/** scan models settings 0 : noraml; 1 : continue auto; 2 : continue manual*/
	public static final String SCAN_MODLES_SETTINGS = "scan_models_settings";
	/** scan continue delay settings 0 : none; 1 : 0.5s; 2 : 1s; 3 : custom*/
	public static final String SCAN_CONTINUE_DELAY_SETTINGS = "scan_continue_delay_settings";
	/** scan continue delay value */
	public static final String SCAN_CONTINUE_DELAY_CUSTOM_VALUE = "scan_continue_delay_custom_value";
	/** barcode receive model 0 : fast; 1 : slow; 2 : broadcast*/
	public static final String BARCODE_RECEIVE_MODELS_SETTINGS = "barcode_receive_models_settings";
	/** barcode separator model 0 : none; 1 : '\n'; 2 : Enter*/
	public static final String BARCODE_SEPARATOR_SETTINGS = "barcode_separator_settings";
	/** barcode separator prefix settings 0 : no; 1 : yes*/
	public static final String BARCODE_SEPARATOR_PREFIX_SETTINGS = "barcode_separator_prefix_settings";
	/** barcode separator prefix content */
	public static final String BARCODE_SEPARATOR_PREFIX = "barcode_separator_prefix";
	/** barcode separator suffix settings 0 : no; 1 : yes*/
	public static final String BARCODE_SEPARATOR_SUFFIX_SETTINGS = "barcode_separator_suffix";
	/** barcode separator suffix content */
	public static final String BARCODE_SEPARATOR_SUFFIX = "barcode_separator_suffix";
	/** scan sound settings 0 : no; 1 : yes*/
	public static final String SCAN_SOUND_SETTINGS = "scan_sound_settings";
	/** scan vibrate settings 0 : no; 1 : yes*/
	public static final String SCAN_VIBRATE_SETTINGS = "scan_vibrate_settings";
	
	/**
	 * scan toggle state
	 * @param context
	 * @return true : open; false : close
	 */
	public static boolean getScanToggleState(Context context) {
		boolean flag = false;
		

		return flag;
	}

	static ReentrantLock mLock = new  ReentrantLock();
	/**
	 * send barcode out
	 * @param context
	 * @param barcode
	 */
	public static void sendBarcode(final Context context, final String barcode){
	}
	
	/**
	 * send barcode out
	 * @param context
	 * @param barcode
	 * ReceiveModel:2
	 * dqw
	 */
	public static void sendBarcodebyBroadcast(final Context context, final String barcode){
		
	}
	/**
	 * final barcode result
	 * @param context
	 * @param barcode
	 * @return
	 */
	protected static String getSendResult(Context context, String barcode) {
		// TODO Auto-generated method stub
		StringBuffer result = new StringBuffer();
		if(getBarcodePrefixState(context)){
			result.append(getBarcodePrefix(context));
		}
		result.append(barcode);
		if(getBarcodeSuffixState(context)){
			result.append(getBarcodeSuffix(context));
		}
		if(getBarcodeSeparator(context) == 1){
			result.append("\n");
		}
		return result.toString();
	}
	/**
	 * scan left state 
	 * @param context
	 * @return
	 */
	public static boolean getScanSwitchLeft(Context context){
		return  true;
	}
	/**
	 * set scan left state
	 * @param context
	 * @param flag
	 */
	public static void setScanSwitchLeft(Context context, boolean flag){
		
	}
	/**
	 * scan right state 
	 * @param context
	 * @return
	 */
	public static boolean getScanSwitchRight(Context context){
		return true;
	}
	/**
	 * set scan right state
	 * @param context
	 * @param flag
	 */
	public static void setScanSwitchRight(Context context, boolean flag){
		
	}
	/**
	 * scan model state 
	 * @param context
	 * @return	0 : normal; 1 : continue auto; 2 : continue manual
	 */
	public static int getScanModel(Context context){
		return 0;
	}
	
	/**
	 * set scan model state
	 * @param context
	 * @param state	0 : normal; 1 : continue auto; 2 : continue manual
	 */
	public static void setScanModel(Context context, int state){
		
	}

	/**
	 * get scan delay millis 
	 * @param context
	 * @return	delaymillis
	 */
	public static long getDelayMillis(Context context){
		long delayMillis = 0;
		switch (getScanDelaySetting(context)) {
		case 0:
			delayMillis = 0;
			break;
		case 1:
			delayMillis = 500;
			break;
		case 2:
			delayMillis = 1000;
			break;
		case 3:
			delayMillis = (long) (getScanDelay(context) * 1000);
			break;
		}
		return delayMillis;
	}
	/**
	 * scan delay state 
	 * @param context
	 * @return	0 : none; 1 : 0.5s; 2 : 1s;3 : custom delay
	 */
	public static int getScanDelaySetting(Context context){
		return 0;
	}
	
	/**
	 * set scan delay state
	 * @param context
	 * @param state	0 : none; 1 : 0.5s; 2 : 1s;3 : custom delay
	 */
	public static void setScanDelaySetting(Context context, int state){
		
	}
	/**
	 * scan delay value 
	 * @param context
	 * @return	delay
	 */
	public static float getScanDelay(Context context){
		return 0;
	}
	
	/**
	 * set scan delay value
	 * @param context
	 * @param delay
	 */
	public static void setScanDelay(Context context, float delay){
		
	}
	
	/**
	 * barcode receive model state 
	 * @param context
	 * @return	0 : fast; 1 : slow; 2 : broadcast
	 */
	public static int getBarcodeReceiveModel(Context context){
		return 1;
		
	}
	
	/**
	 * set broadcast receive model state
	 * @param context
	 * @param state	0 : fast; 1 : slow; 2 : broadcast
	 */
	public static void setBarcodeReceiveModel(Context context, int state){
		
	}
	
	/**
	 * barcode separator state 
	 * @param context
	 * @return	0 : none; 1 : newline; 2 : enter
	 */
	public static int getBarcodeSeparator(Context context){
		return 1;
	}
	
	/**
	 * set barcode separator state
	 * @param context
	 * @param state	0 : none; 1 : newline; 2 : enter
	 */
	public static void setBarcodeSeparator(Context context, int state){
		
	}
	
	/**
	 * barcode Prefix state 
	 * @param context
	 * @return	0 : close; 1 : open
	 */
	public static boolean getBarcodePrefixState(Context context){
		return false;
	}
	
	/**
	 * set barcode Prefix state
	 * @param context
	 * @param flag	0 : close; 1 : open
	 */
	public static void setBarcodePrefixState(Context context, boolean flag){
		
	}
	
	/**
	 * barcode Prefix 
	 * @param context
	 * @return
	 */
	public static String getBarcodePrefix(Context context){
		return "";
	}
	
	/**
	 * set barcode Prefix
	 * @param context
	 * @param prefix
	 */
	public static void setBarcodePrefix(Context context, String prefix){
		
	}
	
	/**
	 * barcode Suffix state 
	 * @param context
	 * @return	0 : close; 1 : open
	 */
	public static boolean getBarcodeSuffixState(Context context){
		return false;
	}
	
	/**
	 * set barcode Suffix state
	 * @param context
	 * @param flag	0 : close; 1 : open
	 */
	public static void setBarcodeSuffixState(Context context, boolean flag){
		
	}
	
	/**
	 * barcode Suffix 
	 * @param context
	 * @return
	 */
	public static String getBarcodeSuffix(Context context){
		return "";
	}
	
	/**
	 * set barcode Suffix
	 * @param context
	 * @param suffix
	 */
	public static void setBarcodeSuffix(Context context, String suffix){
		
	}
	
	/**
	 * scan sound state 
	 * @param context
	 * @return
	 */
	public static boolean getScanSound(Context context){
		return false;
	}
	
	/**
	 * set scan sound state
	 * @param context
	 * @param flag
	 */
	public static void setScanSound(Context context, boolean flag){
	}
	
	/**
	 * scan Vibrate state 
	 * @param context
	 * @return
	 */
	public static boolean getScanVibrate(Context context){
		return false;
	}
	
	/**
	 * set scan Vibrate state
	 * @param context
	 * @param flag
	 */
	public static void setScanVibrate(Context context, boolean flag){
		
	}
	/**
	 * check is device exist
	 */
	public static void initDevice(Context context){
		
		
	}
	/**
	*check Scan state
	*/
	public static boolean getScanHaved(Context context){
		boolean bRet=true;
		
		return bRet;					
	}
	
	/**
	 * scan coverage 
	 * 
	 * @param context
	 * @return
	 */
	public static int getScanCoverage(Context context) {
		return 1;
	}

	/**
	 * scan coverage 
	 * 
	 * @param context
	 * @param flag
	 */
	public static void setScanCoverage(Context context, int flag) {
	
	}		
}
