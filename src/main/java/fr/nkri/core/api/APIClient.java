package fr.nkri.core.api;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class APIClient {

    /**
     * POST METHOD
     *
     * @param targetUrl target address
     * @param jsonBody content of the request
     * @return the server's response as a String
     * @throws IOException error encountered during the request
     */
    public static String post(final String targetUrl, final String jsonBody) throws IOException {
        HttpURLConnection connection = null;
        try {
            final URL url = new URL(targetUrl);

            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json; utf-8");
            connection.setDoOutput(true);

            try (OutputStream os = connection.getOutputStream()) {
                final byte[] input = jsonBody.getBytes("utf-8");

                os.write(input, 0, input.length);
            }

            try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"))) {
                final StringBuilder response = new StringBuilder();

                String line;
                while ((line = br.readLine()) != null) {
                    response.append(line.trim());
                }

                return response.toString();
            }
        }
        finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
}
