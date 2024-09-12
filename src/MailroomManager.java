/**
 * This class represents an interface for a user to manage a simulation of a mailroom using Stacks
 *
 * @author Sean Shiroma
 *      sean.shiroma@stonybrook.edu
 *      id: 115872064
 *      Recitation 2
 */
import java.util.*;
import java.util.EmptyStackException;
import java.util.Scanner;
public class MailroomManager {

    /**
     * This method determines which stack the recipient should be in alphabetically by giving the index of the array of stacks
     *
     * @param nameOfRecipient
     * The name of the recipient
     * @return
     * The index of the array of stacks
     *
     * Precondition:
     * the name of the recipient is not null and is valid (no special characters in name)
     *
     * @throws NullPointerException
     */
    public static int alphabeticalStoring(String nameOfRecipient) throws NullPointerException{
        if(nameOfRecipient == null) {
            throw new NullPointerException();
        }
        char firstLetter = nameOfRecipient.substring(0,1).toUpperCase().charAt(0);//gets the first letter of the recipient in upper case
        if(firstLetter >= 'A' && firstLetter <= 'G')
            return 0;
        else if(firstLetter >='H' && firstLetter <= 'J')
            return 1;
        else if(firstLetter >='K' && firstLetter <= 'M')
            return 2;
        else if(firstLetter >='N' && firstLetter <= 'R')
            return 3;
        else if(firstLetter >='S' && firstLetter <= 'Z')
            return 4;
        else
            return -1;
    }

    /**
     * This method adds a package to the appropriate stack
     *
     * @param arrayOfStacks
     * The array of stacks where the package will be added
     * @param nameOfRecipient
     * The name of the recipient of the package
     * @param arrivalDate
     * The arrival date of the package
     * @param weight
     * The weight of the package in lbs
     * @throws FullStackException
     * @throws IndexOutOfBoundsException
     */
    public static void addingPackageToStack(PackageStack[] arrayOfStacks, String nameOfRecipient, int arrivalDate, double weight) throws FullStackException, IndexOutOfBoundsException{
            int indexOfArray = alphabeticalStoring(nameOfRecipient);
            if (indexOfArray == -1)
                throw new IndexOutOfBoundsException();
            else
                arrayOfStacks[indexOfArray].push(new Package(nameOfRecipient, arrivalDate, weight));
    }

    /**
     * This method prints the stacks in the mailroom
     * @param arrayOfStacks
     * The array of package stacks
     * @param Floor
     * The floor stack
     */
    public static void printStacks(PackageStack[] arrayOfStacks, Stack<Package> Floor){
        System.out.println("Current Packages: \n" +
                "--------------------------------\n" +
                "Stack 1 (A-G):|" + arrayOfStacks[0] + "\n" +
                "Stack 2 (H-J):|" + arrayOfStacks[1] + "\n" +
                "Stack 3 (K-M):|" + arrayOfStacks[2] + "\n" +
                "Stack 4 (N-R):|" + arrayOfStacks[3] + "\n" +
                "Stack 5 (S-Z):|" + arrayOfStacks[4] + "\n" +
                "Floor: |" + FloorString(Floor) + "\n");
    }

    /**
     * This method gives a string of where the package was placed
     * @param array
     * array of packages
     * @param pushedPackage
     * package to be placed
     * @param Floor
     * Floor stack
     * @return
     * string of which stack the package was placed
     */
    public static String nearestStack(PackageStack[] array, Package pushedPackage, Stack<Package> Floor){
        int index = pushToNearestStack(array, pushedPackage, Floor);
        if(index == 5)
            return "package was placed in floor";
        else
            return "package was placed in stack " + (index+1);

    }

    /**
     * This method gives the index of the stack that has space for the package
     *
     * @param array
     * array of packages
     * @param pushedPackage
     * package to be placed
     * @param Floor
     * Floor stack
     * @return
     * Index of stack
     */
    public static int pushToNearestStack(PackageStack[] array, Package pushedPackage, Stack<Package> Floor){
        for(int i = 0; i < array.length; i++) {
            if (!array[i].isFull()) {
                try {
                    array[i].push(pushedPackage);
                    return i;
                } catch (FullStackException e) {
                    System.out.println();
                }
            }
        }
        Floor.push(pushedPackage);
        return 5;
    }

