package org.aplusstudios.com.signalclient.interfaces;

import org.whispersystems.libsignal.IdentityKey;
import org.whispersystems.libsignal.IdentityKeyPair;
import org.whispersystems.libsignal.InvalidKeyException;
import org.whispersystems.libsignal.InvalidKeyIdException;
import org.whispersystems.libsignal.SignalProtocolAddress;
import org.whispersystems.libsignal.state.PreKeyRecord;
import org.whispersystems.libsignal.state.SessionRecord;
import org.whispersystems.libsignal.state.SignalProtocolStore;
import org.whispersystems.libsignal.state.SignedPreKeyRecord;
import org.whispersystems.libsignal.util.KeyHelper;

import java.util.List;

public class SignalDataStoreInterface implements SignalProtocolStore {

    private IdentityKeyPair    identityKey        = KeyHelper.generateIdentityKeyPair();
    private List<PreKeyRecord> oneTimePreKeys     = KeyHelper.generatePreKeys(0, 100);
    private PreKeyRecord       lastResortKey      = oneTimePreKeys.get(0);
    private SignedPreKeyRecord signedPreKeyRecord;

    public List<PreKeyRecord> getOneTimePreKeys(){
        return oneTimePreKeys;
    }

    /**
     * Get the local client's identity key pair.
     *
     * @return The local client's persistent identity key pair.
     */
    @Override
    public IdentityKeyPair getIdentityKeyPair() {
        return null;
    }

    /**
     * Return the local client's registration ID.
     * <p>
     * Clients should maintain a registration ID, a random number
     * between 1 and 16380 that's generated once at install time.
     *
     * @return the local client's registration ID.
     */
    @Override
    public int getLocalRegistrationId() {
        return 0;
    }

    /**
     * Save a remote client's identity key
     * <p>
     * Store a remote client's identity key as trusted.
     *
     * @param address     The address of the remote client.
     * @param identityKey The remote client's identity key.
     * @return True if the identity key replaces a previous identity, false if not
     */
    @Override
    public boolean saveIdentity(SignalProtocolAddress address, IdentityKey identityKey) {
        return false;
    }

    /**
     * Verify a remote client's identity key.
     * <p>
     * Determine whether a remote client's identity is trusted.  Convention is
     * that the Signal Protocol is 'trust on first use.'  This means that
     * an identity key is considered 'trusted' if there is no entry for the recipient
     * in the local store, or if it matches the saved key for a recipient in the local
     * store.  Only if it mismatches an entry in the local store is it considered
     * 'untrusted.'
     * <p>
     * Clients may wish to make a distinction as to how keys are trusted based on the
     * direction of travel. For instance, clients may wish to accept all 'incoming' identity
     * key changes, while only blocking identity key changes when sending a message.
     *
     * @param address     The address of the remote client.
     * @param identityKey The identity key to verify.
     * @param direction   The direction (sending or receiving) this identity is being used for.
     * @return true if trusted, false if untrusted.
     */
    @Override
    public boolean isTrustedIdentity(SignalProtocolAddress address, IdentityKey identityKey, Direction direction) {
        return false;
    }

    /**
     * Load a local PreKeyRecord.
     *
     * @param preKeyId the ID of the local PreKeyRecord.
     * @return the corresponding PreKeyRecord.
     * @throws InvalidKeyIdException when there is no corresponding PreKeyRecord.
     */
    @Override
    public PreKeyRecord loadPreKey(int preKeyId) throws InvalidKeyIdException {
        return null;
    }

    /**
     * Store a local PreKeyRecord.
     *
     * @param preKeyId the ID of the PreKeyRecord to store.
     * @param record   the PreKeyRecord.
     */
    @Override
    public void storePreKey(int preKeyId, PreKeyRecord record) {

    }

    /**
     * @param preKeyId A PreKeyRecord ID.
     * @return true if the store has a record for the preKeyId, otherwise false.
     */
    @Override
    public boolean containsPreKey(int preKeyId) {
        return false;
    }

    /**
     * Delete a PreKeyRecord from local storage.
     *
     * @param preKeyId The ID of the PreKeyRecord to remove.
     */
    @Override
    public void removePreKey(int preKeyId) {

    }

