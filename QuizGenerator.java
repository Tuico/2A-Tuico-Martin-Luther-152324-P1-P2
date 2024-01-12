import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

//Main ni siya
public class QuizGenerator {
    private final List<Question> questions;
    private final List<Question> newQuestions;
    private ArrayList<String> studentInfo = new ArrayList<>();
    private ArrayList<Integer> studentScore = new ArrayList<>();
    private int id;
    private final String adminUsername = "admin";
    private final String adminPassword = "password";
    private final Scanner scanner;
    //change the file location where you want your txt file saved, ayaw kalimot ug butang sa \\ sa last para di mu error ty
    private final String fileLocation = "C:\\Users\\Asus\\Desktop\\Java\\Project\\Quiz Generator\\list\\";//copy rah ang path sa folder nga gi himu nimo
    final File folder = new File(fileLocation);

    public QuizGenerator() {
        questions = new ArrayList<>();
        newQuestions = new ArrayList<>();
        scanner = new Scanner(System.in);
    }
    //Ari mu run ang atung code
    public static void main(String[] args) {
        QuizGenerator quiz = new QuizGenerator();
        // Given questions
        McQ q1 = new McQ("What does the term \"encapsulation\" refer to in OOP?",
            Arrays.asList("Binding data with methods", "Hiding implementation details", "Reusing code", "Allowing multiple inheritances"), 
            2, "OOP");

        McQ q2 = new McQ("Which of the following is NOT a fundamental principle of OOP?",
            Arrays.asList("Abstraction", "Encapsulation", "Structuring", "Inheritance"), 
            3, "OOP");

        McQ q3 = new McQ("In Java, which keyword is used to implement inheritance between classes?",
            Arrays.asList("extends", "inherits", "implements", "extendsClass"), 
            1, "OOP");

        McQ q4 = new McQ("What is the process of defining a new class based on an existing class called in OOP?",
            Arrays.asList("Inheritance", "Polymorphism", "Abstraction", "Encapsulation"), 
            1, "OOP");

        McQ q5 = new McQ("Which OOP principle refers to the ability of a class to have multiple methods with the same name but different implementations?",
            Arrays.asList("Inheritance", "Polymorphism", "Encapsulation", "Abstraction"), 
            2, "OOP");

        McQ q6 = new McQ("Which keyword is used in Java to signify a class cannot be inherited?",
            Arrays.asList("final", "static", "abstract", "private"), 
            1, "Java");

        McQ q7 = new McQ("What is the purpose of the \"static\" keyword in Java?",
            Arrays.asList("It denotes that a variable is constant and cannot be changed.", "It indicates that a method belongs to the class rather than an instance.", " It specifies a class that cannot be instantiated.", " It enables polymorphism in Java."), 
            2, "Java");

        McQ q8 = new McQ("In Java, which of the following is used to implement multiple inheritance?",
            Arrays.asList("Interfaces", "Abstract classes", "Private inheritance", "Superclasses"), 
            1, "Java");
        
        McQ q9 = new McQ("What is the default value of a variable of type int in Java if it's not explicitly initialized?",
            Arrays.asList("0", "1", "-1", "null"), 
            1, "Java");
        
        McQ q10 = new McQ("Which of the following statements is true about Java Garbage Collection?",
            Arrays.asList("Java requires explicit memory deallocation using free() function.", "Garbage Collection ensures memory leaks never occur in Java.", "Developers have no control over when Garbage Collection occurs.", "It exclusively targets only unused objects in the heap."), 
            2, "Java");
        
        quiz.addQuestion(q1);
        quiz.addQuestion(q2);
        quiz.addQuestion(q3);
        quiz.addQuestion(q4);
        quiz.addQuestion(q5);
        quiz.addQuestion(q6);
        quiz.addQuestion(q7);
        quiz.addQuestion(q8);
        quiz.addQuestion(q9);
        quiz.addQuestion(q10);

        quiz.displayMenu();
    }
    public void displayMenu() {
        boolean exit = false;
        //mag looping mahuman rani ug mu exit nah which is enter 0
        while (!exit) {
            clearScreen();
            System.out.println("Welcome to the Quiz Generator!");
            System.out.println("What is your role?");
            System.out.println("[1]-Student");
            System.out.println("[2]-Teacher");
            System.out.println("[3]-Exit");
            
            int choice = getUserInput(3);

            switch (choice) {
                
                case 1:
                    clearScreen();
                    studentMenu();
                    break;
                case 2:
                    clearScreen();
                    teacherMenu();
                    break;
                case 3:
                    exit = true;
                    System.out.println("Exiting the Quiz Generator. Goodbye!");
                    break;
            }
        }
    }
    //method ni sa teacher 
    public void teacherMenu() {
        System.out.println("Teacher Login");
        System.out.print("Enter username: ");
        String usernameInput = scanner.nextLine();
        System.out.print("Enter password: ");
        String passwordInput = scanner.nextLine();

        if (validateCredentials(usernameInput, passwordInput)) {
            boolean exit = false;
            while(!exit){
                clearScreen();
                System.out.println("Login successful! Welcome, Professor.");
                System.out.println("[1]Add quiz");
                System.out.println("[2]Student records");
                System.out.println("[3]Log out");
                int choice = getUserInput(3);
                switch (choice) {
                    
                    case 1:
                        clearScreen();
                        addNewQuestion();
                        break;
                    case 2:
                        clearScreen();
                        printStudentRecords(folder);
                        break;
                    case 3:
                        clearScreen();
                        displayMenu();
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter a number between 1 and 3.");
                }
                promptEnterKey();
            }
        } else {
            clearScreen();
            System.out.println("Invalid credentials. Login failed.");
            promptEnterKey();
        }
    }
    //mu check if ang gi input nga username ug password kay sakto bah
    private boolean validateCredentials(String username, String password) {
        return username.equals(adminUsername) && password.equals(adminPassword);
    }
    //method sa student 
    public void studentMenu(){
        System.out.println("[Please enter your Student information]");
        System.out.print("CTU Student Id: ");//mangayp sa CTU id
        id = getCTUid(8000000, 8229999);//mu identify if valib bah nga CTU id
        scanner.nextLine();
        checkAllFile(folder, id);//check sa file if naa nabay nah record nga same ug CTU id
        System.out.print("Full Name: ");//if wala mu out siya ato then mangayo siyag info sa student
        String name = scanner.nextLine();
        System.out.print("Section: ");
        String section = scanner.nextLine();
        System.out.print("Course: ");
        String course = scanner.nextLine();
        
        //mu add sa student info sa array list
        studentInfo.add(0, name);
        studentInfo.add(1, section);
        studentInfo.add(2, course);
        studentScore.add(0);// mu add ug default value sa quiz score
        studentScore.add(0);// if wala gani ni mu earror ang StudentRecord method martin nah bahala mu explain  
        studentScore.add(0);
        studentScore.add(0);
        studentScore.add(0);
        studentScore.add(0);
        studentScore.add(0);
        studentScore.add(0);
        
        StudentRecord(id, studentScore, studentInfo);
        promptEnterKey();
        displayTopicSelection();
        
    }
    private int getUserInput(int max) {
        int userAnswer = -1;
        boolean validInput = false;
        //Mu ask ug input if valid input mu out sa while loop if not valid mag sig looping 
        while (!validInput) {
            try {
                System.out.print("Enter (1-" + max + "): ");
                userAnswer = scanner.nextInt();
                if (userAnswer < 1 || userAnswer > max) {
                    throw new InputMismatchException();
                }
                validInput = true;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number between 1 and " + max + ".");
                scanner.nextLine(); // Clear the invalid input
            }
        }
        scanner.nextLine(); // Consume newline
        return userAnswer;
    }
    public int getCTUid(int min, int max) {
        int userAnswer = -1;
        boolean validInput = false;
    
        while (!validInput) {
            
            try {
                userAnswer = scanner.nextInt();
    
                if (userAnswer < min || userAnswer > max) {
                    throw new InputMismatchException();
                }
    
                validInput = true;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input, that is not a valid CTU id");
                scanner.nextLine(); // Clear the invalid input
                System.out.print("Try again: ");
            }
            
        }
        // scanner.nextLine(); // Consume newline
        return userAnswer;
    }

