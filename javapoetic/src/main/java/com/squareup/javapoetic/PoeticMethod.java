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

import java.io.IOException;
import java.io.StringWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeParameterElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.type.TypeVariable;

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
public class PoeticMethod {

    List<Modifier> mModifiers = new ArrayList();

    public static class Of {
        public static PoeticMethod publics() {
            PoeticMethod poeticMethod = new PoeticMethod();
            poeticMethod.modifiers().add(Modifier.PUBLIC);
            return poeticMethod;
        }

        public static PoeticMethod privates() {
            PoeticMethod poeticMethod = new PoeticMethod();
            poeticMethod.modifiers().add(Modifier.PRIVATE);
            return poeticMethod;
        }

        public static PoeticMethod protecteds() {
            PoeticMethod poeticMethod = new PoeticMethod();
            poeticMethod.modifiers().add(Modifier.PROTECTED);
            return poeticMethod;
        }
    }

    public PoeticMethod finals() {
        mModifiers.add(Modifier.FINAL);
        return this;
    }

    public PoeticMethod statics() {
        mModifiers.add(Modifier.STATIC);
        return this;
    }

    String mName;

    public PoeticMethod name(String name) {
        mName = name;
        return this;
    }

    public List<Modifier> modifiers() {
        return mModifiers;
    }

    Class<?> mType;

    public PoeticMethod voids() {
        return type(void.class);
    }

    public PoeticMethod ints() {
        return type(int.class);
    }

    public PoeticMethod booleans() {
        return type(boolean.class);
    }

    public PoeticMethod bytes() {
        return type(byte.class);
    }

    public PoeticMethod ofInteger() {
        return type(Integer.class);
    }

    public PoeticMethod ofString() {
        return type(String.class);
    }

    public PoeticMethod ofBoolean() {
        return type(Boolean.class);
    }

    public PoeticMethod type(Class<?> type) {
        mType = type;
        return this;
    }

    List<ParameterSpec> mParameters = new ArrayList<>();

    public PoeticMethod parameters(Iterable<ParameterSpec> parameterSpecs) {
        //checkArgument(parameterSpecs != null, "parameterSpecs == null");

        for (ParameterSpec parameterSpec : parameterSpecs) {
            mParameters.add(parameterSpec);
        }
        return this;
    }

    public PoeticMethod parameter(ParameterSpec parameterSpec) {
        mParameters.add(parameterSpec);
        return this;
    }

    public PoeticMethod parameter(TypeName type, String name, Modifier... modifiers) {
      return parameter(ParameterSpec.builder(type, name, modifiers).build());
    }

    public PoeticMethod parameter(Type type, String name, Modifier... modifiers) {
      return parameter(TypeName.get(type), name, modifiers);
    }

    public MethodSpec statements(PoeticStatement... statements) {
        //checkArgument(statements != null, "statements == null");

        MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder(mName)
            .addModifiers(mModifiers.toArray(new Modifier[0]))
            .returns(mType)
            .addParameters(mParameters);

        for (PoeticStatement statement : statements) {
            methodBuilder.addStatement(statement.format, statement.args);
        }

        return methodBuilder.build();
    }

    public MethodSpec statement(PoeticStatement... statements) {
        return statements(statements);
    }
}
