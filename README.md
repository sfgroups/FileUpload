# FileUpload

Commands for refference

```
keytool -import -alias consul-server -file /path/to/server-cert.pem -keystore client-truststore.jks

-Djavax.net.ssl.trustStore=/path/to/client-truststore.jks
-Djavax.net.ssl.trustStorePassword=your_password

System.setProperty("javax.net.ssl.trustStore", "/path/to/client-truststore.jks");
System.setProperty("javax.net.ssl.trustStorePassword", "your_password");

javac SSLJavaClient.java
java SSLJavaClient

```
