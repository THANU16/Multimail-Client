package thanu;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHandling {

    ArrayList<String> mailIds = new ArrayList<String>();

    //Write the data on given file
    public void writeInFile(File myFile, String data){
        System.out.println(data + " wrote in your file");
        try {
            FileWriter fileWriter = new FileWriter (myFile, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            PrintWriter printWriter = new PrintWriter(bufferedWriter);
            printWriter.print(data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    //read the data in the given file
    //return the data
    public String readFile(File myFile){
        String data = "";
        System.out.println("read file");
        try{
            Scanner myReader = new Scanner(myFile);
            while (myReader.hasNext()){
//                System.out.println(myReader.nextLine());
                data += myReader.nextLine()+"\n";

            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        return data;
    }

    //read the data in the given file
    //return the arraylist
    //arraylist have each single line data
    public ArrayList<String> readFileReturnArray (File mailIDFile){
        System.out.println("Read the mail address");
        try{
            Scanner myReader = new Scanner(mailIDFile);
            while (myReader.hasNext()){
                mailIds.add(myReader.nextLine().trim());
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return mailIds;
    }


    //read the data in the given html file
    //return the data in html format

    public String readFileReturnHTMLString(String fileName) throws FileNotFoundException {
        /* Constructing String Builder to
        append the string into the html */
        StringBuilder html = new StringBuilder();

        // Reading html file on local directory
        FileReader fileReader = new FileReader(
                fileName);

        // Try block to check exceptions
        try {

            // Initialization of the buffered Reader to get
            // the String append to the String Builder
            BufferedReader br = new BufferedReader(fileReader);

            String value;

            // Reading the String till we get the null
            // string and appending to the string
            while ((value = br.readLine()) != null) {
                html.append(value);
            }



            // Closing the file after all the completion of
            // Extracting
            br.close();
        }

        // Catch block to handle exceptions
        catch (Exception ex) {

            /* Exception of not finding the location and
            string reading termination the function
            br.close(); */
            System.out.println(ex.getMessage());
        }
        // AtLast converting into the string
        String result = html.toString();
        return result;
    }
}