    /**
     * this method gives a string representation of the floor stack
     * @param Floor
     * The floor stack
     * @return
     * A string representation of the floor stack
     */
    public static String FloorString(Stack<Package> Floor){
        if(Floor.isEmpty())
            return "empty.";
        String str = Floor.toString().replaceAll(",","");
        return str.substring(1,str.length()-1);
    }
    /**
     * This method gives the interface for a user to manipulate the six package stacks through adding, removing, and getting packages for customers
     *
     * @param args
     * arguments
     */
    public static void main(String[] args){
        PackageStack[] arrayOfStacks = {
                new PackageStack(),//A-G
                new PackageStack(),//H-J
                new PackageStack(),//K-M
                new PackageStack(),//N-R
                new PackageStack(),//S-Z
        };
        Stack<Package> FloorStack = new Stack<Package>();
        Scanner input = new Scanner(System.in);
        int currentDay = 0;
        String selection = null;
        do {
            System.out.print("Welcome to the Irving Mailroom Manager. You can try to make it better, but the odds are stacked against you. It is day " + currentDay + ".\n" +
                    "Menu:\n" +
                    "   D) Deliver a package\n" +
                    "   G) Get someone's package\n" +
                    "   T) Make it tomorrow\n" +
                    "   P) Print the stacks\n" +
                    "   M) Move a package from one stack to another\n" +
                    "   F) Find packages in the wrong stack and move to floor\n" +
                    "   L) List all packages awaiting a user\n" +
                    "   E) Empty the floor\n" +
                    "   Q) Quit\n" +
                    "Please select an option: ");
            selection = input.next();

            if(selection.equalsIgnoreCase("D")){
                input.nextLine();
                System.out.print("Please enter the recipient name: ");
                String recipientName = input.nextLine();
                System.out.print("Please enter the weight (lbs): ");
                double weight = input.nextDouble();

                try {
                    addingPackageToStack(arrayOfStacks, recipientName, currentDay, weight);
                    System.out.printf("A %.0f lb package is awaiting pickup by " + recipientName + ".\n\n", weight);
                } catch (NullPointerException e) {
                    System.out.println("There is no name for the recipient, try again.\n");
                } catch (FullStackException e) {
                    System.out.printf("A %.0f lb package is awaiting pickup by " + recipientName + "." + " As stack " + (alphabeticalStoring(recipientName)+1) + " was full, " + nearestStack(arrayOfStacks, new Package(recipientName, currentDay, weight), FloorStack) + "\n\n",weight);
                } catch (IndexOutOfBoundsException e){
                    System.out.println("The recipient's name is invalid, try again.\n");
                }
            }

            if(selection.equalsIgnoreCase("G")){
                input.nextLine();
                System.out.print("Please enter the recipient name: ");
                String recipientName = input.nextLine();
                int arrayIndex = alphabeticalStoring(recipientName);
                PackageStack stack = arrayOfStacks[arrayIndex];
                Package givenPackage;
                int numberOfPackagesMoved = 0;

                while(!stack.isEmpty()){
                    if(stack.peek().getRecipient().equals(recipientName)){
                        System.out.println("Move " + numberOfPackagesMoved + " packages from Stack " + (arrayIndex + 1) + " to floor.");
                        printStacks(arrayOfStacks, FloorStack);
                        break;
                    } else {
                        numberOfPackagesMoved++;
                        FloorStack.push(stack.pop());
                    }
                }

                if(stack.isEmpty()){
                    System.out.println(recipientName+" did not have a package delivered because an it's an empty stack therefore cannot get packages.");
                }
                else {
                    givenPackage = stack.pop();
                    System.out.printf("Give " + recipientName + " %.0f lb package delivered on day " + givenPackage.getArrivalDate() + ".\n" +
                            "Return " + numberOfPackagesMoved + " packages to Stack " + (arrayIndex+1) + " from floor.", givenPackage.getWeight());
                }
                for(int i = 0; i < numberOfPackagesMoved; i++) {
                    try {
                        stack.push(FloorStack.pop());
                    } catch (FullStackException e) {
                        i = numberOfPackagesMoved; //stops the loop, honestly this catch statement is kind of useless, but I don't want to break anything
                        System.out.println("Stack is full, keep the packages on the floor.");
                    }
                }
                printStacks(arrayOfStacks, FloorStack);
            }

            if(selection.equalsIgnoreCase("T")){
                currentDay++;
                int numberOfPackagesReturned = 0;
                for(int i = 0; i < arrayOfStacks.length; i++){
                    int numberOfMovedPackages = 0;

                    while(!arrayOfStacks[i].isEmpty()){
                        if(currentDay - arrayOfStacks[i].peek().getArrivalDate() >= 5) {
                            arrayOfStacks[i].pop();
                            numberOfPackagesReturned++;
                        }
                        else{
                            numberOfMovedPackages++;
                            FloorStack.push(arrayOfStacks[i].pop());
                        }
                    }

                    for(int j = 0; j < numberOfMovedPackages; j++){
                        try {
                            arrayOfStacks[i].push(FloorStack.pop());
                        } catch (FullStackException e) {
                            System.out.println("Stack is full, leaving packages on floor");
                        }
                    }
                }
                System.out.println("It is now day " + currentDay + ". "  + ((numberOfPackagesReturned > 0) ? (numberOfPackagesReturned+" packages have been returned to sender.\n") : "\n"));
            }

            if(selection.equalsIgnoreCase("P")) {
                printStacks(arrayOfStacks, FloorStack);
            }

            if(selection.equalsIgnoreCase("M")){
                System.out.print("Please enter the source stack (enter 0 for floor): ");
                int sourceStack = input.nextInt();
                System.out.print("Please enter the destination stack: ");
                int destinationStack = input.nextInt();

                if(sourceStack < 0 || sourceStack > 5 || destinationStack < 0 || destinationStack > 5)
                    System.out.println("Invalid input\n");
                else {
                    if (sourceStack == 0) {
                        try {
                            arrayOfStacks[destinationStack - 1].push(FloorStack.pop());
                            System.out.println("Package moved successfully from stack " + sourceStack + " to stack " + destinationStack);
                        } catch (FullStackException e) {
                            System.out.println(" As stack " + destinationStack + " was full, " + nearestStack(arrayOfStacks, FloorStack.pop(), FloorStack));
                        } catch (EmptyStackException e) {
                            System.out.println("Source stack is empty. Cannot move a package.");
                        }
                    } else if (destinationStack == 0) {
                        try {
                            FloorStack.push(arrayOfStacks[sourceStack - 1].pop());
                            System.out.println("Package moved successfully from stack " + sourceStack + " to stack " + destinationStack);
                        } catch (EmptyStackException e) {
                            System.out.println("Source stack is empty. Cannot move a package.");
                        }
                    } else {
                        try {
                            arrayOfStacks[destinationStack - 1].push(arrayOfStacks[sourceStack - 1].pop());
                            System.out.println("Package moved successfully from stack " + sourceStack + " to stack " + destinationStack);
                        } catch (FullStackException e) {
                            System.out.println(" As stack " + destinationStack + " was full, " + nearestStack(arrayOfStacks, FloorStack.pop(), FloorStack));
                        } catch (EmptyStackException e) {
                            System.out.println("Source stack is empty. Cannot move a package.");
                        }
                    }
                }
            }

            if(selection.equalsIgnoreCase("F")){
                for(int i = 0; i < arrayOfStacks.length; i++){
                    PackageStack temp = new PackageStack();

                    while(!arrayOfStacks[i].isEmpty()){
                        if(i != alphabeticalStoring(arrayOfStacks[i].peek().getRecipient()) )
                            FloorStack.push(arrayOfStacks[i].pop());
                        else {
                            try {
                                temp.push(arrayOfStacks[i].pop());
                            } catch (FullStackException e) {
                                System.out.println("Bug");//impossible to reach (hopefully)
                            }
                        }
                    }
                    while(!temp.isEmpty()){
                        try {
                            arrayOfStacks[i].push(temp.pop());
                        } catch (FullStackException e) {
                            System.out.println("Bug"); //impossible to reach (hopefully)
                        }
                    }
                }
                System.out.println("Misplaced packages moved to floor.\n");
            }

            if(selection.equalsIgnoreCase("L")){
                input.nextLine();
                System.out.print("Please enter the recipient name: ");
                String recipientName = input.nextLine();
                int totalPackages = 0;
                PackageStack temp = new PackageStack();
                int stackIndex = alphabeticalStoring(recipientName);
                int movedPackages = 0;

                while(!arrayOfStacks[stackIndex].isEmpty()){
                    if(arrayOfStacks[stackIndex].peek().getRecipient().equals(recipientName)) {
                        try {
                            temp.push(arrayOfStacks[stackIndex].peek());
                            totalPackages++;
                            FloorStack.push(arrayOfStacks[stackIndex].pop());
                            movedPackages++;
                        } catch (FullStackException e) {
                            System.out.println(recipientName + "has too many packages");
                        }
                    } else {
                        FloorStack.push(arrayOfStacks[stackIndex].pop());
                        movedPackages++;
                        }
                    }
                for(int j = 0; j < movedPackages; j++){
                    try {
                        arrayOfStacks[stackIndex].push(FloorStack.pop());
                    } catch (FullStackException e) {
                        System.out.println("Stack is full");//Impossible to reach
                    }
                }
                System.out.println(((totalPackages == 0) ? "No packages found for " + recipientName + ".\n" : recipientName + " has " + totalPackages + " package total."));
                for(int i = 0; i < totalPackages; i++) {
                    Package topOfTemp = temp.pop();
                    System.out.printf("Package " + totalPackages + " is in Stack " + (stackIndex + 1) + ", it was delivered on day " + topOfTemp.getArrivalDate() + ", and weighs %.0f lbs.\n", topOfTemp.getWeight());
                }
            }

            if(selection.equalsIgnoreCase("E")){
                while(!FloorStack.isEmpty())
                    FloorStack.pop();
                System.out.println("The floor has been emptied. Mr. Trash Can is no longer hungry.\n");
            }
        }while(!selection.equalsIgnoreCase("Q"));
        input.close();
        System.out.println("Use Amazon Locker next time.\n" +
                "(A-G, H-J, K-M, N-R, S-Z)");
    }
}
