//package com.xrci.standup;
//
//import com.google.android.gms.location.ActivityRecognitionResult;
//import com.google.android.gms.location.DetectedActivity;
//
//import android.app.IntentService;
//import android.content.Intent;
//import android.util.Log;
//
//public class ActivityRecognitionService extends IntentService  {
//
// private String TAG = this.getClass().getSimpleName();
// public ActivityRecognitionService() {
//  super("My Activity Recognition Service");
// }
//
// @Override
// protected void onHandleIntent(Intent intent) {
//  if(ActivityRecognitionResult.hasResult(intent)){
//   ActivityRecognitionResult result = ActivityRecognitionResult.extractResult(intent);
//  // Log.i(TAG, getType(result.getMostProbableActivity().getType()) +"t" + result.getMostProbableActivity().getConfidence());
//   Intent i = new Intent("com.xrci.standup.ACTIVITY_RECOGNITION_DATA");
//   i.putExtra("Activity", result.getMostProbableActivity().getType() );
//   i.putExtra("Confidence", result.getMostProbableActivity().getConfidence());
//   sendBroadcast(i);
//  }
// }
//
// public static String getType(int type)
// {
//
//  if(type == DetectedActivity.UNKNOWN)
//   return "Unknown";
//  else if(type == DetectedActivity.STILL)
//   return "Still";
//else if(type == DetectedActivity.ON_FOOT)
//	return "Walk";
//
//  else if(type == DetectedActivity.IN_VEHICLE)
//   return "In Vehicle";
//  else if(type == DetectedActivity.ON_BICYCLE)
//   return "On Bicycle";
//  else
//	  return "Unknown";
//
//
//
//
//
// // else
//   //return "";
// }
//
// public static int getTypeFromString(String activity)
// {
//
//	 if(activity.contentEquals("Still"))
//		 return DetectedActivity.STILL;
//	 else if(activity.contentEquals("Walk"))
//		 return DetectedActivity.ON_FOOT;
//	 else if(activity.contentEquals("In Vehicle"))
//		 return DetectedActivity.IN_VEHICLE;
//	 else if(activity.contentEquals("On Bicycle"))
//		 return DetectedActivity.ON_BICYCLE;
//
//
//
//
//
// // else
//   return -1;
// }
//
//}
