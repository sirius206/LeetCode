//HashMap Time O(1), Space O(n)
class Logger {

    /** Initialize your data structure here. */
    private HashMap<String, Integer> hm;
    public Logger() {
        this.hm = new HashMap<String, Integer>();
    }
    
    /** Returns true if the message should be printed in the given timestamp, otherwise returns false.
        If this method returns false, the message will not be printed.
        The timestamp is in seconds granularity. */
    public boolean shouldPrintMessage(int timestamp, String message) {
        if (message.equals("")) return false;
        if (!hm.containsKey(message) || hm.get(message) + 10 <= timestamp) {
            hm.put(message, timestamp);
            return true;
        } 
        else return false;
    }
}

/**
 * Your Logger object will be instantiated and called as such:
 * Logger obj = new Logger();
 * boolean param_1 = obj.shouldPrintMessage(timestamp,message);
 */
