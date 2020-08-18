package interpreter.bytecode;
import interpreter.virtualmachine.VirtualMachine;

/**
 *
 * The ByteCode objects represent individual machine code commands and are contained within
 * instances of the Program class.
 */
public abstract class ByteCode {

    /**
     * @param arguments
     * @implNote Every bytecode will be responsible for its own initialization
     */
    public void init(String arguments[]){}
    
    /**
     * @param vm : vm
     * @implNote : Every bytecode will be responsible for its own execution.
     */
    abstract public void execute(VirtualMachine vm);
    
    public String toString(){
        return "";
    }
    
}
