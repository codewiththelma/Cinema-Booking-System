import java.text.DecimalFormat;
	import java.util.Scanner;
public class copy {//start of  class block

	

			//Declaring Variables as a Set
			static String[] movies = {"Mulan", "Free Guy", "Sniper", "Black Adam"};
			static double[] moviePrice = {9.50, 3.50, 5.00};
			static String[] ticketType = {"Adult", "Child", "Student"};
			static double[] movieTime = {15.30,18.00,21.00};
			//declaring variables 
			static String name;
			static String email;
			static int chosenMovie;
			static int times;
			static int ticketQty;
			static int adultQty;
			static int childQty;
			static int studentQty;
			//declaring variables and assigning initial values
			static int ticketSold = 0;
			static int numTransactions=0;
			static double totalTakings=0;
			static DecimalFormat currency = new DecimalFormat("€0.00");
			static Scanner input = new Scanner(System.in);
			static DecimalFormat time = new DecimalFormat("0.00");
			static int adminCode = 2001;

			
			public static void main (String[]args){//start of program
				
				System.out.println("\t\t Book Ticket");
				System.out.println("");
				selectMovie(); //calling next method			}
			}
		
			public static void selectMovie() {//Allow user to select a Movie
				System.out.println("Hello, Please select a movie from the movie list.");
				System.out.println("---------------Movie List-----------------------");
				for (int i = 0; i < movies.length; i++) {
				
				System.out.println("Press " + (i+1) + " to select " + movies[i]);	
				}
				System.out.println("------------------------------------------------");
			  chosenMovie = input.nextInt()-1;//capture user choice
			  
			   if (chosenMovie>movies.length-1 || chosenMovie<-1) {
				  System.out.println("Invalid choice, Please try again.");
				  selectMovie(); 
				  
			  }
			   else if(chosenMovie==0-1) {	 
				  enterPin();//"0" calls the enterPin method which goes to admin.
				
			  }
			
			  
			  else {
				 selectTicketType(); //calling next method
			  }
				 
			}
			public static void selectTicketType(){//Allow user to select a Ticket Type
				System.out.println("Please select Ticket Type");
				System.out.println("----------------Ticket Types--------------------");
				for (int i = 0; i < ticketType.length; i++) {
					System.out.print("Press " + (i+1) + " for " + ticketType[i]);	
					System.out.println("\t" + currency.format(moviePrice[i]));
					}
				System.out.println("------------------------------------------------");
				 
				int choice = input.nextInt();//capture user input
				
						  if (choice==1) {	 
							  addAdult();
						  }
						  else if (choice==2) {	 
							  addChild();
						  }
						  else if (choice==3) {	 
							  addStudent();
						  }
						  else {
							  System.out.println("Invalid choice, Please try again.");
							  selectTicketType();   
						  }
					 
				}
				  
			

			public static void addAdult() {
				System.out.println("How many " + ticketType[0]+ " tickets do you want?");
				adultQty = input.nextInt();
				addMoreTicket();	
				
			}

			public static void addChild() {
				System.out.println("How many " + ticketType[1]+ " tickets do you want?");
				childQty = input.nextInt();
				addMoreTicket();
				
			}


			public static void addStudent() {
				System.out.println("How many " + ticketType[2]+ " tickets do you want?");
				studentQty = input.nextInt();
				addMoreTicket();
				
			}
			
			

			private static void addMoreTicket() {//Allow user to add more Tickets
				ticketQty = adultQty + childQty + studentQty;
				System.out.println("Do you want to add more tickets?");
				System.out.println("");
				System.out.println("Press 1 for Yes");
				System.out.println("Press 2 for No");
				System.out.println("Press 3 to clear selection and reselect.");
				
				System.out.println("------------------------------------------------");
				
				int choice = input.nextInt();//capture user choice
					if (choice==1)	{
						selectTicketType();
					}
					else if (choice==2){
						selectMovieTime();
					}
					else if (choice==3){
						ticketQty = 0;
						selectTicketType();
					}
					else {
						System.out.println("Invalid Choice. Try Again.");
						addMoreTicket();
					}
					
				
			}


