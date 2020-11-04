package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.ActiveDbProfileResolver;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
@ActiveProfiles(resolver = ActiveDbProfileResolver.class)
public abstract class AbstractMealServiceTest {

    @Test
    public abstract void delete();

    @Test
    public abstract void deleteNotFound();

    @Test
    public abstract void deleteNotOwn();

    @Test
    public abstract void create();

    @Test
    public abstract void duplicateDateTimeCreate();

    @Test
    public abstract void get();

    @Test
    public abstract void getNotFound();

    @Test
    public abstract void getNotOwn();

    @Test
    public abstract void update();

    @Test
    public abstract void updateNotOwn();

    @Test
    public abstract void getAll();

    @Test
    public abstract void getBetweenInclusive();

    @Test
    public abstract void getBetweenWithNullDates();
}