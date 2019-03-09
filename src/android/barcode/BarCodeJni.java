package android.barcode;  
public class BarCodeJni {  
    public BarCodeJni(){}
  
    public static native String BarScanGet(String path);
//{wangyantao2014.0911
    public static native int BarScanGetData(String path,byte[]scandata);
	//public static native int setOTGStatus(String path,int cmd);
	public static native int getOTGStatus(String path);
	//{wangyantao2017.1127 Reduce the charger CP1601 cause the system to restart
	public static native int setOTGHostStatus(int nSave);
	public static native int setOTGDeviceStatus(int nSave);
	//Reduce the charger CP1601 cause the system to restart wangyantao2017.1127}
//wangyantao2014.0911}
	public static native int BarScanOpen(String path);   
    public static native int BarScanClose(String path);   
	//{wangyantao 2016.1215 To determine whether it is GB2312
	public static char IsGB2312(byte []buf,int nlen)
	{
		char flag=0,nret=1,ntemp=0;
		int i=0;
		char temp=0;
		for(i=0;i<nlen;i++)
		{
			temp=(char)(buf[i]&0xff);
			if((flag==1)&&((temp>0xA0)&&(temp<0xff)))
			{
				nret&= 1;
				flag=0;
				ntemp=1;
			}
			else if((flag==1)&&(temp<0xa1))
			{
				nret&= 0;
				flag=0;
			}
			else if((flag==0)&&((temp>0xA0)&&(temp<0xf8)))
			{
				flag=1;
			}
			else if((flag==0)&&((temp<0xA0)&&(temp>127)))
			{
				flag=0;
				nret&= 0;
			}
			else
				flag=0;
		}
		if((nret==1)&&(ntemp==0))
		nret=0;
		return nret;
	}
	//wangyantao 2016.1215 To determine whether it is GB2312}
	//{wangyantao 2019.0114 To determine whether it is GBK
		public static char is_gbk(byte[] buf, int nlen) {
		int i = 0;
		char temp = 0, tempV1, tempV2;
		for (i = 0; i < nlen; i++) {
			temp = (char) (buf[i] & 0xff);
			if (temp > 127) {
				if ((temp >= 228) && (temp <= 233)) {
					if ((i + 2) > (nlen - 1))
						return 1; // not enough characters
					tempV1 = (char) (buf[i + 1] & 0xff);
					;
					tempV2 = (char) (buf[i + 2] & 0xff);
					;
					if ((tempV1 >= 128) && (tempV1 <= 191) && (tempV2 >= 128)
							&& (tempV2 <= 191)) 
						return 0;
					else
						return 1;
				}
			}
		}
		return 1;
	}
	public static boolean IsTextUTF8(byte[] buf, int nlen) {
		byte nBytes = 0;
		char chr;
		boolean bAllAscii = true; // all are ASCII, not UTF-8
		for (int i = 0; i < nlen; ++i) {
			chr = (char) (buf[i] & 0xff);
			if ((chr & 0x80) != 0) // Determine if ASCII encoding, if not, suggests that it is possible to utf-8,ascii with 7-bit encoding, but with one byte, the highest bit is marked as 0,C0xxxxxxx
				bAllAscii = false;
			if (nBytes == 0) // If it is not an ASCII code, it should be a multibyte character, calculating the number of bytes
			{
				if (chr >= 0x80) {
					if (chr >= 0xFC && chr <= 0xFD)
						nBytes = 6;
					else if (chr >= 0xF8)
						nBytes = 5;
					else if (chr >= 0xF0)
						nBytes = 4;
					else if (chr >= 0xE0)
						nBytes = 3;
					else if (chr >= 0xC0)
						nBytes = 2;
					else
						return false;

					nBytes--;
				}
			} else // A non-first byte of a multibyte character, which should be 10xxxxxx
			{
				if ((chr & 0xC0) != 0x80)
					return false;

				nBytes--;
			}
		}
		if (nBytes > 0) 
			return false;
		if (bAllAscii) // If it's all ASCII, it means it's not UTF-8.
			return false;

		return true;
	}

	//wangyantao 2019.0114 To determine whether it is GBK}

}  