    public void displayTopicSelection() {
        clearScreen();
        System.out.println("Hi "+studentInfo.get(0)+" please select a topic:");
        System.out.println("[1]-Object-Oriented Programming (OOP)");
        System.out.println("[2]-Java Basics");
        System.out.println("[3]-Science");
        System.out.println("[4]-Math");
        System.out.println("[5]-Back to Menu");
        //Add ug topic ari if naa pa 

        int choice = getUserInput(5); 
        //Mag pick ug topic 
        switch (choice) {
            
            case 1:
                displayQuizByTopic("OOP");
                break;
            case 2:
                
                displayQuizByTopic("Java");
                break;
            case 3:
                
                displayQuizByTopic("Science");
                break;
            case 4:
                
                displayQuizByTopic("Math");
                break;
            case 5:
                studentInfo.clear();
                studentScore.clear();
                displayMenu();
                break;
        }
        //silbe murag rag looping ni siya kay after sa operation sa babaw mu balik ang kani nga functiom
        displayTopicSelection();
    }
    // Display base rah sa topic nga gi pili
    // mura nig filter :)
    public void displayQuizByTopic(String selectedTopic) {
        List<Question> selectedQuestions = getQuestionsByTopic(selectedTopic);
        
        if (selectedQuestions.isEmpty()) {
            //if wala gani question ang gi pili nga topic mu print ni 
            System.out.println("No questions found for the selected topic: " + selectedTopic);
            promptEnterKey();
        } else {
            clearScreen();
            displayQuiz(selectedQuestions, selectedTopic);
        }
    }

