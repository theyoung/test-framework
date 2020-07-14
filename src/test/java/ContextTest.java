import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ContextTest {


    ArrayList<Bean> beans = new ArrayList<>();

    void readXml() {
        Bean bean1 = new Bean();
        bean1.setPath("TestCase");
        bean1.setParamsName("steven");
        bean1.setParamsYear("41");

        Bean bean2 = new Bean();
        bean2.setPath("TestCaseAlpha");
        bean2.setParamsName("google");
        bean2.setParamsYear("20");

        beans.add(bean1);
        beans.add(bean2);
    }

    @Test
    void injection() throws Exception{
        Context context = new Context();

        context.setPointCut("onCreate");

        readXml();

        ArrayList<TestCaseInterface> list = context.creatBeans(beans);

        for (TestCaseInterface elem : list) {
            System.out.println(context.getHashCode(elem));
        }

        TestCaseInterface tt = list.get(0);
        tt.setYear("33");

        System.out.println(context.getHashCode(tt));
    }


    //Not Properly Working Code
    @Test
    void injectionThread() throws Exception{
        Context context = new Context();

        context.setPointCut("onCreate");

        readXml();

        TestCaseInterface ic = context.creatBean("TestCaseThread");
        ic.setName("Normal Person");
        ic.setYear("1");

        System.out.println(context.getHashCode(ic));
    }
}