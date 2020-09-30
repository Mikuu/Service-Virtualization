package wiremocking;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.github.tomakehurst.wiremock.WireMockServer;
import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

public class App {

    private static int port = 8083;
    private static String serverAddress = "localhost";
    private static WireMockServer wireMockServer = new WireMockServer(options().port(port));

    public static void main(String[] args) {
        launchServer();
    }

    private static void launchServer() {
        wireMockServer.start();
        System.out.println("\n--------------- Mock Server is running ---------------\n");
        loadStubs();
    }

    private static void loadStubs() {
        configureFor(serverAddress, port);
        stubExactUrlOnly();
    }

    public static void stubExactUrlOnly() {
        stubFor(get(urlEqualTo("/consumer/employee/api/employee/ca05aa3d8cb741dfb6eb3f5877b7d374"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withStatus(200)
                        .withJsonBody(jsonBody())));
    }

    private static JsonNode jsonBody() {
        String bodyString = "{\"uid\":\"ca05aa3d8cb741dfb6eb3f5877b7d374\",\"name\":\"hatusne miku\",\"gender\":\"female\",\"nationality\":\"japan\",\"age\":15,\"salary\":57500}\n";
        ObjectMapper mapper = new ObjectMapper();
        JsonNode body = JsonNodeFactory.instance.objectNode();

        try {
            body = mapper.readTree(bodyString);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return body;
    }
}
