
//1. mine
public class ZigzagIterator {
    public List<Integer> v1;
    public List<Integer> v2;
    public boolean isV1 = true;;
    public int curr1 = 0;
    public int curr2 = 0;
    public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
        this.v1 = v1;
        this.v2 = v2;
    }

    public int next() {
        int res = 0;
        if (isV1){
            if (curr1 < v1.size()){
                res = v1.get(curr1);
                curr1++;
            }
            else {
                res = v2.get(curr2);
                curr2++;
            }
            isV1 = false;
        }
        else{
            if (curr2 < v2.size()){
                res = v2.get(curr2);
                curr2++;
            }
            else {
                res = v1.get(curr1);
                curr1++;
            }
            isV1 = true;            
        }
        return res;
    }

    public boolean hasNext() {
        if (curr1 + curr2 >= v1.size() + v2.size()) return false;
        else return true;
    }
}

/**
 * Your ZigzagIterator object will be instantiated and called as such:
 * ZigzagIterator i = new ZigzagIterator(v1, v2);
 * while (i.hasNext()) v[f()] = i.next();
 */
