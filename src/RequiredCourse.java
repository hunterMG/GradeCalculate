/**
 * Created by yg on 2017/9/25.
 */
import java.lang.String;

import java.io.*;

public class RequiredCourse {

    public static void main(String[] args) {

        char c[] = new char[19];
        String [] student = {"杨学进","范华磊","曹刚毓","曾新贵","程景亮","杜强","范成龙","官翔","黄超","李昊霖","李晓航","刘子龙","孟才钰","甯代豪","秦广乾","王德聪","王明亮","王小哲","王旭","仵宇","颜光","张伟","周锦","程枫","计晗雪","宋金彩","徐延辉","张娜","王志文"};//29 persons
        String [][] scoreRead = new String[1400][11] ;
        File sourceFile = new File("Required1404.csv");
        File targetFile = new File("DesRequired1404.csv");

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

            bufferedWriter.write("姓名,必修所修总学分（包括不及格）,必修所修学分（不包括不及格）,必修欠学分（截止第7学期之前的必修应为117）,欠学分科目,总分（已过必修成绩*学分的总和）,学分绩,有无冲抵,\n");

            for (int k=0; k<29; k++){
                bufferedWriter.write(student[k]);
                double allCredit = 0;//总学分
                double credit2 = 0;  //及格的学分
                double creditOwed = 0;//必修欠学分
                StringBuilder owedCourses = new StringBuilder();//欠学分科目
                double wholeScore = 0;//总分
                double creditScore = 0;//学分绩
                for (int i=0; i<1395; i++){

                    if(scoreRead[i][1].equals(student[k])){
                        allCredit += Double.parseDouble(scoreRead[i][10]);
                        if(Double.parseDouble(scoreRead[i][6]) >= 60){
                            credit2 += Double.parseDouble(scoreRead[i][10]);
                            wholeScore += (Double.parseDouble(scoreRead[i][10]) * Double.parseDouble(scoreRead[i][6]));
                        }else {
                            owedCourses.append(scoreRead[i][5]+"，");
                        }

                    }
                }

                if(credit2 >= 117.0){
                    creditOwed = 0;
                }else {
                    creditOwed = 117 - credit2;
                }
                creditScore = wholeScore / credit2;
                String buf1 = ","+Double.toString(allCredit) + ","+ Double.toString(credit2)+ ","+ Double.toString(creditOwed) + "," + owedCourses + "," + Double.toString(wholeScore)+","+Double.toString(creditScore);
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
