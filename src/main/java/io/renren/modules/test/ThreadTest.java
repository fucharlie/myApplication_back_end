package io.renren.modules.test;

import com.alibaba.druid.sql.visitor.functions.Char;
import lombok.Builder;
public class ThreadTest extends Thread{
    //当前线程编号
    public static final int PRINT_A = 0;
    public static final int PRINT_B = 1;
    //共享状态码
    public static int state = 0;
    //同步锁。要求只能一份
    public static final Object t = new Object();
    //编号
    int which;
    //由构造方法绘制不同线程
    public ThreadTest(int which){
        this.which = which;
    }
    //重写方法
    @Override
    public void run(){
        for(int i=0;i<10;i++){
            synchronized (t){
                //状态码符合当前线程
                if(state%2==which){
                    System.out.println(toPrintAB(which));
                    state++;
                    //两个线程重新争夺,唤醒获取该同步锁的所有线程
                    t.notifyAll();
                }else{
                    //释放同步锁
                    try {
                        t.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        new ThreadTest(PRINT_A).start();
        new ThreadTest(PRINT_B).start();
    }
    public char toPrintAB(int which){
        return (char)('A'+which);
    }

}

