package fm.liveswitch;

interface DtlsIClient {
    void close();

    byte[] getKeyingMaterial();

    int getSelectedSrtpProtectionProfile();

    Error open();

    void receive(DataBuffer dataBuffer);

    void send(DataBuffer dataBuffer);

    void setOnDataDecrypted(IAction1<DataBuffer> iAction1);

    void setOnError(IAction1<Exception> iAction1);

    void setOnKeyingMaterialAvailable(IAction1<DataBuffer> iAction1);
}
