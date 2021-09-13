package com.app.ecommerce.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.IOException;

@Configuration
public class DebeziumConnectorConfig {

    /**
     * Database details.
     */
    @Value("${secondary.datasource.host}")
    private String secondaryDbHost;

    @Value("${secondary.datasource.database}")
    private String secondaryDbName;

    @Value("${secondary.datasource.port}")
    private String secondaryDbPort;

    @Value("${secondary.datasource.username}")
    private String secondaryDbUsername;

    @Value("${secondary.datasource.password}")
    private String secondaryDbPassword;

    /**
     * secondary Database Connector Configuration
     */
    @Bean
    public io.debezium.config.Configuration secondaryConnector() throws IOException {
        File offsetStorageTempFile = File.createTempFile("offsets_", ".dat");
        File dbHistoryTempFile = File.createTempFile("dbhistory_", ".dat");
        return io.debezium.config.Configuration.create()
                .with("name", "secondary-mysql-connector")
                .with("connector.class", "io.debezium.connector.mysql.MySqlConnector")
                .with("offset.storage", "org.apache.kafka.connect.storage.FileOffsetBackingStore")
                .with("offset.storage.file.filename", offsetStorageTempFile.getAbsolutePath())
                .with("offset.flush.interval.ms", "60000")
                .with("database.hostname", secondaryDbHost)
                .with("database.port", secondaryDbPort)
                .with("database.user", secondaryDbUsername)
                .with("database.password", secondaryDbPassword)
                .with("database.dbname", secondaryDbName)
                .with("database.include.list", secondaryDbName)
                .with("include.schema.changes", "false")
                .with("database.allowPublicKeyRetrieval", "true")
                .with("database.server.id", "10181")
                .with("database.server.name", "secondary-mysql-db-server")
                .with("database.history", "io.debezium.relational.history.FileDatabaseHistory")
                .with("database.history.file.filename", dbHistoryTempFile.getAbsolutePath())
                .build();
    }
}
