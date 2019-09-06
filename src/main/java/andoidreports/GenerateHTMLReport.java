package andoidreports;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import andoidreports.pojo.HTMLReportContants;
import andoidreports.pojo.ResultClass;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GenerateHTMLReport {

    private static boolean LOGIN_FIRST_TIME = true;
    private static ChromeDriver WD_SINGLE_INSTANCE = null;
    private static List<HashMap<String,String>> resultList = new ArrayList<>();
    private static  List<ResultClass> RESULT_LIST = new ArrayList<>();
    private static File folder = new File("Execution_Report_"+new Date());
    private static List<HashMap <String,String>> overallResult = new ArrayList<>();
    private static int pass = 0 ,fail = 0, error = 0 ;


    public static ChromeDriver geWebDrivertInstance() {
        if (WD_SINGLE_INSTANCE == null) {
            synchronized (GenerateHTMLReport.class) {
                if (WD_SINGLE_INSTANCE == null) {
                    GenerateHTMLReport.setupChromeProperties();
                    WD_SINGLE_INSTANCE = new ChromeDriver();
                }
            }
        }
        return WD_SINGLE_INSTANCE;
    }

    public static void setupChromeProperties()
    {
        System.setProperty("webdriver.chrome.driver",HTMLReportContants.CHROME_DRIVER_PATH);
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless");
        chromeOptions.addArguments("--disable-gpu");
        chromeOptions.addArguments("window-size=1980,1080");
    }


    public  static void getResultUsingSel(String testCase) {
    String url = String.format(HTMLReportContants.TEST_ANALYZER,testCase);
    WebDriver driver = GenerateHTMLReport.geWebDrivertInstance();
    HashMap<String, String> resultMap;
    String ReportLink = "";
    try {
            driver.get(url);
            if (LOGIN_FIRST_TIME == true) {
                LOGIN_FIRST_TIME = oneLoginHandler(driver);
            }

            ResultClass[] getResult = getResultList(driver);
            resultMap = getPassFailCount(getResult);
            ReportLink = generateHtmlReport(getResult, resultMap);
            resultMap.put("REPORT_LINK", ReportLink);
            resultList.add(resultMap);
            overallResult.add(resultMap);
            System.out.println(String.format("Test Case Name : %s PASS : %s FAIL : %s TOTAL : %s",resultMap.get("TESTCASENAME"),resultMap.get("PASS"),resultMap.get("FAIL"),resultMap.get("TOTAL")));

    }
    catch (Exception ex)
    {
        ex.printStackTrace();
    }

    }

    public static HashMap <String,String>   getPassFailCount(ResultClass[] resultClass)
    {
        HashMap<String,String> resultMap= new HashMap<>();
        int passCount=0,failCount=0,errorCount =0;
        for(ResultClass result: resultClass)
        {
            RESULT_LIST.add(result);

            resultMap.put("TESTCASENAME",String.format("%s",result.getTestCaseName().substring(result.getTestCaseName().indexOf("/")+1)));
            if (result.getStatus().equalsIgnoreCase("pass"))
                passCount+=1;
            else
            if (result.getStatus().equalsIgnoreCase("fail"))
                failCount+=1;
            if (result.getStatus().equalsIgnoreCase("error"))
                errorCount+=1;
        }
        resultMap.put("TOTAL",String.format("%s",passCount+failCount));
        resultMap.put("PASS",String.format("%s",passCount));
        resultMap.put("FAIL",String.format("%s",failCount));
        resultMap.put("ERROR",String.format("%s",errorCount));
        return resultMap;
    }

    public static ResultClass[]  getResultList(WebDriver driver)
    {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        WebDriverWait wait = new WebDriverWait(driver, 60);
        driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
         wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(),'testCaseName')]")));
        String source = driver.getPageSource();
        source=source.substring(source.indexOf("["),source.lastIndexOf("]") + 1);
        ResultClass[] resultArray  = gson.fromJson(source,ResultClass[].class);
        return resultArray;

    }


    public static boolean oneLoginHandler(WebDriver driver)
    {
        String username = System.getProperty("username");
        String password = System.getProperty("password");
        boolean isAutoPush = Boolean.parseBoolean(System.getProperty("isAutoPush"));
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("user_email")))).sendKeys(username);
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("user_password")))).sendKeys(password);
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("user_submit")))).click();
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        driver.switchTo().defaultContent();
        driver.switchTo().frame(driver.findElement(By.id("duo_iframe")));
        if(!isAutoPush)
            wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//button[contains(text(),'Send Me a Push')]")))).click();

        System.out.println("Onlogin - Kindly Check Push Notification you your Duo app and approve  !!!! ");

        return false;
    }

    /* readFile - Method to read list of test cases from txt file and return in List collection
       Parameters : fileName - file name with absolute path
    */
    public static List<String> readFile(String fileName)
    {
        List<String> testList = new ArrayList<>();
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            // convert it into a List
            testList = stream.sorted()
                    .collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return testList;
    }

    public static String generateHtmlReport(ResultClass[] resultClass,HashMap <String,String> resultMap) throws Exception
    {
        String fileName = folder.getName()+"/"+resultMap.get("TESTCASENAME")+".html";
        File file = new File(fileName);
        FileWriter fw=new FileWriter(fileName);
        String html ="";
        String head = "";
        String script = "";
        String body = "";
        String resultStr = "";
        String data_row = "";
        String table = "";
        String th_row = "";
        String div = "";
        String href = "";
        script += String.format(HTMLReportContants.SCRIPT,resultMap.get("PASS"),resultMap.get("FAIL"),resultMap.get("ERROR"),"TEST RUN DETAILS");
        th_row += String.format(HTMLReportContants.TR, HTMLReportContants.TH_TESTNAME+ HTMLReportContants.TH_STATUS+ HTMLReportContants.TH_BUILD+ HTMLReportContants.TH_TESTNAME);

        for(ResultClass result: resultClass)
        {
            String columnTestName = String.format(HTMLReportContants.TD_TESTNAME,resultMap.get("TESTCASENAME"));
            String columnStatus = String.format(HTMLReportContants.TD_STATUS,result.getStatus());
            href = String.format(HTMLReportContants.HREF,result.getBuildUrl(),result.getBuildUrl());
            String columnBuild = String.format(HTMLReportContants.TD_BUILD,href);
            String columnDate = String.format(HTMLReportContants.TD_DATE,result.getReportedAtStr());
            data_row += String.format(HTMLReportContants.TR,columnTestName+columnStatus+columnBuild+columnDate);
        }


        table +=String.format(HTMLReportContants.TABLE,th_row+data_row);
        head += String.format(HTMLReportContants.HEAD,script+ HTMLReportContants.STYLE);
        div += String.format(HTMLReportContants.DIV,table);

        body += String.format(HTMLReportContants.BODY, HTMLReportContants.PIECHART_DIV+div);
        html += String.format(HTMLReportContants.HTML,head+body);

        fw.write(html);
        fw.close();

        return String.format("%s%s","./",file.getName());

    }

    public static void generateSummaryReport() throws Exception
    {

        FileWriter fw=new FileWriter(folder.getName()+"/Summary.html");
        String html ="";
        String head = "";
        String script = "";
        String body = "";
        String data_row = "";
        String table = "";
        String th_row = "";
        String div = "";
        String href = "";

        for (HashMap<String,String> resultClass:overallResult) {

            href = String.format(HTMLReportContants.HREF, resultClass.get("REPORT_LINK"),resultClass.get("TESTCASENAME"));
            String columnTestName = String.format(HTMLReportContants.TD_TESTNAME,href);
            String columnStatus = String.format(HTMLReportContants.TD_STATUS, resultClass.get("PASS")) + String.format(HTMLReportContants.TD_STATUS, resultClass.get("FAIL")) + String.format(HTMLReportContants.TD_STATUS, resultClass.get("ERROR"));
            data_row += String.format(HTMLReportContants.TR,columnTestName + columnStatus);
            pass += Integer.parseInt(resultClass.get("PASS"));
            fail += Integer.parseInt(resultClass.get("FAIL"));
            error += Integer.parseInt(resultClass.get("ERROR"));
        }


        script += String.format(HTMLReportContants.SCRIPT, pass, fail,error,"OVERALL EXECUTION SUMMARY");
        th_row += String.format(HTMLReportContants.TR, HTMLReportContants.TH_TESTNAME + HTMLReportContants.TH_PASS_COUNT + HTMLReportContants.TH_FAIL_COUNT + HTMLReportContants.TH_ERROR_COUNT);
        table += String.format(HTMLReportContants.TABLE, th_row + data_row);
        head += String.format(HTMLReportContants.HEAD, script + HTMLReportContants.STYLE);
        div += String.format(HTMLReportContants.DIV, table);
        body += String.format(HTMLReportContants.BODY, HTMLReportContants.PIECHART_DIV + div);
        html += String.format(HTMLReportContants.HTML, head + body);

        fw.write(html);
        fw.close();
    }


    public static void main(String arg[])
    {
        try {
            String fileName = HTMLReportContants.TEST_CASE_LIST;
            WebDriver driver = GenerateHTMLReport.geWebDrivertInstance();
            folder.mkdir();
            if(System.getProperty("username") == null)
            {
                System.setProperty("username", arg[0]);
                System.setProperty("password", arg[1]);
                System.setProperty("isAutoPush", arg[2]);
            }

            GenerateHTMLReport.readFile(fileName).forEach(GenerateHTMLReport::getResultUsingSel);
            GenerateHTMLReport.generateSummaryReport();
            driver.close();
            driver.quit();
            System.exit(0);
        }catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
