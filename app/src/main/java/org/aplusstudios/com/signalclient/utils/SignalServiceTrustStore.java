package org.aplusstudios.com.signalclient.utils;

import android.content.Context;

import org.aplusstudios.com.signalclient.R;
import org.whispersystems.signalservice.api.push.TrustStore;

import java.io.InputStream;

public class SignalServiceTrustStore implements TrustStore {

    private final Context context;

    public SignalServiceTrustStore(Context context) {
        this.context = context.getApplicationContext();
    }

    @Override
    public InputStream getKeyStoreInputStream() {
        return context.getResources().openRawResource(R.raw.whisper);
    }

    @Override
    public String getKeyStorePassword() {
        return "whisper";
    }
}