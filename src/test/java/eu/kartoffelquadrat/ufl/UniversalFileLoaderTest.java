package eu.kartoffelquadrat.ufl;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * One and only unit test to verify file content access after cloning into buffer dir.
 */
public class UniversalFileLoaderTest {

  @Test
  public void loadResourceTest() throws IOException {

    // Try to create a file clone for a resource that is known to reside on the classpath
    File file = UniversalFileLoader.getInstance().getFileForResource("poem.txt");

    // Try to actually access the content of that resource, using the cloned file reference
    String poem = FileUtils.readFileToString(file, StandardCharsets.UTF_8.name());

    // Verify poem content
    Assert.assertTrue("Cloned resource file does not contain original content.",
        poem.contains("Universal File Loader"));
  }

}
