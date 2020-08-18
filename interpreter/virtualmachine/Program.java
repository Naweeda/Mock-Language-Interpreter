package interpreter.virtualmachine;

import java.util.ArrayList;
import java.util.HashMap;
import interpreter.bytecode.CallByteCode;
import interpreter.bytecode.FalseBranchByteCode;
import interpreter.bytecode.GoToByteCode;
import interpreter.bytecode.LabelByteCode;
import interpreter.bytecode.ByteCode;

public class Program {

    private ArrayList<ByteCode> program;

    // private ArrayList<ByteCode> byteCodeList = new ArrayList<>();

    public Program() {
        program = new ArrayList<>();
    }


    //Method responsible to get bytecode
    protected ByteCode getCode(int pc) {
        return this.program.get(pc);
    }

    public void pushByteCode(ByteCode code){
        program.add(code);
    }

    //Return bytecode size
    public int getNumberOfByteCodes(){
        return this.program.size();
    }

    /**
     * This function should go through the program and resolve all addresses.
     * Currently all labels look like LABEL <<num>>>, these need to be converted into
     * correct addresses so the VirtualMachine knows what to set the Program Counter(PC)
     * HINT: make note what type of data-stucture bytecodes are stored in.
     */
    public void resolveAddress(){
        HashMap<String,Integer> addresses = new HashMap<String,Integer>();
        // Map each Name associated with a Label byte code to the address
        // of that label byte code. 
        for ( int i=0; i<program.size(); i++){
            if (program.get(i).getClass().getName()=="interpreter.bytecode.LabelByteCode") {
                String label = ((LabelByteCode)program.get(i)).label;
                addresses.put(label, i);
            }
        }
        // Run through the ByteCodes a second time to resolve the String targets
        // of GOTO, CALL, and FALSEBRANCH bytecodes to the integer address
        // of the corresponding LABEL bytecode. 

        for ( int i=0; i<program.size(); i++){

        	//CALL bytecodes
            if (program.get(i).getClass().getName().equals("interpreter.bytecode.CallByteCode")) {
                CallByteCode call = ((CallByteCode)program.get(i));
                String label = call.func;
                call.targetAddress = addresses.get(label);
            }
            
            // GOTO bytecodes
            if (program.get(i).getClass().getName().equals("interpreter.bytecode.GoToByteCode")) {
                GoToByteCode gotoCode = ((GoToByteCode)program.get(i));
                String label = gotoCode.label;
                gotoCode.targetAddress = addresses.get(label);
            }
            
            // FALSEBRANCH
            if (program.get(i).getClass().getName().equals("interpreter.bytecode.FalseBranchByteCode")) {
                FalseBranchByteCode branchCode = ((FalseBranchByteCode)program.get(i));
                String label =  branchCode.targetLabel;
                branchCode.targetAddress = addresses.get(label);
            }
            
        }
        
    }
    
}
