import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Exercise {
    // Використовуємо мапи та сети для більшої ефективності (хоча в цій задачі це лишнє)
    // Це зменшує час пошуку з O(n) до O(1)
    private static final String EN_ALPHABET_STR = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private static final Map<Character, Integer> EN_INDEX = createIndexMap(EN_ALPHABET_STR);

    private static final Set<Character> EN_ALPHABET = EN_INDEX.keySet();

    public static String vigenereEncode(String text, String key) {
        return processEncode(text, key);
    }

    // Основний метод для шифрування
    private static String processEncode(String text, String key) {
        StringBuilder result = new StringBuilder(); // Використовуємо StringBuilder для можливої зміни рядків
        key = key.toUpperCase(); // Приводимо ключ до верхнього регістру відповідно алфавітам
        int j = 0;
        int keyLength = key.length();

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            boolean isLowerCase = Character.isLowerCase(c);
            char upperC = Character.toUpperCase(c); // Приводимо символ до верхнього регістру для обробки

            Map<Character, Integer> alphabetIndex = getAlphabetIndex(upperC); // Визначаємо, до якого алфавіту належить символ
            if (alphabetIndex != null) {
                int textIndex = alphabetIndex.get(upperC);
                char keyChar = key.charAt(j % keyLength);
                int keyIndex = alphabetIndex.get(keyChar);
                int alphabetLength = alphabetIndex.size();


                int newIndex = (textIndex + keyIndex) % alphabetLength; // Обчислюємо новий індекс символу для шифрування
                char newChar = getCharByIndex(alphabetIndex, newIndex);

                result.append(isLowerCase ? Character.toLowerCase(newChar) : newChar);// Якщо вхідний символ був у нижньому регістрі, повертаємо його у нижній регістр
                j++;
            } else {
                result.append(c); // Залишаємо без змін все що не налижить алфавіту
            }
        }
        return result.toString();
    }

    // Створення мапи символів алфавіту та їх індексів
    private static Map<Character, Integer> createIndexMap(String alphabet) {
        Map<Character, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < alphabet.length(); i++) {
            indexMap.put(alphabet.charAt(i), i);
        }
        return indexMap;
    }

    // Визначаєм, що символ належить алфавіту
    private static Map<Character, Integer> getAlphabetIndex(char c) {
        if (EN_ALPHABET.contains(c)) return EN_INDEX;
        return null;
    }

    // Отримуєм символ за індексом у алфавіті
    private static char getCharByIndex(Map<Character, Integer> alphabetIndex, int index) {
        for (Map.Entry<Character, Integer> entry : alphabetIndex.entrySet()) {
            if (entry.getValue() == index) {
                return entry.getKey();
            }
        }
        return '!'; // Помилка, не має трплятися при вірних вводних
    }

}