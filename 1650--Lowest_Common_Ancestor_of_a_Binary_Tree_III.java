//Traverse use list, Time O(n) Space O(n)
//set slower than list
class Solution {
    public Node lowestCommonAncestor(Node p, Node q) {
        List<Node> parentsP = new ArrayList<>();
        parentsP.add(p);
        while (p.parent != null){
            parentsP.add(p.parent);
            p = p.parent;
        }
        
        if (parentsP.contains(q)) return q; 
        while (q.parent != null){
            if (parentsP.contains(q.parent)) return q.parent;
            q = q.parent;
        }
        return null;
    }
}

//2. two pointers, faster why?
//Since it is guaranteed that there will be a LCA, we can use two pointers for each list. 
//When we reach the end of one list, switch it to point at the beginning of the other list until p1 is p2.
public Node lowestCommonAncestor(Node p, Node q) {
     Node p1 = p, p2 = q;
	 while (p1 != p2) {
		 p1 = p1 == null ? q : p1.parent;
		 p2 = p2 == null ? p : p2.parent;    
	  }
	  return p1;
}
