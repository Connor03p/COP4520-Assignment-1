### How to compile and run the code
Open a command prompt in the src folder and run "javac Assignment1.java", then "java Assignment1"

### Summary
This program creates several threads to calculate many prime numbers. Values are checked from least to greatest. Each thread must obtain a number from the SharedData class, but only one thread at a time can ask for the next number. This is enforced using a simple lock system. While a thread holds a lock, all other threads must wait for that lock to be released before attempting to claim it. When a thread recieves a number that is prime, it adds it to a shared list. The list is managed by the same lock system, ensuring that each prime is successfully added to the list. The list of primes are sorted once all threads are finished to display the last 10 prime numbers in order.

### Test Results
When given a maximum value of 100,000,000:
| # of Threads | Time 1  | Time 2  | Time 3  |
|--------------|---------|---------|---------|
| 8            | 22.788  | 28.964  | 28.879  |
| 4            | 30.728  | 30.807  | 33.009  |
| 2            | 49.033  | 48.084  | 48.739  |
| 1            | 83.972  | 82.237  | 87.976  |
