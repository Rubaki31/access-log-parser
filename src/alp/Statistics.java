package alp;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

import static java.time.ZoneOffset.UTC;

public class Statistics {
    private long totalTraffic;
    private LocalDateTime minTime;
    private LocalDateTime maxTime;
    private final HashSet<String> sites = new HashSet<>();
    private final HashMap<String, Integer> oses = new HashMap<>();
    private final HashSet<String> notSites = new HashSet<>();
    private final HashMap<String, Integer> browsers = new HashMap<>();
    private final HashMap<String, Integer> users = new HashMap<>();
    private int counter;
    private int errorReq;
    private final HashMap <Integer,Integer> ratePerSec = new HashMap<>();
    private final HashSet<String> referers = new HashSet<>();
    private final HashMap<String,Integer> visitsPerUser = new HashMap<>();



    public Statistics() {
        this.totalTraffic = 0;
        this.minTime = LocalDateTime.MAX;
        this.maxTime = LocalDateTime.MIN;
        this.counter = 0;
    }

    public void addEntry(LogEntry entry) {
        this.totalTraffic += Integer.parseInt(entry.value);
        LocalDateTime dateTrafic = entry.date;
        referers.add(entry.refer.split("/")[2]);
        if (dateTrafic.isBefore(minTime)) {
            minTime = dateTrafic;
        }
        if (dateTrafic.isAfter(maxTime)) {
            maxTime = dateTrafic;
        }
        if (entry.code == 200) {
            sites.add(entry.path);
        }
        if (entry.code == 404) {
            notSites.add(entry.path);
        }
        if(entry.code>=400 &&entry.code<=520){errorReq++;}
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
        if(!ua.isBot()){
           if(users.containsKey(entry.ip)){
               users.put(entry.ip,users.get(entry.ip)+1);
           }else {
               users.put(entry.ip,1);
           }
           int secCount= (int) dateTrafic.toEpochSecond(UTC);
           if(ratePerSec.containsKey(secCount)){
               ratePerSec.put(secCount,ratePerSec.get(secCount)+1);
           } else {
               ratePerSec.put(secCount,1);
           }
            visitsPerUser.put(entry.ip,visitsPerUser.getOrDefault(entry.ip,0)+1);

            counter++;
        }


    }

    public int getTrafficRate() {
        int rate = 0;
        Duration duration = Duration.between(minTime, maxTime);

        long diffHours = duration.toHours();
        rate = Math.toIntExact(totalTraffic / diffHours);
        return rate;
    }
    public int getMaxRate(){

        return Collections.max(ratePerSec.values());
    }
    public int getMaxVisits(){
        return Collections.max(visitsPerUser.values());
    }

    public double getUsersRate(){

        long hours = Duration.between(minTime, maxTime).toHours();
        return (double) counter / hours;

    }
    public double getErrorsPerHour(){
        long hours = Duration.between(minTime,maxTime).toHours();
        return (double) errorReq/hours;
    }
    public double getVisitPerUser(){
        int unique = users.size();
        return (double) counter/unique;
    }

    public HashMap<String, Double> getOsShares() {
        HashMap<String,Double> osShares = new HashMap<>();
        int total = 0;
        for (int count : oses.values()) {
            total += count;
        }
        for (Map.Entry<String, Integer> entry : oses.entrySet()) {
            String os1 = entry.getKey();
            int count = entry.getValue();
            double shares = (double) count / total;
            osShares.put(os1, shares);
        }

        return osShares;
    }
    public HashMap<String,Double>getBrowserShares(){
        HashMap<String,Double> browserShares = new HashMap<>();
        int total = 0;
        for (int count:browsers.values()){
            total+=count;
        }
        for (Map.Entry<String,Integer>entry: browsers.entrySet()){
            String br= entry.getKey();
            int count = entry.getValue();
            double shares= (double) count/total;
            browserShares.put(br,shares);
        }
        return browserShares;
    }

    public HashSet<String> getSites() {
        return sites;
    }

    public HashSet<String> getNotSites() {
        return notSites;
    }
    public HashSet<String> getReferers() {
        return referers;
    }
}
