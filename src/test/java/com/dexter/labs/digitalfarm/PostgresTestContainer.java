package com.dexter.labs.digitalfarm;

import org.testcontainers.containers.PostgreSQLContainer;

public final class PostgresTestContainer extends PostgreSQLContainer<PostgresTestContainer> {

    private static final String IMAGE_VERSION = "postgres:11.1";

    private static PostgresTestContainer postgresTestContainer;

    private PostgresTestContainer(){
        super(IMAGE_VERSION);
    }

    public static PostgresTestContainer getInstance(){
        if(postgresTestContainer == null){
            postgresTestContainer = new PostgresTestContainer();
        }
        return postgresTestContainer;
    }

    @Override
    public void start() {
        super.start();
        System.setProperty("DB_URL", postgresTestContainer.getJdbcUrl());
        System.setProperty("DB_USERNAME", postgresTestContainer.getUsername());
        System.setProperty("DB_PASSWORD", postgresTestContainer.getPassword());
    }

    @Override
    public void stop() {
        //do nothing, JVM handles shut down
    }
}

