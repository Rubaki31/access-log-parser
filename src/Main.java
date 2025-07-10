import alp.LogEntry;
import alp.Statistics;
import alp.UserAgent;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        int counter = 0;
        ArrayList<Integer> lineslist = new ArrayList<>();
        System.out.println("Введите адрес файла");
        while (true) {
            String path = new Scanner(System.in).nextLine();
            File file = new File(path);
            boolean fileExist = file.exists();
            boolean isDirectory = file.isDirectory();
            if (!fileExist) {
                System.out.println("указанный файл не существует");
                continue;
            }
            if (isDirectory) {
                System.out.println("указанный путь является путём к папке");
                continue;
            }
            counter++;
            System.out.println("Путь указан верно Это файл номер " + counter);
            try {
                FileReader fileReader = new FileReader(path);
                BufferedReader reader = new BufferedReader(fileReader);
                String line;
                int yandexCount = 0;
                int googleCount = 0;
                Statistics stat = new Statistics();
                while ((line = reader.readLine()) != null) {
                    int length = line.length();
                    if (length >= 1024) {
                        throw new IOException("Длина строки превышает 1024 символа: " + length);
                    }
                    lineslist.add(length);
                    String[] parts = line.split(";");
                    if (parts.length >= 2) {
                        String fragment = parts[1];
                        String[] fragment2 = fragment.split("/");
                        String userAgent = fragment2[0];
                        if (userAgent.trim().equals("YandexBot")) yandexCount++;
                        if (userAgent.trim().equals("Googlebot")) googleCount++;
                    }
                    LogEntry log = new LogEntry(line);
                    UserAgent logUA= new UserAgent(log.getUserAgent());
                    stat.addEntry(log);



                }
                System.out.println(stat.getTrafficRate());
                System.out.println("общее количество строк в файле= " + lineslist.size());
                int total = lineslist.size();
                double yaPercent = (double) yandexCount / total * 100;
                double goPercent = (double) googleCount / total * 100;
                System.out.println("yandex= " + yaPercent + "%" + " google= " + goPercent + "%");
            } catch (IOException ex) {
                throw new IOException(ex);
            }
            break;
        }
    }
}


