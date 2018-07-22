package Highscore;

import java.io.*;

public class Highscore {

	public static void writeHighscore(int score) {
		try{
			FileWriter fw = new FileWriter("src/Highscore/Highscore.txt");
			PrintWriter pw = new PrintWriter(fw);
			
			pw.println(Integer.toString(score));
			pw.close();
		}catch(Exception e) {
			System.out.println("ERROR");
		}
	}
	
	public static int returnHighscore() {
		
		String scoreStr = "";
		try {
			
			FileReader fr = new FileReader("src/Highscore/Highscore.txt");
			BufferedReader br = new BufferedReader(fr);
			String help;
			while((help = br.readLine()) != null) {
				scoreStr += help;
			}
			br.close();
			
		}catch(Exception e) {
			System.out.println("Laeuft net");
		}
		if(scoreStr.equals(""))	return 0;
		int score = Integer.parseInt(scoreStr);
		return score;
	}  
	
	public static void main(String[] args) {
		writeHighscore(317);
		System.out.println(returnHighscore());
		
		
	}
	
}
