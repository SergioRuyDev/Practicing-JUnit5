package belly.builders;

import belly.domain.Transaction;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.util.*;


public class BuilderMasterPOJO {

    Set<String> listaImports;

    public BuilderMasterPOJO() {
        listaImports = new HashSet<String>();
        listaImports.add("import java.util.Arrays;");
//		listaImports.add("import java.util.Collections;");
//		listaImports.add("import java.util.ArrayList;");
    }

    @SuppressWarnings("rawtypes")
    public void generateCodeClass(Class aclass) {


        String nameClass = aclass.getSimpleName() + "Builder";

        StringBuilder builder = new StringBuilder();

        builder.append("public class ").append(nameClass).append(" {\n");
        builder.append("\tprivate ").append(aclass.getSimpleName()).append(" element;\n");

        builder.append("\tprivate ").append(nameClass).append("(){}\n\n");

        builder.append("\tpublic static ").append(nameClass).append(" um").append(aclass.getSimpleName()).append("() {\n");
        builder.append("\t\t").append(nameClass).append(" builder = new ").append(nameClass).append("();\n");
        builder.append("\t\tinitializeStandardData(builder);\n");
        builder.append("\t\treturn builder;\n");
        builder.append("\t}\n\n");

        builder.append("\tpublic static void initializeStandardData(").append(nameClass).append(" builder) {\n");
        builder.append("\t\tbuilder.element = new ").append(aclass.getSimpleName()).append("();\n");
        builder.append("\t\t").append(aclass.getSimpleName()).append(" element = builder.element;\n");
        builder.append("\n\t\t\n");

        List<Field> declaredFields = getClassFields(aclass);
        for(Field field: declaredFields) {
            if(field.getName().equals("serialVersionUID"))
                continue;
            if(Modifier.isStatic(field.getModifiers()))
                continue;
            builder.append("\t\telement.set").append(field.getName().substring(0, 1).toUpperCase()).append(field.getName().substring(1)).append("(").append(getDefaultParameter(field)).append(");\n");

        }
        builder.append("\t}\n\n");

        for(Field field: declaredFields) {
            if(field.getName().equals("serialVersionUID"))
                continue;
            if(Modifier.isStatic(field.getModifiers()))
                continue;
            if(field.getType().getSimpleName().equals("List")) {
                ParameterizedType stringListType = (ParameterizedType) field.getGenericType();
                builder.append("\tpublic ")
                        .append(nameClass)
                        .append(" withList").append(field.getName().substring(0, 1).toUpperCase()).append(field.getName().substring(1))
                        .append("(").append(((Class)stringListType.getActualTypeArguments()[0]).getSimpleName()).append("... params) {\n");
//				List<elemType> list = new ArrayList<elemType>();
//				builder.append("\t\tList<").append(((Class)stringListType.getActualTypeArguments()[0]).getSimpleName()).append("> list = new ArrayList<")
//					.append(((Class)stringListType.getActualTypeArguments()[0]).getSimpleName()).append(">();\n");
//				registrarImports(((Class)stringListType.getActualTypeArguments()[0]).getName());
//				Collections.addAll(list, args);
//				builder.append("\t\tCollections.addAll(list, params);\n");
//				elemento.setelemTypes(list);
                builder.append("\t\telement.set").append(field.getName().substring(0, 1).toUpperCase()).append(field.getName().substring(1)).append("(Arrays.asList(params));\n");

                builder.append("\t\treturn this;\n");
                builder.append("\t}\n\n");
            } else {
                builder.append("\tpublic ")
                        .append(nameClass)
                        .append(" com").append(field.getName().substring(0, 1).toUpperCase()).append(field.getName().substring(1))
                        .append("(").append(field.getType().getSimpleName()).append(" param) {\n");
                registrarImports(field.getType().getCanonicalName());
                builder.append("\t\telement.set")
                        .append(field.getName().substring(0, 1).toUpperCase()).append(field.getName().substring(1))
                        .append("(param);\n");
                builder.append("\t\treturn this;\n");
                builder.append("\t}\n\n");
            }
        }

        builder.append("\tpublic ").append(aclass.getSimpleName()).append(" createEntity() {\n");
        builder.append("\t\treturn element;\n");
        builder.append("\t}\n");

        builder.append("}");

        for(String str: listaImports) {
            System.out.println(str);
        }
        System.out.println("import " + aclass.getCanonicalName() + ";");
        System.out.println("\n");
        System.out.println(builder.toString());
    }

    @SuppressWarnings("rawtypes")
    public List<Field> getClassFields(Class aClass) {
        List<Field> fields = new ArrayList<Field>();
        fields.addAll(Arrays.asList(aClass.getDeclaredFields()));
        Class superClass = aClass.getSuperclass();
        if(superClass != Object.class) {
            List<Field> fieldsSC = Arrays.asList(superClass.getDeclaredFields());
            fields.addAll(fieldsSC);
        }
        return fields;
    }

    public String getDefaultParameter(Field field) {
        String type = field.getType().getSimpleName();
        if(type.equals("int") || type.equals("Integer")){
            return "0";
        }
        if(type.equals("long") || type.equals("Long")){
            return "0L";
        }
        if(type.equals("double") || type.equals("Double")){
            return "0.0";
        }
        if(type.equals("boolean") || type.equals("Boolean")){
            return "false";
        }
        if(type.equals("String")){
            return "\"\"";
        }
        return "null";
    }

    public void registrarImports(String aClass) {
        if(aClass.contains("."))
            listaImports.add("import " + aClass + ";");
    }

    public static void main(String[] args) {
        new BuilderMasterPOJO().generateCodeClass(Transaction.class);
    }
}
