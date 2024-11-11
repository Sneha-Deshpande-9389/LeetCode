package LeetCode;

import java.util.ArrayList;
import java.util.List;

public class RestoreIp {
    public static void main(String args[]) {
        System.out.println(new RestoreIp().restoreIp("0000").toString());
        System.out.println(new RestoreIp().restoreIp("25525511135").toString());
    }
    public List<String> restoreIp(String str) {
        List<String> res = new ArrayList<>();
        backTrack("", str, 0, res, 0);
        return res;
    }
    public void backTrack(String path, String input, int start, List<String> res, int dots) {
        if(dots > 4) {
            return;
        }

        if(dots == 4 && start >= input.length()) {
            res.add(path.substring(0, path.length()-1));
            return;
        }


        for(int length = 1; length <= 3 && start + length <= input.length(); length++){
            String num = input.substring(start, start+length);
            if(num.charAt(0) == '0' && length != 1)  {
                break;
            } else if (Integer.parseInt(num) <= 255) {
                backTrack(path + num + ".", input, start+length, res, dots + 1);
            }
        }

    }

}


/*
0.0.0.00000

 */
