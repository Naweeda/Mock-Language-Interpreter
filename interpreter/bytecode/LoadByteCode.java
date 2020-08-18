package interpreter.bytecode;
import interpreter.virtualmachine.VirtualMachine;
/**
 *
 */
public class LoadByteCode extends ByteCode{
    
    int currentFrameOffset;
    String id;
    
    public void init(String arguments[]){
        currentFrameOffset = Integer.parseInt(arguments[1]);
        id = arguments[2];
    }
    
    public void execute(VirtualMachine vm){
        int var = vm.getValueAtOffset(currentFrameOffset);
        vm.pushRunTimeStack(var);
    }
    
    
    public String toString(){
        return "LOAD " + currentFrameOffset + " " + id + "      " + "<load " +id + ">";
    }
}
