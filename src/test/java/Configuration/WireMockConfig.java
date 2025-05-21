package Configuration;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;

public class WireMockConfig {
    public static WireMockServer wireMockServer;

    public static void startServer() {
        if (wireMockServer == null) {
            wireMockServer = new WireMockServer(8080);
            wireMockServer.start();;
        }
    }

    public static void stopServer() {
        if (wireMockServer != null)
            wireMockServer.stop();
    }

    public static WireMockServer getServer() {
        return wireMockServer;
    }
}