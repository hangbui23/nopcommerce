package factoryBrowser;

public class BrowserNotSupportException extends IllegalStateException {
	 static final long serialVersionUID = 3540548682203339240L;
	public BrowserNotSupportException(String browserName) {
			super(String.format("Browser not supported %s", browserName));
	}
}
