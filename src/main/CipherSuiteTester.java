import javax.net.ssl.*;
import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

public class CipherSuiteTester {

    public static void main(String[] args) {
        // List of cipher suites to test
        List<String> cipherSuites = Arrays.asList(
                "TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256",
                "TLS_RSA_WITH_AES_256_GCM_SHA384",
                "TLS_DHE_RSA_WITH_AES_128_GCM_SHA256",
                "TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384"
                // Add more cipher suites as needed
        );

        // Server URL to connect to
        String serverUrl = "your-server.com";
        int serverPort = 443; // Standard HTTPS port

        for (String cipher : cipherSuites) {
            try {
                // Create SSLContext for TLSv1.2
                SSLContext sslContext = SSLContext.getInstance("TLSv1.2");
                sslContext.init(null, new TrustManager[]{new CustomTrustManager()}, new java.security.SecureRandom());

                // Get SSLSocketFactory from the context
                SSLSocketFactory socketFactory = sslContext.getSocketFactory();

                // Create a socket and connect to the server
                try (SSLSocket socket = (SSLSocket) socketFactory.createSocket(serverUrl, serverPort)) {
                    // Set the enabled cipher suites
                    socket.setEnabledCipherSuites(new String[]{cipher});

                    // Start handshake
                    socket.startHandshake();

                    System.out.println("Server supports cipher: " + cipher);
                } catch (Exception e) {
                    System.out.println("Server does NOT support cipher: " + cipher);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // Custom TrustManager to trust all certificates (not recommended for production)
    static class CustomTrustManager implements X509TrustManager {
        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
            return null;
        }

        public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType) {
        }

        public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType) {
        }
    }
}
