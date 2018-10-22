package algorithm.bakjun;
import java.io.*;
import java.util.Arrays;

/**
 * https://www.acmicpc.net/problem/2309 일곱 난쟁이
 *
 */
public class Case2309 {
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br =  new BufferedReader(new InputStreamReader(System.in));


        int[] table = new int[101];
        int[] arr = new int[9];
        for (int i = 0; i < 9; i++) {
            int a = Integer.valueOf(br.readLine().trim());
            table[a] = a;
            arr[i] = a;
        }

        solve(arr, table, bw);

        bw.flush();
        br.close();
        bw.close();
    }

    private static void solve(int[] arr, int[] table, BufferedWriter bw) throws IOException {
        int arrSum = 0;
        for (int i = 0; i < arr.length; i++) {
            arrSum += arr[i];
        }

        int res = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = i+1; j < 9; j++) {
                res = arrSum - arr[i] - arr[j];
                if (res == 100) {
                    for (int k : table) {
                        if (k != arr[i] && k != arr[j] && k != 0) {
                            bw.write(k+"\n");
                        }
                    }
                    return ;
                }
            }
        }

        return;
    }
}
