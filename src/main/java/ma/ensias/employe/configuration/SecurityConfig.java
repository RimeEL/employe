package ma.ensias.employe.configuration;

import org.springframework.context.annotation.Configuration;

@Configuration
public class SecurityConfig {

    // Vulnerability 1 : hardcoded credentials (SonarCloud S2068 - BLOCKER)
    private static final String DB_PASSWORD = "admin@1234";
    private static final String SECRET_TOKEN = "mysecrettoken_employe_2024";
    private static final String API_KEY = "sk-prod-employe-api-key-abc123xyz";

    public String getDbPassword() {
        return DB_PASSWORD;
    }

    public String getSecretToken() {
        return SECRET_TOKEN;
    }

    public String getApiKey() {
        return API_KEY;
    }
}
