import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int counter = 0;
        System.out.println("Введите адрес файла");
        while (true) {
            String path = new Scanner(System.in).nextLine();
            File file = new File(path);
            boolean fileExist = file.exists();
            boolean isDirectory = file.isDirectory();
            if (!fileExist){
                System.out.println("указанный файл не существует");
                continue;
            }
            if(isDirectory){
                System.out.println("указанный путь является путём к папке");
                continue;
            }
            counter++;

            System.out.println("Путь указан верно Это файл номер " + counter);
        }
    }
}
