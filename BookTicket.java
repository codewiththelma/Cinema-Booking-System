import java.text.DecimalFormat;
import java.util.Scanner;
public class BookTicket {

		//Declaring Variables as a Set
		static String[] movies = {"Mulan", "Free Guy", "Sniper", "Black Adam"};
		static double[] moviePrice = {9.50, 3.50, 5.00};
		static String[] ticketType = {"Adult", "Child", "Student"};
		static double[] movieTime = {15.30,18.00,21.00};
		static String name;
		static String email;
		static int choice;
		static int type;
		static int times;
		static int ticketQty;
		static int ticketSold = 0;
		static int numTransactions=0;
		static double totalTakings=0;
		static DecimalFormat currency = new DecimalFormat("€0.00");
		static Scanner input = new Scanner(System.in);
		static DecimalFormat time = new DecimalFormat("0.00");
		static int adminCode = 2001;

		
		public static void main (String[]args)
		{
			System.out.println("\t\t Book Ticket");
			System.out.println("");
			chooseMovie();
		}

	
		public static void chooseMovie() {
			System.out.println("Hello, Please select a movie from the movie list.");
			System.out.println("---------------Movie List-----------------------");
			for (int i = 0; i < movies.length; i++) {
			
			System.out.println("Press " + (i+1) + " for " + movies[i]);	
			}
			System.out.println("------------------------------------------------");
		  choice = input.nextInt()-1;
		  
		   if (choice>movies.length-1 || choice<-1) {
			  System.out.println("Invalid choice, Please try again.");
			  chooseMovie(); 
			  
		  }
		   else if(choice==0-1)
		  {	 
			  enterPin();
			
		  }
		
		  
		  else {
			 chooseTicketType(); 
		  }
			 
		}

		public static void enterPin() {
			int attempts = 3;
			System.out.println("---------------------------------------------");
			System.out.println("Enter Admin Pin. You have " + attempts +  " attempts");
				int pin = input.nextInt();
				int pinCount=0;
				
			 while (pin != adminCode){
				 System.out.println("Incorrect PIN. Try again.");
				 attempts--;
				 pinCount++;
		
				if(pinCount == 3){
					System.out.println("Max Pin Entry reached.");
					chooseMovie();
				}
				System.out.println("Re-enter Admin Pin. You have " + attempts +  " attempt(s)");
				pin = input.nextInt();
			
				}
			 	System.out.println("------------------------------------------------");
			    System.out.println("Correct PIN. Entering Admin Mode.");
				admin();
		}

