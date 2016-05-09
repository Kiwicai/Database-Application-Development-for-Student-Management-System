import java.io.OutputStream;
import java.io.PrintStream;
import javax.swing.SwingUtilities; 
import javax.swing.JTextArea;

public class ConsolePrintStream extends PrintStream {
	private JTextArea text;
	private StringBuffer sb = new StringBuffer();
	
	public ConsolePrintStream(OutputStream out, JTextArea text) {
		super(out);
		this.text = text;
	}

/**
     * �ؽ�write����,���еĴ�ӡ������Ҫ���õķ���
*/
	public void write(byte[] buf, int off, int len) {
		final String message =new String(buf, off, len);
        SwingUtilities.invokeLater(new Runnable() {  
            public void run() {  
                sb.append(message);  
                text.setText(sb.toString());  
            }  
        });  
    }
}