package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import ru.stqa.pft.addressbook.appmanager.ApplicationManager;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;
import ru.stqa.pft.addressbook.model.KontactData;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

@Listeners(MyTestListener.class)
public class TestBase {

    Logger logger = LoggerFactory.getLogger(TestBase.class);

    protected static final ApplicationManager app
            = new ApplicationManager(System.getProperty("browser", BrowserType.FIREFOX));

    @BeforeSuite(alwaysRun = true)
    public void setUp(ITestContext context) throws Exception {
        app.init();
        context.setAttribute("app", app);
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws Exception {
        app.stop();
    }

    @BeforeMethod
    public void logTestStart(Method m, Object[] p){
        logger.info("Start test " + m.getName() + " with parameters " + Arrays.asList(p));
    }

    @BeforeMethod(alwaysRun = true)
    public void logTestStop(Method m, Object[] p){
        logger.info("Stop test " + m.getName() + " with parameters " + Arrays.asList(p));
    }

    public void verifyGroupListInUI() {
        if (Boolean.getBoolean("VerifyUI")) {
            Groups dbGroups = app.db().groups();
            Groups uiGroups = app.group().all();
            MatcherAssert.assertThat(uiGroups, CoreMatchers.equalTo(dbGroups.stream(). //упращенное сравнение
                    map((q) -> new GroupData().withId(q.getId()).withName(q.getName())).collect(Collectors.toSet())));
        }
    }

    public void verifyContactListInUI() {
        if (Boolean.getBoolean("VerifyUI")) {
            Contacts dbContacts = app.db().contacts();
            Contacts uiContacts = app.contact().all();
            MatcherAssert.assertThat(uiContacts, CoreMatchers.equalTo(dbContacts.stream().
                    map((q) -> new KontactData().withId(q.getId()).withFirstname(q.getFirstname()).withLastname(q.getLastname()))
                    .collect(Collectors.toSet())));
        }
    }
}