		public static void chooseTicketType(){
			System.out.println("Please select Ticket Type");
			System.out.println("----------------Ticket Types--------------------");
			for (int i = 0; i < ticketType.length; i++) {
				System.out.print("Press " + (i+1) + " for " + ticketType[i]);	
				System.out.println("\t" + currency.format(moviePrice[i]));
				}
			System.out.println("------------------------------------------------");
				type = input.nextInt()-1;
				
				  if (type>ticketType.length-1) {
					System.out.println("Invalid choice, Please try again.");
					  chooseTicketType(); 
				  }
				  else if(type<0){
					  System.out.println("Invalid choice, Please try again.");
					  chooseTicketType();  
				  }
				  else {	 
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
			System.out.println("---------------------Movie Time-----------------");
			for (int i = 0; i < movieTime.length; i++) {
				System.out.println("Press " + (i+1) + " for " + time.format(movieTime[i]));
			}
			System.out.println("------------------------------------------------");
			times = input.nextInt()-1;
			 if (times>movieTime.length-1) {
				  System.out.println("Invalid choice, Please try again.");
				  selectMovieTime(); 
			  }
			 else if(times<0){
				  System.out.println("Invalid choice, Please try again.");
				  selectMovieTime();
			 }
			  else {	 
				  enterPersonalDetails();
			  }
			
		}
		public static void enterPersonalDetails() {
			System.out.println("---------------Personal Details------------------");
			System.out.println("Hello,");
			System.out.println("Please enter your name.");
			name =input.next();
			System.out.println("Please enter your Email Address.");
			email = input.next();
			confirmSelections();
				
		}
		
				
		public static void confirmSelections() {
			System.out.println("------------------------------------------------");
			System.out.println("Hi " + name+"," );
			System.out.println("You have chosen to see the movie " + movies[choice] + " at " + time.format(movieTime[times]) );
			
			System.out.println("");
			System.out.println("Press 1 to Confirm");
			System.out.println("Press 2 to Cancel");
			System.out.println("------------------------------------------------");
			int choice = input.nextInt();
			switch(choice)
			{
			case 1:
			{
				payment();
				break;
			}
			case 2:
			{
				chooseMovie();
				break;
			}
			default:
			{
				System.out.println("Invalid Choice. Try again.");	
				confirmSelections();
			}
			
			
			}
		
		}
		public static void payment() {
			double transactionPrice = moviePrice[type] * ticketQty;
			System.out.println("------------------------------------------------");
			System.out.println("You are to pay " + currency.format(transactionPrice) + " for " + ticketQty +" " + ticketType[type]+ " ticket(s).");
			System.out.println("Please enter payment to complete your booking.");
	         double moneyEntered = input.nextDouble();//Holds money entered into machine
			
			while (moneyEntered<transactionPrice){
				double balance = transactionPrice - moneyEntered;
				System.out.println("Balance Remaining:" + currency.format(balance));
				System.out.println("Please enter remaining balance.");
				moneyEntered = moneyEntered + input.nextDouble();
				
			}
			System.out.println("Booking confirmed. Please check your email for confirmation.");
			if (moneyEntered>transactionPrice){
			double change = moneyEntered - transactionPrice;
				System.out.println("Change due:" + currency.format(change));
				System.out.println("Please take your change.");
			}
			System.out.println("Enjoy your Movie!!");
			System.out.println("------------------------------------------------");
			
			
			numTransactions++;
			totalTakings =totalTakings + transactionPrice;
			ticketSold = ticketSold +ticketQty;
		
			chooseMovie();
			
		}
		
		public static void admin() {
			System.out.println("---------------------Admin Mode----------------");
			System.out.println("Press 1 to View stats");
			System.out.println("Press 2 to Edit Movie Details");
			System.out.println("Press 3 to exit admin mode");
			System.out.println("------------------------------------------------");
			
			String choice =input.next();
			
			switch(choice)
			{
			case "1":
			{
				viewStats();
				break;
			}
			
			case "2":
			{
				editMovieDetails();
				break;
			}
			case "3":
			{
				chooseMovie();
				break;
			}
			default:
			{
				System.out.println("Invalid Choice. return into admin mode.");
				admin();
				break;
			}
		  }
				
			
		}
		
			public static void viewStats() {
				System.out.println("---------------------STATS----------------------");
			    System.out.println("Total number of Transactions \t  " +numTransactions);
			    System.out.println("Total Value of all transactions:  "+ currency.format(totalTakings));
			    System.out.println("Total Tickets sold:    \t\t  " + ticketSold);
			    double average = totalTakings/numTransactions;
			    System.out.println("Average Value:         \t\t  " + currency.format(average));
				System.out.println("------------------------------------------------");
			    
			    admin();
		    
		
	}
		
		
			public static void editMovieDetails() {
				System.out.println("------------------Edit Details--------------------");
				System.out.println("Press 1 to change Current movie List.");
				System.out.println("Press 2 to change Price for Movies.");
				System.out.println("Press 3 to change Time for Movies.");
				System.out.println("Press 4 to change Types of Tickets.");
				System.out.println("------------------------------------------------");
				
				int choice = input.nextInt();
				
				switch (choice)
				
				{
				case 1:
					
				{
					for(int i = 0; i< movies.length;i++)
					{
						System.out.println("Enter new Movie to replace " + movies[i] );
						movies[i] = input.next();
						System.out.println("Movie updated to " + movies[i]);	
					}
					
					
					
					break;
				}
				case 2:
					
				{
					for (int i = 0; i< moviePrice.length; i++)
					{
						System.out.println("Enter new price for movie to replace " + currency.format(moviePrice[i]));
						moviePrice[i] = input.nextDouble();
						System.out.println("Prices Updated to " + currency.format(moviePrice[i]));	
					}
					break;
				}
				case 3:
					
				{
					for (int i = 0; i< movieTime.length; i++)
					{
						System.out.println("Enter new time to replace " + time.format(movieTime[i]));
						movieTime[i] = input.nextDouble();
						System.out.println("Time for Movies Updated to " + time.format(movieTime[i]));
					}
					
					break;
				}
				case 4:
					
				{
					for (int i =0; i<ticketType.length; i++)
					{
						System.out.println("Enter new ticket Type to replace " + ticketType[i]);
						ticketType[i] = input.next();
						System.out.println("Types of ticket Updated to " + ticketType[i]);	
					}
					break;
				}
				default:
				{
					System.out.println("Invalid Choice. Try again.");
					editMovieDetails();
					break;
				}
				
				}
			admin();	
			}

			public static void dataConversion() {
				
				String movieTime ="80"; 
				
				double mTime= Double.parseDouble(movieTime);//convert string to double
			System.out.println(mTime);
				String newmovieTime = Double.toString(mTime);//convert double to string	
			
			}
	}

