package listenerutility;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import basetest.BaseClass;

public class ListenerImplementation implements ITestListener, ISuiteListener {

	public ExtentReports report;
	public ExtentSparkReporter spark;
	public ExtentTest test;

	@Override
	public void onStart(ISuite suite) {
		Reporter.log("Report Configuration", true);
		Date d = new Date();
		String newDate = d.toString().replace(" ", "_").replace(":", "_");

		spark = new ExtentSparkReporter("./AdvanceReport/report_" + newDate + ".html");
		spark.config().setDocumentTitle("NinzaCRM Test Suite Results");
		spark.config().setReportName("CRM report");
		spark.config().setTheme(Theme.DARK);

		report = new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("OS", "Windows 11");
		report.setSystemInfo("Browser", "Edge");

	}

	@Override
	public void onFinish(ISuite suite) {
		report.flush();
		Reporter.log("Report backup", true);

	}

	@Override
	public void onTestStart(ITestResult result) {
		
		test=report.createTest("result.getMethod().getMethodName() ");
		test.log(Status.INFO,"====" + result.getMethod().getMethodName() + "Execution Started====");
		//Reporter.log("====" + result.getMethod().getMethodName() + "Execution Started====", true);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		test.log(Status.PASS, "====" + result.getMethod().getMethodName() + "SUCCESS====");
		//Reporter.log("====" + result.getMethod().getMethodName() + "SUCCESS====", true);

	}

	@Override
	public void onTestFailure(ITestResult result) {

		String testName = result.getMethod().getMethodName();
		//Reporter.log("====" + testName + "Failure====", true);
		Date d = new Date();
		String newDate = d.toString().replace(" ", "_").replace(":", "_");

		TakesScreenshot ts = (TakesScreenshot) BaseClass.sdriver;
		 String temp = ts.getScreenshotAs(OutputType.BASE64);
		test.addScreenCaptureFromBase64String(temp,testName+newDate);
		
		test.log(Status.FAIL, "====" + testName + "Failure====");

	}

	@Override
	public void onTestSkipped(ITestResult result) {
		test.log(Status.SKIP, "====" + result.getMethod().getMethodName() + "SKIPPED====");
		//Reporter.log("====" + result.getMethod().getMethodName() + "SKIPPED====", true);
 
	}

}
