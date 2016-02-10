package com.effers.kaky.nend;

import org.apache.cordova.*;
import net.nend.android.NendAdInterstitial;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.Activity;

public class Nend extends CordovaPlugin {
    @Override
    public boolean execute(String action, JSONArray inputs, final CallbackContext callbackContext) throws JSONException {
        PluginResult result = null;
        final Activity activity = cordova.getActivity();

        LinearLayout mRootLayout = null;
        NendAdView mNendAdView = null;

        if (action.equals("createBanner")) {
            JSONObject options = inputs.optJSONObject(0);

            mRootLayout = (LinearLayout) findViewById(R.id.root);
            mNendAdView = new NendAdView(this, options.getInt("bannerSpotId"), options.getString("bannerApiKey"));

            activity.runOnUiThread(new Runnable() {
                public void run() {
                    mRootLayout.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM);
                    mRootLayout.addView(mNendAdView, new LinearLayout.LayoutParams( LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
                    mNendAdView.loadAd();
                    callbackContext.success();
                }
            });
        } else if (action.equals("removeBanner")) {
            activity.runOnUiThread(new Runnable() {
                public void run() {
                    if(mRootLayout != null && mNendAdView != null) {
                        mRootLayout.removeView(mNendAdView);
                    }
                    callbackContext.success();
                }
            });
        } else if (action.equals("createInterstitial")) {

            JSONObject options = inputs.optJSONObject(0);
            NendAdInterstitial.loadAd(
                activity.getApplicationContext(),
                options.getString("interstitialApiKey"),
                options.getInt("interstitialSpotId")
            );
            activity.runOnUiThread(new Runnable() {
                public void run() {
                    NendAdInterstitial.showAd(activity);
                    callbackContext.success();
                }
            });
        } else if (action.equals("removeInterstitial")) {
            activity.runOnUiThread(new Runnable() {
                public void run() {
                    NendAdInterstitial.dismissAd();
                    callbackContext.success();
                }
            });
        }

        if(result != null) callbackContext.sendPluginResult( result );
        return true;
    }
}
