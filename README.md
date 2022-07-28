
# Freedom [![](https://jitpack.io/v/kishansinghpanwar/Freedom.svg)](https://jitpack.io/#kishansinghpanwar/Freedom )
**This android library allows you to set the expiry date of APK to restrict the app use for a valid time, which gives you freedom to risk of misuse the Presentation/Demo app of any project.**

 **To add this Library in your project :**
 - Step 1. Add the JitPack repository to your build file
    
    
```
allprojects {
    repositories {
       ...
       maven { url 'https://jitpack.io' }
    }
}
 ```  
 
 - Step 2. Add the dependency
 ```
dependencies {
      implementation 'com.github.kishansinghpanwar:Freedom:v1.0.1'
}
 ```  
  - Step 3. Add the buildConfigField in Build.gradle(app) file
 

*Freedom required this parameter for fetch the build created time.*

 ```
android { 
	defaultConfig {  
		  buildConfigField "long", "FreedomTime", System.currentTimeMillis() + "L"  
	} 
}
 ```  
**Basic Usage :**

*Initialize this library in onCreate method of LAUNCHER Activity.
For Example : Splash Screen*

    Freedom.initialize(this, BuildConfig.FreedomTime)
		   .setExpireAfterDays(3)
	      	   .setWorkForAllVariant(false)
		   .setMessage("This APK has expired, please contact the developer to get a new APK.")
		   .setHaveToShowMessage(true)
		   .setHaveToClearNotifications(false)
		   .start();



**Properties :**
***
**setExpireAfterDays** :

This method is used for set the time of expire APK from last build generated, 
For example if you will generate a build on Monday 07:00 AM and you have set Expiry Days 3 then that APK will valid till Thursday 07:00 AM. 

     .setExpireAfterDays(3)
*Note : By default Freedom library have 7 days expiry date.* 
***
 **setWorkForAllVariant** : 
 
 This method is used for set Freedom library should work for all build variant(Release, Debug, etc.) or not in your project. if you have set false in this method then the expiry validation should only work on debug build. 
 

    .setWorkForAllVariant(false)

*Note : By default Freedom library have `false` for this, that mean by default this library will work for only Debug Build.* 
***
**setMessage** :

This method is used for set message for expired build, this message will appear as Toast when APK is expire and user have tried to open app.

    .setMessage("This app has expired")
   
*Note : By default Freedom library have *"This APK has expired, please contact the developer to get a new APK."* this message for expired APK.*
***
**setHaveToShowMessage** :

This method is used for set if you have to show message after app expire or not. 

     .setHaveToShowMessage(true)

*Note : By default Freedom library have `true` for this method, that mean if you will not use this method then also Toast message will appear when APK is expired and user will try to open app.*
***
**setHaveToClearNotifications** :

This option  is used for set if you want to clear all notification if app build is expired and user have tried to open app.

     .setHaveToClearNotifications(false)
     
*Note : By default Freedom library have `true` for this method, that mean all notifications will dismiss for your app when user will try to open app after expired.* 
***
**Example :**

To use this library, please see this layout  : 
[MainActivity.java](https://github.com/kishansinghpanwar/Freedom/blob/master/app/src/main/java/com/app/freedomexample/MainActivity.java)


	
## Support

For support, email singhkishanpanwar@gmail.com or send message to Skype : https://join.skype.com/invite/fBcaUWiNAWTh


## Contribution

🌟 Thank you for using this library and If this is useful for you, then please Star 🌟 this. Also you can send pull request and create a issue for any bug. Thanks again.
