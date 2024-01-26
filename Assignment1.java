import java.sql.Time;
import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Assignment1 
{
    public static int maxValue = 100000000;
    public static int numberOfThreads = 8;

    public static int nextValue = 0;
    public static ArrayList<Integer> primeNumbers = new ArrayList<Integer>();
    
    public static Lock valueLock = new ReentrantLock();
    public static Lock listLock = new ReentrantLock();

    public static int getNextValue()
    {
        int temp = 0;
        valueLock.lock();
        
        try
        {
            temp = ++nextValue;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            valueLock.unlock();
        }
        
        return temp;
    }

    public static void Add(int value)
    {
        listLock.lock();
        primeNumbers.add(value);
        listLock.unlock();
    }

	public static void main(String[] args)
	{
        long startTime = System.nanoTime();
        ArrayList<Thread> threads = new ArrayList<Thread>();
		
        for (int i = 0; i < numberOfThreads; i++) 
        {
			threads.add(new TestThread(maxValue));
			threads.get(i).start();
		}

        for (int i = 0; i < threads.size(); i++)
        {
            try
            { 
                threads.get(i).join();
            }
            catch(Exception ex)
            {
                System.out.println("Could not join thread " + i); 
            }
        }

        long sumOfPrimes = 0;
        for (int i = 0; i < primeNumbers.size(); i++)
            sumOfPrimes += primeNumbers.get(i);

        long endTime = System.nanoTime();

        System.out.print("<" + ((endTime - startTime) / 1000000000.0) + " seconds> <" + primeNumbers.size() + "> <" + sumOfPrimes + ">");
	}
}

class TestThread extends Thread 
{
    int maxValue = 0;
    int currValue = 0;

    public TestThread (int maxValue)
    {
        this.maxValue = maxValue;
    }

	public void run()
	{
        while (true)
        {
            currValue = Assignment1.getNextValue();
            
            if (currValue > maxValue)
                return;
            
            if (isPrime(currValue)) 
                Assignment1.Add(currValue);
        }
	}

    public boolean isPrime(int value)
    {
        double sqrt = Math.sqrt(value);
        
        for (int divisor = 2; divisor <= sqrt; divisor++)
            if (value % divisor == 0)
                return false;

        return true;
    }
}
