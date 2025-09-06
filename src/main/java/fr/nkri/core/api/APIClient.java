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
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            try (OutputStream os = connection.getOutputStream()) {
                final byte[] input = jsonBody.getBytes("utf-8");

                os.write(input, 0, input.length);
            }

            return readResponse(connection);
        }
        finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    /**
     * GET METHOD with token
     *
     * @param targetUrl target address
     * @param token player token
     * @return the server's response as a String
     * @throws IOException error encountered during the request
     */
    public static String get(final String targetUrl, final String token) throws IOException {
        HttpURLConnection connection = null;
        try {
            final URL url = new URL(targetUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");

            if(token != null && !token.isEmpty()){
                connection.setRequestProperty("Authorization", "Bearer " + token);
            }

            return readResponse(connection);
        }
        finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    /**
     * Read the response returned by an HTTP server
     * @param connection http connection
     * @return response
     * @throws IOException error encountered during the request
     */
    private static String readResponse(final HttpURLConnection connection) throws IOException {
        final InputStream stream = connection.getResponseCode() >= 400 ? connection.getErrorStream() : connection.getInputStream();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(stream, "utf-8"))) {
            final StringBuilder response = new StringBuilder();

            String line;
            while((line = br.readLine()) != null){
                response.append(line.trim());
            }

            return response.toString();
        }
    }
}
