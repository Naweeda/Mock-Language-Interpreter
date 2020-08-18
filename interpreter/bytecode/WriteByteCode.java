package interpreter.bytecode;
import interpreter.virtualmachine.VirtualMachine;
/**
 * WRITE: 
 * Write the value on top of the stack to output; Leave the value on top of the 
 * stack.
 */
public class WriteByteCode extends ByteCode{
    
    public void init(String arguments[]){}
    
    // Every bytecode will be responsible for its own execution. 
    public void execute(VirtualMachine vm){
        System.out.println(vm.peekRunTimeStack());
    }
    
    public String toString(){
        return "WRITE ";
    }
}
