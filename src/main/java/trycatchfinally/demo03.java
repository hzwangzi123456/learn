package trycatchfinally;

import lombok.Data;

public class demo03 {
    public static void main(String[] args) {
        Accout accout01 = new Accout("A账户");
        Accout accout02 = new Accout("B账户");
        User user01 = new User("甲", accout01);
        User user02 = new User("乙", accout01);
        User user03 = new User("丙", accout02);
        User user04 = new User("丁", accout02);
        new addThread(200L,accout01,user01).start();
        new quThread(100L,accout01,user02).start();

        new addThread(500L,accout02,user03).start();
        new quThread(2000L,accout02,user04).start();
    }
}

@Data
class Accout {
    public String name;
    public long money = 1000L;

    public Accout(String name) {
        this.name = name;
    }

}

@Data
class User {
    public String name;
    public Accout accout;

    public User(String name, Accout accout) {
        this.name = name;
        this.accout = accout;
    }
}

//存钱
class addThread extends Thread {
    public long money;
    public Accout accout;
    public User user;

    public addThread(long money,Accout accout,User user) {
        this.money = money;
        this.accout = accout;
        this.user = user;
    }

    @Override
    public void run() {
        synchronized (accout) {
            accout.setMoney(accout.getMoney() + money);
            System.out.println(user.getName() + "对"+ accout.getName() + "存" + money + " 余额:" + accout.getMoney());
        }

    }
}

//查
class selectThread extends Thread {
    public Accout accout;
    public User user;

    public selectThread(Accout accout,User user) {
        this.accout = accout;
        this.user = user;
    }
    @Override
    public void run() {
        System.out.println(user.getName() + "对"+ accout.getName() + "查" + accout.getMoney());
    }
}

//取钱
class quThread extends Thread {
    public long money;
    public Accout accout;
    public User user;

    public quThread(long money, Accout accout, User user) {
        this.money = money;
        this.accout = accout;
        this.user = user;
    }

    @Override
    public void run() {
        synchronized (accout) {
            accout.setMoney(accout.getMoney() - money);
            if(accout.getMoney() < 0) {
                accout.setMoney(accout.getMoney() + money);
                try {
                    throw new Exception("余额不足") ;
                } catch (Exception e) {
                    System.out.println(user.getName() + "对"+ accout.getName() + "取" + money + " " + e.getMessage());
                } finally {
                    return;
                }
            }
            System.out.println(user.getName() + "对"+ accout.getName() + "取" + money+ " 余额:" + accout.getMoney());
        }
    }
}
