public class Device extends Thread {
    private String name;
    private String type;
    private Router router;

    public Device(String name, String type, Router router)
    {
        this.name = name;
        this.type = type;
        this.router = router;
    }

//    public String getName()
//    {
//        return this.name;
//    }
    @Override // the Threads run method
    public void run()
    {
        connect();
        performActivity();
        logout();
    }
    // define each of the run functions
    private void connect()
    {
        System.out.println(name + " " + type + " Connected");
        router.occupyConnection(this);
    }
    private void performActivity()
    {
        System.out.println(name + " " + type + " Connection number " + router.connectionNumber(this) + " Is performing online activity");
        try {
            Thread.sleep(5000);
        }
        catch (InterruptedException e)
        {
            System.out.println(e.getMessage());
        }
    }
    private void logout()
    {
        System.out.println(name + " " + type + " Connection number " + router.connectionNumber(this) + " Logged out");
        router.releaseConnection(this);
    }
}
