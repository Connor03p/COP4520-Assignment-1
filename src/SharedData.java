import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SharedData
{
    public static int nextValue = 0;
    public static Lock valueLock = new ReentrantLock();

    public static ArrayList<Integer> primeNumbers = new ArrayList<Integer>();
    public static Lock listLock = new ReentrantLock();

    public static int getNextValue()
    {
        int temp = 0;
        valueLock.lock();
        
        try {
            temp = ++nextValue;
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        finally {
            valueLock.unlock();
        }
        
        return temp;
    }

    public static void AddPrime(int value)
    {
        listLock.lock();

        try {
            primeNumbers.add(value);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        finally {
            listLock.unlock();
        }
    }
}