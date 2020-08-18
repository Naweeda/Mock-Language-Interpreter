package interpreter.bytecode;
import interpreter.virtualmachine.VirtualMachine;
/**
 *Pop the top n levels of the runtime stack
 */
public class PopByteCode extends ByteCode{
    int numLevels;
    
    public void init(String arguments[]){
        numLevels = Integer.parseInt(arguments[1]);
    }
    
    public void execute(VirtualMachine vm){
        for(int i=0; i<numLevels; i++) vm.popRunTimeStack();
        
    }
    
    public String toString(){
        return "POP " + numLevels;
    }
}
