public class Router {
    // Mock router class class keda w keda
    private int connections;
    public Router()
    {
        this.connections = 10;
    }
    public Router(int connections)
    {
        this.connections = connections;
    }
    public void occupyConnection(Device device)
    {
        System.out.println(device.getName() + " Occupying");
    }
    public void releaseConnection(Device device)
    {
        System.out.println(device.getName() + " Release");
    }
    public int connectionNumber(Device device)
    {
        return (connections);
    }
}
