import javassist.*; 

public class ClassModifier {
    public static void main(String[] args) {        
        try {            
            System.loadLibrary("jvmfaketime");

            ClassPool pool = ClassPool.getDefault();
            CtClass systemClass = pool.get("java.lang.System");

            CtMethod registerFakeCurrentTimeMillis = CtNewMethod.make(Modifier.PUBLIC | Modifier.STATIC | Modifier.NATIVE, CtClass.voidType, "registerFakeCurrentTimeMillis", new CtClass[0], new CtClass[0], null, systemClass);
            systemClass.addMethod(registerFakeCurrentTimeMillis);

            CtMethod deregisterFakeCurrentTimeMillis = CtNewMethod.make(Modifier.PUBLIC | Modifier.STATIC | Modifier.NATIVE, CtClass.voidType, "deregisterFakeCurrentTimeMillis", new CtClass[0], new CtClass[0], null, systemClass);
            systemClass.addMethod(deregisterFakeCurrentTimeMillis);
            
            CtClass[] parameter = new CtClass[1];
            parameter[0] = CtClass.longType;
			CtMethod setTimeOffset = CtNewMethod.make(Modifier.PUBLIC | Modifier.STATIC | Modifier.NATIVE, CtClass.voidType, "setTimeOffset", parameter, new CtClass[0], null, systemClass);
            systemClass.addMethod(setTimeOffset);
            
            CtMethod getTimeOffset = CtNewMethod.make(Modifier.PUBLIC | Modifier.STATIC | Modifier.NATIVE, CtClass.longType, "getTimeOffset", new CtClass[0], new CtClass[0], null, systemClass);
            systemClass.addMethod(getTimeOffset);

            systemClass.writeFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
