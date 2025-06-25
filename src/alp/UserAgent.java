package alp;


public class UserAgent {

    final String browser;
    final String os;

    public UserAgent(String userAgent) {
        this.os = osParser(userAgent);
        this.browser = browserParser(userAgent);
    }

    public String getBrowser() {
        return browser;
    }

    public String getOs() {
        return os;
    }

    private String osParser(String userAgent) {
        if (userAgent == null || userAgent.isEmpty()) {
            return "Unknown";
        }
        if (userAgent.contains("Windows")) {
            return "Windows";
        } else if (userAgent.contains("Linux")) {
            return "Linux";
        } else if (userAgent.contains("iPhone")) {
            return "iOs";
        } else if (userAgent.contains("AppleWebKit")) {
            return "MacOs";
        } else if (userAgent.contains("Android")) {
            return "Android";
        } else return "other";
    }

    private String browserParser(String userAgent) {
        if (userAgent == null || userAgent.isEmpty()) {
            return "Unknown";
        }
        if (userAgent.contains("Opera")) {
            return "Opera";
        } else if (userAgent.contains("Edg")) {
            return "Edge";
        } else if (userAgent.contains("Mobile")) {
            return "Safari";
        } else if (userAgent.contains("Googlebot") || userAgent.contains("Bot")||userAgent.contains("YandexAccessibilityBot")) {
            return "Crawler";
        } else if (userAgent.contains("OPR")) {
            return "Opera";
        } else if (userAgent.contains("KHTML") || userAgent.contains("Chrome")) {
            return "Chrome";
        } else if (userAgent.contains("Firefox") || userAgent.contains("Gecko")) {
            return "Mozilla";
        } else return "other";
    }



}
