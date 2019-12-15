package ru.stqa.pft.rest;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.testng.SkipException;

import java.io.IOException;
import java.util.Set;

public class TestBase {
    private String issueStatus;

    private Executor getExecutor() {
        return Executor.newInstance().auth("deb9007ed0fa03a797fcb8ff57b0d606", "");
    }

    public void skipIfNotFixed(int issueId) throws IOException {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }

    public boolean isIssueOpen(int issueId) throws IOException {
        String status = getIssue(issueId);
        if (status.equals("Resolved")) {
            return false;
        }
        return true;
    }

    public String getIssue(int issueId) throws IOException {
        String json = getExecutor().execute(Request.Get("http://demo.bugify.com/api/issues/" + issueId + ".json")).returnContent().asString();
        JsonElement parsed = new JsonParser().parse(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");
        Set<Issue> issue = new Gson().fromJson(issues, new TypeToken<Set<Issue>>(){}.getType());
        issueStatus = issue.iterator().next().getStatus();
        return issueStatus;
    }
}