    // Function to filter questions by the selected topic
    private List<Question> getQuestionsByTopic(String selectedTopic) {
        List<Question> selectedQuestions = new ArrayList<>();
        for (Question q : questions) {
            if (q.getTopic().equals(selectedTopic)) {
                selectedQuestions.add(q);
            }
        }
        return selectedQuestions;
    }

    
    // display tanan quiz mag depend sa topic nga gi selected
    private void displayQuiz(List<Question> selectedQuestions, String selectedTopic) {
        int score = 0;
        int n=1;
        for (Question q : questions) {
            //Mu display sa Question
            if (q.getTopic().equals(selectedTopic)) {
                System.out.println("Q"+ n +") " + q.getQuestion()+"\n");

                List<String> options = q.getOptions();
                for (int i = 0; i < options.size(); i++) {
                    //Mu display sa Options
                    System.out.println((i + 1) + ". " + options.get(i));
                }

                int userAnswer = getUserInput(options.size());
                // Mu check sa answer
                if (userAnswer == q.getCorrectOption()) {
                    System.out.println("Correct!\n");
                    score++;
                } else {
                    System.out.println("Incorrect. The correct answer was: " + q.getOptions().get(q.getCorrectOption() - 1) + "\n");
                }
                n++;
            }
        }
        int totalScore = n-1;
        updateScore(score, totalScore, selectedTopic);
        
        clearScreen();
        System.out.println("Quiz ended! Your score is: " + score + " out of " + (n-1));
        
        promptEnterKey();
    }
    // mu save sa student info and i save niya sa file
    private void StudentRecord(int id,ArrayList<Integer> score, ArrayList<String> info) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileLocation+id+".txt"));
            // mag write sa file
            writer.write("ID NO.    :  " + id+ "\n");
            writer.write("Full Name : " + info.get(0) + "\n");
            writer.write("Section   : " + info.get(1) + "\n");
            writer.write("Course    : " + info.get(2) + "\n");
            writer.write("      [Quiz Score]\n"); 
            writer.write("Topic     |   Score\n");
            writer.write("OOP       : "+ score.get(0) +"/"+score.get(1)+"\n");
            writer.write("Java      : "+ score.get(2) +"/"+score.get(3)+"\n");
            writer.write("Science   : "+ score.get(4) +"/"+score.get(5)+"\n");
            writer.write("Math      : "+ score.get(6) +"/"+score.get(7)+"\n");
            writer.write("---------------\n"); // Separator for different entries

            writer.close();
            System.out.println("User information successfully recorded.");
        } catch (Exception e) {
            clearScreen();
            System.out.println("Writer error");
            promptEnterKey();
            studentMenu();
        }     
    }
    //Print sa student records
    private void StudentReader(int id, ArrayList<Integer> score, ArrayList<String> info) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileLocation + id + ".txt"));
            String line;
            int lineCount = 0;
            info.clear();
            while ((line = reader.readLine()) != null) {
                lineCount++;
                if (lineCount == 1) {
                    continue;// skip ang first line kay id info rmn nah
                } else if (lineCount >= 2 && lineCount <= 4) {
                    // iya kuhaon ang info sa student Name, section, course and ibutang sa array list info
                    info.add(line.split(":\\s+")[1].trim());
                    //iya i trim ang line sa text by string then ang istore ra kay ang index 1 which is ang full name sa student
                } else if (lineCount >= 7 && lineCount <= 10) {
                    // iya iget ang current score sa student if wala pa ang mu gawas kay default mao tong 0
                    String[] parts = line.split(":\\s+")[1].split("/");
                    //same procedure ari iya itrim then iget nato base sa index
                    score.add(Integer.parseInt(parts[0].trim()));
                    score.add(Integer.parseInt(parts[1].trim()));
                }
            }
            reader.close();
        } catch (Exception e) {
            System.out.println("Reader error");
        }
    }
    //mao ni mu check sa file name then iprint niya
    private void checkStudentFile(String id){
        
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileLocation + id));
            String line;
            //nag looping  if ang text file gani ay naay sulod or dili siya null mu run ang loop
            while((line = reader.readLine()) != null) {
            System.out.println(line);
            }
            //mu exit ra sa loop if naa nah sa last nga line 
            reader.close();
        } catch (Exception e) {
            System.out.println("Reader error");
        }
    }
    //mu print sa student records tanan one by one
    private void printStudentRecords(final File folder) {
        System.out.println("\t\tSTUDENT RECORDS");
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                printStudentRecords(fileEntry);
            } else {
                checkStudentFile(fileEntry.getName()); //
            }
        }
        
    }
    //kani mu check sa file
    private void checkAllFile(final File folder,int id){
        //mu loop sa tanan file with in the directory or folder
        for (final File fileEntry : folder.listFiles()) {
            
            if (fileEntry.getName().equals(id+".txt")) {
                //if id nga gi input kay naa sa folder naa mu run ni
                System.out.println(id+" already has a record");
                //mao nani siya mu get sa information sa student sulod sa text file
                StudentReader(id, studentScore, studentInfo);
                promptEnterKey();
                displayTopicSelection();
            }
        }
    }
    //update sa score bases sa selected topic and irecord niya sa student file
    public void updateScore(int score, int totalScore, String selectedTopic) {
        if(selectedTopic.equals("OOP")){
            studentScore.set(0, score);
            studentScore.set(1, totalScore);
            StudentRecord(id, studentScore, studentInfo);
        }else if(selectedTopic.equals("Java")){
            studentScore.set(2, score);
            studentScore.set(3, totalScore);
            StudentRecord(id, studentScore, studentInfo);
        }else if(selectedTopic.equals("Science")){
            studentScore.set(4, score);
            studentScore.set(5, totalScore);
            StudentRecord(id, studentScore, studentInfo);
        }else if(selectedTopic.equals("Math")){
            studentScore.set(6, score);
            studentScore.set(7, totalScore);
            StudentRecord(id, studentScore, studentInfo);
        }
    }
    
    //Mag kuha sa max options para di mu lapas 
    
    public void addNewQuestion() {
        String[] availableTopics = {"Math", "Science", "OOP", "Java"};

        System.out.println("\tAdding a new question");
        System.out.println("Available Topics:");
        for (int i = 0; i < availableTopics.length; i++) {
            System.out.println((i + 1) + ". " + availableTopics[i]);
        }
        int topic = getUserInput(4);
        String selectedTopic = availableTopics[topic-1];
        System.out.println("How many questions would you like to add?");
        int numOfQuestion = getUserInput(20);
        for (int i = 0; i < numOfQuestion; i++) {
            System.out.print("Enter the question for number "+(i+1)+": \n ");
            String question = scanner.nextLine();

            List<String> options = new ArrayList<>();
            for (int j = 1; j <= 4; j++) {
                System.out.print("Enter option " + j + ": ");
                String option = scanner.nextLine();
                options.add(option);
            }
            System.out.print("Please select the correct answer ");
            int correctOption = getUserInput(4);

            //add sa newQuestion nga list
            Question newMCQ = new McQ(question, options, correctOption, selectedTopic);
            newQuestions.add(newMCQ);
        }
        mergeNewQuestions();
        System.out.println("New question added successfully to the topic: " + selectedTopic + "\n");
    }
    
    //Mu add ug Questions adto sa list Question
    public void addQuestion(Question question) {
        questions.add(question);
    }
    public void displayNewQuestions() {
        
        if (newQuestions.isEmpty()) {
            System.out.println("No new questions have been added.");
        } else {
            System.out.println("Newly added questions:");
            int count = 1;
            for (Question q : newQuestions) {
                System.out.println("Question " + count + ": " + q.getQuestion() + " [Topic: " + q.getTopic() + "]");
                count++;
            }
        }
        promptEnterKey();
    }
    
    //For merging 
    public void mergeNewQuestions() {
        System.out.println("Do you want to merge new questions?");
        System.out.println("[1]yes or [2]no");
        int answer = getUserInput(2);
        if(answer == 1) {
            // add ang tanan newly add question sa list of Questions
            questions.addAll(newQuestions);
            newQuestions.clear(); //mu clear sa new questions
            promptEnterKey();
        }
    }
    public void promptEnterKey() {
        System.out.print("Press \"ENTER\" to continue...");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        clearScreen();
    }
    public static void clearScreen() {
        //mu clear sa screen para limpyo tanawon 
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
