import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class HashTable {
    private String[] table;
    private final double LOAD_FACTOR;
    private int occupiedSize;
    private int numberOfCollisions;
    private int maximumCollisions;

    HashTable() {
        this.table = new String[31];
        LOAD_FACTOR = 0.5;
        occupiedSize = 0;
        numberOfCollisions = 0;
        maximumCollisions = 0;
    }


    private int hashFunction(String word) {
        int hashValue = 31;

        for (int i = 0; i < word.length(); i++) {
            hashValue += (i * word.charAt(i));
        }

        return hashValue % table.length;
    }

    private void rehash() {
        int nextPrime = findNextPrime(table.length * 2);
        String[] copyOfTable = new String[nextPrime];

        for (String s : table) {
            if (s != null) {
                int indx = hashFunction(s);
                copyOfTable[indx] = s;
            }
        }

        table = copyOfTable;
        maximumCollisions = Math.max(maximumCollisions, numberOfCollisions);
    }

    private int quadraticProbing(int indx, int squareIndex) {
        return (indx + (squareIndex*squareIndex)) % table.length;
    }

    void insertInHashTable(String word) {
        if (occupiedSize >= table.length * LOAD_FACTOR) {
            System.out.println("Array is half occupied with occupied size == " + occupiedSize);
            numberOfCollisions = 0;
            System.out.println("Rehashing ...\n");

            rehash();
        }

        int indx = hashFunction(word);
        int collisionIndex = 1;

        while (table[indx] != null) {
            System.out.println("Collision number: " + ++numberOfCollisions);
            indx = quadraticProbing(indx, collisionIndex++);
        }

        System.out.println("inserting the word " + "'" + word + "'" + " at index: " + indx);

        table[indx] = word;
        occupiedSize++;
    }

    private int findNextPrime(int num) {
        while (true) {
            if (isPrime(num)) return num;
            num++;
        }
    }

    private boolean isPrime(int num) {
        int index = 2;

        while (index < Math.sqrt(num)) {
            if (num % index == 0) return false;
            index++;
        }

        return true;
    }

    @Override
    public String toString() {
        StringBuilder hashtable = new StringBuilder();

        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                hashtable.append("key: ").append(i).append(" value: ").append(table[i]).append("\n");
            }
        }

        return hashtable.toString();
    }

    int size() {
        return table.length;
    }

    int getMaximumCollisions() {
        return maximumCollisions == 0 ? numberOfCollisions : maximumCollisions;
    }

    public static void main(String[] args) throws FileNotFoundException {
        HashTable hashTable = new HashTable();
        BufferedReader br = new BufferedReader(new FileReader("resources/words.txt"));

        try {
            String line = br.readLine();

            while (line != null) {
                hashTable.insertInHashTable(line);
                line = br.readLine();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("\n---------------\n");

        System.out.println("HashTable is as follows: \n" + hashTable);
        System.out.println("HashTable size == " + hashTable.size());
        System.out.println("Elements in the hash table == " + hashTable.occupiedSize);

        // Collision count is reset after each rehash
        // This is the maximum collisions that occurred before rehashing
        System.out.println("Maximum Collisions == " + hashTable.getMaximumCollisions());
    }
}
