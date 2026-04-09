package conexaoApi;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public class RegistroApi {

    private static final String ENDPOINT = "http://localhost:8082/api/registros/save";

    public static void enviarRegistro(Long lojaId, int totalAcumulado) {

        if (totalAcumulado <= 0) {
            System.out.println("Nenhum registro para enviar (delta=0)");
            return;
        }

        try {
            URL url = new URL(ENDPOINT);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            try {
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setConnectTimeout(10000);
                conn.setReadTimeout(10000);
                conn.setDoOutput(true);

                String json = String.format(
                    "{\"lojaId\": %d, \"dataHora\": \"%s\", \"totalAcumulado\": %d}",
                    lojaId,
                    OffsetDateTime.now(ZoneOffset.of("-03:00")),
                    totalAcumulado
                );

                try (OutputStream os = conn.getOutputStream()) {
                    os.write(json.getBytes(StandardCharsets.UTF_8));
                    os.flush();
                }

                int status = conn.getResponseCode();
                if (status == 200 || status == 201) {
                    System.out.println("HTTP " + status + " - Registro enviado com sucesso!");
                } else {
                    System.err.println("HTTP " + status + " - Erro ao enviar registro");
                }
            } finally {
                conn.disconnect();
            }

        } catch (Exception e) {
            System.err.println("Falha ao chamar API: " + e.getMessage());
        }
    }
    
}
