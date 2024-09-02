SSL Handshake issue

```
System.setProperty("jsse.enableSNIExtension", "false");
java -Djsse.enableSNIExtension=false

Security.setProperty("crypto.policy", "unlimited");
```

1. Basic Command to Capture Handshake
   Use the following command to initiate an SSL/TLS handshake and display the details:
```shell
openssl s_client -connect example.com:443 -msg -tls1_2

openssl s_client -connect www.sfgroups.com:443 -msg -tls1_2 -showcerts -state -debug
openssl s_client -connect www.sfgroups.com:443 -servername www.sfgroups.com -msg -tls1_2 -showcerts -state -debug

openssl s_client -cipher 'DHE-DSS-AES256-GCM-SHA384' -connect www.sfgroups.com:443
openssl s_client -connect <hostname>:<port> -no_tls1_2


openssl ciphers -v

openssl s_client -connect <hostname>:<port> -CAfile /path/to/ca-bundle.crt

openssl s_client -connect <hostname>:<port> -key /path/to/client.key -cert /path/to/client.crt
openssl s_client -connect www.sfgroups.com:443 -verify 5

openssl s_client -connect www.sfgroups.com:443  -prexit

#session re-use
openssl s_client -connect www.sfgroups.com:443 -sess_out session.pem
openssl s_client -connect www.sfgroups.com:443 -sess_in session.pem

openssl s_client -connect www.sfgroups.com:443 -brief
``` 

```shell
curl -v --ciphers ECDHE-RSA-AES256-GCM-SHA384 https://example.com


openssl ciphers -v | awk '{print $1}' | while read cipher; do
echo "Testing $cipher..."
openssl s_client -connect <hostname>:<port> -cipher "$cipher" </dev/null 2>&1 | grep -E "Cipher|handshake|Server certificate"
done
```


https://docs.oracle.com/javase/8/docs/technotes/guides/security/jsse/JSSERefGuide.html#SNIExtension

https://howtodoinjava.com/java/java-security/tls12-sslhandshakeexception/


```
 cat intermediate.crt root.crt > ssl_chain.crt

```
https://discuss.hashicorp.com/t/trouble-getting-consul-connect-and-envoy-to-work/6415/28

Commands for reference

```
keytool -import -alias consul-server -file /path/to/server-cert.pem -keystore client-truststore.jks

-Djavax.net.ssl.trustStore=/path/to/client-truststore.jks
-Djavax.net.ssl.trustStorePassword=your_password

System.setProperty("javax.net.ssl.trustStore", "/path/to/client-truststore.jks");
System.setProperty("javax.net.ssl.trustStorePassword", "your_password");

javac SSLJavaClient.java
java SSLJavaClient

```

https://stackoverflow.com/questions/38539924/consul-check-https-self-signed


If you encounter any issues, please do not hesitate to reach out to us for assistance. You can contact us via the provided email address or through the designated Microsoft Teams channel at https://team.microsfot.com.
