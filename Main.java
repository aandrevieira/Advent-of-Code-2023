import java.io.*;
import java.util.*;
import java.lang.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;
        int choice2;
        do {
            System.out.println("\nMenu:");
            System.out.println("1. C1");
            System.out.println("2. C2");
            System.out.println("3. C3");
            System.out.println("42. Exit");
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
                    System.out.println("1. P1");
                    System.out.println("2. P2");
                    choice2 = scanner.nextInt();
                    if(choice2 == 1) {
                        C2P1(); 
                    } else if (choice2 ==2) {
                        C2P2();
                    }
                    break;
                case 42:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 42);

        scanner.close();
    }

    public static void C1P1() { //answer to challenge one part 1 was 55386
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
            System.out.println("Your answer: " + answer);     
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
      }
    }

    public static void C1P2() { //answer to challenge one part 2 was 54824
        try{

            File file = new File("C1file.txt");
            Scanner scanner = new Scanner(file);
            Integer answer = 0;
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

            while (scanner.hasNextLine()) {

                String data = scanner.nextLine();
                char num1 = '0';
                char num2 = '0';
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
            System.out.println("Your answer: " + answer);    
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
      }
    }

    public static void C2P1 () { //answer to challenge 2 part 1 was 3035
        try{

            Integer answer = 0;
            File file = new File("C2file.txt");
            Scanner scanner = new Scanner(file);
            Map<String, Integer> colorsMap = Map.of(
                "red", 12,
                "green", 13,
                "blue", 14
            );

            while (scanner.hasNextLine()) {

                String data = scanner.nextLine();
                boolean possible = true;

                for(String color : colorsMap.keySet()){
                    Pattern pattern = Pattern.compile("(\\d+) " + color);
                    Matcher matcher = pattern.matcher(data);

                    while(matcher.find()){
                        if (Integer.parseInt(matcher.group(1)) > colorsMap.get(color)){
                            possible = false;
                        }
                    }
                }

                if(possible){
                    Pattern pattern = Pattern.compile("Game (\\d+):");
                    Matcher matcher = pattern.matcher(data);
                    if(matcher.find()){
                        answer += Integer.parseInt(matcher.group(1));
                    }
                }
            }
            scanner.close();
            System.out.println("Your answer: " + answer);     
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
      }
    }

    public static void C2P2 () { //answer to challenge 2 part 1 was 66027
        try{

            Integer answer = 0;
            File file = new File("C2file.txt");
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {

                String data = scanner.nextLine();
                boolean possible = true;
                Map<String, Integer> colorsMap = new HashMap<>();
                colorsMap.put("red", -1);
                colorsMap.put("green", -1);
                colorsMap.put("blue", -1);

                for(String color : colorsMap.keySet()){
                    Pattern pattern = Pattern.compile("(\\d+) " + color);
                    Matcher matcher = pattern.matcher(data);

                    while(matcher.find()){
                        int n = Integer.parseInt(matcher.group(1));
                        if (n > colorsMap.get(color)){
                            colorsMap.put(color, n);
                        }
                    }
                }

                answer +=  colorsMap.get("red") * colorsMap.get("green") * colorsMap.get("blue");
            }

            scanner.close();
            System.out.println("Your answer: " + answer);   
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
      }
    }
}