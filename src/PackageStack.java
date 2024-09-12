/**
 * This class represents a stack of packages using the Java Stack API
 *
 * @author Sean Shiroma
 *      sean.shiroma@stonybrook.edu
 *      id: 115872064
 *      Recitation 2
 */

import java.util.*;
import java.util.EmptyStackException;

public class PackageStack{
    private final int CAPACITY =  7; //The capacity of the stack
    private int packagesInStack; //The number of packages in the stack
    private Stack<Package> stack; //The stack to hold packages

    /**
     * This constructor creates an instance of the package stack using the Deque interface,
     * which is recommended by the Java Stack implementation documentation
     */
    public PackageStack(){
        packagesInStack = 0;
        stack = new Stack<Package>();
    }

    /**
     * Pushes x onto the top of the stack
     *
     * @param x
     * The package to be pushed
     *
     * @throws FullStackException
     */
    public void push(Package x) throws FullStackException {
        if(packagesInStack == CAPACITY)
            throw new FullStackException();
        packagesInStack++;
        stack.push(x);
    }

    /**
     * Removes the topmost package from the stack and returns it
     *
     * @return
     * the removed package from the stack
     *
     * @throws EmptyStackException
     */
    public Package pop() throws EmptyStackException {
        if(packagesInStack == 0)
            throw new EmptyStackException();
        packagesInStack--;
        return stack.pop();
    }

    /**
     * Shows the topmost package in the stack without removing it
     *
     * @return
     * the topmost package
     *
     * @throws EmptyStackException
     */
    public Package peek() throws EmptyStackException {
        if(packagesInStack == 0)
            throw new EmptyStackException();
        return stack.peek();
    }

    /**
     * Checks if the stack is full
     *
     * @return
     * true if stack is full, false otherwise
     */
    public boolean isFull(){
        return packagesInStack == CAPACITY;
    }

    /**
     * Checks if the stack is empty
     *
     * @return
     * true if stack is empty, false otherwise
     */
    public boolean isEmpty(){
        return packagesInStack == 0;
    }

    /**
     * This method gives a string representation of the stack
     *
     * @return
     * a string representation of the stack
     */
    public String toString(){
        if(isEmpty())
            return "empty.";
        String str = stack.toString().replaceAll(",","");
        return str.substring(1, str.length()-1);
    }













}
