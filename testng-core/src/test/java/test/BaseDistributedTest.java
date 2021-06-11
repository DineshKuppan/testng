package test;

import java.util.List;
import java.util.Map;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.collections.Maps;

public class BaseDistributedTest {
  private boolean m_verbose = false;

  protected void verifyTests(String title, String[] exp, Map<String, List<ITestResult>> found) {
    Map<String, String> expected = Maps.newHashMap();
    for (String element : exp) {
      expected.put(element, element);
    }

    Assert.assertEquals(
        found.size(), expected.size(), "Verification for " + title + " tests failed:");

    for (Object o : expected.values()) {
      String name = (String) o;
      if (null == found.get(name)) {
        dumpMap("Expected", expected);
        dumpMap("Found", found);
      }

      Assert.assertNotNull(
          found.get(name),
          "Expected to find method " + name + " in " + title + " but didn't find it.");
    }
  }

  protected void dumpMap(String title, Map<?, ?> m) {
    if (m_verbose) {
      System.out.println("==== " + title);
      for (Map.Entry<?, ?> entry : m.entrySet()) {
        ppp(entry.getKey() + "  => " + entry.getValue());
      }
    }
  }

  private void ppp(String s) {
    if (m_verbose) {
      System.out.println("[BaseDistributedTest] " + s);
    }
  }
}