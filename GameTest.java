import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Console;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

 class system {
	
	private File file;
	private HashMap<String, String>map;
	
	public system() throws IOException {
		file = new File("./user_pwd.txt");
		map = new HashMap<String,String>();
	   	
		if(!file.exists()) {
			System.out.println("[error] No log file found, system exit with -1.");
			System.exit(-1);
		}
		Start();
	}
	
	private void Start() throws IOException {
		System.out.println("Enter a choice to proceed:");
		System.out.println("(1) Sign up");
		System.out.println("(2) Log in");
		Scanner scanner = new Scanner(System.in);
		switch (Integer.parseInt(scanner.nextLine())) {
		case 1:
			Signup();
			break;
		case 2:
			Login();
			break;
		default:
			System.out.println("Wrong number, exit program");
			System.exit(-1);
			break;
		}
	}
	
	private void Login() throws IOException {									//this memnber function is used for registered players login  

		loadtxt();
		
		System.out.println("Please input your username:");
		Scanner scanner = new Scanner(System.in);
		String username = scanner.nextLine();
		System.out.println("Please input your password:");
		String password = scanner.nextLine();
		
	//	int enter_conut=0;
		  
	              	
		while(!(map.containsKey(username)&&map.get(username).equals(password))){
			System.out.println("Unmatched username and password, please try again.");
			System.out.println("Please input your username:");
			username = scanner.nextLine();
			System.out.println("Please input your password:");
			password = scanner.nextLine();
		//	
 	/*	else													//extra function for further development to reset password, when player enters
															// incorrect passsword for more than three times 
		  {	
                        System.out.println("You may forget your password,do you want to reset your password?");	
                        String forget_password = scanner.nextLine(); 
			if(forget_password.equals("yes")){			
			System.out.println("Please inoput your email address:");					//registered players could enter email to reset password 
 			String email = scanner.nextLine();
			}
			}*/  
	        }
            

		System.out.println("Welcome back "+username+"!");							//when players enter correct username and password,system returns successful message
		System.out.println("Do you change your password?");							//Extra function:players reset password after successfully login
                String reset = scanner.nextLine();									
		if(reset.equals("yes"))
		  {
	    		System.out.println("Now you can change your password,");
			Reset(username);
		  }
    		else {
		
                      System.out.println("Welcome to the game!");							//system returns message to show successful password  change
			}		
	}
	
	private void Signup() throws IOException {									//member function: used for new players to create a new account
		
		//loadtxt();		

		System.out.println("Please input your username:");
		Scanner scanner = new Scanner(System.in);
		String username = scanner.nextLine();

	/*	while(map.containsKey(username))								//this marked section is used for determine whether the new username exists, if the name
		{
		   System.out.println("The username exists,Please input an another username:");    	//exists, the promot wil guid player to change another new name, due to time limits, I do not implem
		   username = scanner.nextLine();							//implement it successfully,and this could be used for further development
		}		
	*/
		System.out.println("Please input your password:");
		String password = scanner.nextLine();
		System.out.println("Please input again:");
		String password_c = scanner.nextLine();
		if(password.equals(password_c)) {									//password emter twice for users to double check entered password
			createAccount(username, password);
			System.out.println("Account Created!");
			Start();
		}else{
			System.out.println("Password not match.");
			Signup();
		}
	}
	
	private void Reset(String username) throws IOException {							//member function: registered players enter new password to reset password,
		Scanner scanner = new Scanner(System.in);								//this function is frequrntly involed by "login"
		System.out.println("Please input your new password:");
		String password = scanner.nextLine();
		System.out.println("Please input again:");
		String password_c = scanner.nextLine();
		if(password.equals(password_c)) {
			createAccount(username, password_c);
			System.out.println("Reset successfully!");
		}else{
			System.out.println("Password not match.");
			Reset(username);
		}
	}
	
	private void loadtxt() throws IOException {									//member function: read in data from file
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		String temp = null;
		for(temp=br.readLine();temp!=null;temp=br.readLine()) {
			map.put(temp.split(";")[0], temp.split(";")[1]);
		}
		fr.close();
		br.close();
	}
	
	private void createAccount(String username, String password) throws IOException {				//new palyers create a new account,when the username existes, system returns prompt
		/*
		FileReader fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr);
		String temp = null;
                for(temp=br.readLine();temp!=null;temp=br.readLine()) {
                        if(temp.split(";")[0].equals(username))
			{
			 System.out.println("----------");
			System.out.println("Username exists,please input a new one:");
			}
                } */
	
		FileWriter fw = new FileWriter(file,true);
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(username+";"+password+"\n");
		bw.close();
		fw.close();
	}
	
	
	public static void main(String args[]) throws IOException {
		new system();
	}
}


