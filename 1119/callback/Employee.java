package callback;

/**
 * 控制类(员工对象)，必须持有老板的地址（回调接口）
 * */
public class Employee implements Workable{
    private CallBackInterface callBack = null;

    //告诉老板的联系方式，也就是注册
    public void setCallBack(CallBackInterface callBack){
        this.callBack = callBack;
    }

    //工人自己的工作
    public void work(){
        //1.开始干活了
        for(int i=0;i<10;i++){
            System.out.println("第【" + i + "】事情干完了！");
        }
        //2.告诉老板干完了
       callBack.callback();

    }

}
