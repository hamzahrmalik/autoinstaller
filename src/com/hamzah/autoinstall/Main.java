package com.hamzah.autoinstall;

import android.widget.Button;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XSharedPreferences;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;

public class Main implements IXposedHookLoadPackage {

	@Override
	public void handleLoadPackage(LoadPackageParam lpparam) throws Throwable {
		if(!lpparam.packageName.equals("com.android.packageinstaller"))
			return;
		//hook the method
		XposedHelpers.findAndHookMethod("com.android.packageinstaller.PackageInstallerActivity",
				lpparam.classLoader, "startInstallConfirm", new XC_MethodHook(){
			@Override
			protected void afterHookedMethod(MethodHookParam param)
					throws Throwable {
				//init sharedpref
				XSharedPreferences pref = new XSharedPreferences("com.hamzah.autoinstall", "pref");
				//check if its enabled
				if(pref.getBoolean("enabled", false)){
					XposedBridge.log("Going to autoinstall");
					//get the confirm button
					Button mOk = (Button) XposedHelpers.getObjectField(param.thisObject, "mOk");
					//make it think user has read permissions
					XposedHelpers.setBooleanField(param.thisObject, "mOkCanInstall", true);
					//click install button
					mOk.performClick();
				}
			}
		});
	}

}