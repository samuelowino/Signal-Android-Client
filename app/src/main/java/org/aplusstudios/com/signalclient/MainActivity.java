package org.aplusstudios.com.signalclient;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.widget.Toast;

import org.aplusstudios.com.signalclient.interfaces.CustomSignalTrustStore;
import org.aplusstudios.com.signalclient.interfaces.SignalDataStoreInterface;
import org.aplusstudios.com.signalclient.utils.AccountManagerFactory;
import org.whispersystems.libsignal.util.guava.Optional;
import org.whispersystems.signalservice.api.SignalServiceAccountManager;
import org.whispersystems.signalservice.api.push.TrustStore;
import org.whispersystems.signalservice.internal.configuration.SignalCdnUrl;
import org.whispersystems.signalservice.internal.configuration.SignalContactDiscoveryUrl;
import org.whispersystems.signalservice.internal.configuration.SignalServiceConfiguration;
import org.whispersystems.signalservice.internal.configuration.SignalServiceUrl;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private final String URL = "https://my.signal.server.com";
    private final TrustStore TRUST_STORE = new CustomSignalTrustStore();
    private final String USERNAME = "+254706916765";
    private final String PASSWORD = "password";
    private final String USER_AGENT = "[FILL_IN]";

    private SignalServiceConfiguration serviceConfiguration;
    private SignalDataStoreInterface signalDataStoreInterface;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            registerWithSignalServer();
        } catch (IOException e) {
            Log.e(getClass().getSimpleName(),"registerWithServer Error "+e);
        }
    }

    public  SignalServiceConfiguration getServiceConfig(){
        return new SignalServiceConfiguration(
                new SignalServiceUrl[]{},
                new SignalCdnUrl[]{},
                new SignalContactDiscoveryUrl[]{});
    }

    public void registerWithSignalServer() throws IOException {
        SignalServiceAccountManager accountManager = new SignalServiceAccountManager(getServiceConfig(),
                USERNAME,PASSWORD,USER_AGENT
        );

        accountManager.requestSmsVerificationCode();
        accountManager.verifyAccountWithCode("VERI-CODE","signal-key",123,true,"PIN");
        accountManager.setGcmId(Optional.<String>absent());
        accountManager.setPreKeys(signalDataStoreInterface.getIdentityKeyPair().getPublicKey(),
                signalDataStoreInterface.getSignedPreKeyRecord(),
                signalDataStoreInterface.getOneTimePreKeys());
    }

    @SuppressLint("StaticFieldLeak")
    private void handleRequestVerification(String e164number, final boolean gcmSupported) {

        new AsyncTask<Void, Void, Pair<String, Optional<String>>>() {
            @Override
            protected Pair<String, Optional<String>> doInBackground(Void... voids) {
                try {

                    String password = "181818181";

                    Optional<String> gcmToken;

                    if (gcmSupported) {
                        gcmToken = Optional.absent();
                    } else {
                        gcmToken = Optional.absent();
                    }

                    SignalServiceAccountManager accountManager = AccountManagerFactory.createManager(MainActivity.this, e164number, password);
                    accountManager.requestSmsVerificationCode();

                    return new Pair<>(password, gcmToken);
                } catch (IOException e) {
                    Log.w(getClass().getSimpleName(), "Error during account registration", e);
                    return null;
                }
            }

            protected void onPostExecute(Pair<String, Optional<String>> result) {
                if (result == null) {
                    Toast.makeText(MainActivity.this, "Unable to connect", Toast.LENGTH_LONG).show();
                    return;
                }else {
                    Toast.makeText(MainActivity.this, "Connected "+result, Toast.LENGTH_LONG).show();
                }
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

}
