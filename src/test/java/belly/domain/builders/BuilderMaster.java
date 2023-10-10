package belly.domain.builders;

import static java.lang.String.format;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Class responsible for crete builders
 *
 * @author Sergio Ruy
 *
 */
public class BuilderMaster { // todo: translate for english

    Set<String> listImports = new HashSet<String>();

    @SuppressWarnings("rawtypes")
    public void generateCodeClass(Class classed) {

        String className = classed.getSimpleName() + "Builder";

        StringBuilder builder = new StringBuilder();

        builder.append(format("public class %s {\n", className));

        List<Field> declaredFields = getClassFields(classed).stream()
                .filter(campo -> !campo.getName().equals("serialVersionUID") && !Modifier.isStatic(campo.getModifiers()))
                .toList();
        declaredFields.forEach(field -> {
            if (field.getType().getSimpleName().equals("List"))
                builder.append(format("\tprivate %s<%s> %s;\n", field.getType().getSimpleName(), getGenericSimpleName(field), field.getName()));
            else
                builder.append(format("\tprivate %s %s;\n", field.getType().getSimpleName(), field.getName()));
        });

        builder.append(format("\n\tprivate %s(){}\n\n", className));

        builder.append(format("\tpublic static %s um%s() {\n", className, classed.getSimpleName()));
        builder.append(format("\t\t%s builder = new %s();\n", className, className));
        builder.append("\t\tinitializeStandardAttributes(builder);\n");
        builder.append("\t\treturn builder;\n");
        builder.append("\t}\n\n");

        builder.append(format("\tprivate static void initializeStandardAttributes(%s builder) {\n", className));
        declaredFields.forEach(campo -> builder.append(format("\t\tbuilder.%s = %s;\n", campo.getName(), getDefaultParameter(campo))));
        builder.append("\t}\n\n");

        for (Field field : declaredFields) {
            registerImports(field.getType().getCanonicalName());
            if (field.getType().getSimpleName().equals("List")) {
                registerImports("java.util.Arrays");
                builder.append(format("\tpublic %s withList%s%s(%s... %s) {\n",
                        className, field.getName().substring(0, 1).toUpperCase(), field.getName().substring(1), getGenericSimpleName(field), field.getName()));
                builder.append(format("\t\tthis.%s = Arrays.asList(%s);\n", field.getName(), field.getName()));
            } else {
                builder.append(format("\tpublic %s com%s%s(%s %s) {\n",
                        className, field.getName().substring(0, 1).toUpperCase(), field.getName().substring(1), field.getType().getSimpleName(), field.getName()));
                builder.append(format("\t\tthis.%s = %s;\n", field.getName(), field.getName()));
            }
            builder.append("\t\treturn this;\n");
            builder.append("\t}\n\n");
        }

        builder.append(format("\tpublic %s agora() {\n", classed.getSimpleName()));
        builder.append(format("\t\treturn new %s(", classed.getSimpleName()));
        boolean first = true;
        for (Field campo : declaredFields) {
            if(first) {
                first = false;
            } else {
                builder.append(", ");
            }
            builder.append(campo.getName());
        }
        builder.append(");\n\t}\n");

        builder.append("}");

        for (String str : listImports) {
            if(!str.contains("java.lang."))
                System.out.println(str);
        }
        System.out.println(format("import %s;\n", classed.getCanonicalName()));
        System.out.println(builder.toString());
    }

    @SuppressWarnings("rawtypes")
    private String getGenericSimpleName(Field campo) {
        ParameterizedType stringListType = (ParameterizedType) campo.getGenericType();
        return ((Class) stringListType.getActualTypeArguments()[0]).getSimpleName();
    }

    @SuppressWarnings("rawtypes")
    public List<Field> getClassFields(Class classe) {
        List<Field> fields = new ArrayList<Field>();
        fields.addAll(Arrays.asList(classe.getDeclaredFields()));
        Class superClass = classe.getSuperclass();
        if (superClass != Object.class) {
            List<Field> fieldsSC = Arrays.asList(superClass.getDeclaredFields());
            fields.addAll(fieldsSC);
        }
        return fields;
    }

    public String getDefaultParameter(Field campo) {
        String tipo = campo.getType().getSimpleName();
        if (tipo.equals("int") || tipo.equals("Integer")) {
            return "0";
        }
        if (tipo.equalsIgnoreCase("long")) {
            return "0L";
        }
        if (tipo.equalsIgnoreCase("double") || tipo.equalsIgnoreCase("float")) {
            return "0.0";
        }
        if (tipo.equalsIgnoreCase("boolean")) {
            return "false";
        }
        if (tipo.equals("String")) {
            return "\"\"";
        }
        return "null";
    }

    public void registerImports(String classe) {
        if (classe.contains("."))
            listImports.add("import " + classe + ";");
    }

    public static void main(String[] args) {
        BuilderMaster master = new BuilderMaster();
        master.generateCodeClass(null);
    }
}
