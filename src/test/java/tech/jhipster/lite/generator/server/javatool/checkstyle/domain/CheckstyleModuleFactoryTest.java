package tech.jhipster.lite.generator.server.javatool.checkstyle.domain;

import static tech.jhipster.lite.module.infrastructure.secondary.JHipsterModulesAssertions.assertThatModuleWithFiles;
import static tech.jhipster.lite.module.infrastructure.secondary.JHipsterModulesAssertions.pomFile;

import org.junit.jupiter.api.Test;
import tech.jhipster.lite.TestFileUtils;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.JHipsterModulesFixture;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;

@UnitTest
class CheckstyleModuleFactoryTest {

  private final CheckstyleModuleFactory factory = new CheckstyleModuleFactory();

  @Test
  void shouldBuildModule() {
    JHipsterModuleProperties properties = JHipsterModulesFixture.propertiesBuilder(TestFileUtils.tmpDirForTest()).build();

    JHipsterModule module = factory.buildModule(properties);

    assertThatModuleWithFiles(module, pomFile())
      .hasFile("pom.xml")
      .containing("<maven-checkstyle-plugin.version>")
      .containing(
        """
              <plugin>
                <artifactId>maven-checkstyle-plugin</artifactId>
                <version>${maven-checkstyle-plugin.version}</version>
                <executions>
                  <execution>
                    <id>validate</id>
                    <phase>validate</phase>
                    <goals>
                      <goal>check</goal>
                    </goals>
                  </execution>
                </executions>
                <configuration>
                  <configLocation>checkstyle.xml</configLocation>
                  <includeTestSourceDirectory>true</includeTestSourceDirectory>
                  <consoleOutput>true</consoleOutput>
                  <failsOnError>true</failsOnError>
                </configuration>
              </plugin>
        """
      )
      .and()
      .hasFile("checkstyle.xml")
      .containing("<module name=\"IllegalImport\">")
      .containing("<module name=\"Checker\">")
      .containing("<module name=\"TreeWalker\">")
      .containing("<module name=\"UnusedImports\" />");
  }
}
