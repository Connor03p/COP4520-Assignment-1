public class PrimeThread extends Thread 
{
    public void run()
	{
        int currValue = SharedData.getNextValue();
        
        while (currValue < Assignment1.maxValue)
        {
            if (isPrime(currValue)) 
                SharedData.AddPrime(currValue);

            currValue = SharedData.getNextValue();
        }
	}

    public boolean isPrime(int value)
    {
        if (value < 2) return false;

        for (int divisor = 2; divisor <= Math.sqrt(value); divisor++)
            if (value % divisor == 0)
                return false;

        return true;
    }
}
