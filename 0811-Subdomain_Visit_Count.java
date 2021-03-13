//1. HashMap Time O(n), Space O(n) 
class Solution {
    public List<String> subdomainVisits(String[] cpdomains) {
        HashMap<String, Integer> count = new HashMap<String, Integer>();
        List<String> res = new ArrayList<String>();
        for (String cpdomain : cpdomains) {
            int visit = Integer.valueOf(cpdomain.split("\\s")[0]);
            String name = cpdomain.split("\\s")[1];
            String[] domains = name.split("\\.");
            String str = "";
            for (int i = domains.length - 1; i >= 0; i--) {
                if (i == domains.length - 1) str = domains[domains.length - 1];
                else str = domains[i] + "." + str;
                count.put(str, visit + count.getOrDefault(str, 0));
            }    
        }
        for (String domain : count.keySet()) {
            res.add(String.valueOf(count.get(domain)) + " " + domain);
        }
        return res;
    }
}
