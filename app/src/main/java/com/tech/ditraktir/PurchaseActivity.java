package com.tech.ditraktir;

import android.content.Intent;
import android.os.Bundle;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.anjlab.android.iab.v3.BillingProcessor;
import com.anjlab.android.iab.v3.TransactionDetails;


public class PurchaseActivity extends AppCompatActivity {
    // SAMPLE APP CONSTANTS
    private static final String ACTIVITY_NUMBER = "activity_num";
    private static final String LOG_TAG = "iabv3";

    // PRODUCT & SUBSCRIPTION IDS
    private static final String PRODUCT_ID_25K = "com.tech.ditraktir.25k";
    private static final String PRODUCT_ID_50K = "com.tech.ditraktir.50k";
    private static final String PRODUCT_ID_100K = "com.tech.ditraktir.100k";
    private static final String PRODUCT_ID_200K = "com.tech.ditraktir.200k";
    private static final String PRODUCT_ID_300K = "com.tech.ditraktir.300k";
    private static final String PRODUCT_ID_400K = "com.tech.ditraktir.400k";
    private static final String PRODUCT_ID_500K = "com.tech.ditraktir.500k";

    //private static final String SUBSCRIPTION_ID = "com.anjlab.test.iab.subs1";
    private static final String LICENSE_KEY = null; // PUT YOUR MERCHANT KEY HERE;
    // put your Google merchant id here (as stated in public profile of your Payments Merchant Center)
    // if filled library will provide protection against Freedom alike Play Market simulators
    private static final String MERCHANT_ID=null;

    private BillingProcessor bp;
    private boolean readyToPurchase = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase);

        //TextView title = (TextView)findViewById(R.id.titleTextView);
        //title.setText(String.format(getString(R.string.app_name)));

        if(!BillingProcessor.isIabServiceAvailable(this)) {
            showToast("In-app billing service is unavailable, please upgrade Android Market/Play to version >= 3.9.16");
        }

        bp = new BillingProcessor(this, LICENSE_KEY, MERCHANT_ID, new BillingProcessor.IBillingHandler() {
            @Override
            public void onProductPurchased(@NonNull String productId, @Nullable TransactionDetails details) {
                showToast("onProductPurchased: " + productId);
                updateTextViews();


            }
            @Override
            public void onBillingError(int errorCode, @Nullable Throwable error) {
                showToast("onBillingError: " + Integer.toString(errorCode));
            }
            @Override
            public void onBillingInitialized() {
                showToast("onBillingInitialized");
                readyToPurchase = true;
                updateTextViews();
            }
            @Override
            public void onPurchaseHistoryRestored() {
                showToast("onPurchaseHistoryRestored");
                /*for(String sku : bp.listOwnedProducts())
                    Log.d(LOG_TAG, "Owned Managed Product: " + sku);
                for(String sku : bp.listOwnedSubscriptions())
                    Log.d(LOG_TAG, "Owned Subscription: " + sku);
                    */
                updateTextViews();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        updateTextViews();
    }

    @Override
    public void onDestroy() {
        if (bp != null)
            bp.release();
        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!bp.handleActivityResult(requestCode, resultCode, data))
            super.onActivityResult(requestCode, resultCode, data);
    }

    private void updateTextViews() {
        TextView text = (TextView)findViewById(R.id.productIdTextView);
        //text.setText(String.format("%s is%s purchased", PRODUCT_ID, bp.isPurchased(PRODUCT_ID) ? "" : " not"));
        //text = (TextView)findViewById(R.id.subscriptionIdTextView);
        //text.setText(String.format("%s is%s subscribed", SUBSCRIPTION_ID, bp.isSubscribed(SUBSCRIPTION_ID) ? "" : " not"));
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    public void onClick(View v) {
        if (!readyToPurchase) {
            showToast("Billing not initialized.");
            return;
        }
        Boolean consumed=false;
        switch (v.getId()) {
            case R.id.pid_25K:
                bp.purchase(this,PRODUCT_ID_25K);
                consumed = bp.consumePurchase(PRODUCT_ID_25K);
                updateTextViews();
                if (consumed)
                    showToast("Successfully consumed");
                break;
            case R.id.pid_50K:
                bp.purchase(this,PRODUCT_ID_50K);
                consumed = bp.consumePurchase(PRODUCT_ID_50K);
                updateTextViews();
                if (consumed)
                    showToast("Successfully consumed");
                break;
            case R.id.pid_100K:
                bp.purchase(this,PRODUCT_ID_100K);
                consumed = bp.consumePurchase(PRODUCT_ID_100K);
                updateTextViews();
                if (consumed)
                    showToast("Terima kasih atas traktirannya :)");
                break;
            case R.id.pid_200K:
                bp.purchase(this,PRODUCT_ID_200K);
                consumed = bp.consumePurchase(PRODUCT_ID_200K);
                updateTextViews();
                if (consumed)
                    showToast("Terima kasih atas traktirannya :)");
                break;
            case R.id.pid_300K:
                bp.purchase(this,PRODUCT_ID_300K);
                consumed = bp.consumePurchase(PRODUCT_ID_300K);
                updateTextViews();
                if (consumed)
                    showToast("Terima kasih atas traktirannya :)");
                break;
            case R.id.pid_400K:
                bp.purchase(this,PRODUCT_ID_400K);
                consumed = bp.consumePurchase(PRODUCT_ID_400K);
                updateTextViews();
                if (consumed)
                    showToast("Terima kasih atas traktirannya :)");
                break;
            case R.id.pid_500K:
                bp.purchase(this,PRODUCT_ID_500K);
                consumed = bp.consumePurchase(PRODUCT_ID_500K);
                updateTextViews();
                if (consumed)
                    showToast("Terima kasih atas traktirannya :)");
                break;

            default:
                break;
        }
    }

}