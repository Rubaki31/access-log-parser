package alp;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Statistics {
    private long totalTraffic;
    private LocalDateTime minTime;
    private LocalDateTime maxTime;
    private final HashSet<String> sites = new HashSet<>();
    private final HashMap<String, Integer> oses = new HashMap<>();
    private final HashSet<String> notSites = new HashSet<>();
    private final HashMap<String, Integer> browsers = new HashMap<>();

    public Statistics() {
        this.totalTraffic = 0;
        this.minTime = LocalDateTime.MAX;
        this.maxTime = LocalDateTime.MIN;
        //this.entryCount = 0;
    }

    public void addEntry(LogEntry entry) {
        this.totalTraffic += Integer.parseInt(entry.value);
        LocalDateTime dateTrafic = entry.date;
        if (dateTrafic.isBefore(minTime)) {
            minTime = dateTrafic;
        }
        if (dateTrafic.isAfter(maxTime)) {
            maxTime = dateTrafic;
        }
        if (entry.code == 200) {
            sites.add(String.valueOf(entry.method));
        }
        if (entry.code == 404) {
            notSites.add(String.valueOf(entry.method));
        }
        UserAgent ua = new UserAgent(entry.userAgent);
        String os = ua.getOs();
        String browser = ua.getBrowser();
        if (oses.containsKey(os)) {
            oses.put(os, oses.get(os) + 1);
        } else {
            oses.put(os, 1);
        }
        if (browsers.containsKey(browser)) {
            browsers.put(browser, browsers.get(browser) + 1);
        } else {
            browsers.put(browser, 1);
        }

    }

    public int getTrafficRate() {
        int rate = 0;
        Duration duration = Duration.between(minTime, maxTime);

        long diffHours = duration.toHours();
        rate = Math.toIntExact(totalTraffic / diffHours);
        return rate;
    }

    public HashMap<String, Double> osShares() {
        int total = 0;
        for (int count : oses.values()) {
            total += count;
        }
        for (Map.Entry<String, Integer> entry : oses.entrySet()) {
            String os1 = entry.getKey();
            int count = entry.getValue();
            double shares = (double) count / total;
            osShares().put(os1, shares);
        }

        return osShares();
    }
    public HashMap<String,Double>browserShares(){
        int total = 0;
        for (int count:browsers.values()){
            total+=count;
        }
        for (Map.Entry<String,Integer>entry: browsers.entrySet()){
            String br= entry.getKey();
            int count = entry.getValue();
            double shares= (double) count/total;
            browserShares().put(br,shares);
        }
        return browserShares();
    }

    public HashSet<String> getSites() {
        return sites;
    }
}
