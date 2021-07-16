package interexteninter;

/**
 * @program: TestProject
 * @description:
 * @author: zhangfl
 * @create: 2021-06-10 17:39
 **/
public class implementB implements A, B{
    @Override
    public void funA() {
        System.out.println("implementB====funA");
    }

    @Override
    public void funB() {
        System.out.println("implementB====funB");
    }
}
