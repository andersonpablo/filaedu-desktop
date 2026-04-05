package conexaoApi;

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

        try {
            URL url = new URL(ENDPOINT);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);
            conn.setDoOutput(true);

            String json = String.format(
                "{\"lojaId\": %d, \"dataHora\": \"%s\", \"totalAcumulado\": %d}",
                lojaId,
                OffsetDateTime.now(ZoneOffset.of("-03:00")),
                totalAcumulado
            );

            try (OutputStream os = conn.getOutputStream()) {
                os.write(json.getBytes(StandardCharsets.UTF_8));
            }

            int status = conn.getResponseCode();
            if (status != 200 && status != 201) {
                System.err.println("Erro ao enviar registro: HTTP " + status);
            } else {
                System.out.println("Registro enviado com sucesso");
            }

            conn.disconnect();

        } catch (Exception e) {
            System.err.println("Falha ao chamar API");
            e.printStackTrace();
        }
    }
    
}
