package hck.testgooglecloudmessage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by hck on 4/27/16.
 */
public class Utility {
    private static Pattern pattern;
    private static Matcher matcher;
    //Email Pattern
    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-+]+(.[_A-Za-z0-9-]+)*@"+"[A-Za-z0-9-]+(.[A-Za-z0-9]+)*(.[A-Za-z]{2,})$";

    /**
     * Validate Email with regular expression
     *
     * @param email
     * @return true for Valid Email and false for Invalid Email
     */
    public static boolean validate(String email) {
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static LinkedList<String> readFile(File path, String fileName){
        LinkedList<String> data = new LinkedList<String>();
        try {
            File file = new File(path, fileName);

            FileInputStream fis = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
            BufferedReader br = new BufferedReader(isr);
            String readLine;
            while ((readLine = br.readLine()) != null) {
                readLine = readLine.replaceAll("\\r\\n", "\n");
                data.add(readLine);
            }
            br.close();
            isr.close();
            fis.close();

        } catch (IOException e) {
            return data;
        }
        return data;
    }

    public static LinkedList<OptionListItem> readFileToItems(File path, String fileName){
        LinkedList<OptionListItem> items = new LinkedList<>();
        LinkedList<String> data = readFile(path, fileName);
        int c = 0;
        OptionListItem item = new OptionListItem();
        for (String line:data){
            if (c++ == 0){
                item = new OptionListItem();
                item.setOption(line);
            }else{
                Date date = new Date();
                date.setTime(Long.parseLong(line));
                item.setIntroduction(FixData.DefaultLongDateFormat.format(date));
                item.setMap();
                items.add(item);
                c = 0;
            }
        }
        return  items;
    }

    public static String[] readFileToArray(File path, String fileName){
        LinkedList<String> data = readFile(path, fileName);
        return data.toArray(new String[]{});
    }

    public static boolean appendFile(File path, String fileName, String content){
        LinkedList<String> data = readFile(path, fileName);
        data.addFirst(content);
        return writeFile(path, fileName, data);
    }

    public static boolean writeFile(File path, String fileName, List<String> contents){
        try {
            File file = new File(path, fileName);

            if (!file.exists()) {
                file.createNewFile();
            }

            FileOutputStream fos = new FileOutputStream(file);
            OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
            BufferedWriter bw = new BufferedWriter(osw);

            for (String line:contents){
                line = line.replaceAll("\\n", "\r\n");
                bw.write(line);
                bw.newLine();
            }

            bw.close();
            osw.close();
            fos.close();
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    public static boolean clearData(File path, String fileName){
        File file = new File(path, fileName);
        return file.delete();
    }
}