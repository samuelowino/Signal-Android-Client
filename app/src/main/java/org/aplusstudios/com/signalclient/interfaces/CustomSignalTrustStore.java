package org.aplusstudios.com.signalclient.interfaces;

import org.whispersystems.signalservice.api.push.TrustStore;

import java.io.InputStream;

public class CustomSignalTrustStore implements TrustStore {
    @Override
    public InputStream getKeyStoreInputStream() {
        return null;
    }

    @Override
    public String getKeyStorePassword() {
        return null;
    }
}
