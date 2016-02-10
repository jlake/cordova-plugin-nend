package com.effers.kaky.nend;

import org.apache.cordova.*;
import net.nend.android.NendAdInterstitial;
import net.nend.android.NendAdView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

public class Nend extends CordovaPlugin {
    private FrameLayout mRootLayout = null;
    private NendAdView mNendAdView = null;

    @Override
    public boolean execute(String action, JSONArray inputs, final CallbackContext callbackContext) throws JSONException {
        PluginResult result = null;
        final Activity activity = cordova.getActivity();

        if (action.equals("createBanner")) {
            JSONObject options = inputs.optJSONObject(0);
            final int spotId = options.getInt("bannerSpotId");
            final String apiKey = options.getString("bannerApiKey");
            //final int bgColor = Integer.decode(options.getString("bannerBackgroundColor"));

            mRootLayout = (FrameLayout)activity.findViewById(android.R.id.content);

            activity.runOnUiThread(new Runnable() {
                public void run() {
                    mNendAdView = new NendAdView(activity, spotId, apiKey);
                    //mNendAdView.setBackgroundColor(bgColor);
                    mRootLayout.addView(mNendAdView, new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT, Gravity.CENTER_HORIZONTAL));
                    mNendAdView.loadAd();
                    callbackContext.success();
                }
            });
        } else if (action.equals("removeBanner")) {
            activity.runOnUiThread(new Runnable() {
                public void run() {
                    if (mRootLayout != null && mNendAdView != null) {
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
