package com.bendcap.enkel.compiler.utils;

import com.bendcap.enkel.compiler.domain.expression.FunctionParameter;
import com.bendcap.enkel.compiler.domain.scope.FunctionSignature;
import com.bendcap.enkel.compiler.domain.type.ClassType;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

/**
 * Created by KevinOfNeu on 2018/8/30  16:07.
 */
public class ReflectionObjectToSignatureMapper {
    public static FunctionSignature fromMethod(Method method) {
        String name = method.getName();
        List<FunctionParameter> parameters = Arrays.stream(method.getParameters())
                .map(p -> new FunctionParameter(p.getName(), new ClassType(p.getType().getCanonicalName()), Optional.empty()))
                .collect(toList());
        Class<?> returnType = method.getReturnType();
        return new FunctionSignature(name, parameters, new ClassType(returnType.getCanonicalName()));
    }

    public static FunctionSignature fromConstructor(Constructor constructor) {
        String name = constructor.getName();
        List<FunctionParameter> parameters = Arrays.stream(constructor.getParameters())
                .map(p -> new FunctionParameter(p.getName(), new ClassType(p.getType().getCanonicalName()), Optional.empty()))
                .collect(toList());
        Class<?> returnType = constructor.getDeclaringClass();
        return new FunctionSignature(name, parameters, new ClassType(returnType.getCanonicalName()));
    }
}
