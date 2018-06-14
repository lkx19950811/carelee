import java.io.File;
import java.io.IOException;

/**
 * 描述:
 *
 * @author Leo
 * @create 2018-04-16 下午 9:50
 */
public class FileTest {
    public static void createFile(){

// fileName表示你创建的文件名；为txt类型；
        String fileName="test.txt";
        File file = new File(fileName);
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void readFile(){//将test.txt移入D:/下
        String fileName = "D:/test.txt";
        File file = new File(fileName);
        System.out.println( file.getPath());
    }

    public static void main(String[] args) {
        createFile();
        readFile();
    }

}