    /**
     * Returns a copy of the {@link SessionRecord} corresponding to the recipientId + deviceId tuple,
     * or a new SessionRecord if one does not currently exist.
     * <p>
     * It is important that implementations return a copy of the current durable information.  The
     * returned SessionRecord may be modified, but those changes should not have an effect on the
     * durable session state (what is returned by subsequent calls to this method) without the
     * store method being called here first.
     *
     * @param address The name and device ID of the remote client.
     * @return a copy of the SessionRecord corresponding to the recipientId + deviceId tuple, or
     * a new SessionRecord if one does not currently exist.
     */
    @Override
    public SessionRecord loadSession(SignalProtocolAddress address) {
        return null;
    }

    /**
     * Returns all known devices with active sessions for a recipient
     *
     * @param name the name of the client.
     * @return all known sub-devices with active sessions.
     */
    @Override
    public List<Integer> getSubDeviceSessions(String name) {
        return null;
    }

    /**
     * Commit to storage the {@link SessionRecord} for a given recipientId + deviceId tuple.
     *
     * @param address the address of the remote client.
     * @param record  the current SessionRecord for the remote client.
     */
    @Override
    public void storeSession(SignalProtocolAddress address, SessionRecord record) {

    }

    /**
     * Determine whether there is a committed {@link SessionRecord} for a recipientId + deviceId tuple.
     *
     * @param address the address of the remote client.
     * @return true if a {@link SessionRecord} exists, false otherwise.
     */
    @Override
    public boolean containsSession(SignalProtocolAddress address) {
        return false;
    }

    /**
     * Remove a {@link SessionRecord} for a recipientId + deviceId tuple.
     *
     * @param address the address of the remote client.
     */
    @Override
    public void deleteSession(SignalProtocolAddress address) {

    }

    /**
     * Remove the {@link SessionRecord}s corresponding to all devices of a recipientId.
     *
     * @param name the name of the remote client.
     */
    @Override
    public void deleteAllSessions(String name) {

    }

    /**
     * Load a local SignedPreKeyRecord.
     *
     * @param signedPreKeyId the ID of the local SignedPreKeyRecord.
     * @return the corresponding SignedPreKeyRecord.
     * @throws InvalidKeyIdException when there is no corresponding SignedPreKeyRecord.
     */
    @Override
    public SignedPreKeyRecord loadSignedPreKey(int signedPreKeyId) throws InvalidKeyIdException {
        return null;
    }

    /**
     * Load all local SignedPreKeyRecords.
     *
     * @return All stored SignedPreKeyRecords.
     */
    @Override
    public List<SignedPreKeyRecord> loadSignedPreKeys() {
        return null;
    }

    /**
     * Store a local SignedPreKeyRecord.
     *
     * @param signedPreKeyId the ID of the SignedPreKeyRecord to store.
     * @param record         the SignedPreKeyRecord.
     */
    @Override
    public void storeSignedPreKey(int signedPreKeyId, SignedPreKeyRecord record) {

    }

    /**
     * @param signedPreKeyId A SignedPreKeyRecord ID.
     * @return true if the store has a record for the signedPreKeyId, otherwise false.
     */
    @Override
    public boolean containsSignedPreKey(int signedPreKeyId) {
        return false;
    }

    /**
     * Delete a SignedPreKeyRecord from local storage.
     *
     * @param signedPreKeyId The ID of the SignedPreKeyRecord to remove.
     */
    @Override
    public void removeSignedPreKey(int signedPreKeyId) {

    }

    public void createKeys() throws InvalidKeyException {
        IdentityKeyPair    identityKey        = KeyHelper.generateIdentityKeyPair();
        List<PreKeyRecord> oneTimePreKeys     = KeyHelper.generatePreKeys(0, 100);
        PreKeyRecord       lastResortKey      = oneTimePreKeys.get(0);
        SignedPreKeyRecord signedPreKeyRecord = KeyHelper.generateSignedPreKey(identityKey, 1);
    }

    public IdentityKeyPair getIdentityKey() {
        return identityKey;
    }

    public PreKeyRecord getLastResortKey() {
        return lastResortKey;
    }

    public SignedPreKeyRecord getSignedPreKeyRecord() {
        return signedPreKeyRecord;
    }
}
