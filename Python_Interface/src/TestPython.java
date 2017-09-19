import java.io.IOException;
import java.util.ArrayList;

import net.razorvine.pyro.*;

public class TestPython {

	public static void main(String args[]) throws IOException {
		NameServerProxy ns = NameServerProxy.locateNS(null);
		PyroProxy remoteObject = new PyroProxy(ns.lookup("GreetingMaker"));
		Object result = remoteObject.call("get_fortune", "Vishal");
		String message = (String)result;  // cast to the type that 'pythonmethod' returns
		System.out.println("result message="+message);
		
		
		System.out.println("calling numpy methods");
		Object intArray = remoteObject.call("get_array");
		ArrayList <Integer> x = (ArrayList <Integer>)intArray;
		
		System.out.println("X value is"+x);
		remoteObject.close();
		ns.close();
	}
}
//https://pythonhosted.org/Pyro4/tipstricks.html?highlight=numpy#pyro-and-numpy