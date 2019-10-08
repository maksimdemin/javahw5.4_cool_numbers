import java.util.*;

public class Main {

    private static ArrayList<String> coolNumbers = new ArrayList<>(); // создали множество ArrayList
    private static HashSet<String> coolNumbersHashSet = new HashSet<>(); // создали множество HashSet
    private static TreeSet<String> coolNumbersTreeSet = new TreeSet<>(); // создали множество TreeSet


    public static void main(String[] args) {

        System.out.println("Wait.......");
        generatorNumber();
        Collections.sort(coolNumbers);
        coolNumbersHashSet.addAll(coolNumbers); // копирование множества номеров HashSet
        coolNumbersTreeSet.addAll(coolNumbers); // копирование множества номеров TreeSet

        Scanner scanner = new Scanner(System.in);

        while (true) {

            System.out.println("Enter your number for search: ");
            String enterNumber = scanner.nextLine().replaceAll("[^А-я\\d]+", "").toUpperCase().trim();
            if (enterNumber.matches("^([АВЕКМНОРСТУХ])(\\d{3})([АВЕКМНОРСТУХ]{2})(\\d{2,3})$")) {
            searchDirect(enterNumber);
            searchBinary(enterNumber);
            searchHashSet(enterNumber);
            searchTreeSet(enterNumber);
            }
            else
                System.out.println("Invalid enter number. Try again.");
        }
    }


    private static void generatorNumber() { // метод генерации блатных госномеров (одинаковые буквы, цифры 001-999, регион 01-199)
        String[] chars = new String[]{"А", "В", "Е", "К", "М", "Н", "О", "Р", "С", "Т", "У", "Х"};
        for (int region = 1; region <= 199; region++) {
            for (String wordForNumber : chars) {
                for (int numbers = 1; numbers <= 999; numbers++) {
                    coolNumbers.add(String.format("%s%03d%s%s%02d", wordForNumber, numbers, wordForNumber, wordForNumber, region));
                }
            }
        }
        System.out.println("Total generated " + coolNumbers.size() + " numbers");
    }


    private static void searchDirect(String enterNumber) { // метод поиска номера прямым переором
            long startTime = System.nanoTime();
        for (int i = 0; i < coolNumbers.size(); i++) {
            boolean isFound = coolNumbers.contains(enterNumber);
            long finishTime = System.nanoTime() - startTime;
            String nameMethodSearch = "Direct ";
            printFoundText(nameMethodSearch, enterNumber, isFound, finishTime);
            return;
        }
    }


    private static void searchBinary(String enterNumber) { // метод поиска номера бинарным способом
            long startTime = System.nanoTime();
            boolean isFound = Collections.binarySearch(coolNumbers, enterNumber) >= 0;
            long finishTime = System.nanoTime() - startTime;
            String nameMethodSearch = "Binary ";
            printFoundText(nameMethodSearch, enterNumber, isFound, finishTime);
    }


    private static void searchHashSet(String enterNumber) { // метод поиска номера с использованием HashSet
            long startTime = System.nanoTime();
            boolean isFound = coolNumbersHashSet.contains(enterNumber);
            long finishTime = System.nanoTime() - startTime;
            String nameMethodSearch = "HashSet";
            printFoundText(nameMethodSearch, enterNumber, isFound, finishTime);
    }


    private static void searchTreeSet(String enterNumber) { // метод поиска номера с использованием HashSet
            long startTime = System.nanoTime();
            boolean isFound = coolNumbersTreeSet.contains(enterNumber);
            long finishTime = System.nanoTime() - startTime;
            String nameMethodSearch = "TreeSet";
            printFoundText(nameMethodSearch, enterNumber, isFound, finishTime);
    }

    private static void printFoundText(String nameMethodSearch, String enterNumber, boolean isFound, long finishTime) { // метод печати информаци о найденом номере
             String isFoundForTextPrint = isFound ? "found" : "don't found";
             System.out.printf("Number %s %s by %s search, time spent   - %d ns.%n", enterNumber, isFoundForTextPrint, nameMethodSearch, finishTime);

    }

}