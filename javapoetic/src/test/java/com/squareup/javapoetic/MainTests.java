package com.squareup.javapoetic;

import com.squareup.javapoet.*;

import java.util.Date;
import javax.lang.model.element.Modifier;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.IOException;

//import static com.google.common.truth.Truth.assertThat;

@RunWith(JUnit4.class)
public final class MainTests {
  @Test
  public void testMain() {
      JavaFile javaFile = PoeticFile.Of.packages("com.example.helloworld").of(
              PoeticClass.Of.publics().classes("HelloWorld").method(
                  PoeticMethod.Of.publics().statics().voids().name("main").parameter(String.class, "args").statement(
                      PoeticStatement.of("$T.out.println($S)", System.class, "Hello, JavaPoet!")
                      )
                  )
              );

      //javaFile.emit(System.out);
      try {
          javaFile.writeTo(System.out);
      } catch (IOException e) {
          e.printStackTrace();
      }
  }

  @Test
  public void testMain2() {
    JavaFile javaFile = PoeticFile.Of.packages("com.squareup.tacos").of(
            PoeticClass.Of.publics().classes("Taco").method(
                PoeticMethod.Of.publics().voids().name("taco").parameter(String.class, "args").statement(
                    PoeticStatement.of("$T.out.println($S)", System.class, "Hello, JavaPoet!")
                    )
                )
            );

    try {
        javaFile.writeTo(System.out);
    } catch (IOException e) {
        e.printStackTrace();
    }

    /*
    assertThat(source).isEqualTo(""
        + "package com.squareup.tacos;\n"
        + "\n"
        + "class Taco {\n"
        + "}\n");
    */
  }
}
