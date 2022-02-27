package com.tcontainer.testcontainers;

import org.testcontainers.containers.MySQLContainer;

public class AbstractContainerBaseTest {
    static final MySQLContainer MY_SQL_CONTAINER;

    static {
        MY_SQL_CONTAINER = new MySQLContainer("mysql:8.0.28-oracle");
        MY_SQL_CONTAINER.start();
    }

}
