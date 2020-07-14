import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Context {
    BlockingQueue<Message> blockingQueue = new ArrayBlockingQueue<>(10);
    HashMap<TestCaseInterface, Integer> singletone = new HashMap<>();
    String pointCut = "";

    public String getPointCut() {
        return pointCut;
    }

    public void setPointCut(String pointCut) {
        this.pointCut = pointCut;
    }

    void injection(TestCaseInterface testCase) {
        singletone.put(testCase, (testCase.getName() + testCase.getYear()).hashCode());
    }

    int getHashCode(TestCaseInterface testCase) {
        testCase.onDestroy();
        return singletone.get(testCase);
    }

    TestCaseInterface creatBean(String clazzName) throws Exception {
        Class<?> clazz = Class.forName(clazzName);
        Type type = clazz.getGenericSuperclass();
        if (clazz != null) {
//            TestCaseInterface testCaseInterface = (TestCaseInterface)clazz.getDeclaredConstructor().newInstance();
//            testCaseInterface.onCreate();
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(clazz);
            enhancer.setCallback(new MethodInterceptor() {
                @Override
                public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                    Object result;

                    //PointCut
                    if (method.getName() == pointCut) {
                        System.out.println(method.getName() + " : Start");
                        result = methodProxy.invokeSuper(o, objects);
                        System.out.println(method.getName() + " : End");

                    } else {
                        result = methodProxy.invokeSuper(o, objects);
                    }

                    //Observer
                    if (method.getName() == "setName" || method.getName() == "setYear") {
                        TestCaseInterface proxy = (TestCaseInterface) o;

                        if(singletone.containsKey(proxy)){
                            singletone.put(proxy, (proxy.getName() + proxy.getYear()).hashCode());
                        }
                    }

                    return result;

                }
            });
            TestCaseInterface test;

            if(type.getTypeName() == "TestCaseAbstract"){

                test = (TestCaseInterface) enhancer.create(new Class[]{blockingQueue.getClass()}, new Object[]{blockingQueue});
            } else {
                test = (TestCaseInterface) enhancer.create();
            }
            test.onCreate();
            return test;
        }

        return null;
    }

    ArrayList<TestCaseInterface> creatBeans(ArrayList<Bean> beans) throws Exception{
        ArrayList<TestCaseInterface> array = new ArrayList<>();

        for (Bean bean : beans) {
            TestCaseInterface testCase = this.creatBean(bean.getPath());
            testCase.setName(bean.getParamsName());
            testCase.setYear(bean.getParamsYear());
            this.injection(testCase);
            array.add(testCase);
        }

        return array;
    }


}
