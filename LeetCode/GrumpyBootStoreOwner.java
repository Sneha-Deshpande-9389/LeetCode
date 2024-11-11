package LeetCode;

public class GrumpyBootStoreOwner {
    public int window(int window, int[] customers, int[] grumpyMin) {
        //base condition - toDo

        //initialisation- toDo
/*
0,1,2,3,4,
0,1,0,1,1, window = 3
12,4,5,10,12

0-3 -        4   - 21
1-4 - 4+10 = 14
2-5 - 10+12 = 22

totalsatisfied = 26
4,14,22-4,14,27 - 18+22 = 40
21,19,27-




12+4+5 - 21,-
4+5+10 - 19 -
5+10+12 - 27, -

22



 */

        // window
        int i, j = 0, satisfiedCustomer = 0, max = 0, n = grumpyMin.length, extra = 0;
        for(i = 0 ;i < n ;i ++) {
            satisfiedCustomer += grumpyMin[i] == 0 ? customers[i] : 0;
        }
        i = 0;
        while(j<n) {
            if(j-i+1 < window) {
                satisfiedCustomer += grumpyMin[j] == 0 ? customers[j] : 0;
                extra += customers[j];
                j++;
            } else if (j-i+1 >= window){
                //calculate consecutive non-grumpy minutes between i to K
                satisfiedCustomer += grumpyMin[i] == 0 ? customers[i] : 0;
                i++;
                j++;
            }
        }
        return extra + satisfiedCustomer;
    }
}
