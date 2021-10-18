package cn.sdust.dao;

import cn.sdust.pojo.Remark;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("remarkDao")
public interface RemarkDao {
    List<Remark> getRemarkById(int id);
    int insertRemark(Remark remark);
}
