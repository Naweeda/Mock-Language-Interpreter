package interpreter.virtualmachine;
import java.util.*;
import interpreter.bytecode.*;
import interpreter.virtualmachine.Program;
import interpreter.virtualmachine.RunTimeStack;

public class VirtualMachine {
    


    private RunTimeStack runTimeStack;
    private Stack<Integer> returnAddress;
    private Program program;
    private int pc;
    private boolean isRunning;
    public boolean dumping = false;
    boolean haltReached=false;
           
            
    public VirtualMachine(Program prog){
        program = prog;
        
    }
    
    
    /**
     * @implNote : Each time a function is entered in the Program, the address the VM should
     * return to once the Program returns from the function is pushed onto the returnAddrs stack.
     * Every ByteCode object should have an exectute( VirtualMacine ) method. 
     */
    public void executeProgram() {
        pc = 0;
        runTimeStack = new RunTimeStack();
        returnAddress = new Stack();
        isRunning = true;
        BinaryOpTable.init();

        /*VM should be responsible for dumping to Console. */
        while (isRunning) {
            ByteCode bytCode = program.getCode(pc);
            bytCode.execute(this);
            // only dump if the current code is not DUMP and dumping is set.

            if (!bytCode.getClass().getName().equals("interpreter.ByteCode.DumpByteCode") && dumping) {
                {
                    System.out.println(bytCode.toString());
                    runTimeStack.dump();
                }

            }
            pc++;
        }

        //if (!bytCode.getClass().getName().equals("interpreter.ByteCode.DumpByteCode") && dumping) {
            //if (!(bytCode instanceof DumpByteCode)&& dumping)
            //{
                //System.out.println(bytCode.toString());
                //runTimeStack.dump();
            //}

        //}
        //pc++;
    }

    /**
     * @implNote : Increment Count
     */
    public void incrementPc(){
        pc++;
    }
    
    /**
     * @param n
     * @implNote : Set Value
     */
    public void setPc(int n){
        pc = n;
    }
    
    /**
     * @return PC value
     * @implNote : Setter
     */
    public int getPc(){
        return pc;
    }
    
    /**
     * @param n
     * @implNote : Push and return Address
     */
    public void pushReturnAddrs(int n){
        returnAddress.push(n);
    }
    
    /**
     * @return
     */
    public int popReturnAddrs(){
        return returnAddress.pop();
    }
    
    /**
     * @implNote : end virtualExecution 
     */
    public void turnOffVm(){
        isRunning = false;
        haltReached = true;
    }
    
    /**
     * @return
     */
    public boolean doneExecuting(){
        return haltReached;
    }
    
    /**
     * @param numArguments
     */
    public void newFrameOnRunTimeStackAt( int numArguments){
        runTimeStack.newFrameAt(numArguments);
    }
    /**
     * @return
     */
    public int popRunTimeStack(){
        return runTimeStack.pop();
    }
    /**
     * @param n
     */
    public void pushRunTimeStack(int n){
        runTimeStack.push(n);
    }
    
    /**
     * @param n
     * @return
     */
    public int getValueAtOffset(int n){
        return runTimeStack.load(n);
    }
    /**
     * 
     */
    public void poprunTimeStackFrame(){
        runTimeStack.popFrame();
    }
    /**
     * @param offset
     * @return storerunTimeStack
     */
    public int storeRunTimeStack(int offset){
        return runTimeStack.store(offset);
    }
    
    /**
     * 
     * @return peekrunTimeStack
     */
    public int peekRunTimeStack(){
        return runTimeStack.peek();
    }
    
    /**
     * @return CurrentOffset
     */
    public int getCurrentOffset(){
        return runTimeStack.currentOffset();
    }

}
