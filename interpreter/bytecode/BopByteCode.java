package interpreter.bytecode;
import interpreter.virtualmachine.VirtualMachine;
/**
 * BOP <binary op> 
 * Pop the top two levels of the stack an perform indicated operation : 
 * operations are: +-/* == != <= > >= > | &. 
 * 
 * The lower level is the first operand.
 */
public class BopByteCode extends ByteCode{
    
    String binaryOp;
    
    
    
    public void init(String arguments[]){
        binaryOp = arguments[1];
    }
    
    public void execute(VirtualMachine vm){
        BinaryOperation op = BinaryOpTable.get(binaryOp);
        
        int secondOperand = vm.popRunTimeStack();
        int firstOperand = vm.popRunTimeStack();
        
        int result = op.binaryOp(firstOperand, secondOperand);
        vm.pushRunTimeStack(result);
    }
    
    public String toString(){
        return "BOP " + binaryOp;
    }
}