public class GameTest{
        public static void main(String args[]) throws IOException {                                                                     //main function which contains login,create account and fight
                new system();
                int enemy_attack = (int)(Math.random()*100+1);                                                                  //randomly produce attack value of enemy soldier and our soldier
                int soldier_attack = (int)(Math.random()*100+1);
                int soldier_blood = (int)(Math.random()*1000+1);                                                        //randomly produce blood of enemy soldier and our soldier btween 1 to 1000
                int enemy_blood = (int)(Math.random()*1000+1);
                Soldiers  soldiers1 = new Soldiers() ;
                Soldiers  soldiers2 = new Soldiers() ;
                soldiers1.setBlood(soldier_blood);
		soldiers1.setattack(soldier_attack);
                soldiers2.setBlood(enemy_blood);
		soldiers2.setattack(enemy_attack);
                Calculate.play(soldiers1,soldiers2);
        }
}


class Soldiers {                                                                                                                                                       //class for soldiers                  
    private int blood;                                                                                                                                  // the blood represents ability of soldiers
    private int attack;                                                                                                                                 //the capability to fight with enemies


    public int getBlood() {
        return blood;
    }
    public void setBlood(int blood) {
        this.blood = blood;
    }

    public int getattack(){
        return attack;
    }

       public void setattack(int attack) {
        this.attack = attack;
        }
}


 class Calculate{

    public static void play(Soldiers s, Soldiers s1) {

        Scanner sc = new Scanner(System.in);
        System.out.println("the initial stage of two fighting soldiers:");
        Calculate.print(s, s1);
        System.out.println("game starts:");

        for (int i = 0; i < 32; i++) {                                                                                                                  // maximum 32 rounds for two soldiers fighting
            while(determine(s, s1)){                                                                                                                    //determine the blood left
                System.out.println("please input command number for battle---input [2]: enemy attack,input [1]: our soldier fight-------------");
                int a = sc.nextInt();
            if (a == 1) {                                                                                                                                       // our attack
                System.out.println("No." + (i + 1) + "\t round");
                Calculate.attack(s, s1);
                Calculate.print(s, s1);
            } else if (a == 2) {                                                                                                                                        // enemy attack
                System.out.println("No." + (i + 1) + "\t round:");
                Calculate.attack(s1,  s);
                Calculate.print(s1, s);
            }
            }
        }
         System.out.println("Game over");                                                                                                                       //prompt of game over
    }

public static boolean determine(Soldiers s,  Soldiers s1) {                                                                                                     //function used for show battle result
        if (s.getBlood() <= 0) {
             System.out.println("our soldier insufficient blood");
             System.out.println("Our soldier died!Game over!");
        }else if(s1.getBlood() <= 0) {
             System.out.println("Enemy insufficient blood!");
             System.out.println("Target died!Game over");
        }
        return true;
     }


 public static void attack(Soldiers s1, Soldiers s2) {                                                                                                  // calculate left blood according to attack
        s2.setBlood(s2.getBlood() - s1.getattack());
    }


  public static void print(Soldiers s1, Soldiers s2) {                                                                                          //this function aims to show the status of soldiers
        System.out.println("our soldier's attack value: \t" + s1.getattack() + "\t blood left : " + s1.getBlood());
        System.out.println("enemy soldier's attack value: \t" + s2.getattack() +  "\t blood left:  " + s2.getBlood());
    }
}






