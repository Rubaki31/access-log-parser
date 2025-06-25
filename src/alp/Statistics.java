package alp;
import java.time.LocalDateTime;

public class Statistics {
    int totalTraffic;
    LocalDateTime minTime;
    LocalDateTime maxTime;

    public Statistics() {
        this.totalTraffic = 0;
        this.minTime = null;
        this.maxTime = null;
        //this.entryCount = 0;
    }

    public void addEntry(LogEntry entry) {
        this.totalTraffic += Integer.parseInt(entry.value);
    }

   String he= "Я тут сдался ";
}
