import java.util.HashMap;

private class MemoManager {

    private HashMap<String, Memo> store; //holds a list of the Memos

    public MemoManager(){
        this.store = new HashMap<String, Memo>();
    }

    public void addMemo(int id, String content){
        if (this.store.containsKey(content)){
            Memo newMemo = new Memo(content);
            this.store.put(content, newMemo);
        }
        this.store.get(content).addEvent(id);
    }

    public void removeMemo(String content, int id){
        this.store.get(content).removeEvent(id); //Need a Memo method to remove the id of a Memo
        if (this.store.get(content).getEvents().size() == 0){
            this.store.remove(content);
        }
        //Do we want to remove a memo that isn't associated with any events anymore?
    }

}
