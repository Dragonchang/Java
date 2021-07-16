package interexteninter;

/**
 * @program: TestProject
 * @description:
 * @author: zhangfl
 * @create: 2021-06-10 16:53
 **/
public class implement extends implementB implements B, C{

    @Override
    public void funA() {
        System.out.println("implement====funA");
    }



    @Override
    public void funC() {
        System.out.println("implement====funC");

    }
}
