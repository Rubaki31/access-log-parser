package alp;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class LogEntry {
    final String ip;
    final Method method;
    LocalDateTime date;

    final String path;
    final int code;
    final String value;
    final String refer;
    final String userAgent;

    enum Method {
        POST,
        GET,
        PUT,
        PATCH,
        DELETE;

        static Method methodSet(String method) {
            return Method.valueOf(method);
            }
    }

    public LogEntry(String line) {
        String [] s= line.split(" ");
        //String [] date = line.split("\\[");
        this.date=LocalDateTime.parse(s[3].replace("]","").replace("[",""), DateTimeFormatter.ofPattern("dd/MMM/yyyy:HH:mm:ss", Locale.ENGLISH));
        this.ip = s[0];
        //this.date = LocalDateTime.parse(s[3]+s[4]);
        this.method =Method.methodSet(s[5].replace("\"",""));
        this.path = s[6] + s[7];
        this.value = s[9];
        this.code = Integer.parseInt(s[8]);
        this.refer = s[10];
        String[] s2 = line.split("\"");
        this.userAgent = s2[5];
    }

    public String getIp() {
        return ip;
    }

    public Method getMethod() {
        return method;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getPath() {
        return path;
    }

    public int getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }

    public String getRefer() {
        return refer;
    }

    public String getUserAgent() {
       return userAgent;

    }
}
