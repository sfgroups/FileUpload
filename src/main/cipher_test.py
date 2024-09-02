import socket
import ssl
import sys

def test_cipher_suites(hostname, port):
    context = ssl.create_default_context()

    print(f"Supported Cipher Suites on {hostname}:{port}")
    for cipher in context.get_ciphers():
        try:
            # Create a new SSLContext with only the specific cipher
            test_context = ssl.SSLContext(ssl.PROTOCOL_TLS_CLIENT)
            test_context.set_ciphers(cipher['name'])
            test_context.check_hostname = False
            test_context.verify_mode = ssl.CERT_NONE

            with socket.create_connection((hostname, port)) as sock:
                with test_context.wrap_socket(sock, server_hostname=hostname) as ssock:
                    print(f"  [+] {cipher['name']} - Supported")
        except (ssl.SSLError, ConnectionResetError) as e:
            print(f"  [-] {cipher['name']} - Not Supported ({str(e)})")

if __name__ == "__main__":
    if len(sys.argv) < 3:
        print("Usage: python supported_ciphers_tester.py <hostname> <port>")
        sys.exit(1)

    hostname = sys.argv[1]
    port = int(sys.argv[2])

    test_cipher_suites(hostname, port)
