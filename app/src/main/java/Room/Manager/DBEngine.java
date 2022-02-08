package Room.Manager;


import android.content.Context;
import android.os.AsyncTask;

import java.util.List;

import Room.Student;
import Room.StudentDB;
import Room.StudentDao;
import Utils.LogUtils;

//DB的引擎
public class DBEngine {

   private StudentDao studentDao;

   public DBEngine(Context context) {
      StudentDB studentDB = StudentDB.getInstance(context);
      studentDao = studentDB.getStudentDao();
   }

   //Dao 增删改查

   //insert
   public void insertStudents(Student... students) {
      new InsertAsyncTask(studentDao).execute(students);
   }

   //update
   public void updateStudents(Student... students) {
      new UpdateAsyncTask(studentDao).execute(students);
   }

   //query 单个
   public void queryStudents(int id) {
      new QueryAsyncTask(studentDao).execute(id);
   }

   //query 全部
   public void queryAllStudents() {
      new QueryAllAsyncTask(studentDao).execute();
   }

   //delete 单个
   public void deleteStudents(Student... students) {
      new DeleteAsyncTask(studentDao).execute(students);
   }

   //delete 全部
   public void deleteAllStudents() {
      new DeleteAllAsyncTask(studentDao).execute();
   }

   //insert异步操作
   static class InsertAsyncTask extends AsyncTask<Student, Void, Void> {

      private StudentDao dao;

      public InsertAsyncTask(StudentDao studentDao) {
         dao = studentDao;
      }
      @Override
      protected Void doInBackground(Student... students) {
         dao.insertStudents(students);
         return null;
      }
   }

   //update异步操作
   static class UpdateAsyncTask extends AsyncTask<Student, Void, Void> {

      private StudentDao dao;

      public UpdateAsyncTask(StudentDao studentDao) {
         dao = studentDao;
      }
      @Override
      protected Void doInBackground(Student... students) {
         dao.updateStudents(students);
         return null;
      }
   }

   //query 单个 异步操作
   static class QueryAsyncTask extends AsyncTask<Integer, Void, Void> {

      private StudentDao dao;

      public QueryAsyncTask(StudentDao studentDao) {
         dao = studentDao;
      }

      @Override
      protected Void doInBackground(Integer... integers) {
         Student students1 = dao.getStudents(integers[0]);
         if(students1 != null) {
            LogUtils.i("DB", "doInBackground 单个查询---" + students1.toString());
         }
         return null;
      }
   }

   //query 全部 异步操作
   static class QueryAllAsyncTask extends AsyncTask<Void, Void, Void> {

      private StudentDao dao;

      public QueryAllAsyncTask(StudentDao studentDao) {
         dao = studentDao;
      }

      @Override
      protected Void doInBackground(Void... voids) {
         List<Student> studentList = dao.getAllStudents();

         for (Student student: studentList) {
            LogUtils.i("DB", "doInBackground 全部查询---" + student.toString());
         }
         return null;
      }
   }

   //delete 单个 异步操作
   static class DeleteAsyncTask extends AsyncTask<Student, Void, Void> {

      private StudentDao dao;

      public DeleteAsyncTask(StudentDao studentDao) {
         dao = studentDao;
      }
      @Override
      protected Void doInBackground(Student... students) {
         dao.deleteStudents(students);
         return null;
      }
   }

   //delete 全部 异步操作
   static class DeleteAllAsyncTask extends AsyncTask<Void, Void, Void> {

      private StudentDao dao;

      public DeleteAllAsyncTask(StudentDao studentDao) {
         dao = studentDao;
      }


      @Override
      protected Void doInBackground(Void... voids) {
         dao.deleteAllStudents();
         return null;
      }
   }

}
