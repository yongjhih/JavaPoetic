# JavaPoetic

![art/java-poetic.png](art/java-poetic.png)

JavaPoet Simple Builder.

Square JavaPoet: https://github.com/square/javapoet

For JavaPoet example:

```java
package com.example.helloworld;

public final class HelloWorld {
  public static void main(String[] args) {
    System.out.println("Hello, JavaPoet!");
  }
}
```

Before:

```java
MethodSpec main = MethodSpec.methodBuilder("main")
    .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
    .returns(void.class)
    .addParameter(String[].class, "args")
    .addStatement("$T.out.println($S)", System.class, "Hello, JavaPoet!")
    .build();

TypeSpec helloWorld = TypeSpec.classBuilder("HelloWorld")
    .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
    .addMethod(main)
    .build();

JavaFile javaFile = JavaFile.builder("com.example.helloworld", helloWorld)
    .build();

javaFile.emit(System.out);
```

After:

```java
JavaFile javaFile = JavaFile.package("com.example.helloworld").class(
    JavaClass.public().final().name("HelloWorld").method(
            JavaMethod.public().static().void().name("main").parameter(String[].class, "args").statement(
                JavaStatement("$T.out.println($S)", System.class, "Hello, JavaPoet!"),
                JavaStatement("$T.out.println($S)", System.class, "Hello, JavaPoet!")
            )
        )
    )
);
```
