import java.io.*;
import java.util.HashMap;

/**
 * Created by yg on 2017/9/26.
 */
public class NumberRequiredCourse {
    public static void main(String[] args) {

        char c[] = new char[19];
        String [][] scoreRead = new String[1400][11] ;

        int errorCount = 0;
        //读入编号
        HashMap course2number = new HashMap();//CourseName--Number
        try {
            File RequireCourseList = new File("RequireCourseList.csv");
            Reader listReader = new FileReader(RequireCourseList);
            BufferedReader buffListReader = new BufferedReader(listReader);

            String listbuff;
            while ((listbuff=buffListReader.readLine())!=null){
                String [] map = listbuff.split(",");
                if(map.length>1) {
                    course2number.put(map[0], map[1]);
                }
            }
        }catch (Exception e){
            System.out.println("Exception "+e);
        }

        File sourceFile = new File("Required1404.csv");
        File targetFile = new File("NumberRequired1404.csv");
        try{
            Writer out= new FileWriter(targetFile,false);//true：采用追加的方式写入字符到文件,false为覆盖的方式
            Reader in= new FileReader(sourceFile);

            BufferedWriter bufferedWriter = new BufferedWriter(out);
            BufferedReader bufferedReader = new BufferedReader(in);
            String str = null;
            for(int i=0; i<2; i++) {        //跳过前面无用的行
                str = bufferedReader.readLine();
            }
            int count = 0;
            while ((str= bufferedReader.readLine()) != null)
            //str = bufferedReader.readLine();
            {

                String [] buff = str.split(",");
                scoreRead [count++] = buff;
            }
            System.out.println("共 "+ count +" 行");
            String buf1;
            for (int i=0; i<1395; i++){

            //    System.out.println("1"+scoreRead[i][5]+"1");
                if(course2number.containsKey(scoreRead[i][5])){//name of the course
                    buf1 = scoreRead[i][5] + "," + course2number.get(scoreRead[i][5]);
                }else {
                    System.out.println( (errorCount++) + " Not found: " + scoreRead[i][5]);
                    buf1 = scoreRead[i][5] + "\t ";
                }

                bufferedWriter.write(buf1);
                bufferedWriter.newLine();
            }

            bufferedReader.close();
            bufferedWriter.close();
        }
        catch(IOException e){
            System.out.println("Error:"+e);
        }
    }
}
