package callback;

public class Test {
    public static void main(String[] args) {
        Boss boss = new Boss();
        Employee employee = new Employee();
        boss.setWorker(employee);
        employee.setCallBack(boss);
        boss.assign();


    }
}
