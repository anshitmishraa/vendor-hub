package com.project.VendorHub.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseConfig {
    @Value("${MYSQL_HOST:localhost}")
    private String mysqlHost;

    @Value("${MYSQL_PORT:3306}")
    private String mysqlPort;

    @Value("${MYSQL_DATABASE:vendorHub}")
    private String mysqlDatabase;

    @Value("${MYSQL_USERNAME:root}")
    private String mysqlUsername;

    @Value("${MYSQL_PASSWORD:root}")
    private String mysqlPassword;

    // Define getters for the properties if needed
    // ...

    // Define the DataSource bean
    // ...
}
