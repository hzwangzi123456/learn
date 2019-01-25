package trycatchfinally;

/**
 * @author wangzi
 * @date 18/12/5 上午10:30.
 */
public class demo {
    public static int WithException() {
        int i = 10;
        try {
            System.out.println("i in try block is ： " + i);
            i = i / 0;
            return --i;
        } catch (Exception e) {
            System.out.println("i in catch - form try block is ： " + i);
            --i;
            System.out.println("i in catch block is ： " + i);
            return --i;
        } finally {
            System.out.println("i in finally - from try or catch block is--" + i);
            --i;
            System.out.println("i in finally block is--" + i);
            return --i;
        }
    }
//    执行顺序：
//    抛出异常后，执行catch块，在catch块的return的--i执行完后，并不直接返回而是执行finally，因finally中有return语句，所以，执行，返回结果6。
//    结论：
//    try块中抛出异常，try、catch和finally中都有return语句，返回值是finally中的return。
//    总体结论：
//    结论一：
//    return语句并不是函数的最终出口，如果有finally语句，这在return之后还会执行finally（return的值会暂存在栈里面，等待finally执行后再返回）
//    结论二：
//    finally里面不建议放return语句，根据需要，return语句可以放在try和catch里面和函数的最后。可行的做法有四：
//            （1）return语句只在函数最后出现一次。
//            （2）return语句仅在try和catch里面都出现。
//            （3）return语句仅在try和函数的最后都出现。
//            （4）return语句仅在catch和函数的最后都出现。
//    注意，除此之外的其他做法都是不可行的，编译器会报错
}
