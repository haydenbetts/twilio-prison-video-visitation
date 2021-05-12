package com.twilio.video;

import androidx.annotation.NonNull;
import java.util.ArrayList;
import java.util.List;

public class StatsReport {
    private List<IceCandidatePairStats> iceCandidatePairStats = new ArrayList();
    private List<IceCandidateStats> iceCandidateStats = new ArrayList();
    private List<LocalAudioTrackStats> localAudioTrackStats = new ArrayList();
    private List<LocalVideoTrackStats> localVideoTrackStats = new ArrayList();
    private final String peerConnectionId;
    private List<RemoteAudioTrackStats> remoteAudioTrackStats = new ArrayList();
    private List<RemoteVideoTrackStats> remoteVideoTrackStats = new ArrayList();

    StatsReport(@NonNull String str) {
        this.peerConnectionId = str;
    }

    @NonNull
    public String getPeerConnectionId() {
        return this.peerConnectionId;
    }

    @NonNull
    public List<LocalAudioTrackStats> getLocalAudioTrackStats() {
        return this.localAudioTrackStats;
    }

    @NonNull
    public List<LocalVideoTrackStats> getLocalVideoTrackStats() {
        return this.localVideoTrackStats;
    }

    @NonNull
    public List<RemoteAudioTrackStats> getRemoteAudioTrackStats() {
        return this.remoteAudioTrackStats;
    }

    @NonNull
    public List<RemoteVideoTrackStats> getRemoteVideoTrackStats() {
        return this.remoteVideoTrackStats;
    }

    @NonNull
    public List<IceCandidatePairStats> getIceCandidatePairStats() {
        return this.iceCandidatePairStats;
    }

    @NonNull
    public List<IceCandidateStats> getIceCandidateStats() {
        return this.iceCandidateStats;
    }

    /* access modifiers changed from: package-private */
    public void addIceCandidatePairStats(IceCandidatePairStats iceCandidatePairStats2) {
        this.iceCandidatePairStats.add(iceCandidatePairStats2);
    }

    /* access modifiers changed from: package-private */
    public void addIceCandidateStats(IceCandidateStats iceCandidateStats2) {
        this.iceCandidateStats.add(iceCandidateStats2);
    }

    /* access modifiers changed from: package-private */
    public void addLocalAudioTrackStats(LocalAudioTrackStats localAudioTrackStats2) {
        this.localAudioTrackStats.add(localAudioTrackStats2);
    }

    /* access modifiers changed from: package-private */
    public void addLocalVideoTrackStats(LocalVideoTrackStats localVideoTrackStats2) {
        this.localVideoTrackStats.add(localVideoTrackStats2);
    }

    /* access modifiers changed from: package-private */
    public void addAudioTrackStats(RemoteAudioTrackStats remoteAudioTrackStats2) {
        this.remoteAudioTrackStats.add(remoteAudioTrackStats2);
    }

    /* access modifiers changed from: package-private */
    public void addVideoTrackStats(RemoteVideoTrackStats remoteVideoTrackStats2) {
        this.remoteVideoTrackStats.add(remoteVideoTrackStats2);
    }
}
