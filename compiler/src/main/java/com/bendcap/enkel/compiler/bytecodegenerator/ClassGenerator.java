package com.bendcap.enkel.compiler.bytecodegenerator;

import com.bendcap.enkel.compiler.domain.Function;
import com.bendcap.enkel.compiler.domain.ClassDeclaration;
import com.bendcap.enkel.compiler.domain.scope.Field;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;

import java.util.Collection;
import java.util.List;

/**
 * Created by KevinOfNeu on 2018/8/22  11:50.
 */
public class ClassGenerator {
    private static final int CLASS_VERSION = 52;
    private ClassWriter classWriter;

    public ClassGenerator() {
        classWriter = new ClassWriter(ClassWriter.COMPUTE_FRAMES + ClassWriter.COMPUTE_MAXS);
    }

    public ClassWriter generate(ClassDeclaration classDeclaration) {
        String name = classDeclaration.getName();
        classWriter.visit(
                CLASS_VERSION,
                Opcodes.ACC_PUBLIC + Opcodes.ACC_SUPER,
                name,
                null,
                "java/lang/Object",
                null
        );
        List<Function> methods = classDeclaration.getMethods();
        Collection<Field> fields = classDeclaration.getFields();
        FieldGenerator fieldGenerator = new FieldGenerator(classWriter);
        fields.forEach(f -> f.accept(fieldGenerator));
        MethodGenerator methodGenerator = new MethodGenerator(classWriter);
        methods.forEach(f -> f.accept(methodGenerator));
        classWriter.visitEnd();
        return classWriter;
    }
}
