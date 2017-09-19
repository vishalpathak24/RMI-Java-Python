# saved as greeting-server.py
import Pyro4
import numpy

@Pyro4.expose
class GreetingMaker(object):
    def get_fortune(self, name):
        return "Hello, {0}. Here is your fortune message:\n" \
               "Behold the warranty -- the bold print giveth and the fine print taketh away.".format(name)
    def get_array(self):
    	return numpy.ones(3).tolist()

daemon = Pyro4.Daemon()                # make a Pyro daemon
ns = Pyro4.locateNS()				   # nameserver
uri = daemon.register(GreetingMaker)   # register the greeting maker as a Pyro object
ns.register("GreetingMaker", uri)   # register the object with a name in the name server

print("Ready.")
daemon.requestLoop()                   # start the event loop of the server to wait for calls

