package interpreter.bytecode;
import interpreter.virtualmachine.VirtualMachine;
/**
 *RETURN <funcname> - Return from the current function; <funcname> is used 
 * as a comment to indicate the current function.
 */
public class ReturnByteCode extends ByteCode{
    
    String func = "";
    
    public void init(String arguments[]){
        if (arguments.length>1) func = arguments[1];
    }
    
    // Every bytecode will be responsible for its own execution. 
    public void execute(VirtualMachine vm){
        vm.setPc(vm.popReturnAddrs());
        vm.poprunTimeStackFrame();
    }
    
    public String toString(){
        return "RETURN " + func;
    }
}
