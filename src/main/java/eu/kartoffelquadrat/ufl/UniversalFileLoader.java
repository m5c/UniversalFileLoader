package eu.kartoffelquadrat.ufl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

/**
 * This library provides file-system access for classpath resources, using the OS provided buffer
 * directory. Resulting access is reliable and independent of program packaging and runtime
 * environment.
 *
 * @author Maximilian Schiedermeier
 */
public class UniversalFileLoader {

  // Target location is a sub-folder in temp-dir, named "resource-buffer"
  private static final String BUFFER_NAME = "resource-buffer";
  private static final String BUFFER_DIR =
      System.getProperty("java.io.tmpdir") + System.getProperty("file.separator") + BUFFER_NAME;

  // private reference for singleton pattern.
  private static UniversalFileLoader singletonReference = null;

  /**
   * Private constructor for singleton access. Ensures buffer exists and is empty.
   */
  private UniversalFileLoader() {

    // Wipe all traces of previous caches and ensure buffer exists.
    File bufferDirFile = new File(BUFFER_DIR);
    if (bufferDirFile.exists()) {
      deleteDir(bufferDirFile);
    }
    bufferDirFile.mkdirs();
  }

  /**
   * Singleton access method.
   *
   * @return UniversalFileLoader instance that can be used for resource access.
   */
  public static UniversalFileLoader getInstance() {
    if (singletonReference == null) {
      singletonReference = new UniversalFileLoader();
    }
    return singletonReference;
  }

  /**
   * Helper method to recursively remove a directory with all contents. Source: Stackoverflow:
   * https://stackoverflow.com/a/29175213
   *
   * @param file as the location of the directory to be deleted with all contents.
   */
  public static void deleteDir(File file) {
    File[] contents = file.listFiles();
    if (contents != null) {
      for (File f : contents) {
        if (!Files.isSymbolicLink(f.toPath())) {
          deleteDir(f);
        }
      }
    }
    file.delete();
  }

  /**
   * Clones a resource on the project classpath to the OS temp directory and generates a java file
   * object pointing to the copied file on disk.
   *
   * @param resourceName of a resource indexed by the project classpath.
   * @return File location of the cloned resource in the temp directory.
   */
  public File getFileForResource(String resourceName) {
    File targetFile = buildTargetFile(resourceName);

    // Skip file clone if file already exists, use state of the art java resource
    // handling to clone any resource on classpath to the OS temp dir otherwise
    if (!targetFile.exists()) {
      cloneResource(resourceName, targetFile);
    }

    // At this point the file either already existed or has been cloned, so the file
    // location is valid and can be safely used by *bleep* libraries.
    return targetFile;
  }

  /**
   * Helper method to build the corresponding java file (location only, no copying) for a given
   * resource. Also runs a sanity check on the provided resource string.
   *
   * @param resourceName as the relative name and location of the resource in the project sources.
   * @return File location pointing to a location in the temp / buffer dir.
   */
  private File buildTargetFile(String resourceName) {
    if (resourceName.contains("/")) {
      throw new RuntimeException(
          "Target resource contains a path separator. Cowardly refusing treatment of the provided"
              + " resource: " + resourceName);
    }
    return new File(BUFFER_DIR + System.getProperty("file.separator") + resourceName);
  }

  /**
   * Helper method to create an physical file on disk, filled with identical content as a given
   * resource on the classpath. Uses only reliable java resource handling for this task, so it will
   * work no matter how this program was built or started.
   *
   * @param resourceName as the name of the resource whose content we want to clone.
   * @param outputFile   as the java file location of the target file in the temp buffer directory
   * @throws IOException in case the temp directory cannot be accessed.
   */
  private void cloneResource(String resourceName, File outputFile) {

    // See: https://mkyong.com/java/java-read-a-file-from-resources-folder/
    // Load content of resource (on classpath) using input stream reader
    // The class loader that loaded the class
    ClassLoader classLoader = getClass().getClassLoader();
    InputStream resourceContentStream = classLoader.getResourceAsStream(resourceName);

    // Verify the specified resource actually exists
    if (resourceContentStream == null) {
      throw new IllegalArgumentException("Resource not found! " + resourceName);
    }

    try {
      // Write the resource content back to an actual file in the buffer directory
      // See: https://www.baeldung.com/convert-input-stream-to-a-file
      java.nio.file.Files.copy(resourceContentStream, outputFile.toPath());

      // Tidy up streams\
      resourceContentStream.close();
    } catch (IOException e) {
      throw new RuntimeException("Cloning of resource to file buffer failed: " + e);
    }

  }
}
