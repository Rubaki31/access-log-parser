import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int counter = 1;
        while (true) {
            String path = new Scanner(System.in).nextLine();
            File file = new File(path);
            boolean fileExist = file.exists();
            boolean isDirectory = file.isDirectory();
            if (fileExist == false || isDirectory == false) {
                if (fileExist == false) {
                    System.out.println("файл не существует");
                } else {
                    System.out.println("указанный путь является путём к папке");
                }
                continue;
            }
            counter++;
            System.out.println("Путь указан верно Это файл номер" + counter);
        }
    }
}
