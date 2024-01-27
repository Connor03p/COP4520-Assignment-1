import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.ArrayList;
import java.nio.file.Path;

public class Assignment1 
{
    public static final int maxValue = 100000000;
    public static final int numberOfThreads = 8;

    private static double time;
    private static ArrayList<Thread> threads = new ArrayList<Thread>();
    
	public static void main(String[] args)
	{
        time = System.nanoTime();
		
        // Create threads
        for (int i = 0; i < numberOfThreads; i++) 
        {
			threads.add(new PrimeThread());
			threads.get(i).start();
		}

        // Wait for threads to finish
        for (int i = 0; i < threads.size(); i++)
        {
            try {
                threads.get(i).join();
            }
            catch (InterruptedException e) {
                System.out.println("Failed to join thread");
                e.printStackTrace();
            }
        }

        // Calculate total time
        time = ((System.nanoTime() - time) / 1000000000.0);

        // Sort prime numbers
        SharedData.primeNumbers.sort(null);

        // Get sum of primes
        long sumOfPrimes = 0;
        for (int i = 0; i < SharedData.primeNumbers.size(); i++)
            sumOfPrimes += SharedData.primeNumbers.get(i);

        // Write output to file
        try {
            printOutput(time, SharedData.primeNumbers.size(), sumOfPrimes);
        }
        catch (IOException e) {
            System.out.println("Failed to write file");
            e.printStackTrace();
        }
	}

    private static void printOutput(double totalTime, int numberOfPrimes, long sumOfPrimes) throws IOException
    {
        String output = "< " + totalTime + " seconds > < " + SharedData.primeNumbers.size() + " > < " + sumOfPrimes + " >\n<";
        
        int startIndex = Math.max(numberOfPrimes - 10, 0);
        int endIndex = numberOfPrimes - 1;

        for (int i = startIndex; i <= endIndex; i++)
            output += " " + SharedData.primeNumbers.get(i);
        
        output += " >";
        Path filepath = FileSystems.getDefault().getPath("output.txt");
        Files.writeString(filepath, output);
    }
}