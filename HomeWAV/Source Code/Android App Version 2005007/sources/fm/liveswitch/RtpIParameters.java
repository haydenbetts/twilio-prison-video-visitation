package fm.liveswitch;

interface RtpIParameters {
    boolean addLocalContributingSource(long j);

    boolean addLocalSynchronizationSource(long j, String str, String str2, int i);

    boolean addRemoteSynchronizationSource(long j, String str, String str2, int i);

    String getCanonicalName();

    int getInitialReceiveBandwidthEstimate(long j);

    int getInitialReceiveBandwidthEstimate(String str);

    int[] getInitialReceiveBandwidthEstimates();

    int getInitialSendBandwidthEstimate(long j);

    int getInitialSendBandwidthEstimate(String str);

    int[] getInitialSendBandwidthEstimates();

    long[] getLocalContributingSources();

    String getLocalRepairedRtpStreamId(long j);

    String getLocalRepairedRtpStreamId(String str);

    String[] getLocalRepairedRtpStreamIds();

    String[] getLocalRtpStreamIds();

    long[] getLocalSynchronizationSources();

    String getRemoteRepairedRtpStreamId(long j);

    String getRemoteRepairedRtpStreamId(String str);

    String[] getRemoteRepairedRtpStreamIds();

    String[] getRemoteRtpStreamIds();

    long[] getRemoteSynchronizationSources();

    boolean hasLocalContributingSource(long j);

    boolean hasLocalRepairedRtpStreamId(String str);

    boolean hasLocalRtpStreamId(String str);

    boolean hasLocalSynchronizationSource(long j);

    boolean hasNegotiatedPayloadType(int i);

    boolean hasRemoteRepairedRtpStreamId(String str);

    boolean hasRemoteRtpStreamId(String str);

    boolean hasRemoteSynchronizationSource(long j);

    boolean removeLocalContributingSource(long j);

    boolean removeLocalSynchronizationSource(long j);

    boolean removeRemoteSynchronizationSource(long j);

    boolean updateInitialReceiveBandwidthEstimate(long j, int i);

    boolean updateInitialReceiveBandwidthEstimate(String str, int i);

    boolean updateInitialSendBandwidthEstimate(long j, int i);

    boolean updateInitialSendBandwidthEstimate(String str, int i);

    boolean updateLocalSynchronizationSource(long j, String str, String str2);

    boolean updateRemoteSynchronizationSource(long j, String str, String str2);
}
