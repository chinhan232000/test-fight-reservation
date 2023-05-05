package Common;

import Utilities.DataFaker;
import Utilities.PropertiesFile;

import java.time.Duration;

public class GlobalVariables {
    public static String PROJECT_PATH = System.getProperty("user.dir");
    public static String OUTPUT_PATH = PROJECT_PATH + "/resources/output/";
    public static String JSON_FILE_PATH = PROJECT_PATH + "/src/test/java/";

    //data test
    public static String BROWSER = PropertiesFile.getPropValue("browser");
    public static String autoGeneratePID = DataFaker.generateRandomString(10);

    //report data
    public static int TOTAL_TESTCASES = 0;
    public static int TOTAL_EXECUTED = 0;
    public static int TOTAL_PASSED = 0;
    public static int TOTAL_FAILED = 0;
    public static int TOTAL_SKIPPED = 0;
    public static final Duration WAIT_TIME_60 = Duration.ofSeconds(60);
    
    //URL path
    public static final String PAGE_URL = "http://localhost:3000/";


}
