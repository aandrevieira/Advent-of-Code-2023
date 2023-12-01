import java.io.*;
import java.util.*;
import java.lang.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;
        int choice2;
        do {
            System.out.println("\nMenu:");
            System.out.println("1. C1");
            System.out.println("2. C2");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("1. P1");
                    System.out.println("2. P2");
                    choice2 = scanner.nextInt();
                    if(choice2 == 1) {
                        C1P1(); 
                    } else if (choice2 ==2) {
                        C1P2();
                    }
                    break;
                case 2:
                    break;
                case 3:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 3);

        scanner.close();
    }

    public static void C1P1() { //answer to challenge one was 55386
        try{
            File file = new File("C1file.txt");
            Scanner scanner = new Scanner(file);
            Integer answer = 0;
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                char num1 = '0';
                char num2 = '0';
                for(int i = 0; i < data.length(); i++) {
                    if(Character.isDigit(data.charAt(i)) && num1 == '0' ) {
                        num1 = data.charAt(i);
                        num2 = data.charAt(i);
                    }
                    if(Character.isDigit(data.charAt(i))) {
                        num2 = data.charAt(i);
                    }
                }
                answer += Integer.valueOf(String.valueOf(num1) + String.valueOf(num2));
            }
            scanner.close();
            System.out.println(answer);   
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
      }
    }

    public static void C1P2() { //answer to challenge two was 54824
        try{

            File file = new File("C1file.txt");
            Scanner scanner = new Scanner(file);
            Integer answer = 0;

            while (scanner.hasNextLine()) {

                String data = scanner.nextLine();
                char num1 = '0';
                char num2 = '0';
                Map<String,Character> numsMap = Map.of(
                    "one", '1',
                    "two", '2',
                    "three", '3',
                    "four", '4',
                    "five", '5',
                    "six", '6',
                    "seven", '7',
                    "eight", '8',
                    "nine", '9'
                );
                int firstId = data.length();
                int lastId = -1;
                String[] firstLastSubstrings = new String[2];

                for (String substr : numsMap.keySet()){

                    int currentFirstIndex = data.indexOf(substr);
                    int currentLastIndex = data.lastIndexOf(substr);

                    if (currentFirstIndex != -1 && currentFirstIndex < firstId) {
                        firstId = currentFirstIndex;
                        firstLastSubstrings[0] = substr;
                    }
        
                    if (currentLastIndex > lastId) {
                        lastId = currentLastIndex;
                        firstLastSubstrings[1] = substr;
                    }
                }

                for(int i = 0; i < data.length(); i++) {
                    if(Character.isDigit(data.charAt(i)) && num1 == '0' ) {
                        if(firstId < i){
                            num1 = numsMap.get(firstLastSubstrings[0]);
                            num2 = numsMap.get(firstLastSubstrings[0]);
                        } else {
                            num1 = data.charAt(i);
                            num2 = data.charAt(i);
                        }
                    }
                    if(Character.isDigit(data.charAt(i))) {
                        num2 = data.charAt(i);
                        if(lastId > i){
                            num2 = numsMap.get(firstLastSubstrings[1]);
                        } else {
                            num2 = data.charAt(i);
                        }
                    }
                }

                answer += Integer.valueOf(String.valueOf(num1) + String.valueOf(num2));
            }
            scanner.close();
            System.out.println(answer);   
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
      }
    }
}