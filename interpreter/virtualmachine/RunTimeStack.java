package interpreter.virtualmachine;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;


public class RunTimeStack {

    private ArrayList<Integer> runTimeStack;
    private Stack<Integer> framePointer;


        /**
         * @implNote : Constructor
         */
    public RunTimeStack() {
            runTimeStack = new ArrayList<>();
            framePointer = new Stack<Integer>();
            /* Add initial Frame Pointer, main is the entry*/
            /* point of our language, so its frame pointer is 0.*/
            framePointer.add(0);
    }
	
    /**
     * Used for dumping the current state of the runTimeStack .
     * It will print portions of the stack based on respective
     * frame markers .
     * Example [1 ,2 ,3] [4 ,5 ,6] [7 ,8]
     * Frame pointers would be 0 ,3 ,6 ,8
     */
    public void dump() {
       ArrayList<Integer> runTimeStackClone = (ArrayList)runTimeStack.clone();
       ArrayList<Integer> framePointerClone = (ArrayList)framePointer.clone();

        ArrayList frameArray[] = new ArrayList[runTimeStackClone.size()];
        for(int i = framePointerClone.size(); i > 0; i++ )
        {
            frameArray[i-1] = new ArrayList<>();
            int startIndex = framePointerClone.get(i-1);
            int currentSize = runTimeStackClone.size();
            System.out.println("[");
            for(int j = startIndex; j < currentSize; j++)
            {
                System.out.print(runTimeStackClone.get(j));
                if(j != startIndex -1)
                {
                    System.out.print(",");
                }
            }
        }
        for(int i = 0; i < framePointerClone.size(); i++){
            System.out.print(frameArray[i].toString());
        }
        System.out.println("]");

    }

    /**
     * returns the top of the runtime stack , but does not remove
     * @return copy of the top of the stack .
     */
	public int peek(){
		if(runTimeStack.size()==0){
			return 0;
		}
        return runTimeStack.get(runTimeStack.size()-1);
    }
	
	/**
	 * @return pop the top item from the runtime stack
	 */
    public int pop(){
    	if(runTimeStack.size()==0){
			return 0;
		}
        int top = runTimeStack.get(runTimeStack.size()-1);
        runTimeStack.remove(runTimeStack.size()-1);
        return top;
    }
    
    /**
    * @param i value to be pushed .
    * @return value pushed
    * @implNote push the value i to the top of the stack .S
    */
    public int push( int i){
    	runTimeStack.add(i);
        return i;
    }
    
    
    /**
     * @param offsetFromTopOfRunStack slots down from the top of the runtime stack
     * @implNote create a new frame pointer at the index offset slots down
     * from the top of the runtime stack .
     */
    public void newFrameAt(int offsetFromTopOfRunStack){
        int size = runTimeStack.size();
        framePointer.push(size-offsetFromTopOfRunStack);
        
    }
            
    /**
     * @implNote : Pop the top frame when we return from a function. Before popping the
     * function's return value is at the top of the stack so we'll save the 
     * value, pop the top frame then push the return value.
     */
    public void popFrame(){
        int top = this.pop();
        int currentSize = runTimeStack.size();
        int startIndex = framePointer.pop();
        for ( int i=startIndex; i<currentSize; i++){
        	runTimeStack.remove(startIndex);
        }
        this.push(top);
    }
            
    /**
     * Takes the top item of the run time stack , and stores
     * it into a offset starting from the current frame .
     * @param offsetFromFramePointer number of slots above current frame marker
     * @return the item just stored
     */	
    public int store(int offsetFromFramePointer){
        int top = this.pop();
        runTimeStack.add(framePointer.peek()+offsetFromFramePointer, top);
        runTimeStack.remove(framePointer.peek()+offsetFromFramePointer+1);
        return top;
    }
    
    /**
     * Takes a value from the run time stack that is at offset
     * from the current frame marker and pushes it onto the top of
     * the stack .
     * @param offsetFromFramePointer number of slots above current frame marker
     * @return item just loaded into the offset
     */
     public int load( int offsetFromFramePointer ) {
        return runTimeStack.get(framePointer.peek()+offsetFromFramePointer);
    }
    
    /**
     * @implNote : Used to load literals onto the stack - e.g. for lit 5 we call push(5)
     */
    public Integer push(Integer i){
    	runTimeStack.add(i);
        return i;
    }
    /**
     * @implNote : Returns the difference between the current slot in the runStack and the
     * position of the current frame pointer.
     */
    public int currentOffset(){
        int offset = (runTimeStack.size()-1) -framePointer.peek();
        return offset;
    }
}
