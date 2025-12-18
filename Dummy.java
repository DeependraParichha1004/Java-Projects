import java.util.Arrays;

public class Dummy {
    public static void sort_012(int[] num) {
        int count_0 = 0;
        int count_1 = 0;
        int count_2 = 0;

        // Count 0s, 1s, and 2s
        for (int i = 0; i < num.length; i++) {
            if (num[i] == 0) count_0++;
            else if (num[i] == 1) count_1++;
            else if (num[i] == 2) count_2++;
        }

        int i =0;
        while(count_0-- > 0) num[i++]=0;
        while(count_1-- > 0) num[i++]=1;
        while(count_2-- > 0) num[i++]=2;

        System.out.println(Arrays.toString(num));
    }
    
    public static void main(String[] args) {
        int[] num = {0, 1, 1, 2, 0, 2, 1, 0};
        sort_012(num);
    }
}

