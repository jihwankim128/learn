package tobyspring.tobyspringtwo.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.util.stream.Collectors;

public class SimpleApiExecutor implements ApiExecutor {

    @Override
    public String execute(final URI uri) throws IOException {
        final HttpURLConnection connection;
        final String response;
        connection = (HttpURLConnection) uri.toURL().openConnection();
        try (final BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            response = br.lines().collect(Collectors.joining());
        }
        return response;
    }

}
