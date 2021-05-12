package fm.liveswitch;

import com.microsoft.appcenter.Constants;
import java.io.IOException;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.tls.Certificate;
import org.bouncycastle.crypto.tls.CertificateRequest;
import org.bouncycastle.crypto.tls.DefaultTlsSignerCredentials;
import org.bouncycastle.crypto.tls.SignatureAndHashAlgorithm;
import org.bouncycastle.crypto.tls.TlsAuthentication;
import org.bouncycastle.crypto.tls.TlsContext;
import org.bouncycastle.crypto.tls.TlsCredentials;
import org.bouncycastle.crypto.tls.TlsFatalAlert;

class DtlsBouncyCastleClientAuthentication implements TlsAuthentication {
    private DtlsCertificate certificate;
    private TlsContext context;
    private IAction1<byte[]> onRemoteCertificate;
    public String remoteFingerprint;
    public String remoteFingerprintAlgorithm;

    public String getRemoteFingerprintAlgorithm() {
        return this.remoteFingerprintAlgorithm;
    }

    public String getRemoteFingerprint() {
        return this.remoteFingerprint;
    }

    public IAction1<byte[]> getOnRemoteCertificate() {
        return this.onRemoteCertificate;
    }

    public DtlsCertificate getCertificate() {
        return this.certificate;
    }

    public DtlsBouncyCastleClientAuthentication(TlsContext tlsContext, DtlsCertificate dtlsCertificate, String str, String str2, IAction1<byte[]> iAction1) {
        this.context = tlsContext;
        this.certificate = dtlsCertificate;
        this.remoteFingerprintAlgorithm = str;
        this.remoteFingerprint = str2;
        this.onRemoteCertificate = iAction1;
    }

    public void notifyServerCertificate(Certificate certificate2) throws IOException {
        String str;
        IAction1<byte[]> iAction1;
        if (certificate2 != null) {
            org.bouncycastle.asn1.x509.Certificate[] certificateList = certificate2.getCertificateList();
            if (certificateList == null || certificateList.length == 0) {
                throw new TlsFatalAlert(42);
            }
            org.bouncycastle.asn1.x509.Certificate certificate3 = certificateList[0];
            if (this.remoteFingerprintAlgorithm.toLowerCase().equals("sha2") || this.remoteFingerprintAlgorithm.toLowerCase().equals("sha256") || this.remoteFingerprintAlgorithm.toLowerCase().equals("sha-256")) {
                str = HashContextBase.compute(HashType.Sha256, DataBuffer.wrap(certificate3.getEncoded())).toHexString();
            } else if (this.remoteFingerprintAlgorithm.toLowerCase().equals("sha") || this.remoteFingerprintAlgorithm.toLowerCase().equals("sha1") || this.remoteFingerprintAlgorithm.toLowerCase().equals("sha-1")) {
                str = HashContextBase.compute(HashType.Sha1, DataBuffer.wrap(certificate3.getEncoded())).toHexString();
            } else {
                throw new TlsFatalAlert(49);
            }
            if (str.toLowerCase().equals(this.remoteFingerprint.replace(Constants.COMMON_SCHEMA_PREFIX_SEPARATOR, "").toLowerCase())) {
                byte[] bArr = null;
                try {
                    bArr = certificate3.getEncoded();
                } catch (Exception e) {
                    Log.error("Could not process remote DTLS certificate.", e);
                }
                if (bArr != null && (iAction1 = this.onRemoteCertificate) != null) {
                    iAction1.invoke(bArr);
                    return;
                }
                return;
            }
            throw new TlsFatalAlert(49);
        }
        throw new TlsFatalAlert(42);
    }

    public TlsCredentials getClientCredentials(CertificateRequest certificateRequest) throws IOException {
        Log.debug("Generating DTLS 'client certificate' message.");
        if (certificateRequest.getCertificateTypes() == null) {
            return null;
        }
        AsymmetricKeyParameter ecdsaPrivateKey = DtlsBouncyCastleUtility.getEcdsaPrivateKey(getCertificate());
        if (ecdsaPrivateKey != null) {
            if (certificateRequest.getSupportedSignatureAlgorithms() == null) {
                return new DefaultTlsSignerCredentials(this.context, DtlsBouncyCastleUtility.getCertificate(getCertificate()), ecdsaPrivateKey);
            }
            SignatureAndHashAlgorithm signatureAndHashAlgorithm = DtlsBouncyCastleUtility.getSignatureAndHashAlgorithm(certificateRequest.getSupportedSignatureAlgorithms(), 3);
            if (signatureAndHashAlgorithm != null) {
                return new DefaultTlsSignerCredentials(this.context, DtlsBouncyCastleUtility.getCertificate(getCertificate()), ecdsaPrivateKey, signatureAndHashAlgorithm);
            }
        }
        AsymmetricKeyParameter rsaPrivateKey = DtlsBouncyCastleUtility.getRsaPrivateKey(getCertificate());
        if (rsaPrivateKey == null) {
            return null;
        }
        if (certificateRequest.getSupportedSignatureAlgorithms() == null) {
            return new DefaultTlsSignerCredentials(this.context, DtlsBouncyCastleUtility.getCertificate(getCertificate()), rsaPrivateKey);
        }
        SignatureAndHashAlgorithm signatureAndHashAlgorithm2 = DtlsBouncyCastleUtility.getSignatureAndHashAlgorithm(certificateRequest.getSupportedSignatureAlgorithms(), 1);
        if (signatureAndHashAlgorithm2 != null) {
            return new DefaultTlsSignerCredentials(this.context, DtlsBouncyCastleUtility.getCertificate(getCertificate()), rsaPrivateKey, signatureAndHashAlgorithm2);
        }
        return null;
    }
}
