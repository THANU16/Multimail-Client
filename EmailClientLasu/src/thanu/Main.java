package thanu;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        FileHandling fileHandling = new FileHandling();

        // this is your html file name
//        String fileName ="D:\\lasu\\EmailClientLasu\\bodyHTML.htm";
//        String fileName ="D:\\lasu\\EmailClientLasu\\bodyNewHTML.htm";


        // this we are add html tag in out txt
        //and open that file
//        File mailBodyFile = new File("src\\sampleBody.txt");
//      File mailBodyFile = new File("src\\body.txt");
        File mailBodyFile = new File("EmailClientLasu\src\sampleBody.txt");


        //open the mailid details file
        File mailIDFile = new File("src\\mailIDs.txt");

        //read the mailid detail and store in arrayList
        ArrayList<String> mailIds= fileHandling.readFileReturnArray(mailIDFile);

        //Set the subject
        String subject = ("APPLICATION FOR THE POST OF CIVIL /SITE ENGINEER");  // set the mail subject
//        String subject = ("CHECK MY APPLICATION");  // set the mail subject

        //set the body
        String body = fileHandling.readFile(mailBodyFile);   //read the text file and set the body
//        String body = fileHandling.readFileReturnHTMLString(fileName);      //read the html file and set the body

//        System.out.println(body);


        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter option type: \n"
                + "1 - Sending an email\n"
                + "2 - Printing out the number of emails send");

        while (scanner.hasNextInt()) {
            int option = scanner.nextInt();


            switch (option) {
                case 1:
                    // code to send an email
                    System.out.println("You are select Sending an email");

                    Scanner data = new Scanner(System.in);

                    //Traverse the mailIDs arraylist
                    for (int i=0; i< mailIds.size(); i++) {
                        String mailAddress = mailIds.get(i);  // get the mailID and set the mail address
                        System.out.println(mailAddress);
                        Mail mail = new Mail(mailAddress, subject, body);
                        mail.sendMail();
                    }
                    break;


                case 2:
                    // code to print the number of recipient objects in the application
                    System.out.println("You are select Printing out the number of emails send");
                    System.out.println("the number of emails send => " + Mail.getNoOfMailSend());
                    break;

            }

            System.out.println("Enter your option");
        }

    }
}
