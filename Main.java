import java.io.*;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {
    String[] ouiArr = {};

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in); // 初始化scanner
        System.out.println("input file path");// 提示输入oui文件路径
        String filePath = in.nextLine();// 读取用户输入
        File f = new File(filePath); // 转化为file对象方便后续提取绝对路径
        
        if (f.exists() && f.isFile()) {// 判断文件是否存在，并且必须是文件
            Scanner file = new Scanner(Paths.get(f.getAbsolutePath()), "UTF-8");// 读取文件
            while (file.hasNextLine()) {// 如果有下一行 就一直读取
                System.out.println(file.nextLine());// 打印文件
            }

        } else {
            return;
        }
    }

}