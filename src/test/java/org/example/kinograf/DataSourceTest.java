package org.example.kinograf;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import javax.sql.DataSource;
@DataJpaTest
public class DataSourceTest {
    @Autowired
    DataSource dataSource;

    @Test
    void testDataSource() throws Exception {
        String dbName = dataSource.getConnection().getMetaData().getDatabaseProductName();
        System.out.println("Database Product Name: " + dbName);
    }
}