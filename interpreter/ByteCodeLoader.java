
package interpreter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import interpreter.bytecode.ByteCode;
import interpreter.virtualmachine.Program;

public class ByteCodeLoader extends Object {

	private BufferedReader byteSource;

	/**
	 * opens up the file and puts in in a scanner to be read into a Program
	 */
	public ByteCodeLoader(String file) throws IOException {
		URL path = ClassLoader.getSystemResource(file);
		if (path == null) {
		//	 The file was not found, insert error handling here
		}
		File file1 = null;
		try {
		file1 = new File(path.toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		this.byteSource = new BufferedReader(new FileReader(file));
	}

	/**
	 * This function should read one line of source code at a time. For each
	 * line it should: Tokenize string to break it into parts. Grab THE correct
	 * class name for the given ByteCode from CodeTable Create an instance of
	 * the ByteCode class name returned from code table. Parse any additional
	 * arguments for the given ByteCode and send them to the newly created
	 * ByteCode instance via the init function.
	 */
	public Program loadCodes() {
		Program prog = new Program();
		try {
			String line = byteSource.readLine();
			while (line != null) {
				String lineTokens[] = line.split(" ");
				Class byteCodeType;

				byteCodeType = CodeTable.get(lineTokens[0]);

				ByteCode theByteCode = (ByteCode) byteCodeType.newInstance();
				theByteCode.init(lineTokens);
				prog.pushByteCode(theByteCode);
//				System.out.println(lineTokens[0]);
				line = byteSource.readLine();

			}
		} catch (Exception e) {
		}
		prog.resolveAddress();

		return prog;
	}

}
