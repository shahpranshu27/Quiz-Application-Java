import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Scanner;

class Question {
    private String question;
    private String[] options;
    private int correctOption;
    private int maxAttempts;
    private int attempts;

    public Question(String question, String[] options, int correctOption, int maxAttempts) {
        this.question = question;
        this.options = options;
        this.correctOption = correctOption;
        this.maxAttempts = maxAttempts;
        this.attempts = 0;
    }

    public String getQuestion() {
        return question;
    }

    public String[] getOptions() {
        return options;
    }

    public int getMaxAttempts() {
        return maxAttempts;
    }

    public int getAttempts() {
        return attempts;
    }

    public boolean isCorrect(int userChoice) {
        attempts++;
        return userChoice == correctOption;
    }
}

class Quiz {
    private Question[] questions;
    private int score;

    public Quiz(Question[] questions) {
        this.questions = questions;
        this.score = 0;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Quiz! Press any key to start or 'q' to quit.");
        String startOrQuit = scanner.nextLine();
        if (startOrQuit.equalsIgnoreCase("q")) {
            System.out.println("Quiz quit!");
            return;
        }

        int numberOfQuestions = 0;
        while(true){
            System.out.println("Enter number of questions: ");
            String userInput = scanner.nextLine();
            if(userInput.equalsIgnoreCase("q")){
                System.out.println("Quiz quit!");
                return;
            }

            try {
                numberOfQuestions = Integer.parseInt(userInput);
                if(numberOfQuestions >=1 && numberOfQuestions <= questions.length){
                    break;
                }
                else{
                    System.out.println("Enter a number between 1 and "+questions.length);
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input!");
            }

        }
        
        // List<Question> shuffledQuestions = shuffleQuestions();
        List<Question> shuffledQuestions = shuffleAndSelectQuestions(numberOfQuestions);
        for (Question question : shuffledQuestions) {
            System.out.println(question.getQuestion());
            String[] options = question.getOptions();
            List<Integer> optionIndexes = new ArrayList<>();
            for (int i = 0; i < options.length; i++) {
                optionIndexes.add(i);
            }
            // shuffleOptions(optionIndexes);

            for (int i = 0; i < optionIndexes.size(); i++) {
                System.out.println((i + 1) + ". " + options[optionIndexes.get(i)]);
            }
            int remainingAttempts = question.getMaxAttempts();
            while (remainingAttempts > 0) {
                System.out.print("Enter your choice: ");
                String userInput = scanner.nextLine();
                if (userInput.equalsIgnoreCase("q")) {
                    System.out.println("Quiz quit!");
                    System.out.println("Quiz completed! Your score: " + score + "/" + numberOfQuestions);
                    return;
                }
                int userChoice;
                try {
                    userChoice = Integer.parseInt(userInput);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input! Please enter a valid option or 'q' to quit.");
                    continue;
                }
                if (userChoice < 1 || userChoice > optionIndexes.size()) {
                    System.out.println("Invalid choice! Please enter a valid option or 'q' to quit.");
                    continue;
                }
                if (question.isCorrect(optionIndexes.get(userChoice - 1))) {
                    System.out.println("Correct!");
                    score++;
                    break;
                } else {
                    System.out.println("Incorrect! Remaining attempts: " + (--remainingAttempts));
                    if (remainingAttempts == 0) {
                        System.out.println("Out of attempts for this question!");
                    }
                }
            }
            System.out.println();
        }
        System.out.println("Quiz completed! Your score: " + score + "/" + numberOfQuestions);
        scanner.close();
    }

    private List<Question> shuffleQuestions() {
        
        List<Question> shuffledQuestions = new ArrayList<>();
        for (Question question : questions) {
            shuffledQuestions.add(question);
        }
        Collections.shuffle(shuffledQuestions);
        return shuffledQuestions;

    }

    private List<Question> shuffleAndSelectQuestions(int numQuestions) {
        List<Question> shuffledQuestions = new ArrayList<>();
        for (Question question : questions) {
            shuffledQuestions.add(question);
        }
        Collections.shuffle(shuffledQuestions);
        return shuffledQuestions.subList(0, numQuestions);
    }

}

public class Main {
    public static void main(String[] args) {

        Question[] questions = {
                new Question("What is the capital of France?",
                        new String[]{"Paris", "Berlin", "London", "Rome"}, 0, 2),
                new Question("Who wrote 'To Kill a Mockingbird'?",
                        new String[]{"Harper Lee", "Mark Twain", "J.K. Rowling", "Stephen King"}, 0, 2),
                new Question("Which country is known as the 'Land of the Rising Sun'?",
                        new String[]{"China", "Japan", "Korea", "Vietnam"}, 1, 2),
                new Question("Who painted the Mona Lisa?",
                        new String[]{"Leonardo da Vinci", "Pablo Picasso", "Vincent van Gogh", "Michelangelo"}, 0, 3),
                new Question("What is the largest ocean on Earth?",
                        new String[]{"Atlantic Ocean", "Indian Ocean", "Arctic Ocean", "Pacific Ocean"}, 3, 2),
                new Question("Which gas is most abundant in Earth's atmosphere?",
                        new String[]{"Nitrogen", "Oxygen", "Carbon Dioxide", "Argon"}, 0, 3),
                new Question("What is the chemical symbol for gold?",
                        new String[]{"Au", "Ag", "Fe", "Cu"}, 0, 3),
                new Question("What is the main ingredient in guacamole?",
                        new String[]{"Tomato", "Onion", "Avocado", "Lemon"}, 2, 3),
                new Question("Who was the first woman to win a Nobel Prize?",
                        new String[]{"Marie Curie", "Rosalind Franklin", "Mother Teresa", "Ada Lovelace"}, 0, 3),
                new Question("What is the largest organ in the human body?",
                        new String[]{"Liver", "Heart", "Skin", "Brain"}, 0, 3),
                new Question("What is the capital of Canada?",
                        new String[]{"Toronto", "Vancouver", "Ottawa", "Montreal"}, 2, 3),
                new Question("Who developed the theory of relativity?",
                        new String[]{"Isaac Newton", "Albert Einstein", "Stephen Hawking", "Galileo Galilei"}, 1, 3),
                new Question("Which country is known as the 'Land of Fire and Ice'?",
                        new String[]{"Norway", "Finland", "Iceland", "Sweden"}, 2, 3),
                new Question("What is the chemical symbol for water?",
                        new String[]{"Wa", "Wt", "Wt", "H2O"}, 3, 3),
                new Question("What is the smallest prime number?",
                        new String[]{"2", "3", "1", "0"}, 0, 3),
                new Question("Who wrote 'Pride and Prejudice'?",
                        new String[]{"Charles Dickens", "Jane Austen", "Leo Tolstoy", "Emily Brontë"}, 1, 3),
                new Question("What is the largest living bird?",
                        new String[]{"Ostrich", "Emu", "Albatross", "Condor"}, 0, 3),
                new Question("What is the smallest unit of computer memory?",
                        new String[]{"Byte", "Bit", "Nibble", "Megabyte"}, 1, 3),
                new Question("Who is known as the father of computing?",
                        new String[]{"Bill Gates", "Alan Turing", "Steve Jobs", "Charles Babbage"}, 3, 3),
                new Question("What does 'www' stand for in a website browser?",
                        new String[]{"World Wide Web", "Web World Wide", "Wide Web World", "World Web Wide"}, 0, 3),
                new Question("Who is the current Prime Minister of India?",
                        new String[]{"Narendra Modi", "Manmohan Singh", "Rahul Gandhi", "Amit Shah"}, 0, 3),
                new Question("Where did the 2021 United Nations Climate Change Conference (COP26) take place?",
                        new String[]{"New York", "London", "Glasgow", "Paris"}, 2, 3),
                new Question("Which country won the most gold medals in the Tokyo 2020 Olympics?",
                        new String[]{"United States", "China", "Japan", "Russia"}, 0, 3),
                new Question("What is the capital of Australia?",
                        new String[]{"Sydney", "Melbourne", "Canberra", "Brisbane"}, 2, 3),
                new Question("Who is the current President of the United States?",
                        new String[]{"Barack Obama", "Joe Biden", "Donald Trump", "Hillary Clinton"}, 1, 3),
                new Question("Which country shares the longest land border with India?",
                        new String[]{"China", "Pakistan", "Bangladesh", "Nepal"}, 2, 3),
                new Question("Who was the first President of independent India?",
                        new String[]{"Jawaharlal Nehru", "Sardar Vallabhbhai Patel", "Dr. Rajendra Prasad", "B.R. Ambedkar"}, 2, 3),
                new Question("Which Mughal emperor built the Taj Mahal?",
                        new String[]{"Akbar", "Jahangir", "Shah Jahan", "Aurangzeb"}, 2, 3),
                new Question("Who led the Salt March during the Indian independence movement?",
                        new String[]{"Mahatma Gandhi", "Jawaharlal Nehru", "Subhas Chandra Bose", "Sardar Vallabhbhai Patel"}, 0, 3),
                new Question("Who is the goddess of wealth in Hindu mythology?",
                        new String[]{"Lakshmi", "Saraswati", "Parvati", "Durga"}, 0, 3),
                new Question("Who is considered the author of the Hindu epic Ramayana?",
                        new String[]{"Valmiki", "Vyasa", "Tulsidas", "Kalidasa"}, 0, 3),
                new Question("Who wrote 'The Diary of a Young Girl'?",
                    new String[]{"Anne Frank", "Harper Lee", "J.K. Rowling", "Mark Twain"}, 0, 3),
                new Question("Which book is known for the concept of 'The Law of Attraction'?",
                    new String[]{"The Secret", "How to Win Friends and Influence People", "Think and Grow Rich", "The Power of Now"}, 0, 3),
                new Question("Which author wrote 'Sapiens: A Brief History of Humankind'?",
                    new String[]{"Yuval Noah Harari", "Dan Brown", "Haruki Murakami", "Agatha Christie"}, 0, 3),
                new Question("What is the chemical symbol for the element Iron?",
                    new String[]{"Fe", "I", "In", "Ir"}, 0, 3),
                new Question("What is the freezing point of water in degrees Celsius?",
                    new String[]{"0°C", "100°C", "32°C", "-273°C"}, 3, 3),
                new Question("Who is considered the father of modern chemistry?",
                    new String[]{"Marie Curie", "Antoine Lavoisier", "Dmitri Mendeleev", "Louis Pasteur"}, 1, 3),
                new Question("What is the study of earthquakes called?",
                    new String[]{"Meteorology", "Seismology", "Botany", "Anthropology"}, 1, 3),
                new Question("Who is known as the Iron Man of India?",
                    new String[]{"Jawaharlal Nehru", "Sardar Vallabhbhai Patel", "Subhas Chandra Bose", "Mahatma Gandhi"}, 1, 3),
                new Question("Who was the first woman President of the Indian National Congress?",
                    new String[]{"Indira Gandhi", "Sarojini Naidu", "Annie Besant", "Sonia Gandhi"}, 1, 3),
                new Question("What was the slogan coined by Lal Bahadur Shastri during the Indo-Pak war of 1965?",
                    new String[]{"Jai Jawan, Jai Kisan", "Inquilab Zindabad", "Sarvodaya", "Jai Hind"}, 0, 3),
                new Question("Who was the Prime Minister of India during the 1999 Kargil War?",
                    new String[]{"Atal Bihari Vajpayee", "Rajiv Gandhi", "Manmohan Singh", "Indira Gandhi"}, 0, 3),
                // new Question("What is the capital of New Zealand?",
                //     new String[]{"Wellington", "Auckland", "Christchurch", "Sydney"}, 1, 3),
                // new Question("Which city is known as the 'City of Love'?",
                //     new String[]{"Paris", "Rome", "Venice", "Florence"}, 1, 3),
                // new Question("What is the currency of Japan?",
                //     new String[]{"Yen", "Dollar", "Euro", "Pound"}, 1, 3),
                // new Question("Which sport does Usain Bolt compete in?",
                //     new String[]{"Sprinting", "Swimming", "Tennis", "Football"}, 1, 3),
                // new Question("Which country won the FIFA World Cup in 2018?",
                //     new String[]{"France", "Brazil", "Germany", "Argentina"}, 1, 3),
                // new Question("Who won the Wimbledon Men's Singles in 2021?",
                //     new String[]{"Roger Federer", "Novak Djokovic", "Rafael Nadal", "Andy Murray"}, 2, 3),
                // new Question("Which sport uses the term 'Love' to represent a score of zero?",
                //     new String[]{"Tennis", "Basketball", "Soccer", "Golf"}, 0, 3),
                // new Question("What is the longest river in the world?",
                //     new String[]{"Nile", "Amazon", "Yangtze", "Mississippi"}, 1, 3),
                

        };

        Quiz quiz = new Quiz(questions);
        quiz.start();
    }
}