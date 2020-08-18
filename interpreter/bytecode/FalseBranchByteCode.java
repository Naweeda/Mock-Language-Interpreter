package interpreter.bytecode;
import interpreter.virtualmachine.VirtualMachine;
/**
 *Ex: FALSEBRANCH <label> 
 * This pops the top of the stack and checks whether or not it is false (0).
 * If it is false, then branch to <label>, else execute the next byte code.
 */
public class FalseBranchByteCode extends ByteCode{
    
    public String targetLabel;
    public int targetAddress;


    public void init(String arguments[]){
        targetLabel = arguments[1];
    }

    public void execute(VirtualMachine vm){
        if (vm.popRunTimeStack()==0) vm.setPc(targetAddress);
       }

    public String getLabel() {
        return targetLabel;
    }

    public void setAddress(Integer a) {
        this.targetAddress = a;
    }

    public String toString(){
        return "FALSEBRANCH " + targetLabel;
    }
}
