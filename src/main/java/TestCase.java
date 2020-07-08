public class TestCase implements TestCaseInterface { //Bean, DTO, VO, EO
    String name;
    String year;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @Override
    public void onCreate() {
        System.out.println("onCreate");

    }

    @Override
    public void onDestroy() {
        System.out.println("onDestory");

    }

}
