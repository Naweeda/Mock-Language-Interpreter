package interpreter.bytecode;
import interpreter.virtualmachine.VirtualMachine;
/**
 * LIT n - the VM loads the value n to the top of the stack.
 */
public class LitByteCode extends ByteCode{
    protected int value;
    protected String varName = "";
    
    public void init(String arguments[]){
        value = Integer.parseInt(arguments[1]);
        if (arguments.length>2) varName = arguments[2];
    }    
    
    public void execute(VirtualMachine vm){
        vm.pushRunTimeStack(value);
    }
    
    public String toString(){
        return "LIT " + value + " " + varName + "  " + "int " + varName;
    }
}
