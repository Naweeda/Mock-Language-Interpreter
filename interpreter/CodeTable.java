/**
 * Code table of byte codes in language X
 * @key name of a specific byte code
 * @value name of the class that the key belongs to.
 * returns Class name as a string.
 */
package interpreter;

import java.util.HashMap;

import interpreter.bytecode.ArgsByteCode;
import interpreter.bytecode.BopByteCode;
import interpreter.bytecode.CallByteCode;
import interpreter.bytecode.DumpByteCode;
import interpreter.bytecode.FalseBranchByteCode;
import interpreter.bytecode.GoToByteCode;
import interpreter.bytecode.HaltByteCode;
import interpreter.bytecode.LabelByteCode;
import interpreter.bytecode.LitByteCode;
import interpreter.bytecode.LoadByteCode;
import interpreter.bytecode.PopByteCode;
import interpreter.bytecode.ReadByteCode;
import interpreter.bytecode.ReturnByteCode;
import interpreter.bytecode.StoreByteCode;
import interpreter.bytecode.WriteByteCode;

public class CodeTable {
    
    private static HashMap<String, Class> codeTable = new HashMap<String,Class>();
    
    /**
     * byte code name
     * @return class of that bytecode
     */

    private CodeTable(){}

    public static void init(){
    	  // for each type of ByteCode, create a hash {"type" => ClassOfByteCode}.
    	codeTable.put("HALT", HaltByteCode.class);
    	codeTable.put("POP", PopByteCode.class);
    	codeTable.put("FALSEBRANCH", FalseBranchByteCode.class);
    	codeTable.put("GOTO", GoToByteCode.class);
    	codeTable.put("STORE", StoreByteCode.class);
    	codeTable.put("LOAD", LoadByteCode.class);
    	codeTable.put("LIT", LitByteCode.class);
    	codeTable.put("ARGS", ArgsByteCode.class);
    	codeTable.put("CALL", CallByteCode.class);
    	codeTable.put("RETURN", ReturnByteCode.class);
    	codeTable.put("BOP", BopByteCode.class);
    	codeTable.put("READ", ReadByteCode.class);
    	codeTable.put("WRITE", WriteByteCode.class);
    	codeTable.put("LABEL", LabelByteCode.class);
    	codeTable.put("DUMP", DumpByteCode.class);
          
    }

	public static Class get(String byteCodeName){
		return codeTable.get(byteCodeName);
	}

}
