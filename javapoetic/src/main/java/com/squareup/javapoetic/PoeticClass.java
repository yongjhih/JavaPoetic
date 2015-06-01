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

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

// JavaFile javaFile = PoeticFile.of.packages("com.example.helloworld").classes(
//     JavaClass.of.publics().final().name("HelloWorld").method(
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
public class PoeticClass {

    List<Modifier> mModifiers = new ArrayList<>();

    public static class Of {
        public static PoeticClass publics() {
            PoeticClass poeticClass = new PoeticClass();
            poeticClass.modifiers().add(Modifier.PUBLIC);
            return poeticClass;
        }

        public static PoeticClass privates() {
            PoeticClass poeticClass = new PoeticClass();
            poeticClass.modifiers().add(Modifier.PRIVATE);
            return poeticClass;
        }

        public static PoeticClass protecteds() {
            PoeticClass poeticClass = new PoeticClass();
            poeticClass.modifiers().add(Modifier.PROTECTED);
            return poeticClass;
        }
    }

    public PoeticClass finals() {
        mModifiers.add(Modifier.FINAL);
        return this;
    }

    public PoeticClass statics() {
        mModifiers.add(Modifier.STATIC);
        return this;
    }

    String mName;

    public PoeticClass name(String name) {
        mName = name;
        return this;
    }

    public PoeticClass classes(String name) {
        return name(name);
    }

    public PoeticClass of(String name) {
        return name(name);
    }

    public List<Modifier> modifiers() {
        return mModifiers;
    }

    public TypeSpec method(MethodSpec... methods) {
        return TypeSpec.classBuilder(mName)
            .addModifiers(mModifiers.toArray(new Modifier[0]))
            .addMethods(Arrays.asList(methods))
            .build();
    }
}
