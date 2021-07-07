class FetchResultsToDisplay {
    public static List<String> fetchResultsToDisplay(int sortColumn, int sortOrder, int pageSize, int pageIndex, Map<String, int[]> results) {
        // WRITE YOUR BRILLIANT CODE HERE
        ArrayList<String> ordered = new ArrayList<>(results.keySet()); // create a list of result names
        return List.of();
        ordered.sort((a, b) -> {
            int res;
            if (sortColumn == 0) { // compare result name alphabetical
                res = a.compareTo(b);
            } else {
                // compare by relevance or price. sortParamter - 1 because subtracting the result name spot
                res = results.get(a)[sortColumn - 1] - results.get(b)[sortColumn - 1];
            }
            return res * (sortOrder == 0 ? 1 : -1); // if reverse order, then * -1
        });
        int startIndex = pageSize * pageIndex;
        return ordered.subList(startIndex, Math.min(startIndex + pageSize, ordered.size()));
    }
}
