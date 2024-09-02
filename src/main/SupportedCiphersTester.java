import javax.net.ssl.*;
import java.io.IOException;
import java.net.Socket;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class SupportedCiphersTester {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: java SupportedCiphersTester <hostname> <port>");
            return;
        }

        String hostname = args[0];
        int port = Integer.parseInt(args[1]);

        try {
            SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
            try (SSLSocket socket = (SSLSocket) factory.createSocket(hostname, port)) {
                String[] supportedCiphers = socket.getSupportedCipherSuites();
                System.out.println("Supported Cipher Suites on " + hostname + ":" + port);
                for (String cipher : supportedCiphers) {
                    socket.setEnabledCipherSuites(new String[]{cipher});
                    try {
                        socket.startHandshake();
                        System.out.println("  [+] " + cipher + " - Supported");
                    } catch (IOException e) {
                        System.out.println("  [-] " + cipher + " - Not Supported");
                    }
                }
            }
        } catch (IOException | NoSuchAlgorithmException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
