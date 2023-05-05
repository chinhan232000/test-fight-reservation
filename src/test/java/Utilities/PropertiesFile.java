package Utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

import static Common.GlobalVariables.PROJECT_PATH;


public class PropertiesFile {
    private static Properties properties;
    private static FileInputStream fileIn;
    private static FileOutputStream fileOut;

    public static String projectPath = PROJECT_PATH + "/";
    private static String propertiesFilePathRoot = "src/test/java/configs.properties";

    static {
        properties = new Properties();
        try {
            fileIn = new FileInputStream(projectPath + propertiesFilePathRoot);
            properties.load(fileIn);
        } catch (Exception exp) {
            System.out.println(exp.getMessage());
            System.out.println(exp.getCause());
            exp.printStackTrace();
        }
    }

    public static String getPropValue(String KeyProp) {
        String value = null;
        try {
            value = properties.getProperty(KeyProp);
            System.out.println(value);
        } catch (Exception exp) {
            System.out.println(exp.getMessage());
            System.out.println(exp.getCause());
            exp.printStackTrace();
        }
        return value;
    }

    public static void setPropValue(String KeyProp, String Value) {
        try {
            fileOut = new FileOutputStream(projectPath + propertiesFilePathRoot);
            properties.setProperty(KeyProp, Value);
            properties.store(fileOut, "Set new value in properties file");
            System.out.println("Set new value in file properties success.");
        } catch (Exception exp) {
            System.out.println(exp.getMessage());
            System.out.println(exp.getCause());
            exp.printStackTrace();
        }
    }
}
