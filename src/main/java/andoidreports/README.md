# How to Use Report Utility 
1. Make Sure List of All Tests present in TestCases.txt file for i. e.


                "Test Class Full Reference"/"Test Case Method Name"
                com.ubercab.presidio.feature.midtrip_reprice_ui.MidtripRepriceLocationInsideOutsideThresholdTest/midtriprepricing_whenChangeDestinationOutsideThreshold_shouldShowSurge

2. Copy chromedriver in desired path and update chromedriver path and TestCases.txt path in HTMLReportContants.java (under package androidtesthtmlreports.pojo)
        for i. e.  public static String CHROME_DRIVER_PATH = "/Users/username/Uber/chromedriver";
3. Using any of the below method html report can be generated :
       
       a. Using Main class runner : - 
                Update run configuration 1st Arg = uber email , 2nd Arg = password , 3rd Arg = true or false (If auto push option on onlogin set to true else false)
        For i. e. xxx@xxx.xxx.com xxxxxx false
        
       b. Maven goal 
               mvn exec:java -Dexec.mainClass="androidtesthtmlreports.GenerateHTMLReport" -Dexec.args="xxx@xxx.xxx.com xxxxxx false"  
                OR
              mvn exec:java -Dexec.mainClass="androidtesthtmlreports.GenerateHTMLReport" -Dusername="xxx@xxx.xxx.com" -Dpassword="xxxxxx" -DisAutoPush="true/false"
