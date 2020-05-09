import java.io.*;
import java.util.regex.*;
import java.nio.file.Paths;
import java.util.*;
import java.util.Scanner;

/*
 * ReadOui
 */
public class ReadOui {

    public static void main(String[] args) {
        InnerReadOui oui = new InnerReadOui("./oui.txt");
        System.out.println(oui.getOuiData());
    }
}

/**
 * InnerReadOui
 */
class InnerReadOui {
    private String ouiFilePath;
    private Map<String, String> ouiMap = new HashMap<String, String>();

    public InnerReadOui(String ouiFilePath) {
        this.ouiFilePath = ouiFilePath;
    }

    private void ProcessOuiFile() throws IOException {
        File f = new File(this.ouiFilePath);
        if (f.exists() && f.isFile()) {
            System.out.println("正在上传数据");
            Scanner ouiFile = new Scanner(Paths.get(f.getAbsolutePath()), "UTF-8");
            for (int i = 0; ouiFile.hasNextLine(); i++) {
                Pattern p = Pattern.compile("(.*)\\(hex\\)(.*)");
                Matcher m = p.matcher(ouiFile.nextLine());

                if (m.matches()) {
                    String key = m.group(1).substring(0, 8);
                    String inc = m.group(2).substring(2);
                    ouiMap.put(key, inc);
                }
            }
            System.out.println("数据上传完成");
        }
    }

    public Map<String, String> getOuiData() {
        try {
            ProcessOuiFile();
        } catch (IOException e) {
            e.getMessage();
        }
        return this.ouiMap;
    }
}