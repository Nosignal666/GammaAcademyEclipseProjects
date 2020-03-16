package corsojava.Pattern;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class UpperCaseDecorator extends FilterInputStream {
	protected UpperCaseDecorator(InputStream arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}
	

	@Override
	public int read() throws IOException {
		return super.read();
	}

	@Override
	public int read(byte[] b, int off, int len) throws IOException {
		return super.read(b, off, len);
	}

	@Override
	public int read(byte[] b) throws IOException {
		return super.read(b);
	}
}
