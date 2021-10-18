package cn.sdust.dao;

import cn.sdust.pojo.Bill;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("billDao")
public interface BillDao {
    void insertBill(Bill bill);
    List<Bill> getBillByid(int uid);
}
