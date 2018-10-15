package org.aplusstudios.com.signalclient;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.aplusstudios.com.signalclient.interfaces.CustomSignalTrustStore;
import org.aplusstudios.com.signalclient.interfaces.SignalDataStoreInterface;
import org.whispersystems.libsignal.util.guava.Optional;
import org.whispersystems.signalservice.api.SignalServiceAccountManager;
import org.whispersystems.signalservice.api.SignalServiceMessageSender;
import org.whispersystems.signalservice.api.messages.SignalServiceDataMessage;
import org.whispersystems.signalservice.api.push.SignalServiceAddress;
import org.whispersystems.signalservice.api.push.TrustStore;

public class MainActivity extends AppCompatActivity {

    private final String URL = "https://my.signal.server.com";
    private final TrustStore TRUST_STORE = new CustomSignalTrustStore();
    private final String USERNAME = "+254706916765";
    private final String PASSWORD = "password";
    private final String USER_AGENT = "[FILL_IN]";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void registerWithSignalServer(){
        SignalServiceAccountManager accountManager = new SignalServiceAccountManager(
                URL,TRUST_STORE,USERNAME,PASSWORD,USER_AGENT
        );

        accountManager.requestSmsVerificationCode();
        accountManager.verifyAccountWithCode();
        accountManager.setGcmId();
        accountManager.setPreKeys();
    }

    public void sendTextMessage(){
        SignalServiceMessageSender messageSender = new SignalServiceMessageSender(
                URL,TRUST_STORE,USERNAME,PASSWORD, new SignalDataStoreInterface(),USER_AGENT, Optional.absent()
        );

        messageSender.sendMessage(
                new SignalServiceAddress("+256706996784"),
                SignalServiceDataMessage.newBuilder()
                        .withBody("Hello, this is a test message")
                        .build());

    }

}
