package cn.sdust.dao;
import cn.sdust.pojo.Book;
import cn.sdust.pojo.SerchItem;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("bookDao")
public interface BookDao {
    List<Book> selectBook();
    List<Book> selectBookbyValue(double value);
    List<Book> selectBookbyName(String name);
    List<Book> selectBookbyType(String type);
    List<Book> selectBookbyWriter(String type);
    Book selectBookbyId(int id);
    List<Book> selectBookbyItem(SerchItem serchItem);
    void addXiao(@Param(value="bid") Integer bid, @Param(value="value") Integer vv);

}
