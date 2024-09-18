import java.text.DecimalFormat;
import java.util.Scanner;

public class Cinema 
{
	//Declaring Variables as a Set
	static String[] movies = {"Mulan", "Crank", "Avatar", "Frozen"};
	static double[] moviePrice = {9.50, 3.50, 5.00};
	static String[] ticketType = {"Adult", "Child", "Student"};
	static double[] movieTime = {15.30,18.00,21.00};
	static int ticketQty;
	static int ticketSold;
	static int numTransactions=0;
	static double totalTakings=0;
	static int type;
	static int times;
	static int choice;
	static String name;
	static int chosenMovie;
	static DecimalFormat currency = new DecimalFormat("€0.00");
	static Scanner input = new Scanner(System.in);
	static DecimalFormat time = new DecimalFormat("0.00");

	
	public static void main (String[]args)
	{
		enterPersonalDetails();
	}

	public static void enterPersonalDetails() {
		System.out.println("Hello,");
		System.out.println("Please enter your name");
		name =input.next();
		System.out.println("Hello " + name + ", Please select a movie.");
		chooseMovie();
			
	}
	public static void chooseMovie() {
		for (int i = 0; i < movies.length; i++) {
		System.out.println("Press " + (i+1) + " for " + movies[i]);	
		
		}
	  choice = input.nextInt()-1;
	  
	  if (choice==99)
	  {
		  admin();
	  }
	  
	  else if (choice>movies.length) {
		  System.out.println("Invalid choice, Please try again");
		  chooseMovie(); 
	  }
	  else
	  {	 
		chooseTicketType();
	  }
		 
	}


	private static void admin() {
		// TODO Auto-generated method stub
		
	}

	public static void chooseTicketType(){
		System.out.println("Please select Ticket Type");
		for (int i = 0; i < ticketType.length; i++) {
			System.out.print("Press " + (i+1) + " for " + ticketType[i]);	
			System.out.println("\t" + currency.format(moviePrice[i]));
			}
			type = input.nextInt()-1;
			
			  if (type>ticketType.length) {
				  System.out.println("Invalid choice, Please try again");
				  chooseTicketType(); 
			  }
			  else
			  {	 
				  selectTicketQty();
			  }
		
	}
	public static void selectTicketQty() {
		System.out.println("How many " + ticketType[type]+ " tickets do you want?");
		ticketQty = input.nextInt();
		selectMovieTime();
	}
	public static void selectMovieTime(){
		System.out.println("Please select Movie Time");
		for (int i = 0; i < movieTime.length; i++) {
			System.out.println("Press " + (i+1) + " for " + time.format(movieTime[i]));
		}
		times = input.nextInt()-1;
		 if (type>movieTime.length) {
			  System.out.println("Invalid choice, Please try again");
			  selectMovieTime(); 
		  }
		  else
		  {	 
			  confirmSelections();
		  }
		
	}
	
			
	public static void confirmSelections() {
		System.out.println("Hi " + name );
		System.out.println("You have chosen to see the movie " + movies[choice] + " at " + time.format(movieTime[times]) + "pm.");
		payment();
		
	}
	public static void payment() {
		double transactionPrice = moviePrice[type] * ticketQty;
		System.out.println("You are to pay " + currency.format(transactionPrice) + " for " + ticketQty +" " + ticketType[type]+ " ticket(s).");
		System.out.println("Please enter payment");
         double moneyEntered = input.nextDouble();//Holds money entered into machine
		
		while (moneyEntered<transactionPrice)
		{
			double balance = transactionPrice - moneyEntered;
			System.out.println("Balance Remaining:" + currency.format(balance));
			System.out.println("Please enter ramaining balance.");
			moneyEntered = moneyEntered + input.nextDouble();
		}
		System.out.println("Booking confirmed. Please check your email for confirmation.");
		if (moneyEntered>transactionPrice)
		{
			double change = moneyEntered - transactionPrice;
			System.out.println("Change due:" + currency.format(change));
			System.out.println("Please take your change.");
		}
		System.out.println("Enjoy your Movie!!");
		enterPersonalDetails();
		
	}
	




}
