package interpreter.bytecode;
import interpreter.virtualmachine.VirtualMachine;

/**
 * Stops the VirtualMachine and quits the program.
 */
public class HaltByteCode extends ByteCode{
    
    public void execute(VirtualMachine vm){
        vm.turnOffVm();
    }
}
