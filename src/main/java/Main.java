import java.io.*;
import java.util.*;
import java.lang.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.javatuples.*;

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
            System.out.println("4. C4");
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
                case 3:
                    System.out.println("1. P1");
                    System.out.println("2. P2");
                    choice2 = scanner.nextInt();
                    if(choice2 == 1) {
                        C3P1();  
                    } else if (choice2 ==2) {
                        C3P2();
                    }
                    break;
                case 4:
                    System.out.println("1. P1");
                    System.out.println("2. P2");
                    choice2 = scanner.nextInt();
                    if(choice2 == 1) {
                        C4P1();  
                    } else if (choice2 ==2) {
                        C4P2();
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

    public static void C3P1 () { //answer to challenge 3 part 1 was 539637
        try{

            Integer answer = 0;
            File file = new File("C3file.txt");
            Scanner scanner = new Scanner(file);
            Map<Integer, String> fileMap = new HashMap<>();
            Map<Integer, List<Integer>> symbolMap = new HashMap<>();
            int count = 0;

            while (scanner.hasNextLine() || count == 140) {
                
                if(count != 140) {
                    List<Integer> temp = new ArrayList<>();
                    String data = scanner.nextLine();
                    fileMap.put(count,data);

                    for(int j = 0; j < data.length(); j++) {
                        if(data.charAt(j) != '.' && !Character.isDigit(data.charAt(j))){
                            temp.add(j);
                        }
                    }
                    symbolMap.put(count,temp);
                }
            
                if(count > 0){ 
                    Pattern pattern = Pattern.compile("\\d+");
                    Matcher matcher = pattern.matcher(fileMap.get(count-1));
                    
                    while (matcher.find()) {
                        String num = matcher.group();
                        int length = num.length() + 1;
                        int index = matcher.start();
                        boolean found = false;
                        for(int i = -2; i<1; i++) {
                            if(count + i == -1 || count + i == 140) {
                                continue;
                            }
                            for (Integer symbol : symbolMap.get(count + i)) {
                                if((symbol < index + length) && (symbol > index -2)){
                                    answer += Integer.parseInt(num);
                                    found = true;
                                    break;
                                }
                            }
                            if(found){
                                break;
                            }
                        }
                    }
                }
                count++;
            }
            scanner.close();

            System.out.println("Your answer: " + answer);     
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
      }
    }

    public static void C3P2 () { //answer to challenge 3 part 2 was 82818007
        try{

            Integer answer = 0;
            File file = new File("C3file.txt");
            Scanner scanner = new Scanner(file);
            Map<Integer, String> fileMap = new HashMap<>();
            Map<Integer, List<Pair<Integer, String>>> pairMap = new HashMap<>();
            int count = 0;

            while (scanner.hasNextLine() || count == 140) {
                
                if(count != 140) {
                    List<Pair<Integer, String>> temp = new ArrayList<>();
                    String data = scanner.nextLine();
                    fileMap.put(count,data);
                    Pattern pattern1 = Pattern.compile("\\d+");
                    Matcher matcher1 = pattern1.matcher(data);

                    while(matcher1.find()){
                        String num = matcher1.group();
                        int index = matcher1.start();
                        
                        temp.add(Pair.with(index,num));
                    }
                    pairMap.put(count,temp);
                }
            
                if(count > 0){ 
                    Pattern pattern = Pattern.compile("\\*");
                    Matcher matcher = pattern.matcher(fileMap.get(count-1)); 

                    while (matcher.find()) {
                        int index = matcher.start();
                        boolean found1 = false;
                        boolean found2 = false;
                        String num1 = "";
                        for(int i = -2; i<1; i++) {
                            if(count + i == -1 || count + i == 140) {
                                continue;
                            }
                            for (Pair pair : pairMap.get(count + i)) {
                                Integer nLength = ((String) pair.getValue1()).length();
                                Integer nIndex = (Integer) pair.getValue0();
                                for(int k = 0; k < nLength; k++) {
                                    if((nIndex + k < index + 2) && (nIndex + k > index - 2) && !found1){
                                        found1 = true;
                                        num1 = (String) pair.getValue1();
                                        break;
                                    }
                                    
                                    if((nIndex + k < index + 2) && (nIndex + k > index - 2) && found1 && !found2){
                                        answer += Integer.parseInt(num1) * Integer.parseInt(((String) pair.getValue1()));
                                        found2 = true;
                                        break;
                                    }
                                }
                                if(found2){
                                    break;
                                }
                            }
                            if(found2) {
                                break;
                            }
                        }
                    }
                }
                count++;
            }
            scanner.close();

            System.out.println("Your answer: " + answer);     
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
      }
    }

    public static void C4P1 () { //answer to challenge 4 part 1 was 24733
        try{

            Integer answer = 0;
            File file = new File("C4file.txt");
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                Integer sum = 0;
                String data = scanner.nextLine();
                String[] fstSplit = data.split(":");
                String[] sndSplit = fstSplit[1].split("\\|");
                List<List<String>> numsList = new ArrayList<>();
                    
                for(int i = 0; i < 2; i++) {
                    Pattern pattern = Pattern.compile("\\d+");
                    Matcher matcher = pattern.matcher(sndSplit[i]);
                    List<String> temp = new ArrayList<>();
                    
                    while (matcher.find()) {
                        String num = matcher.group();                        
                        temp.add(num);
                    }
                    numsList.add(temp);
                }

                for(String elem : numsList.get(0)){
                    for(String elem2 : numsList.get(1)){
                        if(Integer.parseInt(elem) == Integer.parseInt(elem2)){
                            if(sum == 0){
                                sum += 1;
                            } else {
                                sum = sum * 2;
                            }
                            break;
                        }
                    }
                }
                answer += sum;
            }
            scanner.close();
            System.out.println("Your answer: " + answer);     
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
      }
    }

    public static void C4P2 () { //answer to challenge 4 part 2 was 5422730
        try{
            Integer answer = 0;
            File file = new File("C4file.txt");
            Scanner scanner = new Scanner(file);
            Integer count = 1;
            Map<Integer, Integer> cardMap = new HashMap<>();
            
            for (int i = 1; i <= 202; i++) {
                cardMap.put(i, 1);
            }

            while (scanner.hasNextLine()) {
                Integer sum = 0;
                String data = scanner.nextLine();
                String[] fstSplit = data.split(":");
                String[] sndSplit = fstSplit[1].split("\\|");
                List<List<String>> numsList = new ArrayList<>();
                    
                for(int i = 0; i < 2; i++) {
                    Pattern pattern = Pattern.compile("\\d+");
                    Matcher matcher = pattern.matcher(sndSplit[i]);
                    List<String> temp = new ArrayList<>();
                    
                    while (matcher.find()) {
                        String num = matcher.group();                        
                        temp.add(num);
                    }
                    numsList.add(temp);
                }

                for(String elem : numsList.get(0)){
                    for(String elem2 : numsList.get(1)){
                        if(Integer.parseInt(elem) == Integer.parseInt(elem2)){
                                sum += 1;
                            break;
                        }
                    }
                }

                for(int j = 1; j <= cardMap.get(count); j++) {
                    for(int i = 1; i <= sum; i++) {
                            cardMap.put(count + i, cardMap.get(count + i) + 1);
                    }
                }

                answer += cardMap.get(count);
                count++;
            }

            scanner.close();
            System.out.println("Your answer: " + answer);     
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
      }
    }
}