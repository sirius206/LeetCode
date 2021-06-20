//1. DFS
// Time O(m * n) Space O(n)
class Solution {
    public double[] calcEquation(List<List<String>> equations, double[] values,
            List<List<String>> queries) {

        HashMap<String, HashMap<String, Double>> graph = new HashMap<>();

        // Step 1). build the graph from the equations
        for (int i = 0; i < equations.size(); i++) {
            List<String> equation = equations.get(i);
            String dividend = equation.get(0), divisor = equation.get(1);
            double quotient = values[i];

            if (!graph.containsKey(dividend))
                graph.put(dividend, new HashMap<String, Double>());
            if (!graph.containsKey(divisor))
                graph.put(divisor, new HashMap<String, Double>());

            graph.get(dividend).put(divisor, quotient);
            graph.get(divisor).put(dividend, 1 / quotient);
        }

        // Step 2). Evaluate each query via bactracking (DFS)
        // by verifying if there exists a path from dividend to divisor
        double[] results = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            List<String> query = queries.get(i);
            String dividend = query.get(0), divisor = query.get(1);

            if (!graph.containsKey(dividend) || !graph.containsKey(divisor))
                results[i] = -1.0;
            else if (dividend == divisor)
                results[i] = 1.0;
            else {
                HashSet<String> visited = new HashSet<>();
                results[i] = backtrackEvaluate(graph, dividend, divisor, 1, visited);
            }
        }

        return results;
    }

    private double backtrackEvaluate(HashMap<String, HashMap<String, Double>> graph, String currNode, String targetNode, double accProduct, Set<String> visited) {

        // mark the visit
        visited.add(currNode);
        double ret = -1.0;

        Map<String, Double> neighbors = graph.get(currNode);
        if (neighbors.containsKey(targetNode))
            ret = accProduct * neighbors.get(targetNode);
        else {
            for (Map.Entry<String, Double> pair : neighbors.entrySet()) {
                String nextNode = pair.getKey();
                if (visited.contains(nextNode))
                    continue;
                ret = backtrackEvaluate(graph, nextNode, targetNode,
                        accProduct * pair.getValue(), visited);
                if (ret != -1.0)
                    break;
            }
        }

        // unmark the visit, for the next backtracking
        // visited.remove(currNode); // could remove this line since it's dfs and not backtracking
        return ret;
    }
}        
        

//2. Union find, Union-Find data structure is more efficient for the repetitive/redundant query scenario.
// Time O((M+N)⋅log ∗ N), Space O(n)
class Solution {

    public double[] calcEquation(List<List<String>> equations, double[] values,
            List<List<String>> queries) {

        HashMap<String, Pair<String, Double>> gidWeight = new HashMap<>();

        // Step 1). build the union groups
        for (int i = 0; i < equations.size(); i++) {
            List<String> equation = equations.get(i);
            String dividend = equation.get(0), divisor = equation.get(1);
            double quotient = values[i];

            union(gidWeight, dividend, divisor, quotient);
        }

        // Step 2). run the evaluation, with "lazy" updates in find() function
        double[] results = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            List<String> query = queries.get(i);
            String dividend = query.get(0), divisor = query.get(1);

            if (!gidWeight.containsKey(dividend) || !gidWeight.containsKey(divisor))
                // case 1). at least one variable did not appear before
                results[i] = -1.0;
            else {
                Pair<String, Double> dividendEntry = find(gidWeight, dividend);
                Pair<String, Double> divisorEntry = find(gidWeight, divisor);

                String dividendGid = dividendEntry.getKey();
                String divisorGid = divisorEntry.getKey();
                Double dividendWeight = dividendEntry.getValue();
                Double divisorWeight = divisorEntry.getValue();

                if (!dividendGid.equals(divisorGid))
                    // case 2). the variables do not belong to the same chain/group
                    results[i] = -1.0;
                else
                    // case 3). there is a chain/path between the variables
                    results[i] = dividendWeight / divisorWeight;
            }
        }

        return results;
    }

    private Pair<String, Double> find(HashMap<String, Pair<String, Double>> gidWeight, String nodeId) {
        if (!gidWeight.containsKey(nodeId))
            gidWeight.put(nodeId, new Pair<String, Double>(nodeId, 1.0));

        Pair<String, Double> entry = gidWeight.get(nodeId);
        // found inconsistency, trigger chain update
        if (!entry.getKey().equals(nodeId)) {
            Pair<String, Double> newEntry = find(gidWeight, entry.getKey());
            gidWeight.put(nodeId, new Pair<String, Double>(
                    newEntry.getKey(), entry.getValue() * newEntry.getValue()));
        }

        return gidWeight.get(nodeId);
    }

    private void union(HashMap<String, Pair<String, Double>> gidWeight, String dividend, String divisor, Double value) {
        Pair<String, Double> dividendEntry = find(gidWeight, dividend);
        Pair<String, Double> divisorEntry = find(gidWeight, divisor);

        String dividendGid = dividendEntry.getKey();
        String divisorGid = divisorEntry.getKey();
        Double dividendWeight = dividendEntry.getValue();
        Double divisorWeight = divisorEntry.getValue();

        // merge the two groups together,
        // by attaching the dividend group to the one of divisor
        if (!dividendGid.equals(divisorGid)) {
            gidWeight.put(dividendGid, new Pair<String, Double>(divisorGid,
                    divisorWeight * value / dividendWeight));
        }
    }
}
