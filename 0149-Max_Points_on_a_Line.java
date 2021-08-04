//Time O(n^2), Space O(n)
/*
look for all possible rays starting at point[i]
all points on a ray share: the same starting point and the same slope
find the max number of points on such a ray
keep track of points on a ray in a map[slope]=counter
the slope is a string key 'dx/dy' (float is not precise enough)
the duplicates of the starting point are valid for all rays with that starting point, so we apply it to the ray with the biggest number of points

     *  A line is determined by two factors,say y=ax+b
     *  
     *  If two points(x1,y1) (x2,y2) are on the same line(Of course). 
     *  Consider the gap between two points.
     *  We have (y2-y1)=a(x2-x1),a=(y2-y1)/(x2-x1) a is a rational, b is canceled since b is a constant
     *  If a third point (x3,y3) are on the same line. So we must have y3=ax3+b
     *  Thus,(y3-y1)/(x3-x1)=(y2-y1)/(x2-x1)=a
     *  Since a is a rational, there exists y0 and x0, y0/x0=(y3-y1)/(x3-x1)=(y2-y1)/(x2-x1)=a
     *  So we can use y0&x0 to track a line;
     */
    
class Solution {
    public int maxPoints(int[][] points) {
        int n = points.length;
        if (n <= 2) return n;
        int res = 0;

        for (int i = 0; i < n; i++){
            //new map for each point
            //slope co-prime, count            
            Map<String, Integer> map = new HashMap<>();            
            int max = 0;
            int duplicate = 0;
            
            int x1 = points[i][0];
            int y1 = points[i][1];
            // start with i+1, since if any previous point is on the same line,
            // then this was already calculated then that point was a starting point 
            for (int j = i + 1; j < n; j++){
                int x2 = points[j][0];
                int y2 = points[j][1];
                
                int dx = x2 - x1;
                int dy = y2 - y1;
                
                if (dx == 0 && dy == 0) duplicate++;
                
                else{
                    // we need the slope: dx/dy. but float rounds up the end and produces slightly different results,
                    // so instead we keep both dx and dy as the key.
                    // to make them identical for the identical slope, use GCD: greatest common divisor         
                    // dx and dy define the slope.
                    // we keep the map for the current point i, so the full key is point[i]+slope excludes parallel lines.
                    // vertical line: dx==0, horizontal line: dy==0. GCD will set vertical: dx=0, dy=1, horizontal: dx=1, dy=0                     
                    int gcd = generateGCD(dx, dy);
                    dx = dx / gcd;
                    dy = dy / gcd;
                    String key = dx + ":" + dy;
                    map.put(key, map.getOrDefault(key, 0) + 1);
                    max = Math.max(max, map.get(key));
                }
                // duplicates should increase our best result found with point[i]     
                res = Math.max(res, max + duplicate + 1);
            }
        }     
        return res;
    }
    
    public int generateGCD(int a, int b){
        if (b == 0) return a;
        return generateGCD(b, a % b);
    }
}
