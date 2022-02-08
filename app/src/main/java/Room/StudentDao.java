package Room;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.RawQuery;
import androidx.room.Update;

import java.util.List;

//@Dao注解 ----->对表进行增删改查
@Dao
public interface StudentDao {

   //增
   @Insert
   void insertStudents(Student... students);

   //改
   @Update
   void updateStudents(Student... students);

   //删 单个
   @Delete
   void deleteStudents(Student... students);

   //查 单个
   @Query("select * from Student where id = :id order by ID desc")
   Student getStudents(int id);

   //查 所有
   @Query("select * from Student order by ID desc")
   List<Student> getAllStudents();

   //删 所有  @Delete用于删除单个
   @Query("delete from Student")
   void deleteAllStudents();
}
