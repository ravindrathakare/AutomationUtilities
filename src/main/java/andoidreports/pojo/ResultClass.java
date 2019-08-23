package andoidreports.pojo;
public class ResultClass
{
    private String id;

    private String testCaseName;

    private String status;

    private double runtime;

    private String sha1;

    private int reportedAt;

    private String jenkinsMaster;

    private String buildId;

    private String job;

    private String diffId;

    private String buildUrl;

    private String diffUrl;

    private String message;

    private String stacktrace;

    private String source;

    private String suite;

    private int statusAsInt;

    private String reportedAtStr;

    public void setId(String id){
        this.id = id;
    }
    public String getId(){
        return this.id;
    }
    public void setTestCaseName(String testCaseName){
        this.testCaseName = testCaseName;
    }
    public String getTestCaseName(){
        return this.testCaseName;
    }
    public void setStatus(String status){
        this.status = status;
    }
    public String getStatus(){
        return this.status;
    }
    public void setRuntime(double runtime){
        this.runtime = runtime;
    }
    public double getRuntime(){
        return this.runtime;
    }
    public void setSha1(String sha1){
        this.sha1 = sha1;
    }
    public String getSha1(){
        return this.sha1;
    }
    public void setReportedAt(int reportedAt){
        this.reportedAt = reportedAt;
    }
    public int getReportedAt(){
        return this.reportedAt;
    }
    public void setJenkinsMaster(String jenkinsMaster){
        this.jenkinsMaster = jenkinsMaster;
    }
    public String getJenkinsMaster(){
        return this.jenkinsMaster;
    }
    public void setBuildId(String buildId){
        this.buildId = buildId;
    }
    public String getBuildId(){
        return this.buildId;
    }
    public void setJob(String job){
        this.job = job;
    }
    public String getJob(){
        return this.job;
    }
    public void setDiffId(String diffId){
        this.diffId = diffId;
    }
    public String getDiffId(){
        return this.diffId;
    }
    public void setBuildUrl(String buildUrl){
        this.buildUrl = buildUrl;
    }
    public String getBuildUrl(){
        return this.buildUrl;
    }
    public void setDiffUrl(String diffUrl){
        this.diffUrl = diffUrl;
    }
    public String getDiffUrl(){
        return this.diffUrl;
    }
    public void setMessage(String message){
        this.message = message;
    }
    public String getMessage(){
        return this.message;
    }
    public void setStacktrace(String stacktrace){
        this.stacktrace = stacktrace;
    }
    public String getStacktrace(){
        return this.stacktrace;
    }
    public void setSource(String source){
        this.source = source;
    }
    public String getSource(){
        return this.source;
    }
    public void setSuite(String suite){
        this.suite = suite;
    }
    public String getSuite(){
        return this.suite;
    }
    public void setStatusAsInt(int statusAsInt){
        this.statusAsInt = statusAsInt;
    }
    public int getStatusAsInt(){
        return this.statusAsInt;
    }
    public void setReportedAtStr(String reportedAtStr){
        this.reportedAtStr = reportedAtStr;
    }
    public String getReportedAtStr(){
        return this.reportedAtStr;
    }
}
