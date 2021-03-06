class Solution {
    public int promotionCheck(List<List<String>> codes, List<String> shoppingCart){
        int listIndex = 0;
        int listItemIndex = 0;
        boolean currListMatches = false;       
        List<String> currList = codes.get(listIndex++);

        for(String item : shoppingCart) {
            String code = currList.get(listItemIndex);
            if(currListMatches && !itemsMatch(code, item)) {
                listItemIndex = 0; //reset to current code list starting position
            } else if(itemsMatch(code, item)) {
                currListMatches = true;
                listItemIndex++;
                if(listItemIndex == currList.size()) {
                    if(listIndex == codes.size()){
                        return 1; //all the codes matched
                    }
                    currList = codes.get(listIndex++); //move on to next code list
                    currListMatches = false;
                    listItemIndex = 0;
                }
            }
        }
        return 0;
    }

    private boolean itemsMatch(String code, String item){
        if("anything".equals(code)) return true;
        else if(item.equals(code)) return true;
        else return false;
    }
}