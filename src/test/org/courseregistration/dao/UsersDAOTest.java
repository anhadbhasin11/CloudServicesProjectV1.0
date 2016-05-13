package org.courseregistration.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

@RunWith(BlockJUnit4ClassRunner.class)
public class UsersDAOTest extends BaseTest {


    // Checking the Isolation Level (READ COMMITTED)
    @Test
    public void check_isolation_level() {
        entityManager.getTransaction().begin();
        entityManager.createNativeQuery("insert into roles(role_id, name) values (3833, 'student')").executeUpdate();
        entityManager.getTransaction().commit();

        entityManager.getTransaction().begin();
        entityManager.createNativeQuery("insert into roles(role_id, name) values (7777, 'professor')").executeUpdate();

    }

}
