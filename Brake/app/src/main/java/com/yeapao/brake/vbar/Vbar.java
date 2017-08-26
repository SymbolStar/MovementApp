package com.yeapao.brake.vbar;


import android.util.Log;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.ptr.IntByReference;

public class Vbar {
	public interface Vdll extends Library {

		Vdll INSTANCE = (Vdll) Native.loadLibrary("vbar", Vdll.class);

		//打开设备
		public IntByReference vbar_open(String addr, long parm);
		//背光控制
		public boolean vbar_backlight(IntByReference vbar_device, boolean on);
		//蜂鸣器控制
		public boolean vbar_beep(IntByReference vbar_device, int times);
		//判断设备是否已连接
		public boolean vbar_is_connected(IntByReference vbar_device);
		//添加要支持的码制
		public boolean vbar_add_symbol_type(IntByReference vbar_device, int type);
		//扫码
		public boolean vbar_scan(IntByReference vbar_device, IntByReference symbol_type, byte[] result_buffer, IntByReference result_size);
		//关闭设备
		public void vbar_close(IntByReference vbar_device);



	}


	//初始化设备变量
	IntByReference device = null;
	byte [] result_buffer = new byte[1024];
	IntByReference symbol_type = new IntByReference(256);
	IntByReference result_size = new IntByReference(1024);
	//打开设备
	//public boolean vbarOpen() {
	public boolean vbarOpen(String addr, long parm) {
		device = Vdll.INSTANCE.vbar_open(addr,parm);
		if(device != null)
		{

			Log.v("######################", "open success");
			return true;
		}
		else
		{

			Log.v("######################", "open fail");
			return false;
		}
	}

	//蜂鸣器控制
	public boolean vbarBeep(int times){
		if(Vdll.INSTANCE.vbar_beep(device,times))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	//背光控制
	public boolean vbarBacklight(boolean bool){
		if(Vdll.INSTANCE.vbar_backlight(device,bool))
		{
			return true;
		}
		else
		{
			return false;
		}
	}


	//判断设备是否已连接
	public boolean vbarIsConnected(){
		if(Vdll.INSTANCE.vbar_is_connected(device))
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	//添加要支持的码制
	public boolean vbarAddSymbolType(int symbol_type){
		if(Vdll.INSTANCE.vbar_add_symbol_type(device,symbol_type))
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	//扫码
	public String vbarScan() {
		String decode = null;
		if(Vdll.INSTANCE.vbar_scan(device,symbol_type,result_buffer,result_size))
		{
			decode = new String(result_buffer);
			return decode;
		}
		else
		{
			return null;
		}
	}
	//关闭设备
	public void vbarClose() {
		if(device != null)
		{
			Vdll.INSTANCE.vbar_close(device);
		}
	}

}



	
	

