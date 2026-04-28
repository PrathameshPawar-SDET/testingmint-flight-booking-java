package com.testingmint.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager {
    private static ExtentReports extent;

    public static ExtentReports getInstance() {
        if (extent == null) {
            String reportDir = System.getProperty("user.dir") + "/reports";
            java.io.File dir = new java.io.File(reportDir);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            String reportPath = reportDir + "/ExtentReport.html";
            ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
            spark.config().setReportName("Flight Booking Automation Results");
            spark.config().setDocumentTitle("Test Execution Report");

            extent = new ExtentReports();
            extent.attachReporter(spark);
            extent.setSystemInfo("Host Name", "Localhost");
            extent.setSystemInfo("Environment", "QA");
            extent.setSystemInfo("User Name", "Test Engineer");
        }
        return extent;
    }
}
