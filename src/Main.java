import java.io.*;

public class Main {

    public static void main(String[] args) {

        double credit[] = {3,3,3,3,3,2,2,3.5,3,3,4,2,3,2,3,3,3,4,2,3,2,4,3,3,3};
        char c[] = new char[19];

        System.out.println(credit.length + "门课");

        File sourceFile = new File("src.csv");
        File targetFile = new File("des.txt");

        try{
            Writer out= new FileWriter(targetFile,false);//true：采用追加的方式写入字符到文件,false为覆盖的方式
            Reader in= new FileReader(sourceFile);

            BufferedWriter bufferedWriter = new BufferedWriter(out);
            BufferedReader bufferedReader = new BufferedReader(in);
            String str = null;
            for(int i=0; i<4; i++){
                str = bufferedReader.readLine();
            }

            while ((str= bufferedReader.readLine()) != null)
            //str = bufferedReader.readLine();
            {

                String [] buff = str.split(",");
                double credit1 = 0;
                //25 classes
                for (int i=4; i<29; i++){
                    //System.out.println(buff[i]);
                    if(buff[i].equals("0")){
                        credit1 += credit[i-4];
                    }

                }
                String buf1 = Double.toString(credit1);
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
