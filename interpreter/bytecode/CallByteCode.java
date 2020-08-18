package interpreter.bytecode;
import interpreter.virtualmachine.VirtualMachine;
/**
 * CALL <funcname> - transfer control to the indicated function.
 */
public class CallByteCode extends ByteCode{
    public String func;
    public int targetAddress;
    
    
    public void init(String arguments[]){
        func = arguments[1];
    }
    
    public void execute(VirtualMachine vm){
        // store the address of where the VM left off at the CallByteCode. 
        // this will be incremented after the ReturnByteCode is executed within 
        // the VM
        vm.pushReturnAddrs(vm.getPc());
        vm.setPc(targetAddress);
    }

    public String getLabel() {
        return func;
    }

    public void setAddress(Integer a) {
        this.targetAddress = a;
    }
    
    public String toString(){
        return "CALL " + func;
    }
}
