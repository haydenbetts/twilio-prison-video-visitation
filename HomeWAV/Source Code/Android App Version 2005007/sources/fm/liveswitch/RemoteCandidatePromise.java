package fm.liveswitch;

class RemoteCandidatePromise extends Promise<Candidate> {
    private Candidate _remoteCandidate;

    public Candidate getRemoteCandidate() {
        return this._remoteCandidate;
    }

    public RemoteCandidatePromise(Candidate candidate) {
        setRemoteCandidate(candidate);
    }

    private void setRemoteCandidate(Candidate candidate) {
        this._remoteCandidate = candidate;
    }
}