			public static void selectMovieTime(){//Allow user to select a Movie Time
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
					  confirmSelections();
				  }
				
			}
		
					
			public static void confirmSelections() {//Request user to enter personal details and confirm selection
				System.out.println("---------------Personal Details------------------");
				System.out.println("Please enter your name.");
				name =input.next();
				System.out.println("------------------------------------------------");
				System.out.println("Hi " + name+"," );
				System.out.println("You have chosen to see the movie " + movies[chosenMovie] + " at " + time.format(movieTime[times]) );
				
				System.out.println("");
				System.out.println("Press 1 to proceed to payment");
				System.out.println("Press 2 to Cancel entire booking");
				System.out.println("------------------------------------------------");
				int choice = input.nextInt();//capture user choice
				switch(choice)
				{
				case 1:
				{
					payment();
					break;
				}
				case 2:
				{
					selectMovie();
					break;
				}
				default:
				{
					System.out.println("Invalid Choice. Try again.");	
					confirmSelections();
				}
				
				
				}
			
			}
			public static void payment() {//Payment Method
				double transactionPrice = (moviePrice[0] * adultQty) + (moviePrice[1] * childQty) + ( moviePrice[2] * studentQty);
				System.out.println("------------------------------------------------");
				System.out.println("You are to pay " + currency.format(transactionPrice) + " for " + ticketQty +" " + movies[chosenMovie]+ " ticket(s).");
				System.out.println("Please enter payment to complete your booking.");
		         double moneyEntered = input.nextDouble();//Holds money entered 
				
				while (moneyEntered<transactionPrice){
					double balance = transactionPrice - moneyEntered;
					System.out.println("Balance Remaining:\t" + currency.format(balance));
					System.out.println("Please enter remaining balance.");
					moneyEntered = moneyEntered + input.nextDouble();
					
				}
				System.out.println("Booking confirmed. Please check your email for confirmation.");
				if (moneyEntered>transactionPrice){
				double change = moneyEntered - transactionPrice;
					System.out.println("Change due:\t" + currency.format(change));
					System.out.println("Please take your change.");
				}
				System.out.println("Enjoy your Movie, Here's your recipt!!");
				System.out.println("------------------------------------------------");
				System.out.println("Movie Title:     \t" + movies[chosenMovie]);
				System.out.println("Time:             \t"+ time.format(movieTime[times]) );
				System.out.println("Number of Tickets:\t" + ticketQty);
				System.out.println("Amount Paid:      \t" + currency.format(transactionPrice) );
				System.out.println("------------------------------------------------");
				
				//Update Stats
				numTransactions++;
				totalTakings =totalTakings + transactionPrice;
				ticketSold = ticketSold +ticketQty;
			
				selectMovie();//create program loop
				
			}
			
			public static void enterPin() {
				int attempts = 3;//admin has 3 attempts to enter pin
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
						selectMovie();
					}
					System.out.println("Re-enter Admin Pin. You have " + attempts +  " attempt(s)");
					pin = input.nextInt();
				
					}
				 	System.out.println("------------------------------------------------");
				    System.out.println("Correct PIN. Entering Admin Mode.");
					admin();
			}
			
			public static void admin() {//Admin Mode
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
					selectMovie();
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
						
					{// Edit movies in array
						for(int i = 0; i< movies.length;i++)
						{
							System.out.println("Enter new Movie to replace " + movies[i] );
							movies[i] = input.next();
							System.out.println("Movie updated to " + movies[i]);	
						}
						
						
						
						break;
					}
					case 2:
						
					{//Edit prices in array
						for (int i = 0; i< moviePrice.length; i++)
						{
							System.out.println("Enter new price for movie to replace " + currency.format(moviePrice[i]));
							moviePrice[i] = input.nextDouble();
							System.out.println("Prices Updated to " + currency.format(moviePrice[i]));	
						}
						break;
					}
					case 3:
						
					{ //Edit Times in array
						for (int i = 0; i< movieTime.length; i++)
						{
							System.out.println("Enter new time to replace " + time.format(movieTime[i]));
							movieTime[i] = input.nextDouble();
							System.out.println("Time for Movies Updated to " + time.format(movieTime[i]));
						}
						
						break;
					}
					case 4:
						
					{// Edit types of ticket
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
						System.out.println("Invalid Choice. return into admin mode.");
						admin();
						break;
					}
					
					}
				admin();	
				}

				public static void dataConversion() {
				//not integrated with my program	
					String movieTime ="80"; 
					
					double mTime= Double.parseDouble(movieTime);//convert string to double
				System.out.println(mTime);
					String newmovieTime = Double.toString(mTime);//convert double to string	
				
				}

				
}//End of  class block