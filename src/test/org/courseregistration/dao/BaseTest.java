package org.courseregistration.dao;


import org.courseregistration.dbtests.HibernateUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import javax.persistence.EntityManager;

@RunWith(BlockJUnit4ClassRunner.class)
public class BaseTest {

    protected static EntityManager entityManager;

    @BeforeClass
    public static void setUp() throws Exception {
        entityManager = HibernateUtils.getEntityManager();
    }

    @AfterClass
    public static void tearDown() throws Exception {
        HibernateUtils.closeEntityManager();
    }
}
