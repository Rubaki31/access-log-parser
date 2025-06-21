import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
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
                while ((line = reader.readLine()) != null) {
                    int length = line.length();
                    if (length >= 1024) {
                        throw new IOException("Длина строки превышает 1024 символа: " + length);
                    }
                    lineslist.add(length);
                }
                System.out.println("общее количество строк в файле= " + lineslist.size());
                System.out.println("длинa самой длинной строки в файле= " + Collections.max(lineslist));
                System.out.println("длинa самой короткой строки в файле= " + Collections.min(lineslist));
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

