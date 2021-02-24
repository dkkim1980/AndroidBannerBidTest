package com.linecorp.b612.android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AdListener;
import com.facebook.ads.AdSettings;
import com.facebook.ads.AdSize;
import com.facebook.ads.AdView;
import com.facebook.ads.AudienceNetworkAds;

public class MainActivity extends AppCompatActivity {
    private AdView adView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //AdSettings.setDebugBuild(true);
        //AdSettings.setTestMode(true);
        AudienceNetworkAds.initialize(this);



    }

    @Override
    protected void onDestroy() {
        if (adView != null) {
            adView.destroy();
        }
        super.onDestroy();
    }

    public void loadAdBtn(View view) {
        String payload= "{\"type\":\"ID\",\"bid_id\":\"8664050603206114506\",\"placement_id\":\"164620885124192_173032504283030\",\"resolved_placement_id\":\"164620885124192_173032504283030\",\"sdk_version\":\"6.1.0\",\"device_id\":null,\"template\":5,\"payload\":null,\"bid_time_token\":\"V1_eyJwbGF0Zm9ybV9pZCI6MTY0NjIwODg1MTI0MTkyLCJvcnRiX2F1Y3Rpb25faWQiOiI5Njg1M2UyMC03MjhhLTExZWItOWNhNy0wNjAzMDAwMDAwMDAtdmY0bmxoOGUiLCJiaWRfc291cmNlIjo1LCJjbHVzdGVyX2lkIjo4OSwiZW5jcnlwdGVkX2NwbSI6IkFSRTBCYl85eEdXMGVINGR0c0dmdGs5RjVHU3NpaDVnUDZVN0JNc3lfcGFKeXhac3dRTVpsNmx2aTNmeVQ4eVBxZ3Z1ZFhqSl94VGFtUzVjYm1fcEtFeHNTTVR1TFBuaURlOXNvcTgifQ==\",\"encrypted_cpm\":\"ARE0Bb_9xGW0eH4dtsGftk9F5GSsih5gP6U7BMsy_paJyxZswQMZl6lvi3fyT8yPqgvudXjJ_xTamS5cbm_pKExsSMTuLPniDe9soq8\"}";


        //adView = new AdView(this, "164620885124192_173032504283030", AdSize.BANNER_HEIGHT_50);
        adView = new AdView(this, "164620885124192_173032504283030", AdSize.BANNER_HEIGHT_50);

        // payload as constructor parameter test
//        try {
//            adView=new AdView(this, "164620885124192_173032504283030",payload );
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        // Find the Ad Container
        LinearLayout adContainer = (LinearLayout) findViewById(R.id.banner_container);

        // Add the ad view to your activity layout
        adContainer.addView(adView);

        // Request an ad
        AdListener adListener = new AdListener() {

            @Override
            public void onError(Ad ad, AdError adError) {
                // Ad error callback
                Toast.makeText(
                        MainActivity.this,
                        "Error: " + adError.getErrorMessage(),
                        Toast.LENGTH_LONG)
                        .show();
                Log.v("myfb",adError.getErrorMessage());
            }

            @Override
            public void onAdLoaded(Ad ad) {
                // Ad loaded callback
                Log.v("myfb","loaded");
            }

            @Override
            public void onAdClicked(Ad ad) {
                // Ad clicked callback
            }

            @Override
            public void onLoggingImpression(Ad ad) {
                // Ad impression logged callback
            }
        };
        // Request an ad
        adView.loadAd(adView.buildLoadAdConfig().withAdListener(adListener).withBid(payload).build());
        
    }
}