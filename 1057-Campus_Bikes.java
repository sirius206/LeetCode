//1.  Greedy, heap
//Time O(m * n * log(m * n))   log for heap insertion, Space O(m*n)?
   public int[] assignBikes(int[][] workers, int[][] bikes) {
        int n = workers.length;
        
        // order by Distance ASC, WorkerIndex ASC, BikeIndex ASC
        PriorityQueue<int[]> q = new PriorityQueue<int[]>((a, b) -> {
            int comp = Integer.compare(a[0], b[0]);
            if (comp == 0) {
                if (a[1] == b[1]) {
                    return Integer.compare(a[2], b[2]);
                }
                
                return Integer.compare(a[1], b[1]);
            }
            
            return comp;
        });
            
        // loop through every possible pairs of bikes and people,
        // calculate their distance, and then throw it to the pq.
        for (int i = 0; i < workers.length; i++) {
            
            int[] worker = workers[i];
            for (int j = 0; j < bikes.length; j++) {
                int[] bike = bikes[j];
                int dist = Math.abs(bike[0] - worker[0]) + Math.abs(bike[1] - worker[1]);
                q.add(new int[]{dist, i, j}); 
            }
        }
        
        // init the result array with state of 'unvisited'.
        int[] res = new int[n];
        Arrays.fill(res, -1);
        
        // assign the bikes.
        Set<Integer> bikeAssigned = new HashSet<>();
        while (bikeAssigned.size() < n) {
            int[] workerAndBikePair = q.poll();
            if (res[workerAndBikePair[1]] == -1 
                && !bikeAssigned.contains(workerAndBikePair[2])) {   
                
                res[workerAndBikePair[1]] = workerAndBikePair[2];
                bikeAssigned.add(workerAndBikePair[2]);
            }
        }
        
        return res;
    }


//2. similar but use class
class node{
        int dist;
        int worker;
        int bike;
        public node(int dist, int worker, int bike){
            this.dist = dist;
            this.worker = worker;
            this.bike = bike;
        } 
    }
    public int[] assignBikes(int[][] workers, int[][] bikes) {
        int[] ans = new int[workers.length];
        Arrays.fill(ans,-1);
        boolean[] used = new boolean[bikes.length];
        int cnt = 0;
        PriorityQueue<node> heap = new PriorityQueue<node>(new Comparator<node>(){
             @Override
            public int compare(node o1, node o2){
                if(o1.dist == o2.dist){
                    if(o1.worker == o2.worker){
                        return o1.bike - o2.bike;
                    }
                    return o1.worker - o2.worker;
                }else{
                    return o1.dist - o2.dist;
                }
            }
        });
            
     
        for(int i = 0; i < workers.length; i++){
            for(int j = 0; j < bikes.length; j++){
                int dist = calDist(workers[i],bikes[j]);
                node n = new node(dist,i,j);
                heap.offer(n);
            }
        }
        while(cnt != workers.length){
            node cur = heap.poll();
            int index = cur.worker;
            int bike = cur.bike;
            if(ans[index] == -1 && !used[bike]){
                ans[index] = bike;
                used[bike] = true;
                cnt++;
            }
        }
        return ans;
        
    }
    public int calDist(int[] worker, int[] bikes){
        return Math.abs(worker[0] - bikes[0]) + Math.abs(worker[1] - bikes[1]);
    }

//3. bucket sort since dist < 2000, Time O(m * n) Space O(m * n)
public int[] assignBikes(int[][] workers, int[][] bikes) {
        List<int[]>[ ] buckets = new ArrayList[2001];
        
        for (int i=0; i<workers.length; i++) {
            for (int j=0; j<bikes.length; j++) {
                int dist = manDist(workers[i], bikes[j]);
                if (buckets[dist] == null) {
                    buckets[dist] = new ArrayList<int[]>();
                }
                buckets[dist].add(new int[] {i, j});               
            }
        }
        
        boolean[] bikeVisited = new boolean[bikes.length];
        int[] result = new int[workers.length];
        Arrays.fill(result, -1);
        // Buckets[dist] is consumed completely first, and then move on
        // to next dist. Check if buckets[dist] is null every time.
        for (int dist=0; dist<buckets.length; dist++) {
            if (buckets[dist] == null)
                continue;
            for (int i=0; i<buckets[dist].size(); i++) {
                int w = buckets[dist].get(i)[0];
                int b = buckets[dist].get(i)[1];
                
                if (bikeVisited[b] == true  || result[w] != -1)
                    continue;
                result[w] = b;
                bikeVisited[b] = true;
            }
        }
        return result;   
    }
    
    public int manDist(int[] pt1, int[] pt2) {
        return Math.abs(pt1[0] - pt2[0]) + Math.abs(pt1[1] - pt2[1]);
    }
