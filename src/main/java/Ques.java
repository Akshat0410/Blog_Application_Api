import java.util.Scanner;

public class Ques {
    public static void main(String[] args) {
            Scanner in = new Scanner(System.in);

            int arrSize = in.nextInt();
            int[] numbers = new int[arrSize];
            for (int i = 0; i < arrSize; i++) {
                numbers[i] = in.nextInt();
            }
            int k = in.nextInt();

            in.close();

            //sort
            int min, minI;
            for (int b = 0; b < arrSize; b++) {
                min = numbers[b];
                minI = b;
                for (int cur = b + 1; cur < arrSize; cur++) {
                    if (numbers[cur] < min) {
                        min = numbers[cur];
                        minI = cur;
                    }
                }
                int tmp = numbers[b];
                numbers[b] = min;
                numbers[minI] = tmp;
            }

            //calculate
            int result = 0, sum;
            for (int b = 0; b < arrSize - 1; b++) {
                sum = numbers[b] + numbers[b + 1];
                result += k * sum;
                numbers[b + 1] = sum;
            }

            System.out.println(result);
        }
    }
