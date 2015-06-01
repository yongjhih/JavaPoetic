/*
 * Copyright (C) 2015 8tory, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.squareup.javapoetic;

import com.squareup.javapoet.*;

import javax.lang.model.element.Modifier;

// JavaFile javaFile = PoeticFile.of.packages("com.example.helloworld").classes(
//     JavaClass.of.public().final().name("HelloWorld").method(
//             JavaMethod.of.public().static().void().name("main").parameter(String[].class, "args").statement(
//                 JavaStatement("$T.out.println($S)", System.class, "Hello, JavaPoet!"),
//                 JavaStatement("$T.out.println($S)", System.class, "Hello, JavaPoet!")
//             )
//         )
//     )
// );
//
// MethodSpec main = MethodSpec.methodBuilder("main")
//     .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
//     .returns(void.class)
//     .addParameter(String[].class, "args")
//     .addStatement("$T.out.println($S)", System.class, "Hello, JavaPoet!")
//     .build();
//
// TypeSpec helloWorld = TypeSpec.classBuilder("HelloWorld")
//     .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
//     .addMethod(main)
//     .build();
//
// JavaFile javaFile = JavaFile.builder("com.example.helloworld", helloWorld)
//     .build();
//
// javaFile.emit(System.out);
public class PoeticFile {

    String mPackages;

    public static class Of {
        public static PoeticFile packages(String packages) {
            PoeticFile poeticFile = new PoeticFile();
            poeticFile.packages(packages);
            return poeticFile;
        }
    }

    public PoeticFile packages(String packages) {
        mPackages = packages;
        return this;
    }

    // TODO public static PoeticFile classes(PoeticClass... classes) {
    public JavaFile classes(TypeSpec type) {
        return of(type);
    }

    public JavaFile of(TypeSpec type) {
        return JavaFile.builder(mPackages, type).build();
    }
}
