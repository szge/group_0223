//Author:Arsham
import java.util.HashMap;
import java.util.ArrayList;

public class MemoManager {

    private ArrayList<Memo> store; //holds a list of the Memos

    public MemoManager(ArrayList<> store){
        this.store = store;
    }

    public Memo addMemo(int id, String content){
        //create a memo for event with id
        Memo newMemo = this.getContent(content);
        if (!this.contains(content)){
            newMemo = new Memo(content);
            this.store.add(newMemo);
        }
        newMemo.addEvent(id);
        return newMemo;
    }

    public void removeMemo(String content, int id){
        //remove the id of an event from a memo
        this.getContent(content).removeEvent(id); //Need a Memo method to remove the id of a Memo
        if (this.getContent(content).getEvents().size() == 0){
            this.store.remove(content);
        }
        //Do we want to remove a memo that isn't associated with any events anymore?
    }

    public boolean contains(String content){
        for (int i = 0; i < this.store.size(); i++) {
            if (this.store.get(i).toString().equals(content)){
                return true;
            }
        }
        return false;
    }

    public Memo getContent(String content){
        for (int i = 0; i < this.store.size(); i++) {
            if (this.store.get(i).toString().equals(content)){
                return this.store.get(i);
            }
        }
        return null;
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

