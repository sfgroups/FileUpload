import groovy.util.logging.Slf4j
import org.apache.http.HttpEntity
import org.apache.http.client.methods.CloseableHttpResponse
import org.apache.http.client.methods.HttpPost
import org.apache.http.entity.ContentType
import org.apache.http.entity.mime.MultipartEntityBuilder
import org.apache.http.entity.mime.content.FileBody
import org.apache.http.entity.mime.content.StringBody
import org.apache.http.impl.client.CloseableHttpClient
import org.apache.http.impl.client.HttpClients
import org.apache.http.util.EntityUtils
import picocli.CommandLine
import picocli.CommandLine.Command
import picocli.CommandLine.Option

@Command(name = "FileUpload", version = "File Upload 0.1", mixinStandardHelpOptions = true, description = "@|bold Groovy|@ @|underline picocli|@ example")

@Slf4j
class FileUpload implements Runnable {
    @Option(names = ["-u", "--url"], paramLabel = "<URL>", description = "URL to upload")
    String url

    @Option(names = ["-f", "--filename"], paramLabel = "<FILENAME>", description = "filename to upload")
    String filename

    @Option(names = ["-d", "--debug"], description = "Enable Debug")
    String debug

    void run() {
        if (debug) {
            System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.SimpleLog");
            System.setProperty("org.apache.commons.logging.simplelog.showdatetime", "true");
            System.setProperty("org.apache.commons.logging.simplelog.log.org.apache.http.wire", "DEBUG");
            System.setProperty("org.apache.commons.logging.simplelog.log.org.apache.http.impl.conn", "DEBUG");
            System.setProperty("org.apache.commons.logging.simplelog.log.org.apache.http.impl.client", "DEBUG");
            System.setProperty("org.apache.commons.logging.simplelog.log.org.apache.http.client", "DEBUG");
            System.setProperty("org.apache.commons.logging.simplelog.log.org.apache.http", "DEBUG");
        }

        if (!filename || !new File(filename).exists()) {
            log.info( "File name argument missing, or File not found $filename")
            usage()
            return
        }

        if (!url) {
            log.info( "URL required")
            usage()
            return
        }

        upload(url, filename)
    }

    def static void main(args) {
        CommandLine.run(new FileUpload(), args)
    }

    def static usage(){
        println '''
            Usage: FileUpload -u http://192.168.16.8:9090/home/upload -f c:\\shared\\c1.csv
        '''
    }

    public static void upload(url, filename) throws Exception {
        if (!new File(filename).exists()) {
            System.out.println("File path not given");
            System.exit(1);
        }
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            HttpPost httppost = new HttpPost(url);
            File f = new File(filename)
            FileBody bin = new FileBody(f);
            StringBody comment = new StringBody("A binary file of some kind", ContentType.TEXT_PLAIN);

            HttpEntity reqEntity = MultipartEntityBuilder.create()
                    .addPart("filename", bin)
                    .addPart("filename1", new StringBody(f.getName()))
                    .addPart("comment", comment)
                    .build();

            httppost.setEntity(reqEntity);

            log.info("executing request " + httppost.getRequestLine());
            CloseableHttpResponse response = httpclient.execute(httppost);

            try {
                log.info("----------------------------------------");
                log.info(response.getStatusLine().toString());
                println(response.getStatusLine());
                log.info("----------------------------------------");
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    log.info("Response content length: " + resEntity.getContentLength());
                }
                EntityUtils.consume(resEntity);
            } finally {
                response.close();
            }
        } finally {
            httpclient.close();
        }
    }

}
