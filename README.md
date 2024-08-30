SSL Handshare issue

```
System.setProperty("jsse.enableSNIExtension", "false");
java -Djsse.enableSNIExtension=false

Security.setProperty("crypto.policy", "unlimited");
```

https://docs.oracle.com/javase/8/docs/technotes/guides/security/jsse/JSSERefGuide.html#SNIExtension

https://howtodoinjava.com/java/java-security/tls12-sslhandshakeexception/


```
# .gitlab-ci.yml

stages:
  - build
  - test

build:
  stage: build
  script:
    - echo "Building..."
    # Build your code here

test:
  stage: test
  script:
    - echo "Testing..."
    # Run your tests here

release:
  stage: deploy
  script:
    - echo "Deploying..."
    # Deploy your code here
  rules:
    - if: '$CI_COMMIT_TAG'

```

```
# .gitlab-ci.yml

variables:
  PACKAGE_NAME: "my-package-${CI_COMMIT_TAG}"

build_package:
  stage: build
  script:
    - echo "Building package: $PACKAGE_NAME"
    # Build and package your code using the $PACKAGE_NAME

```
```
<style>
  .empty-line {
    padding-bottom: 1em; /* Add padding at the bottom of the div */
  }
</style>

<div>
  This is the first line.
</div>

<div class="empty-line"></div>

<div>
  This is the second line.
</div>

```

```
 cat intermediate.crt root.crt > ssl_chain.crt

```
https://discuss.hashicorp.com/t/trouble-getting-consul-connect-and-envoy-to-work/6415/28

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

https://stackoverflow.com/questions/38539924/consul-check-https-self-signed


If you encounter any issues, please do not hesitate to reach out to us for assistance. You can contact us via the provided email address or through the designated Microsoft Teams channel at https://team.microsfot.com.
