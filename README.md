### How to compile and run the code
Open a command prompt in the src folder and run "javac Assignment1.java", then "java Assignment1"

### Summary
This program creates several threads to calculate many prime numbers. Values are checked from least to greatest. Each thread must obtain a number from the SharedData class, but only one thread at a time can ask for the next number. This is enforced using a simple lock system. While a thread holds a lock, all other threads must wait for that lock to be released before attempting to hold the lock themselves. While this system may sometimes delay individual threads, this system is still significantly faster than if all the calculations were done on a single thread. When a thread recieves a number that is prime, it adds it to a shared list. The list is also managed by the same lock system, ensuring that each prime is successfully added to the list.

### Test Results
Max Value | # of Threads | Runtime
100000000 |      8       | 23 seconds
100000000 |      4       | 35 seconds
100000000 |      2       | 52 seconds
100000000 |      1       | 83 seconds

Max Value | # of Threads | Runtime
10000000  |      8       | 1 seconds
10000000  |      4       | 1 seconds
10000000  |      1       | 3 seconds

While multithreading is significantly more efficient with greater maximum values, they are slightly less efficient when given a smaller task.