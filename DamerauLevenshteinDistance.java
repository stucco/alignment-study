public class DamerauLevenshteinDistance	{
	
	private String s1;
	private String s2;
	private int length1;
	private int length2;
	private int [][] table;
	
	//initializing private variables; calling distance computing functions
	int damerau_levenshtein_distance (String str1, String str2)	{
		
		if (str1.equals(str2))	return 0;	//checking if strings are the same
		if ((length1 = str1.length()) == 0)	return str2.length();
		if ((length2 = str2.length()) == 0)	return length1;
		
		s1 = new String (str1);
		s2 = new String (str2);

		set_table();
		
		return compute_distance();
	}	
	
	//enumirating the positions in the table
	void set_table ()	{
	
		table = new int[length1+1][length2+1];
		
		for (int i = 0; i <= length1; i++)	table[i][0] = i;	
		for (int j = 0; j <= length2; j++)	table[0][j] = j;		
	}
	
	//computing and returning Damerau-Levenshtein distance 
	int compute_distance ()	{
		
		int edit = 0;
		
		for (int i = 0; i < length1; i++)	{
			for (int j = 0; j < length2; j++)	{
				//addition & delition
				if (s1.charAt(i) == s2.charAt(j))	edit = 0;
				else	edit = 1;
				table [i+1][j+1] = find_min(table[i+1][j] + 1, table[i][j+1] + 1, table [i][j] + edit);
				if (i == 0 || j == 0)	continue;
				//transposition
				if (s1.charAt(i) == s2.charAt(j-1) && s1.charAt(i-1) == s2.charAt(j))
					table[i+1][j+1] = Math.min (table[i+1][j+1], table[i-1][j-1] + edit);		
			}
		}	
		
		return table[length1][length2];
	}
	
	//finding a min between three values
	int find_min (int a, int b, int c)	{
		
		int temp = Math.min(a,b);
		return Math.min(temp, c);
	}	
}

