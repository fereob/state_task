package oscarblancarte.ipd.state.states;

import oscarblancarte.ipd.state.Server;

public class StoppingServerState extends AbstractServerState {

    public StoppingServerState(final Server server) {
        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
//                    System.out.println("Server Stopping");
                    Thread.sleep(10 * 10);
                    if (server.getMessageProcess().countMessage() 
                            >= server.getMaxRequest()) {
                        server.setState(
                                new SaturatedServerState(server));
                    } else if(server.getMessageProcess().countMessage() != 0) {
                        this.run();
                    }else {
                        server.setState(new StopServerState(server));
                    }
                } catch (Exception e) {
                }
            }
        }
        ).start();
        System.out.println("Server Stop");
    }

    @Override
    public void handleMessage(Server server, String message) {
        System.out.println("The server is stopping");
    }

}
