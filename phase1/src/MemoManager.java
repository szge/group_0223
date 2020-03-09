//Author:Arsham
import java.util.HashMap;
import java.util.ArrayList;

public class MemoManager {

    private HashMap<String, Memo> store; //holds a list of the Memos

    public MemoManager(){
        this.store = new HashMap<String, Memo>();
    }

    public Memo addMemo(int id, String content){
        //create a memo for event with id
        Memo newMemo = null;
        if (!this.store.containsKey(content)){
            newMemo = new Memo(content);
            CalendarDataFacade.addMemo(newMemo);
            this.store.put(content, newMemo);
        }
        this.store.get(content).addEvent(id);
        return newMemo;
    }

    public void removeMemo(String content, int id){
        //remove the id of an event from a memo
        this.store.get(content).removeEvent(id); //Need a Memo method to remove the id of a Memo
        if (this.store.get(content).getEvents().size() == 0){
            this.store.remove(content);
        }
        //Do we want to remove a memo that isn't associated with any events anymore?
    }

    public void removeEventMemo(Memo memo, int id){
        memo.removeEvent(id);
    } //remove a event id from a memo

    public void deleteMemo(Memo memo){
        this.store.remove(memo.toString());
    } //deletes a memo completely

    public void editName(Memo memo, String content){
        memo.changeName(content);
    } //edits the name of a memo

    public ArrayList<Integer> memoEvent(Memo memo){
        return memo.getEvents();
    } //returns the id of events that have a memo

}

