package callback;

/**
 * 回调类对象（老板）
 * */
public class Boss implements CallBackInterface{
    Workable worker;

    public void setWorker(Workable worker){
        this.worker = worker;
    }
    public void assign(){
        System.out.println("Begin to work");
        worker.work();
    }
    @Override
    public void callback() {
        System.out.println("OK! I know you finish the work!" );
    }
}
