package ru.stqa.pft.mantis.tests;

import biz.futureware.mantis.rpc.soap.client.IssueData;
import org.openqa.selenium.remote.BrowserType;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import ru.stqa.pft.mantis.appmanager.ApplicationManager;
import ru.stqa.pft.mantis.model.Issue;
import ru.stqa.pft.mantis.model.Project;

import javax.xml.rpc.ServiceException;
import java.io.File;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Set;

public class TestBase {

    protected static final ApplicationManager app
            = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

    @BeforeSuite(alwaysRun = true)
    public void setUp() throws Exception {
        app.init();
        app.ftp().upload(new File("src/test/resources/config_inc.php"), "config_inc.php", "config_inc.php.bak");
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws Exception {
        app.ftp().restore("config_inc.php.bak", "config_inc.php");
        app.stop();
    }

    /*1) В классе TestBase, от которого наследуются все тесты, необходимо реализовать функцию boolean isIssueOpen(int issueId) ,
    которая должна через Remote API получать из баг-трекера информацию о баг-репорте с заданным идентификатором, и возвращать значение false или true в зависимости от того, помечен он как исправленный или нет.
    2) Туда же в TestBase необходимо добавить такую функцию:
    и вызывать её в начале нужного теста, чтобы он пропускался, если баг ещё не исправлен.*/


    public void skipIfNotFixed(int issueId) throws RemoteException, ServiceException, MalformedURLException {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }

    @BeforeTest
    public boolean isIssueOpen(int issueId) throws RemoteException, ServiceException, MalformedURLException {
    //получение задач по фильтру status and summary
        Set<Issue> issues = app.soap().getIssues();
        //проверка статуса
        Issue shablonIssue = new Issue().withSummary("test").withStatus_id(80);
        for (Issue issue : issues) {
            int status_id = issue.getStatus_id();
            String summary = issue.getSummary();
            if (Assert.assertEquals(issues.contains(new Issue().withStatus_id(status_id).withSummary(summary)), shablonIssue)) {
                return true;
            }
        }
    //если задача решена <option value="80">решена</option>, то true
    //если статус отличен от true, то ничего не делаем (тест не запускаем)
    }
}
