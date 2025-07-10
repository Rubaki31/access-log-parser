package alp;
import java.time.Duration;
import java.time.LocalDateTime;

public class Statistics {
    long totalTraffic;
    LocalDateTime minTime;
    LocalDateTime maxTime;

    public Statistics() {
        this.totalTraffic = 0;
        this.minTime = LocalDateTime.MAX;
        this.maxTime = LocalDateTime.MIN;
        //this.entryCount = 0;
    }

    public void addEntry(LogEntry entry) {
        this.totalTraffic += Integer.parseInt(entry.value);
        LocalDateTime dateTrafic = entry.date;
        if (dateTrafic.isBefore(minTime)){
            minTime=dateTrafic;
        }
        if(dateTrafic.isAfter(maxTime)){
            maxTime=dateTrafic;
        }
    }
    public int getTrafficRate (){
        int rate = 0;
        Duration duration = Duration.between(minTime,maxTime);

        long diffHours = duration.toHours();
        rate = Math.toIntExact(totalTraffic / diffHours);
        return rate;
    }

    public LocalDateTime getMaxTime() {
        return maxTime;
    }

    public long getTotalTraffic() {
        return totalTraffic;
    }
}
