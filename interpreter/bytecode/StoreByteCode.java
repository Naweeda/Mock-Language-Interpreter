package interpreter.bytecode;
import interpreter.virtualmachine.VirtualMachine;
/**
 * STORE n <id>
 * - pop the top of the stack; store value into the offset n from the 
 * start of the frame;  <id> is used as a comment, it's the variable name 
 * where the data is stored.
 */
public class StoreByteCode extends ByteCode{
    
    int currentFrameOffset;
    String id = "";
    int value;
    
    public void init(String arguments[]){
        currentFrameOffset = Integer.parseInt(arguments[1]);
        if (arguments.length>2) id = arguments[2];
    }
    
    public void execute(VirtualMachine vm){
        value = vm.storeRunTimeStack(currentFrameOffset);
    }
    
    public String toString(){
        return "STORE " + currentFrameOffset + " " + id + "     " + id + " = " + value;
    }
    
}
