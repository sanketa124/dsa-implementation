class Solution {
  public static boolean isMatch(String s, String p) {
    final int m = s.length();
    final int n = p.length();
    // dp[i][j] := true if s[0..i) matches p[0..j)
    boolean[][] dp = new boolean[m + 1][n + 1];
    dp[0][0] = true;

    for (int j = 0; j < p.length(); ++j)
      if (p.charAt(j) == '*' && dp[0][j - 1])
        dp[0][j + 1] = true;

    for (int i = 0; i < m; ++i)
      for (int j = 0; j < n; ++j)
        if (p.charAt(j) == '*') {
          // The minimum index of '*' is 1.
          final boolean noRepeat = dp[i + 1][j - 1];
          final boolean doRepeat = isMatch(s, i, p, j - 1) && dp[i][j + 1];
          dp[i + 1][j + 1] = noRepeat || doRepeat;
        } else if (isMatch(s, i, p, j)) {
          dp[i + 1][j + 1] = dp[i][j];
        }
    
    for(int i = 0; i < (m+1); i++){
      for(int j = 0; j < (n+1); j++){
        if(j == 0){
          System.out.print("[ "+dp[i][j] + " ");  
        }else{
          System.out.print(dp[i][j] + " ");  
        }
      }
      System.out.println(" ]");  
    }
    return dp[m][n];
  }

  private static boolean isMatch(final String s, int i, final String p, int j) {
    return j >= 0 && p.charAt(j) == '.' || s.charAt(i) == p.charAt(j);
  }

  public static void main(String args[])  //static method  
  {
    if(args.length == 2){
      System.out.println("The Pattern Match Result is: "+isMatch(args[0], args[1]));  
    }else{
      System.out.println("Enter the Input String followed by the Pattern to match it with.");     
    }
  }    

}