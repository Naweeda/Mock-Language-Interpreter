package interpreter.bytecode;
import interpreter.virtualmachine.VirtualMachine;
public class GoToByteCode extends ByteCode{
    
    public String label;
    public int targetAddress;
    
    public void init(String arguments[]){
        label = arguments[1];
    }
    
    public void execute(VirtualMachine vm){
        vm.setPc(targetAddress);
    }
    public String getLabel() {
        return label;
    }

    public void setAddress(Integer a) {
        this.targetAddress= a;
    }
    
    public String toString(){
        return "GOTO " + label;
    }
    
}
